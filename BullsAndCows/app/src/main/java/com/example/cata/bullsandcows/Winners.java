package com.example.cata.bullsandcows;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Cata on 11/16/2016.
 */

public class Winners {
    private Hashtable<String, Integer> users;

    public static Winners winnersList = null;

    private Winners(){
        users =  new Hashtable<>();
    }

    public static Winners getInstance(){
        if (winnersList == null)
            winnersList = new Winners();

        return winnersList;
    }

    public void addUser(String userName, int score){
        users.put(userName, score);
    }

    public boolean contains(String userName){
        if (users.containsKey(userName))
            return true;

        return false;
    }

    public void addValue(String user, int value){
        users.put(user, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Winners winners = (Winners) o;

        return users.equals(winners.users);

    }

    @Override
    public int hashCode() {
        return users.hashCode();
    }

    public String[] generateString() {
        /* sort */
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(users.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<?, Integer>>() {

            @Override
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        String[] result = new String[users.size()];
        int index = 0;

        for (String key: users.keySet()){
            result[index] = key + "      " + users.get(key) + "\n";
            index ++;
        }
        return result;
    }
}
