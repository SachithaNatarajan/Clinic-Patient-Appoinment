package in.kce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import in.kce.entity.*;
import in.kce.util.HibernateUtil;

public class AppointmentDAO {
	public boolean recordAppointment(Appointment appt) {
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.save(appt);
			transaction.commit();
			return true;
		}catch (Exception e) {
            transaction.rollback();
        }
		return false;
		
	}
	
	public boolean cancelAppointment(int appointmentID) {
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			Appointment appt=session.get(Appointment.class, appointmentID);
			if(appt!=null) {
				appt.setStatus("CANCELLED");
				session.update(appt);
				transaction.commit();
				return true;
			}
		}catch (Exception e) {
            transaction.rollback();
        }
		return false;
		
	}
	
	 public boolean isSlotAvailable(String doctorID, java.util.Date date, String time) {

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	            String hql = "SELECT COUNT(a) FROM Appointment a "
	                       + "WHERE a.doctorID = :docId "
	                       + "AND a.appointmentDate = :date "
	                       + "AND a.appointmentTime = :time "
	                       + "AND a.status = 'ACTIVE'";

	            Query<Long> query = session.createQuery(hql, Long.class);
	            query.setParameter("docId", doctorID);
	            query.setParameter("date", date);
	            query.setParameter("time", time);

	            Long count = query.uniqueResult();

	            return count == 0;
	        }
	    }
	
	public Appointment findAppointment(int AppointmentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Appointment appt = session.get(Appointment.class, AppointmentID);
        session.close();
        return appt;
	}
}
