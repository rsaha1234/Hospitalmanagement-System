
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagement {

    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Patients");
            System.out.println("5. View Doctors");
            System.out.println("6. View Appointments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    addDoctor(scanner);
                    break;
                case 3:
                    scheduleAppointment(scanner);
                    break;
                case 4:
                    viewPatients();
                    break;
                case 5:
                    viewDoctors();
                    break;
                case 6:
                    viewAppointments();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid age: ");
            scanner.next();
        }
        int age = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();

        Patient patient = new Patient(name, age, gender);
        patients.add(patient);

        System.out.println("✅ Patient added successfully!");
    }

    private static void addDoctor(Scanner scanner) {
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Doctor Specialty: ");
        String specialty = scanner.nextLine();

        Doctor doctor = new Doctor(name, specialty);
        doctors.add(doctor);

        System.out.println("✅ Doctor added successfully!");
    }

    private static void scheduleAppointment(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid Patient ID: ");
            scanner.next();
        }
        int patientId = scanner.nextInt();

        System.out.print("Enter Doctor ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid Doctor ID: ");
            scanner.next();
        }
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Patient patient = findPatientById(patientId);
        Doctor doctor = findDoctorById(doctorId);

        if (patient != null && doctor != null) {
            Appointment appointment = new Appointment(patient, doctor, date);
            appointments.add(appointment);
            System.out.println("✅ Appointment scheduled successfully!");
        } else {
            System.out.println("❌ Invalid Patient ID or Doctor ID.");
        }
    }

    private static void viewPatients() {
        System.out.println("\n--- List of Patients ---");
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        }
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void viewDoctors() {
        System.out.println("\n--- List of Doctors ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        }
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    private static void viewAppointments() {
        System.out.println("\n--- List of Appointments ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private static Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
}

// -------- Patient Class --------
class Patient {

    private static int idCounter = 1;
    private int id;
    private String name;
    private int age;
    private String gender;

    public Patient(String name, int age, String gender) {
        this.id = idCounter++;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}

// -------- Doctor Class --------
class Doctor {

    private static int idCounter = 1;
    private int id;
    private String name;
    private String specialty;

    public Doctor(String name, String specialty) {
        this.id = idCounter++;
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialty: " + specialty;
    }
}

// -------- Appointment Class --------
class Appointment {

    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment: [Patient: " + patient.getId() + " - " + patient.toString()
                + ", Doctor: " + doctor.getId() + " - " + doctor.toString()
                + ", Date: " + date + "]";
    }
}
