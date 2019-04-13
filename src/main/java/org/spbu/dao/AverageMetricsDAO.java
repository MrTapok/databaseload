package org.spbu.dao;

public class AverageMetricsDAO {

    private double averageNameVowelCount;
    private double averageNameConsonantCount;
    private double averageNameSignCount;

    private double averageSurnameVowelCount;
    private double averageSurnameConsonantCount;
    private double averageSurnameSignCount;

    private double averagePatronymicVowelCount;
    private double averagePatronymicConsonantCount;
    private double averagePatronymicSignCount;

    public AverageMetricsDAO(double[] metrics){
        averageNameVowelCount = metrics[0];
        averageNameConsonantCount = metrics[1];
        averageNameSignCount = metrics[2];

        averageSurnameVowelCount = metrics[3];
        averageSurnameConsonantCount = metrics[4];
        averageSurnameSignCount = metrics[5];

        averagePatronymicVowelCount = metrics[6];
        averagePatronymicConsonantCount = metrics[7];
        averagePatronymicSignCount = metrics[8];
    }

    public AverageMetricsDAO(){

    }

    public double getAverageNameVowelCount() {
        return averageNameVowelCount;
    }

    public void setAverageNameVowelCount(double averageNameVowelCount) {
        this.averageNameVowelCount = averageNameVowelCount;
    }

    public double getAverageNameConsonantCount() {
        return averageNameConsonantCount;
    }

    public void setAverageNameConsonantCount(double averageNameConsonantCount) {
        this.averageNameConsonantCount = averageNameConsonantCount;
    }

    public double getAverageNameSignCount() {
        return averageNameSignCount;
    }

    public void setAverageNameSignCount(double averageNameSignsCount) {
        this.averageNameSignCount = averageNameSignsCount;
    }

    public double getAverageSurnameVowelCount() {
        return averageSurnameVowelCount;
    }

    public void setAverageSurnameVowelCount(double averageSurnameVowelCount) {
        this.averageSurnameVowelCount = averageSurnameVowelCount;
    }

    public double getAverageSurnameConsonantCount() {
        return averageSurnameConsonantCount;
    }

    public void setAverageSurnameConsonantCount(double averageSurnameConsonantCount) {
        this.averageSurnameConsonantCount = averageSurnameConsonantCount;
    }

    public double getAverageSurnameSignCount() {
        return averageSurnameSignCount;
    }

    public void setAverageSurnameSignCount(double averageSurnameSignsCount) {
        this.averageSurnameSignCount = averageSurnameSignsCount;
    }

    public double getAveragePatronymicVowelCount() {
        return averagePatronymicVowelCount;
    }

    public void setAveragePatronymicVowelCount(double averagePatronymicVowelCount) {
        this.averagePatronymicVowelCount = averagePatronymicVowelCount;
    }

    public double getAveragePatronymicConsonantCount() {
        return averagePatronymicConsonantCount;
    }

    public void setAveragePatronymicConsonantCount(double averagePatronymicConsonantCount) {
        this.averagePatronymicConsonantCount = averagePatronymicConsonantCount;
    }

    public double getAveragePatronymicSignCount() {
        return averagePatronymicSignCount;
    }

    public void setAveragePatronymicSignCount(double averagePatronymicSignsCount) {
        this.averagePatronymicSignCount = averagePatronymicSignsCount;
    }

    @Override
    public String toString(){
        return averageNameVowelCount + " " +
        averageNameConsonantCount+ " " +
        averageNameSignCount + " " +
        averageSurnameVowelCount + " " +
        averageSurnameConsonantCount + " " +
        averageSurnameSignCount + " " +
        averagePatronymicVowelCount + " " +
        averagePatronymicConsonantCount + " " +
        averagePatronymicSignCount;
    }
}
