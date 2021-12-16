import java.io.*;
import java.util.*;

class Day3 {
	public static void main(String[] args) throws IOException {
		// f1();
        f2();
	}

    private static void f1() throws IOException {
        File file = new File("Day3.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        int[] count = new int[12];
 
        for (int i=0;i<1000;i++) {
            String str = br.readLine();
            for (int j=0;j<12;j++) {
                if (str.charAt(j) == '1') {
                    count[j] += 1;
                }
            }
        }
        long x = 0;
        long y = 0;

        for (int i=0;i<12;i++) {
            x <<= 1;
            y <<= 1;
            if (count[i] > 500) {
                x = x | 1;
            } else {
                y = y | 1;
            }
        }

        System.out.println(x * y);
    }

    private static void f2() throws IOException {
        File file = new File("Day3.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String[] strings = new String[1000];
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
 
        for (int i=0;i<1000;i++) {
            strings[i] = br.readLine();
            set1.add(i);
            set2.add(i);
        }

        int bit1 = 0;

        while (set1.size() > 1) {
            int count = 0;
            for (Iterator<Integer> it = set1.iterator(); it.hasNext(); ) {
                int idx = it.next();
                if (strings[idx].charAt(bit1) == '1') {
                    count++;
                }
            }
            // System.out.println(set1.size() + " "+ count);

            int size = (set1.size() % 2 == 0) ? set1.size() : set1.size() + 1;
            if (count >= size / 2) {
                for (Iterator<Integer> it = set1.iterator(); it.hasNext(); ) {
                    int idx = it.next();
                    if (strings[idx].charAt(bit1) == '0') {
                        it.remove();
                    }       
                }    
            } else {
                for (Iterator<Integer> it = set1.iterator(); it.hasNext(); ) {
                    int idx = it.next();
                    if (strings[idx].charAt(bit1) == '1') {
                        it.remove();
                    }       
                }
            }
            bit1++;
        }

        long x = 0;

        for (int idx : set1) {
            String str = strings[idx];
            // System.out.println(str);
            for (int i=0;i<12;i++) {
                x <<= 1;
                if (str.charAt(i) == '1') {
                    x = x | 1;
                }
            }
        }

        System.out.println(x);

        int bit2 = 0;

        while (set2.size() > 1) {
            int count = 0;
            for (Iterator<Integer> it = set2.iterator(); it.hasNext(); ) {
                int idx = it.next();
                if (strings[idx].charAt(bit2) == '1') {
                    count++;
                }
            }

            int size = (set2.size() % 2 == 0) ? set2.size() : set2.size() + 1;
            if (count >= size / 2) {
                for (Iterator<Integer> it = set2.iterator(); it.hasNext(); ) {
                    int idx = it.next();
                    if (strings[idx].charAt(bit2) == '1') {
                        it.remove();
                    }       
                }    
            } else {
                for (Iterator<Integer> it = set2.iterator(); it.hasNext(); ) {
                    int idx = it.next();
                    if (strings[idx].charAt(bit2) == '0') {
                        it.remove();
                    }       
                }
            }
            bit2++;
        }

        // System.out.println(set1.size() + " "+ set2.size());

        long y = 0;

        for (int idx : set2) {
            String str = strings[idx];
            // System.out.println(str);
            for (int i=0;i<12;i++) {
                y <<= 1;
                if (str.charAt(i) == '1') {
                    y = y | 1;
                }
            }
        }

        System.out.println(y);

        // long x = 0;
        // long y = 0;

        // for (int i=0;i<12;i++) {
        //     // System.out.println(count[i]);
        //     x <<= 1;
        //     y <<= 1;
        //     if (count[i] > 500) {
        //         x = x | 1;
        //     } else {
        //         y = y | 1;
        //     }
        // }

        System.out.println(x * y);
    }
}