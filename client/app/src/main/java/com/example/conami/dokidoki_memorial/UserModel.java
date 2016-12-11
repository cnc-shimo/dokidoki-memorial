package com.example.conami.dokidoki_memorial;

public final class UserModel {
    private static int id = 1;
    private static String name = "user1";
    private static int coupleId = 1;
    private static int partnarId = 3;

    private UserModel() {}

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCoupleId(int coupleId) {
        this.coupleId = coupleId;
    }

    private void setPartnarId(int partnarId) {
        this.partnarId = partnarId;
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

    public static int getPartnarId() {
        return partnarId;
    }
}
