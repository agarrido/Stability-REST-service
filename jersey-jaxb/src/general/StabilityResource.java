package general;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import algorithm.prueba.Kristina;
import algorithm.prueba.Parser;

import model.Stability;

@Path("/stability")
public class StabilityResource {
	
		// This method is called if XML is request
		@GET
		@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Stability getXML(@QueryParam("ROprov")@DefaultValue("") String uri) {
			Stability c = new Stability();
			//cuando haya uri se llama ahí en vez d hacer lo de Kristina
			// Downloader d=new Downloader();
			// String trace=d.getTrace(ROprov);
			Parser k=new Parser(uri);
			c.setStabilityValue(k.getStability());
			return c;
		}
		
		// This can be used to test the integration with the browser
		@GET
		@Produces( { MediaType.TEXT_XML })
		public Stability getHTML(@QueryParam("ROprov")@DefaultValue("") String uri) {
			Stability c = new Stability();
			//cuando haya uri se llama ahí en vez d hacer lo de Kristina
			// Downloader d=new Downloader();
			// String trace=d.getTrace(ROprov);
			Parser k=new Parser(uri);
			c.setStabilityValue(k.getStability());
			return c;
		}

}
