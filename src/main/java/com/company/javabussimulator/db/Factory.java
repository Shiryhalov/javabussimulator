package com.company.javabussimulator.db;

public class Factory {

    private static StudentDAO studentDAO = null;
    private static PersonDAO personDAO = null;
    private static BusDAO busDAO = null;
    private static StopDAO stopDAO = null;
    private static LineDAO lineDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public StudentDAO getStudentDAO() {
        if (studentDAO == null) {
            studentDAO = new StudentChange();
        }
        return studentDAO;
    }

    public PersonDAO getPersonDAO() {
        if (personDAO == null) {
            personDAO = new PersonChange();
        }
        return personDAO;
    }

    public BusDAO getBusDAO() {
        if (busDAO == null) {
            busDAO = new BusChange();
        }
        return busDAO;
    }

    public LineDAO getLineDAO() {
        if (lineDAO == null) {
            lineDAO = new LineChange();
        }
        return lineDAO;
    }

    public StopDAO getStopDAO() {
        if (stopDAO == null) {
            stopDAO = new StopChange();
        }
        return stopDAO;
    }
}
