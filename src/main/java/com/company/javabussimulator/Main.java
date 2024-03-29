package com.company.javabussimulator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.company.javabussimulator.db.Factory;
import com.company.javabussimulator.entities.Line;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Main {
    public static void main(String[] args) throws SQLException, SAXException, IOException, ParserConfigurationException {
        LinesDOM.dBWriter();
        DBFiller.dBFiller();
        List<Line> lines = Factory.getInstance().getLineDAO().getAllLines();
        /*System.out.println("Все маршруты:");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Id: " + lines.get(i).getId() + ", cost: " + lines.get(i).getCost() +
                    ", number: " + lines.get(i).getNumber());
            System.out.println("Stops: ");
            List<Stop> stops = lines.get(i).getStops();
            for (Stop k : stops) {
                System.out.println(k.getName());
            }
        }*/
        /*for(int x; x<2)
        for (Line x : lines) {
            Thread thread1 = new Thread(x);
            thread1.start();
        }*/
        for (long x = 1; x < 3; x++) {
            Line line = Factory.getInstance().getLineDAO().getLineById(x);
            Thread thread1 = new Thread(line);
            thread1.start();
        }
    }
}