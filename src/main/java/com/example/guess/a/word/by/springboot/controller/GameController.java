package com.example.guess.a.word.by.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guess.a.word.by.springboot.service.GameService;
import com.example.guess.a.word.by.springboot.utils.GameUtils;



@Controller
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@Autowired
	GameUtils gameUtils;
	
//	@ResponseBody
	@GetMapping("/game-home")
	public String showGameHomePage(@RequestParam(value = "guessedChar", required = false) String guessedChar, ModelMap modelMap)
	{
		System.out.println("Guessed word is: " + guessedChar);
		
		String randomWord=gameService.toString();
	
		
		if(guessedChar!=null){
			//System.out.println("character fetched from the oth index of the captured word: "+ guessedChar.charAt(0));
			boolean isGuessCorrect=gameService.addGuess(guessedChar.charAt(0));
			randomWord=gameService.toString();
			if(isGuessCorrect==false)
			{
				gameUtils.reduceTry();
			}
		}
		System.out.println("number of tries remaining: "+ gameUtils.getTriesRemaining());
		
		modelMap.addAttribute("wordToDisplay", randomWord);
		modelMap.addAttribute("triesLeft",gameUtils.getTriesRemaining());
		
		return "game-home-page";
	}
	
	@GetMapping("/reload")
	public String reloadGame()
	{
		gameService= gameUtils.reload();
		return "redirect:/game-home";
	}

}
