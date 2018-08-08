package com.ddshteam.web.controller.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ddsh.util.service.api.IExcelService;
import com.ddsh.util.service.api.IFileService;
import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.ExcelImportReqData;
import com.ddsh.util.service.api.data.ExcelImportRespData;
import com.ddsh.util.service.api.data.FileInfo;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Api(value = "/util/excel", description = "excel-工具类")
@RestController
@RequestMapping(value = "/util/excel")
public class ExcelUtilController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(ExcelUtilController.class);

	
	@Reference(version = "1.0.0")
	IExcelService excelService;
	
	@Reference(version = "1.0.0")
	IFileService fileService;
	
	@ApiOperation(value = "excel文件导入", notes = "excel文件导入")
	@PostMapping(value = { "/import" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_EXCEL_IMPORT_FILE)
	public Object importExcel(@RequestParam ExcelImportReqData reqdata, BindingResult errors) {
		logger.debug("ExcelUtilController.importExcel()");

		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		if(StringUtils.isEmpty(reqdata.getFileid())) {
			logger.error("fileid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		FileInfo fileinfo=fileService.getFileInfoByid(reqdata.getFileid());
		if(fileinfo==null)
		{
			logger.error("fileinfo query is null.");
			return getResponse(HttpCode.NOT_FOUND, false);
		}
		ExcelImportRespData respdata=excelService.importExcel(fileinfo, reqdata);
 		return getResponse(respdata);
	}
	
	
	@ApiOperation(value = "excel文件导出", notes = "excel文件导出")
	@PostMapping(value = { "/export" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_EXCEL_EXPORT_FILE)
	public Object exportExcel(HttpServletResponse response, HttpServletRequest request) {
		logger.debug("ExcelUtilController.exportExcel()");
		response.setStatus(HttpCode.OK.value());

		String params = null;
		String[] items = null;
		JSONArray groupArr = null;
		JSONArray columnArr = null;
		if (StringUtils.isEmpty(params) || params.endsWith("null") || params == null) {
			logger.error("params is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}

		JSONObject jsonObjectArgs = JSONObject.parseObject(params);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
		} catch (Exception e) {
		}
		String fileName = jsonObjectArgs.getString(UtilContants.ExcelExport.FILE_NAME);
		if (StringUtils.isEmpty(fileName) || fileName.equals("null")) {
			fileName = UtilContants.ExcelExport.EXPORT_DEFAULT_NAME;
		}

		String sheetName = jsonObjectArgs.getString(UtilContants.ExcelExport.SHEET_NAME);
		if (StringUtils.isEmpty(sheetName) || sheetName.equals("null")) {
			sheetName = UtilContants.ExcelExport.EXPORT_DEFAULT_NAME;
		}

		if (!fileName.toLowerCase().endsWith(UtilContants.ExcelExport.EXPORT_EXT))
			;
		{
			fileName = fileName + UtilContants.ExcelExport.EXPORT_EXT;
		}

		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		// 创建sheet页
		WritableWorkbook wbook = null;
		try {
			wbook = Workbook.createWorkbook(os);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		WritableSheet wsheet = wbook.createSheet(sheetName, 0);

		// 获取分组数据和表头数据
		groupArr = jsonObjectArgs.getJSONArray("groups");
		columnArr = jsonObjectArgs.getJSONArray("columns");

		// 获取并分析表头内容
		int arrSize = columnArr.size();

		String[] columns = new String[arrSize];
		String[] columnHeaders = new String[arrSize];
		for (int i = 0; i < arrSize; i++) {
			JSONObject column = columnArr.getJSONObject(i);
			columns[i] = column.getString("name");
			columnHeaders[i] = column.getString("caption");
		}

		try {
			// 绘制表头等
			renderHeader(wsheet, jsonObjectArgs, groupArr, columnHeaders);
		} catch (WriteException e) {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
				}
			}

			logger.error("绘制表头发生错误:" + e.getMessage());
			return getResponse(HttpCode.BAD_REQUEST, false, e.getMessage());
		}

		try {
			// 分组数
			int groupSize = groupArr.size();
			if (groupSize == 0) {// 只有Columns时，补差
				groupSize = 1;
			}

			// 获取请求的数据并写入到文件中
			JSONArray array = null;// resultValue.getJSONArray("items");
			int length = 0;// array.size();
			JSONObject item;
			int columnsLength = columns.length;
			String column, value;

			// 获取表头样式
			WritableCellFormat contextFormat = getContextFormat();

			JSONObject _data = null;
			JSONObject _resultValue = null;
			int y = 0; // 记录Y方向坐标
			for (int j = 0; j < items.length; j++) {
				// 由于前段传入字符串过长会被系统截断，固分次传入
				_data = JSONObject.parseObject(items[j]);
				_resultValue = _data.getJSONObject("resultValue");
				array = _resultValue.getJSONArray("items");
				length = array.size();
				for (int i = 0; i < length; i++, y++) {
					item = array.getJSONObject(i);
					// 逐列写
					for (int m = 0; m < columnsLength; m++) {
						column = columns[m];
						// value = item.getString(column);
						value = item.get(column) != null ? item.get(column).toString() : ""; // String.valueOf(
																								// item.get(column));

						if (StringUtils.isEmpty(value) || "null".equals(value)) {
							value = "";
						}

						// 写入数据
						Label cell = new Label(m, y + groupSize, value, contextFormat);
						wsheet.addCell(cell);
					}
				}
			}

			// 写入并关闭
			wbook.write(); // 写入文件
			wbook.close();
			os.flush();
			os.close();

			// 成功后设置返回值状态为OK
			response.setStatus(response.SC_OK);
		} catch (WriteException e) {
			response.setStatus(response.SC_EXPECTATION_FAILED);
			logger.error("绘制表数据发生错误:" + e.getMessage());
			return getResponse(HttpCode.BAD_REQUEST, false, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// 设置返回状态并刷新（非正常中断时有用）
		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request;

	}

	/**
	 * 绘制表头
	 * 
	 * @param wsheet 指定excel的sheet页
	 * @param args 前段传入的json数据对象
	 * @param groupArr 数据分组
	 * @param columnHeaders 数据表的列头
	 * 
	 * @throws WriteException
	 */
	public void renderHeader(WritableSheet wsheet, JSONObject args, JSONArray groupArr, String[] columnHeaders)
			throws WriteException {
		// 获取表头样式
		WritableCellFormat titleFormat = getTitleFormat();

		// 设定列宽
		JSONArray widthArr = args.getJSONArray("widths");
		int size = widthArr.size();
		for (int i = 0; i < size; i++) {
			wsheet.setColumnView(i, widthArr.getIntValue(i));
		}

		// 开始写入分组信息
		int groupSize = groupArr.size();
		int rowSpan, colSpan, leftIndex;
		JSONObject group, obj;
		JSONArray items;
		// 按行写入
		for (int i = 0; i < groupSize; i++) {
			group = groupArr.getJSONObject(i);
			items = group.getJSONArray("items");
			int lth = items.size();
			// 按列写入
			for (int m = 0; m < lth; m++) {
				obj = items.getJSONObject(m);
				String text = obj.getString("caption");

				rowSpan = obj.getIntValue("rowspan");
				colSpan = obj.getIntValue("colspan");
				leftIndex = obj.getIntValue("leftindex");

				// 合并单元格
				wsheet.mergeCells(leftIndex, i, leftIndex + colSpan - 1, i + rowSpan - 1);
				// 写入单元格
				Label excelTitle = new Label(leftIndex, i, text, titleFormat);
				wsheet.addCell(excelTitle);
			}
		}

		// 在有group的时候，默认不再显示column，这样避免重复显示。这适用于正常情况
		if (groupSize == 0) {
			// 开始写入表头...
			for (int i = 0; i < columnHeaders.length; i++) {
				Label excelTitle = new Label(i, groupSize, columnHeaders[i], titleFormat);
				wsheet.addCell(excelTitle);
			}
		}
	}

	/**
	 * 获取列头样式，开发人员可通过重写该方法实现改变表头样式
	 * 
	 * @return WritableCellFormat
	 * @throws WriteException
	 */
	public WritableCellFormat getTitleFormat() throws WriteException {
		// 设置表头字体
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, jxl.format.ScriptStyle.SUBSCRIPT);
		// 设置表头样式
		WritableCellFormat titleFormat = new WritableCellFormat(wfont);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		titleFormat.setBackground(Colour.GREY_25_PERCENT);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setWrap(false);
		return titleFormat;
	}

	/**
	 * 获取内容的样式，开发人员可通过重写该方法实现改变内容样式
	 * 
	 * @return WritableCellFormat
	 * @throws WriteException
	 */
	public WritableCellFormat getContextFormat() throws WriteException {
		// 设置表头字体
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, jxl.format.ScriptStyle.SUBSCRIPT);
		// 设置表头样式
		WritableCellFormat titleFormat = new WritableCellFormat(wfont);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setWrap(true); // 设置自动换行
		return titleFormat;
	}
}
