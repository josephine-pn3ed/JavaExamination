/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexamination;

/**
 *
 * @author morrejo_sd2023
 */
class Check {
    public static boolean isString(String input) {
        try {
            int number = Integer.parseInt(input);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }
}
