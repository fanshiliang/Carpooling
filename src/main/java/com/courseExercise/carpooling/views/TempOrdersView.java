package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class TempOrdersView extends MasterView {
	public TempOrdersView(UserAuthorization authorization){
		super("/views/tempCarpooling/tempOrders.mustache",authorization);
	}
}
