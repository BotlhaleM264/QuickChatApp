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
        }
    }
