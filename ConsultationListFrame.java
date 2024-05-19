import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ConsultationListFrame extends JFrame
{
    String[] consultationColNames = new String[]
            {
                    "Consultation ID","Consultation Date","Start Time","End Time",
                    "Doctor's ID","Doctor's Name","Patient's Id","Patient's Name","Cost","Notes"
            };
    ArrayList<Consultation> consultationArrayList = WestminsterSkinConsultationManager.consultations;
    String[] consultData = new String[10];

    DefaultTableModel consultationTableModel = new DefaultTableModel(consultationColNames,0);
    JTable consultationInfoTable = new JTable(consultationTableModel);
    JScrollPane consultationScrollPane = new JScrollPane(consultationInfoTable);
    public ConsultationListFrame()
    {
        for(Consultation consultation:consultationArrayList)
        {
            consultData[0] = String.valueOf(consultation.generateConsultationId());
            consultData[1] = String.valueOf(consultation.getConsultDate());
            consultData[2] = String.valueOf(consultation.getConsultStartTime());
            consultData[3] = String.valueOf(consultation.getConsultEndTime());
            consultData[4] = consultation.getDoc().getMedicalLicenceNo();
            consultData[5] = consultation.getDoc().getName() + " " + consultation.getDoc().getSurname();
            consultData[6] = consultation.getPatient().getPatientId();
            consultData[7] = consultation.getPatient().getName().substring(0,1).toUpperCase() + consultation.getPatient()
                            .getName().substring(1) + " " + consultation.getPatient().getSurname().substring(0,1)
                            .toUpperCase() + consultation.getPatient().getSurname().substring(1);
            consultData[8] = "£" + consultation.getCost();
            consultData[9] = consultation.getNotes().substring(0,1).toUpperCase() + consultation.getNotes().substring(1);
            consultationTableModel.addRow(consultData);
        }

        MainGUIFrame.consultationListFrame.add(consultationScrollPane);
        consultationInfoTable.setAutoCreateRowSorter(true);
        consultationInfoTable.setRowHeight(50);
        consultationInfoTable.setBackground(new Color(98,173,235));
        consultationInfoTable.setFont(new Font("Arial", Font.BOLD, 13));

        MainGUIFrame.mainFrame.setVisible(true);
        MainGUIFrame.consultationListFrame.setBounds(300,100,1000,700);
        MainGUIFrame.consultationListFrame.setVisible(true);
        MainGUIFrame.consultationListFrame.setLocationRelativeTo(null);
        MainGUIFrame.consultationListFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }
}
