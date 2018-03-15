package com.ddshteam.web.system.service.util;

import java.util.ArrayList;
import java.util.List;

import com.ddshteam.web.system.service.api.data.Tree;
import com.ddshteam.web.system.service.api.model.SysMenuInfo;
import com.google.common.collect.Lists;
import com.mysql.cj.core.util.StringUtils;

public class MenuTreeBuilder {

	public static List<Tree> build(List<SysMenuInfo> list) {
		List<Tree> result = Lists.newArrayList();

		for (SysMenuInfo menu : list) {
			// root
			if (StringUtils.isNullOrEmpty(menu.getParentId())) {
				// {id,name,url,iconClass,children}
				Tree tree = Tree.builder().id(menu.getId()).name(menu.getName()).parentId(menu.getParentId())
						.url(menu.getUrl()).icon(menu.getIcon()).disabled(false).isLeaf(false).checked(false)
						.type(menu.getType()).children(Lists.newArrayList()).build();

				if (ifHasChild(list, menu.getId())) {
					List<Tree> children = getChildren(list, menu.getId());
					tree.setChildren(children);
				} else {
					tree.setIsLeaf(true);
				}

				result.add(tree);
			}
		}

		return result;
	}

	public static List<Tree> getChildren(List<SysMenuInfo> list, String pid) {

		List<Tree> children = Lists.newArrayList();

		for (SysMenuInfo menu : list) {
			String p_id = menu.getParentId();
			if (!StringUtils.isNullOrEmpty(p_id) && p_id.equals(pid)) {
				Tree tree = Tree.builder().id(menu.getId()).name(menu.getName()).parentId(menu.getParentId())
						.url(menu.getUrl()).icon(menu.getIcon()).disabled(false).isLeaf(false).checked(false)
						.type(menu.getType()).children(Lists.newArrayList()).build();
				if (ifHasChild(list, menu.getId())) {
					List<Tree> c = getChildren(list, menu.getId());
					tree.setChildren(c);
				} else {
					tree.setIsLeaf(true);
				}
				children.add(tree);
			}
		}
		return children;
	}

	public static boolean ifHasChild(List<SysMenuInfo> list, String pid) {
		boolean flag = false;
		for (SysMenuInfo r : list) {
			String p_id = r.getParentId();
			if (!StringUtils.isNullOrEmpty(p_id) && p_id.equals(pid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static List<Tree> convert(List<Tree> trees) {
		if (null != trees && trees.size() > 0) {
			List<Tree> result = new ArrayList<Tree>();

			for (Tree t : trees) {
				if (StringUtils.isNullOrEmpty(t.getParentId())) {
					if (!t.isIsLeaf()) {
						List<Tree> children = getChildren(t.getId(), trees);
						if (null == children) {
							t.setIsLeaf(true);
						} else {
							t.setChildren(children);
						}
					}
					result.add(t);
				}
			}

			return result;
		}
		return null;
	}

	private static List<Tree> getChildren(String parentId, List<Tree> trees) {
		if (null == parentId) {
			return null;
		}
		List<Tree> result = new ArrayList<Tree>();
		for (Tree t : trees) {
			if (parentId.equals(t.getParentId())) {
				if (!t.isIsLeaf()) {
					List<Tree> children = getChildren(t.getId(), trees);
					if (null == children) {
						t.setIsLeaf(true);
					} else {
						t.setChildren(children);
					}
				}
				result.add(t);
			}
		}
		return result.size() > 0 ? result : null;
	}
}
