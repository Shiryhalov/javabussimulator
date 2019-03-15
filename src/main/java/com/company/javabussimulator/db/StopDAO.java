package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Stop;

import java.sql.SQLException;
import java.util.List;

public interface StopDAO {
    public void addStop(Stop stop) throws SQLException;

    public void updateStop(Stop stop) throws SQLException;

    public Stop getStopById(Long id) throws SQLException;

    public List<Stop> getAllStops() throws SQLException;

    public void deleteStop(Stop stop) throws SQLException;
}
