package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.core.Person;

import io.dropwizard.views.View;

public class LoginView extends View{
	public LoginView() {  
        super("/views/signin.mustache");
    }
}
