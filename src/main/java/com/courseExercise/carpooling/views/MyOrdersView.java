package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class MyOrdersView extends MasterView{
	public MyOrdersView(UserAuthorization authorization){
		super("/views/myOrders.mustache",authorization);
	}
}
