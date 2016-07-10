package at.irian.cdiatwork.ideafork.ee.frontend.jaxrs.export;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.api.data.view.ExportView;
import at.irian.cdiatwork.ideafork.core.api.util.CdiUtils;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.JSON;

@Provider
@Produces(MediaType.APPLICATION_JSON)
//used to ensure a portable result across different ee-servers
public class CustomJsonWriter implements MessageBodyWriter<Object> {
    @Inject
    @ExternalFormat(JSON)
    private ObjectConverter objectConverter;

    @Override
    public boolean isWriteable(Class<?> rawType, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object o, Class<?> rawType, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
        lazyInit();
        //jackson would support OutputStream, but GSon doesn't
        entityStream.write(objectConverter.toString(o, ExportView.Public.class).getBytes());
    }

    @Override
    public long getSize(Object o, Class<?> rawType, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    /*
     * the following part is needed for some ee-servers.
     * it looks portable, but it isn't.
     */
    private void lazyInit() {
        if (objectConverter == null) {
            init();
        }
    }

    private synchronized void init() {
        if (objectConverter == null) {
            CdiUtils.injectFields(this);
        }
    }
}
