package base;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by akui on 16/11/8.
 */
public class Predicates {

    @Test
    public void combo() {
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        List<String> list = Arrays.asList("Json", "JJJJ", "ABCD", "JKLMN", "Q").stream()
                .filter(startsWithJ.and(fourLetterLong)).collect(Collectors.toList());
        System.out.println(list);


    }

}
