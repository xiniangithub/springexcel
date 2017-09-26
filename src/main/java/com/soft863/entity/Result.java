package com.soft863.entity;

import java.util.List;

/**
 * 结果集对象
 */
public class Result<T> {

    private boolean state;

    private String message;

    private T obj;

    private List<T> lsit;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getLsit() {
        return lsit;
    }

    public void setLsit(List<T> lsit) {
        this.lsit = lsit;
    }
}
