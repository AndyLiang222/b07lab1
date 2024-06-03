import java.io.*;
import java.util.*;
public class Driver {
	public static void main(String[] args) throws IOException {
		File a = new File("out.txt");
		Polynomial p3 = new Polynomial(a);
		System.out.println(p3);
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {-1,1};
		int[] e1 = {0,1};
		Polynomial p1 = new Polynomial(c1,e1);
		double[] c2 = {1,1};
		int[] e2 = {1,0};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p2.add(p);
		p3.saveToFile("out.txt");
		System.out.println(s);
	}
}

//(x-2)(-2x^3+3x^2)
