import java.io.*;
import java.util.*;

public class Day5 {
	public static void main(String[] args) throws IOException {
		// f1("Day5-small.txt", 10);
        // f1("Day5.txt", 500);
        f2("Day5-small.txt", 10);
        f2("Day5.txt", 500);
	}

    private static void f1(String fileName, int rows) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int[][] points = new int[rows][4];

        int maxX = 0;
        int maxY = 0;

        for (int i=0;i<rows;i++) {
            String[] s = br.readLine().split(" -> ");
            String[] s1 = s[0].split(",");
            String[] s2 = s[1].split(",");
            points[i][0] = Integer.parseInt(s1[0]);
            points[i][1] = Integer.parseInt(s1[1]);
            points[i][2] = Integer.parseInt(s2[0]);
            points[i][3] = Integer.parseInt(s2[1]);

            maxX = Math.max(maxX, Math.max(points[i][0], points[i][2]));
            maxY = Math.max(maxY, Math.max(points[i][1], points[i][3]));
        }

        int[][] grid = new int[maxX+1][maxY+1];

        for (int i=0;i<rows;i++) {
            // Vertical Line
            if (points[i][0] == points[i][2]) {
                int c = points[i][0];
                int min = Math.min(points[i][1], points[i][3]);
                int max = Math.max(points[i][1], points[i][3]);

                for (int k=min;k<=max;k++) {
                    grid[k][c]++;
                }
            }

            // Horizontal Line
            if (points[i][1] == points[i][3]) {
                int r = points[i][1];
                int min = Math.min(points[i][0], points[i][2]);
                int max = Math.max(points[i][0], points[i][2]);

                for (int k=min;k<=max;k++) {
                    grid[r][k]++;
                }
            }
        }

        int ans = 0;

        for (int i=0;i<=maxX;i++) {
            for (int j=0;j<=maxY;j++) {
                System.out.print(grid[i][j]);
                if (grid[i][j] >= 2) {
                    ans++;
                }
            }
            System.out.println();
        }
        System.out.println(ans);
    }

    private static void f2(String fileName, int rows) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int[][] points = new int[rows][4];

        int maxR = 0;
        int maxC = 0;

        for (int i=0;i<rows;i++) {
            String[] s = br.readLine().split(" -> ");
            String[] s1 = s[0].split(",");
            String[] s2 = s[1].split(",");
            points[i][0] = Integer.parseInt(s1[0]);
            points[i][1] = Integer.parseInt(s1[1]);
            points[i][2] = Integer.parseInt(s2[0]);
            points[i][3] = Integer.parseInt(s2[1]);

            maxC = Math.max(maxC, Math.max(points[i][0], points[i][2]));
            maxR = Math.max(maxR, Math.max(points[i][1], points[i][3]));
        }

        int[][] grid = new int[maxR+1][maxC+1];

        for (int i=0;i<rows;i++) {
            // Vertical Line
            if (points[i][0] == points[i][2]) {
                int c = points[i][0];
                int min = Math.min(points[i][1], points[i][3]);
                int max = Math.max(points[i][1], points[i][3]);

                for (int k=min;k<=max;k++) {
                    grid[k][c]++;
                }
                continue;
            }

            // Horizontal Line
            if (points[i][1] == points[i][3]) {
                int r = points[i][1];
                int min = Math.min(points[i][0], points[i][2]);
                int max = Math.max(points[i][0], points[i][2]);

                for (int k=min;k<=max;k++) {
                    grid[r][k]++;
                }
                continue;
            }

            // Diagonal Line
            if (points[i][0] > points[i][2]) {
                int r = points[i][1];
                int c = points[i][0];
                if (points[i][1] > points[i][3]) {
                    while (r >= points[i][3]) {
                        grid[r][c]++;
                        r--;
                        c--;
                    }
                } else {
                    while (r <= points[i][3]) {
                        grid[r][c]++;
                        r++;
                        c--;
                    }
                }
            } else {
                int r = points[i][1];
                int c = points[i][0];
                if (points[i][1] < points[i][3]) {
                    while (r <= points[i][3]) {
                        grid[r][c]++;
                        r++;
                        c++;
                    }
                } else {
                    while (r >= points[i][3]) {
                        grid[r][c]++;
                        r--;
                        c++;
                    }
                }
            }
        }

        int ans = 0;

        for (int i=0;i<=maxR;i++) {
            for (int j=0;j<=maxC;j++) {
                // System.out.print(grid[i][j]);
                if (grid[i][j] >= 2) {
                    ans++;
                }
            }
            // System.out.println();
        }
        System.out.println(ans);
    }
}

