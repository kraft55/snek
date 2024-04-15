import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener{
	private int width, height, squaresize;
	private JButton button;
	private JButton exitbutton;
	private boolean gamestarted = false;
	public MainMenu(int w, int h, int ss){
		width = w;
		height = h;
		squaresize = ss;
		button = new JButton("Play"); 
		exitbutton = new JButton("Quit");
		button.setLayout(null);
		exitbutton.setLayout(null);
		this.setLayout(null);
		button.setBounds(2*width,3*height,width*6,height*1);
		exitbutton.setBounds(2*width,5*height,width*6,height*1);
		add(button);
		add(exitbutton);
		button.addActionListener(this);
		exitbutton.addActionListener(this);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button){
            setGamestarted(true);
        }
		if(ae.getSource() == exitbutton){
			System.exit(0);
		}
	}
	public boolean getGamestarted(){
		return gamestarted;
	}
	public void setGamestarted(boolean b){
		gamestarted = b;
	}
}
