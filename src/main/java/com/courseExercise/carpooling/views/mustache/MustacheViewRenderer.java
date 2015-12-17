package com.courseExercise.carpooling.views.mustache;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.dropwizard.views.View;
import io.dropwizard.views.ViewRenderer;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheException;
import com.github.mustachejava.MustacheFactory;
import com.google.common.base.Charsets;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import jersey.repackaged.com.google.common.util.concurrent.UncheckedExecutionException;

public class MustacheViewRenderer implements ViewRenderer{
	
	private static final String CACHE_DURATION = "cacheDuration";
	
	private final LoadingCache<Class<? extends View>, MustacheFactory> factories;
	
	public MustacheViewRenderer(Map<String, Map<String, String>> viewRendererConfig){
		ImmutableMap<String, String> config = ImmutableMap.of();
		if (viewRendererConfig.containsKey(getSuffix()))
			config = ImmutableMap.copyOf(viewRendererConfig.get(getSuffix()));
		
		this.factories = (!config.containsKey(CACHE_DURATION)) ? 
				CacheBuilder.newBuilder().build(new FactoryLoader(config)) :
				CacheBuilder.newBuilder().expireAfterWrite(NumberUtils.toInt(config.get(CACHE_DURATION)), TimeUnit.SECONDS).build(new FactoryLoader(config));
	}
	@Override
	public boolean isRenderable(View view) {
		return view.getTemplateName().endsWith(getSuffix());
	}
	
	@Override
	public void render(View view, Locale locale, OutputStream output)throws IOException, WebApplicationException {
		try {
			final Mustache templete = factories.get(view.getClass()).compile(view.getTemplateName());
			final Charset charset = view.getCharset().or(Charsets.UTF_8);
			try(OutputStreamWriter writer = new OutputStreamWriter(output, charset)) {
				templete.execute(writer, view);
			}
		} catch(ExecutionException | UncheckedExecutionException | MustacheException ignored) {
			throw new FileNotFoundException("Template " + view.getTemplateName() + " not found.");
		}
	}
	
	@Override 
	public void configure(Map<String, String> config) {
		// DO Nothing - current implementation is using preRunConfiguration obtained during application construction
	}
	
	@Override
	public String getSuffix() {
		return ".mustache";
	}
	
	private static class FactoryLoader extends CacheLoader<Class<? extends View>, MustacheFactory> {
		
		public FactoryLoader(ImmutableMap<String, String> config) {
			// DO NOTHING - current cache loading has no customizable configuration
		}
	
		@Override
		public MustacheFactory load(Class<? extends View> key) throws Exception {
			String prefix = StringUtils.EMPTY;
			String mainUrl = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
			String viewUrl = key.getProtectionDomain().getCodeSource().getLocation().toString();
			if(!StringUtils.equals(mainUrl, viewUrl)) {
				String endingString = null;
				if(viewUrl.endsWith(".jar")) {
					endingString = ".jar";
				} else {
					endingString = "/target/classes";
				}
				prefix = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(viewUrl, endingString), "/");
			}
			return new PerClassMustacheFactory(prefix, key);
		}
		
	}
	
}
