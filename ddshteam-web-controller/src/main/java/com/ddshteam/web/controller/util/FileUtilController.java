package com.ddshteam.web.controller.util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ddsh.util.service.api.IFileService;
import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.FileInfo;
import com.ddsh.util.service.api.data.FileUploadReqData;
import com.ddshteam.web.core.base.BaseController;
import com.ddshteam.web.core.support.HttpCode;
import com.ddshteam.web.system.service.api.model.SysUserInfo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/util/file", description = "文件-工具类")
@RestController
@RequestMapping(value = "/util/file")
public class FileUtilController  extends BaseController{
	private final static Logger logger = LoggerFactory.getLogger(FileUtilController.class);

	
	@Reference(version = "1.0.0")
	IFileService fileService;
	
	@ApiOperation(value = "文件上传", notes = "文件上传")
	@PostMapping(value = { "/upload" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_FILE_UPLOAD)
	public Object fileUpload(@RequestParam MultipartFile[] upload,@RequestParam String reqdata,BindingResult errors) {
		logger.debug("FileUtilController.fileUpload()");
		
		if(StringUtils.isEmpty(reqdata)) {
			logger.error("reqdata is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		if (upload==null||errors.hasErrors()||upload.length<1) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		List<FileUploadReqData> reqdatas=(List<FileUploadReqData>) JSON.parse(reqdata);

		Subject subject = SecurityUtils.getSubject();
		SysUserInfo user = (SysUserInfo) subject.getPrincipals().getPrimaryPrincipal();

		if (StringUtils.isEmpty(user)) {
			return getResponse(HttpCode.UNAUTHORIZED, false);
		}
		
		List<FileInfo> fileInfos=new ArrayList<FileInfo>();
		int i=0;
		for(MultipartFile multipartFile:upload)
		{
			if(multipartFile.getSize()>0)
			{
				FileUploadReqData fileData=reqdatas.get(i);
				i++;
				String fileName=multipartFile.getOriginalFilename();
				fileData.setFileName(fileName);
				File file=new File(getRealPath(fileData));
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					logger.error(e.getMessage());
					return getResponse(HttpCode.BAD_REQUEST, false, e.getMessage());
				} catch (IOException e) {
					logger.error(e.getMessage());
					return getResponse(HttpCode.BAD_REQUEST, false, e.getMessage());
				}
				
 				FileInfo fileInfo=new FileInfo();
				fileInfo.setBussnessObjId(fileData.getBussnessObjId());
				fileInfo.setFilename(fileName);
				fileInfo.setPath(file.getPath());
				fileInfo.setFileSize(multipartFile.getSize()/1024/1024);
				fileInfo.setFileType(fileData.getFileType());
				fileInfo.setStatus(UtilContants.Status.EFFECT);
				fileInfo.setTableName(fileData.getAttType());
				fileInfo.setType(fileData.getAttSubType());
				fileInfo.setUserid(user.getId());
				fileInfo.setUsername(user.getName());
				fileInfos.add(fileInfo);
			}
		}
		
		List<FileInfo> result=fileService.addFileInfo(fileInfos);
		return getResponse(result);
	}
	
	
	@ApiOperation(value = "文件下载", notes = "文件下载")
	@PostMapping(value = { "/download" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_FILE_DOWNLOAD)
	public Object filedownload(@RequestParam FileUploadReqData reqdata,HttpServletResponse respone, BindingResult errors) {
		logger.debug("FileUtilController.filedownload()");
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		File file=new File(getRealPath(reqdata));
		if(!file.exists())
		{
			logger.error("文件不存在!");
			return getResponse(HttpCode.NOT_FOUND, false, "文件不存在!");
		}
		
		try {
			respone.getOutputStream().write(FileUtils.readFileToByteArray(file));
		} catch (IOException e) {
			logger.error(e.getMessage());
			return getResponse(HttpCode.INTERNAL_SERVER_ERROR, false, e.getMessage());
		}
 
		return getResponse(HttpCode.OK, true);
	}
	
	
	@ApiOperation(value = "获取文件列表", notes = "获取文件列表")
	@PostMapping(value = { "/fileinolist" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_FILE_LIST)
	public Object fileList(@RequestParam FileInfo FileInfo,@PageableDefault(page = 1, size = 10) Pageable pageable, BindingResult errors) {
		logger.debug("FileUtilController.fileList()");
		if (errors.hasErrors()) {
			String msg = errors.getAllErrors().get(0).getDefaultMessage();
			logger.error(msg);
			return getResponse(HttpCode.BAD_REQUEST, false, msg);
		}
		PageInfo<com.ddsh.util.service.api.data.FileInfo> fileInfos=fileService.getFileInfo(FileInfo,pageable.getPageNumber(), pageable.getPageSize());
		return getResponse(fileInfos);
	}
	
	@ApiOperation(value = "获取文件信息", notes = "获取文件信息")
	@GetMapping(value = { "/fileinfo/{objid}" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_FILE_INFO)
	public Object fileinfo(@PathVariable("objid") String objid) {
		logger.debug("FileUtilController.fileinfo()");
		if(StringUtils.isEmpty(objid)) {
			logger.error("objid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		FileInfo fileInfo=fileService.getFileInfoByid(objid);
		
 		return getResponse(fileInfo);
	}
	
	@ApiOperation(value = "获取文件信息", notes = "获取文件信息")
	@GetMapping(value = { "/del/{objid}" })
	@RequiresPermissions(UtilContants.Permission.PERMISSION_FILE_DEL)
	public Object fileDel(@PathVariable("objid") String objid) {
		logger.debug("FileUtilController.fileDel()");
		if(StringUtils.isEmpty(objid)) {
			logger.error("objid is null.");
			return getResponse(HttpCode.BAD_REQUEST, false);
		}
		
		FileInfo fileInfo=fileService.getFileInfoByid(objid);
		if(fileInfo.getPath()!=null)
		{
			File file=new File(fileInfo.getPath());
			if(file.exists())
			{
				if(file.delete())
				{
					fileService.delFile(fileInfo.getId());
				}
			}
		}
 		return getResponse(fileInfo);
	}
	
	
	private String getRealPath(FileUploadReqData reqdata)
	{
		return UtilContants.Sysset.UPLOAD_ROOT_PATH+File.pathSeparator+reqdata.getType()+File.pathSeparator+reqdata.getBussnessObjId()+File.pathSeparator+reqdata.getFileName();
	}
}
