/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *Unit tests for the message class
 * @author Botlhale motshwanedi
 */
public class MessageTest {
    
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
    
    
}
