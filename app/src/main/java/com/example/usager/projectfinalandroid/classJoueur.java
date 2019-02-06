package com.example.usager.projectfinalandroid;

public class classJoueur {
    private int id;
    private int score;
    private int manuel;
    private int automatic;
    public classJoueur(){}
    public classJoueur(int id,int Score, int Manuel, int Automatic){
        this.score = Score;
        this.manuel = Manuel;
        this.automatic=Automatic;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getScore() {
        return score;
    }
    public int getManuel() {
        return manuel;
    }
    public int getAutomatic() {
        return automatic;
    }
    public void setScore(int Score) {
        this.score = Score;
    }
    public void setAutomatic(int Automatic) {
        this.automatic = Automatic;
    }
    public void setManuel(int Manuel) {
        this.manuel = Manuel;
    }
}
