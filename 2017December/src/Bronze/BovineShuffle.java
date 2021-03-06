package Bronze;

import java.util.*;
import java.io.*;

public class BovineShuffle {
	
	private static int N;
	private static int[] shufflePattern;
	private static int[] finalOrder;
	private static int[] oneShufflesBefore;
	private static int[] twoShufflesBefore;
	private static int[] threeShufflesBefore;

	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/stuff/src/stuff/shuffle.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/stuff/src/stuff/shuffle.out"));
		Scanner in = new Scanner(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new File("shuffle.out"));
		
		N = in.nextInt();
		shufflePattern = new int[N + 1];
		finalOrder = new int[N + 1];
		oneShufflesBefore = new int[N + 1];
		twoShufflesBefore = new int[N + 1];
		threeShufflesBefore = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			shufflePattern[i] = in.nextInt();
		}
		for (int i = 1; i <= N; i++) {
			finalOrder[i] = in.nextInt();
		}
		
		for (int i = 1; i <= N; i++) {
			int num = shufflePattern[i];
			oneShufflesBefore[i] = finalOrder[num];
		}
		
		for (int i = 1; i <= N; i++) {
			int num = shufflePattern[i];
			twoShufflesBefore[i] = oneShufflesBefore[num];
		}
		
		for (int i = 1; i <= N; i++) {
			int num = shufflePattern[i];
			threeShufflesBefore[i] = twoShufflesBefore[num];
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(threeShufflesBefore[i]);
			out.println(threeShufflesBefore[i]);
		}
		out.close();

	}

}
