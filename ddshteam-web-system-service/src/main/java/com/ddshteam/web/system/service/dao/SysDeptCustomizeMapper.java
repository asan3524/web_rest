package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.data.DeptInfoResp;
import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.ddshteam.web.system.service.api.model.SysDepInfoCriteria;

@Repository
//@Mapper
public interface SysDeptCustomizeMapper {
	
	/**
	 * 获取所有部门列表
	 * @Title: getDeptList
	 * @return List<SysDept>
	 * @author duyu
	 */
	public List<SysDepInfo> getDeptList();
	
	/**
	 * 获取所有直接子部门
	 * @Title: getChildrenDeptList
	 * @param deptId
	 * @return List<SysDept>
	 * @author duyu
	 */
	public List<SysDepInfo> getChildrenDeptList(@Param("deptId") String deptId);
	
	public SysDepInfo getSysDeptById(@Param("deptId") String deptId);
	
	public int saveDept(SysDepInfo sysDept);
	
	public int saveDeptList(List<SysDepInfo> sysDepts);
	
	public int updateDept(SysDepInfo sysDept);
	
	public int deleteDept(@Param("deptId") String deptId);
	
	public int isDeptHasUser(@Param("deptId") String deptId);
	
	List<Tree> selectTreeByPrimaryKey(String id);
	
    List<DeptInfoResp> selectByExample(SysDepInfoCriteria example);

    DeptInfoResp selectByPrimaryKey(String id);
    
    List<Tree> selectTreeByUserDefault(String id);
}
