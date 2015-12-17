package com.courseExercise.carpooling.views.mustache;

import io.dropwizard.views.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.lang3.StringUtils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheException;
import com.google.common.base.Charsets;

public class PerClassMustacheFactory extends DefaultMustacheFactory {
	
	private final static String VIEW_PATH = "/views/";
	private final Class<? extends View> vClass;
	private final String prefix;
	
	PerClassMustacheFactory(String prefix, Class<? extends View> vClass){
		this.vClass = vClass;
		this.prefix = resolveprefix(prefix);
	}
	
	private String resolveprefix(String prefix) {
		return StringUtils.isBlank(prefix) ? VIEW_PATH : "/" + prefix + "/resources" + VIEW_PATH;
	}
	
	@Override
	public Reader getReader(String resourceName) {
		InputStream is = vClass.getResourceAsStream(prefix + resourceName);
		if(is == null) {
			is = vClass.getResourceAsStream(VIEW_PATH + resourceName);
			if(is == null)
				throw new MustacheException("Template " + resourceName + " not found");
		}
		return new BufferedReader(new InputStreamReader(is, Charsets.UTF_8));
	}

}
