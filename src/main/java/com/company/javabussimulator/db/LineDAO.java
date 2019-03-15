package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Line;

import java.sql.SQLException;
import java.util.List;

public interface LineDAO {
    public void addLine(Line line) throws SQLException;

    public void updateLine(Line line) throws SQLException;

    public Line getLineById(Long id) throws SQLException;

    public List<Line> getAllLines() throws SQLException;

    public void deleteLine(Line line) throws SQLException;
}
