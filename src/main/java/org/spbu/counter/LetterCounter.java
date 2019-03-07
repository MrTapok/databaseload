package org.spbu.counter;

/**
 * Class containing methods for counting statistic from raw data
 */

public class LetterCounter {

    private String vowels = "УЕЫАОЭЯИЮЁ";
    private String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private String signs = "ЬЪ-";

    public int vowelCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(vowels.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    public int consonantCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(consonant.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

    public int signCount(String string){
        int output = 0;
        for (int i = 0; i < string.length(); i++) {
            if(signs.indexOf(string.charAt(i))>0){
                output++;
            }
        }
        return output;
    }

}
