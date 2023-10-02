import java.io.File;

public class Driver {

	public static void main(String [] args) {
		
		double test_result;
		
		double[] a = {1.0, 5.0, 3.0};
		int[] b = {0, 2, 1};
		double[] c = {10.0, 2.00};
		int[] d = {3, 0};
		
		
		Polynomial p1 = new Polynomial(a, b);
		Polynomial p2 = new Polynomial(c,d);
		File f = new File("C:\\Users\\peter\\test\\f1.txt");
		
		Polynomial p3 = new Polynomial(f);
		
		test_result = p1.multiply(p2).evaluate(2.0);
		if(test_result != 2214.0) {
			System.out.println("There's something wrong");
		}
		else {
			System.out.println("add method functionable");
		}
		
		test_result = p3.evaluate(1.0);
		if(test_result != 4.0) {
			System.out.println("There's something wrong with the constructor that takes a File as an argument or with the evaluate method");
		}
		else {
			System.out.println("Seem's good");
		}
		
		Polynomial p4;
		p4 = p2.add(p3);
		p4.printPoly();
		
		p4.saveToFile("C:\\Users\\peter\\test\\f2.txt");
		System.out.println("Please check the corresponding file in the directory");
		
		double[] e = {4.0, 4.0, -3.0};
		int[] g = {2, 1, 0};
		double[] h = {2.5, 3.2, -16.4};
		int[] i = {2, 1, 0};
		Polynomial p5 = new Polynomial(e, g);
		Polynomial p6 = new Polynomial(h, i);
		
		if(p5.hasRoot(3) == false && p6.hasRoot(2) == true) {
			System.out.println("hasRoot method functionable");
		}
		else {
			System.out.println("Something's wrong with hasRoot method");
		}
		
		System.out.println("Everything seems good, have a good night sleep!");
	}
}
