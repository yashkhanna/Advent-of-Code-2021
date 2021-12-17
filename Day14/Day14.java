import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Day14 {

	public static void main(String[] args) throws IOException {
		// f1("Day14-small.txt");
  //       f1("Day14.txt");

        f2("Day14-small.txt");
        f2("Day14.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Character> inp = new ArrayList<>();
        char[] temp = br.readLine().toCharArray();
        for (char c : temp) {
            inp.add(c);
        }

        br.readLine();

        Map<String, Character> map = new HashMap<>();

        while (true) {
            String s = br.readLine();
            if (s == null || s.equals("")) {
                break;
            }
            String[] ss = s.split(" -> ");
            map.put(ss[0], ss[1].charAt(0));
        }

        int steps = 10;

        // ArrayList<Character> next = new ArrayList<>();
        // next.addAll(inp);

        long[] count = new long[26];
        for (char c : inp) {
            count[c - 'A']++;
        }

        for (int i=0;i<steps;i++) {
            // System.out.println(i);  
            int idx = 0;
            while (idx + 1 < inp.size()) {
                String ss = inp.get(idx) + "" + inp.get(idx + 1);
                // System.out.print(ss);
                if (map.containsKey(ss)) {
                    // System.out.print(" , "+map.get(ss));
                    inp.add(idx + 1, map.get(ss));
                    count[map.get(ss)-'A']++;
                    idx++;
                }
                idx++;
                // System.out.println();
            }
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i=0;i<26;i++) {
            if (count[i] > 0L) {
                min = Math.min(min, count[i]);
                max = Math.max(max, count[i]);
            }
        }
        // print(inp);
        System.out.println(min + " " + max + " " + (max - min));
    }

    private static void print(ArrayList<Character> list) {
        for (char c : list) {
            System.out.print(c);
        }
        System.out.println();
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int depth = 40;

        String inp = br.readLine();
        br.readLine();

        Map<String, Character> map = new HashMap<>();

        while (true) {
            String s = br.readLine();
            if (s == null || s.equals("")) {
                break;
            }
            String[] ss = s.split(" -> ");
            map.put(ss[0], ss[1].charAt(0));
        }

        Map<String, BigInteger[][]> map2 = new HashMap<>();

        for (String s : map.keySet()) {
            BigInteger[][] arr = new BigInteger[depth + 1][26];
            arr[0] = new BigInteger[26];
            for (int j=0;j<26;j++) {
                arr[0][j] = BigInteger.ZERO;
            }
            map2.put(s, arr);
        }

        for (int i = 1; i<=depth;i++) {
            for (String s : map.keySet()) {
                String s1 = s.charAt(0) + "" + map.get(s);
                String s2 = map.get(s) + "" + s.charAt(1);

                BigInteger[] one, two;

                if (map2.containsKey(s1)) {
                    one = map2.get(s1)[i - 1];
                } else {
                    one = new BigInteger[26];
                    for (int j=0;j<26;j++) {
                        one[j] = BigInteger.ZERO;
                    }
                }

                if (map2.containsKey(s2)) {
                    two = map2.get(s2)[i - 1];
                } else {
                    two = new BigInteger[26];
                    for (int j=0;j<26;j++) {
                        two[j] = BigInteger.ZERO;
                    }
                }

                BigInteger[] three = new BigInteger[26];

                for (int j=0;j<26;j++) {
                    three[j] = one[j].add(two[j]);
                }
                three[map.get(s) - 'A'] = three[map.get(s) - 'A'].add(BigInteger.ONE);
                
                map2.get(s)[i] = three;
            }
        }

        for (String s : map2.keySet()) {
            BigInteger[] last = map2.get(s)[depth];
            // last[s.charAt(0) - 'A'] = last[s.charAt(0) - 'A'].add(BigInteger.ONE);
            // last[s.charAt(1) - 'A'] = last[s.charAt(1) - 'A'].add(BigInteger.ONE);
        }

        // for (int i=0;i<26;i++) {
        //     System.out.print(map2.get("NN")[depth][i]+", ");
        // }

        // System.exit(0);


        BigInteger[] count = new BigInteger[26];
        for (int i = 0;i<26;i++) {
            count[i] = BigInteger.ZERO;
        }

        for (int i = 0; i<inp.length();i++) {
            count[inp.charAt(i) - 'A'] = count[inp.charAt(i) - 'A'].add(BigInteger.ONE);
        }

        for (int i=0; i + 1 < inp.length();i++) {
            String s = inp.charAt(i) + "" + inp.charAt(i + 1);

            BigInteger[] temp;

            if (map2.containsKey(s)) {
                temp = map2.get(s)[depth];
            } else {
                temp = new BigInteger[26];
                for (int j=0;j<26;j++) {
                    temp[j] = BigInteger.ZERO;
                }
                temp[s.charAt(0) - 'A'] = temp[s.charAt(0) - 'A'].add(BigInteger.ONE);
                temp[s.charAt(1) - 'A'] = temp[s.charAt(1) - 'A'].add(BigInteger.ONE);
            }

            for (int j=0;j<26;j++) {
                count[j] = count[j].add(temp[j]);
            }
        }

        BigInteger mini = null;
        BigInteger maxi = null;
        for (int i=1;i<26;i++) {
            if (count[i].compareTo(BigInteger.ZERO) == 1) {
                if (mini == null) {
                    mini = count[i];
                } else {
                    mini = mini.min(count[i]);
                }
                
                if (maxi == null) {
                    maxi = count[i];
                } else {
                    maxi = maxi.max(count[i]);
                }
            }
        }
        // print(inp);
        System.out.println(mini.toString() + " " + maxi.toString() + " " + (maxi.subtract(mini)).toString());
    }
}