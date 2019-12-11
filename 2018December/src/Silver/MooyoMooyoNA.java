package Silver;

import java.util.*;
import java.io.*;

public class MooyoMooyoNA {
	
	private static int N, K;
	private static int[][] grid;
	private static boolean gridChanged;

	public static void solve(String inFile, String outFile) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(inFile));
		PrintWriter out = new PrintWriter(new File(outFile));
		
		StringTokenizer firstLine = new StringTokenizer(in.readLine());
		N = Integer.parseInt(firstLine.nextToken());
		K = Integer.parseInt(firstLine.nextToken());
		
		grid = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			String nextLine = in.readLine();
			for (int j = 0; j < 10; j++) {
				grid[i][j] = Character.getNumericValue(nextLine.charAt(j));
			}
		}
		
//		printGrid();
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.print(grid[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		do{
			gridChanged = false;
			reformGrid();
			for (int row = N - 1; row >= 0; row--) {
				for (int col = 0; col < 10; col++) {
					if (!(grid[row][col] == 0)) { 
						boolean[][] visited = new boolean[N][10];
						boolean delete = floodfillcheck(row, col, grid[row][col], 1, visited);
//						System.out.println("Color: " + grid[row][col] + " Row/Col: " + row + "/" + col);
						if (delete) floodfilldelete(row, col, grid[row][col]);
//						printGrid();
					}
				}
			}
		} while (gridChanged);
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
//				System.out.print(grid[i][j]);
				out.print(grid[i][j]);
			}
			out.println();
//			System.out.println();
		}
		out.close();

	}
	public static void main(String[] args) throws IOException {
		
//		BufferedReader in = new BufferedReader(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Silver/mooyomooyo.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Silver/mooyomooyo.out"));
		
		String file = "D:\\Repos\\USACO\\2018December\\src\\Silver\\mooyomooyo_silver_dec18\\"; //fileName
		for (int i = 1; i <= 10; i++) {
			System.out.print("Test#" + i + " ... ");
			String inFile = file + i + ".in";
			String myOutFile = file + i + ".myout";
			solve(inFile, myOutFile);
			String correctOutFile = file + i + ".out";
			compare(correctOutFile, myOutFile);
		}
		
	}
	
	public static void compare (String correctOutFile, String myOutFile) throws IOException {
		Scanner correctOutputScanner = new Scanner(new File(correctOutFile));
		Scanner myOutputScanner = new Scanner(new File(myOutFile));
		boolean passed = true;
		int lineNum = 1;
		while(correctOutputScanner.hasNextLine()) {
			String correctoutputLine = correctOutputScanner.nextLine();
			if(!myOutputScanner.hasNextLine()) {
				System.out.print(" failed at line#" + lineNum);
				System.out.println("...Expected: " + correctoutputLine + ", Yours: missing");
				return;
			} else {
				String myOutputLine = myOutputScanner.nextLine();
				if (!correctoutputLine.equals(myOutputLine)) {
					System.out.print(" failed at line#" + lineNum);
					System.out.println("...Expected: " + correctoutputLine + ", Yours: " + myOutputLine);
					return;
				}
			}
			lineNum++;
		}
		System.out.println(" passed. ");
		
	}
	
	public static void printGrid (){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	public static boolean floodfillcheck(int row, int  col, int color, int count, boolean[][] visited) {
		if (row >= N || row < 0 || col >= 10 || col < 0) return false;
		if (grid[row][col] != color) return false;
		if (visited[row][col]) return false;
		visited[row][col] = true;
		if (count == K) return true;
		
		return floodfillcheck(row + 1, col, color, count + 1, visited) ||
		floodfillcheck(row - 1, col, color, count + 1, visited) ||
		floodfillcheck(row, col + 1, color, count + 1,visited) ||
		floodfillcheck(row, col - 1, color, count + 1, visited);
		
	}
	
	public static void floodfilldelete(int row, int col, int color) {
		if (row >= N || row < 0 || col >= 10 || col < 0) return;
		if (grid[row][col] != color) return;
		grid[row][col] = 0;
		gridChanged = true;
		floodfilldelete(row + 1, col, color);
		floodfilldelete(row - 1, col, color);
		floodfilldelete(row, col + 1, color);
		floodfilldelete(row, col - 1, color);
	}
	
	public static void reformGrid() {
		for (int col = 0; col < 10; col++) {
			Queue<Integer> zero = new LinkedList<Integer>();
			for (int row = N - 1; row >= 0; row--) {
				if (grid[row][col] == 0) zero.add(row);
				else {
					if (!zero.isEmpty()) {
						int r = zero.poll();
						grid[r][col] = grid[row][col];
						grid[row][col] = 0;
						zero.add(row);
					}
				}
			}
		}
		printGrid();
	}

}