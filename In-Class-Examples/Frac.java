package fractions;

public class Frac {
	private int num;
	private int den;
	
	// default constructor  1/1
	public Frac()
	{
		num = 1;
		den = 1;
	}
	
	// non-default constructor
	public Frac(int n, int d)
	{
		num = n;
		den = d;
	}
	
	// accessors / getters
	public int getNumerator()
	{
		return num;
	}
	public int getDenominator()
	{
		return den;
	}
	// modifiers / setters
	public void setNumerator(int n)
	{
		num = n;
	}
	public void setDenominator(int d)
	{
		den = d;
	}
	
	public String toPrint()
	{
		String s = this.num + "/" + this.den;
		return s;
	}
	
	public Frac reciprocal()
	{
		Frac temp = new Frac(den, num);
		return temp;
	}
	
	public Frac add(Frac f2)
	{
		int top = ((this.num * f2.den) + (f2.num * this.den));
		int bottom = this.den * f2.den;
		Frac f3 = new Frac(top,bottom);
		f3 = f3.reduce();
		return f3;
	}
	
	public Frac mult(Frac f2)
	{
		int top = this.num * f2.num;
		int bottom = this.den * f2.den;
		Frac f3 = new Frac(top,bottom);
		f3 = f3.reduce();
		return f3;
	}
	
	public Frac div(Frac f2)
	{
		f2 = f2.reciprocal(); 
		Frac f3 = this.mult(f2);
		f3 = f3.reduce();
		return f3;
	}
	private int gcd(int x,int y)
	{
		if(x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}
	public Frac reduce()
	{
		int g = gcd(num, den);
		Frac temp = new Frac(num/g, den/g);
		return temp;
	}
	public Frac sub(Frac f2)
	{
		int top = ((this.num * f2.den) - (f2.num * this.den));
		int bottom = this.den * f2.den;
		Frac f3 = new Frac(top,bottom);
		f3 = f3.reduce();
		int tempN;
		int tempD;
		if(f3.getDenominator() < 0) {
			tempN = f3.getNumerator();
			tempD = f3.getDenominator();
			f3.setNumerator(-tempN);
			f3.setDenominator(-tempD);
		}
		return f3;
	}
}
