/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.util.regex.Pattern;

/**
 * Part 1: Registration and Login feature
 * The login class handles user registration and authentication.
 * It validates the usernames, password, and the SA cell phone numbers.
 * @author Botlhale Motshwanedi
 */
public class Login {
    //Declarations
    //Stores the registered username
    private String username = "";
    //Stores the registered password
    private String password = "";
    //Stores the registered cellphone number
    private String cellPhoneNumber = "";
    //Stores the user's first name
    private String firstName = "";
    //Stores the user's last name
    private String lastName = "";
    
    //Setter Methods
    
    /**
     * Sets the username.
     * @param user The username to set
     */
    public void setUsername(String user){
        username = user;
    }
    
    /**
     * Sets the password.
     * @param pass The password to set
     */
    public void setPassword(String pass){
        password = pass;
    }
    
    /**
     * Sets the cell phone number.
     * @param cell The cell phone number to set
     */
    public void setCellPhoneNumber(String cell){
        cellPhoneNumber = cell;
    }
    
    /**
     * Sets the user's first name.
     * @param first The first name to set
     */
    public void setFirstName(String first){
        firstName = first;
    }
    
    /**
     * Sets the user's last name.
     * @param last The last name to set
     */
    public void setLastName(String last){
        lastName = last;
    }
    
    //Validation Methods
    
    /**
     * Checks if username contains an underscore and is not more than 5 characters long.
     * @return true if username is correctly formatted, false otherwise.
     */
    public boolean checkUserName(){
        //Username must contain '_' and be <= 5 characters
        boolean hasUnderScore = username.contains("_");
        boolean isCorrectLength = username.length() <= 5;
        return hasUnderScore && isCorrectLength;
    }
    
    /**
     * Checks if password meets complexity requirements:
     * - At least 8 characters long
     * - Contains a capital letter
     * - contains a number
     * - contains a special character
     * @return 
     * @reurn true if password meets requirements, false otherwise.
     */
    public boolean checkPasswordComplexity(){
        //Checks length (atleast 8 characters long)
        if (password.length()< 8){
            return false;
        }
        //Checks for captal letter
        boolean hasCapital = false;
        //Checks for number
        boolean hasNumber = false;
        //Checks for special character
        boolean hasSpecial = false;
        
        //Loop through each chracter in the password
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if (Character.isUpperCase(c)){
                hasCapital = true;
            }
            else if (Character.isDigit(c)){
                hasNumber = true;
            }
            else if (!Character.isLetterOrDigit(c)){
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }
    
    /**
     * Checks if the cell phone number is correctly formatted.
     * Must contain international country code (+27) and be no more than 10 characters.
     * Reference: Oracle (2024) 'Pattern (Java Platform SE 8)', Available at: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
     * @return true if cell phone number is correctly formatted, false otherwise.
     */
    public boolean checkCellPhoneNumber(){
        //SA international code is +27
        //Format: +27 followed by 9 digits
        String regex = "\\+27[0-9]{7,9}$";
        return Pattern.matches(regex, cellPhoneNumber);
    }
    
    //Registration Method
    
    /**
     * Registers the user by validating all fields.
     * Returns the appropriate messages based on the validation results.
     * @return 
     * @returns Registration status message
     */
    public String registerUser(){
        //Check username
        if (!checkUserName()){
            return "Username is not correctly formatted, please ensure that your username" 
                    + "contains an underscore and is no more than five characters in length."; 
        }
        //Check password
        if (!checkPasswordComplexity()){
            return "Password is not correctly formatted, please ensure that the password" 
                    + "contains atleast eight characters, a capital letter, a number" 
                    + "and a special character.";
        }
        //Check cell phone number
        if (!checkCellPhoneNumber()){
            return "Cell phone number is not correctly formatted or does not contain international code.";
        }
        //All validatoions passed
        return """
               Username successfully captured.
               Password successfully captured.
               Cell phone number successfully added.
               User registered successfully.""";
    }
    
    //Login Methods
    
    /**
     * Verifies that the login detail match the registered details.
     * @param enteredUsername The username entered during login
     * @param enteredPassword The password entered during login
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String enteredUsername, String enteredPassword){
        return username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }
    
    /**
     * Returns the login status message on authentication result.
     * @param loginSuccess Whether login was successful
     * @return Login status message
     */
    public String returnLoginStatus(boolean loginSuccess){
        if (loginSuccess){
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return null;
    }
}
