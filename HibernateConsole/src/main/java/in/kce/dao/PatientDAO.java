package in.kce.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import in.kce.entity.Patient;
import in.kce.util.HibernateUtil;




public class PatientDAO {
	public Patient findPatient(String patientID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Patient patient = session.get(Patient.class, patientID);
        session.close();
        return patient;
	}
	
	public List<Patient> viewAllPatients(){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Patient_Tbl", Patient.class).list();
        }
	}
	
	public boolean insertPatient(Patient patient) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction =session.beginTransaction();
			session.save(patient);
			transaction.commit();
			return true;
		}catch (Exception e) {
            transaction.rollback();
        }
		return false;
	}
	
	public boolean deletePatient(String patientID) {
		Transaction transaction=null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction =session.beginTransaction();
			Patient patient = session.get(Patient.class, patientID);
			if(patient!=null) {
				session.delete(patient);
			}
			transaction.commit();
			return true;
		}catch (Exception e) {
            transaction.rollback();
        }
		return false;
	}
}
