package com.ecom.entity;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter
@ToString
@AllArgsConstructor
@Table(name="users")
@Entity
@NoArgsConstructor
@AttributeOverride(name = "user_id", column =@Column(name="id"))
public class User extends BaseEntity{
	@Column(name="first_name",nullable = false)
	private String firstName;
	@Column(name="middle_name",nullable = false)
	private String middleName;
	@Column(name="last_name",nullable = false)
	private String lastName;
	@Enumerated(EnumType.STRING)
	private  UserRole role = UserRole.CUSTOMER;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true,nullable = false)
	private String password;
	@Column(name="phone_number")
	private String phoneNo;
	@Column(name="is_active")
	private boolean isActive;
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
	private Address address;
}
