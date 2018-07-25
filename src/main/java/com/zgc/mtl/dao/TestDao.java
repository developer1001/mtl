package com.zgc.mtl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zgc.mtl.model.Person;
public interface TestDao{

	List<Person> queryAll();
}
