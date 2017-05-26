package com.example.ahsan.projects.Helper;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class Projects {

    private String name;
    private int admin_id;
    private int id;

    public Projects(String name, int admin_id, int id) {
        this.name = name;
        this.admin_id = admin_id;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }
}
