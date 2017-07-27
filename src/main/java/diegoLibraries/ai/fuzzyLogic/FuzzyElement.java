package diegoLibraries.ai.fuzzyLogic;

public class FuzzyElement {

	private String linguisticValue;
	private double sharpValue;
	private double membershipLevel;
	
	public FuzzyElement(String linguisticValue) {
		setLinguisticValue(linguisticValue);
	}
	
	
	public String getLinguisticValue() {
		return linguisticValue;
	}


	public void setLinguisticValue(String v) {
		this.linguisticValue = v;
	}


	public double getSharpValue() {
		return sharpValue;
	}


	public void setSharpValue(double v) {
		this.sharpValue = v;
	}


	public double getMembershipLevel() {
		return membershipLevel;
	}


	public void setMembershipLevel(double n) {
		this.membershipLevel = n;
	}

}
