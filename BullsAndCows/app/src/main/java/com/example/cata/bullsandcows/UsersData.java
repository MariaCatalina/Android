package com.example.cata.bullsandcows;

import com.example.cata.bullsandcows.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Cata on 11/16/2016.
 */

public class UsersData {
    private List<User> usersLIist;

    public static UsersData winnersList = null;

    private UsersData(){
        usersLIist =  new ArrayList<>();
    }

    public static UsersData getInstance(){
        if (winnersList == null)
            winnersList = new UsersData();

        return winnersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersData usersData = (UsersData) o;

        return usersLIist.equals(usersData.usersLIist);

    }

    public boolean contains(String userName){
        for (User user: usersLIist)
            if(user.getUserName().toLowerCase().equals(userName.toLowerCase()))
                return true;

        return false;
    }

    public void addUser(User user){
        usersLIist.add(user);
    }

    public List<User> getUsers() {
        return usersLIist;
    }

    public void addScore(String userName, int counterRounds) {
        for( User user: usersLIist)
            if(user.getUserName().toLowerCase().equals(userName.toLowerCase()))
                user.setScore(counterRounds);
    }

    public String getUserDataFile() {
        String data = new String();
        for(User user:usersLIist)
            data += user.getUserName() + ":" + user.getScore() + "-";
        return data;
    }
}
