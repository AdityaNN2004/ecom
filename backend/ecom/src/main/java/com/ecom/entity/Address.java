package com.ecom.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="addresses")
@AttributeOverride(name= "address_id",column = @Column(name="id"))
@NoArgsConstructor
@Setter@Getter
public class Address extends BaseEntity {
	@Column(name="house_number",nullable = false)
	private String houseNo;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String country;
	@Column(length = 6,nullable = false)
	private String zipcode;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false, referencedColumnName = "id" )
	private User user;
}
