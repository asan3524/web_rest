package com.ddshteam.web.system.service.api;

import java.util.List;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDept;

public interface SysDeptService {

	/**
	 * 获取部门及子部门的树结构
	 * @Title: getDeptTreeByUser
	 * @param lazy 延迟加载（一次返回一级）默认false
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getDeptTree(boolean lazy);
	
	/**
	 * 获取当前登录用户的部门及子部门
	 * @Title: getDeptTree
	 * @param userId 当前登录用户ID
	 * @param lazy 延迟加载（一次返回一级）默认false
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getDeptTree(String userId, boolean lazy);
	
	/**
	 * 获取直接子部门
	 * @Title: getChildrenDeptList
	 * @param deptId
	 * @return List<Tree>
	 * @author duyu
	 */
	public List<Tree> getChildrenDeptList(String deptId);

	/**
	 * 获取部门详情列表(包含部门下的人员)
	 * <p>用于展示部门树</p>
	 * @return
	 */
	@Deprecated
	public List<SysDept> getSysDeptDetailList();

	/**
	 * 获取部门详情(包含部门人员)
	 * @param deptId  部门id
	 * @return
	 */
	public SysDept getSysDeptById(String deptId);

	/**
	 * 添加部门
	 * @param sysDept
	 * @return
	 */
	public boolean saveDept(SysDept sysDept);

	/**
	 * 批量导入部门
	 * @param sysDepts
	 * @return
	 */
	public boolean saveDept(List<SysDept> sysDepts);

	/**
	 * 更新部门
	 * @param sysDept
	 * @return
	 */
	public boolean updateDept(SysDept sysDept);

	/**
	 * 根据id删除部门
	 * <p>注意部门下如果有至少一人则不允许删除</>
	 * @param deptId  部门id
	 * @return
	 */
	public boolean deleteDept(String deptId);

	/**
	 * 部门下是否有人员
	 * @param deptId  部门id
	 * @return
	 */
	public boolean isDeptHasUser(String deptId);
}
