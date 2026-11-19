/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 *Unit tests for the message class
 * @author Botlhale motshwanedi
 */
public class MessageTest {
    
    private Message msg;
    
    @Test
    public void testMessageLenghtSuccess(){
        //Test a message under 250 chracters
        String text = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(text.length() <= 250);
    }
    
    @Test
    public void testMessageLengthFailure(){
        //Generate a string longer than 250 characters
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 260; i++){
            longText.append("a");
        }
        assertFalse(longText.length() <= 250);
    }
    
    @Test
    public void testRecipientCellCorrect(){
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone successfully captured.", msg.checkRecipientCell());
    }
    
    @Test
    public void testCreateMessageHash(){
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        //Hash should contain the message number "0" and first/ last words "HITONIGHT"
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.contains("HI TONIGHT"));
    }
    
    @Test
    public void testCheckMessageID(){
        Message msg = new Message(1, "+27718693002", "Test");
        assertTrue(msg.checkMessageID());
    }
    
    @Test
    public void testSentMessage(){
        Message msg = new Message(1,"+27718693002", "Test message");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
    
    @BeforeEach
    public void setUp(){
        msg = new Message();
        //Populate exactly as per the PoE Test Data 1-5
        msg.addMessage("+27834557896", "Did you get the cake?", "Sent");
        msg.addMessage("+27838884567", "Where are you? You are late!I have asked you to be on time.", "Stored");
        msg.addMessage("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        msg.addMessage("0838884567", "It is dinner time!", "Sent");
        msg.addMessage("+27838884567", "Ok, I am leaving without you.", "Stored");
    }
    @Test
    public void testSentMessageArrayPopulated(){
        List<String> sent = msg.sentMessages;
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0));
        assertEquals("It is dinner time!", sent.get(1));
    }
    
    @Test
    public void testDisplayLongestMessage(){
        assertEquals("Where are you? You are late! I have asked you to be on time.", msg.displayLongestMessage());
    }
    
    @Test
    public void testSearchForMessageID(){
        String id = msg.messageIDs.get(3); //Id for "It is dinner time!"
        String result = msg.searchByMessageID(id);
        assertTrue(result.contains("It is dinner time!"));
    }
    
    @Test
    public void testSearchByRecipient(){
        String result = msg.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    @Test
    public void testDeleteMessageUsingHash(){
        String hash = msg.messageHashes.get(1); //The hash for message 2
        String result = msg.deleteByHash(hash);
        assertEquals("Message: \"Where are you? You arec late! I have asked you to be on time.\" successfully deleted.", result);
    }
    
    @Test
    public void testDisplayReport(){
        String report = msg.displayReport();
        assertTrue(report.contains("Message Hash: "));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Ok, I am leaving without you."));
    }
}
