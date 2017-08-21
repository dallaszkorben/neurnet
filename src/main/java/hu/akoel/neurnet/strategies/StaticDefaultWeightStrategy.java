package hu.akoel.neurnet.strategies;

public class StaticDefaultWeightStrategy implements DefaultWeightStrategy{
	private Double value;
	
	public StaticDefaultWeightStrategy( Double value ){
		this.value = value;
	}
	
	public Double getValue() {
		return value;		
	}
	
}