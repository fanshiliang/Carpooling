package com.courseExercise.carpooling;

import io.dropwizard.auth.AuthValueFactoryProvider;

import com.courseExercise.carpooling.cli.RenderCommand;
import com.courseExercise.carpooling.core.Person;
import com.courseExercise.carpooling.core.Template;
import com.courseExercise.carpooling.core.User;
import com.courseExercise.carpooling.db.PersonDAO;
import com.courseExercise.carpooling.filter.DateRequiredFeature;
import com.courseExercise.carpooling.health.TemplateHealthCheck;
import com.courseExercise.carpooling.resources.FilteredResource;
import com.courseExercise.carpooling.resources.HelloWorldResource;
import com.courseExercise.carpooling.resources.HomeResource;
import com.courseExercise.carpooling.resources.PeopleResource;
import com.courseExercise.carpooling.resources.PersonResource;
import com.courseExercise.carpooling.resources.ProtectedResource;
import com.courseExercise.carpooling.resources.SignIn;
import com.courseExercise.carpooling.resources.ViewResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import java.util.Map;

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

    @Override
    public void run(CarpoolingConfiguration configuration, Environment environment) {
        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(DateRequiredFeature.class);

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(new ViewResource());
        environment.jersey().register(new ProtectedResource());
        environment.jersey().register(new PeopleResource(dao));
        environment.jersey().register(new PersonResource(dao));
        environment.jersey().register(new FilteredResource());
        environment.jersey().register(new SignIn());
        environment.jersey().register(new HomeResource(null));
    }
}
