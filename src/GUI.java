import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JPanel implements ActionListener{
	private int width,height,squaresize;
	private ArrayList<Square> list;
	private int foodX,foodY;
	private int score;
	private boolean lost;
	private boolean menu;
	public JButton menubutton;
	
	public GUI(int w, int h, int ss, ArrayList<Square> l){
		width = w;
		height = h;
		squaresize = ss;
		list = l;
		lost = false;
		menu = false;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
	
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

		if(lost){
			g.setFont(new Font("Serif", Font.BOLD, width-5));
			g.drawString("You lose! :^) - Score: "+100*score, squaresize, (height*squaresize)/3);
			menubutton = new JButton("Main Menu");
			menubutton.setLayout(null);
			this.setLayout(null);
			add(menubutton);
			menubutton.setBounds(2*width,1*height,width*6,height*1);
			menubutton.addActionListener(this);

		}else{
			g.drawString("Score: "+100*score, 30, 30);
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		menu = true;
	}
	public boolean getMenu(){
		return menu;
	}
	public void setMenu(boolean b){
		menu = b;
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
	public void setlost(boolean b){
		lost = b;
	}
	public void setScore(int s){
		score = s;
	}

}
