package com.courseExercise.carpooling.views;

import io.dropwizard.views.View;

public class TCP extends View{
	public TCP(){
		super("/views/tempCarPooling.mustache");
	}
}
