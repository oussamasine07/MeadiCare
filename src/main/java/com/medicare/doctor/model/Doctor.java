package com.medicare.doctor.model;

public class Doctor {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;

    public Doctor () {}

    public Doctor ( int id, String name, String username, String email, String phone ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Doctor ( String name, String username, String email, String phone ) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Doctor ( String name, String phone ) {
        this.name = name;
        this.phone = phone;
    }

    public int getId () { return this.id; }

    public String getName () { return this.name; }
    public void setName( String name ) {
        this.name = name;
    }

    public String getUsername () { return this.username; }
    public void setUsername ( String username ) {
        this.username = username;
    }

    public String getEmail () { return  this.email; }
    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getPhone () { return  this.phone; }
    public void setPhone ( String phone ) {
        this.phone = phone;
    }

}
