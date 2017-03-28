package org.com.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

@Deprecated
public class App {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        List<String> sublist = list.subList(3, list.size());
        System.err.println(sublist);
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase("a") || list.get(i).equalsIgnoreCase("e")) {
                list.remove(i);
                list.add("F");
            }
        }
        System.err.println(list);

        String[] array = new String[] { "a", "b", "c", "d", "e" };
        List<String> arrayList = Arrays.asList(array);

        // try {
        // operateObjectProperties();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // m(1L);
        // m(127);
        // m(128);
        // m(1000);

        HashSet<String> hs = new HashSet<String>();
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");
        hs.add("B1");
        hs.add("A1");
        hs.add("D1");
        hs.add("E1");
        hs.add("C1");
        hs.add("F1");
        System.out.println("HashSet 顺序:\n" + hs);

        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        lhs.add("B");
        lhs.add("A");
        lhs.add("D");
        lhs.add("E");
        lhs.add("C");
        lhs.add("F");
        lhs.add("B1");
        lhs.add("A1");
        lhs.add("D1");
        lhs.add("E1");
        lhs.add("C1");
        lhs.add("F1");
        System.out.println("LinkedHashSet 顺序:\n" + lhs);

        TreeSet<String> ts = new TreeSet<String>();
        ts.add("B");
        ts.add("A");
        ts.add("D");
        ts.add("E");
        ts.add("C");
        ts.add("F");
        ts.add("B1");
        ts.add("A1");
        ts.add("D1");
        ts.add("E1");
        ts.add("C1");
        ts.add("F1");
        System.out.println("TreeSet 顺序:\n" + ts);

    }


    public static void m(Integer m) {
        System.err.println("do integer...");
    }

    public static void m(Long m) {
        System.err.println("do long...");
    }

    /**
     * 
     * @throws ClassNotFoundException 
     * @MethodName: createClazz
     * @Description: 创建Class对象
     */
    public static void createClazz() throws ClassNotFoundException {
        Person person = new Person();
        // 通过getClass()
        Class<?> clazz = person.getClass();
        // 通过Class.forName
        Class<?> clazz2 = Class.forName("org.com.demo.reflection.Person");
        // 通过.class
        Class<?> clazz3 = Person.class;
    }

    /**
     * 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @MethodName: operateObjectProperties
     * @Description: 对对象属性操作
     */
    public static void operateObjectProperties()
 throws NoSuchFieldException, SecurityException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        Class<?> clazz = Person.class;

        /**
         * 对象属性
         */
        // getFields()只能访问类中声明为公有的字段,私有的字段它无法访问，能访问从其它类继承来的公有字段.
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            // 获取类属性的修饰符
            System.err.println(Modifier.toString(field.getModifiers()) + "||" + field.getName());
        }
        System.err.println("--------神奇的分割线--------");
        // getDeclaredFields()能访问类中所有的字段,与public,private,protect无关，不能访问从其它类继承来的字段
        Field[] fields2 = clazz.getDeclaredFields();
        for (Field field : fields2) {
            System.err.println(Modifier.toString(field.getModifiers()) + "||" + field.getName());
        }

        // getField只能获取类的public字段
        // getDeclaredField是可以获取一个类的所有字段
        Field field = clazz.getDeclaredField("phone");
        Object object = clazz.newInstance();
        field.setAccessible(true);// 打破了封装的特性,允许对私有属性直接进行操作
        field.set(object, "jack");
        System.err.println(field.get(object));

        /**
         * 获取类的方法
         */
        // getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.err.println(Modifier.toString(method.getModifiers()) + " | " + method.getName());
        }
        System.err.println("-----------------------");
        // getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。
        Method[] methods2 = clazz.getDeclaredMethods();
        for (Method method : methods2) {
            System.err.println(Modifier.toString(method.getModifiers()) + " | " + method.getName());
        }

        /**
         * 获取方法的参数和返回值的类型
         */
        Method method = clazz.getDeclaredMethod("shot", String.class, String.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.err.println(parameter.getType());
        }
        Class<?>[] classes = method.getParameterTypes();
        for (Class<?> class1 : classes) {
            System.err.println(class1.getName());
        }
        System.err.println(method.getReturnType());
        System.err.println(method.isAccessible());
        System.err.println(Modifier.toString(method.getModifiers()));
        method.setAccessible(true);
        method.invoke(object, "abc", "123");

    }

}
