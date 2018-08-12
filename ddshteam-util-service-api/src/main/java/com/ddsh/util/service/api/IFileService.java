package com.ddsh.util.service.api;

import java.util.List;

import com.ddsh.util.service.api.data.FileInfo;
import com.ddsh.util.service.api.model.AttAttachmentInfo;
import com.github.pagehelper.PageInfo;

/**
 * 文件服务
 * @ClassName: IFileService
 * @author arpgate
 * @date 2018年6月13日 下午6:38:17
 * @version v1.0.0
 * 
 */
public interface IFileService {

	/**
	 * 增加文件信息
	 * @Title: addFileInfo
	 * @param fileInfo
	 * @return boolean
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public FileInfo addFileInfo(FileInfo fileInfo);
	public List<FileInfo> addFileInfo(List<FileInfo> fileInfos);
	 
	/**
	 * 获取文件信息
	 * @Title: getFileInfo
	 * @param fileInfo
	 * @return FileInfo
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public FileInfo getFileInfoByid(String id);
	
	public PageInfo<FileInfo> getFileInfo(FileInfo fileInfo,int pageNum, int pageSize);
	
	public boolean delFile(String id);
	
	
	/**
	 * 获取基础地址
	 * @Title: getBasePath
	 * @return String
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	public String getBasePath();
    public AttAttachmentInfo getfileinfo(String id);

}
