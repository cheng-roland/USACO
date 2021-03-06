package Silver;

import java.util.*;
import java.io.*;

public class MountainView {
	
	public static class Mountain {
		int peakX; int peakY; int left; int right;
		public Mountain(int peakX, int peakY, int left, int right) { //left and right are the x-cordinates (x-intercepts)
			this.peakX= peakX;
			this.peakY = peakY;
			this.left = left; 
			this.right = right;
		}
	}

	private static int N;
	private static int count;
	private static Mountain[] m;
	public static void main(String[] args) throws IOException{
		
		
//		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/Random/src/mountains.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/Random/src/mountains.out"));
//		
		Scanner in = new Scanner(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new File("mountains.out"));
		
		//reading input
		N = in.nextInt();
		m = new Mountain[N];
		for (int i = 0; i < N; i++) {
			int peakX = in.nextInt();
			int peakY = in.nextInt();
			m[i] = new Mountain(peakX, peakY, peakX - peakY, peakX + peakY);
		}
		
		for (int i =0; i < N; i++) {
			if (m[i] == null) continue;
			calculatation(i);
		}
		System.out.println(count);
		out.println(count);
		out.close();		
	}
	
	public static void calculatation(int index) {
		for (int i = 0; i < N; i++) {
			if (m[i] == null) continue;
			if (i == index) continue;
			Mountain comparingM = m[i];
//			System.out.println(comparingM.peakX + " " + comparingM.peakY + " " + comparingM.left + " " + comparingM.right);
			Mountain currentM = m[index];
			//System.out.println(currentM.peakX + " " + currentM.peakY +  " " + currentM.left + " " + currentM.right);
			
			if (comparingM.left < currentM.left && comparingM.right > currentM.right) { //currentM's peak lies within another mountains peak
				m[index] = null;
				return;
			} else if (comparingM.left < currentM.left && comparingM.right == currentM.right || comparingM.left == currentM.left && comparingM.right > currentM.right)  { //currentM's peak lies on  side of another mountains triagle
				m[index] = null;
				return;
			}
			else if (currentM.left < comparingM.left && currentM.right > comparingM.right) {//the comparing Mountain lies within current mountain's peak
				m[i] = null;
			} else if (comparingM.left > currentM.left && comparingM.right == currentM.right || comparingM.left == currentM.left && comparingM.right < currentM.right) {
				m[i] = null;
			}
		}
//		System.out.println(m[index].peakX + " " + m[index].peakY +  " " +m[index].left + " " +m[index].right);
		count++; //passes through all cases, and no mountains are blocking this mountain
			
	}

}
