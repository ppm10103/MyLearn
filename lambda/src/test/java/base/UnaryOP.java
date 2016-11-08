package base;

import org.junit.Test;

import java.util.*;

/**
 * Created by akui on 16/11/6.
 */
public class UnaryOP {

    @Test
    public void listSum() {
        int sum = Arrays.asList("1", "2", "3", "4", "5").stream().mapToInt(Integer::valueOf).sum();
        System.out.println(sum);
    }

    @Test
    public void replaceAll() {
        List<String> res = Arrays.asList("1", "2", "3", "4", "5");
        res.replaceAll(e -> e + "s");
        System.out.println(res);
    }


    @Test
    public void func() {
        Arrays.asList(1, 3, 2, 5, 4).forEach(System.out::println);
        //
        Map<String, String> map = new TreeMap();
        map.put("alpha", "X");
        map.put("bravo", "Y");
        map.put("charlie", "Z");
        String str = "alpha-bravo-charlie";
        //str中对应于map的key部分替换为value部分
        map.replaceAll(str::replace);
        System.out.println(map);
        //key和value作为两个参数传入处理,然后作为新value替换旧value
        map.replaceAll(String::concat);
        System.out.println(map);
        //

    }

    public static void main(String[] args) {

    }
}
