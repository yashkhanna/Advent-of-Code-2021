import java.io.*;
import java.util.*;

public class Day12 {

	public static void main(String[] args) throws IOException {
		f2("Day12-small.txt", 7);
        f2("Day12-small2.txt", 10);
        f2("Day12-medium.txt", 18);
        f2("Day12.txt", 25);
	}

    private static void f1(String fileName, int m) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, Integer> map = new HashMap<>();

        Set<Integer> small = new HashSet<>();
        Set<Integer> large = new HashSet<>();

        int n = 0;

        for (int i=0;i<m;i++) {
            String[] s = br.readLine().split("-");
            String u = s[0];
            String v = s[1];
            if (!map.containsKey(u)) {
                map.put(u, n++);
            }
            if (!map.containsKey(v)) {
                map.put(v, n++);
            }
            if (u.charAt(0) >= 'A' && u.charAt(0) <= 'Z') {
                large.add(map.get(u));
            } else {
                small.add(map.get(u));
            }

            if (v.charAt(0) >= 'A' && v.charAt(0) <= 'Z') {
                large.add(map.get(v));
            } else {
                small.add(map.get(v));
            }
        }

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i=0;i<n;i++) {
            graph[i] = new ArrayList<>();
        }

        br = new BufferedReader(new FileReader(file));

        for (int i=0;i<m;i++) {
            String[] s = br.readLine().split("-");
            String u = s[0];
            String v = s[1];
            graph[map.get(u)].add(map.get(v));
            graph[map.get(v)].add(map.get(u));
        }

        int source = map.get("start");
        int sink = map.get("end");

        long paths = dfs(source, source, sink, graph, small, large, new boolean[n]);

        System.out.println(paths);
    }

    private static long dfs(int curr, int source, int sink, ArrayList<Integer>[] graph, Set<Integer> small, Set<Integer> large, boolean[] visited) {
        long ans = 0L;

        for (int child : graph[curr]) {
            if (child == source) {
                continue;
            }
            if (child == sink) {
                ans++;
                continue;
            }
            if (small.contains(child) && !visited[child]) {
                visited[child] = true;
                ans += dfs(child, source, sink, graph, small, large, visited);
                visited[child] = false;
                continue;
            }
            if (large.contains(child)) {
                ans += dfs(child, source, sink, graph, small, large, visited);
                continue;       
            }
        }
        return ans;
    }

    private static void f2(String fileName, int m) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, Integer> map = new HashMap<>();

        Set<Integer> small = new HashSet<>();
        Set<Integer> large = new HashSet<>();

        int n = 0;

        for (int i=0;i<m;i++) {
            String[] s = br.readLine().split("-");
            String u = s[0];
            String v = s[1];
            if (!map.containsKey(u)) {
                map.put(u, n++);
            }
            if (!map.containsKey(v)) {
                map.put(v, n++);
            }
            if (u.charAt(0) >= 'A' && u.charAt(0) <= 'Z') {
                large.add(map.get(u));
            } else {
                small.add(map.get(u));
            }

            if (v.charAt(0) >= 'A' && v.charAt(0) <= 'Z') {
                large.add(map.get(v));
            } else {
                small.add(map.get(v));
            }
        }

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i=0;i<n;i++) {
            graph[i] = new ArrayList<>();
        }

        br = new BufferedReader(new FileReader(file));

        for (int i=0;i<m;i++) {
            String[] s = br.readLine().split("-");
            String u = s[0];
            String v = s[1];
            graph[map.get(u)].add(map.get(v));
            graph[map.get(v)].add(map.get(u));
        }

        /*
        for (int s : small) {
            System.out.print(s+", ");
        }

        System.out.println();

        for (int s : large) {
            System.out.print(s+", ");
        }

        System.out.println();

        for (String s : map.keySet()) {
            System.out.println(s + " "+ map.get(s));
        }

        for (int i=0;i<n;i++) {
            System.out.print(i + " : ");
            for (int child : graph[i]) {
                System.out.print(child+", ");
            }
            System.out.println();
        }
        */

        int source = map.get("start");
        int sink = map.get("end");

        long paths = dfs2(source, source, sink, graph, small, large, new int[n], false);

        System.out.println(paths);
    }

    private static long dfs2(int curr, int source, int sink, ArrayList<Integer>[] graph, Set<Integer> small, Set<Integer> large, int[] visited, boolean flag) {
        long ans = 0L;

        for (int child : graph[curr]) {
            if (child == source) {
                continue;
            }
            if (child == sink) {
                ans++;
                continue;
            }
            if (small.contains(child) && (visited[child] == 0 || (visited[child] == 1 && !flag))) {
                visited[child]++;
                if (visited[child] == 2) {
                    flag = true;
                }
                ans += dfs2(child, source, sink, graph, small, large, visited, flag);
                visited[child]--;
                if (visited[child] == 1) {
                    flag = false;
                }
                continue;
            }
            if (large.contains(child)) {
                ans += dfs2(child, source, sink, graph, small, large, visited, flag);
                continue;       
            }
        }
        return ans;
    }
}
