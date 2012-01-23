package algorithm.stability;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import algorithm.Enums.Actions;
import algorithm.Enums.Impact;
import algorithm.Enums.Users;
import algorithm.Events.Event;
import algorithm.knowledge.ActionUserTable;
import algorithm.knowledge.ImpactTable;



public class StabilityObjects {
	
	private Hashtable<String,ArrayList<Double>> so;
	private ArrayList<Double> stabilityVector;
	
	
	public StabilityObjects(){
		so=new Hashtable<String, ArrayList<Double>>();
		stabilityVector= new ArrayList<Double>();
	}
	
	public void addEvent(Event e){
		double value= getValue(e.getUser(), e.getAction(), e.getImpact());

		switch (e.getAction()){
		case CREATE: addCreate(e.getId(), value); break;
		case ADD: addAdd(e.getId(), value); break;
		case REMOVE: addAdd(e.getId(), value); break; //it does exactly the same
		case DELETE: addDelete(e.getId());break;
		case GENERATE: addGenerate(e.getId(), e.getSources());break;
		}
		
		stabilityVector.add(getFullStability());
	}


//////////////////// Consistency	
	public double getFullStability(){
		double acum=0;
		String e;
		int numOfElements=so.size();
		
		Enumeration<String> elements=so.keys();
		while (elements.hasMoreElements()){
			e=elements.nextElement();
			acum=acum+getObjectStability(e);
		}
		return acum/numOfElements;
	}
	
	//need to refactor
	/*public ArrayList<Double> getFullConsistencyVector(){
		ArrayList<Double> vector=new ArrayList<Double>();
		double acum=0;
		Enumeration<String> elements=co.keys();
		int numOfElements=co.size();
		String e;
		while (elements.hasMoreElements()){
			e=elements.nextElement();
			vector.add(getObjectConsistency(e));
			acum=acum+getObjectConsistency(e);	
		}
		vector.add(acum/numOfElements);
		return vector;
	}*/
	
	public ArrayList<Double> getStabilityVector(){
		return stabilityVector;
	}
	
	private double getObjectStability(String id){
		if (so.containsKey(id))
			return so.get(id).get(so.get(id).size()-1);
		
		return 0;
	}
	

////////////////////////////// Calculations

	private double getValue(Users user, Actions action, Impact impact) {
		double value=ActionUserTable.getInstance().getValueActionUser(action, user); 
		if (impact!=null)
			value=value*ImpactTable.getInstance().getImpactValue(impact);
		
		return value;
	}

	
/////////////////////////// Actions
	private void addCreate(String id, double value){
		if (!so.containsKey(id)){
			ArrayList<Double> list=new ArrayList<Double>();
			list.add(value);
			so.put(id,list);
		}
		// else Exception because it's replicated
	}
	
	private void addAdd(String id, double value) {
		if (so.containsKey(id)){
			//ArrayList of events
			ArrayList<Double> list=so.get(id);
			//position of the last value
			int pos=list.size()-1;
			//last value
			double last=list.get(pos);
			
			//adding
			list.add(last+last*value/100);
		}
		//else Exception, id not found
	}
	
	private void addDelete(String id) {
		if (so.containsKey(id))
			so.remove(id);
		//else Exception, id not found
	}
	
	private void addGenerate(String id, ArrayList<String> sources) {
		double acum=0;
		for (String s: sources){
			if(so.containsKey(s))
				acum=acum+getObjectStability(s);
		}
		
		//check the RO element existency
		ArrayList<Double> list;
		if (!so.containsKey(id)){
			list=new ArrayList<Double>();
			so.put(id, list);
		}
			else
				list=so.get(id);
		
		list.add(acum/sources.size());
				
	}


	

}
