package org.com.demo.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beff", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {

        /**
         * filter
         */
        List<Dish> list = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.err.println(list);

        /**
         * map：映射
         */
        List<String> list2 = menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.err.println(list2);

        List<Integer> list3 = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.err.println(list3);

        /**
         * map & flatmap:
         */
        String[] arrayOfWords = { "Goodbye", "World" };
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);// 接收一个数组并产生一个流
        streamOfWords.map(word -> word.split("")).map(Arrays::stream);// 1、将每个单词转换为由其字母构成的数组
                                                                      // 2、让每个数组变成一个单独的流
        /**
         * 使用flatmap方法的效果是。各个数组并不是分别映射成一个流，而是映射成流的内容
         * 所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流
         */
        streamOfWords.map(word -> word.split("")).flatMap(Arrays::stream);// 1、将每个单词转换为由其字母构成的数组
                                                                          // 2、将各个生成流扁平化为单个流
        /**
         * 给定［1，2，3，4，5］返回［1，4，9，16，25］
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(n -> n * n).collect(Collectors.toList());

        /**
         * 给定［1，2，3］和［3，4］返回[(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
         * flatMap:让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
         */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j })).collect(Collectors.toList());

        /**
         * 给定［1，2，3］和［3，4］返回和能被3整除的数对[(2,4),(3,3)]
         */
        numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j }))
                .collect(Collectors.toList());

        /**
         * 匹配一个
         */
        menu.stream().anyMatch(Dish::isVegetarian);

        /**
         * 匹配所有
         */
        menu.stream().allMatch(d -> d.getCalories() < 1000);

        /**
         * 没有匹配
         */
        menu.stream().noneMatch(d -> d.getCalories() >= 1000);

        /**
         * 查找元素 findAny：返回当前流的任意元素
         */
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.err.println(d.getName()));
        
        /**
         * 查找第一个平方数能被3整除的
         */
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5,6);
        someNumbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst().ifPresent(n -> System.err.println(n));

        /**
         * 归约 reduce
         */
        someNumbers.stream().reduce(0, (a, b) -> a + b);// 求和
        someNumbers.stream().reduce(0, Integer::sum);// 使用方法引用实现
        someNumbers.stream().reduce(Integer::sum);// 无初始值，返回一个Optional
        someNumbers.stream().reduce(Integer::max);// 最大值

        someNumbers.stream().count();// 获取元素个数

        /**
         * 原始类型流特化
         */
        someNumbers.stream().mapToInt(n -> n).sum();

        /**
         * 转换回对象流
         */
        menu.stream().mapToInt(Dish::getCalories).boxed();

        /**
         * 默认值OptionalInt
         */
        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();
        optionalInt.orElse(1);// 如果不存在最大值，设置显示默认值为1

        /**
         * 收集器:查找大小
         */
        menu.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories))).ifPresent(System.out::print);

        /**
         * 求和
         */
        menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        /**
         * 平均数
         */
        menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        /**
         * 汇总:如sum averag max min
         */
        menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        /**
         * 连接字符串
         */
        menu.stream().map(Dish::getName).collect(Collectors.joining(","));

        /**
         * 广义的归约汇总
         */
        menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (c1, c2) -> c1 + c2));
        menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        /**
         * 分组
         */
        Map<Dish.Type, List<Dish>> menuMap = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        /**
         * 多级分组
         */
        Map<Dish.Type, Map<String, List<Dish>>> mulMap = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(Dish::getName)));

        /**
         * 按子组收集数据
         */
        menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

    }

}
