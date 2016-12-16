package org.com.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * jdk8 新特性lambda表达式
 * 
 * @author binary
 *
 */
public class LambdaExample {

    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        System.out.println("所有人员的姓名");
        javaProgrammers.forEach(item -> System.out.printf("%s %s;", item.getFirstName(), item.getLastName()));
        phpProgrammers.forEach(item -> System.out.printf("%s %s;", item.getFirstName(), item.getLastName()));

        System.out.println("\r\n月薪超过1400的员工");
        phpProgrammers.stream().filter(p -> p.getSalary() > 1400)
                .forEach(p -> System.out.printf("%s %s;   ", p.getFirstName(), p.getLastName()));

        Predicate<Person> ageFilter = p -> p.getAge() > 25;
        Predicate<Person> salaryFilter = p -> p.getSalary() > 1400;
        Predicate<Person> genderFilter = p -> "female".equals(p.getGender());

        System.out.println("\r\n下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream().filter(ageFilter).filter(salaryFilter).filter(genderFilter)
                .forEach(p -> System.out.printf("%s %s;   ", p.getFirstName(), p.getLastName()));

        System.out.println("\r\n最前面的3个 Java programmers:");
        javaProgrammers.stream().limit(3)
                .forEach(p -> System.out.printf("%s %s;  ", p.getFirstName(), p.getLastName()));

        System.out.println("\r\n最前面的3个女性 Java programmers:");
        javaProgrammers.stream().filter(genderFilter).limit(3)
                .forEach(p -> System.out.printf("%s %s;  ", p.getFirstName(), p.getLastName()));

        System.out.println("\r\n根据 name 排序,并显示前5个 Java programmers:");
        List<Person> list = javaProgrammers.stream().sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
                .limit(5).collect(Collectors.toList());
        list.forEach(p -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("\r\n工资最低的 Java programmer:");
        Person person = javaProgrammers.stream().min((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(),
                person.getSalary());

        System.out.println("\r\n工资最高的 Java programmer:");
        person = javaProgrammers.stream().max((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(),
                person.getSalary());

        System.out.println("\r\n将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers.stream().map(Person::getFirstName).collect(Collectors.joining(";"));
        System.out.println(phpDevelopers);

        System.out.println("\r\n将 Java programmers 的 first name 存放到 Set:");
        javaProgrammers.stream().map(Person::getFirstName).collect(Collectors.toSet());
    }

}
