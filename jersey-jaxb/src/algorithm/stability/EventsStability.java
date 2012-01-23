package algorithm.stability;

import java.util.ArrayList;

import algorithm.Events.Event;


public class EventsStability {
	
	private ArrayList<Event> events;
	private StabilityObjects so;
	
	public EventsStability(ArrayList<Event> e){
		events=e;
		so=new StabilityObjects();
		setEvents();
	}

	private void setEvents() {
		for (int i=0; i<events.size(); i++)
			so.addEvent(events.get(i));	
	}
	
/*	public void launchGraph(){
		new SimplePlot(co.getConsistencyVector());
	}
	*/
	

}
