/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatpoe2025;

/**
 *
 * @author lab_services_student
 */
import javax.swing.*;

public class QuickChatPOE2025 { // (Varsity college)
    public static void main(String[] args) {
        // Registration
        String registeredUsername = JOptionPane.showInputDialog("Register - Enter a new username:");
        String registeredPassword = JOptionPane.showInputDialog("Register - Enter a new password:");
        JOptionPane.showMessageDialog(null, "Registration successful! Please log in.");

        // Login
        boolean loggedIn = false; // (Varsity college)
        for (int i = 0; i < 3; i++) {
            String username = JOptionPane.showInputDialog("Login - Enter your username:");
            String password = JOptionPane.showInputDialog("Login - Enter your password:");
            if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                loggedIn = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Try again."); // (Varsity college)
            }
        }

        if (!loggedIn) { // (Varsity college)
            JOptionPane.showMessageDialog(null, "Too many failed attempts. Exiting...");
            return;
        }

        MessageManager manager = new MessageManager(); // (Varsity college)
        int msgCount = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));

        int menuOption; // (Varsity college)
        do {
            String menu = """
                Please select an option:
                1) Send Messages
                2) Show Recently Sent Messages
                3) Display Longest Message
                4) Search Message by ID
                5) Search Messages by Recipient
                6) Delete Message by Hash
                7) Display Report
                8) Quit""";
            menuOption = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (menuOption) { // (Varsity college)
                case 1 -> {
                    for (int i = 0; i < msgCount; i++) {
                        Message msg = new Message(i);
                        msg.captureMessageDetails();
                        String result = msg.sendMessageOption();
                        JOptionPane.showMessageDialog(null, result);
                        manager.addMessage(msg, result);
                        if (msg.wasSent()) {
                            JOptionPane.showMessageDialog(null, msg.printMessages());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Messages sent: " + manager.getSentMessages().size());
                }
                case 2 -> manager.displaySendersAndRecipients();
                case 3 -> JOptionPane.showMessageDialog(null, "Longest: " + manager.displayLongestMessage());
                case 4 -> {
                    String id = JOptionPane.showInputDialog("Enter message ID:");
                    JOptionPane.showMessageDialog(null, manager.searchByMessageID(id));
                }
                case 5 -> {
                    String rec = JOptionPane.showInputDialog("Enter recipient number:");
                    JOptionPane.showMessageDialog(null, String.join("\n", manager.searchByRecipient(rec)));
                }
                case 6 -> {
                    String hash = JOptionPane.showInputDialog("Enter message hash:");
                    JOptionPane.showMessageDialog(null, manager.deleteMessageByHash(hash));
                }
                case 7 -> JOptionPane.showMessageDialog(null, manager.generateReport());
                case 8 -> JOptionPane.showMessageDialog(null, "Goodbye!");
                default -> JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        } while (menuOption != 8); // (Varsity college)
    }
}
