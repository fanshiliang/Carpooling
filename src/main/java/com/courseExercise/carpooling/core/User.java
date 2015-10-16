package com.courseExercise.carpooling.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class User {
	private String id;

    private String password;

    public User() {
        // Jackson deserialization
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", password)
                .toString();
    }
}
