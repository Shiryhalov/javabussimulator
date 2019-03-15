package com.company.javabussimulator;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.javabussimulator.db.Factory;
import com.company.javabussimulator.entities.Line;
import com.company.javabussimulator.entities.Stop;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class LinesDOM {
    public static void dBWriter() throws SQLException, SAXException, IOException, ParserConfigurationException {
        Stop stop;
        Line line = new Line();
        List<Stop> stops = new ArrayList<>();
        Document doc;
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(new File("src/main/resources/lines.xml"));
        Node rootNode = doc.getDocumentElement();
        for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
            Node lineData = rootNode.getChildNodes().item(i);
            if (lineData.getNodeName().equals("LineData")) {
                for (int j = 0; j < lineData.getAttributes().getLength(); j++) {
                    if (lineData.getAttributes().item(j).getNodeName().equals("Number")) {
                        line.setNumber(lineData.getAttributes().item(j).getNodeValue());
                    }
                    if (lineData.getAttributes().item(j).getNodeName().equals("Interval")) {
                        line.setInterval(Double.parseDouble(lineData.getAttributes().item(j).getNodeValue()));
                    }
                    if (lineData.getAttributes().item(j).getNodeName().equals("Cost")) {
                        line.setCost(Double.parseDouble(lineData.getAttributes().item(j).getNodeValue()));
                    }
                }
                Factory.getInstance().getLineDAO().addLine(line);
                for (int k = 0; k < lineData.getChildNodes().getLength(); k++) {
                    Node stopsData = lineData.getChildNodes().item(k);
                    if (stopsData.getNodeName().equals("StopsData")) {
                        for (int s = 0; s < stopsData.getChildNodes().getLength(); s++) {
                            Node currentNode = stopsData.getChildNodes().item(s);
                            if (currentNode.getNodeName().equals("StopData")) {
                                for (int j = 0; j < currentNode.getAttributes().getLength(); j++) {
                                    if (currentNode.getAttributes().item(j).getNodeName().equals("Name")) {
                                        stop = new Stop();
                                        stop.setName(currentNode.getAttributes().item(j).getNodeValue());
                                        stop.setLine(line);
                                        Factory.getInstance().getStopDAO().addStop(stop);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

