/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchatapp;

import java.util.Scanner;

/**
 *The QuickChatApp class is the main entry point for the application.
 * It handles registration and login flow for the user
 * @author Botlhale Motshwanedi
 */
public class QuickChatApp {
    
    /**
     * Main method - entry point of the app.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        //Declarations
            Scanner myInputs = new Scanner(System.in);
            Login login = new Login();
            
            //Registration process
            System.out.println("======================================================");
            System.out.println("QuickChat Registration");
            System.out.println("======================================================");
            
            //Get first name
            System.out.println("Please enter your first name: ");
            String firstName = myInputs.nextLine();
            login.setFirstName(firstName);
            
            //Get last name
            System.out.println("Please enter last name: ");
            String lastName = myInputs.nextLine();
            login.setLastName(lastName);
            
            //Get username
            System.out.println("Please enter username (must contain '_' and be <= 5 chars): ");
            String username = myInputs.nextLine();
            login.setUsername(username);
            
            //Get password
            System.out.println("Please enter password (>= 8 chars, 1 capital letter, 1 number, 1 special char): ");
            String password = myInputs.nextLine();
            login.setPassword(password);
            
            //Get cell phone number
            System.out.println("Please enter SA cell phone number (e.g., +27838968976): ");
            String cellNumber = myInputs.nextLine();
            login.setCellPhoneNumber(cellNumber);
            
            //Register user and display result
            System.out.println("======================================================");
            System.out.println("Registration Result");
            System.out.println("======================================================");
            String registrationResult = login.registerUser();
            System.out.println(registrationResult);
            
            //If registration failed, exit
            if (!registrationResult.contains("successfully")){
                System.out.println("\nRegistration failed. Please try again.");
                
                myInputs.close();
                return;
            }
            
            //Login Process
            System.out.println("======================================================");
            System.out.println("QuickChat Login");
            System.out.println("======================================================");
            System.out.println("Enter your username: ");
            String loginUsername = myInputs.nextLine();
            System.out.println("Enter your password: ");
            String loginPassword = myInputs.nextLine();
            
            //Verify login
            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            String loginMessage = login.returnLoginStatus(loginSuccess);
            
            System.out.println("======================================================");
            System.out.println("Login Result");
            System.out.println("======================================================");
            System.out.println(loginMessage);
            
            //Part 2: Messaging feature
            if (!loginSuccess){
                myInputs.close();
                return;
            }
            System.out.println("======================================================");
            System.out.println("    Welcome to QuickChat.");
            System.out.println("======================================================");
            
            System.out.println("How many messages do you wish to enter? ");
            Message msg = new Message();
            msg.loadStoredMessages();
            
            boolean keepRunning = true;
            
            while (keepRunning){
                System.out.println("\n --- MENU ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages");
                System.out.println("Choose an option: ");
                
                String choice = myInputs.nextLine().trim();
            
                if(choice.equals("1")){
                    sendFlow(myInputs, msg);
                }
                else if(choice.equals("2")){
                     System.out.println("Coming Soon.");
                }
                else if(choice.equals("3")){
                    keepRunning = false;
                     System.out.println("Goodbye");
                }
                else if(choice.equals("4")){
                    storedMenu(myInputs, msg);
                }
                else{
                     System.out.println("Invalid option.");
                }
            }
            myInputs.close();
    }
    
    //Helper method for sending messages
    private static void sendFlow(Scanner myInputs, Message msg){
         System.out.println("How many mesages do you wish to send?");
         int n = Integer.parseInt(myInputs.nextLine().trim());
         
         for(int i = 0; i < n; i++){
              System.out.println("Recipient: ");
              String r = myInputs.nextLine();
              System.out.println("Message: ");
              String t = myInputs.nextLine();
              
              if(t.length() > 250){
                   System.out.println("Please enter a message of less than 250 characters.");
                   i--;
                   continue;
              }
              
              
               System.out.println("1) Send 2) Disregard 3) Store");
               int c = Integer.parseInt(myInputs.nextLine().trim());
               
                System.out.println(msg.SentMessage(c));
                
                msg.addMessage(r,t, "Stored");
                
                if(c == 1){
                     System.out.println("Message ID: " + msg.getMessageID());
                     System.out.println("Message Hash: " + msg.getMessageHash());
                     System.out.println("Recipient: " + r);
                     System.out.println("Message: " + t);
                }
         }
          System.out.println("Total messages sent: " + msg.returnTotalMessages());
    }
    
    //Helper method for stored messagesmenu
    private static void storedMenu(Scanner myInputs, Message msg){
         System.out.println("\n--- Stored Messages Menu ---");
         System.out.println("a) Sender & recipient");
         System.out.println("b) Longest message");
         System.out.println("c) Search by message ID");
         System.out.println("d) Messages for recipient");
         System.out.println("e) Delete by hash");
         System.out.println("f) Full report");
         System.out.println("Option: ");
         
         String opt = myInputs.nextLine().trim().toLowerCase();
         
         if(opt.equals("a")){
              System.out.println(msg.displaySenderAndRecipient());
         }
         else if(opt.equals("b")){
              System.out.println("Longest: " + msg.displayLongestMessage());
         }
         else if(opt.equals("c")){
              System.out.println("Message ID: ");
              System.out.println(msg.searchByMessageID(myInputs.nextLine()));
         }
         else if(opt.equals("d")){
              System.out.println("Recipient: ");
               System.out.println(msg.searchByRecipient(myInputs.nextLine()));
         }
         else if(opt.equals("e")){
              System.out.println("Hash: ");
               System.out.println(msg.deleteByHash(myInputs.nextLine()));
         }
         else if(opt.equals("f")){
              System.out.println(msg.displayReport());
         }
         else{
              System.out.println("Invalid option");
         }
    }
        }

