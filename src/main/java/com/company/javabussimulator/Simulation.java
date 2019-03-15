package com.company.javabussimulator;

import com.company.javabussimulator.db.Factory;
import com.company.javabussimulator.entities.Bus;
import com.company.javabussimulator.entities.Line;
import com.company.javabussimulator.entities.Stop;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Simulation {
    public int passengerDurationMin = 300;
    public int passengerDurationMax = 800;

    private static final Random globalRandom = new Random(System.currentTimeMillis());

    public synchronized int getRandomIntInRangeEnclosed(int min, int max) {
        return globalRandom.nextInt((max - min + 1) + min);
    }

    public static void main(String[] args) throws SQLException, SAXException, IOException, ParserConfigurationException {
        /*LinesDOM.dBWriter();
        DBFiller.dBFiller();*/
        List<Line> lines = Factory.getInstance().getLineDAO().getAllLines();
        List<Stop> stops = Factory.getInstance().getStopDAO().getAllStops();
        List<Bus> buses = Factory.getInstance().getBusDAO().getAllBuses();
        System.out.println(lines.size());
        for (Line x : lines) {
            Thread thread1 = new Thread(x);
            thread1.start();
        }
    }
}
