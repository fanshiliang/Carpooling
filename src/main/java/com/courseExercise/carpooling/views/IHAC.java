package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.core.Person;

import io.dropwizard.views.View;

public class IHAC extends View{
	public IHAC(){
		super("/views/iHaveACar.mustache");
	}
}