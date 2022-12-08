package com.djlaser.adventofcode.util;

import java.util.List;

public abstract class AOCYear {

    public abstract List<AOCDay> getDays();
    public abstract int getYear();

    public AOCDay getDay(int day) throws IllegalArgumentException{
        List<AOCDay> days = getDays();
        if(day < 1){
            throw new IllegalArgumentException("Day should not be less than one");
        } else if(day > days.size()){
            throw new IllegalArgumentException("Day is bigger than number of days(" + days.size() + ")");
        }

        return days.get(day - 1);
    }

    public void runDay(int day, int challenge){
        getDay(day).runChallenge(challenge);
    }
}
