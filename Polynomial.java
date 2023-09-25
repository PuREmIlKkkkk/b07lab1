
public class Polynomial {
	
	public double[100]p;
	
	
	public Polynomial() {
		
		this.p[0] = 0;
	}
	
	
	public Polynomial(double[] input) {
		
		for (int i = 0; i < this.p.length; i++) {
			this.p[i] = input[i];
		}
	}
	
	
	public Polynomial add(Polynomial input) {
		
		for (int i = 0; i < this.p.length; i++) {
			this.p[i] = this.p[i] + input.p[i];
		}
		return this;
	}
	
	
	public double evaluate(double x) {
		
		double result = 0.0;
		
		for (int i = 0; i < this.p.length; i++) {
			result = result + this.p[i] * Math.pow(x, i);
		}
		
		return result;
	}
	
	
	public boolean hasRoot(double x) {
		
		if (this.evaluate(x) == 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
}
