package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.core.Person;

import io.dropwizard.views.View;

public class TCP extends View{
	public TCP(){
		super("/views/tempCarPooling.mustache");
	}
}
