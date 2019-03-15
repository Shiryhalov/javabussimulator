package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {
    public void addPerson(Person person) throws SQLException;

    public void updatePerson(Person person) throws SQLException;

    public Person getPersonById(Long id) throws SQLException;

    public List<Person> getAllPersons() throws SQLException;

    public void deletePerson(Person person) throws SQLException;
}
