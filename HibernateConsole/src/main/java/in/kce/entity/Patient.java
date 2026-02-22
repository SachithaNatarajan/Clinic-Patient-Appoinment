package in.kce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PATIENT_TABLE")
public class Patient {
	
	@Id
    @Column(name = "Patient_ID")
	private String patientID;
	
	@Column(name = "Patient_Name")
	private String patientName;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Phone")
	private String phone;
	
	
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
