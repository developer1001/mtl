package com.zgc.mtl.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zgc.mtl.model.Customer;

/**
 * jpa 客户dao接口层,不需要接口实现层
 * @date 2019-08-05 09:43:44
 * @author yang
 */
public interface CustomersRepository extends JpaRepository<Customer, String>{
	List<Customer> findByNameLike(String name);

	List<Customer> findTop3ByNameLike(String name);

	List<Customer> findTop3ByName(String name);
	
	@Query(value="select c from Customer c where c.phone = ?1")
	List<Customer> phoneNumber(String phone);
	
	@Query(value="select * from t_customer  where sex = :sex", nativeQuery = true)
	List<Customer> xingBie(@Param("sex")String sex);
	
	@Transactional
	@Modifying
	@Query(value="update  t_customer set phone = ?1 where id = ?2", nativeQuery = true)
	void uptPho(String phone,String id);
}
