import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUIFrame extends JFrame
{
    static JFrame mainFrame = new JFrame("Skin Consultation Management System");
    static JFrame doctorListFrame = new JFrame("View Doctors' List");
    static JFrame consultationListFrame = new JFrame("View Consultations List");
    static JFrame addConsultationFrame = new JFrame("Add Consultations");

    JButton addConsultationBtn = new JButton("Add Consultation");
    JButton doctorsListBtn = new JButton("View Doctor List");
    JButton viewConsultationsBtn = new JButton("View Consultations");
    JLabel title = new JLabel("Welcome to Skin Consultation Management System");
    public MainGUIFrame()
    {
        JPanel panel = new JPanel();
        mainFrame.add(panel);
        mainFrame.setResizable(false);

        panel.setLayout(new BorderLayout());
        ImageIcon img1 = new ImageIcon("C:\\Users\\Tashini\\Documents\\IIT-2ND YEAR-1ST SEMESTER\\" +
                "Object Oriented Programming\\Coursework\\Images\\pic17.jpg");
        JLabel background=new JLabel(img1);
        panel.add(background);

        GridBagLayout bagLayout = new GridBagLayout();
        background.setLayout(bagLayout);
        background.setBounds(300,100,700,600);
        background.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints bagConstraints = new GridBagConstraints();
        bagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        bagConstraints.anchor = GridBagConstraints.NORTH;
        bagConstraints.weighty = 2;

        title.setFont(new Font("Showcard Gothic",Font.PLAIN,28));
        title.setForeground(new Color(0,11,142));
        background.add(title,bagConstraints);

        bagConstraints.anchor = GridBagConstraints.CENTER;
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;

        addConsultationBtn.setForeground(Color.BLACK);
        background.add(addConsultationBtn, bagConstraints);
        background.add(doctorsListBtn, bagConstraints);
        bagConstraints.gridy = 8000;
        background.add(viewConsultationsBtn, bagConstraints);

        addConsultationBtn.setBounds(250,150,100,120);

        doctorsListBtn.setBackground(new Color(134,191,255));
        doctorsListBtn.setForeground(new Color(0,11,142));
        doctorsListBtn.setFont(new Font("Segoe UI Semi-bold",Font.BOLD,25));
        doctorsListBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addConsultationBtn.setBackground(new Color(134,191,255));
        addConsultationBtn.setForeground(new Color(0,11,142));
        addConsultationBtn.setFont(new Font("Segoe UI Semi-bold",Font.BOLD,25));
        addConsultationBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewConsultationsBtn.setBackground(new Color(134,191,255));
        viewConsultationsBtn.setForeground(new Color(0,11,142));
        viewConsultationsBtn.setFont(new Font("Segoe UI Semi-bold",Font.BOLD,25));
        viewConsultationsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ActionHandler actionHandler = new ActionHandler();
        doctorsListBtn.addActionListener(actionHandler);
        addConsultationBtn.addActionListener(actionHandler);
        viewConsultationsBtn.addActionListener(actionHandler);

        mainFrame.setBounds(300,100,900,400);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }
    private class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == doctorsListBtn) { new DoctorListFrame(); }
            if (e.getSource() == addConsultationBtn) { new ConsultationFrame(); }
            if (e.getSource() == viewConsultationsBtn) { new ConsultationListFrame(); }
        }
    }
}