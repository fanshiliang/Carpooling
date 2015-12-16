package com.courseExercise.carpooling.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class User {
	private String id;

    private String password;
    
    private String userName;
    
    public String getUserName() {
		return userName;
	}
 
	public void setUserName(String userName) {
		this.userName = userName;
	}


	private String age;
    
    private String drivingYears;
    
    private String gender;
    
	private String cellphone;
    
    private String carOwner;

    public User() {
        // Jackson deserialization
    }

    public User(String id) {
        this.id = id;
    }
    
    @JsonProperty
    public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@JsonProperty
	public String getDrivingYears() {
		return drivingYears;
	}

	public void setDrivingYears(String drivingYears) {
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
	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
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
