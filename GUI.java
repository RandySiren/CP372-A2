import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
        // Check if we are currently receiving or not based on button
        if (buttonReceive.getText().equals("RECEIVE")) {
            try {
                // Parse all JComponents into useful values
                String address = txtSenderAddress.getText();
                int senderPort = Integer.parseInt(txtSenderPort.getText());
                int receiverPort = Integer.parseInt(txtReceiverPort.getText());
                String outputFileName = txtOutputFileName.getText();
                boolean reliable = checkboxReliable.isSelected();

                // Background process to start receiving so we don't block our GUI and its repainting
                new SwingWorker<Void, Void>() {
                    @Override
                    public Void doInBackground() {
                        try {
                            receiverHandler.setInOrderPacketLabel(lblPacketsReceived); // Assign our JLabel to our ReceiverHandler to update it
                            receiverHandler.startReceiving(address, senderPort, receiverPort, outputFileName, reliable);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
                buttonReceive.setText("TERMINATE");
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Invalid port number(s), please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Stop receiving/disconnect
            receiverHandler.stopReceiving();
            buttonReceive.setText("RECEIVE");
        }
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
        txtSenderAddress.setText("127.0.0.1"); //TODO: REMOVE DEFAULT
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
        txtSenderPort.setText("3321"); //TODO: REMOVE DEFAULT
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
        txtReceiverPort.setText("4455"); //TODO: REMOVE DEFAULT
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
        txtOutputFileName.setText("received.txt"); //TODO: REMOVE DEFAULT
        txtOutputFileName.setColumns(10);
        panelOutputFileName.add(txtOutputFileName, BorderLayout.SOUTH);

        panelOtherComponents = new JPanel();
        panelOtherComponents.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelMain.add(panelOtherComponents);
        panelOtherComponents.setLayout(new BorderLayout(0, 0));

        checkboxReliable = new JCheckBox("Reliable");
        checkboxReliable.setSelected(true);
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