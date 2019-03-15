package com.company.javabussimulator.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.company.javabussimulator.util.HibernateUtil;
import com.company.javabussimulator.entities.Person;

public class PersonChange implements PersonDAO {

    public void addPerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updatePerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Person getPersonById(Long id) throws SQLException {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            person = (Person) session.load(Person.class, id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return person;
    }

    @SuppressWarnings("unchecked")
    public List<Person> getAllPersons() throws SQLException {
        Session session = null;
        List<Person> people = new ArrayList<Person>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            people = (List<Person>) session.createCriteria(Person.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return people;
    }

    public void deletePerson(Person stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
