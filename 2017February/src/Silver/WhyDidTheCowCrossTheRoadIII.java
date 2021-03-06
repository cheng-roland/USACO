package Silver;

import java.util.*;
import java.io.*;

public class WhyDidTheCowCrossTheRoadIII {
	private static int[] dir_x = {0, 0, 1, -1};
	private static int[] dir_y = {1, -1, 0, 0};
	private static boolean[][] hasCow;
	private static boolean[][] visited;
	private static HashSet<String>[][] roads;
	private static int N, K, R;
	

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("countcross.in"));
		PrintWriter out = new PrintWriter(new File("countcross.out"));
		N = in.nextInt();
		K = in.nextInt();
		R = in.nextInt();
		hasCow = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		roads = new HashSet[N + 1][N + 1];
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				roads[x][y] = new HashSet<String>();
			}
		}
		for (int r = 1; r <= R; r++) {
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			roads[x1][y1].add(x2 + "_" + y2);
			roads[x2][y2].add(x1 + "_" + y1);
		}
		for (int i = 1; i <= K; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			hasCow[x][y] = true;
		}

		
		int total = 0;
		int pairCount = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (hasCow[i][j] == true) {
					visited = new boolean[N + 1][N + 1];
					total = dfs(i,j);
					pairCount += (K - total);
				}
				
			}
		}
		
		
		out.println(pairCount / 2);
		System.out.println(pairCount / 2);
		out.close();
		
		
		
		
		
		
		
	}
	
	static int dfs(int x, int y) {
		int canReach = 0;
		visited[x][y] = true;
		if (hasCow[x][y])
			canReach++;
		for (int d = 0; d < 4; d++) {
			int dX = x + dir_x[d];
			int dY = y + dir_y[d];
			if (dX <= N && dX >= 1 && dY >= 1 && dY <= N && !visited[dX][dY] && !roads[x][y].contains(dX + "_" + dY) ) {
					canReach += dfs(dX, dY);
			}
		}
		return canReach;
	}

}
