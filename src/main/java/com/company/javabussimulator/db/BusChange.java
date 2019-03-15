package com.company.javabussimulator.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.javabussimulator.entities.Bus;
import com.company.javabussimulator.util.HibernateUtil;
import org.hibernate.Session;

public class BusChange implements BusDAO {
    public void addBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Bus getBusById(Long id) throws SQLException {
        Session session = null;
        Bus bus = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bus = (Bus) session.load(Bus.class, id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bus;
    }

    @SuppressWarnings("unchecked")
    public List<Bus> getAllBuses() throws SQLException {
        Session session = null;
        List<Bus> buses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            buses = (List<Bus>) session.createCriteria(Bus.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buses;
    }

    public void deleteBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bus);
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

