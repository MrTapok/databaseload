package org.spbu.service;

public class FuzzySearch {

    private static int tripleMin(int i1, int i2, int i3){
        if((i1<=i2)&(i1<=i3)){
            return i1;
        }
        if((i2<=i1)&(i2<=i3)){
            return i2;
        }
        if((i3<=i2)&(i3<=i1)){
            return i3;
        }
        return 0;
    }

    public static double methodJaro(char[] ch1, String s2){
        int length1 = ch1.length;
        int length2 = s2.length();
        char[] ch2 = new char[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            ch2[i] = s2.charAt(i);
        }
        double m = 0;
        double t = 0;
        int match_range = (int)Math.floor(Math.max(length1, length2)/2) - 1;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if((ch1[i] == ch2[j])&(Math.abs(i-j)<match_range)){
                    m++;
                    if(i-j != 0){
                        t++;
                    }
                }
            }
        }
        if(m == 0){ return 1; }
        return(1 - ((1.0/3.0)*(m/(double)length1 + m/(double)length2 + (m-t)/m)));
    }

    public static double methodJaroWinkler(char[] ch1, String s2, double p){
        int length1 = ch1.length;
        int length2 = s2.length();
        char[] ch2 = new char[length2];
        for (int i = 0; i < length2; i++) {
            ch2[i] = s2.charAt(i);
        }
        double jaro_distance = methodJaro(ch1, s2);
        double l = 0;
        int pref_length = tripleMin(length1, length2, 4);
        for (int i = 0; i < pref_length; i++) {
            if(ch1[i] == ch2[i]){
                l++;
            }
            else{break;}
        }
        return jaro_distance + (l*p*(1.0 - jaro_distance));
    }

    public static int methodWagnerFisherL(char[] ch1, String s2){
        int length1 = ch1.length;
        int length2 = s2.length();
        char[] ch2 = new char[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            ch2[i] = s2.charAt(i);
        }
        int[][] D = new int[length1+1][length2+1];
        D[0][0] = 0;
        for (int i = 1; i < length2+1; i++) {
            D[0][i] = D[0][i-1] + 1;
        }
        for (int i = 1; i < length1+1; i++) {
            D[i][0] = D[i-1][0] + 1;
            for (int j = 1; j < length2+1; j++) {
                D[i][j] = tripleMin(
                        D[i - 1][j] + 1,
                        D[i][j - 1] + 1,
                        D[i - 1][j - 1] + ((ch1[i-1]!=ch2[j-1]) ? 1 : 0)
                );
            }
        }
        return D[length1][length2];
    }

    public static int methodWagnerFisherLTwoRows(char[] ch1, String s2){
        int length1 = ch1.length;
        int length2 = s2.length();
        char[] ch2 = new char[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            ch2[i] = s2.charAt(i);
        }
        int[] D0 = new int [length2+1];
        int[] D1 = new int [length2+1];
        int[] temp;
        for (int i = 0; i < length2+1; i++) {
            D0[i] = i;
        }
        for (int i = 1; i < length1+1; i++) {
            D1[0] = i;
            for (int j = 1; j < length2+1; j++) {
                D1[j] = tripleMin(D0[j]+1, D1[j-1]+1, (D0[j-1] + (ch1[i-1]!=ch2[j-1] ? 1 : 0)));
            }
            temp = D0;
            D0 = D1;
            D1 = temp;
        }
        return D0[length2];
    }

    public static int methodWagnerFisherDL(char[] ch1, String s2){
        int length1 = ch1.length;
        int length2 = s2.length();
        char[] ch2 = new char[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            ch2[i] = s2.charAt(i);
        }
        int[][] D = new int[length1+1][length2+1];
        D[0][0] = 0;
        for (int i = 1; i < length2+1; i++) {
            D[0][i] = D[0][i-1] + 1;
        }
        for (int i = 1; i < length1+1; i++) {
            D[i][0] = D[i-1][0] + 1;
            for (int j = 1; j < length2+1; j++) {
                D[i][j] = tripleMin(
                        D[i - 1][j] + 1,
                        D[i][j - 1] + 1,
                        D[i - 1][j - 1] + ((ch1[i - 1] != ch2[j - 1]) ? 1 : 0)
                );
                if(i>1 && j>1 && ch1[i-1] == ch2[j-2] && ch1[i-2] == ch2[j-1]){
                    D[i][j] = Math.min(D[i][j], D[i-2][j-2] + 1);
                }
            }
        }
        return D[length1][length2];
    }
}
