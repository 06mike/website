package org.com.demo.reflection;

public class BasePerson {

    public String basename;
    private Integer baseid;

    public void fun() {
        System.err.println("父类fun方法.....");
    }

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public Integer getBaseid() {
        return baseid;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

}
