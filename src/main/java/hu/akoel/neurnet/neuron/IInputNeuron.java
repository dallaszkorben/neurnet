package hu.akoel.neurnet.neuron;

public interface IInputNeuron extends INeuron{
	
	//TODO probably the Neuron should know nothing about the Layer which contains it
	//public void initializeNeuron( ILayer actualLayer );
	
	public void setInput( double inputValue );

}