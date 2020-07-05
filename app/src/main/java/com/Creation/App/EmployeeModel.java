package com.Creation.App;

public class EmployeeModel {
    private String Name;

    private EmployeeModel() {}

    private EmployeeModel(String Name) {
        this.Name = Name;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
