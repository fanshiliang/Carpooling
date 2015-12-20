package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class RaiseLongOrderView extends MasterView{
	public RaiseLongOrderView(UserAuthorization authorization){
		super("/views/raiseLongOrder.mustache",authorization);
	}
}
