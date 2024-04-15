import java.awt.*;

import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JPanel{
	private int width,height,squaresize;
	private ArrayList<Square> list;
	private int foodX,foodY;
	private int score;
	private boolean lost;
	
	public GUI(int w, int h, int ss, ArrayList<Square> l){
		width = w;
		height = h;
		squaresize = ss;
		list = l;
		lost = false;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
	
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		for(int i=0;i<list.size();i++){
			Square s = list.get(i);
			for(int n = 0 ;n<squaresize;n++){
				g.fillRect(s.getx()*squaresize, s.gety()*squaresize, squaresize, squaresize);
				repaint();
			}
		}
		g.setColor(Color.RED);
		g.fillRect(foodX*squaresize, foodY*squaresize, squaresize, squaresize);
		g.drawString("Score: "+100*score, 30, 30);
		if(lost){
			g.setFont(new Font("Serif", Font.BOLD, 40));
			g.drawString("You did a lose! :^) - Score: "+100*score, 100, (height*squaresize)/2);
		}
	}
	public void setList(ArrayList<Square> l){
		list = l;
	}
	public void setFood(int x, int y){
		foodX = x;
		foodY = y;
	}
	public void lose(){
		lost = true;
	}
	public void setScore(int s){
		score = s;
	}

}
