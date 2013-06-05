package com.test.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserDetail {
	@Id
	private ObjectId id;
	private String firstName;
	private String lastName;
	private String userName;
	private String userPwd;
	private String email;
	private String phone;
	
	
	/*
	 *Here we have used to DFRef annotation which means the address will not be stored as an embeded template in
	 *userdetail template instead it will be stored in a separate collection. 
	 *And we are just adding a reference to address obejct here
	 *
	 *Without this annotation the address will be stored as an embedded template
	 *
	 *currently spring mongo data does not support cascade save, so special handling is needed here.
	 *Without @DbRef no special handling is required while saving and the address will be saved automatically as embeded template
	 *  
	 */
	@DBRef
	private Address address;


	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(final String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
