import java.io.*;
import java.util.*;

public class Day4 {
	public static void main(String[] args) throws IOException {
		f1("Day4-small.txt");
        f1("Day4.txt");
        f2("Day4-small.txt");
        f2("Day4.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] str = br.readLine().split(",");
        ArrayList<Integer> list = new ArrayList<>();

        for (String s : str) {
            list.add(Integer.parseInt(s));
        }
        int size = list.size();

        ArrayList<Pair[][]> boards = new ArrayList<>();

        while (br.readLine() != null) {
            Pair[][] board = new Pair[5][5];
            for (int i=0;i<5;i++) {
                String[] s = br.readLine().trim().split("\\s+");
                for (int j=0;j<5;j++) {
                    board[i][j] = new Pair(Integer.parseInt(s[j]));
                }
            }
            boards.add(board);
        }

        int numBoards = boards.size();
        long prod = 0L;

        outer:
        for (int num : list) {
            for (Pair[][] board : boards) {
                set(board, num);
                long v1 = check1(board);
                long v2 = check2(board);
                if (v1 != -1) {
                    prod = ((long) num) * v1;
                    break outer;
                }
                if (v2 != -1) {
                    prod = ((long) num) * v2;
                    break outer;
                }
            }
        }

        System.out.println(prod);
    }

    private static void set(Pair[][] board, int num) {
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                if (board[i][j].val == num) {
                    board[i][j].mark = true;
                }
            }
        }
    }

    private static long check1(Pair[][] board) {
        boolean bingo = false;
        long sum = 0L;
        for (int i=0;i<5;i++) {
            int temp = 0;
            for (int j=0;j<5;j++) {
                if (board[i][j].mark) {
                    temp++;
                } else {
                    sum += (long) (board[i][j].val);
                }
            }
            if (temp == 5) {
                bingo = true;
            }
        }

        if (bingo) {
            return sum;
        } else {
            return -1L;
        }
    }

    private static long check2(Pair[][] board) {
        boolean bingo = false;
        long sum = 0L;
        for (int i=0;i<5;i++) {
            int temp = 0;
            for (int j=0;j<5;j++) {
                if (board[j][i].mark) {
                    temp++;
                } else {
                    sum += (long) (board[j][i].val);
                }
            }
            if (temp == 5) {
                bingo = true;
            }
        }

        if (bingo) {
            return sum;
        } else {
            return -1L;
        }
    }

    private static void printBoard(Pair[][] board) {
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                System.out.print(board[i][j].val+" ");
            }
            System.out.println();
        }
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] str = br.readLine().split(",");
        ArrayList<Integer> list = new ArrayList<>();

        for (String s : str) {
            list.add(Integer.parseInt(s));
        }
        int size = list.size();

        ArrayList<Pair[][]> boards = new ArrayList<>();

        while (br.readLine() != null) {
            Pair[][] board = new Pair[5][5];
            for (int i=0;i<5;i++) {
                String[] s = br.readLine().trim().split("\\s+");
                for (int j=0;j<5;j++) {
                    board[i][j] = new Pair(Integer.parseInt(s[j]));
                }
            }
            boards.add(board);
        }

        int numBoards = boards.size();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<numBoards;i++) {
            set.add(i);
        }

        long prod = 0L;

        for (int num : list) {
            for (Iterator<Integer> itr = set.iterator(); itr.hasNext(); ) {
                int idx = itr.next();
                Pair[][] board = boards.get(idx);
                set(board, num);
                long v1 = check1(board);
                long v2 = check2(board);
                if (v1 != -1) {
                    itr.remove();
                    prod = ((long) num) * v1;
                } else if (v2 != -1) {
                    itr.remove();
                    prod = ((long) num) * v2;
                }
            }
        }

        System.out.println(prod);
    }
}

class Pair {
    int val;
    boolean mark;

    Pair(int val) {
        this.val = val;
        this.mark = false;
    }
}