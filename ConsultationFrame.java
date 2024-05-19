import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public class ConsultationFrame extends JFrame
{

    static Consultation consultation = new Consultation();
    static JPanel panel1 = new JPanel();
    Doctor doc = new Doctor();
    String[] dates = new String[]
    {
        "01", "02", "03", "04", "05","06", "07", "08", "09", "10","11", "12", "13", "14","15","16", "17",
        "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30","31"
    };
    String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] consultYears = new String[]{"2022","2023"};
    String[] time = new String[]
            {
                    "09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00",
                    "19:00","20:00"
            };

    String[] docMedicalNos = doc.getMedicalNos();
    JComboBox<String> comboConsultDates = new JComboBox<>(dates);
    JComboBox<String> comboConsultMonths = new JComboBox<>(months);
    JComboBox<String> comboConsultYears = new JComboBox<>(consultYears);
    JComboBox<String> comboStartTime = new JComboBox<>(time);
    JComboBox<String> comboEndTime = new JComboBox<>(time);
    JComboBox<String> comboDocMedicalNos = new JComboBox<>(docMedicalNos);

    JLabel consultationDateLbl = new JLabel("Consultation Date:");
    JLabel consultTimeLbl = new JLabel("Consultation Time: ");
    JLabel toLbl = new JLabel("To ");
    JLabel checkAvailabilityLbl = new JLabel("Check Availability");
    JLabel doctorIdLbl = new JLabel("Doctor ID:");
    JLabel addImageNoteLbl = new JLabel("Add Note or Image");

    JButton checkBtn = new JButton("Check");
    JButton clearBtn = new JButton("Clear");
    JButton addPatientBtn = new JButton("Add Patient");
    static String patientId;
    static String licenseNo;
    ButtonGroup btnGroup = new ButtonGroup();
    JRadioButton noteRadioBtn = new JRadioButton("Note");
    JRadioButton imageRadioBtn = new JRadioButton("Image");

    JTextArea txtNote = new JTextArea();
    JButton btnAddConsultation = new JButton("Add Consultation");
    JButton resetBtn = new JButton("Reset");
    JLabel costLbl = new JLabel("Consultation Cost: ");
    JTextField txtCost = new JTextField();
    JLabel consultationLbl = new JLabel("Consultation Id: ");
    JTextField txtConsultationIds = new JTextField();

    public ConsultationFrame()
    {
        Container container = MainGUIFrame.addConsultationFrame.getContentPane();
        container.setLayout(null);
        MainGUIFrame.addConsultationFrame.add(panel1);
        container.setBackground(new Color(171,205,239));

        consultationDateLbl.setFont(new Font("Arial",Font.BOLD,13));
        consultationDateLbl.setSize(120,20);
        consultationDateLbl.setLocation(155,70);

        comboConsultDates.setSize(50,20);
        comboConsultDates.setLocation(300,70);

        comboConsultMonths.setSize(50,20);
        comboConsultMonths.setLocation(350,70);

        comboConsultYears.setSize(70,20);
        comboConsultYears.setLocation(400,70);

        consultTimeLbl.setFont(new Font("Arial",Font.BOLD,13));
        consultTimeLbl.setSize(120,20);
        consultTimeLbl.setLocation(155,125);

        comboStartTime.setSize(70,20);
        comboStartTime.setLocation(275,125);

        toLbl.setSize(50,20);
        toLbl.setLocation(360,125);

        comboEndTime.setSize(70,20);
        comboEndTime.setLocation(400,125);

        checkAvailabilityLbl.setFont(new Font("Arial", Font.BOLD, 18));
        checkAvailabilityLbl.setForeground(new Color(0,11,142));
        checkAvailabilityLbl.setSize(180,20);
        checkAvailabilityLbl.setLocation(250,10);

        doctorIdLbl.setSize(100,20);
        doctorIdLbl.setFont(new Font("Arial",Font.BOLD,13));
        doctorIdLbl.setLocation(210,180);

        comboDocMedicalNos.setSize(100,20);
        comboDocMedicalNos.setLocation(315,180);

        checkBtn.setFont(new Font("Arial", Font.BOLD, 15));
        checkBtn.setBackground(new Color(100,149,237));
        checkBtn.setForeground(new Color(0,11,142));
        checkBtn.setSize(100,30);
        checkBtn.setLocation(210,240);
        checkBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clearBtn.setFont(new Font("Arial", Font.BOLD, 15));
        clearBtn.setBackground(new Color(100,149,237));
        clearBtn.setForeground(new Color(0,11,142));
        clearBtn.setSize(100,30);
        clearBtn.setLocation(320,240);
        clearBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addPatientBtn.setVisible(false);
        addPatientBtn.setFont(new Font("Arial", Font.BOLD, 15));
        addPatientBtn.setBackground(new Color(100,149,237));
        addPatientBtn.setForeground(new Color(0,11,142));
        addPatientBtn.setLocation(240,310);
        addPatientBtn.setSize(150,30);
        addPatientBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addImageNoteLbl.setFont(new Font("Arial", Font.BOLD, 18));
        addImageNoteLbl.setForeground(new Color(0,11,142));
        addImageNoteLbl.setSize(250,20);
        addImageNoteLbl.setLocation(250,400);

        noteRadioBtn.setSize(60,20);
        noteRadioBtn.setLocation(230,450);

        imageRadioBtn.setSize(60,20);
        imageRadioBtn.setLocation(350,450);

        btnGroup.add(noteRadioBtn);
        btnGroup.add(imageRadioBtn);

        txtNote.setToolTipText("Enter your notes here");
        txtNote.setSize(150,80);
        txtNote.setLocation(450,450);
        txtNote.setVisible(false);

        btnAddConsultation.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddConsultation.setForeground(new Color(0,11,142));
        btnAddConsultation.setBackground(new Color(134,191,255));
        btnAddConsultation.setSize(180,40);
        btnAddConsultation.setLocation(150,550);
        btnAddConsultation.setCursor(new Cursor(Cursor.HAND_CURSOR));

        resetBtn.setFont(new Font("Arial", Font.BOLD, 14));
        resetBtn.setForeground(new Color(0,11,142));
        resetBtn.setBackground(new Color(134,191,255));
        resetBtn.setSize(150,40);
        resetBtn.setLocation(350,550);
        resetBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        costLbl.setFont(new Font("Arial", Font.BOLD, 13));
        costLbl.setSize(150,20);
        costLbl.setLocation(200,350);
        container.add(costLbl);

        txtCost.setSize(70,20);
        txtCost.setLocation(350,350);
        container.add(txtCost);

        consultationLbl.setFont(new Font("Arial", Font.BOLD, 13));
        consultationLbl.setSize(150,20);
        consultationLbl.setLocation(200,300);
        container.add(consultationLbl);

        txtConsultationIds.setSize(100,20);
        txtConsultationIds.setLocation(350,300);
        container.add(txtConsultationIds);

        addImageNoteLbl.setVisible(false);
        noteRadioBtn.setVisible(false);
        imageRadioBtn.setVisible(false);
        btnAddConsultation.setVisible(false);
        resetBtn.setVisible(false);
        costLbl.setVisible(false);
        txtCost.setVisible(false);
        consultationLbl.setVisible(false);
        txtConsultationIds.setVisible(false);

        container.add(consultationDateLbl);
        container.add(comboConsultDates);
        container.add(comboConsultMonths);
        container.add(comboConsultYears);
        container.add(consultTimeLbl);
        container.add(comboStartTime);
        container.add(toLbl);
        container.add(comboEndTime);
        container.add(comboDocMedicalNos);
        container.add(checkAvailabilityLbl);
        container.add(doctorIdLbl);
        container.add(checkBtn);
        container.add(clearBtn);
        container.add(addImageNoteLbl);
        container.add(noteRadioBtn);
        container.add(imageRadioBtn);
        container.add(txtNote);
        container.add(btnAddConsultation);
        container.add(resetBtn);
        container.add(addPatientBtn);

        txtConsultationIds.setText(consultation.generateConsultationId());

        checkBtn.addActionListener(listener->
        {
            LocalDate consultationDate = LocalDate.parse(comboConsultYears.getSelectedItem() + "-"
                    + comboConsultMonths.getSelectedItem() + "-" + comboConsultDates.getSelectedItem());
            String docMedicalNo = (String) comboDocMedicalNos.getSelectedItem();
            LocalTime consultStartTime = LocalTime.parse((CharSequence) Objects.requireNonNull(comboStartTime.getSelectedItem()));
            LocalTime consultEndTime = LocalTime.parse((CharSequence) Objects.requireNonNull(comboEndTime.getSelectedItem()));

            if (comboDocMedicalNos.getSelectedItem() == "")
            {
                JOptionPane.showMessageDialog(null,"Select a Doctor Id!");
            }
            else if (comboStartTime.getSelectedItem() == comboEndTime.getSelectedItem() || LocalTime.parse((CharSequence)
                    Objects.requireNonNull(comboStartTime.getSelectedItem())).isAfter(LocalTime.parse((CharSequence)
                    Objects.requireNonNull(comboEndTime.getSelectedItem()))))
            {
                JOptionPane.showMessageDialog(null,"Check your Consultation time slot again");
            }
            else if (checkDoctorAvailability(docMedicalNo,consultationDate,consultStartTime,consultEndTime))
            {
                addPatientBtn.setVisible(true);
                addPatientBtn.addActionListener(e ->
                {
                    licenseNo = (String) comboDocMedicalNos.getSelectedItem();
                    addPatientBtn.setVisible(false);
                    new AddPatientFrame();
                    addImageNoteLbl.setVisible(true);
                    noteRadioBtn.setVisible(true);
                    imageRadioBtn.setVisible(true);
                    btnAddConsultation.setVisible(true);
                    resetBtn.setVisible(true);
                    costLbl.setVisible(true);
                    txtCost.setVisible(true);
                    consultationLbl.setVisible(true);
                    txtConsultationIds.setVisible(true);
                });
            }
            else
            {
                JOptionPane.showMessageDialog(null,"This doctor is not available currently");
                while (true)
                {
                    Random randomSelection = new Random();
                    int no = randomSelection.nextInt(0,WestminsterSkinConsultationManager.doctors.size());
                    if (checkDoctorAvailability(WestminsterSkinConsultationManager.doctors.get(no).getMedicalLicenceNo(),
                            consultationDate,consultStartTime,consultEndTime))
                    {
                        JOptionPane.showMessageDialog(null,"The Doctor with ID " +
                        WestminsterSkinConsultationManager.doctors.get(no).getMedicalLicenceNo() + " (Dr." +
                        WestminsterSkinConsultationManager.doctors.get(no).getName() + ") will be assigned for your consultation!");
                        addPatientBtn.setVisible(true);
                        licenseNo = WestminsterSkinConsultationManager.doctors.get(no).getMedicalLicenceNo();
                        addPatientBtn.addActionListener(e ->
                        {
                            addPatientBtn.setVisible(false);
                            new AddPatientFrame();
                            addImageNoteLbl.setVisible(true);
                            noteRadioBtn.setVisible(true);
                            imageRadioBtn.setVisible(true);
                            btnAddConsultation.setVisible(true);
                            resetBtn.setVisible(true);
                            costLbl.setVisible(true);
                            txtCost.setVisible(true);
                            consultationLbl.setVisible(true);
                            txtConsultationIds.setVisible(true);
                        });
                        break;
                    }
                }
            }
        });

        ActionHandler actionHandler = new ActionHandler();

        clearBtn.addActionListener(actionHandler);

        resetBtn.addActionListener(actionHandler);

        btnAddConsultation.addActionListener(actionHandler);

        imageRadioBtn.addActionListener(actionHandler);

        noteRadioBtn.addActionListener(actionHandler);

        MainGUIFrame.addConsultationFrame.setBounds(450,100,700,678);
        MainGUIFrame.addConsultationFrame.setVisible(true);
        MainGUIFrame.addConsultationFrame.setResizable(false);
        MainGUIFrame.addConsultationFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == clearBtn)
            {
                comboConsultDates.setSelectedIndex(0);
                comboConsultMonths.setSelectedIndex(0);
                comboConsultYears.setSelectedIndex(0);
                comboStartTime.setSelectedIndex(0);
                comboEndTime.setSelectedIndex(0);
                comboDocMedicalNos.setSelectedIndex(0);
            }
            if (e.getSource() == resetBtn)
            {
                String noValue = "";
                txtConsultationIds.setText(noValue);
                txtCost.setText(noValue);
                noteRadioBtn.setSelected(false);
                imageRadioBtn.setSelected(false);
            }
            if (e.getSource() == btnAddConsultation)
            {
                if (!noteRadioBtn.isSelected() && !imageRadioBtn.isSelected())
                {
                    JOptionPane.showMessageDialog(null,"Note/Image is required");
                }
                else if (txtNote.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(null,"Note cannot be empty");
                }
                else
                {
                    txtCost.setText(String.valueOf(getConsultationCost(patientId,LocalTime.parse((CharSequence) Objects.requireNonNull(comboStartTime.getSelectedItem())),LocalTime.parse((CharSequence) Objects.requireNonNull(comboEndTime.getSelectedItem())))));
                    WestminsterSkinConsultationManager.consultations.add(new Consultation(LocalDate.parse(comboConsultYears.getSelectedItem() + "-"
                            + comboConsultMonths.getSelectedItem() + "-" + comboConsultDates.getSelectedItem()),
                            LocalTime.parse((CharSequence) Objects.requireNonNull(comboStartTime.getSelectedItem())),LocalTime.parse((CharSequence) Objects.requireNonNull(comboEndTime.getSelectedItem())),Double.parseDouble(txtCost.getText()),txtNote.getText(),consultation.getDoc(),consultation.getPatient()));
                    JOptionPane.showMessageDialog(null,"The Total Consultation Cost will be £" +
                            getConsultationCost(patientId,LocalTime.parse((CharSequence) Objects.requireNonNull(comboStartTime.getSelectedItem())),LocalTime.parse((CharSequence) Objects.requireNonNull(comboEndTime.getSelectedItem()))));
                    JOptionPane.showMessageDialog(null,"Consultation added successfully");
                    MainGUIFrame.addConsultationFrame.setVisible(false);
                }
            }
            if (e.getSource() == imageRadioBtn)
            {
                txtNote.setVisible(false);
                addImage();
            }
            if (e.getSource() == noteRadioBtn){txtNote.setVisible(true);}
        }
    }

    /**
     *
     * @param licenseNo The Medical License Number of the doctor
     * @param consultDate The date booked for the consultation
     * @param startTime Start Time of the Consultation
     * @param endTime End Time of the Consultation
     * @return boolean - Returns true when a doctor is available otherwise returns false
     */
    private boolean checkDoctorAvailability(String licenseNo,LocalDate consultDate,LocalTime startTime,LocalTime endTime)
    {
        if (WestminsterSkinConsultationManager.doctors.isEmpty())
        {
            return true;
        }
        else
        {
            for (Consultation consultation:WestminsterSkinConsultationManager.consultations)
            {
                if (consultation.getDoc().getMedicalLicenceNo().equals(licenseNo))
                {
                    if (consultation.getConsultDate().equals(consultDate))
                    {
                        return consultation.getConsultEndTime().isBefore(startTime) || consultation.getConsultEndTime() == startTime
                                || consultation.getConsultStartTime() == endTime || consultation.getConsultStartTime().isAfter(endTime);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Adding an image of the skin to the system
     */
    private void addImage()
    {
        JFileChooser imageFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = imageFile.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedImageFile = imageFile.getSelectedFile();
            JOptionPane.showMessageDialog(null,"Image got selected!");
        }
    }

    /**
     * Calculating the time duration of the consultation
     * @param startTime Start Time of the Consultation
     * @param endTime End Time of the Consultation
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
        for (Patient patient: WestminsterSkinConsultationManager.patients)
        {
            if (patient.getPatientId().equals(patientId))
            {
                return 25 * getConsultationHours(startTime,endTime);
            }
        }
        return 15 * getConsultationHours(startTime,endTime);
    }
}
