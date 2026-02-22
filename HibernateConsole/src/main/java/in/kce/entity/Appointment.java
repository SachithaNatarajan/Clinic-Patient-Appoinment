package in.kce.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "APPOINTMENT_TABLE")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Appointment_ID")
    private int appointmentID;  
	
	@Column(name = "Patient_ID")
	private String patientID;
	
	@Column(name = "Doctor_ID")
	private String doctorID;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Appointment_Date")
	private Date appointmentDate;
	
    @Column(name = "Appointment_Time")
	private String appointmentTime;
	 
	@Column(name = "Status")
	private String status;
	
	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
