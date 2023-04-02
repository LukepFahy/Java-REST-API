package configuration;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import resources.PassportsResource;

@ApplicationPath("/rest")
public class PassportsRestConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes
                = new HashSet<Class<?>>();
        classes.add(PassportsResource.class);
        return classes;
    }
}
