import java.io.*;
import java.util.*;

public class Day17 {

	public static void main(String[] args) throws IOException {
		f2("Day17.txt");
	}

    private static void f1(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] t1 = (br.readLine().split(": "))[1].split(", ");
        String[] xx = ((t1[0].split("="))[1]).split("\\.\\.");
        String[] yy = ((t1[1].split("="))[1]).split("\\.\\.");

        int minx = Integer.parseInt(xx[0]);
        int maxx = Integer.parseInt(xx[1]);
        int miny = Integer.parseInt(yy[0]);
        int maxy = Integer.parseInt(yy[1]);

        List<Integer> speedsx = new ArrayList<>();

        Map<Integer, Integer> stuck = new HashMap<>();
        Set<Integer> notstuckSteps = new HashSet<>();
        int minSteps = Integer.MAX_VALUE;

        for (int speedx = 0; speedx <= maxx; speedx++) {
            int pos = 0;
            int speed = speedx;
            int steps = 0;
            Set<Integer> stepsList = new HashSet<>();
            boolean flag = false;
            boolean shoots = false;
            do {
                pos += speed;
                speed--;
                steps++;
                if (pos >= minx && pos <= maxx) {
                    flag = true;
                    stepsList.add(steps);
                } else if (flag) {
                    shoots = true;
                    break;
                }
            } while (speed > 0);

            if (flag) {
                if (shoots) {
                    notstuckSteps.addAll(stepsList);
                } else {
                    minSteps = Math.min(minSteps, steps);
                    stuck.put(speedx, steps);
                }
                speedsx.add(speedx);
            }
        }

        /*
        for (int speed : speedsx) {
            if (stuck.containsKey(speed)) {
                System.out.println(speed + " : " + stuck.get(speed));
            } else {
                System.out.print(speed + " : ");
                for (int step : notstuck.get(speed)) {
                    System.out.print(step +" ");
                }
                System.out.println();
            }
        }

        for (int step : notstuckSteps) {
            System.out.println(step);
        }

        System.exit(0);
        */

        int ans = 0;

        for (int steps : notstuckSteps) {
            int speedy = 0;
            while (true) {
                // System.out.println(steps + " " + speedy);
                int y = 0;
                int tempMax = 0;
                int currspeedy = speedy;
                for (int i = 0; i<steps;i++) {
                    y += currspeedy;
                    tempMax = Math.max(y, tempMax);
                    currspeedy--;
                }

                // System.out.println(steps + " " + speedy + " " + y);

                if (y < miny) {
                    speedy++;
                } else if (y > maxy) {
                    break;
                } else {
                    ans = Math.max(tempMax, ans);
                    speedy++;
                }
            }
        }

        // System.out.println(ans);
        
        int initspeedy = 1;
        boolean terminate = false;
        while (!terminate) {
            int posy = 0;
            int steps = 0;
            boolean entered = false;
            int tempMax = 0;
            int currspeedy = initspeedy;

            while (posy >= miny) {
                posy += currspeedy;
                if (steps > 0 && posy == 0 && Math.abs(currspeedy) > Math.abs(miny)) {
                    terminate = true;
                }
                if (posy >= miny && posy <= maxy) {
                    entered = true;
                }
                tempMax = Math.max(posy, tempMax);
                currspeedy--;
                steps++;
            }
            if (entered) {
                if (steps >= minSteps) {
                    ans = Math.max(tempMax, ans);
                }   
            }
            initspeedy++;
        }
        // System.out.println(initspeedy);
        System.out.println(ans);
    }

    private static void f2(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] t1 = (br.readLine().split(": "))[1].split(", ");
        String[] xx = ((t1[0].split("="))[1]).split("\\.\\.");
        String[] yy = ((t1[1].split("="))[1]).split("\\.\\.");

        int minx = Integer.parseInt(xx[0]);
        int maxx = Integer.parseInt(xx[1]);
        int miny = Integer.parseInt(yy[0]);
        int maxy = Integer.parseInt(yy[1]);

        long ans = 0L;

        for (int speedx = 0; speedx <= maxx; speedx++) {
            for (int speedy = miny; speedy <= -miny; speedy++) {
                int x = 0;
                int y = 0;
                int isx = speedx;
                int isy = speedy;

                while (true) {
                    x += isx;
                    y += isy;

                    if (x >= minx && x <= maxx && y >= miny && y <= maxy) {
                        ans++;
                        break;
                    }
                    if (x > maxx || y < miny) {
                        break;
                    }
                    if (isx > 0) {
                        isx--;
                    }
                    isy--;
                }
            }
        }
        System.out.println(ans);
    }
}