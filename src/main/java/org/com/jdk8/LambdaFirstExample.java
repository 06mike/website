package org.com.jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    }

}
