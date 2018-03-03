package com.ddshteam.web.system.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ddshteam.web.system.service.api.model.SysDept;

@Repository
//@Mapper
public interface SysDeptDao_back {
	
	/**
	 * 获取所有部门列表
	 * @Title: getDeptList
	 * @return List<SysDept>
	 * @author duyu
	 */
	public List<SysDept> getDeptList();
	
	/**
	 * 获取所有直接子部门
	 * @Title: getChildrenDeptList
	 * @param deptId
	 * @return List<SysDept>
	 * @author duyu
	 */
	public List<SysDept> getChildrenDeptList(@Param("deptId") String deptId);
	
	public SysDept getSysDeptById(@Param("deptId") String deptId);
	
	public int saveDept(SysDept sysDept);
	
	public int saveDeptList(List<SysDept> sysDepts);
	
	public int updateDept(SysDept sysDept);
	
	public int deleteDept(@Param("deptId") String deptId);
	
	public int isDeptHasUser(@Param("deptId") String deptId);
}
