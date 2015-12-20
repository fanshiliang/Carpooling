package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class LongTermCarpoolingView extends MasterView{
	public LongTermCarpoolingView(UserAuthorization authorization){
		super("/views/longTerm.mustache",authorization);
	}
}
