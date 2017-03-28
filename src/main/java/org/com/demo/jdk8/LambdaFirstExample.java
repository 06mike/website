package org.com.demo.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * 从java原始方法到Lambda表达式
 * 
 * @author binary
 *
 */
public class LambdaFirstExample {

    public static void main(String[] args) {

        List<Apple> list = new ArrayList<Apple>();

        /**
         * 使用匿名内部类实现
         */
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        /**
         * 使用Lambda表达式实现
         */
        list.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        /**
         * java编辑器可以根据lambda出现的上下文来推断lambda表达式的参数
         */
        list.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        
        /**
         * 使用方法引用
         */
        list.sort(Comparator.comparing(Apple::getWeight));

        /**
         * 倒序
         */
        list.sort(Comparator.comparing(Apple::getWeight).reversed());

        /**
         * 比较器链:当重量相同时，按照颜色排序
         */
        list.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));

        /**
         * 谓词复合:negate、and、or || Predicate?
         */

        /**
         * 函数复合：
         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);// g(f(x))
        System.err.println(h.apply(1));// ->4

        Function<Integer, Integer> o = f.compose(g);// f(g(x))
        System.err.println(o.apply(1));// ->3

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> spelling = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        System.err.println(spelling.apply("abc"));

    }

}
