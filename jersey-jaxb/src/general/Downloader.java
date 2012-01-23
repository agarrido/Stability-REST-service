package general;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Downloader {
	private String uriBase = "http://sandbox.wf4ever-project.org";
	private ClientConfig config = new DefaultClientConfig();
	private Client client = Client.create(config);
	private WebResource service = client.resource(getBaseURI());
	
	private URI getBaseURI() {
		//el contexto del servicio
		return UriBuilder.fromUri(
				uriBase).build();
	}
	
	public String getTrace(String id, String pathToResource){
		return (service.path("rosrs5").path("ROs").path(id)).path(pathToResource).accept(MediaType.TEXT_XML).get(String.class);
	}

}
