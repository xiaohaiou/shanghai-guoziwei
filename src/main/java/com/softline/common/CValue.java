package com.softline.common;

import java.io.Serializable;

public class CValue implements Serializable {
    /**
     * 版本序列化.
     */
    private static final long serialVersionUID = -2274443157610433076L;

    private Integer id;

    private String key;

    private Object value;

    public CValue() {
    }

    public CValue(Integer id, String key, Object value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CValue{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
