package com.zgc.mtl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;

import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.model.Menu;

/**
 * 递归测试
 * 
 * @date 2020-10-28 09:50:56
 * @author yang
 */
public class RecursionTest {

	public static void main(String[] args) {
		ArrayList<Menu> menuList = new ArrayList<Menu>(16);
		menuList.add(new Menu(1, 0, "长期险", "icon1", 1));
		menuList.add(new Menu(2, 1, "年金险", "icon2", 2));
		menuList.add(new Menu(3, 1, "意外险", "icon3", 4));
		menuList.add(new Menu(4, 1, "健康险", "icon4", 3));
		menuList.add(new Menu(5, 0, "重疾险", "icon5", 5));
		menuList.add(new Menu(6, 0, "医疗险", "icon6", 11));
		menuList.add(new Menu(7, 0, "寿险", "icon7", 10));
		menuList.add(new Menu(8, 5, "成人重疾险攻略", "icon8", 6));
		menuList.add(new Menu(9, 5, "小儿重疾险攻略", "icon9", 7));
		menuList.add(new Menu(10, 8, "成人重疾短险攻略", "icon10", 9));
		menuList.add(new Menu(11, 8, "成人重疾长险攻略", "icon11", 8));
		Collections.sort(menuList, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getOrder() - o2.getOrder();
			}
		});
		// 最终结果集
		List<Menu> result = new ArrayList<>();
		// 所有菜单id
		HashSet<Integer> ids = new HashSet<Integer>();
		menuList.forEach(e -> ids.add(e.getId()));
		// 找出所有一级菜单
		for (Menu menu : menuList) {
			if (!ids.contains(menu.getParentId())) {
				result.add(menu);
			}
		}
		// 一级菜单找子菜单
		result.forEach(e -> e.setChildMenus(Recursion(e.getId(), menuList, ids)));
		System.out.println(JSONObject.toJSONString(result));
	}

	public static ArrayList<Menu> Recursion(Integer id, ArrayList<Menu> menuList, HashSet<Integer> ids) {
		ArrayList<Menu> childList = new ArrayList<>();
		for (Menu menu : menuList) {
			if (menu.getParentId().intValue() == id.intValue()) {
				childList.add(menu);
			}
		}
		for (Menu menu : childList) {
			if (ids.contains(menu.getParentId())) {
				menu.setChildMenus(Recursion(menu.getId(), menuList, ids));
			}
		}
		return childList.size() == 0 ? null : childList;
	}
}
