package cn.seaii.s2053.action;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
    public String name = "VulApps";

    public IndexAction() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String execute() {
        return "SUCCESS";
    }
}
