package Bronze;

import java.util.*;
import java.io.*;

public class CircularBarn {
	
	static int[] barn;
	static int n;

	public static void main(String[] args) throws IOException{
//		Scanner in = new Scanner(new File("C://Users//Gavin//Dropbox//USACO//2016February//src//Bronze//cbarn.in"));
//		PrintWriter out = new PrintWriter(new File("C://Users//Gavin//Dropbox//USACO//2016February//src//Bronze//cbarn.out"));
		Scanner in = new Scanner(new File("cbarn.in"));
		PrintWriter out = new PrintWriter(new File("cbarn.out"));
		n = in.nextInt();
		barn = new int[n];
		for (int i = 0; i < n; i++) {
			barn[i] = in.nextInt();
		}
		int min = Integer.MAX_VALUE;
		int distance;
		for (int trial = 0; trial <= n; trial++) {
			distance = 0;
			for (int index = 0; index < n; index++) {
				distance += index * barn[(trial + index) % n];
			}
			if (distance < min)
				min = distance;
		}
		
		out.print(min);
		out.close();
	}

}
