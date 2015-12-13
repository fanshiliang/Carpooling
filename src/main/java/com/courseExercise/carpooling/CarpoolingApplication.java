
package com.courseExercise.carpooling;
import com.courseExercise.carpooling.core.*;
import com.courseExercise.carpooling.db.*;
import com.courseExercise.carpooling.resources.*;
import com.courseExercise.carpooling.cli.RenderCommand;
import com.courseExercise.carpooling.filter.DateRequiredFeature;
import com.courseExercise.carpooling.health.TemplateHealthCheck;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import java.util.Map;
import java.util.EnumSet;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

public class CarpoolingApplication extends Application<CarpoolingConfiguration> {
    public static void main(String[] args) throws Exception {
        new CarpoolingApplication().run(args);
    }

    private final HibernateBundle<CarpoolingConfiguration> hibernateBundle =
            new HibernateBundle<CarpoolingConfiguration>(Person.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(CarpoolingConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<CarpoolingConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addCommand(new RenderCommand());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<CarpoolingConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CarpoolingConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<CarpoolingConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(CarpoolingConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }
    
    private void configureCors(Environment environment) {
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
      }

    @Override
    public void run(CarpoolingConfiguration configuration, Environment environment) {
    	
    	configureCors(environment);
    	
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());

        final Template template = configuration.buildTemplate();
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(DateRequiredFeature.class);


        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(new ViewResource());
        environment.jersey().register(new FilteredResource());

        //create DAO instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final MyDAO myDAO = jdbi.onDemand(MyDAO.class);
        
        
        environment.jersey().register(new UserResource(myDAO));
        environment.jersey().register(new OrderResource(myDAO));

        environment.jersey().register(new SignIn());
        environment.jersey().register(new TempCarPoolingResource());
        environment.jersey().register(new HomeResource(null));
    }
    
    
}
