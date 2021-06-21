package com.duynt.projectsecurity.model;

public class Home {
    private Integer id;
    private String name;
    private String decription;

    public Home(Integer id, String name, String decription) {
        this.id = id;
        this.name = name;
        this.decription = decription;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
