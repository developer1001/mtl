package com.zgc.mtl.model;

import java.util.List;

/**
 * 递归菜单
 * @date 2020-10-28 09:40:13
 * @author yang
 */
public class Menu {
	private Integer id;
	private Integer parentId;
	private String name;
	private String icon;
	private Integer order;
	private List<Menu> childMenus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Menu> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Menu(Integer id, Integer parentId, String name, String icon, Integer order) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.icon = icon;
		this.order = order;
	}
	
	
}
