import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable
{
    private Doctor doc;
    private Patient patient;
    private LocalDate consultDate;
    private LocalTime consultStartTime,consultEndTime;
    private double cost;
    private String notes;
    static int consultationId = 100;

    public Consultation() {}

    public Consultation(LocalDate consultDate, LocalTime consultStartTime, LocalTime consultEndTime, double cost, String notes,Doctor doctor,Patient patient)
    {
        this.consultDate = consultDate;
        this.consultStartTime = consultStartTime;
        this.consultEndTime = consultEndTime;
        this.cost = cost;
        this.notes = notes;
        doc = doctor;
        this.patient = patient;
    }

    public LocalDate getConsultDate() {
        return consultDate;
    }

    public LocalTime getConsultStartTime() {
        return consultStartTime;
    }

    public LocalTime getConsultEndTime() { return consultEndTime; }

    public double getCost() {
        return cost;
    }

    public String getNotes() {
        return notes;
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Generating an ID for a consultation when a consultation is booked
     * @return String Consultation Id
     */
    public String generateConsultationId()
    {
        return "" + consultationId++;
    }

}
