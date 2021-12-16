import java.io.*;
import java.util.*;

class Day1 {
	public static void main(String[] args) throws IOException {
        f1();
        f2();
	}

    private static void f1() throws IOException {
        File file = new File("Day1.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        int curr;
        int ans = 0;
        int prev = Integer.parseInt(br.readLine());
        for (int i=1;i<2000;i++) {
            curr = Integer.parseInt(br.readLine());
            if (curr > prev) {
                ans++;
            }
            prev = curr;
        }
        System.out.println(ans);
    }

    private static void f2() throws IOException {
        File file = new File("Day1.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        int ans = 0;
        int one = Integer.parseInt(br.readLine());
        int two = Integer.parseInt(br.readLine());
        int three = Integer.parseInt(br.readLine());
        for (int i=3;i<2000;i++) {
            int temp = Integer.parseInt(br.readLine());
            int sum = one + two + three;
            int newSum = sum - one + temp;
            if (newSum > sum) {
                ans++;
            }
            one = two;
            two = three;
            three = temp;
        }
        System.out.println(ans);
    }
}