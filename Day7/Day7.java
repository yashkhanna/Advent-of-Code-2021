import java.io.*;
import java.util.*;

public class Day7 {
	public static void main(String[] args) throws IOException {
		f1("Day7-small.txt");
        f1("Day7.txt");
        f2("Day7-small.txt");
        f2("Day7.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] s = br.readLine().split(",");
        int n = s.length;
        int[] a = new int[n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i=0;i<n;i++) {
            a[i] = Integer.parseInt(s[i]);
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        long sum = Long.MAX_VALUE;

        for (int i=min;i<=max;i++) {
            long temp = 0L;
            for (int e : a) {
                temp += Math.abs((long) e - (long) i);
            }
            sum = Math.min(sum, temp);
        }
        System.out.println(sum);
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] s = br.readLine().split(",");
        int n = s.length;
        int[] a = new int[n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i=0;i<n;i++) {
            a[i] = Integer.parseInt(s[i]);
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        // System.out.println(n + " "+ min + " " + max);

        long sum = Long.MAX_VALUE;

        for (int i=min;i<=max;i++) {
            long temp = 0L;
            for (int e : a) {
                temp += diff(Math.abs((long) e - (long) i));
            }
            sum = Math.min(sum, temp);
        }
        System.out.println(sum);
    }

    private static long diff(long n) {
        return (n * (n + 1)) / 2;
    }
}

