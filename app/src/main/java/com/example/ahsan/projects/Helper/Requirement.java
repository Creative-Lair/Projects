package com.example.ahsan.projects.Helper;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class Requirement {

    private int id;
    private int user_id;
    private int project_id;
    private String note;

    public Requirement(int id, int user_id, int project_id, String note) {
        this.id = id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
