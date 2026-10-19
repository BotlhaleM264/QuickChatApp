/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 *The message class handles message creation, validation, and storage.
 * @author Botlhale Motshwanedi
 */
public class Message {
    //Declarations
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    //Static variables to keep track of totals
    private static int totalMessagesSent = 0;
    public static ArrayList<Message> allMessages = new ArrayList<>();
    
    //Constuctor
    public Message(int messageNumber, String recipient, String messageText){
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
                }
    
    //Generate a random 10-digit message id
    private String generateMessageID(){
        String timeStr = String.valueOf(System.currentTimeMillis());
        return timeStr.substring(timeStr.length() - 10);
    }
    
    //Getters
    public String getMessageID(){
        return messageID;
    }
    public int getMessageNumber(){
        return messageNumber;
    }
    public String getRecipient(){
        return recipient;
    }
    public String getMessageText(){
        return messageText;
    }
    public String getMessageHash(){
        return messageHash;
    }
    
    //Validation methods
    public boolean checkMessageID(){
        return messageID.length() <= 10;
    }
    public String checkRecipientCell(){
        //regex to check for +27 followed by 7 to 9 didgitd
        if (recipient.startsWith("+27") && recipient.length() >= 10 && recipient.length() <= 12){
            return "Cellphone number successfully captured.";
        }
        else{
            return "Cell phone is incorrectly formatted or does not contain an international code. Please try again.";
        }
    }
    
    //Generate the Meassage hash
    public String createMessageHash(){
        //Extract first two digits of ID
        String firstTwoID = messageID.substring(0, 2);
        //Extract first and last words of the message
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        
        //Format : 00:0:HITHANKS
        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    //Option selection for message
    public String SentMessage(int choice){
        if (choice == 1){
            totalMessagesSent++;
            allMessages.add(this);
            return "Message successfully sent.";
        }
        else if (choice == 2){
            return "Press 0 to delete the message.";
        }
        else if (choice == 3){
            storeMessage();
            return "Message successfully stored";
        }
        else{
            return "Invalid option";
        }
    }
    
    //Stores message to a text file
    public void storeMessage(){
        try{
            FileWriter file = new FileWriter("messages.txt", true);
            file.write("ID:" + messageID + " | Hash: " + messageHash + " | To: " + recipient + " | Msg: " + messageText + "\n");
            file.close();
        }
        catch(IOException e){
            System.out.println("An error occured while saving the message.");
        }
    }
    
    //Prints details of a single message
    public String printMessage(){
        return "Message ID: " + messageID + "\n" + "Message Hash: " + messageHash + "\n" + "Recipient: " + 
                recipient + "\n" + "Message: " + messageText;
    }
    
    //Returns total messages sent
    public static int returnTotalMessages(){
        return totalMessagesSent;
    }
    }
