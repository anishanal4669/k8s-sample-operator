package org.k8s.sample.cr;

public class CoffeeResourceSpec {

    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size.toString();
    }
}
