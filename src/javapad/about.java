package javapad;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class about extends JFrame implements ActionListener 
{
	JPanel panel= new JPanel(null);
	JLabel l=new JLabel("<html>This is the JavaPad designed <br>"+"purely in Java. It is developed<br>"+"for the purpose of the editing <br>"+"of the Text or to Save a new <br>"+"text in a file or to edit the <br>"+"already saved text in a file.");
	JButton jb=new JButton("Done");
	
	about()
	{
		add(panel);
		panel.add(l);
		panel.add(jb);
		
		l.setBounds(40,10,200,200);
		jb.setBounds(60,220,100,30);
	
		setSize(300,300);
		setTitle("About Javapad");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jb.addActionListener(this);
	}
	public static void main(String[] args) 
	{
		new about();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ch=e.getActionCommand();
		if(ch.equals("Done"))
		{
			this.dispose();
		}
	}

}
