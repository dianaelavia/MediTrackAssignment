package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.exception.*;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.observer.*;
import com.airtribe.meditrack.util.*;
import com.airtribe.meditrack.config.AppConfiguration;
import com.airtribe.meditrack.factory.BillFactory;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static DoctorService doctorService;
    private static PatientService patientService;
    private static AppointmentService appointmentService;
    private static AppointmentNotificationService notificationService;
    private static Scanner scanner;

    static {
        notificationService = new AppointmentNotificationService();
        notificationService.subscribe(new ConsoleNotificationListener());
        
        doctorService = new DoctorService();
        patientService = new PatientService();
        appointmentService = new AppointmentService(notificationService);
        scanner = new Scanner(System.in);
        
        AppConfiguration.getInstance().setDataLoaded(false);
    }

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("   Welcome to MediTrack - Clinic Management System");
        System.out.println("═══════════════════════════════════════════════════════════");

        if (args.length > 0 && "--loadData".equals(args[0])) {
            loadSampleData();
            AppConfiguration.getInstance().setDataLoaded(true);
        }

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1":
                        handleDoctorOperations();
                        break;
                    case "2":
                        handlePatientOperations();
                        break;
                    case "3":
                        handleAppointmentOperations();
                        break;
                    case "4":
                        handleBillingOperations();
                        break;
                    case "5":
                        handleAnalytics();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Thank you for using MediTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n┌─ MAIN MENU ────────────────────────────────────┐");
        System.out.println("│ 1. Doctor Management                            │");
        System.out.println("│ 2. Patient Management                           │");
        System.out.println("│ 3. Appointment Management                       │");
        System.out.println("│ 4. Billing Management                           │");
        System.out.println("│ 5. Analytics & Reports                          │");
        System.out.println("│ 0. Exit                                         │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.print("Choose option: ");
    }

    private static void handleDoctorOperations() throws InvalidDataException {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─ DOCTOR MENU ──────────────────────────────────┐");
            System.out.println("│ 1. Add Doctor                                   │");
            System.out.println("│ 2. View All Doctors                             │");
            System.out.println("│ 3. Search Doctor                                │");
            System.out.println("│ 4. Filter by Specialization                     │");
            System.out.println("│ 5. Back to Main Menu                            │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    addDoctorUI();
                    break;
                case "2":
                    viewAllDoctors();
                    break;
                case "3":
                    searchDoctorUI();
                    break;
                case "4":
                    filterBySpecializationUI();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    private static void addDoctorUI() throws InvalidDataException {
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.println("Select Specialization:");
        for (Specialization s : Specialization.values()) {
            System.out.println("  " + s.ordinal() + ". " + s.getDisplayName());
        }
        System.out.print("Choose: ");
        int specChoice = Integer.parseInt(scanner.nextLine().trim());
        Specialization specialization = Specialization.values()[specChoice];
        
        System.out.print("Enter Consultation Fee: ");
        double fee = Double.parseDouble(scanner.nextLine().trim());
        
        System.out.print("Enter Years of Experience: ");
        int years = Integer.parseInt(scanner.nextLine().trim());
        
        Doctor doctor = doctorService.addDoctor(name, email, phone, age, specialization, fee, years);
        System.out.println("✅ Doctor added successfully!");
        System.out.println(doctor);
    }

    private static void viewAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("📭 No doctors found");
            return;
        }
        System.out.println("\n┌─ ALL DOCTORS ──────────────────────────────────┐");
        doctors.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void searchDoctorUI() {
        System.out.print("Enter search query (name/specialization/id): ");
        String query = scanner.nextLine().trim();
        
        List<Doctor> results = doctorService.searchDoctors(query);
        if (results.isEmpty()) {
            System.out.println("❌ No doctors found matching: " + query);
            return;
        }
        System.out.println("\n┌─ SEARCH RESULTS ───────────────────────────────┐");
        results.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void filterBySpecializationUI() {
        System.out.println("Select Specialization:");
        for (Specialization s : Specialization.values()) {
            System.out.println("  " + s.ordinal() + ". " + s.getDisplayName());
        }
        System.out.print("Choose: ");
        int specChoice = Integer.parseInt(scanner.nextLine().trim());
        Specialization specialization = Specialization.values()[specChoice];
        
        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
        if (doctors.isEmpty()) {
            System.out.println("❌ No doctors found in " + specialization.getDisplayName());
            return;
        }
        System.out.println("\n┌─ DOCTORS BY SPECIALIZATION ────────────────────┐");
        doctors.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void handlePatientOperations() throws InvalidDataException {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─ PATIENT MENU ─────────────────────────────────┐");
            System.out.println("│ 1. Add Patient                                  │");
            System.out.println("│ 2. View All Patients                            │");
            System.out.println("│ 3. Search Patient                               │");
            System.out.println("│ 4. Test Deep Copy (Cloneable)                   │");
            System.out.println("│ 5. Back to Main Menu                            │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    addPatientUI();
                    break;
                case "2":
                    viewAllPatients();
                    break;
                case "3":
                    searchPatientUI();
                    break;
                case "4":
                    testDeepCopy();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    private static void addPatientUI() throws InvalidDataException {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.print("Enter Medical History: ");
        String history = scanner.nextLine().trim();
        
        System.out.print("Enter Emergency Contact (10 digits): ");
        String emergency = scanner.nextLine().trim();
        
        Patient patient = patientService.addPatient(name, email, phone, age, history, emergency);
        System.out.println("✅ Patient added successfully!");
        System.out.println(patient);
    }

    private static void viewAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("📭 No patients found");
            return;
        }
        System.out.println("\n┌─ ALL PATIENTS ─────────────────────────────────┐");
        patients.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void searchPatientUI() {
        System.out.print("Enter search query (name/email/id): ");
        String query = scanner.nextLine().trim();
        
        List<Patient> results = patientService.searchPatients(query);
        if (results.isEmpty()) {
            System.out.println("❌ No patients found matching: " + query);
            return;
        }
        System.out.println("\n┌─ SEARCH RESULTS ───────────────────────────────┐");
        results.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void testDeepCopy() throws InvalidDataException, CloneNotSupportedException {
        System.out.println("\n🔄 Testing Deep Copy (Cloneable)");
        List<Patient> patients = patientService.getAllPatients();
        
        if (patients.isEmpty()) {
            System.out.println("❌ No patients to clone. Add a patient first.");
            return;
        }
        
        Patient original = patients.get(0);
        Patient cloned = (Patient) original.clone();
        
        System.out.println("Original Patient: " + original.getName());
        System.out.println("Cloned Patient: " + cloned.getName());
        System.out.println("Are they the same object? " + (original == cloned));
        System.out.println("Allergies are different objects? " + (original.getAllergies() != cloned.getAllergies()));
        System.out.println("✅ Deep copy test completed!");
    }

    private static void handleAppointmentOperations() throws InvalidDataException, AppointmentNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─ APPOINTMENT MENU ────────────────────────────┐");
            System.out.println("│ 1. Schedule Appointment                         │");
            System.out.println("│ 2. View All Appointments                        │");
            System.out.println("│ 3. Confirm Appointment                          │");
            System.out.println("│ 4. Cancel Appointment                           │");
            System.out.println("│ 5. View Appointments by Patient                 │");
            System.out.println("│ 6. View Appointments by Doctor                  │");
            System.out.println("│ 7. Back to Main Menu                            │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    scheduleAppointmentUI();
                    break;
                case "2":
                    viewAllAppointments();
                    break;
                case "3":
                    confirmAppointmentUI();
                    break;
                case "4":
                    cancelAppointmentUI();
                    break;
                case "5":
                    viewAppointmentsByPatientUI();
                    break;
                case "6":
                    viewAppointmentsByDoctorUI();
                    break;
                case "7":
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    private static void scheduleAppointmentUI() throws InvalidDataException {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();
        Patient patient = patientService.getPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Patient not found");
            return;
        }
        
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine().trim();
        Doctor doctor = doctorService.getDoctorById(doctorId);
        
        if (doctor == null) {
            System.out.println("❌ Doctor not found");
            return;
        }
        
        System.out.print("Enter Appointment Date (YYYY-MM-DD HH:MM): ");
        String dateStr = scanner.nextLine().trim();
        LocalDateTime dateTime = LocalDateTime.parse(dateStr.replace(" ", "T"));
        
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine().trim();
        
        Appointment appointment = appointmentService.scheduleAppointment(patient, doctor, dateTime, notes);
        System.out.println("✅ Appointment scheduled successfully!");
        System.out.println(appointment);
    }

    private static void viewAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("📭 No appointments found");
            return;
        }
        System.out.println("\n┌─ ALL APPOINTMENTS ─────────────────────────────┐");
        appointments.forEach(System.out::println);
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void confirmAppointmentUI() throws AppointmentNotFoundException {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        appointmentService.confirmAppointment(appointmentId);
        System.out.println("✅ Appointment confirmed!");
    }

    private static void cancelAppointmentUI() throws AppointmentNotFoundException {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        appointmentService.cancelAppointment(appointmentId);
        System.out.println("✅ Appointment cancelled!");
    }

    private static void viewAppointmentsByPatientUI() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().trim();
        Patient patient = patientService.getPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Patient not found");
            return;
        }
        
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient);
        System.out.println("\n┌─ APPOINTMENTS FOR PATIENT: " + patient.getName() + " ──────────┐");
        if (appointments.isEmpty()) {
            System.out.println("  No appointments found");
        } else {
            appointments.forEach(System.out::println);
        }
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void viewAppointmentsByDoctorUI() {
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine().trim();
        Doctor doctor = doctorService.getDoctorById(doctorId);
        
        if (doctor == null) {
            System.out.println("❌ Doctor not found");
            return;
        }
        
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);
        System.out.println("\n┌─ APPOINTMENTS FOR DOCTOR: " + doctor.getName() + " ──────────┐");
        if (appointments.isEmpty()) {
            System.out.println("  No appointments found");
        } else {
            appointments.forEach(System.out::println);
        }
        System.out.println("└────────────────────────────────────────────────┘");
    }

    private static void handleBillingOperations() throws InvalidDataException, AppointmentNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─ BILLING MENU ────────────────────────────────┐");
            System.out.println("│ 1. Create Bill for Appointment                  │");
            System.out.println("│ 2. Create Emergency Bill                        │");
            System.out.println("│ 3. View Bill Details                            │");
            System.out.println("│ 4. Back to Main Menu                            │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    createConsultationBillUI();
                    break;
                case "2":
                    createEmergencyBillUI();
                    break;
                case "3":
                    viewBillDetailsUI();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    private static void createConsultationBillUI() throws AppointmentNotFoundException {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        
        String billId = IdGenerator.getInstance().generateBillId();
        Bill bill = BillFactory.createConsultationBill(billId, appointment);
        
        System.out.println("✅ Consultation Bill Created!");
        System.out.println(bill.generateBillDescription());
    }

    private static void createEmergencyBillUI() throws AppointmentNotFoundException {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        
        String billId = IdGenerator.getInstance().generateBillId();
        Bill bill = BillFactory.createEmergencyBill(billId, appointment);
        
        System.out.println("✅ Emergency Bill Created!");
        System.out.println(bill.generateBillDescription());
    }

    private static void viewBillDetailsUI() throws AppointmentNotFoundException {
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine().trim();
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        
        String billId = IdGenerator.getInstance().generateBillId();
        Bill bill = BillFactory.createConsultationBill(billId, appointment);
        BillSummary summary = BillSummary.fromBill(bill);
        
        System.out.println("\n" + summary);
    }

    private static void handleAnalytics() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌─ ANALYTICS MENU ───────────────────────────────┐");
            System.out.println("│ 1. Average Doctor Consultation Fee              │");
            System.out.println("│ 2. Appointments Per Doctor                      │");
            System.out.println("│ 3. Appointments by Status                       │");
            System.out.println("│ 4. Back to Main Menu                            │");
            System.out.println("└─────────────────────────────────────────────────┘");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    double avgFee = doctorService.getAverageConsultationFee();
                    System.out.println("💰 Average Consultation Fee: ₹" + String.format("%.2f", avgFee));
                    break;
                case "2":
                    Map<String, Long> appointmentsPerDoc = appointmentService.getAppointmentsPerDoctor();
                    System.out.println("\n📊 Appointments Per Doctor:");
                    appointmentsPerDoc.forEach((doc, count) -> 
                        System.out.println("  " + doc + ": " + count + " appointment(s)"));
                    break;
                case "3":
                    List<Appointment> pending = appointmentService.getAppointmentsByStatus(com.airtribe.meditrack.constants.AppointmentStatus.PENDING);
                    System.out.println("\n📅 Pending Appointments: " + pending.size());
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid option");
            }
        }
    }

    private static void loadSampleData() {
        try {
            System.out.println("📥 Loading sample data...");
            
            doctorService.addDoctor("Dr. Rajesh Kumar", "rajesh.kumar@meditrack.com", "9876543210", 45, 
                Specialization.CARDIOLOGY, 1000, 15);
            doctorService.addDoctor("Dr. Priya Sharma", "priya.sharma@meditrack.com", "9876543211", 38, 
                Specialization.DERMATOLOGY, 800, 10);
            doctorService.addDoctor("Dr. Amit Verma", "amit.verma@meditrack.com", "9876543212", 42, 
                Specialization.ORTHOPEDICS, 900, 12);
            
            patientService.addPatient("John Doe", "john.doe@email.com", "8765432109", 35, 
                "Hypertension", "9999999999");
            patientService.addPatient("Jane Smith", "jane.smith@email.com", "8765432108", 28, 
                "No major history", "9999999998");
            
            System.out.println("✅ Sample data loaded successfully!");
        } catch (InvalidDataException e) {
            System.out.println("❌ Error loading sample data: " + e.getMessage());
        }
    }
}