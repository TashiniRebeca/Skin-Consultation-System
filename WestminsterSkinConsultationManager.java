import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager
{
    Doctor focusDoctor;
    LocalDate focusDate;
    LocalTime focusStartTime, focusEndTime;

    Patient focusPatient;
    String focusNotes;
    Double focusCost;

    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Consultation> consultations = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();

    /**
     *Adding a New Doctor to the system.
     *@param input The console inputs
     **/
    @Override
    public void adding_new_doctor(Scanner input)
    {
        try
        {
            if (doctors.size()<=9)
            {
                String name,surname,licenseNo;
                Doctor doctor = new Doctor();
                System.out.print("\nEnter your name: ");
                name = input.next();
                while (!name.matches("^[A-Za-z ]*$"))
                {
                    System.out.println("\nEnter a valid name");
                    System.out.print("\nEnter your name: ");
                    name = input.next();
                }
                doctor.setName(name.substring(0,1).toUpperCase() + name.substring(1));
                //String name = doctor.getName();
                System.out.print("Enter your surname: ");
                surname = input.next();
                while (!surname.matches("^[A-Za-z ]*$"))
                {
                    System.out.println("\nEnter a valid name");
                    System.out.print("\nEnter your surname: ");
                    surname = input.next();
                }
                doctor.setSurname(surname.substring(0,1).toUpperCase() + surname.substring(1));
                System.out.print("Enter your birthday(yyyy-mm-dd): ");
                String dateOfBirth = input.next();
                while (!dateOfBirth.matches("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]" +
                        "|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$"))
                {
                    System.out.println("\nEnter a valid Date Of Birth!");
                    System.out.print("Enter your birthday(yyyy-mm-dd): ");
                    dateOfBirth = input.next();
                }
                doctor.setDateOfBirth(LocalDate.parse(dateOfBirth));
                System.out.print("Enter your Phone Number: ");
                String mobileNum = input.next();
                while (mobileNum.length() != 10)
                {
                    System.out.println("\nThe Phone Number should have 10 digits!Try again...");
                    System.out.print("\nEnter your Phone Number: ");
                    mobileNum = input.next();
                }
                doctor.setMobileNumber(mobileNum);
                System.out.print("Enter your Licence No(XXXXX): ");
                licenseNo = input.next();
                while (!licenseNo.matches("^[0-9]{5}$"))
                {
                    System.out.println("\nEnter a valid license number(XXXXX)!");
                    System.out.print("\nEnter your Licence No: ");
                    licenseNo = input.next();
                }
                doctor.setMedicalLicenceNo(licenseNo);
                doctor.setSpecialisation(Specialisations.chooseSpecialisation());
                doctors.add(doctor);
                System.out.println("\nThe Doctor Added Successfully to the System");
            }
            else System.out.println("\nList is full...Can't add more doctors!!!");
        }
        catch (DateTimeParseException dateTimeParseException)
        {
            System.out.println("\nInvalid Date Of Birth!!Try again...");
            adding_new_doctor(input);
        }
    }

    /**
     * Deleting a Doctor from the system.
     *@param medicalLicenceNo The License Number of the doctor
     **/
    @Override
    public void deleting_doctor(String medicalLicenceNo)
    {
        if (doctors.isEmpty()){System.out.println("\nLIST IS EMPTY");}
        else
        {
            Doctor doctor = new Doctor();
            boolean isAvailable = false;
            for (Doctor doc:doctors)
            {
                if (doc.getMedicalLicenceNo().equals(medicalLicenceNo))
                {
                    doctor = doc;
                    isAvailable=true;
                    break;
                }
            }
            if (isAvailable)
            {
                doctors.remove(doctor);
                System.out.println("\nDoctor with the licence no: " + medicalLicenceNo + " is successfully removed!");
                System.out.println("\nThe Number of Doctors in the List right now are " + doctors.size());
            }
            else System.out.println("\nThere's no any doctor with this licence number to be removed...Try Again");
        }

    }

    /**
     *Printing the list of the Doctors in the system.
     **/
    @Override
    public void printing_doctor_list()
    {
        int i = 0;
        if (doctors.size()==0){ System.out.println("\nThere're no doctors to display"); }
        else
        {
            doctors.sort((doc1, doc2) ->
            {
                String s1 = doc1.getSurname();
                String s2 = doc2.getSurname();
                return s1.compareToIgnoreCase(s2);
            });
            System.out.printf("%n%2s  %13s  %-13s  %-13s  %-13s  %-11s  %13s%n"," ","License-No","First-Name","Sur-name"
                    ,"Date Of Birth","Mobile Number","Specialization");
            for (Doctor doc:doctors)
            {
                i++;
                System.out.printf("%n %2s  %13s  %-13s  %-13s  %-13s  %-11s  %13s%n",i, doc.getMedicalLicenceNo(),
                        doc.getName(), doc.getSurname(),doc.getDateOfBirth(), doc.getMobileNumber(),
                        doc.getSpecialisation().name().toLowerCase());
            }
        }
    }

    /**
     Saving the information of Doctors to a separate file
     **/
    @Override
    public void saving_doctor_data_to_file()
    {
        try
        {
            FileOutputStream doctorF = new FileOutputStream("Skin Consultation Management - Doctor Details.txt");
            ObjectOutputStream doctorO = new ObjectOutputStream(doctorF);

            doctorO.writeObject(doctors);
            doctorO.close();
            doctorF.close();

            System.out.println("\nSuccessfully store the Doctors' data to file");
        }
        catch (IOException notFoundException) { System.out.println("There's no such file!!!");}
    }

    /**
     Loading the Doctor list Information from the saved text file.
     **/
    @Override
    public void loading_doctor_data()
    {
        try
        {
            FileInputStream doctorFI = new FileInputStream("Skin Consultation Management - Doctor Details.txt");
            ObjectInputStream doctorOI = new ObjectInputStream(doctorFI);

            doctors = (ArrayList<Doctor>) doctorOI.readObject();
            doctorOI.close();
            doctorFI.close();

            System.out.println("\nLoad the Doctors' data successfully");
        }
        catch (IOException notFoundException) { System.out.println("There's no such file!!!");}
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    /**
     Booking a doctor.(Adding Consultations)
     @param input The console inputs
     **/
    public void booking_a_doctor(Scanner input)
    {
        try {
            int j=0;
            System.out.println("""
                
                ADDING CONSULTATION
                
                Select your preferred Doctor from the below list""");

            System.out.printf("%n %2s  %10s  %-13s  %-13s  %13s%n"," ","License-No","First-Name","Sur-name"
                    ,"Specialization");
            for (Doctor doc2:doctors)
            {
                j++;
                System.out.printf("%n %2s  %10s  %-13s  %-13s  %13s%n",j, doc2.getMedicalLicenceNo(),
                        doc2.getName(), doc2.getSurname(), doc2.getSpecialisation());
            }
            System.out.print("\nType the Doctor's Licence No: ");
            String consultedDoctorNo = input.next();
            boolean isThere = false;

            for(Doctor docc:doctors)
            {
                if (docc.getMedicalLicenceNo().equals(consultedDoctorNo))
                {
                    isThere = true;
                    focusDoctor = docc;
                    break;
                }
            }
            if (isThere)
            {
                String patientName,patientSurname,patientMobileNo,patientId;
                focusDate = validateConsultDate(input);
                validateTimePeriod(input);
                System.out.print("Enter your name: ");
                patientName = input.next();
                while (!patientName.matches("^[A-Za-z ]*$"))
                {
                    System.out.println("\nEnter a valid name!");
                    System.out.print("Enter your name: ");
                    patientName = input.next();
                }
                System.out.print("Enter your surname: ");
                patientSurname = input.next();
                while (!patientSurname.matches("^[A-Za-z ]*$"))
                {
                    System.out.println("\nEnter a valid surname!");
                    System.out.print("Enter your surname: ");
                    patientSurname = input.next();
                }
                System.out.print("Enter your DOB(yyyy-mm-dd): ");
                LocalDate patientDOB = LocalDate.parse(input.next());
                while (!patientDOB.toString().matches("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))" +
                        "|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$"))
                {
                    System.out.println("\nEnter a valid Date Of Birth!");
                    System.out.print("Enter your DOB(yyyy-mm-dd): ");
                    patientDOB = LocalDate.parse(input.next());
                }
                System.out.print("Enter your Mobile Number: ");
                patientMobileNo = input.next();
                while (patientMobileNo.length() != 10)
                {
                    System.out.println("\nEnter a Mobile Number with 10 digits!");
                    System.out.print("Enter your Mobile Number: ");
                    patientMobileNo = input.next();
                }
                System.out.print("Enter your ID(XXXXX): ");
                patientId = input.next();
                while (patientId.length() != 5)
                {
                    System.out.println("\nEnter a valid ID number!");
                    System.out.print("Enter your ID(XXXXX): ");
                    patientId = input.next();
                }
                System.out.print("Want to add some notes? ('y' for Yes & 'n' for No): ");
                input.nextLine();
                String choice = input.nextLine().toLowerCase();
                if (choice.equals("y"))
                {
                    System.out.print("Write your notes here: ");
                    focusNotes = input.nextLine();
                }
                else focusNotes = " - ";

                focusCost = getConsultationCost(patientId,focusStartTime,focusEndTime);

                focusPatient = new Patient(patientName.substring(0,1).toUpperCase() +
                               patientName.substring(1),patientSurname.substring(0,1).toUpperCase() +
                               patientSurname.substring(1),patientDOB,patientMobileNo,patientId,focusDoctor);
                patients.add(focusPatient);

                Consultation newConsultation = new Consultation(focusDate,focusStartTime,focusEndTime,focusCost,
                        focusNotes,focusDoctor,focusPatient);

                consultations.add(newConsultation);
                System.out.println("""

                    Your Consultation is Successfully booked.
                    \t\tBOOKING DETAILS\t\t
                    """);
                System.out.println("Booked Date: " + focusDate + "\nBooked Time: " + focusStartTime + " to " +
                        focusEndTime + "\nNotes : " + focusNotes + "\nCost: £" + focusCost + "\nDoctor's Id: "
                        + focusDoctor.getMedicalLicenceNo() + "\nDoctor's Name: " + focusDoctor.getName() + "\nSpecialisation: "
                        + focusDoctor.getSpecialisation() + "\nPatient's Id: " + patientId + "\nPatient's Name: "
                        + patientName + " " + patientSurname
                );
            }
            else
            {
                System.out.println("There's no doctor with the License Number "+ consultedDoctorNo + "... Try Again!!!!");
                booking_a_doctor(input);
            }
        }
        catch (DateTimeParseException d)
        {
            System.out.println("\nEnter a valid Date Of Birth!");
            booking_a_doctor(input);
        }
    }

    /**
     Canceling a booked consultation.
     @param input The console inputs
     **/
    public void canceling_a_consultation(Scanner input)
    {
        int j = 0;
        System.out.println("Currently these are the consultations in the system!!!");
        System.out.printf("%n%2s  %13s  %13s  %13s  %20s  %23s  %22s  %22s  %18s  %n"," ","Consultation-Date","Consultation-Time",
                "Consultation-Cost","Consultation-Notes","Doctor ID","Doctor's Name","Patient's ID","Patient's Name");
        for (Consultation c:consultations)
        {
            j++;
            System.out.printf("%n%2s  %13s  %15s  %13s  %35s  %19s  %18s  %18s  %18s  %n",j, c.getConsultDate(), c.getConsultStartTime()+ "-" + c.getConsultEndTime(),
                    c.getCost(), c.getNotes(),c.getDoc().getMedicalLicenceNo(),c.getDoc().getName(),c.getPatient().getPatientId(),c.getPatient().getName());
        }
        System.out.print("\nEnter the Patient's ID of the consultation you want to remove: ");
        String removeId = input.next();
        for (Consultation consultation: consultations)
        {
            if (Objects.equals(consultation.getPatient().getPatientId(), removeId))
            {
                consultations.remove(consultation);
                System.out.println("\nSuccessfully Removed the Consultation with Patient ID " + removeId + " from the System...");
                if (consultations.isEmpty())
                {
                    System.out.println("\nThere're no Consultations available in the system...");
                }
                break;
            }
            else
            {
                System.out.println("\nThere's no patient with that ID!Try Again...");
                canceling_a_consultation(input);
            }
        }
    }

    /**
     * Displaying the GUI of the System
     **/
    public void displaying_gui()
    {
        new MainGUIFrame();
        System.out.println("\nThe GUI Application will display now...");
    }

    /**
     * Saving the information of Patients to a separate file
     */
    public void saving_patient_data_to_file()
    {
        try
        {
            FileOutputStream patientF = new FileOutputStream("Skin Consultation Management - Patient Details.txt");
            ObjectOutputStream patientO = new ObjectOutputStream(patientF);

            patientO.writeObject(patients);
            patientO.close();
            patientF.close();

            System.out.println("\nSuccessfully store the Patients Data to file");
        } catch (IOException e) { System.out.println("There's no such file!!!");}
    }

    /**
     * Saving the information of Consultations to a separate file
     */
    public void saving_consultation_data_to_file()
    {
        try
        {
            FileOutputStream consultationF = new FileOutputStream("Skin Consultation Management - Consultation Details.txt");
            ObjectOutputStream consultationO = new ObjectOutputStream(consultationF);

            consultationO.writeObject(consultations);
            consultationO.close();
            consultationF.close();

            System.out.println("\nSuccessfully store the Consultation Data to file");
        } catch (IOException e)
        {
            System.out.println("There's no such file!!!");
            e.printStackTrace();
        }
    }

    /**
     * Loading the Patient list Information from the saved text file.
     */
    public void loading_patient_data()
    {
        try
        {
            FileInputStream patientFI = new FileInputStream("Skin Consultation Management - Patient Details.txt");
            ObjectInputStream patientOI = new ObjectInputStream(patientFI);

            patients = (ArrayList<Patient>) patientOI.readObject();
            patientOI.close();
            patientFI.close();

            System.out.println("\nLoad the patient data successfully");
        }
        catch (IOException notFoundException) { System.out.println("There's no such file!!!");}
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    /**
     * Loading the Consultation list Information from the saved text file.
     */
    public void loading_consultation_data()
    {
        try
        {
            FileInputStream consultationFI = new FileInputStream("Skin Consultation Management - Consultation Details.txt");
            ObjectInputStream consultationOI = new ObjectInputStream(consultationFI);

            consultations = (ArrayList<Consultation>) consultationOI.readObject();
            consultationOI.close();
            consultationFI.close();

            System.out.println("\nLoad the consultation data successfully");
        }
        catch (IOException notFoundException) { System.out.println("There's no such file!!!");}
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    /**
     * Validating the Consultation Date which is of type LocalDate not to be a date in the past
      @param input The console inputs
      @return LocalDate
     **/
    private LocalDate validateConsultDate(Scanner input)
    {
        LocalDate date;
        System.out.print("Enter the date for the consultation(yyyy-mm-dd): ");
        date = LocalDate.parse(input.next());
        if (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())){ return date; }
        else
        {
            System.out.println("Enter a valid date for your consultation....");
            validateConsultDate(input);
        }
        return date;
    }

    /**
     * Validating the time period of the consultation- The start time must be before the end time
     @param input The console inputs
     **/
    private void validateTimePeriod(Scanner input)
    {
        System.out.print("Enter the specific Time Slot for the consultation(hh:mm) - Start Time: ");
        LocalTime startTime = LocalTime.parse(input.next());
        System.out.print("Enter the specific Time Slot for the consultation(hh:mm) - End Time: ");
        LocalTime endTime = LocalTime.parse(input.next());
        if (endTime.isAfter(startTime))
        {
            if(endTime.isBefore(LocalTime.parse("20:00")) && startTime.isAfter(LocalTime.parse("09:00")))
            {
                focusStartTime = startTime;
                focusEndTime = endTime;
            }
            else
            {
                System.out.println("Consultation hours are between 09:00 a.m. to 20:00 p.m.");
                validateTimePeriod(input);
            }
        }
        else
        {
            System.out.println("End time cannot be before start time...");
            validateTimePeriod(input);
        }
    }

    /**
     * Calculating the time duration of the consultation
     * @param startTime The Start Time of the Consultation
     * @param endTime The End Time of the Consultation
     * @return int
     */
    private int getConsultationHours(LocalTime startTime, LocalTime endTime)
    {
        return endTime.getHour() - startTime.getHour();
    }

    /**
     * Calculating the Consultation Cost
     * @param patientId The ID of the patient
     * @param startTime The Start Time of the Consultation
     * @param endTime The End Time of the Consultation
     * @return double
     */
    private double getConsultationCost(String patientId,LocalTime startTime,LocalTime endTime)
    {
        for (Patient patient:patients)
        {
            if (patient.getPatientId().equals(patientId))
            {
                return 25 * getConsultationHours(startTime,endTime); //If the added patient was there before in the system then the consultation cost would be  £25  per hour
            }
        }
        return 15 * getConsultationHours(startTime,endTime); // If this is the patient's first consultation then the consultation cost would be  £15  per hour
    }
}
