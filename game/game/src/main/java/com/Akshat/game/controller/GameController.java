package com.Akshat.game.controller;

import com.Akshat.game.model.Game;
import com.Akshat.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("game")
public class GameController {
    @Autowired
    private GameService gameService;

    @ModelAttribute("game")
    public Game game() {
        return new Game();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/rules")
    public String rules() {
        return "rules";
    }

    @GetMapping("/highscore")
    public String highscore(@ModelAttribute("game") Game game, Model model) {
        gameService.loadHighScore(game);
        model.addAttribute("highscore", game.getHighScore());
        model.addAttribute("highScoreUser", game.getHighScoreUser());
        return "highscore";
    }

    // Step 1: New endpoint for entering username
    @GetMapping("/enterUsername")
    public String enterUsername() {
        return "enterUsername";
    }

    @PostMapping("/setUsername")
    public String setUsername(@ModelAttribute("game") Game game, @RequestParam("username") String username) {
        game.setUsername(username);
        return "redirect:/play"; // Redirect to the select rounds page
    }

    @GetMapping("/play")
    public String play(@ModelAttribute("game") Game game) {
        game.setCurrentRound(0);
        game.setUserScore(0);
        game.setComputerScore(0);
        return "play";
    }

    @PostMapping("/startGame")
    public String startGame(@ModelAttribute("game") Game game, @RequestParam("rounds") int rounds, Model model) {
        if (rounds < 1) {
            model.addAttribute("error", "Please select at least one round.");
            return "play";
        }
        game.setRounds(rounds);
        return "redirect:/game_play";
    }

    @GetMapping("/game_play")
    public String gamePlay(@ModelAttribute("game") Game game, Model model) {
        model.addAttribute("currentRound", game.getCurrentRound() + 1);
        return "game_play";
    }

    @PostMapping("/playRound")
    public String playRound(@ModelAttribute("game") Game game, @RequestParam("userChoice") int userChoice, Model model) {
        String result = gameService.playRound(game, userChoice);
        model.addAttribute("result", result);
        model.addAttribute("userScore", game.getUserScore());
        model.addAttribute("computerScore", game.getComputerScore());

        if (game.getCurrentRound() >= game.getRounds()) {
            boolean isHighScore = gameService.checkHighScore(game, game.getHighScoreUser());
            model.addAttribute("isHighScore", isHighScore);
            return "game_end";
        }

        return "game_play";
    }

    @GetMapping("/exit")
    public String exit() {
        return "redirect:/";
    }
}
