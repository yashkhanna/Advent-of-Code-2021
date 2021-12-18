import java.io.*;
import java.util.*;

public class Day16 {

	public static void main(String[] args) throws IOException {
		// f1("Day16-small.txt");
        f2("Day16.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        char[] inp = hexToDecimal(br.readLine());
        int n = inp.length;

        System.out.println(parse(inp, n, 0, -1, -1));
    }

    private static int parse(char[] inp, int n, int idx, int packets, int bits) {
        if (check(inp, idx, n - 1)) {
            return 0;
        }

        int ct = 0;
        
        int version = binaryToDecimal(inp, idx, idx + 2);
        int ans = version;
        ct += 3;
        idx += 3;
        
        int id = binaryToDecimal(inp, idx, idx + 2);
        ct += 3;
        idx += 3;

        if (id == 4) {
            boolean flag = false;
            while (!flag) {
                char c = inp[idx];
                ct++;
                idx++;

                if (c == '1') {
                    // Do nothing.
                } else {
                    flag = true;
                }
                ct += 4;
                idx +=4;
            }
            if (packets > 0) {
                ans += parse(inp, n, idx, packets - 1, -1);
            } else if (bits > 0) {
                ans += parse(inp, n, idx, -1, bits - ct);
            } else {
                ans += parse(inp, n, idx, -1, -1);
            }
        } else {
            char lt_id = inp[idx];
            ct++;
            idx++;

            if (lt_id == '1') {
                int newPackets = binaryToDecimal(inp, idx, idx + 10);
                ct += 11;
                idx += 11;
                ans += parse(inp, n, idx, newPackets, -1);
            } else {
                int newBits = binaryToDecimal(inp, idx, idx + 14);
                ct += 15;
                idx += 15;
                ans += parse(inp, n, idx, -1, newBits);
            }
        }

        return ans;
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        char[] inp = hexToDecimal(br.readLine());
        int n = inp.length;

        System.out.println(parse2(inp, n, 0, 0)[3]);
    }

    private static long []parse2(char[] inp, int n, int idx, int depth) {
        // if (check(inp, idx, n - 1)) {
        //     return 0;
        // }

        int ct = 0;
        
        long version = binaryToDecimal2(inp, idx, idx + 2);
        ct += 3;
        idx += 3;
        
        long id = binaryToDecimal2(inp, idx, idx + 2);
        ct += 3;
        idx += 3;

        if (id == 4) {
            boolean flag = false;
            StringBuilder value = new StringBuilder();
            while (!flag) {
                char c = inp[idx];
                ct++;
                idx++;

                value.append(inp[idx]+"");
                value.append(inp[idx + 1]+"");
                value.append(inp[idx + 2]+"");
                value.append(inp[idx + 3]+"");

                if (c == '1') {
                    // Do nothing.
                } else {
                    flag = true;
                }
                ct += 4;
                idx +=4;
            }
            // for (int i=0;i<depth;i++) {
            //     System.out.print("\t");
            // }
            // System.out.println("Leaf: "+ depth + " " +binaryToDecimal2(value.toString()));
            return new long[]{ct, id, version, binaryToDecimal2(value.toString())};
            // if (packets > 0) {
            //     ans += parse(inp, n, idx, packets - 1, -1);
            // } else if (bits > 0) {
            //     ans += parse(inp, n, idx, -1, bits - ct);
            // } else {
            //     ans += parse(inp, n, idx, -1, -1);
            // }
        } else {
            char lt_id = inp[idx];
            ct++;
            idx++;

            long value;
            Long packet1 = null;
            Long packet2 = null;

            if (id == 0) {
                value = 0L;
            } else if (id == 1) {
                value = 1L;
            } else if (id == 2) {
                value = Long.MAX_VALUE;
            } else if (id == 3) {
                value = Long.MIN_VALUE;
            } else {
                value = 0L;
            }

            if (lt_id == '1') {
                long newPackets = binaryToDecimal2(inp, idx, idx + 10);
                ct += 11;
                idx += 11;

                while (newPackets > 0L) {
                    long[] temp = parse2(inp, n, idx, depth + 1);
                    idx += temp[0];
                    ct += temp[0];

                    if (id == 0) {
                        value += temp[3];
                    } else if (id == 1) {
                        value *= temp[3];
                    } else if (id == 2) {
                        value = Math.min(value, temp[3]);
                    } else if (id == 3) {
                        value = Math.max(value, temp[3]);
                    } else if (id == 5) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1 > packet2) ? 1L : 0L);
                        }
                    } else if (id == 6) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1 < packet2) ? 1L : 0L);
                        }
                    } else if (id == 7) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1.equals(packet2)) ? 1L : 0L);
                        }
                    }
                    newPackets--;
                }
                // for (int i=0;i<depth;i++) {
                //     System.out.print("\t");
                // }
                // System.out.println(depth + " " + id + " " + value);
                return new long[]{ct, id, version, value};
            } else {
                int newBits = binaryToDecimal(inp, idx, idx + 14);
                // System.out.println(newBits);
                ct += 15;
                idx += 15;

                while (newBits > 0) {
                    long[] temp = parse2(inp, n, idx, depth + 1);
                    idx += temp[0];
                    newBits -= temp[0];
                    ct += temp[0];

                    if (id == 0) {
                        value += temp[3];
                    } else if (id == 1) {
                        value *= temp[3];
                    } else if (id == 2) {
                        value = Math.min(value, temp[3]);
                    } else if (id == 3) {
                        value = Math.max(value, temp[3]);
                    } else if (id == 5) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1 > packet2) ? 1L : 0L);
                        }
                    } else if (id == 6) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1 < packet2) ? 1L : 0L);
                        }
                    } else if (id == 7) {
                        if (packet1 == null) {
                            packet1 = temp[3];
                        } else {
                            packet2 = temp[3];
                            value = ((packet1.equals(packet2)) ? 1L : 0L);
                        }
                    }
                }
                // for (int i=0;i<depth;i++) {
                //     System.out.print("\t");
                // }
                // System.out.println(depth + " " + id + " " + value);
                return new long[]{ct, id, version, value};
            }
        }
    }

    private static boolean check(char[] inp, int lo, int hi) {
        boolean flag = true;
        for (int i=lo;i<=hi;i++) {
            if (inp[i] == '1') {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static int binaryToDecimal(char[] inp, int lo, int hi) {
        int num = 0;
        for (int i=lo; i<=hi; i++) {
            num *= 2;
            if (inp[i] == '1') {
                num += 1;
            }
        }
        return num;
    }

    private static long binaryToDecimal2(char[] inp, int lo, int hi) {
        long num = 0L;
        for (int i=lo; i<=hi; i++) {
            num *= 2L;
            if (inp[i] == '1') {
                num += 1L;
            }
        }
        return num;
    }

    private static long binaryToDecimal2(String str) {
        char[] temp = str.toCharArray();
        return binaryToDecimal2(temp, 0, temp.length - 1);
    }

    private static char []hexToDecimal(String s) {
        Map<Character, String> map = new HashMap<>();
        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "0101");
        map.put('6', "0110");
        map.put('7', "0111");
        map.put('8', "1000");
        map.put('9', "1001");
        map.put('A', "1010");
        map.put('B', "1011");
        map.put('C', "1100");
        map.put('D', "1101");
        map.put('E', "1110");
        map.put('F', "1111");

        StringBuilder str = new StringBuilder();
        for (int i=0;i<s.length(); i++) {
            str.append(map.get(s.charAt(i)));
        }

        return str.toString().toCharArray();
    }

    private static void print(char[] inp, int lo, int hi) {
        for (int i=lo;i<=hi;i++) {
            System.out.print(inp[i]);
        }
        System.out.println();
    }
}