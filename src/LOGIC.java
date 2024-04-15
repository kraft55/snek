
import java.awt.event.*;

import java.io.*;
import javax.sound.sampled.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Logic extends JFrame implements KeyListener{
	
	int width, height, squaresize;			//Game Dimensions + Pixel size of squares
	int x,y,currentX,currentY;				//x,y = directions of movement(left: x=-1;y=0) ; Currentx/y = Postition of snake head
	int framespersquare;
	ArrayList<Square> list;					//List containing the snake pieces
	Food food;								//The food object
	Direction direction;
	GUI gui;
	MainMenu menu;
	int score;
	LinkedList<Direction> nextdirection;
	
	public Logic(int w, int h, int s){
		width = w;
		height = h;
		squaresize = s;
		currentX = w/2;						//Snake starts in the middle
		currentY = h/2;					
		list = new ArrayList<Square>();
		setDirection(Direction.RIGHT);		//Right = default direction
		nextdirection = new LinkedList<>();
		nextdirection.add(Direction.RIGHT);
		food = new Food(width-squaresize, height-squaresize);		//Food Object
		gui = new GUI(width, height+20, squaresize, list);
		menu = new MainMenu(width, height);
		score = 0;

	}
	
	public enum Direction{
		LEFT, RIGHT, UP, DOWN;
	}
	public void setDirection(Direction d){	//Sets x,y changes for each direction
		switch(d){
			case LEFT: 
				setX(-1);
				setY(0);
				direction = d;
				break;
			case RIGHT: 
				setX(1);
				setY(0);
				direction = d;
				break;
			case UP: 
				setX(0);
				setY(-1);
				direction = d;
				break;
			case DOWN: 
				setX(0);
				setY(1);
				direction = d;
				break;
		}
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public boolean isSnake(){				//hit detection
		for(int i=0;i<list.size()-2;i++){
			int headX = list.get(list.size()-1).getx();			
			int headY = list.get(list.size()-1).gety();
			if(list.get(i).getx()==headX&&list.get(i).gety()==headY){		//Head position equal to a snake piece position == hit
				System.out.println(headX+""+headY);
				return true;
			}
		}
		return false;
	}
	public void mainmenu(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(menu);
		frame.setFocusable(true);
		frame.addKeyListener(this);
		frame.setSize(width*squaresize+2*squaresize, height*squaresize+2*squaresize);
		frame.setVisible(true);
	}
	public void play(JFrame frame) throws Exception{
		frame.remove(menu);
		frame.add(gui);
		frame.setFocusable(true);

		frame.addKeyListener(this);
		frame.setSize(width*squaresize+2*squaresize, height*squaresize+2*squaresize);
		frame.setVisible(true);
		for(boolean b = true;b==true;){
			b = step();
			gui.setList(list);						//Gives gui the updated list
			gui.setFood(food.getx(), food.gety());	//and the updated food location
			gui.repaint();	
			
		}
		System.out.println("Game Over");
	}
	public void game() throws Exception{	//Exception for sleep()
		
		JFrame f = new JFrame("Snake by Michael");
		mainmenu(f);
		while(!menu.getGamestarted()){
			Thread.sleep(50);
		}
		menu.setGamestarted(false);
		play(f);
		f.add(gui);
		f.setFocusable(true);

		f.addKeyListener(this);
		f.setSize(width*squaresize+2*squaresize, height*squaresize+2*squaresize);
		f.setVisible(true);
		

		//music();
		
	}
	public boolean step() throws Exception{			//Returns false if you hit the snake
		if(!nextdirection.isEmpty()){
			if(legalMove(nextdirection.getFirst()))
				setDirection(nextdirection.getFirst());
			nextdirection.removeFirst();
		}
		currentX+=x;								//Adds direction change to the current position
		currentY+=y;
		outOfBounds();								//Checks for out of bounds and puts snake at opposite direction
		list.add(new Snakepiece(currentX,currentY));
		if(isSnake()){			//Checks if you hit the snake
			gui.lose();
			return false;
		}
		Thread.sleep(50);							// <- very good programming
		if(food.getx()==currentX&&food.gety()==currentY){
			food = new Food(width, height);			//Creates new food if you ate it
			score++;
			gui.setScore(score);
		}else{
			list.remove(0);							//If the snake didn't eat food this step, the last piece of the snake is removed
		}
		return true;
		
	}
	public void outOfBounds(){
		if(currentY>height){						
			currentY = 0;
		}
		if(currentY<0){						
			currentY = height;
		}
		if(currentX>width){						
			currentX = 0;
		}
		if(currentX<0){						
			currentX = width;
		}
		
	}
	public boolean legalMove(Direction dir){
		if(dir == Direction.LEFT && direction == Direction.RIGHT)
			return false;
		if(dir == Direction.RIGHT && direction == Direction.LEFT)
			return false;
		if(dir == Direction.UP && direction == Direction.DOWN)
			return false;
		if(dir == Direction.DOWN && direction == Direction.UP)
			return false;
		
		return true;
	}
	public void keyTyped(KeyEvent e){}		//Key Events
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				nextdirection.add(Direction.RIGHT);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
				nextdirection.add(Direction.LEFT);			
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
				nextdirection.add(Direction.UP);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
				nextdirection.add(Direction.DOWN);
		}
	}
	public void keyReleased(KeyEvent e){}
			
/*	public void music(){

	try {
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;

	    stream = AudioSystem.getAudioInputStream(new File("trash2.wav"));
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	}
	catch (Exception e) {
	}
	}
	
	*/
}
