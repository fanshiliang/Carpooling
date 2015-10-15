package com.example.helloworld.views;

import com.example.helloworld.core.Person;

import io.dropwizard.views.View;

public class LoginView extends View{
	public LoginView() {
        super("/views/signin.mustache");
    }
}
