import java.io.*;
import java.util.*;

public class Day13 {

	public static void main(String[] args) throws IOException {
		// f1("Day13-small.txt");
        // f1("Day13.txt");

        // f2("Day13-small.txt");
        f2("Day13.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int maxR = 0;
        int maxC = 0;
        int n = 0;

        while(true) {
            String s = br.readLine();
            if (s == null || s.equals("") || s.charAt(0) == '\n') {
                break;
            }
            String[] ss = s.split(",");
            int c = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            maxC = Math.max(maxC, c);
            maxR = Math.max(maxR, r);
            n++;
        }

        boolean[][] inp = new boolean[maxR+1][maxC+1];
        br = new BufferedReader(new FileReader(file));
        while(true) {
            String s = br.readLine();
            if (s == null || s.equals("") || s.charAt(0) == '\n') {
                break;
            }
            String[] ss = s.split(",");
            int c = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            inp[r][c] = true;
        }

        
        String[] temp = (br.readLine().split(" "))[2].split("=");
        String p1 = temp[0];
        int p2 = Integer.parseInt(temp[1]);

        if (p1.equals("y")) {
            int newRow = p2 - 1;
            for (int i=p2 + 1; i<=maxR;i++) {
                for (int j=0;j<=maxC;j++) {
                    inp[newRow][j] |= inp[i][j];
                }
                newRow--;
            }
            maxR = p2 - 1;
        } else {
            int newCol = p2 - 1;
            for (int i=p2+1; i<=maxC;i++) {
                for (int j=0;j<=maxR;j++) {
                    inp[j][newCol] |= inp[j][i];
                }
                newCol--;
            }
            maxC = p2 - 1;
        }
        // print(inp, 0, maxR, 0, maxC);
        System.out.println(count(inp, 0, maxR, 0, maxC));
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int maxR = 0;
        int maxC = 0;
        int n = 0;

        while(true) {
            String s = br.readLine();
            if (s == null || s.equals("") || s.charAt(0) == '\n') {
                break;
            }
            String[] ss = s.split(",");
            int c = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            maxC = Math.max(maxC, c);
            maxR = Math.max(maxR, r);
            n++;
        }

        boolean[][] inp = new boolean[maxR+1][maxC+1];
        br = new BufferedReader(new FileReader(file));
        while(true) {
            String s = br.readLine();
            if (s == null || s.equals("") || s.charAt(0) == '\n') {
                break;
            }
            String[] ss = s.split(",");
            int c = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            inp[r][c] = true;
        }

        while (true) {
            String s = br.readLine();
            if (s == null || s.equals("")) {
                break;
            }
            String[] temp = (s.split(" "))[2].split("=");
            String p1 = temp[0];
            int p2 = Integer.parseInt(temp[1]);

            if (p1.equals("y")) {
                int newRow = p2 - 1;
                for (int i=p2 + 1; i<=maxR;i++) {
                    for (int j=0;j<=maxC;j++) {
                        inp[newRow][j] |= inp[i][j];
                    }
                    newRow--;
                }
                maxR = p2 - 1;
            } else {
                int newCol = p2 - 1;
                for (int i=p2+1; i<=maxC;i++) {
                    for (int j=0;j<=maxR;j++) {
                        inp[j][newCol] |= inp[j][i];
                    }
                    newCol--;
                }
                maxC = p2 - 1;
            }
        }
        print(inp, 0, maxR, 0, maxC);
    }

    private static void print(boolean[][] inp, int rowStart, int rowEnd, int colStart, int colEnd) {
        for (int i=rowStart;i<=rowEnd;i++) {
            for (int j=colStart;j<=colEnd;j++) {
                if (inp[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();       
        }
    }

    private static int count(boolean[][] inp, int rowStart, int rowEnd, int colStart, int colEnd) {
        int ans = 0;
        for (int i=rowStart;i<=rowEnd;i++) {
            for (int j=colStart;j<=colEnd;j++) {
                if (inp[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}