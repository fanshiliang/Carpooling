package com.courseExercise.carpooling.views;

import com.courseExercise.carpooling.api.UserAuthorization;

public class RegisterView extends MasterView{
	public RegisterView(UserAuthorization authorization) {  
        super("/views/register.mustache",authorization);
    }
}
