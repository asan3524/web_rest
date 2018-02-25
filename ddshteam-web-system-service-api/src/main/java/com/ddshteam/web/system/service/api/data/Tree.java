package com.ddshteam.web.system.service.api.data;

import java.io.Serializable;
import java.util.List;

/**
 * 树对象
 * @ClassName: Tree
 * @author lishibang
 * @date 2018年2月6日 下午12:20:02
 * @version v1.0.0
 * 
 */
@SuppressWarnings("serial")
public class Tree implements Serializable{

	/**
	 * 默认构造
	 * @author lishibang
	 * @version v1.0.0
	 *
	 */
	public Tree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用于导航
	 * @author lishibang
	 * @version v1.0.0
	 * @param id
	 * @param name
	 * @param path
	 * @param iconClass
	 */
	public Tree(String id, String name, String url, String iconClass) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.url = url;
		this.iconClass = iconClass;
	}

	/**
	 * 用于静态树
	 * @author lishibang
	 * @version v1.0.0
	 * @param id
	 * @param parentId
	 * @param name
	 * @param iconClass
	 * @param disabled
	 * @param isLeaf
	 */
	public Tree(String id, String parentId, String name, String iconClass, boolean disabled, boolean isLeaf) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.iconClass = iconClass;
		this.disabled = disabled;
		this.isLeaf = isLeaf;
	}

	/**
	 * 
	 * 用于可选树
	 * @author lishibang
	 * @version v1.0.0
	 * @param id
	 * @param parentId
	 * @param name
	 * @param iconClass
	 * @param disabled
	 * @param isLeaf
	 * @param checkStatus
	 */
	public Tree(String id, String parentId, String name, String iconClass, boolean disabled, boolean isLeaf,
			int checkStatus) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.iconClass = iconClass;
		this.disabled = disabled;
		this.isLeaf = isLeaf;
		this.checkStatus = checkStatus;
	}
	
	public Tree(Tree target) {
		super();
		this.id = target.id;
		this.name = target.name;
		this.parentId = target.parentId;
		this.url = target.url;
		this.iconClass = target.iconClass;
		this.disabled = target.disabled;
		this.isLeaf = target.isLeaf;
		this.checkStatus = target.checkStatus;
		this.children = target.children;
	}

	private String id;
	private String parentId;
	private String name;
	private String url;
	private List<Tree> children;
	private String iconClass;
	private boolean disabled;
	private boolean isLeaf;
	/**
	 * 树节点叶子选中状态，只有isLeaf为true时，此属性值才有效
	 * 0未选中
	 * 1选中
	 */
	private int checkStatus;
	
	public static TreeNodeBuilder builder(){
    	return new TreeNodeBuilder();
    }
	
	public static class TreeNodeBuilder {
		private Tree target;
    	
    	public TreeNodeBuilder(){
    		target = new Tree();
    	}
    	
    	public TreeNodeBuilder id(String id){
    		target.id = id;
    		return this;
    	}
    	public TreeNodeBuilder parentId(String parentId){
    		target.parentId = parentId;
    		return this;
    	}
    	public TreeNodeBuilder name(String name){
    		target.name = name;
    		return this;
    	}
    	public TreeNodeBuilder url(String url){
    		target.url = url;
    		return this;
    	}
    	public TreeNodeBuilder iconClass(String iconClass){
    		target.iconClass = iconClass;
    		return this;
    	}
    	public TreeNodeBuilder disabled(Boolean disabled){
    		target.disabled = disabled;
    		return this;
    	}
    	public TreeNodeBuilder isLeaf(Boolean isLeaf){
    		target.isLeaf = isLeaf;
    		return this;
    	}
    	public TreeNodeBuilder checkStatus(Integer checkStatus){
    		target.checkStatus = checkStatus;
    		return this;
    	}
    	public TreeNodeBuilder children(List<Tree> children){
    		target.children = children;
    		return this;
    	}
    	
    	public Tree build(){
    		return new Tree(target);
    	}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
}
