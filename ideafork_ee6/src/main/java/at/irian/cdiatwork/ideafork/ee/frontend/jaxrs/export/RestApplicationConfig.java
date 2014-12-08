package at.irian.cdiatwork.ideafork.ee.frontend.jaxrs.export;

import at.irian.cdiatwork.ideafork.core.api.util.CdiUtils;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/public")
public class RestApplicationConfig extends Application {
    private static Set<Object> singletons;

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>() {{
            add(IdeaExporter.class);
        }};
    }

    @Override
    public Set<Object> getSingletons() {
        if (singletons != null) {
            return singletons;
        }
        final CustomJsonWriter jsonWriter = new CustomJsonWriter();

        /*
         * the following part is needed for some ee-servers.
         * it looks portable, but it isn't.
         */

        CdiUtils.injectFields(jsonWriter);
        singletons = new HashSet<Object>() {{
            add(jsonWriter);
        }};
        return singletons;
    }
}
