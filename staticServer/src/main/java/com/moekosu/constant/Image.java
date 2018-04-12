package com.moekosu.constant;

import java.io.Serializable;

/**
 * @author chenxu
 * @date 2018/04
 */
public class Image implements Serializable {

    private static final long serialVersionUID = 1930995115634660267L;

    private String id;

    private String path;

    private String name;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
