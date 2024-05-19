import java.time.LocalDate;

public class Patient extends Person
{
    private String patientId;

    Doctor doc;

    public Patient() {}

    public Patient(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String patientId,Doctor doctor)
    {
        super(name,surname,dateOfBirth,mobileNumber);
        this.patientId = patientId;
        doc = doctor;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}
