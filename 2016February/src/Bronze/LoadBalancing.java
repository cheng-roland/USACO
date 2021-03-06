package Bronze;

import java.util.*;
import java.io.*;

public class LoadBalancing {
	
	public static class Point {
		int x; int y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class comparingByX implements Comparator<Point> {

		public int compare(Point o1, Point o2) {
			return o1.x - o2.x;
		}
	}
	
	public static class comparingByY implements Comparator<Point> {
		public int compare(Point o1, Point o2) {
			return o1.y - o2.y;
		}
	}
	
	static int N; int B;
	static int previousX = -1, previousY = -1;
	static int minMax = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
//		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2016February/src/Bronze/balancing.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2016February/src/Bronze/balancing.out"));
		Scanner in = new Scanner(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new File("balancing.out"));
		
		int N = in.nextInt();
		int B = in.nextInt();
		Point[] positionByX = new Point[N];
		Point[] positionByY = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.nextInt() - 1 ;
			int y = in.nextInt() - 1;
			positionByX[i] = new Point(x, y);
			positionByY[i] = new Point(x, y);
		}
		Point[] cows = positionByX.clone();
		Arrays.sort(positionByX, new comparingByX());
		Arrays.sort(positionByY, new comparingByY());
		
		for (int i = 0; i < N; i++) { //counter for x
			if (positionByX[i].x == previousX) continue;
			
			for (int j = 0; j < N; j++) { //counter for y
				
				if (positionByY[j].y == previousY) continue;
				int vLine = positionByX[i].x + 1;
				int hLine = positionByY[j].y + 1;
				if (hLine == 3 && vLine == 5) {
					System.out.print("debugging");
				}
				int topLeft = 0, topRight = 0, bottomLeft = 0, bottomRight = 0;
				for (int cowIndex = 0; cowIndex < N; cowIndex++) {
					if (cows[cowIndex].x < vLine && cows[cowIndex].y < hLine)
						topLeft++;
					if (cows[cowIndex].x < vLine && cows[cowIndex].y > hLine)
						bottomLeft++;
					if (cows[cowIndex].x > vLine && cows[cowIndex].y < hLine)
						topRight++;
					if (cows[cowIndex].x > vLine && cows[cowIndex].y > hLine)
						bottomRight++;	
				}
				int maximum = minMax(topLeft, topRight, bottomLeft, bottomRight);
				minMax = Math.min(minMax, maximum);
				
				previousY = positionByY[j].y;	
			}
			
			previousX = positionByX[i].x;
		}
		
		out.print(minMax);
		System.out.println(minMax);
		out.close();
		
		

	}
	
	public static int minMax(int a, int b, int c, int d) {
		int max1 = Integer.max(a, b);
		int max2 = Integer.max(c, d);
		int max = Integer.max(max1, max2);
		
		return max;
	}

}
