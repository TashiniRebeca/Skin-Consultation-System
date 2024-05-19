import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class AddPatientFrame extends JFrame
{
    static JPanel panel1 = new JPanel();
    static JFrame patientFrame = new JFrame("Add Patient Details");

    String[] dates = new String[]
            {
                    "01", "02", "03", "04", "05","06", "07", "08", "09", "10","11", "12", "13", "14","15","16", "17",
                    "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30","31"
            };
    String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] years = new String[]
            {
                    "1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008",
                    "2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022"
            };

    JLabel patientName = new JLabel("First Name: ");
    JLabel patientSurname = new JLabel("Last Name: ");
    JLabel patientMobileNo = new JLabel("Mobile Number: ");
    JLabel patientDOB = new JLabel("Date Of Birth:");
    JLabel patientId = new JLabel("Patient ID:");
    JTextField txtName = new JTextField(18);
    JTextField txtSurname = new JTextField(18);
    JTextField txtMobileNo = new JTextField();
    JComboBox<String> comboDates = new JComboBox<>(dates);
    JComboBox<String> comboMonths = new JComboBox<>(months);
    JComboBox<String> comboYears = new JComboBox<>(years);
    JTextField txtPatientId = new JTextField();
    JLabel patientDetailsLbl = new JLabel("Add Patient Details");
    JButton addPatientBtn = new JButton("Add");
    JButton clearBtn = new JButton("Clear");
    public AddPatientFrame()
    {
        Container container = patientFrame.getContentPane();
        container.setLayout(null);
        patientFrame.add(panel1);
        container.setBackground(new Color(171,205,239));

        patientName.setSize(100,20);
        patientName.setLocation(100,100);

        patientSurname.setSize(100,20);
        patientSurname.setLocation(450,100);

        patientMobileNo.setSize(100,20);
        patientMobileNo.setLocation(100,150);

        patientDOB.setSize(110,20);
        patientDOB.setLocation(450,150);

        patientId.setSize(110,20);
        patientId.setLocation(100,200);

        txtName.setSize(200,20);
        txtName.setLocation(200,100);
        txtName.setToolTipText("Enter your first name here");

        txtSurname.setSize(200,20);
        txtSurname.setLocation(550,100);
        txtSurname.setToolTipText("Enter your last name here");

        txtMobileNo.setSize(90,20);
        txtMobileNo.setLocation(200,150);
        txtMobileNo.setToolTipText("Enter your phone number here");

        comboDates.setSize(50,20);
        comboDates.setLocation(550,150);

        comboMonths.setSize(50,20);
        comboMonths.setLocation(600,150);

        comboYears.setSize(70,20);
        comboYears.setLocation(650,150);

        txtPatientId.setSize(100,20);
        txtPatientId.setLocation(200,200);
        txtPatientId.setToolTipText("Enter your ID here");

        ConsultationFrame.patientId = txtPatientId.getText();

        patientDetailsLbl.setFont(new Font("Arial", Font.BOLD, 30));
        patientDetailsLbl.setSize(350, 30);
        patientDetailsLbl.setLocation(300, 30);

        addPatientBtn.setLocation(300, 250);
        addPatientBtn.setBackground(new Color(100,149,237));
        addPatientBtn.setSize(100, 30);
        addPatientBtn.setForeground(new Color(0,11,142));
        addPatientBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearBtn.setLocation(450, 250);
        clearBtn.setBackground(new Color(100,149,237));
        clearBtn.setSize(100, 30);
        clearBtn.setForeground(new Color(0,11,142));
        clearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        container.add(patientDetailsLbl);
        container.add(patientName);
        container.add(txtName);
        container.add(patientSurname);
        container.add(txtSurname);
        container.add(patientMobileNo);
        container.add(txtMobileNo);
        container.add(patientDOB);
        container.add(comboDates);
        container.add(comboMonths);
        container.add(comboYears);
        container.add(patientId);
        container.add(txtPatientId);
        container.add(addPatientBtn);
        container.add(clearBtn);

        ActionHandler actionHandler = new ActionHandler();

        addPatientBtn.addActionListener(actionHandler);
        clearBtn.addActionListener(actionHandler);

        patientFrame.setBounds(300,100,850,400);
        patientFrame.setVisible(true);
        patientFrame.setLocationRelativeTo(null);
        patientFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private class ActionHandler extends Component implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == clearBtn)
            {
                String noData= "";
                txtName.setText(noData);
                txtSurname.setText(noData);
                txtMobileNo.setText(noData);
                txtPatientId.setText(noData);
                comboDates.setSelectedIndex(0);
                comboMonths.setSelectedIndex(0);
                comboYears.setSelectedIndex(0);
            }
            if (e.getSource() == addPatientBtn)
            {
                if (txtName.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null,"Name is required!");
                }
                else if (txtSurname.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null,"SurName is required!");
                }
                else if (txtMobileNo.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null,"Mobile Number is required!");
                }
                else if (txtMobileNo.getText().length() != 10)
                {
                    JOptionPane.showMessageDialog(null,"Mobile Number should be in 10 digits!");
                }
                else if (txtPatientId.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null,"Id is required!");
                }
                else if (txtPatientId.getText().length() != 5)
                {
                    JOptionPane.showMessageDialog(null,"Id should be in 5 digits!");
                }
                else
                {
                    int ques = JOptionPane.showConfirmDialog(this,"Are you sure to add this patient?",
                            "Adding Patient Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (ques == JOptionPane.YES_OPTION)
                    {
                        for (Doctor doc: WestminsterSkinConsultationManager.doctors)
                        {
                            if (Objects.equals(doc.getMedicalLicenceNo(), ConsultationFrame.licenseNo))
                            {
                                boolean isAvailable = false;
                                ConsultationFrame.consultation.setDoc(doc);
                                for (Patient patient:WestminsterSkinConsultationManager.patients)
                                {
                                    if (Objects.equals(patient.getPatientId(), txtPatientId.getText()))
                                    {
                                        isAvailable = true;
                                        ConsultationFrame.consultation.setPatient(patient);
                                        JOptionPane.showMessageDialog(null,"Patient Id has assigned before");
                                        break;
                                    }
                                }
                                if (!isAvailable)
                                {
                                    Patient focusPatient = new Patient(txtName.getText(),txtSurname.getText()
                                            ,LocalDate.parse(comboYears.getSelectedItem() + "-" +
                                            comboMonths.getSelectedItem() + "-" + comboDates.getSelectedItem()),
                                            txtMobileNo.getText(),txtPatientId.getText(),doc);
                                    WestminsterSkinConsultationManager.patients.add(focusPatient);
                                    ConsultationFrame.consultation.setPatient(focusPatient);
                                    break;
                                }
                            }
                        }
                        patientFrame.setVisible(false);
                    }
                }
            }
        }
    }

}
