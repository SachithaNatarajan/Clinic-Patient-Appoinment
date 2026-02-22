package in.kce.main;

import java.util.Date;
import java.util.Scanner;

import in.kce.entity.Patient;
import in.kce.service.ClinicService;

public class ClinicMain {
	private static ClinicService clinicService;
	
	public static void main(String[] args) { 
		clinicService = new ClinicService(); 
		Scanner sc = new Scanner(System.in);
		System.out.println("--- Clinic Appointment Console ---");  
		
		Patient p = new Patient(); 
		p.setPatientID("PT104"); 
		p.setPatientName("Raju"); 
		p.setAge(20); 
		p.setGender("Female"); 
		p.setPhone("8032478516"); 
        
		try { 
			boolean r = clinicService.addNewPatient(p); 
			System.out.println(r ? "ADDED" : "FAILED");
		} catch(Exception e) { 
			System.out.println(e);
		}
		try {
			boolean r =clinicService.bookAppointment("PT104","DR101",new Date(),"6:00 PM");
			System.out.println(r ? "BOOKED" : "FAILED");
		} catch(Exception e) { 
			System.out.println(e); 
			}
		 
		try {
			boolean r = clinicService.cancelAppointment(3); 
			System.out.println(r ? "CANCELLED" : "FAILED");
		} catch(Exception e) { 
			System.out.println(e); 
		}
		
			sc.close(); 
		}
}
