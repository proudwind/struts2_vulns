package cn.seaii.s2057.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
    public String name = "VulApps";

    public TestAction() {
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
