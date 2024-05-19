import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        int choice;
        try {
            do
            {
                System.out.println("""
                    
                    SKIN CONSULTATION CENTER - GALLE
                    
                    << 101 : Add a new doctor to the system           >>
                    << 102 : Delete a doctor from the existing system >>
                    << 103 : Print the list of doctors in system      >>
                    << 104 : Save the Doctor info to a file           >>
                    << 105 : Load back the Doctor details again       >>
                    << 106 : Book a doctor                            >>
                    << 107 : Canceling a Consultation                 >>
                    << 108 : GUI Application                          >>
                    << 109 : Save the Consultation Info to file       >>
                    << 110 : Load back the Consultation Details again >>
                    << 111 : Save the Patient Info to file            >>
                    << 112 : Load back the Patient Details again      >>
                    << 113 : Exit                                     >>
                    """);
                System.out.print("Select your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 101 -> manager.adding_new_doctor(scanner);
                    case 102 ->
                            {
                                System.out.print("\nEnter the licence no: ");
                                String removeNo = scanner.next();
                                manager.deleting_doctor(removeNo);
                            }
                    case 103 -> manager.printing_doctor_list();
                    case 104 -> manager.saving_doctor_data_to_file();
                    case 105 -> manager.loading_doctor_data();
                    case 106 -> manager.booking_a_doctor(scanner);
                    case 107 -> manager.canceling_a_consultation(scanner);
                    case 108 -> manager.displaying_gui();
                    case 109 -> manager.saving_consultation_data_to_file();
                    case 110 -> manager.loading_consultation_data();
                    case 111 -> manager.saving_patient_data_to_file();
                    case 112 -> manager.loading_patient_data();
                    case 113 -> System.out.println("Thank youuuu!!!");
                    default -> System.out.println("Wrong choice...Try again!!!");
                }
            } while (choice!=113);
        }
        catch (InputMismatchException mismatchException)
        {
            System.out.println("\nCheck your choice again");
            main(args);
        }
    }
}
