package cn.seaii.s2029.action;

import com.opensymphony.xwork2.ActionSupport;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
    private String message = null;

    public IndexAction() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}