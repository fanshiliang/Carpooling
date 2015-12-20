package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class RaiseTempOrderView extends MasterView{
	public RaiseTempOrderView(UserAuthorization authorization){
		super("/views/tempCarpooling/raiseTempOrder.mustache",authorization);
	}
}
