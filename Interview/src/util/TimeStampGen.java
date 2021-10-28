package util;

import java.util.*;

public class TimeStampGen {

    public static void main(String[] args) {
        Random rand = new Random();
        int n = 4, date = 27, mins, secs = 0;
        SortedSet<Integer> minSeen = new TreeSet<>();
        Set<Integer> secSeen = new HashSet<>();

        for (int i = 0; i < n; i++) {
            mins = rand.nextInt(60);
            while (minSeen.contains(mins)) {
                mins = rand.nextInt(60);
            }
            minSeen.add(mins);

            secs = rand.nextInt(61);
            while (secSeen.contains(secs)) {
                secs = rand.nextInt(61);
            }
            secSeen.add(secs);
        }

        Iterator<Integer> minIter = minSeen.iterator(), secIter = secSeen.iterator();
        while (minIter.hasNext()) {
            mins = minIter.next();
            secs = secIter.next();

            System.out.printf("GIT_AUTHOR_DATE='2021-10-%d 5:%02d:%02d -0400' GIT_COMMITTER_DATE='2021-10-%d " +
                "5:%02d:%02d -0400' git commit -m ''\n", date, mins, secs, date, mins, secs);
        }
    }
}
