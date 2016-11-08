package base;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by akui on 16/11/8.
 */
public class Streams {

    @Test
    public void generate() {
        Stream.generate(() -> "hello world")
                .limit(3)
                .forEach(System.out::println);
    }

    @Test
    public void iterate() {
        Stream.iterate(0, x -> x + 1)
                .limit(5)
                .forEach(System.out::println);
    }
}
