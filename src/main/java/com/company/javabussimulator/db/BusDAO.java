package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Bus;

import java.sql.SQLException;
import java.util.List;

public interface BusDAO {
    public void addBus(Bus bus) throws SQLException;

    public void updateBus(Bus bus) throws SQLException;

    public Bus getBusById(Long id) throws SQLException;

    public List<Bus> getAllBuses() throws SQLException;

    public void deleteBus(Bus bus) throws SQLException;
}
