package com.example.conami.dokidoki_memorial;

/**
 * Created by matsushita on 2016/11/27.
 */

public class User {
    int id = 1;
    String name = "user1";
    int coupleId = 1;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoupleId(int coupleId) {
        this.coupleId = coupleId;
    }


    public int getCoupleId() {
        return coupleId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
