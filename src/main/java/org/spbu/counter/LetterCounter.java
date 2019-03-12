package org.spbu.counter;

import com.sun.istack.internal.NotNull;

/**
 * Class containing methods for counting statistic from raw data
 */

public class LetterCounter {

    private static String vowels = "УЕЫАОЭЯИЮЁ";
    private static String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private static String signs = "ЬЪ-";

    public static int vowelCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(vowels.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    public static int consonantCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(consonant.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    public static int signCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(signs.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    public static boolean doubleLetterContaining(String string){
        for (int i = 0; i < string.length()-1; i++) {
            if(string.charAt(i) == string.charAt(i+1)){
                return true;
            }
        }
        return false;
    }



}
