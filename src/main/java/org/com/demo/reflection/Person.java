package org.com.demo.reflection;

import java.io.Serializable;

public class Person extends BasePerson implements Serializable, IPerson {

    /**
     * 
     */
    private static final long serialVersionUID = -2409971397629379849L;

    private String name;
    protected String age;
    public String phone;

    private Add add;

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", phone=" + phone + ", add=" + add + "]";
    }

    private int shot(String str1, String str2) {
        System.out.println("str1=" + str1 + " | str2=" + str2);
        return 0;
    }

    protected void shot1() {
        System.out.println("111");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String doSomething(String str) {
        System.err.println("do some thing ..." + str);
        return "";
    }

    public Add getAdd() {
        return add;
    }

    public void setAdd(Add add) {
        this.add = add;
    }

}
