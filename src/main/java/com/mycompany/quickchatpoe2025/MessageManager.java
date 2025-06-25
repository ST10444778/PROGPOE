/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatpoe2025;

/**
 *
 * @author lab_services_student
 */
import java.util.*;

public class MessageManager {
    private final ArrayList<Message> sentMessages = new ArrayList<>(); // (Varsity college)
    private final ArrayList<Message> disregardedMessages = new ArrayList<>();// (Varsity college)
    private final ArrayList<Message> storedMessages = new ArrayList<>();// (Varsity college)
    private final ArrayList<String> messageHashes = new ArrayList<>();
    private final ArrayList<String> messageIDs = new ArrayList<>();// (Varsity college)

    public void addMessage(Message msg, String result) {
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());

        if (result.contains("successfully sent")) {// (Varsity college)
            sentMessages.add(msg);
        } else if (result.contains("stored")) {
            storedMessages.add(msg);
        } else if (result.contains("delete")) {
            disregardedMessages.add(msg);
        }
    }

    public void displaySendersAndRecipients() { // (Varsity college)
        for (Message msg : sentMessages) {
            System.out.println("From: Developer | To: " + msg.getRecipient()); // (Varsity college)
        }
    }

    public String displayLongestMessage() { // (Varsity college)
        Message longest = null;
        for (Message msg : sentMessages) {
            if (longest == null || msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        return longest != null ? longest.getMessageText() : "No sent messages.";
    }

    public String searchByMessageID(String id) { // (Varsity college)
        for (Message msg : sentMessages) {
            if (msg.getMessageID().equals(id)) {
                return "To: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText(); // (Varsity college)
            }
        }
        return "Message ID not found.";
    }

    public List<String> searchByRecipient(String recipient)  { // (Varsity college)
        List<String> results = new ArrayList<>();
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) results.add(msg.getMessageText()); // (Varsity college)
        }
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) results.add(msg.getMessageText());
        }
        return results;
    }

    public String deleteMessageByHash(String hash) { // (Varsity college)
        Iterator<Message> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getMessageHash().equals(hash)) {
                iterator.remove();
                return "Message \"" + msg.getMessageText() + "\" successfully deleted.";
            }
        }
        return "Message not found."; // (Varsity college)
    }

    public String generateReport() { // (Varsity college)
        StringBuilder report = new StringBuilder("==== Sent Messages Report ====\n");
        for (Message msg : sentMessages) {
            report.append(msg.printMessages()).append("\n----------------------\n");
        }
        return report.toString();
    }

    public ArrayList<Message> getSentMessages() { // (Varsity college)
        return sentMessages;
    }
}
