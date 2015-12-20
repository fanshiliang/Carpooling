package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class LongTermOrdersView extends MasterView {
	public LongTermOrdersView(UserAuthorization authorization){
		super("/views/longTermOrders.mustache",authorization);
	}
}
