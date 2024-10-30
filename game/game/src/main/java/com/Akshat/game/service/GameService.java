package com.Akshat.game.service;
import com.Akshat.game.model.Game;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class GameService {
    public void loadHighScore(Game game) {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                game.setHighScore(Integer.parseInt(line));
                game.setHighScoreUser(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println("Highscore file not found. Starting a new high score.");
        }
    }

    public String playRound(Game game, int userChoice) {
        int computerChoice = (int) (Math.random() * 3) + 1;  // 1 = Rock, 2 = Paper, 3 = Scissors
        game.setCurrentRound(game.getCurrentRound() + 1);

        String result;
        if (userChoice == computerChoice) {
            result = "It's a tie!";
        } else if ((userChoice == 1 && computerChoice == 3) ||
                   (userChoice == 2 && computerChoice == 1) ||
                   (userChoice == 3 && computerChoice == 2)) {
            game.setUserScore(game.getUserScore() + 1);
            result = "You win this round!";
        } else {
            game.setComputerScore(game.getComputerScore() + 1);
            result = "Computer wins this round!";
        }
        return result;
    }

    public boolean checkHighScore(Game game, String username) {
        if (game.getUserScore() > game.getHighScore()) {
            game.setHighScore(game.getUserScore());
            game.setHighScoreUser(username);
            saveHighScore(game);
            return true;
        }
        return false;
    }

    private void saveHighScore(Game game) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(game.getHighScore()));
            writer.newLine();
            writer.write(game.getUsername());
        } catch (IOException e) {
            System.out.println("Error saving the new high score.");
        }
    }
}
