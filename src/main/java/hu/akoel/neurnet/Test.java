package hu.akoel.neurnet;

import java.util.ArrayList;

import hu.akoel.neurnet.layer.IInnerLayer;
import hu.akoel.neurnet.layer.IInputLayer;
import hu.akoel.neurnet.layer.IOutputLayer;
import hu.akoel.neurnet.layer.InnerLayer;
import hu.akoel.neurnet.layer.InputLayer;
import hu.akoel.neurnet.layer.OutputLayer;
import hu.akoel.neurnet.network.Network;
import hu.akoel.neurnet.neuron.IInputNeuron;
import hu.akoel.neurnet.neuron.INormalNeuron;
import hu.akoel.neurnet.neuron.InputNeuron;
import hu.akoel.neurnet.neuron.NormalNeuron;

public class Test {
	IInputNeuron inputNeuron1;
	IInputNeuron inputNeuron2;

	INormalNeuron innerNeuron11;
	INormalNeuron innerNeuron12;
	INormalNeuron innerNeuron13;
	INormalNeuron innerNeuron14;
	INormalNeuron innerNeuron15;
	INormalNeuron innerNeuron16;
	
	INormalNeuron innerNeuron21;
	INormalNeuron innerNeuron22;
	INormalNeuron innerNeuron23;
	INormalNeuron innerNeuron24;
	INormalNeuron innerNeuron25;
	INormalNeuron innerNeuron26;
	
	INormalNeuron outputNeuron1;
	
	IInputLayer inputLayer;
	IInnerLayer innerLayer1;
	IOutputLayer outputLayer;

	public Test() {


		inputNeuron1 = new InputNeuron();
		inputNeuron2 = new InputNeuron();

		innerNeuron11 = new NormalNeuron();
		innerNeuron12 = new NormalNeuron();
		innerNeuron13 = new NormalNeuron();
		innerNeuron14 = new NormalNeuron();
		innerNeuron15 = new NormalNeuron();
		innerNeuron16 = new NormalNeuron();
		
		innerNeuron21 = new NormalNeuron();
		innerNeuron22 = new NormalNeuron();
		innerNeuron23 = new NormalNeuron();
		innerNeuron24 = new NormalNeuron();
		innerNeuron25 = new NormalNeuron();
		innerNeuron26 = new NormalNeuron();
		
		outputNeuron1 = new NormalNeuron();

		// Input Layer
		inputLayer = new InputLayer();
		inputLayer.addNeuron(inputNeuron1);
		inputLayer.addNeuron(inputNeuron2);

		// Inner Layer 1
		innerLayer1 = new InnerLayer();
		innerLayer1.addNeuron(innerNeuron11);
		innerLayer1.addNeuron(innerNeuron12);
		innerLayer1.addNeuron(innerNeuron13);

		// Output Layer
		outputLayer = new OutputLayer();
		outputLayer.addNeuron(outputNeuron1);

		inputLayer.generateRandomWeights();
		innerLayer1.generateRandomWeights();
		outputLayer.generateRandomWeights();		

		
		ArrayList<double[]> inputList = new ArrayList<double[]>();
		ArrayList<double[]> outputList = new ArrayList<double[]>();
		
		inputList.add( new double[]{0.1,0});
		outputList.add(new double[]{0.4});
		
		inputList.add( new double[]{0.1,0});
		outputList.add(new double[]{0.4});
		
		inputList.add( new double[]{0.2,0});
		outputList.add(new double[]{0.3});

		inputList.add( new double[]{0.3,0});
		outputList.add(new double[]{0.2});

		inputList.add( new double[]{0.4,0});
		outputList.add(new double[]{0.1});

		inputList.add( new double[]{0.5,0});
		outputList.add(new double[]{0.0});
		
		Network network = new Network(inputLayer, outputLayer);
		network.addInnerLayer(innerLayer1);
		network.makeConnections();
		network.training(inputList, outputList, 0.0001);
		
/*		
		
		
		
		
		
		double input1;
		double input2;
		double[] expected = new double[1];
		
		for( int i = 1; i < 10000000; i++){
			
			double sumErr = 0;
			
			input1 = 0.1;
			input2 = 0;		
			expected[0] =0.4;
			sumErr += cycle(input1, input2, expected);
			
			input1 = 0.1;
			input2 = 0;
			expected[0] =0.4;
			sumErr += cycle(input1, input2, expected);
			
			input1 = 0.2;
			input2 = 0;		
			expected[0] =0.3;
			sumErr += cycle(input1, input2, expected);

			input1 = 0.3;
			input2 = 0;		
			expected[0] =0.2;
			sumErr += cycle(input1, input2, expected);

			input1 = 0.4;
			input2 = 0;		
			expected[0] =0.1;
			sumErr += cycle(input1, input2, expected);

			input1 = 0.5;
			input2 = 0;		
			expected[0] =0.0;
			sumErr += cycle(input1, input2, expected);
			
			sumErr = sumErr / 6;
			if( i % 10000 == 0 ){
				System.err.println( "Total Mean Square Error: " + sumErr);
			}
			
			if( sumErr <= 0.0002){
				break;
			}

		}
*/		
		
		double input1 = 0.0;
		double input2 = 0.0;
		double[] expected = new double[]{0.5};
		inputNeuron1.setInput(input1);
		inputNeuron2.setInput(input2);
		
		inputLayer.calculateSigmas();
		innerLayer1.calculateSigmas();
		outputLayer.calculateSigmas();
		
		outputLayer.calculateWeights(expected);
		
		System.out.println(inputLayer.toString());
		System.out.println(innerLayer1.toString());;
		System.out.println(outputLayer.toString());		
		
		

	}

	private double cycle(double input1, double input2, double[] expected) {

		// OUTPUT(t)
		inputNeuron1.setInput(input1);
		inputNeuron2.setInput(input2);

		// Sigma calculation by the NEW Input and the OLD Weight
		inputLayer.calculateSigmas();
		innerLayer1.calculateSigmas();
		outputLayer.calculateSigmas();

		// Weight calculation by the new Sigma
		outputLayer.calculateWeights(expected);
		innerLayer1.calculateWeights(outputLayer);
		inputLayer.calculateWeights(innerLayer1);

		// Sigma calculation  by the NEW Input and the NEW Weight
		inputLayer.calculateSigmas();
		innerLayer1.calculateSigmas();
		outputLayer.calculateSigmas();
		
			
		return Math.pow( outputNeuron1.getSigma() - expected[0], 2 );
	}

	public static void main(String[] args) {
		Test test = new Test();

	}
}