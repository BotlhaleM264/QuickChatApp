/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchatapp;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *Unit tests for the Login class.
 * Tests username, password, and call phone number validation.
 * @author Botlhale Motshwanedi
 */
public class LoginTest {
    //Username Tests
    
    /**
     * Test that a correctly formatted username returns true.
     * Test Data: "kyl_1" (contains underscore and no more than 5 characters
     */
    @Test
    public void testUsernameCorrectlyFormatted(){
        Login login = new Login();
        login.setUsername("kyl_1");
        
        assertTrue(login.checkUserName());
    }
    /**
     *Test that an incorrectly formatted username returns false.
     * Test Data: "kyle!!!!!!" (no underscore, more that 5 characters)
     */
    @Test
    public void testUsernameIncorrectFormatted(){
        Login login = new Login();
        login.setUsername("kyl!!!!!!");
        
        assertFalse(login.checkUserName());
    }
   
    //Password Tests
     
    /**
     * Test that a password meeting complexity requirements returns true
     * Test Data: "Ch&sec@ke99!"
     */
    @Test
    public void testPasswordMeetsComplexity(){
        Login login = new Login();
        login.setPassword("Ch&sec@ke99!");
        
        assertTrue(login.checkPasswordComplexity());
    }
    
    /**
     * Test that a password not meeting complexity requirements returns false.
     * Test Data: "password" 9no capital letter, no number, no special char)
     */
    @Test
    public void testPasswordDoesNotMeetComplexity(){
        Login login = new Login();
        login.setPassword("password");
        
        assertFalse(login.checkPasswordComplexity());
    }
    
    //Cell Phone Tests
    
    /**
     * Test that a correctly formatted cell phone number returns true.
     * Test data: +27838968976
     */
    @Test
    public void testCellPhoneCorrectFormatted(){
        Login login = new Login();
        login.setCellPhoneNumber("+27838968976");
        
        assertTrue(login.checkCellPhoneNumber());
    }
    
    /**
     * Test that an incorrectly formatted cell phone number returns false.
     * Test data: 08966553 (no international code)
     */
    @Test
    public void testCellPhoneIncorrectFormatted(){
        Login login = new Login();
        login.setCellPhoneNumber("08966553");
        
        assertFalse(login.checkCellPhoneNumber());
    }
    
    //Login Tests
    
    /**
     * Test that login succeeds with the correct credentials
     */
    @Test
    public void testLoginSuccessful(){
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&sec@ke99!");
        login.setFirstName("Kyle");
        login.setLastName("Smith");
        
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"));
    }
    
    /**
     * Test that login fails with incorrect credentials.
     */
    @Test
    public void testLoginFailed(){
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&sec@ke99!");
        
        assertFalse(login.loginUser("wrong_user", "wrong_pass"));
    }
    
    /**
     * Test the returnLoginStatus method for successful login.
     */
    @Test
    public void testReturnLoginStatusSuccess(){
        Login login = new Login();
        login.setFirstName("Kyle");
        login.setLastName("Smith");
        
        String expected = "Welcome Kyle Smith, it is great to see you again.";
        String actual = login.returnLoginStatus(true);
        
        assertEquals(expected, actual);
    }
    
    /**
     * Test the returnLoginStaus method for failed login
     */
    @Test
    public void testReturnLoginStatusFailure(){
        Login login = new Login();
        
        String expected = "Username or password incorrect, please try again.";
        String actual = login.returnLoginStatus(false);
        
        assertEquals(expected, actual);
    }
    
    //Registration Message Tests
    
    /**
     * Test registerUser returns success message when all inputs are valid
     */
    @Test
    public void testRegisterUserSuccess(){
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        
        String result = login.registerUser();
        
        assertTrue(result.contains("successfully"));
    }
    
    /**
     * Test registerUser returns error message for invalid username.
     */
    @Test
    public void testRegisterUserInvalidUsername(){
        Login login = new Login();
        login.setUsername("kyle!!!!!!");
        login.setPassword("Ch&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        
        String result = login.registerUser();
        
        assertTrue(result.contains("Username is not correctly formatted"));
    }
    
    /**
     * Test registerUser returns error message for invalid password.
     */
    @Test
    public void testRegisterUserInvalidPassword(){
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("password");
        login.setCellPhoneNumber("+27838968976");
        
        String result = login.registerUser();
        
        assertTrue(result.contains("Password is not correctly formatted"));
    }
    
    /**
     * Test registeUser returns error message for invalid call number.
     */
    @Test
    public void testRegisterUserInvalidCellNumber(){
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&sec@ke99!");
        login.setCellPhoneNumber("08966553");
        
        String result = login.registerUser();
        
        assertTrue(result.contains("Cell phone number incorrectly formatted"));
    }
}
