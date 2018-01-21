package com.example.hw_1;

import java.io.Serializable;
import java.util.Random;


public class GameSession implements Serializable{

	public enum Action { PAPER, SCISSORS, ROCK }
	public enum Outcome { WIN, TIE, LOSS }
	
	private static final long serialVersionUID = 13;
	
	int winCnt = 0;	
	int lossCnt = 0;
	int tieCnt = 0;
	int gameCnt = 0;
	
	public int getWinCnt() {
		return winCnt;
	}

	public void setWinCnt(int winCnt) {
		this.winCnt = winCnt;
	}

	public int getLossCnt() {
		return lossCnt;
	}

	public void setLossCnt(int lossCnt) {
		this.lossCnt = lossCnt;
	}

	public int getTieCnt() {
		return tieCnt;
	}

	public void setTieCnt(int tieCnt) {
		this.tieCnt = tieCnt;
	}

	public int getGameCnt() {
		return gameCnt;
	}

	public void setGameCnt(int gameCnt) {
		this.gameCnt = gameCnt;
	}

	public GameSession() {
		
		
	}
	public GameSession(int gameCnt, int tieCnt, int lossCnt, int winCnt) {
		
		this.gameCnt = gameCnt;
		this.lossCnt = lossCnt;
		this.tieCnt = tieCnt;
		this.winCnt = winCnt;
	}
	
	public void reset() {
		
		this.setGameCnt(0);
		this.setLossCnt(0);
		this.setTieCnt(0);
		this.setWinCnt(0);
	}
	
	public Outcome getOutcomeForP1(Action p1, Action p2) {
		
		Outcome res;
		
		if (p1 == Action.PAPER    && p2 == Action.ROCK  || 
		    p1 == Action.SCISSORS && p2 == Action.PAPER || 
			p1 == Action.ROCK     && p2 == Action.SCISSORS) {
			
			res = Outcome.WIN;
			winCnt++;
		
		}else if (p2 == Action.PAPER    && p1 == Action.ROCK  || 
			      p2 == Action.SCISSORS && p1 == Action.PAPER || 
				  p2 == Action.ROCK     && p1 == Action.SCISSORS) {
			
			res = Outcome.LOSS;
			lossCnt++;
		
		}else {
			
			res = Outcome.TIE;
			tieCnt++;
		}
		
		gameCnt++;
		return res;
		
	}
	
	public Action getAiMove() {
		
		return Action.values()[(new Random()).nextInt(3)];
		
	}
	
	
	
}
