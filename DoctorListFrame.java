import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DoctorListFrame extends MainGUIFrame
{
    String[] doctorColNames = {"Licence Number","First Name","Last Name","Specialisation","DOB","Mobile Number"};
    ArrayList<Doctor> doctorArrayList = WestminsterSkinConsultationManager.doctors;
    DefaultTableModel docTableModel = new DefaultTableModel(doctorColNames,0);
    JTable docInfoTable = new JTable(docTableModel);
    JScrollPane docScrollPane = new JScrollPane(docInfoTable);
    String[] rows = new String[6];
    Panel panel1 = new Panel();
    JLabel docTitle = new JLabel("Doctors' List");
    public DoctorListFrame()
    {
        for(Doctor doctor:doctorArrayList)
        {
            rows[0] = String.valueOf(doctor.getMedicalLicenceNo());
            rows[1] = doctor.getName();
            rows[2] = doctor.getSurname();
            rows[3] = String.valueOf(doctor.getSpecialisation());
            rows[4] = String.valueOf(doctor.getDateOfBirth());
            rows[5] = doctor.getMobileNumber();
            docTableModel.addRow(rows);
        }

        docInfoTable.setBackground(new Color(98,173,235));
        docInfoTable.setFont(new Font("Arial", Font.BOLD, 13));
        docInfoTable.setAutoCreateRowSorter(true);
        docInfoTable.setRowHeight(50);

        MainGUIFrame.doctorListFrame.add(docScrollPane);

        docTitle.setFont(new Font("Arial", Font.BOLD, 30));
        docTitle.setSize(350, 30);
        panel1.add(docTitle);

        mainFrame.setVisible(true);
        doctorListFrame.setBounds(300,100,1000,550);
        doctorListFrame.setVisible(true);
        doctorListFrame.setLocationRelativeTo(null);
        doctorListFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }
}
