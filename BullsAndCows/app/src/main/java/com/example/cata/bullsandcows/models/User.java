package com.example.cata.bullsandcows.models;

/**
 * Created by Cata on 11/30/2016.
 */

public class User {
    private String userName;
    private int score;

    public User(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getLetter(){
        return String.valueOf(userName.charAt(0)).toUpperCase();
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", score=" + score +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
