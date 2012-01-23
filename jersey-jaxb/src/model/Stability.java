package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stability{
	
	private double stabilityValue;
	
	public void setStabilityValue(double s){
		stabilityValue=s;
	}
	
	public double getStabilityValue(){
		return stabilityValue;
	}

}
