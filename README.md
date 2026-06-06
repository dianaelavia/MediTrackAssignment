# MediTrack - Clinic & Appointment Management System

## 📋 Overview

MediTrack is a comprehensive Java-based clinic and appointment management system demonstrating professional OOP design, SOLID principles, and modern design patterns.

## 🎯 Features

### Core Functionality
- **Doctor Management**: Add, search, and filter doctors by specialization
- **Patient Management**: Register patients, manage medical history and allergies
- **Appointment Scheduling**: Book, confirm, and cancel appointments
- **Billing System**: Generate bills with tax calculations using Strategy pattern
- **Analytics**: View statistics about doctors, appointments, and billing

### Design Patterns Implemented
- ✅ **Singleton**: IdGenerator, AppConfiguration
- ✅ **Factory**: BillFactory for creating different bill types
- ✅ **Strategy**: BillingStrategy for consultation and emergency billing
- ✅ **Observer**: AppointmentNotificationService for appointment notifications
- ✅ **Template Method**: Service layer CRUD operations

### OOP Principles
- ✅ **Encapsulation**: Private fields, public getters/setters with validation
- ✅ **Inheritance**: Person → Doctor, Patient hierarchy
- ✅ **Polymorphism**: Overriding matches() and toString()
- ✅ **Abstraction**: Abstract Person class and interfaces
- ✅ **Deep Copy**: Cloneable implementation with proper object cloning
- ✅ **Immutability**: BillSummary with final fields
- ✅ **Enums**: Specialization, AppointmentStatus

### Modern Java Features
- ✅ **Streams & Lambdas**: Filtering, mapping, grouping operations
- ✅ **Generics**: DataStore<T> for type-safe collections
- ✅ **Collections**: ArrayList, HashMap
- ✅ **Concurrency**: AtomicInteger for thread-safe counters
- ✅ **File I/O**: CSV utilities with try-with-resources
- ✅ **Exception Handling**: Custom exceptions with proper chaining

## 📁 Project Structure

```
src/main/java/com/airtribe/meditrack/
├── constants/
│   ├── Constants.java
│   ├── Specialization.java (enum)
│   └── AppointmentStatus.java (enum)
├── entity/
│   ├── Person.java (abstract)
│   ├── Doctor.java
│   ├── Patient.java
│   ├── Appointment.java
│   ├── Bill.java
│   └── BillSummary.java (immutable)
├── service/
│   ├── DoctorService.java
│   ├── PatientService.java
│   └── AppointmentService.java
├── strategy/
│   ├── BillingStrategy.java (interface)
│   ├── ConsultationBillingStrategy.java
│   └── EmergencyBillingStrategy.java
├── observer/
│   ├── AppointmentNotificationService.java
│   └── ConsoleNotificationListener.java
├── factory/
│   └── BillFactory.java
├── config/
│   └── AppConfiguration.java (Singleton)
├── util/
│   ├── Validator.java
│   ├── DateUtil.java
│   ├── IdGenerator.java (Singleton)
│   ├── DataStore.java (Generic)
│   └── CSVUtil.java
├── interfaces/
│   ├── Searchable.java
│   ├── Payable.java
│   └── NotificationListener.java
├── exception/
│   ├── InvalidDataException.java
│   └── AppointmentNotFoundException.java
├── test/
│   └── TestRunner.java
└── Main.java (menu-driven UI)
```

## 🚀 Getting Started

### Prerequisites
- JDK 11 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/dianaelavia/MediTrackAssignment.git
   cd MediTrackAssignment
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   # Without sample data
   mvn exec:java -Dexec.mainClass="com.airtribe.meditrack.Main"
   
   # With sample data loaded
   mvn exec:java -Dexec.mainClass="com.airtribe.meditrack.Main" -Dexec.args="--loadData"
   ```

4. **Run tests**
   ```bash
   mvn exec:java -Dexec.mainClass="com.airtribe.meditrack.test.TestRunner"
   ```

### Compile & Run Directly

```bash
# Compile
javac -d target/classes -sourcepath src/main/java $(find src/main/java -name "*.java")

# Run Main Application
java -cp target/classes com.airtribe.meditrack.Main --loadData

# Run Tests
java -cp target/classes com.airtribe.meditrack.test.TestRunner
```

## 📝 Usage Examples

### Menu-Driven Application
When you run the application, you'll see a main menu:

```
═══════════════════════════════════════════════════════════
   Welcome to MediTrack - Clinic Management System
═══════════════════════════════════════════════════════════

