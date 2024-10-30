package com.Akshat.game.model;

public class Game {
    private int rounds;
    private int userScore;
    private int computerScore;
    private int currentRound;
    private String highScoreUser = "No one";
    private int highScore = 0;
    private String username = "Noobie";

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public String getHighScoreUser() {
        return highScoreUser;
    }

    public void setHighScoreUser(String highScoreUser) {
        this.highScoreUser = highScoreUser;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
