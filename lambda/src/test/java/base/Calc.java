package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Created by akui on 16/11/8.
 */
public class Calc {

    @Test
    public void printTrackLengthStatistics() {
        IntSummaryStatistics trackLengthStats
                = Arrays.asList(new Album("A", 1), new Album("B", 23), new Album("C", 11), new Album("D", 765), new Album("E", 936)).stream()
                .mapToInt((Album album) -> album.getLen())
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }


    class Album {
        private String name;
        private int len;

        public Album(String name, int len) {
            this.name = name;
            this.len = len;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }
    }
}
