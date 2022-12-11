package com.djlaser.adventofcode.util;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AOCYear {
    public int year;
    public AOCYear(int year) {
        this.year = year;
    }

    public List<AOCDay> getDays() {
        List<AOCDay> days = new ArrayList<>();

        try (ScanResult scanResult = new ClassGraph()
                .acceptPackages("com.djlaser.adventofcode.y" + year)
                .scan()) {

            List<Class<AOCDay>> classes = scanResult
                    .getSubclasses(AOCDay.class.getName())
                    .loadClasses(AOCDay.class);

            for (Class<AOCDay> clazz : classes) {
                days.add(clazz.getDeclaredConstructor().newInstance());
            }

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return days;
    }

    public AOCDay getDay(int day) throws IllegalArgumentException{
        List<AOCDay> days = getDays();

        if(day < 1){
            throw new IllegalArgumentException("Day should not be less than one");
        } else if(day > days.size()){
            throw new IllegalArgumentException("Day is bigger than number of days(" + days.size() + ")");
        }

        for (AOCDay aocDay : days) {
            String name = aocDay.getClass().getSimpleName();
            int dayNum = Integer.parseInt(name.split("D")[1]);
            if (day == dayNum) {
                return aocDay;
            }
        }

        throw new IllegalArgumentException("No such day: D" + day);
    }

    public void runDay(int day, int challenge){
        getDay(day).runChallenge(challenge);
    }
}
