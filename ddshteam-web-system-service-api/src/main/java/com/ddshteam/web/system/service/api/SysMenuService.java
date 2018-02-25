package com.ddshteam.web.system.service.api;

import java.util.List;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenu;
import com.github.pagehelper.PageInfo;

/**
 * 
 * 菜单服务接口
 * <li>1.菜单管理: 添加菜单时选择父级菜单弹窗，不需要「功能」的全量树结构</li>
 * <li>2.角色管理: 
 *          2.1添加角色时需要勾选菜单权限，树结构需全量返回(注意保存到role-to-menu表的是所有勾选的节点，不是必须叶子节点)
 *          2.2同上，但需带上角色已选中的状态     
 * </li>
 * <li>3.用户管理: 
 *          3.1用户左侧菜单列表: 权限n-n菜单(页面)的并集(如:A用户拥有2个角色，则页面是两个角色的并集，注意只查type=2)
 *          3.2用户按钮权限:用于shiro控制权限，同上(区别是只查type=3)
 * </li>
 * @ClassName: SysMenuService
 * @author duyu
 * @date 2018年2月18日 上午1:56:32
 * @version v1.0.0
 *
 */
public interface SysMenuService {

	/**
	 * 获取「菜单/目录/按钮」分页列表，可以给权限初始化系统使用
	 * @param pageNum  当前页索引
	 * @param pageSize 分页大小
	 * @return
	 */
	public PageInfo<SysMenu> getMenuList(int pageNum, int pageSize);

	/**
	 * 获取所有非功能菜单树结构，提供给内置账户使用（只返回目录及页面，功能点不返回）
	 * 作用：添加菜单时选择父级菜单弹窗
	 * {id,name,url,iconClass,children}
	 * @Title: getMenuTree
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getMenuTree();
	
	/**
	 * 获取所有菜单的树结构(包含功能点)
	 * 作用：添加角色时需要勾选菜单权限
	 * {id,name,url,iconClass,children}
	 * @Title: getAllMenuTree
	 * @return List<Tree>
	 * @author duyu
	 */
	public List<Tree> getAllMenuTree();

	/**
	 * 获取当前登录用户菜单树结构（只返回目录及页面，功能点不返回）
	 * 作用：当前用户的左侧菜单(type!=3)
	 * {id,name,url,iconClass,children}
	 * @Title: getMenuTree
	 * @param userId
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getMenuTreeByUser(String userId);
	
	/**
	 * 获取用户的权限(type=3)
	 * @Title: getPermissionTreeByUser
	 * @param userId
	 * @return List<String>
	 * @author duyu
	 */
	public List<String> getPermissionByUser(String userId);

	/**
	 * 获取指定角色的菜单可选（带可选标记）树结构（目录、页面、功能）
	 * {id,name,url,iconClass,children,disabled,isLeaf,checkStatus=1/0}
	 * isLeaf=true时为叶子节点，checkStatus才有意义
	 * @Title: getMenuTreeByRole
	 * @param roleId 角色ID 为空时查询
	 * @return List<Tree>
	 * @author lishibang
	 */
	public List<Tree> getMenuTreeByRole(String roleId);

	/**
	 * 根据id获取「菜单/目录/按钮」详情
	 * @param menuId
	 * @return
	 */
	public SysMenu getMenuById(String menuId);
	
	/**
	 * 批量获取菜单详情
	 * @Title: getMenuByIds
	 * @param menuIds
	 * @return List<SysMenu>
	 * @author duyu
	 */
	public List<SysMenu> getMenuByIds(String... menuIds);

	/**
	 * 保存「菜单/目录/按钮」
	 * @param sysMenu
	 * @return
	 */
	public boolean saveMenu(SysMenu sysMenu);

	/**
	 * 更新「菜单/目录/按钮」
	 * @param sysMenu
	 * @return
	 */
	public boolean updateMenu(SysMenu sysMenu);

	/**
	 * 根据id删除「菜单/目录/按钮」
	 * <p>删除role2menu中间表</p>
	 * @param menuId
	 * @return
	 */
	public boolean deleteMenuById(String menuId);
}
