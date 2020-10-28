import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {
    private final ReceiverHandler receiverHandler;

    public GUI() {
        setTitle("CP372 Assignment 2 App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 375, 450);
        setResizable(false);
        receiverHandler = new ReceiverHandler();
        initComponents();
    }

    private void btnReceiveHandler(ActionEvent e) {
        System.out.println("Button clicked!");
    }

    private void checkboxReliableHandler(ActionEvent e) {
        System.out.println("Checkbox clicked!");
    }

    private void initComponents() {
        panelParent = new JPanel();
        panelParent.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelParent.setLayout(new BorderLayout(0, 0));
        setContentPane(panelParent);

        panelHeader = new JPanel();
        panelParent.add(panelHeader, BorderLayout.NORTH);
        panelHeader.setLayout(new BorderLayout(0, 0));

        lblNames = new JLabel("Mandeep Sran & Matthew Paek");
        lblNames.setFont(new Font("Serif", Font.BOLD, 12));
        lblNames.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblNames, BorderLayout.SOUTH);

        lblTitle = new JLabel("CP372: Assignment 2");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelHeader.add(lblTitle, BorderLayout.CENTER);

        panelMain = new JPanel();
        panelParent.add(panelMain, BorderLayout.CENTER);
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        panelSenderAddress = new JPanel();
        panelSenderAddress.setBorder(new EmptyBorder(10, 15, 10, 15));
        panelMain.add(panelSenderAddress);
        panelSenderAddress.setLayout(new BorderLayout(0, 0));

        lblSenderAddress = new JLabel("Sender Address:");
        lblSenderAddress.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelSenderAddress.add(lblSenderAddress, BorderLayout.WEST);

        txtSenderAddress = new JTextField();
        panelSenderAddress.add(txtSenderAddress, BorderLayout.SOUTH);
        txtSenderAddress.setColumns(10);

        panelSenderPort = new JPanel();
        panelSenderPort.setBorder(new EmptyBorder(5, 15, 5, 15));
        panelMain.add(panelSenderPort);
        panelSenderPort.setLayout(new BorderLayout(0, 0));

        lblSenderPort = new JLabel("Sender Port:");
        lblSenderPort.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelSenderPort.add(lblSenderPort, BorderLayout.WEST);

        txtSenderPort = new JTextField();
        txtSenderPort.setColumns(10);
        panelSenderPort.add(txtSenderPort, BorderLayout.SOUTH);

        panelReceiverPort = new JPanel();
        panelReceiverPort.setBorder(new EmptyBorder(5, 15, 5, 15));
        panelMain.add(panelReceiverPort);
        panelReceiverPort.setLayout(new BorderLayout(0, 0));

        lblReceiverPort = new JLabel("Receiver Port:");
        lblReceiverPort.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelReceiverPort.add(lblReceiverPort, BorderLayout.WEST);

        txtReceiverPort = new JTextField();
        txtReceiverPort.setColumns(10);
        panelReceiverPort.add(txtReceiverPort, BorderLayout.SOUTH);

        panelOutputFileName = new JPanel();
        panelOutputFileName.setBorder(new EmptyBorder(5, 15, 20, 15));
        panelMain.add(panelOutputFileName);
        panelOutputFileName.setLayout(new BorderLayout(0, 0));

        lblOutputFileName = new JLabel("Output File Name:");
        lblOutputFileName.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelOutputFileName.add(lblOutputFileName, BorderLayout.WEST);

        txtOutputFileName = new JTextField();
        txtOutputFileName.setColumns(10);
        panelOutputFileName.add(txtOutputFileName, BorderLayout.SOUTH);

        panelOtherComponents = new JPanel();
        panelOtherComponents.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelMain.add(panelOtherComponents);
        panelOtherComponents.setLayout(new BorderLayout(0, 0));

        checkboxReliable = new JCheckBox("Reliable");
        checkboxReliable.addActionListener(this::checkboxReliableHandler);
        panelOtherComponents.add(checkboxReliable, BorderLayout.CENTER);

        buttonReceive = new JButton("RECEIVE");
        buttonReceive.addActionListener(this::btnReceiveHandler);
        panelOtherComponents.add(buttonReceive, BorderLayout.EAST);

        panelInfo = new JPanel();
        panelMain.add(panelInfo);

        lblInfo = new JLabel("Received in-order packets:");
        panelInfo.add(lblInfo);

        lblPacketsReceived = new JLabel("0");
        panelInfo.add(lblPacketsReceived);

        setVisible(true);
    }

    JPanel panelParent;
    JPanel panelHeader;
    JLabel lblNames;
    JLabel lblTitle;
    JPanel panelMain;
    JPanel panelSenderAddress;
    JLabel lblSenderAddress;
    JTextField txtSenderAddress;
    JPanel panelSenderPort;
    JLabel lblSenderPort;
    JTextField txtSenderPort;
    JPanel panelReceiverPort;
    JLabel lblReceiverPort;
    JTextField txtReceiverPort;
    JPanel panelOutputFileName;
    JLabel lblOutputFileName;
    JTextField txtOutputFileName;
    JPanel panelOtherComponents;
    JCheckBox checkboxReliable;
    JButton buttonReceive;
    JPanel panelInfo;
    JLabel lblInfo;
    JLabel lblPacketsReceived;
   
}