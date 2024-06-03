import java.util.*;
import java.io.*;

public class Polynomial{
	double [] coefficients;
	int [] exponents;
	public Polynomial(double [] coefficients, int [] exponents) {
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	public Polynomial(File f) throws FileNotFoundException {
		Scanner reader = new Scanner(f);
		while(reader.hasNext()) {
			String []in = reader.nextLine().split("(?=[+-])");
			double [] co = new double[in.length];
			int [] ex = new int [in.length];
			int idx = 0;
			for(String s: in) {
				System.out.println(s);
				String [] t = s.split("x");
				co[idx] = Double.parseDouble(t[0]);
				ex[idx] = (t.length == 1)?0:Integer.parseInt(t[1]);
				idx++;
			}
			if(co.length == 1 && co[0] == 0) {
				co = null;
				ex = null;
			}
			coefficients = co;
			exponents = ex;
		}
		reader.close();
		
	}
	public Polynomial() {
		coefficients = null;
		exponents = null;
	}
	
	public Polynomial add(Polynomial p) {
		if(coefficients == null && p.coefficients == null)return new Polynomial();
		else if(coefficients == null)return new Polynomial(p.coefficients.clone(),p.exponents.clone());
		else if(p.coefficients == null)return new Polynomial(coefficients.clone(),exponents.clone());
		int maxExp = 0;
		for(int i = 0;i< coefficients.length;i++)maxExp = Math.max(exponents[i], maxExp);
		for(int i = 0;i< p.coefficients.length;i++)maxExp = Math.max(p.exponents[i], maxExp);
		double [] c = new double[maxExp+1];
		int count = 0;
		for(int i = 0;i< Math.max(coefficients.length, p.coefficients.length);i++) {
			if(i < coefficients.length) {
				if(c[exponents[i]] == 0)count++;
				c[exponents[i]] += coefficients[i];
				if(c[exponents[i]] == 0)count--;
			}
			if (i < p.coefficients.length) {
				if(c[p.exponents[i]] == 0)count++;
				c[p.exponents[i]] += p.coefficients[i];
				if(c[p.exponents[i]] == 0)count--;
			}
		}
		if(count == 0)return new Polynomial();
		double [] co = new double [count];
		int [] exp = new int [count];
		int idx = 0;
		for(int i = 0;i<= maxExp;i++) {
			if(c[i] != 0) {
				co[idx] = c[i];
				exp[idx] = i;
				idx++;
			}
		}
		return new Polynomial(co, exp);
	}
	public Polynomial multiply(Polynomial p) {
		if(coefficients == null || p.coefficients == null)return new Polynomial();
		double [] c = new double [1000];
		int terms = 0;
		for(int i= 0;i< coefficients.length;i++) {
			for(int j= 0;j< p.coefficients.length;j++) {
				int power = exponents[i] + p.exponents[j];
				
				if(c[power] == 0) {
					terms++;
				}
				c[power] += coefficients[i]*p.coefficients[j];
				System.out.println(c[power] + "x"+ power);
				if(c[power] == 0) {
					terms--;
				}
			}
		}
		if(terms == 0)return new Polynomial();
		System.out.println(terms);
		double [] co = new double [terms];
		int [] ex = new int [terms];
		int idx = 0;
		for(int i = 0;i< c.length;i++) {
			if(c[i] != 0) {
				co[idx] = c[i];
				ex[idx] = i;
				idx++;
			}
		}
		return new Polynomial(co, ex);
		
	}
	public void saveToFile(String fileName) throws IOException {
		FileWriter file = new FileWriter(fileName);
		String out = "";
		for(int i= 0;i< coefficients.length;i++) {
			out += ((coefficients[i] > 0 && i!= 0)?"+":"")+coefficients[i] + ((exponents[i] > 0 )?"x" + exponents[i]:"");
		}
		file.write(out);
		file.close();
	}
	public double evaluate(double x) {
		if(coefficients == null)return 0;
		double ret = 0;
		for(int i= 0;i< coefficients.length;i++) {
			ret += Math.pow(x, exponents[i]) * coefficients[i];
		}
		return ret;
	}
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0 ;
	}
//	public String toString() {
//		String out = "";
////		for(int i= 0;i<coefficients.length;i++)System.out.print(coefficients[i] + " ");
////		for(int i = 0;i< exponents.length;i++)System.out.println(exponents[i]+ " ");
//		for(int i= 0;i< coefficients.length;i++) {
//			out += ((coefficients[i] > 0 && i!= 0)?"+":"")+coefficients[i] + ((exponents[i] > 0 )?"x" + exponents[i]:"");
//		}
//		return out;
//	}
}