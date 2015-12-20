package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class TestNavigationView extends MasterView{
	 

	public TestNavigationView(UserAuthorization authorization){
		super("/views/tempCarpooling/navigationTemp.mustache",authorization);
	}
}
