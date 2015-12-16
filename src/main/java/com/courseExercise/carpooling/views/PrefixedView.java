package com.courseExercise.carpooling.views;

import io.dropwizard.views.View;

import com.google.common.base.Charsets;

public abstract class PrefixedView extends View{ 
	
	// to avoid extracting templateName in each view rendering
	private final String originalTemplateName;
	
	protected PrefixedView(String templateName) {
		super(templateName, Charsets.UTF_8);
		this.originalTemplateName = templateName;
	}
	
	@Override
	public String getTemplateName() {
		return originalTemplateName;
	}
}
