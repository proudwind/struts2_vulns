package cn.seaii.s2012.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private String name;

    public LoginAction() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String execute() throws Exception {
        return !this.name.isEmpty() ? "redirect" : "success";
    }
}