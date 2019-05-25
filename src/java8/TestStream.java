package java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class TestStream {


    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Stream.of(1, 3 , 5).forEach(e -> sb.append(e));
        System.out.println(sb.toString());

        Stream.of(1, 3, 5).filter( e  -> e % 2 == 0).forEach(System.out::print);

//        最后返回的是 List<String[]>
        System.out.println(Stream.of("hello" , "world").map(e -> e.split("")).collect(toList()));

//       flatMap中将两个流合并为一个
        System.out.println(Stream.of("hello" , "world").map(e -> e.split("")).flatMap(Arrays::stream).collect(toList()));

//     reduce 进行规约，0为初始值，每次都用上一次得到的结果作为初始值，进行相同的操作
        System.out.println(Stream.of(1, 2, 3, 4).reduce(0, (e1, e2) -> e1 + e2));

//        没有初值时就有可能没有值返回，所以返回的是optionnal
        System.out.println(Stream.of(1, 2, 3, 4).reduce( (e1, e2) -> e1 + e2).orElse(0));

        System.out.println(Stream.of(1,2,3,4).reduce(Math::max).orElse(-1));
        List<Integer> list = new ArrayList<>();
        System.out.println(list.stream().reduce(Math::max).orElse(-1));

//        从数组中构建流
        int[] nums = new int[]{1, 3, 5, 7, 9};
        System.out.println(Arrays.stream(nums).reduce(0, (e1, e2) -> e1 + e2));

        try {
//        从文件中生成流, Files是NIO中的内容
            Files.lines(Paths.get("src/resource/test_file.c")).map(line -> line.split("")).flatMap(Arrays::stream).forEach(e -> System.out.print(e +","));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
//        迭代生成
        Stream.iterate(0, n -> n + 2).limit(10).forEach(e -> System.out.print(e + " "));

        System.out.println();

//        斐波那契数列生成
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.print(t[0]+ " "));

        System.out.println();
    }

}
