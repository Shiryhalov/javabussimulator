package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Stop;
import com.company.javabussimulator.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StopChange implements StopDAO {
    public void addStop(Stop stop) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(stop);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStop(Stop stop) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(stop);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Stop getStopById(Long id) throws SQLException {
        Session session = null;
        Stop stop = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            stop = (Stop) session.load(Stop.class, id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stop;
    }

    @SuppressWarnings("unchecked")
    public List<Stop> getAllStops() throws SQLException {
        Session session = null;
        List<Stop> stops = new ArrayList<Stop>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            stops = (List<Stop>) session.createCriteria(Stop.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stops;
    }

    public void deleteStop(Stop stop) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(stop);
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
