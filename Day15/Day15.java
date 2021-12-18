import java.io.*;
import java.util.*;

public class Day15 {

	public static void main(String[] args) throws IOException {
		f1("Day15-small.txt", 10);
        f1("Day15.txt", 100);

        f2("Day15-small.txt", 10);
        f2("Day15.txt", 100);
	}

    private static void f1(String fileName, int r) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] inp = new String[r];

        int c = 0;

        for (int i=0;i<r;i++) {
            inp[i] = br.readLine();
            c = inp[i].length();
        }

        boolean[][] v = new boolean[r][c];
        int[][] dist = new int[r][c];

        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> ((int []) a)[2] - ((int []) b)[2]);
        pq.add(new int[]{0, 0, dist[0][0]});
        v[0][0] = true;

        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            for (int[] delta : deltas) {
                int nextr = delta[0] + curr[0];
                int nextc = delta[1] + curr[1];

                if (nextr >= 0 && nextr < r && nextc >= 0 && nextc < c) {
                    if (v[nextr][nextc]) {
                        continue;
                    } else {
                        if (dist[nextr][nextc] > curr[2] + (inp[nextr].charAt(nextc) - '0')) {
                            dist[nextr][nextc] = curr[2] + (inp[nextr].charAt(nextc) - '0');
                            pq.add(new int[]{nextr, nextc, dist[nextr][nextc]});
                            v[nextr][nextc] = true;
                        }
                    }
                }
            }
        }

        System.out.println(dist[r-1][c-1]);
    }

    private static void f2(String fileName, int row) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] inp = new String[row];

        int r = 5 * row;
        int col = 0;

        for (int i=0;i<row;i++) {
            inp[i] = br.readLine();
            col = inp[i].length();
        }

        int c = col * 5;

        boolean[][] v = new boolean[r][c];
        int[][] dist = new int[r][c];

        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> ((int []) a)[2] - ((int []) b)[2]);
        pq.add(new int[]{0, 0, dist[0][0]});
        v[0][0] = true;

        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            for (int[] delta : deltas) {
                int nextr = delta[0] + curr[0];
                int nextc = delta[1] + curr[1];

                if (nextr >= 0 && nextr < r && nextc >= 0 && nextc < c) {
                    if (v[nextr][nextc]) {
                        continue;
                    } else {
                        if (dist[nextr][nextc] > curr[2] + weight(nextr, nextc, inp, row, col)) {
                            dist[nextr][nextc] = curr[2] + weight(nextr, nextc, inp, row, col);
                            pq.add(new int[]{nextr, nextc, dist[nextr][nextc]});
                            v[nextr][nextc] = true;
                        }
                    }
                }
            }
        }

        System.out.println(dist[r-1][c-1]);
    }

    private static int weight(int nextr, int nextc, String[] inp, int row, int col) {
        int rr = nextr / row;
        int cc = nextc / col;

        int wt = inp[nextr % row].charAt(nextc % col) - '0';

        for (int i=0;i<rr+cc;i++) {
            wt++;
            if (wt == 10) {
                wt = 1;
            }
        }
        return wt;
    }
}