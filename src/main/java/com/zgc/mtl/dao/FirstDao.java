package com.zgc.mtl.dao;

import java.util.List;
import com.zgc.mtl.model.Person;
public interface FirstDao{
	List<Person> getAllPersons();
	/**
	 * 根据id查找user
	 * @return
	 */
	Person selectById(int id);
}
