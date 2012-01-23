package algorithm.prueba;
import algorithm.Enums.Actions;
import algorithm.Enums.Impact;
import algorithm.Enums.Users;
import algorithm.Events.Event;
import algorithm.stability.StabilityObjects;


public class Kristina {
	
	private StabilityObjects useCase;
	private double cons;
	
	public Kristina(){
		useCase=new StabilityObjects();
		
		this.EventCreateAdd("DescDoc", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("AWf", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("EWf1", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("EWf2", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("AWf", Users.OWNER, Actions.ADD, Impact.MED);
		this.EventCreateAdd("IN1", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("IN2", Users.OWNER, Actions.CREATE, null);
		this.EventCreateAdd("AWf", Users.OWNER, Actions.ADD, Impact.MIN); //7.a
		this.EventCreateAdd("O1", Users.OWNER, Actions.GENERATE, null, "EWf1", "IN1");
		this.EventCreateAdd("O2", Users.OWNER, Actions.GENERATE, null, "EWf2", "IN2");
		this.EventCreateAdd("DescDoc", Users.OWNER, Actions.ADD, Impact.MED); //10
		
		this.EventCreateAdd("EWf1", Users.OWNER, Actions.REMOVE, Impact.MIN);
		this.EventCreateAdd("IN1", Users.TRUSTED, Actions.ADD, Impact.MIN);
		this.EventCreateAdd("O1", Users.TRUSTED, Actions.GENERATE, null, "EWf1", "IN1");
		this.EventCreateAdd("AWf", Users.COLLABORATOR, Actions.ADD, Impact.MAX);
		this.EventCreateAdd("ExtDoc", Users.OWNER, Actions.CREATE, null); //15
		this.EventCreateAdd("EWf2", Users.TRUSTED, Actions.ADD, Impact.MED);
		this.EventCreateAdd("O2", Users.OWNER, Actions.GENERATE, null, "EWf2", "IN2");
		this.EventCreateAdd("AWf", Users.OWNER, Actions.REMOVE, Impact.MED);
		this.EventCreateAdd("AWf", Users.OWNER, Actions.ADD, Impact.MAX);
		this.EventCreateAdd("IN3", Users.TRUSTED, Actions.CREATE, null);
		this.EventCreateAdd("O3", Users.OWNER, Actions.GENERATE, null, "AWf", "IN3");
		
		
		cons=useCase.getFullStability();
		//System.out.println("RO Consistency");
		//System.out.println(useCase.getFullConsistency());
		
		//System.out.println("RO Consistency step by step:");
		//for (double c: useCase.getConsistencyVector())
		//System.out.println(c);
		
		//new SimplePlot(useCase.getConsistencyVector());
		
		
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
	
	public double getCons(){
		return cons;
	}

	
	

}
