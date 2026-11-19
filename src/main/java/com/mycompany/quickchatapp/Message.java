/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Files;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *The message class handles message creation, validation, and storage.
 * @author Botlhale Motshwanedi
 */
public class Message {
    //Declarations
    String messageID;
    private int messageNumber = 0;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag;
    
    //Part 3 parallel arrays
    final List<String> sentMessages = new ArrayList<>();
    private final List<String> disregardedMessages = new ArrayList<>();
    private final List<String> storedMessages = new ArrayList<>();
    final List<String> messageHashes = new ArrayList<>();
    final List<String> messageIDs = new ArrayList<>();
    
    
    public Message(){}
    
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
    public int returnTotalMessages(){
        return sentMessages.size();
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
            flag = "Sent";
            return "Message successfully sent.";
        }
        else if (choice == 2){
            flag = "Disregard";
            return "Press 0 to delete the message.";
        }
        else if (choice == 3){
            flag = "Stored";
            storeMessage();
            return "Message successfully stored";
        }
        else{
            return "Invalid option";
        }
    }
    
    //Part 3 - main population Method
    public void addMessage(String recipient, String text, String flag){
        this.recipient = recipient;
        this.messageText = text;
        this.flag = flag;
        this.messageID = generateMessageID();
        this.messageNumber++;
        this.messageHash = createMessageHash();
        
        messageIDs.add(messageID);
        messageHashes.add(messageHash);
        
        if(flag.equals("Sent")){
            sentMessages.add(text);
        }
        else if(flag.equals("Stored")){
            storedMessages.add(text);
            storeMessage();
        }
        else if(flag.equals("Disregard")){
            disregardedMessages.add(text);
        }
    }
    
    //JSON File handling
    public String storeMessage(){
        try{
            JSONArray arr = new JSONArray();
            File file = new File("stored_messages.json");
            if(file.exists()){
                String content = new String(Files.readAllBytes(file.toPath()));
                arr = new JSONArray(content);     
            }
            
            JSONObject obj = new JSONObject();
            obj.put("messageID", messageID);
            obj.put("recipient", recipient);
            obj.put("message", messageText);
            obj.put("flag", flag);
            
            arr.put(obj);
            Files.write(file.toPath(), arr.toString(2).getBytes());
            return "Message successfully stored.";  
        }
        catch(IOException | JSONException e){
            return "Error storing message:" + e.getMessage();
        }
    }
   
    public void loadStoredMessages(){
        storedMessages.clear();
        try{
            File file = new File("stored_messages.json");
            if(!file.exists()){
                return;
            }
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONArray arr = new JSONArray(content);
            for(int i = 0; i < arr.length(); i++){
                JSONObject obj = arr.getJSONObject(i);
                storedMessages.add(obj.getString("message"));
                messageIDs.add(obj.getString("messageID"));
                messageHashes.add(obj.getString("hash"));
            }
        }
        catch(IOException | JSONException e){
        }
    }
         
    //Part 3 required features from a to f
    //a, Sender & recipient of all stored messages
    public String displaySenderAndRecipient(){
        if(storedMessages.isEmpty()){
            return "No stored messages.";
        }
         
            StringBuilder sb = new StringBuilder("Sender\tRecipient\n");
            for(String m : storedMessages){
                sb.append("Me\t").append(findRecipientFor(m)).append("\n");
            }

        return sb.toString();
    }
    
    //b, Longest stored message
    public String displayLongestMessage(){
        if(storedMessages.isEmpty()){
            return "No stored messages.";
        }
        return storedMessages.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }
    
    //c, Search by message ID
    public String searchByMessageID(String id){
        int idx = messageIDs.indexOf(id);
        if(idx == -1){
            return "Message ID not found.";
        }
        //Search all arrays to find the message
        String text = "";
        if(idx < storedMessages.size()){
            text = storedMessages.get(idx);
        }
        else if(idx < sentMessages.size()){
            text = sentMessages.get(idx);
        }
        else{
            text = "Message found in other array";
        }
        return "Recipient: " + findRecipientFor(text) + "\nMessage: " + text;
    }
    
    //d, Search for all messages for a particular recipient
    public String searchByRecipient(String recipient){
        StringBuilder sb = new StringBuilder();
        for(String m : storedMessages){
            if(recipient.equals((findRecipientFor(m)))){
                sb.append(m).append("\n");
            }
        }
        for(String m : sentMessages){
            if(recipient.equals(findRecipientFor(m))){
                sb.append(m).append("\n");
            }
        }
        if(sb.length() == 0){
            return "No messages for that recipient.";
        }
        return sb.toString().trim();
    }
    
    //e, Delete a message using the hash
    public String deleteByHash(String hash){
        int idx = messageHashes.indexOf(hash);
        if(idx == -1){
            return "Message hash not found.";
        }
        String removed = "";
        if(idx < storedMessages.size()){
            removed = storedMessages.remove(idx);
        }
        else if(idx < sentMessages.size()){
            removed = sentMessages.remove(idx);
        }
        messageHashes.remove(idx);
        messageIDs.remove(idx);
        return "Message: \"" + removed + "\" successfully deleted.";
    }
    
    //f, Display full report
    public String displayReport(){
        StringBuilder sb = new StringBuilder("======= STORED MESSAGE REPORT ====\n");
        for(int i = 0; i < storedMessages.size(); i++){
            String h = (i < messageHashes.size()) ? messageHashes.get(i) : "N/A";
            sb.append("Message Hash: ").append(h).append("\n");
            sb.append("Recipient: ").append(findRecipientFor(storedMessages.get(i))).append("\n");
            sb.append("Message: ").append(storedMessages.get(i)).append("\n--------------\n");
        }
        return sb.toString();
    }
    //Helper method to find the recipient of a specific message text
    private String findRecipientFor(String text){
        try{
            File file = new File("stored_messages.json");
            if(file.exists()){
                JSONArray arr = new JSONArray(new String(Files.readAllBytes(file.toPath())));
                for(int i = 0; i < arr.length(); i++){
                    JSONObject o = arr.getJSONObject(i);
                    if(o.getString("message").equals(text)){
                    return o.getString("recipient");
                }
                }
            }
        }
        catch(IOException | JSONException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        return recipient != null ? recipient : "Unknown";
    }
            }
