package org.com.demo.jdk8;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建流
 * 
 * @author binary
 *
 */
public class CreateStreamExample {

    public static void main(String[] args) {

        /**
         * 由值创建流
         */
        Stream<String> stream = Stream.of("Java 8", "Lambda", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        /**
         * 得到一个空流
         */
        Stream<String> emptyStream = Stream.empty();

        /**
         * 由数组创建流
         */
        int[] numbers = { 1, 2, 3, 4, 5 };
        IntStream intStream = Arrays.stream(numbers);

        /**
         * 由文件生成流
         */
        try (Stream<String> lines = Files.lines(Paths.get("/Users/binary/Downloads/test.txt"),
                Charset.defaultCharset())) {
            System.err.println(lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 由函数生成流：创建无限流 Stream.iterate 和 Stream.generate
         */


    }

}
