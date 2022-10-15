How to print multiplication table in JAVA?

A sample output for example input 6:-

  * |  1  2  3  4  5  6
-----------------------
  1 |  1  2  3  4  5  6
  2 |  2  4  6  8 10 12
  3 |  3  6  9 12 15 18
  4 |  4  8 12 16 20 24
  5 |  5 10 15 20 25 30
  6 |  6 12 18 24 30 36

package com.yuvaraj.alg;
public class MulTable 
{
	public static void main( String[] args )
	{
		int n = 6;
		int spc = String.valueOf(n * n).length() + 1;

		System.out.format("%" + spc + "s |", "*");
		for (int i = 0; i < n; i++)
			System.out.format("%" + spc + "s", i+1);
		System.out.format("%n");
		
		for (int i = 0; i < n * spc + 1 * (spc + 2); i++)
			System.out.format("%s", "-");
		System.out.format("%n");
		
		for (int i = 0; i < n; i++) {
			System.out.format("%" + spc + "s |", i+1);
			for (int j = 0; j < n; j++)
			{
				System.out.format("%" + spc + "s", (i+1) * (j+1));
			}
			System.out.format("%n");
		}
	}
}

