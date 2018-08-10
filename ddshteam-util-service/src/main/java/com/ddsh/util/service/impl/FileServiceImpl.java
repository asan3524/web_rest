package com.ddsh.util.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddsh.util.service.api.IFileService;
import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.FileInfo;
import com.ddsh.util.service.api.model.AttAttachmentInfo;
import com.ddsh.util.service.api.model.AttAttachmentInfoCriteria;
import com.ddsh.util.service.dao.AttAttachmentInfoMapper;
import com.ddsh.util.service.util.OSUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(version = "1.0.0")
@Transactional(noRollbackFor=RuntimeException.class)
public class FileServiceImpl implements IFileService {

	@Autowired
	AttAttachmentInfoMapper attAttachmentInfoMapper;
	
	@Override
	public FileInfo addFileInfo(FileInfo fileInfo) {
		
		if(fileInfo==null)
		{
			return null;
		}
		
		AttAttachmentInfo att=convertFileInfoToAtt(fileInfo);
		int vlaue=attAttachmentInfoMapper.insert(att);
		if(vlaue>0) {
			return convertAttToFileinfo(att);
		}
		return null;
	}

	@Override
	public FileInfo getFileInfoByid(String id) {
		AttAttachmentInfo attAttachmentInfo=attAttachmentInfoMapper.selectByPrimaryKey(id);
		FileInfo fileInfo=new FileInfo();
		if(attAttachmentInfo!=null)
		{
			return convertAttToFileinfo(attAttachmentInfo);
		}
		return fileInfo;
	}

	@Override
	public List<FileInfo> addFileInfo(List<FileInfo> fileInfos) {
		if(fileInfos==null||fileInfos.size()==0)
		{
		  return null;
		}
		List<AttAttachmentInfo> records=new ArrayList<AttAttachmentInfo>();
		
		for(FileInfo fileInfo:fileInfos)
		{
			AttAttachmentInfo att=convertFileInfoToAtt(fileInfo);
			records.add(att);
            convertAttToFileinfo(att,fileInfo);
		}
		
		if(records==null||records.size()==0)
		{
		  return null;
		}
		
		int vlaue=attAttachmentInfoMapper.insertBatchSelective(records);
		if( vlaue>0)
		{
			return fileInfos;
		}
		
		return null;
	}
	
	
	private AttAttachmentInfo convertFileInfoToAtt(FileInfo fileInfo)
	{
		AttAttachmentInfo attAttachmentInfo=new AttAttachmentInfo();
		attAttachmentInfo.setId(UUID.randomUUID().toString());
		attAttachmentInfo.setFileName(fileInfo.getFilename());
		attAttachmentInfo.setFileSize(fileInfo.getFileSize().intValue());
		attAttachmentInfo.setFileType(fileInfo.getFileType());
		attAttachmentInfo.setObjId(fileInfo.getBussnessObjId());
		attAttachmentInfo.setPath(fileInfo.getPath());
		attAttachmentInfo.setStatus(fileInfo.getStatus());
		attAttachmentInfo.setTableName(fileInfo.getTableName());
		attAttachmentInfo.setType(fileInfo.getType());
		attAttachmentInfo.setUpdateTime(new Date());
		attAttachmentInfo.setUploadUserid(fileInfo.getUserid());
		attAttachmentInfo.setUploadUsername(fileInfo.getUsername());
		attAttachmentInfo.setName(fileInfo.getName());
		attAttachmentInfo.setRemark(fileInfo.getRemark());
		
		return attAttachmentInfo;
	}
    private  FileInfo convertAttToFileinfo(AttAttachmentInfo attAttachmentInfo,FileInfo fileInfo)
    {
        fileInfo.setId(attAttachmentInfo.getId());
        fileInfo.setBussnessObjId(attAttachmentInfo.getObjId());
        fileInfo.setFilename(attAttachmentInfo.getFileName());
        fileInfo.setFileSize(attAttachmentInfo.getFileSize().longValue());
        fileInfo.setFileType(attAttachmentInfo.getFileType());
        fileInfo.setStatus(attAttachmentInfo.getStatus());
        fileInfo.setUserid(attAttachmentInfo.getUploadUserid());
        fileInfo.setUsername(attAttachmentInfo.getUploadUsername());
        fileInfo.setName(attAttachmentInfo.getName());
        fileInfo.setRemark(attAttachmentInfo.getRemark());
        return fileInfo;
    }

