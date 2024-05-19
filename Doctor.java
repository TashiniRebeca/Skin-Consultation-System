import java.time.LocalDate;
import java.util.ArrayList;

public class Doctor extends Person
{
    private String medicalLicenceNo;
    private Specialisations specialisation;

    public Doctor(){}

    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNo, String medicalLicenceNo, Specialisations specialisation)
    {
        super(name, surname,dateOfBirth,mobileNo);
        this.medicalLicenceNo = medicalLicenceNo;
        this.specialisation = specialisation;
    }

    public String getMedicalLicenceNo() { return medicalLicenceNo; }

    public void setMedicalLicenceNo(String medicalLicenceNo) {
        this.medicalLicenceNo = medicalLicenceNo;
    }

    public Specialisations getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisations specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Getting the Medical License Numbers of the doctors in the system
     * @return String[]
     */
    public String[] getMedicalNos()
    {
        ArrayList<Doctor> doctorArrayList = WestminsterSkinConsultationManager.doctors;
        int i=1;
        String[] doctors = new String[doctorArrayList.size()+1];
        doctors[0] = "";
        for (Doctor d : doctorArrayList)
        {
            doctors[i] = d.getMedicalLicenceNo();
            i++;
        }
        return doctors;
    }

}
