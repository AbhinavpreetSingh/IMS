package org.example;

public class Manager extends Employee{
    private String department;
    // Constructor
    public Manager(String name, int id, String department) {
        super(name, id);
        this.department = department;
    }

    // Getter method for department
    public String getDepartment() {
        return department;
    }
}
