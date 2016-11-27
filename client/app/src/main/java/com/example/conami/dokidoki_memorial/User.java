package com.example.conami.dokidoki_memorial;

/**
 * Created by matsushita on 2016/11/27.
 */

public final class User {
    private static int id = 1;
    private static String name = "user1";
    private static int coupleId = 1;
    private static int idOfPartnar = 3;

    private User(){
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCoupleId(int coupleId) {
        this.coupleId = coupleId;
    }

    private void setIdOfPartnar(int idOfPartnar){
        this.idOfPartnar = idOfPartnar;
    }


    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static int getCoupleId() {
        return coupleId;
    }

    public static int getIdOfPartnar() {
        return idOfPartnar;
    }
}
