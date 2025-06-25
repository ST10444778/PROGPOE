/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatpoe2025;

import javax.swing.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class Message {
    public final String messageID;
    public final int messageNumber;
    public String recipient;
    public String messageText;
    private String messageHash;
    private boolean sent = false;

    public Message(int messageNumber) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
    }

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkRecipientCell(String number) {
        return number.matches("^\\+\\d{1,3}\\d{7,9}$");
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String first = words[0];
        String last = words[words.length - 1];
        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public void captureMessageDetails() {
        do {
            recipient = JOptionPane.showInputDialog("Enter recipient phone number (e.g. +27831234567):");
            if (!checkRecipientCell(recipient)) {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code.");
            }
        } while (!checkRecipientCell(recipient));
        JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");

        do {
            messageText = JOptionPane.showInputDialog("Enter your message (Max 250 characters):");
            if (messageText.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + (messageText.length() - 250));
            }
        } while (messageText.length() > 250);

        JOptionPane.showMessageDialog(null, "Message ready to send.");
        messageHash = createMessageHash();
    }

    public String sendMessageOption() {
        String[] options = {"Send Message", "Disregard Message", "Store Message"};
        int choice = JOptionPane.showOptionDialog(null, "Choose what to do with this message:", "Message Option",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> {
                sent = true;
                return "Message successfully sent.";
            }
            case 1 -> {
                return "Press 0 to delete message.";
            }
            case 2 -> {
                storeMessage();
                return "Message successfully stored.";
            }
            default -> {
                return "No valid choice selected.";
            }
        }
    }

    public void storeMessage() {
        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("messageHash", messageHash);
        json.put("recipient", recipient);
        json.put("message", messageText);

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(json.toJSONString() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    public int returnTotalMessages() {
        return sent ? 1 : 0;
    }

    public boolean wasSent() {
        return sent;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }
}
