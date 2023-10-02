import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Polynomial {
	
	public double[]p ;
	public int[]exponent;
	
	
	public Polynomial() {
		
		this.p = null;
		this.exponent = null;
	}
	
	
	public Polynomial(double[] input_p, int[]input_expo) {
		
		this.p = input_p;
		this.exponent = input_expo;
	}
	
	
	public Polynomial(File file) {
		
		int i = 0, j = 0;
		int line_len;
		String[] new_line, tmp;
		
		
		try {
			BufferedReader b = new BufferedReader(new FileReader(file));
			String line = b.readLine();	
			b.close();
			line_len = line.length();
			while(i < line_len) {
				if(line.charAt(i) == '-' || line.charAt(i) == '+') {
					line = line.substring(0, i) + ' ' + line.substring(i);
					i++;
					line_len++;
				}
				i++;
			}
			System.out.println(line);
			
			new_line = line.split(" ");
			
			this.p = new double[new_line.length];
			this.exponent = new int[new_line.length];
			
			while (j < new_line.length) {
				tmp = new_line[j].split("x");
				this.p[j] = Double.parseDouble(tmp[0]);
				if(tmp.length > 1) {
					this.exponent[j] = Integer.parseInt(tmp[1]);
				}
				else {
					this.exponent[j] = 0;
				}
				
				j++;
			}
			
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*This is a helper method for the add method.
	 * The method takes an integer pow and an array of integer pow_arr[]
	 * as arguments. If pow is in pow_arr, return the index of pow.
	 * Otherwise, return -1
	 */
	private int searchPower(int pow, int[] pow_arr) {
		for(int i = 0; i < pow_arr.length; i++) {
			if(pow_arr[i] == pow) {
				return i;
			}
		}
		return -1;
	}
	
	public Polynomial add(Polynomial input) {
		
		double[] a;
		int[] b;
		int tmp_idx = 0;
		
		int total = this.exponent.length + input.exponent.length;
		for(int i = 0; i < this.exponent.length; i++) {
			for(int j = 0; j < input.exponent.length; j++) {
				if(this.exponent[i] == input.exponent[j]) {
					total -= 1;
				}
			}
		}
		
		a = new double[total];
		b = new int[total];
		
		for(int i = 0; i < this.exponent.length; i++) {
			a[i] = this.p[i];
			b[i] = this.exponent[i];
			tmp_idx ++;
		}
		
		for(int i = 0; i < input.exponent.length; i++) {
			int idx = searchPower(input.exponent[i], this.exponent);
			if(idx >= 0) {
				a[idx] += input.p[i];
			}
			else {
				a[tmp_idx] = input.p[i];
				b[tmp_idx] = input.exponent[i];
				tmp_idx++;
			}
		}

		Polynomial result = new Polynomial(a,b);
		
		return result;
	}
	
	
	public double evaluate(double x) {
		
		double result = 0.0;
		
		for (int i = 0; i < this.p.length; i++) {
			result = result + this.p[i] * Math.pow(x, this.exponent[i]);
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
	
	
	public Polynomial multiply(Polynomial poly) {
		
		double high_pow1 = 0, high_pow2 = 0;
		double[] tmp1, tmp_p;
		int count = 0, x = 0, y = 0;
		int[] tmp_expo;
		Polynomial result;
		
		for (int i = 0; i < this.exponent.length; i++) {
			if (this.exponent[i] > high_pow1) {
				high_pow1 = this.exponent[i];
			}
		}
		
		for (int i = 0; i < poly.exponent.length; i++) {
			if (poly.exponent[i] > high_pow2) {
				high_pow2 = poly.exponent[i];
			}
		}
		
		tmp1 = new double[(int)(high_pow1 + high_pow2) + 1];
		
		for (int i = 0; i < this.p.length; i++) {
			for (int j = 0; j < poly.p.length; j++) {
				tmp1[this.exponent[i]+poly.exponent[j]] += this.p[i] * poly.p[j];
			}
		}
		
		for (int i = 0; i < tmp1.length; i++) {
			if (tmp1[i] != 0) {
				count += 1;
			}
		}
		
		tmp_p = new double[count];
		tmp_expo = new int[count];
		
		while (x < tmp1.length) {
			while (tmp1[x] == 0) {
				x++;
			}
			tmp_p[y] = tmp1[x];
			tmp_expo[y] = x;
			
			x++;
			y++;
		}
		
		result = new Polynomial(tmp_p, tmp_expo);
		
		return result;
	}
	
	
	public void saveToFile(String file) {
		
		try {
			PrintStream ps = new PrintStream(file);
			for(int i = 0; i < this.p.length; i++) {
				if(this.p[i] >= 0 && i > 0) {
					ps.print('+');
				}
				ps.print(Double.toString(this.p[i]));
				if(this.exponent[i] != 0) {
					ps.print('x');
					ps.print(this.exponent[i]);
				}
			}
			ps.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*This is a helper method for testing*/
	public void printPoly() {
		
		System.out.println("The value of the polynomial is: ");
		for(int i = 0; i < this.p.length; i++) {
			System.out.println(Double.toString(this.p[i]) + "x" + Integer.toString(this.exponent[i]));
		}
	}
}
