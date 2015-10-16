package com.example.helloworld.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class User {
	private String id;

    private String password;
    
    private int age;
    
    private int drivingYears;
    
    private String gender;
    
	private String cellphone;
    
    private int carOwner;

    public User() {
        // Jackson deserialization
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }
    
    @JsonProperty
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@JsonProperty
	public int getDrivingYears() {
		return drivingYears;
	}

	public void setDrivingYears(int drivingYears) {
		this.drivingYears = drivingYears;
	}
	
	@JsonProperty
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonProperty
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@JsonProperty
	public int getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(int carOwner) {
		this.carOwner = carOwner;
	}

    @JsonProperty
    public String getId() {
        return id;
    }
    
	public void setId(String id) {
		this.id = id;
	}

    @JsonProperty
    public String getPassword() {
        return password;
    }
    
	public void setPassword(String password) {
		this.password = password;
	}


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", password)
                .toString();
    }
    

}