	private  FileInfo convertAttToFileinfo(AttAttachmentInfo attAttachmentInfo)
	{
		FileInfo fileInfo=new FileInfo();
		fileInfo.setId(attAttachmentInfo.getId());
		fileInfo.setBussnessObjId(attAttachmentInfo.getObjId());
		fileInfo.setFilename(attAttachmentInfo.getFileName());
		fileInfo.setFileSize(attAttachmentInfo.getFileSize().longValue());
		fileInfo.setFileType(attAttachmentInfo.getFileType());
		fileInfo.setStatus(attAttachmentInfo.getStatus());
		fileInfo.setUserid(attAttachmentInfo.getUploadUserid());
		fileInfo.setUsername(attAttachmentInfo.getUploadUsername());
		fileInfo.setName(attAttachmentInfo.getName());
		fileInfo.setRemark(attAttachmentInfo.getRemark());
		return fileInfo;
	}

	@Override
	public PageInfo<FileInfo> getFileInfo(FileInfo fileInfo,int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		AttAttachmentInfoCriteria attAttachmentInfoCriteria=new AttAttachmentInfoCriteria();
		
		attAttachmentInfoCriteria.setPageNo(pageNum);
		attAttachmentInfoCriteria.setPageSize(pageSize);
		attAttachmentInfoCriteria.setOrderByClause("update_time desc");
		
		List<FileInfo> fileInfos=new ArrayList<FileInfo>();
		PageInfo<FileInfo> pageInfos = new PageInfo<FileInfo>(fileInfos);

		if(fileInfo==null)
		{
			return pageInfos;
		}
		
		com.ddsh.util.service.api.model.AttAttachmentInfoCriteria.Criteria criteria=attAttachmentInfoCriteria.createCriteria();
		
		if(fileInfo.getBussnessObjId()!=null&&!fileInfo.getBussnessObjId().trim().equals(""))
		{
			criteria.andObjIdEqualTo(fileInfo.getBussnessObjId());
		}
		
		if(fileInfo.getFilename()!=null&&!fileInfo.getFilename().trim().equals(""))
		{
			criteria.andFileNameLike("%"+fileInfo.getFilename()+"%");
		}
		
		if(fileInfo.getName()!=null&&!fileInfo.getName().trim().equals(""))
		{
			criteria.andNameLike("%"+fileInfo.getName()+"%");
		}
		
		if(fileInfo.getFileType()!=null&&!fileInfo.getFileType().trim().equals(""))
		{
			criteria.andFileTypeEqualTo(fileInfo.getFileType());
		}
		
		if(fileInfo.getStatus()!=null)
		{
			criteria.andStatusEqualTo(fileInfo.getStatus());
		}
		
		if(fileInfo.getTableName()!=null&&!fileInfo.getTableName().trim().equals(""))
		{
			criteria.andTableNameEqualTo(fileInfo.getTableName());
		}
		
		if(fileInfo.getType()!=null&&!fileInfo.getType().trim().equals(""))
		{
			criteria.andTypeEqualTo(fileInfo.getType());
		}
		
		if(fileInfo.getUserid()!=null&&!fileInfo.getUserid().trim().endsWith(""))
		{
			criteria.andUploadUseridEqualTo(fileInfo.getUserid());
		}
		
		List<AttAttachmentInfo> attAttachmentInfos=attAttachmentInfoMapper.selectByExample(attAttachmentInfoCriteria);
		
		if(attAttachmentInfos!=null&&attAttachmentInfos.size()>0)
		{
			for(AttAttachmentInfo att:attAttachmentInfos)
			{
				fileInfos.add(convertAttToFileinfo(att));
			}
		}
		
 		return pageInfos;
	}

	@Override
	public boolean delFile(String id) {
		int result=attAttachmentInfoMapper.deleteByPrimaryKey(id);
		return result>0;
	}

	@Override
	public String getBasePath() {
		if(OSUtil.getSystemType()==UtilContants.OSType.LINUX)
		{
			return UtilContants.Sysset.BASE_L_ROOT;
		}
		else
		{
			return UtilContants.Sysset.BASE_W_ROOT;
		}
	}

}
