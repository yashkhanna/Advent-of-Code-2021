import java.io.*;
import java.util.*;

public class Day10 {
	public static void main(String[] args) throws IOException {
		// f1("Day10-small.txt", 10);
  //       f1("Day10.txt", 90);

        f2("Day10-small.txt", 10);
        f2("Day10.txt", 90);
	}

    private static void f1(String fileName, int r) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        long ans = 0L;

        for (int line = 0; line < r; line++) {
            String str = br.readLine();
            int len = str.length();
            Stack<Character> stack = new Stack<>();

            outer:
            for (int i=0;i < len; i++) {
                char c = str.charAt(i);

                if (c == '(' || c == '<' || c == '{' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        break outer;
                    } else if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        ans += 3L;
                        break outer;
                    }
                } else if (c == '>') {
                    if (stack.isEmpty()) {
                        break outer;
                    } else if (stack.peek() == '<') {
                        stack.pop();
                    } else {
                        ans += 25137L;
                        break outer;
                    }
                } else if (c == '}') {
                    if (stack.isEmpty()) {
                        break outer;
                    } else if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        ans += 1197L;
                        break outer;
                    }
                } else {
                    if (stack.isEmpty()) {
                        break outer;
                    } else if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        ans += 57L;
                        break outer;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void f2(String fileName, int r) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Long> scores = new ArrayList<>();

        for (int line = 0; line < r; line++) {
            String str = br.readLine();
            int len = str.length();
            Stack<Character> stack = new Stack<>();
            boolean flag = false;

            outer:
            for (int i=0;i < len; i++) {
                char c = str.charAt(i);

                if (c == '(' || c == '<' || c == '{' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        flag = true;
                        break outer;
                    }
                } else if (c == '>') {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else if (stack.peek() == '<') {
                        stack.pop();
                    } else {
                        flag = true;
                        break outer;
                    }
                } else if (c == '}') {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        flag = true;
                        break outer;
                    }
                } else {
                    if (stack.isEmpty()) {
                        // Do nothing
                    } else if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        flag = true;
                        break outer;
                    }
                }
            }

            long ans = 0L;

            if (!flag) {
                while (!stack.isEmpty()) {
                    Character curr = stack.pop();
                    ans *= 5L;
                    if (curr == '(') {
                        ans += 1L;
                    } else if (curr == '[') {
                        ans += 2L;
                    } else if (curr == '{') {
                        ans += 3L;
                    } else {
                        ans += 4L;
                    }
                }
                scores.add(ans);
            }
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size() / 2));
    }
}
