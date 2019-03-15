package com.company.javabussimulator.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.javabussimulator.entities.Student;
import com.company.javabussimulator.util.HibernateUtil;
import org.hibernate.Session;

public class StudentChange implements StudentDAO {

    public void addStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(stud);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(stud);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Student getStudentById(Long id) throws SQLException {
        Session session = null;
        Student stud = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            stud = (Student) session.load(Student.class, id);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stud;
    }

    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() throws SQLException {
        Session session = null;
        List<Student> studs = new ArrayList<Student>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            studs = (List<Student>) session.createCriteria(Student.class).list();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studs;
    }

    public void deleteStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(stud);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