┌─ MAIN MENU ────────────────────────────────────┐
│ 1. Doctor Management                            │
│ 2. Patient Management                           │
│ 3. Appointment Management                       │
│ 4. Billing Management                           │
│ 5. Analytics & Reports                          │
│ 0. Exit                                         │
└─────────────────────────────────────────────────┘
```

### Key Operations

**Adding a Doctor**
- Navigate to Doctor Management → Add Doctor
- Enter details: name, email, phone, age, specialization, fee, experience
- System generates unique Doctor ID automatically

**Registering a Patient**
- Navigate to Patient Management → Add Patient
- Enter: name, email, phone, age, medical history, emergency contact
- Add allergies as needed

**Scheduling Appointment**
- Navigate to Appointment Management → Schedule Appointment
- Select patient and doctor IDs
- Enter appointment date and notes
- System notifies observers automatically

**Generating Bills**
- Navigate to Billing Management
- Choose consultation or emergency billing
- System calculates tax automatically (18% GST)

**Analytics**
- View average consultation fees
- See appointments per doctor
- Track appointment statuses

## 🧪 Testing

Run the comprehensive test suite:

```bash
mvn exec:java -Dexec.mainClass="com.airtribe.meditrack.test.TestRunner"
```

Tests cover:
- ✅ Validation logic
- ✅ Singleton pattern
- ✅ Inheritance and polymorphism
- ✅ Deep copy semantics
- ✅ Immutability enforcement
- ✅ Generic type safety

## 🏗️ Architecture Highlights

### Service Layer
Services handle all business logic:
- Thread-safe operations
- Exception handling
- Validation through utilities
- Stream-based analytics

### Data Access
Generic DataStore<T> provides:
- Type-safe storage
- Indexed access by key
- List retrieval
- O(1) lookup performance

### Notification System
Observer pattern:
- Decoupled appointment operations
- Multiple listener support
- Extensible notification types

### Validation
Centralized Validator utility:
- Email, phone, age validation
- Fee and experience validation
- Custom exception throwing

## 🎓 Learning Objectives Met

✅ Java setup and JVM basics  
✅ Core OOP principles (encapsulation, inheritance, polymorphism, abstraction)  
✅ Advanced OOP (cloning, immutability, enums, static blocks)  
✅ Collections, generics, comparators  
✅ Exception handling (custom exceptions, chaining)  
✅ File I/O and CSV parsing  
✅ Concurrency basics (AtomicInteger, threads)  
✅ Design patterns (7 patterns implemented)  
✅ Java 8+ features (streams, lambdas)  
✅ Manual testing and documentation  

## 📚 Key Design Decisions

### Why These Patterns?

**Singleton (IdGenerator, AppConfiguration)**
- Ensures single instance of ID generation
- Thread-safe counter incrementation
- Prevents ID collision

**Strategy (BillingStrategy)**
- Different billing algorithms (consultation vs emergency)
- Easy to add new billing types
- Runtime algorithm selection

**Observer (AppointmentNotificationService)**
- Decouples appointment logic from notifications
- Support multiple notification channels
- Extensible for email, SMS, etc.

**Factory (BillFactory)**
- Centralized bill creation
- Easy to add bill types
- Hides bill creation complexity

**Immutability (BillSummary)**
- Thread-safe by default
- Cannot be modified after creation
- Safe for concurrent access

## 🔧 Technologies Used

- **Language**: Java 11+
- **Build Tool**: Maven
- **Collections**: ArrayList, HashMap
- **Concurrency**: AtomicInteger
- **Streams**: Lambdas, filter, map, collect, groupingBy
- **Exception Handling**: Custom exceptions, try-catch, try-with-resources

## 📖 Code Examples

### Streams & Lambdas
```java
// Filter doctors by specialization
List<Doctor> cardiologists = doctorService.getDoctorsBySpecialization(Specialization.CARDIOLOGY);

// Get average consultation fee
double avgFee = doctorService.getAverageConsultationFee();

// Count appointments per doctor
Map<String, Long> appointmentsPerDoctor = appointmentService.getAppointmentsPerDoctor();
```

### Deep Copy
```java
Patient original = patientService.getPatientById("PAT1001");
Patient cloned = (Patient) original.clone();
// Cloned object has independent allergies list
```

### Immutability
```java
Bill bill = new Bill("BILL001", appointment, 500);
BillSummary summary = BillSummary.fromBill(bill);
// summary cannot be modified - no setters available
```

---

**MediTrack** - Building healthcare management systems the right way! 🏥
