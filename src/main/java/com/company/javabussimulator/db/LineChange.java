package com.company.javabussimulator.db;

import com.company.javabussimulator.entities.Line;
import com.company.javabussimulator.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineChange implements LineDAO {
    public void addLine(Line line) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(line);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateLine(Line line) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(line);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Line getLineById(Long id) throws SQLException {
        Session session = null;
        Line line = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            line = (Line) session.load(Line.class, id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return line;
    }

    @SuppressWarnings("unchecked")
    public List<Line> getAllLines() throws SQLException {
        Session session = null;
        List<Line> lines = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            lines = (List<Line>) session.createCriteria(Line.class).list();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return lines;
    }

    public void deleteLine(Line line) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(line);
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
