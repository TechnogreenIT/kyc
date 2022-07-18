package com.tes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authorities
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String userName;

	@Column(name = "authority")
	private String authority;

	@ManyToMany(mappedBy = "authorities")
	private Set<Users> users = new HashSet<>();
}
