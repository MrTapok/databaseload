package org.spbu.datageneration;

import java.util.Random;

public class ErrorMaker {
    private static String vowels = "УЕЫАОЭЯИЮЁ";
    private static String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private static String signs = "ЬЪ-";

    public static String deformString(String string){
        Random random = new Random();

        int randomInt;
        int randomInt2;
        randomInt = random.nextInt(2);
        randomInt2 = random.nextInt(2) + 1;
        String answer = "";

        if(randomInt == 0){
            String[] temp = new String[string.length()];
            int counter = randomInt2;

            for (int i = 0; i < string.length(); i++) {
                temp[i] = Character.toString(string.charAt(i));
            }

            for (int i = 0; i < string.length(); i++) {
                if(vowels.contains(temp[i])){
                    temp[i] = Character.toString(consonant.charAt(random.nextInt(consonant.length())));
                    counter--;
                }
                if(counter == 0){
                    break;
                }
            }

            for (int i = 0; i < string.length(); i++) {
                answer = answer + temp[i];
            }
        }

        else {
            String[] temp = new String[string.length()];
            int counter = randomInt2;

            for (int i = 0; i < string.length(); i++) {
                temp[i] = Character.toString(string.charAt(i));
            }

            for (int i = 0; i < string.length(); i++) {
                if(consonant.contains(temp[i])){
                    temp[i] = Character.toString(vowels.charAt(random.nextInt(vowels.length())));
                    counter--;
                }
                if(counter == 0){
                    break;
                }
            }

            for (int i = 0; i < string.length(); i++) {
                answer = answer + temp[i];
            }
        }

        return answer;
    }

    public static String randomString(){
        String answ = "";
        Random random = new Random();

        int length = random.nextInt(20) + 1;
        int temp = 0;

        String[] answarr = new String[length];

        for (int i = 0; i < length; i++) {
            temp = random.nextInt(8);
            if(temp == 0 | temp == 2 | temp == 4 | temp == 6){
                answarr[i] = Character.toString(vowels.charAt(random.nextInt(vowels.length())));
            }
            else{
                if(temp == 1 | temp == 3 | temp == 5 | temp == 7){
                    answarr[i] = Character.toString(consonant.charAt(random.nextInt(consonant.length())));
                }
                else {
                    answarr[i] = Character.toString(signs.charAt(random.nextInt(signs.length())));
                }
            }
        }

        for (int i = 0; i < length; i++) {
            answ = answ + answarr[i];
        }

        return answ;
    }
}
