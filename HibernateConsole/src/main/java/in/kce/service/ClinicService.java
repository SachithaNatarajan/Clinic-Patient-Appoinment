package in.kce.service;

import java.util.Date;
import java.util.List;
import in.kce.entity.Appointment;
import in.kce.entity.Patient;
import in.kce.util.ActiveAppointmentException;
import in.kce.util.AppointmentSlotUnavailableException;
import in.kce.util.ValidationException;
import in.kce.dao.AppointmentDAO;
import in.kce.dao.PatientDAO;

public class ClinicService {
	public Patient viewPatientDetails(String patientID) {
		PatientDAO patientdao=new PatientDAO();
		return patientdao.findPatient(patientID);
	}
	
	public List<Patient> viewAllPatients(){
		PatientDAO patientdao=new PatientDAO();
		return patientdao.viewAllPatients();
	}
	
	public boolean addNewPatient(Patient patient) {
		PatientDAO patientdao=new PatientDAO();
		if(patientdao.findPatient(patient.getPatientID())==null) {
			return patientdao.insertPatient(patient);
		}
		return false;
	}
	
	public boolean removePatient(String patientID) {
		PatientDAO patientdao=new PatientDAO();
		if(patientdao.findPatient(patientID) != null)
		{
			return patientdao.deletePatient(patientID);
		}
		return false;
	}
	
	public boolean bookAppointment(String patientID,String doctorID,Date date,String time) {
		AppointmentDAO appointmentdao=new AppointmentDAO();
		final String ACTIVE = "ACTIVE";
		PatientDAO patientDAO=new PatientDAO();
		try {
		if(patientID==null || doctorID==null  || time==null) {
		    throw new ValidationException();
		}
		if(date.before(new Date())) {
			throw new ValidationException();
		}
		if(patientDAO.findPatient(patientID)==null) {
			return false;
		}
		if(!appointmentdao.isSlotAvailable(doctorID, date, time)) {
			throw new AppointmentSlotUnavailableException();
		}
		Appointment appt=new Appointment();
		appt.setPatientID(patientID);
		appt.setDoctorID(doctorID);
		appt.setAppointmentDate(date);
		appt.setAppointmentTime(time);
		appt.setStatus(ACTIVE);
		return appointmentdao.recordAppointment(appt);
		
		}catch(ValidationException e){
			return false;
		} catch (AppointmentSlotUnavailableException e) {
			return false;
		}
	}
	
	public boolean cancelAppointment(int appointmentID) {
		try {
		if(appointmentID<=0) {
			throw new ValidationException();
		}
		 AppointmentDAO appointmentdao = new AppointmentDAO();
	     Appointment appt = appointmentdao.findAppointment(appointmentID);
	        if(appt == null) {
	            return false;
	        }	
	        if("CANCELLED".equals(appt.getStatus())) {
	            throw new ActiveAppointmentException();
	        }
	        return appointmentdao.cancelAppointment(appointmentID);
		}catch(ValidationException | ActiveAppointmentException e) {
			return false;
		}
	}
}
