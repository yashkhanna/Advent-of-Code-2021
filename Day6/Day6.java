import java.io.*;
import java.util.*;

public class Day6 {
	public static void main(String[] args) throws IOException {
		// f1("Day6-small.txt", 80);
        // f1("Day6.txt", 80);
        f2("Day6-small.txt", 80);
        f2("Day6.txt", 256);
	}

    private static void f1(String fileName, int count) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] s = br.readLine().split(",");
        ArrayList<Integer> q = new ArrayList<>();
        for (String ss : s) {
            q.add(Integer.parseInt(ss));
        }

        for (int time = 0; time < count; time++) {
            System.out.println(time);
            for (ListIterator<Integer> itr = q.listIterator(); itr.hasNext();) {
                int curr = itr.next();
                if (curr > 0) {
                    itr.remove();
                    itr.add(curr - 1);
                } else {
                    itr.remove();
                    itr.add(6);
                    itr.add(8);
                }
            }
        }

        System.out.println(q.size());
    }

    private static void f2(String fileName, int count) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] s = br.readLine().split(",");
        long ans = (long) s.length;

        long[] arr = new long[count + 1];
        Arrays.fill(arr, 0L);

        for (String ss : s) {
            int curr = Integer.parseInt(ss);
            curr++;
            while (curr <= count) {
                arr[curr]++;
                curr += 7;
            }
        }

        while (sumArr(arr) > 0L) {
            // printArr(arr);
            ans += sumArr(arr);
            long[] arr2 = new long[count + 1];

            for (int i=0;i<=count;i++) {
                int pos = i + 9;
                long val = arr[i];

                while (val > 0 && pos <= count) {
                    arr2[pos] += val;
                    pos += 7;
                }
            }

            for (int i=0;i<=count;i++) {
                arr[i] = arr2[i];
            }
        }

        System.out.println(ans);

        // for (int i=1;i<=count;i++) {
        //     int j = i;
        //     while (arr[i] > 0L && j + 9 <= count) {
        //         arr[j + 9] += arr[i];
        //         j += 9;
        //     }
        // }

        // System.exit(0);

        // arr[0] = 5;

        // for (int i=1;i<=count;i++) {
        //     arr[i] += arr[i-1];
        // }

        // printArr(arr);
        // System.out.println(arr[count]); 
    }

    private static void printArr(long[] arr) {
        for (long e : arr) {
            System.out.print(e+" ");
        }
        System.out.println();
    }

    private static long sumArr(long[] arr) {
        long sum = 0L;
        for (long e : arr) {
            sum += e;
        }
        return sum;
    }    
}

