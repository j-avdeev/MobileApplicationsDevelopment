package com.example.hw_1;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw_1.GameSession;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity {

	
	
	TextView tvRes, tvScore, tvStats;
	Button btnRock, btnPpr, btnScs, btnNext;
	ImageView imgPaction,  imgAIaction;
	CheckBox chck1;
	boolean game_over = false;
	GameSession game;	
	final String PREFS_NAME = "pref"; 
	GameSession.Action pAction, aiAction;
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		
		super.onSaveInstanceState(savedInstanceState);
		//savedInstanceState.putSerializable("gameState", game);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
	   super.onRestoreInstanceState(savedState);
	   //game = (GameSession) savedState.getSerializable("gameState");
	}
	
	
	@Override
	public void onResume() {
		 super.onResume();
	
		 SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
		 if (sharedPreferences.contains("game_state")) {
		        final Gson gson = new Gson();
		        game =  gson.fromJson(sharedPreferences.getString("game_state", ""), GameSession.class);
		 }
		 
	/*	 if (sharedPreferences.contains("game_is_over")) {
		        
			 	game_over =  sharedPreferences.getBoolean("game_is_over", false);
		 
		        if(game_over) {
		        	
			        pAction =  GameSession.Action.values()[sharedPreferences.getInt("p_choise", -1)];
			        aiAction =  GameSession.Action.values()[sharedPreferences.getInt("ai_choise", -1)];

			        int[] imageIds = new int[] { R.drawable.paper_normal,  R.drawable.sciss_normal, R.drawable.rock_normal };
			        			        
			        imgPaction.setImageResource(imageIds[pAction.ordinal()]);
			        
			        imgAIaction.setImageResource(imageIds[aiAction.ordinal()]);
			        
			        btnNext.setVisibility(View.VISIBLE);
		        }
		 
		 }*/

		 
		 
		 
		 displayScore();
		 
	}
	
	@Override
	public void onPause() {
		super.onPause();
	
		
		 SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
		 SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
		 final Gson gson = new Gson();		 
		 String serializedObject = gson.toJson(game);
		 sharedPreferencesEditor.putString("game_state", serializedObject);
		 
		 sharedPreferencesEditor.putBoolean("game_is_over", game_over);
		 if (game_over) {
			 sharedPreferencesEditor.putInt("p_choise",pAction.ordinal());
			 sharedPreferencesEditor.putInt("ai_choise",aiAction.ordinal());
		 
		 
		 }
		 
		 
		 sharedPreferencesEditor.commit();
		
		
		
	}
	
	public void displayScore() {
		
		int wins = game.getWinCnt();
		int losses = game.getLossCnt();
		int ties = game.getTieCnt();
		int games = game.getGameCnt();
		
		tvScore.setText(String.format("%d wins, %d losses, %d ties in %d games", wins, losses, ties, games));
		if(games>0) {
		
			tvStats.setText(String.format("%.2f%% wins, %.2f%% losses, %.2f%% ties", ((double) wins)/games*100, ((double) losses)/games*100, ((double) ties)/games*100));
		}else {
			tvStats.setText("");
		}
		
	}
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		
		btnRock = (Button) findViewById(R.id.btn_rock);
		btnPpr = (Button) findViewById(R.id.btn_paper);
		btnScs = (Button) findViewById(R.id.btn_sciss);
		btnNext = (Button) findViewById(R.id.btn_next);
		imgPaction = (ImageView) findViewById(R.id.imageView1);
		
		imgAIaction = (ImageView) findViewById(R.id.imageView3);
		tvRes = (TextView) findViewById(R.id.tvResult);
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvStats = (TextView) findViewById(R.id.tvStats);
		chck1 = (CheckBox) findViewById(R.id.checkBox1);
		
		chck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton b, boolean isChecked) {
				
				if(isChecked) {
					tvScore.setVisibility(View.VISIBLE);
					tvStats.setVisibility(View.VISIBLE);
					
				}else {
					tvScore.setVisibility(View.INVISIBLE);
					tvStats.setVisibility(View.INVISIBLE);					
				}
				
			}
		});
		
		game = new GameSession();
		displayScore();
		btnNext.setVisibility(View.INVISIBLE);
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				tvRes.setText(R.string.prompt);
				imgPaction.setBackgroundResource(R.drawable.tie3);
	        	imgAIaction.setBackgroundResource(R.drawable.tie3);
	        	imgPaction.setImageResource(R.drawable.empty);
	        	imgAIaction.setImageResource(R.drawable.empty);
	        	game_over = false;
				btnNext.setVisibility(View.INVISIBLE);
			}
		});
		
		OnClickListener onClick = new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		       
		    	if (game_over)
		        	
		        	return;
		        	
		        int pId = v.getId();
		        
		        pAction = GameSession.Action.ROCK;
		        
		        int[] imageIds = new int[] { R.drawable.paper_normal,  R.drawable.sciss_normal, R.drawable.rock_normal };
		        
		        
		        switch(pId) {
		        
		        case R.id.btn_paper:
		        	pAction = GameSession.Action.PAPER;
		        	break;
		        case R.id.btn_sciss:
		        	pAction = GameSession.Action.SCISSORS;
		        	break;
		        case R.id.btn_rock:
		        	pAction = GameSession.Action.ROCK;
		        	break;            
		        }
		        imgPaction.setImageResource(imageIds[pAction.ordinal()]);
		        
		        game_over = true;
		        
		        
		        aiAction = game.getAiMove();
		                    
		        imgAIaction.setImageResource(imageIds[aiAction.ordinal()]);
		        
		        GameSession.Outcome res = game.getOutcomeForP1(pAction, aiAction); 
		        
		        if (res == GameSession.Outcome.WIN) {
		        	
		        	tvRes.setText(R.string.outcome_win);
		        	imgPaction.setBackgroundResource(R.drawable.win3);       // Define the border color
		        	imgAIaction.setBackgroundResource(R.drawable.loss3);       // Define the border color
		        	
		        	
		        }else if(res == GameSession.Outcome.LOSS) {
		        
		        	tvRes.setText(R.string.outcome_loss);
		        	imgPaction.setBackgroundResource(R.drawable.loss3);       // Define the border color
		        	imgAIaction.setBackgroundResource(R.drawable.win3);       // Define the border color
		        	
		        	
		        }else {
		        	
		        	tvRes.setText(R.string.outcome_tie);
		        	imgPaction.setBackgroundResource(R.drawable.tie3);
		        	imgAIaction.setBackgroundResource(R.drawable.tie3);
		        		
		        }
		        displayScore();
		        btnNext.setVisibility(View.VISIBLE);	
		                    		 
		    }
		};
		
		btnRock.setOnClickListener(onClick);
		btnPpr.setOnClickListener(onClick);
		btnScs.setOnClickListener(onClick);
		
		tvRes.setText(R.string.prompt);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			game.reset();
			displayScore();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	

	
}

