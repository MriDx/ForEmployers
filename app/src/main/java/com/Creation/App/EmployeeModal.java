package com.Creation.App;

public class EmployeeModal {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private EmployeeModal() {}

    private EmployeeModal(String name) {
        this.name = name;
    }
}
