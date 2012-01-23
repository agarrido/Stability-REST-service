package algorithm.prueba;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import algorithm.Enums.Actions;
import algorithm.Enums.Impact;
import algorithm.Enums.Users;
import algorithm.Events.Event;
import algorithm.stability.StabilityObjects;

public class Parser {
	
	private StabilityObjects useCase;
	private double stability;
	
	public Parser(String uri){
		
		useCase=new StabilityObjects();
		BufferedReader br=null;
		String[] parts=null;
		
		try {
			//br = new BufferedReader(new FileReader("Trace1.txt"));
			URL url = new URL(uri);
			InputStream in = url.openStream();
			br = new BufferedReader(new InputStreamReader(in));
		
			String line = "";
		
			line = br.readLine();
			while ((line!=null)){
				
				
	        	parts=line.split(";");
	        	if (parts.length==3)
	        		this.EventCreateAdd(parts[0], Users.valueOf(parts[1]), Actions.CREATE, null);
	        	else
	        		if (parts.length==4)
	        			this.EventCreateAdd(parts[0], Users.valueOf(parts[1]), Actions.valueOf(parts[2]), Impact.valueOf(parts[3]));
	        		else
	        			this.EventCreateAdd(parts[0], Users.valueOf(parts[1]), Actions.GENERATE, null,parts[3],parts[4]);
				line = br.readLine();
	        }
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stability=useCase.getFullStability();
	}
	
	private void EventCreateAdd(String id, Users user, Actions action, Impact impact){
		Event e= new Event(id, user, action, impact);
		useCase.addEvent(e);
	}
	
	private void EventCreateAdd(String id, Users user, Actions action, Impact impact, String a, String b){
		Event e= new Event(id, user, action, impact);
		e.setSource(a);
		e.setSource(b);
		useCase.addEvent(e);
	}
	
	public double getStability(){
		return stability;
	}

}
