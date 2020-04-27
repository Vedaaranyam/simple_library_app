package com.library.manager.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@NotBlank
	private String username;

	@NotBlank
	private String orderstate;

	@NotNull
	private Integer ordercount;

	@NotBlank
	private String eventdate;

	@NotNull
	private Timestamp orderdate;

	@NotBlank
	private String orderaddress;

	@NotBlank
	private String ordername;

}
