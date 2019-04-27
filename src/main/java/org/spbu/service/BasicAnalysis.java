package org.spbu.service;

import java.util.ArrayList;

/**
 * Class containing methods for counting statistic from raw data
 */

public class BasicAnalysis {

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

    public static boolean multipleLetterContaining(String string){
        for (int i = 0; i < string.length()-2; i++) {
            if((string.charAt(i) == string.charAt(i+1))&&(string.charAt(i+2) == string.charAt(i+1))){
                return true;
            }
        }
        return false;
    }

    public static int[] getPercentiles(ArrayList<Integer> arrayList){
        int[] perc = new int[3];

        perc[0] = arrayList.get((arrayList.size()/4));
        perc[1] = arrayList.get((arrayList.size()/2));
        perc[2] = arrayList.get((arrayList.size()*3/4));

        return perc;
    }

    public static double[] boundCount(int[] percentiles){
        double iqr = percentiles[2] - percentiles[0];
        double[] answ = new double[2];
        answ[0] = percentiles[2] - iqr*1.5;
        answ[1] = percentiles[2] + iqr*1.5;

        return answ;
    }

    public static void intArrayToString(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean notInAlphabet(String string){

        String[] temp = new String[string.length()];

        for (int i = 0; i < string.length(); i++) {
            temp[i] = Character.toString(string.charAt(i));
        }

        for (int i = 0; i < string.length(); i++) {
            if (!(vowels.contains(temp[i]) | consonant.contains(temp[i]) | signs.contains(temp[i])))
            {
                return false;
            }
        }

        return true;
    }

    public static int vowelsInRow(String string){
        int count = 0;
        int answ = 0;
        boolean flag = false;
        for (int i = 0; i < string.length(); i++) {
            if((vowels.indexOf(string.charAt(i))>0)&(flag == false)){
                flag = true;
                count++;
            } else {
                if((vowels.indexOf(string.charAt(i))>0)&(flag == true)){
                    count++;
                }
                else {
                    if(!(vowels.indexOf(string.charAt(i))>0)&(flag == false)){
                        continue;
                    }
                    else {
                        if(!(vowels.indexOf(string.charAt(i))>0)&(flag == true)){
                            flag = false;
                            if(count>answ){
                                answ = count;
                            }
                            count = 0;
                        }
                    }
                }
            }
        }
        if(count>answ){
            answ = count;
        }
        return answ;
    }

    public static int consonantsInRow(String string){
        int count = 0;
        int answ = 0;
        boolean flag = false;
        for (int i = 0; i < string.length(); i++) {
            if((consonant.indexOf(string.charAt(i))>0)&(flag == false)){
                flag = true;
                count++;
            } else {
                if((consonant.indexOf(string.charAt(i))>0)&(flag == true)){
                    count++;
                }
                else {
                    if(!(consonant.indexOf(string.charAt(i))>0)&(flag == false)){
                        continue;
                    }
                    else {
                        if(!(consonant.indexOf(string.charAt(i))>0)&(flag == true)){
                            flag = false;
                            if(count>answ){
                                answ = count;
                            }
                            count = 0;
                        }
                    }
                }
            }
        }
        if(count>answ){
            answ = count;
        }
        return answ;
    }
}
