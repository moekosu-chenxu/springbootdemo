package com.moekosu.constant;

import java.io.Serializable;

/**
 * @author chenxu
 * @date 2018/05
 */
public class DouyuBarrage implements Serializable {

    private static final long serialVersionUID = 8807195968119895782L;

    private String id;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
