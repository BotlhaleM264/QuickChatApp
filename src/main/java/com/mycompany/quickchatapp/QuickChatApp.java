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
            int numMessages = myInputs.nextInt();
            myInputs.nextLine(); //clear the scanner
            
            boolean keepRunning = true;
            
            while (keepRunning){
                System.out.println("\n --- MENU ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("Choose an option: ");
                int menuChoice = myInputs.nextInt();
                myInputs.nextLine(); //clear the scanner
                switch (menuChoice) {
                    case 1 -> {
                        //Loop to enter the amount of messages the user asked for
                        for (int i = 1; i <= numMessages; i++){
                            System.out.println("\n--- Messages " + i + " of " + numMessages + " ---");
                            System.out.println("Enter recipient cell phone number (+27...): ");
                            String recipient = myInputs.nextLine();
                            System.out.println("Enter message (max 250 chars): ");
                            String text = myInputs.nextLine();
                            
                            //Check message length
                            if (text.length() > 250){
                                System.out.println("Message esceeds 250 characters by " + (text.length() - 250) + ": please reduce the size.");
                                i--; //Go back one step so that the user can try again
                            }
                            else{
                                Message msg = new Message(i, recipient, text);
                                //Check number
                                String checkNum = msg.checkRecipientCell();
                                if (!checkNum.contains("successfully")){
                                    System.out.println(checkNum);
                                    i--; //Try again
                                }
                                else{
                                    //Show hahs and ID
                                    System.out.println("Message ID: " + msg.getMessageID());
                                    System.out.println("Message Hash: " + msg.getMessageHash());
                                    System.out.println("1) Send Message");
                                    System.out.println("2) Disregard Message");
                                    System.out.println("3) Store Message");
                                    System.out.println("Choice: ");
                                    int action = myInputs.nextInt();
                                    myInputs.nextLine(); //clear scanner
                                    
                                    //Call the method in message class
                                    String result = msg.SentMessage(action);
                                    System.out.println(result);
                                    
                                    //If they sent it, print the details
                                    if (action == 1) {
                                        System.out.println("\n--- Details ---");
                                        System.out.println(msg.printMessage());
                                    }
                                }
                            }
                        }       //Show total sent
                        System.out.println("\nTotal messages sent: " + Message.allMessages.size());
                    }
                    case 2 -> System.out.println("Coming Soon");
                    case 3 -> {
                        System.out.println("Goodbye!");
                        keepRunning = false;
                    }
                    default -> System.out.println("Invalid Option");
                }
            }
        }
        }

