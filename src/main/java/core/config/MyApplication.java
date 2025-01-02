package core.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		packages("web");
		register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
	}
}