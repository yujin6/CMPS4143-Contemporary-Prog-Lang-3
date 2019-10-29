package fractions;

public class FracMain {
	public static void main(String[] args)
	{
		Frac f1 = new Frac();
		Frac f2 = new Frac(10, 16);
		Frac f3 = new Frac();
		f1.setNumerator(2);
		f1.setDenominator(7);
		String s1 = f1.toPrint();
		System.out.println(s1);
		f3 = f1.reciprocal();
		String s3 = f3.toPrint();
		System.out.println(s3);
		f3 = f1.add(f2);
		s3 = f3.toPrint();
		System.out.println(s3);
		f3 = f1.mult(f2);
		s3 = f3.toPrint();
		System.out.println(s3);
		f3 = f1.div(f2);
		s3 = f3.toPrint();
		System.out.println(s3);
		f3 = f3.reduce();
		s3 = f3.toPrint();
		System.out.println(s3);
		f3 = f1.sub(f2);
		s3 = f3.toPrint();
		System.out.println(s3);
		/*print(s3);
*/		
	};


}
