package com.company.javabussimulator;

import com.company.javabussimulator.db.Factory;
import com.company.javabussimulator.entities.Bus;
import com.company.javabussimulator.entities.Line;
import com.company.javabussimulator.entities.Person;
import com.company.javabussimulator.entities.Stop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBFiller {
    public static void dBFiller() throws SQLException {
        Long rnd = (long) (Math.random() * 100);
        Long rnd2 = (long) (Math.random() * 20);
        Random random = new Random();
        long x, y;
        int y1;
        List<Stop> stops = Factory.getInstance().getStopDAO().getAllStops();
        for (int i = 0; i < rnd; i++) {
            x = (long) (random.nextInt(stops.size()) + 1);
            y = (long) (random.nextInt(stops.size()) + 1);
            if (x != y) {
                Factory.getInstance().getPersonDAO().addPerson(
                        new Person(Factory.getInstance().getStopDAO().getStopById(x),
                                Factory.getInstance().getStopDAO().getStopById(y)));
            }
        }
        List<Line> lines = Factory.getInstance().getLineDAO().getAllLines();
        for (int i = 0; i < rnd2; i++) {
            x = (long) (random.nextInt(2) + 1);
            y1 = random.nextInt(6) + 15;
            Factory.getInstance().getBusDAO().addBus(new Bus(y1, Factory.getInstance().getLineDAO().getLineById(x)));
        }
    }
}
