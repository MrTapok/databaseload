package org.spbu.calculation;

/**
 * Class containing methods for counting statistic from raw data
 */

public class LetterCounter {

    private static String vowels = "УЕЫАОЭЯИЮЁ";
    private static String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private static String signs = "ЬЪ-";

    /**
     * Method that count number of vowels in string
     * @param string name, surname or patronymic
     * @return number of russian vowels in string
     */
    public static int vowelCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(vowels.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    /**
     * Method that count number of consonants in string
     * @param string name, surname or patronymic
     * @return number of russian consonants in string
     */
    public static int consonantCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(consonant.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    /**
     * Method that count number of signs in string
     * @param string name, surname or patronymic
     * @return number of russian signs in string
     */
    public static int signCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(signs.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    /**
     * Method that tells, is in string double letter
     * @param string name, surname or patronymic
     * @return true if double letter is in string
     */
    public static boolean doubleLetterContaining(String string){
        for (int i = 0; i < string.length()-1; i++) {
            if(string.charAt(i) == string.charAt(i+1)){
                return true;
            }
        }
        return false;
    }



}
