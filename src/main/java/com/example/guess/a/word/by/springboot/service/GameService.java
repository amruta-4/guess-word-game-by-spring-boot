package com.example.guess.a.word.by.springboot.service;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameService {
	
	private String randomlyChoosenWord;
	private char[] allCharactersOfWord;
	
	public String[] randomWords= {"father","mother","java","sister","brother","hello","world"};
	Random random=new Random();
	
	public GameService()
	{
		randomlyChoosenWord=randomWords[random.nextInt(randomWords.length)];
		System.out.println(randomlyChoosenWord);
		allCharactersOfWord=new char[randomlyChoosenWord.length()];
		
	}
	
	@Override
	public String toString() {
		
		String ret="";
		
		for(char c: allCharactersOfWord)
		{
			if(c=='\u0000')
			{
				ret=ret+"_";
			}
			else {
				ret=ret + c;
			}
			ret=ret+' ';
		}
		return ret;
	}

	public boolean addGuess(char guessedChar) {
		
		boolean isGuessCorrect=false;
		
		for(int i=0;i<randomlyChoosenWord.length();i++)
		{
			if(guessedChar==randomlyChoosenWord.charAt(i))
			{
				allCharactersOfWord[i]=guessedChar;
				isGuessCorrect=true;
			}
			
		}
		
		return isGuessCorrect;
		
		
	}
	

}
