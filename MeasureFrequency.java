
public class MeasureFrequency {
	
	public double measureSample(int freq, int n) {
		double min, max;
		min = -(1/(10*freq));
		max = 1/(10*freq);		
		double sample;
		
		sample = (double) n/freq + getRandomDoubleBetweenRange(min, max);
		
	return sample;
	
	}

	private double getRandomDoubleBetweenRange(double min, double max) {
		double x = (double) (Math.random()*((max-min)+1))+min;
		System.out.println("Randomized value is " + x);
		return x;
	}
	
}
