import java.io.*;
import java.util.*;

public class Day11 {

    private static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	public static void main(String[] args) throws IOException {
		// f1("Day11-small.txt");
  //       f1("Day11.txt");

        f2("Day11-small.txt");
        f2("Day11.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int size = 10;
        int[][] inp = new int[size][size];

        for (int i=0;i<size;i++) {
            String s = br.readLine();
            for (int j=0;j<size;j++) {
                // System.out.println(s.charAt(j)+" ");
                inp[i][j] = s.charAt(j) - '0';
            }
        }

        long ans = 0L;

        for (int step = 0; step < 100; step++) {
            ans += incrementAndCheck(inp, size);
        }
        System.out.println(ans);
    }

    private static long incrementAndCheck(int[][] inp, int size) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] mark = new boolean[size][size];

        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                inp[i][j]++;
                if (inp[i][j] == 10) {
                    q.add(new int[]{i, j});
                    mark[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : delta) {
                int nextr = r + d[0];
                int nextc = c + d[1];

                if (nextr >= 0 && nextr < size && nextc >= 0 && nextc < size) {
                    if (!mark[nextr][nextc]) {
                        inp[nextr][nextc]++;
                        if (inp[nextr][nextc] == 10) {
                            q.add(new int[]{nextr, nextc});
                            mark[nextr][nextc] = true;
                        }
                    }
                }
            }
        }

        long op = 0L;

        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                if (inp[i][j] == 10) {
                    inp[i][j] = 0;
                    op++;
                }
            }
        }

        return op;
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int size = 10;
        int[][] inp = new int[size][size];

        for (int i=0;i<size;i++) {
            String s = br.readLine();
            for (int j=0;j<size;j++) {
                // System.out.println(s.charAt(j)+" ");
                inp[i][j] = s.charAt(j) - '0';
            }
        }

        long ans = 0L;

        while (!incrementAndCheck2(inp, size)) {
            ans++;
        }
        System.out.println(ans + 1);
    }

    private static boolean incrementAndCheck2(int[][] inp, int size) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] mark = new boolean[size][size];

        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                inp[i][j]++;
                if (inp[i][j] == 10) {
                    q.add(new int[]{i, j});
                    mark[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : delta) {
                int nextr = r + d[0];
                int nextc = c + d[1];

                if (nextr >= 0 && nextr < size && nextc >= 0 && nextc < size) {
                    if (!mark[nextr][nextc]) {
                        inp[nextr][nextc]++;
                        if (inp[nextr][nextc] == 10) {
                            q.add(new int[]{nextr, nextc});
                            mark[nextr][nextc] = true;
                        }
                    }
                }
            }
        }

        int op = 0;

        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                if (inp[i][j] == 10) {
                    inp[i][j] = 0;
                    op++;
                }
            }
        }

        if (op == 100) {
            return true;
        } else {
            return false;
        }
    }

    private static void printInp(int[][] inp, int size) {
        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                System.out.print(inp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
