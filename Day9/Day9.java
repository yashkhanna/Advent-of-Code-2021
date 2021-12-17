import java.io.*;
import java.util.*;

public class Day9 {
	public static void main(String[] args) throws IOException {
		// f1("Day9-small.txt", 5);
  //       f1("Day9.txt", 100);

        f2("Day9-small.txt", 5);
        f2("Day9.txt", 100);
	}

    private static void f1(String fileName, int r) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int c = 0;

        String[] inp = new String[r];

        for (int i=0;i<r;i++) {
            inp[i] = br.readLine();
            c = inp[i].length();
        }

        int ans = 0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                char curr = inp[i].charAt(j);
                boolean[] b = new boolean[4];

                if (i - 1 >= 0) {
                    if (curr >= inp[i - 1].charAt(j)) {
                        b[0] = true;
                    }
                }

                if (i + 1 <= r - 1) {
                    if (curr >= inp[i + 1].charAt(j)) {
                        b[1] = true;
                    }
                }

                if (j - 1 >= 0) {
                    if (curr >= inp[i].charAt(j - 1)) {
                        b[2] = true;
                    }
                }

                if (j + 1 <= c - 1) {
                    if (curr >= inp[i].charAt(j + 1)) {
                        b[3] = true;
                    }
                }

                if (!(b[0] || b[1] || b[2] || b[3])) {
                    ans += curr - '0' + 1;
                }
            }
        }
        System.out.println(ans);
    }

    private static void f2(String fileName, int r) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int c = 0;

        String[] inp = new String[r];

        for (int i=0;i<r;i++) {
            inp[i] = br.readLine();
            c = inp[i].length();
        }

        boolean[][] marked = new boolean[r][c];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                char curr = inp[i].charAt(j);
                boolean[] b = new boolean[4];

                if (i - 1 >= 0) {
                    if (curr >= inp[i - 1].charAt(j)) {
                        b[0] = true;
                    }
                }

                if (i + 1 <= r - 1) {
                    if (curr >= inp[i + 1].charAt(j)) {
                        b[1] = true;
                    }
                }

                if (j - 1 >= 0) {
                    if (curr >= inp[i].charAt(j - 1)) {
                        b[2] = true;
                    }
                }

                if (j + 1 <= c - 1) {
                    if (curr >= inp[i].charAt(j + 1)) {
                        b[3] = true;
                    }
                }

                if (!(b[0] || b[1] || b[2] || b[3])) {
                    pq.add(bfs(inp, r, c, i, j, marked));
                }
            }
        }

        int x = pq.poll();
        int y = pq.poll();
        int z = pq.poll();
        System.out.println(x + " " + y +" "+ z + " " + x * y * z);
    }

    private static int bfs(String[] inp, int r, int c, int i, int j, boolean[][] marked) {
        Queue<int[]> q = new ArrayDeque<>();
        int size = 0;
        q.add(new int[]{i, j});
        marked[i][j] = true;

        while (!q.isEmpty()) {
            int[] nbr = q.poll();
            size++;
            int x = nbr[0];
            int y = nbr[1];
            int curr = inp[x].charAt(y);

            boolean[] b = new boolean[4];

            if (x - 1 >= 0) {
                if (curr <= inp[x - 1].charAt(y) && !marked[x-1][y] && inp[x - 1].charAt(y) != '9') {
                    q.add(new int[]{x-1, y});
                    marked[x-1][y] = true;
                }
            }

            if (x + 1 <= r - 1) {
                if (curr <= inp[x + 1].charAt(y) && !marked[x+1][y] && inp[x + 1].charAt(y) != '9') {
                    q.add(new int[]{x+1, y});
                    marked[x+1][y] = true;
                }
            }

            if (y - 1 >= 0) {
                if (curr <= inp[x].charAt(y - 1) && !marked[x][y - 1] && inp[x].charAt(y - 1) != '9') {
                    q.add(new int[]{x, y - 1});
                    marked[x][y - 1] = true;
                }
            }

            if (y + 1 <= c - 1) {
                if (curr <= inp[x].charAt(y + 1) && !marked[x][y + 1] && inp[x].charAt(y + 1) != '9') {
                    q.add(new int[]{x, y + 1});
                    marked[x][y + 1] = true;
                }
            }
        }
        return size;
    }
}
