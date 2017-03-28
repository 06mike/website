package org.com.demo.reflection;

import java.io.Serializable;

public class Add implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7429240517876025379L;

    private String address;
    private String zoocode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZoocode() {
        return zoocode;
    }

    public void setZoocode(String zoocode) {
        this.zoocode = zoocode;
    }


}
