import java.util.ArrayList;

import javax.security.auth.callback.ChoiceCallback;

import controlP5.*;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class MainApplet extends PApplet{
	private final static int width = 1000, height = 700;
	private ControlP5 cp5;
	Button buttonA,buttonB,buttonC,buttonD;
	JSONObject data;
	JSONArray problems;
	JSONObject problem;
	String msg;
	String answer;
	Ani ani;
	int score;
	PImage character,character2;
	int locationx=300,locationy=50;
	ArrayList<PImage> monster;
	private int i=0,j=0;
	private String file = "src/resources/problems.json";
	public void setup(){
		monster=new ArrayList<PImage>();
		character = loadImage("src/resources/img/character_1.png");
		for(j=1;j<=5;j++)
		{
		character2=loadImage("src/resources/img/monster_"+j+".png");
		monster.add(character2);
		}
		Ani.init(this);
		size(width,height);
		smooth();
		cp5 = new ControlP5(this);
		score = 0;
		loadData();
		problem = problems.getJSONObject(0);
		msg = problem.getString("question");
		buttonA = cp5.addButton("buttonA").setLabel(problem.getString("choiceA")).setPosition(350, 420) .setSize(150, 50); 
		buttonB = cp5.addButton("buttonB").setLabel(problem.getString("choiceB")).setPosition(350, 480) .setSize(150, 50); 
		buttonC = cp5.addButton("buttonC").setLabel(problem.getString("choiceC")).setPosition(350, 540) .setSize(150, 50); 
		buttonD = cp5.addButton("buttonD").setLabel(problem.getString("choiceD")).setPosition(350, 600) .setSize(150, 50); 
	
		
	}
	public void buttonA(){
		checkAnswer("A");
		i++;
		problem = problems.getJSONObject(i);
		btUpdate();
		cp5.update();
		Ani.to(this, (float)0.5, "locationx",350,Ani.LINEAR);
		
		//draw();
	}
	public void buttonB(){
		checkAnswer("B");
		i++;
		problem = problems.getJSONObject(i);
		btUpdate();
		cp5.update();
		
	}
	public void buttonC(){
		checkAnswer("C");
		i++;
		problem = problems.getJSONObject(i);
		btUpdate();
		cp5.update();
		
	}
	public void buttonD(){
		checkAnswer("D");
		i++;
		problem = problems.getJSONObject(i);
		btUpdate();
		cp5.update();
		
	}
	public void draw(){
		background(255);
		textSize(18);
		fill(0);
		image(monster.get(0),locationx,locationy,300,300);
		
		line(0, height/2, width, height/2);
		msg = problem.getString("question");
		text(msg,20, 400);
		text(score,width-50,height/2+20);
	
	
	}
	public void checkAnswer(String ans){
		answer = problem.getString("answer");
		System.out.println(answer);
		if(ans.equals(answer))
		{
			System.out.println("����");
			score++;
			
		}
		else
			System.out.println("����");
		
	}
	private void btUpdate(){
		buttonA.setLabel(problem.getString("choiceA"));
		buttonB.setLabel(problem.getString("choiceB"));
		buttonC.setLabel(problem.getString("choiceC"));
		buttonD.setLabel(problem.getString("choiceD"));
	}
	private void loadData(){
		data = loadJSONObject(file);
		problems = data.getJSONArray("problems");
		System.out.println("Number of nodes: " + problems.size());
	}
}
