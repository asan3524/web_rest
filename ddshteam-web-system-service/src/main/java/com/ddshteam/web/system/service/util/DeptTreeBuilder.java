package com.ddshteam.web.system.service.util;

import java.util.List;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysDepInfo;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

public class DeptTreeBuilder {

	public static List<Tree> build(List<SysDepInfo> list, String rootDeptId) {

		List<Tree> result = Lists.newArrayList();

		// 未指定根部门
		if (StringUtils.isNullOrEmpty(rootDeptId)) {

			for (SysDepInfo dept : list) {
				// root
				if (StringUtils.isNullOrEmpty(dept.getParentId())) {
					// {id,name,url,iconClass,children}
					Tree tree = Tree.builder().id(dept.getId()).name(dept.getName()).parentId(dept.getParentId())
							.url(null).icon(null).disabled(dept.getStatus() == 0).isLeaf(false).checked(false)
							.children(Lists.newArrayList()).build();

					if (ifHasChild(list, dept.getId())) {
						List<Tree> children = getChildren(list, dept.getId());
						tree.setChildren(children);
					} else {
						tree.setIsLeaf(true);
					}

					result.add(tree);
				}
			}
		}
		// 指定根部门
		else {
			for (SysDepInfo dept : list) {
				String deptId = dept.getId();

				if (!StringUtils.isNullOrEmpty(deptId) && deptId.equals(rootDeptId)) {
					Tree tree = Tree.builder().id(dept.getId()).name(dept.getName()).parentId(dept.getParentId())
							.url(null).icon(null).disabled(dept.getStatus() == 0).isLeaf(false).checked(false)
							.children(Lists.newArrayList()).build();

					if (ifHasChild(list, dept.getId())) {
						List<Tree> children = getChildren(list, dept.getId());
						tree.setChildren(children);
					} else {
						tree.setIsLeaf(true);
					}

					result.add(tree);
					break;
				}
			}

		}

		return result;
	}

	public static List<Tree> childrenBuild(List<SysDepInfo> list) {

		List<Tree> result = Lists.newArrayList();

		for (SysDepInfo dept : list) {
			// {id,name,url,iconClass,children}
			Tree tree = Tree.builder().id(dept.getId()).name(dept.getName()).parentId(dept.getParentId()).url(null)
					.icon(null).disabled(dept.getStatus() == 0).isLeaf(false).checked(false)
					.children(Lists.newArrayList()).build();

			if (ifHasChild(list, dept.getId())) {
				List<Tree> children = getChildren(list, dept.getId());
				tree.setChildren(children);
			} else {
				tree.setIsLeaf(true);
			}

			result.add(tree);

		}

		return result;
	}

	public static List<Tree> getChildren(List<SysDepInfo> list, String pid) {

		List<Tree> children = Lists.newArrayList();

		for (SysDepInfo dept : list) {
			String p_id = dept.getParentId();
			if (!StringUtils.isNullOrEmpty(p_id) && p_id.equals(pid)) {
				Tree tree = Tree.builder().id(dept.getId()).name(dept.getName()).parentId(dept.getParentId()).url(null)
						.icon(null).disabled(dept.getStatus() == 0).isLeaf(false).checked(false)
						.children(Lists.newArrayList()).build();
				if (ifHasChild(list, dept.getId())) {
					List<Tree> c = getChildren(list, dept.getId());
					tree.setChildren(c);
				} else {
					tree.setIsLeaf(true);
				}
				children.add(tree);
			}
		}
		return children;
	}

	public static boolean ifHasChild(List<SysDepInfo> list, String pid) {
		boolean flag = false;
		for (SysDepInfo r : list) {
			String p_id = r.getParentId();
			if (!StringUtils.isNullOrEmpty(p_id) && p_id.equals(pid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
