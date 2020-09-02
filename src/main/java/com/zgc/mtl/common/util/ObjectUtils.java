package com.zgc.mtl.common.util;

import java.lang.reflect.Field;

public class ObjectUtils {
	/**
	 * 父类对象属性值复制到子类对象属性值
	 * 
	 * @param superObject
	 *            父类对象
	 * @param object
	 *            子类对象
	 * @return 含有父类对象全部属性值的子类对象
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T, U> U copyObj(T superObject, U object) throws IllegalArgumentException, IllegalAccessException {
		if (superObject == null || object == null) {
			throw new NullPointerException("param can not be null!");

		}
		Field[] fields = superObject.getClass().getDeclaredFields();
		Field[] fieldsvo = object.getClass().getSuperclass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			for (Field f2 : fieldsvo) {
				f2.setAccessible(true);
				if (f.get(superObject) != null && f.getName().toString().equals(f2.getName().toString())) {
					f2.set(object, f.get(superObject));
				}
			}
		}
		return object;
	}
	
}
