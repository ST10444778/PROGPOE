/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatpoe2025;

/**
 *
 * @author lab_services_student
 */
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest { // (Varsity college)

    @Test
    public void testAddSentMessage() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg = new Message(0);
        msg.recipient = "+27838884567";
        msg.messageText = "It is dinner time!";
        msg.createMessageHash();
        mgr.addMessage(msg, "Message successfully sent.");

        assertEquals(1, mgr.getSentMessages().size());
    }

    @Test
    public void testLongestMessage() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg1 = new Message(0);
        msg1.recipient = "+1";
        msg1.messageText = "Short.";
        msg1.createMessageHash();

        Message msg2 = new Message(1); // (Varsity college)
        msg2.recipient = "+2";
        msg2.messageText = "Where are you? You are late! I have asked you to be on time.";
        msg2.createMessageHash();

        mgr.addMessage(msg1, "Message successfully sent.");
        mgr.addMessage(msg2, "Message successfully sent.");

        assertEquals(msg2.getMessageText(), mgr.displayLongestMessage()); // (Varsity college)
    }

    @Test
    public void testSearchByID() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg = new Message(0);
        msg.recipient = "+27834484567";
        msg.messageText = "Yohoooo, I am at your gate.";
        msg.createMessageHash();
        mgr.addMessage(msg, "Message successfully sent.");

        assertTrue(mgr.searchByMessageID(msg.getMessageID()).contains("Yohoooo"));
    }

    @Test
    public void testSearchByRecipient() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg1 = new Message(0);
        msg1.recipient = "+27838884567";
        msg1.messageText = "Where are you?";
        msg1.createMessageHash();

        Message msg2 = new Message(1); // (Varsity college)
        msg2.recipient = "+27838884567";
        msg2.messageText = "Ok, I am leaving.";
        msg2.createMessageHash();

        mgr.addMessage(msg1, "Message successfully sent.");
        mgr.addMessage(msg2, "Message successfully stored.");

        List<String> results = mgr.searchByRecipient("+27838884567");
        assertEquals(2, results.size());
    }

    @Test
    public void testDeleteByHash() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg = new Message(0);
        msg.recipient = "+27838884567";
        msg.messageText = "Where are you?";
        msg.createMessageHash();

        mgr.addMessage(msg, "Message successfully stored."); // (Varsity college)
        String deleted = mgr.deleteMessageByHash(msg.getMessageHash());

        assertTrue(deleted.contains("successfully deleted"));
    }

    @Test
    public void testGenerateReport() { // (Varsity college)
        MessageManager mgr = new MessageManager();
        Message msg = new Message(0);
        msg.recipient = "+27834557896";
        msg.messageText = "Did you get the cake?";
        msg.createMessageHash();
        mgr.addMessage(msg, "Message successfully sent."); // (Varsity college)

        String report = mgr.generateReport();
        assertTrue(report.contains("Did you get the cake?"));
    }
}