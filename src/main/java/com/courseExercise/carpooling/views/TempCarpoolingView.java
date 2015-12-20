package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class TempCarpoolingView extends MasterView{
	public TempCarpoolingView(UserAuthorization authorization){
		super("/views/tempCarpooling/tempCarpooling.mustache",authorization);
	}
}
