package Bronze;

import java.util.*;
import java.io.*;

public class BackAndForth {

	
	
	private static ArrayList<Integer> b1 = new ArrayList<Integer>();
	
	private static ArrayList<Integer> b2 = new ArrayList<Integer>();
	
	private static int[] bucketsMoved = new int [4];
	private static boolean[] possibleValues = new boolean[2001];
	
	public static void main(String[] args) throws Exception {
//		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Bronze/backforth.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Bronze/backforth.out"));
		Scanner in = new Scanner(new FileReader("backforth.in"));
		PrintWriter out = new PrintWriter(new File("backforth.out"));
		
		for (int i = 0; i < 10; i++) {
			b1.add(in.nextInt());
		}
		
		for (int i = 0; i < 10; i++) {
			b2.add(in.nextInt());
		}
		
		for (int i = 0; i < 10; i++) {
			tuesday(i, b1, b2);
		}
		
		int counter = 0;
		for (int i = 0; i < 2001; i++) {
			if (possibleValues[i]) {
				counter++;
				System.out.println(i);
			}
		}
		
		System.out.print(counter);
		out.print(counter);
		out.close();
		
	}
	
	//going from first barn to second barn
	public static void tuesday(int milkBucket, ArrayList<Integer> barn1, ArrayList<Integer> barn2) {

		ArrayList<Integer> temp1 = new ArrayList<Integer>(barn1);
		ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
		
		bucketsMoved[0] = temp1.get(milkBucket);
		//after next two lines barn1 should have 9 buckets, and barn2 should have 11 buckets
		temp2.add(temp1.get(milkBucket));
		temp1.remove(milkBucket);
		//moving next bucket
		for (int i = 0; i <= 10; i++) {
			wednesday(i, temp1, temp2 );
		}
	}
	
	//going from second barn to first barn
	public static void wednesday(int milkBucket, ArrayList<Integer> barn1, ArrayList<Integer> barn2) {
		
		ArrayList<Integer> temp1 = new ArrayList<Integer>(barn1);
		ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
		
		
		bucketsMoved[1] = temp2.get(milkBucket);
		//after the next line barn1 should have 10 buckets,and barn2 should have 10 buckets
		temp1.add(temp2.remove(milkBucket));
		
		for (int i = 0; i < 10; i++) {
			thursday(i, temp1, temp2);
			
		}
	}
	
	//going from first barn to second barn
	public static void thursday(int milkBucket, ArrayList<Integer> barn1, ArrayList<Integer> barn2) {
		ArrayList<Integer> temp1 = new ArrayList<Integer>(barn1);
		ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
		
		bucketsMoved[2] = temp1.get(milkBucket);
		
		//after the next line barn1 should have 9 buckets and barn2 should have 11 buckets
		temp2.add(temp1.remove(milkBucket));
		for (int i = 0; i <= 10; i++) {
			friday(i, temp1, temp2);
		}
	}
	
	public static void friday(int milkBucket, ArrayList<Integer> barn1, ArrayList<Integer> barn2) {
		ArrayList<Integer> temp2 = new ArrayList<Integer>(barn2);
		
		bucketsMoved[3] = temp2.get(milkBucket);
		
		int change = bucketsMoved[3] + bucketsMoved[1] - bucketsMoved[2] - bucketsMoved[0];
		possibleValues[1000 + change] = true;
	}

}
