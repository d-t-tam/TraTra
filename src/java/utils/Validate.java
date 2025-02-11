/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author dtam6
 */
public class Validate {

    public static void validatePhoneNumber(String phoneNumber) throws Exception {
        String regex = "";

        if (phoneNumber.length() < 10 || phoneNumber.length() > 11) {
            throw new Exception("");
        }

        if (!phoneNumber.matches(regex)) {
            throw new Exception("");
        }
    }

}
