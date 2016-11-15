package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by akui on 16/11/9.
 */
public class Maps {

    @Test
    public void flatMap() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream.forEach(e -> System.out.println(e));
        //
        Stream<Integer> outputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        ).flatMap((childList) -> childList.stream());
        outputStream.forEach(e -> System.out.println(e));
    }
}
