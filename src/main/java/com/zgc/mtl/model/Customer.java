package com.zgc.mtl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 客户类，采用jpa处理
 * 
 * @date 2019-08-02 18:33:07
 * @author yang
 */
@Entity
@Table(name = "t_customer")
@Data
public class Customer {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "sex", length = 1)
	private String sex;
	
	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "phone",length = 64)
	private String phone;

	@Column(name = "createdBy",  length = 64)
	private String createdBy;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "updatedBy", length = 64)
	private String updatedBy;

	@Column(name = "updatedDate")
	private Date updatedDate;
}
