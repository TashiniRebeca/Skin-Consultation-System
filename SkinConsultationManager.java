import java.io.IOException;
import java.util.Scanner;

public interface SkinConsultationManager
{
    void adding_new_doctor(Scanner scanner);

    void deleting_doctor(String medicalLicenceNo);

    void printing_doctor_list();

    void saving_doctor_data_to_file() throws IOException;

    void loading_doctor_data() throws IOException;

    void booking_a_doctor(Scanner scanner);

    void canceling_a_consultation(Scanner scanner);

    void displaying_gui();

    void loading_consultation_data();

    void loading_patient_data();

    void saving_patient_data_to_file();

    void saving_consultation_data_to_file();
}
