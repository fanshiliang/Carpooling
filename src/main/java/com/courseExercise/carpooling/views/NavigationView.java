package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class NavigationView extends MasterView{

	public NavigationView(UserAuthorization authorization){
		super("/views/navigation.mustache",authorization);
	}
}
