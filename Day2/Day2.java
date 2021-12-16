import java.io.*;
import java.util.*;

class Day2 {
	public static void main(String[] args) throws IOException {
		f1();
        f2();
	}

    private static void f1() throws IOException {
        File file = new File("Day2.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        long x = 0L;
        long y = 0L;
        for (int i=0;i<1000;i++) {
            String[] inp = br.readLine().split(" ");
            String command = inp[0];
            long delta = Long.parseLong(inp[1]);

            if (command.equals("forward")) {
                x += delta;
            } else if (command.equals("down")) {
                y -= delta;
            } else {
                y += delta;
            }
        }
        System.out.println(x * Math.abs(y));
    }

    private static void f2() throws IOException {
        File file = new File("Day2.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        long x = 0L;
        long y = 0L;
        long aim = 0L;
        long ans;
        for (int i=0;i<1000;i++) {
            String[] inp = br.readLine().split(" ");
            String command = inp[0];
            long delta = Long.parseLong(inp[1]);

            if (command.equals("forward")) {
                x += delta;
                y += aim * delta;
            } else if (command.equals("down")) {
                aim += delta;
            } else {
                aim -= delta;
            }
        }
        ans = x * y;
        System.out.println(ans);
    }
}