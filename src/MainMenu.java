import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener{
	private int width, height;
	private JButton button;
	private boolean gamestarted = false;
	public MainMenu(int w, int h){
		width = w;
		height = h;
		button = new JButton("Play"); 
		button.setBounds(width/2,height/2,10,10);
		add(button);
		button.addActionListener(this);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button){
            setGamestarted(true);
        }
	}
	public boolean getGamestarted(){
		return gamestarted;
	}
	public void setGamestarted(boolean b){
		gamestarted = b;
	}
}
