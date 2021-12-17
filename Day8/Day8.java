import java.io.*;
import java.util.*;

public class Day8 {
	public static void main(String[] args) throws IOException {
		// f1("Day8-small.txt", 1);
  //       f1("Day8-med.txt", 10);
  //       f1("Day8.txt", 200);

        f2("Day8-small.txt", 1);
        f2("Day8-med.txt", 10);
        f2("Day8.txt", 200);
	}

    private static void f1(String fileName, int rows) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        long ans = 0L;

        for (int line = 0; line < rows; line++) {
            String[] s = br.readLine().split(" | ");
            String[] base = new String[10];
            String[] test = new String[4];
            
            for (int i=0;i<10;i++) {
                base[i] = s[i];
            }

            for (int i=0;i<4;i++) {
                test[i] = s[i+11];
            }

            for (int i=0;i<4;i++) {
                int len = test[i].length();

                if (len == 2 || len == 3 || len == 4 || len == 7) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    private static void f2(String fileName, int rows) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        long ans = 0L;

        for (int line = 0; line < rows; line++) {
            String[] s = br.readLine().split(" | ");
            String[] test = new String[4];

            Map<Integer, ArrayList<String>> map = new HashMap<>();
            
            for (int i=0;i<10;i++) {
                char[] base = s[i].toCharArray();
                Arrays.sort(base);
                String baseString = new String(base);
                if (!map.containsKey(baseString.length())) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(baseString);
                    map.put(baseString.length(), list);
                } else {
                    ArrayList<String> list = map.get(baseString.length());
                    list.add(baseString);
                }
            }

            String[] mapper = new String[10];
            mapper[1] = map.get(2).get(0);
            mapper[4] = map.get(4).get(0);
            mapper[7] = map.get(3).get(0);
            mapper[8] = map.get(7).get(0);

            ArrayList<Character> temp = new ArrayList<>();
            for (int i=0;i<2;i++) {
                for (int j=0;j<3;j++) {
                    if (mapper[1].charAt(i) == mapper[7].charAt(j)) {
                        temp.add(mapper[1].charAt(i));
                    }
                }
            }

            for (String ss : map.get(5)) {
                int ct = 0;
                for (int i=0;i<ss.length(); i++) {
                    for (Character c : temp) {
                        if (c == ss.charAt(i)) {
                            ct++;
                        }
                    }
                }
                if (ct == 2) {
                    mapper[3] = ss;
                }
            }

            for (String ss : map.get(6)) {
                int ct = 0;
                for (int i=0;i<ss.length(); i++) {
                    for (Character c : temp) {
                        if (c == ss.charAt(i)) {
                            ct++;
                        }
                    }
                }
                if (ct == 1) {
                    mapper[6] = ss;
                }
            }

            for (String ss : map.get(5)) {
                int ct = 0;
                for (int i=0;i<ss.length(); i++) {
                    for (Character c : mapper[6].toCharArray()) {
                        if (c == ss.charAt(i)) {
                            ct++;
                        }
                    }
                }
                if (ct == 5) {
                    mapper[5] = ss;
                }
            }

            for (String ss : map.get(5)) {
                if (ss.equals(mapper[3])) {
                    continue;
                }
                if (ss.equals(mapper[5])) {
                    continue;
                }
                mapper[2] = ss;
            }

            for (String ss : map.get(6)) {
                int ct = 0;
                for (int i=0;i<ss.length(); i++) {
                    for (Character c : mapper[3].toCharArray()) {
                        if (c == ss.charAt(i)) {
                            ct++;
                        }
                    }
                }
                if (ct == 5) {
                    mapper[9] = ss;
                }
            }

            for (String ss : map.get(6)) {
                if (ss.equals(mapper[9])) {
                    continue;
                }
                if (ss.equals(mapper[6])) {
                    continue;
                }
                mapper[0] = ss;
            }

            // for (int i=0;i<10;i++) {
            //     System.out.println(i +" "+mapper[i]);
            // }

            long val = 0;

            for (int i=0;i<4;i++) {
                char[] op = s[i+11].toCharArray();
                Arrays.sort(op);
                test[i] = new String(op);
                int curr = 0;
                for (int j=0;j<10;j++) {
                    if (test[i].equals(mapper[j])) {
                        curr = j;
                        break;
                    }
                }
                val = val * 10 + curr;
            }
            // System.out.println(val);
            ans += val;
        }
        System.out.println(ans);
    }
}
