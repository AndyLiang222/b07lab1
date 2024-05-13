public class Polynomial{
	double [] coefficients;
	public Polynomial(double [] coefficients) {
		this.coefficients = coefficients;
	}
	public Polynomial() {
		coefficients = new double [1];
	}
	public Polynomial add(Polynomial p) {
		double [] c = new double[Math.max(coefficients.length, p.coefficients.length)];
		for(int i = 0;i< c.length;i++) {
			if(i < coefficients.length)c[i] += coefficients[i];
			if (i < p.coefficients.length)c[i] += p.coefficients[i];
		}
		return new Polynomial(c);
	}
	public double evaluate(double x) {
		double power = 1;
		double ret = 0;
		for(int i= 0;i< coefficients.length;i++) {
			ret += power * coefficients[i];
			power *= x;
		}
		return ret;
	}
	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0 ;
	}
}