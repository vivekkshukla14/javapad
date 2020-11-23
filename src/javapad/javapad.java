package javapad;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class javapad extends JFrame implements ActionListener
{
	
	JPanel panel= new JPanel(null);
	JMenuBar bar=new JMenuBar();
	JMenu file=new JMenu("File");
	JMenu edit=new JMenu("Edit");
	JMenuItem neww=new JMenuItem("New");
	JMenuItem open=new JMenuItem("Open");
	JMenuItem save=new JMenuItem("Save");
	JMenuItem saveas=new JMenuItem("Save as");
	JMenuItem exit=new JMenuItem("Exit");
	JMenuItem cut=new JMenuItem("Cut");
	JMenuItem copy=new JMenuItem("Copy");
	JMenuItem paste=new JMenuItem("Paste");
	JMenuItem delete=new JMenuItem("Delete");
	JMenuItem selectall=new JMenuItem("Select All");
	JMenuItem font=new JMenuItem("Font...");
	JMenuItem status=new JMenuItem("Status Bar");
	JMenuItem about=new JMenuItem("About Javapad");
	JMenuItem color=new JMenuItem("Text Colour");
	JMenuItem Areacolor=new JMenuItem("Area Colour");
	JMenu format=new JMenu("Format");
	JMenu view=new JMenu("View");
	JMenu help=new JMenu("Help");
	JTextArea area=new JTextArea();

	String a,bb;
	int d;
	FontChooser fontDialog=null;
	public javapad()
	{
		add(panel);

		bar.setLayout(new FlowLayout());
		bar.add(file);
		bar.add(edit);
		bar.add(format);
		bar.add(view);
		bar.add(help);
		setJMenuBar(bar);
				
		file.add(neww);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.add(exit);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.add(selectall);
		
		format.add(font);
		format.add(color);
		format.add(Areacolor);
		view.add(status);
		
		help.add(about);
		add(new JScrollPane(area));
		area.setBounds(0,0,1370,700);
		
		
		setSize(750,600);
		setTitle("JavaPad");
		setLocation(200,50);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		neww.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		delete.addActionListener(this);
		font.addActionListener(this);
		status.addActionListener(this);
		about.addActionListener(this);
		selectall.addActionListener(this);
		color.addActionListener(this);
		Areacolor.addActionListener(this);
		
	}
	
	public static void main(String [] args)
	{
		new javapad();
	}

	String filename;
	
	Font fon;
	int i;
	
	public void actionPerformed(ActionEvent ae) 
	{
		
		String what=ae.getActionCommand();
		
		
		
		if(what.equals("New"))
		{
				newfile();
		}
		
		if(what.equals("Open"))
		{
			String result=area.getText();
			if(result.isEmpty())
			{
				FileDialog fileDialog=new FileDialog(this, "Open File", FileDialog.LOAD);
			fileDialog.setVisible(true);
			
			if(fileDialog.getFile()!=null)
			{
				filename=fileDialog.getDirectory()+ fileDialog.getFile();
				setTitle(filename);
			}
			try {
				FileReader fr = new FileReader(filename);
				BufferedReader br=new BufferedReader(fr);
				StringBuilder sb=new StringBuilder();
				
				String line;
				
				while((line=br.readLine())!=null)
				{
					sb.append(line+ "\n");
					area.setText(sb.toString());
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			}
			else
			{
				newfile();
			}
			
			
		}
		
				
		if(what.equals("Save"))
		{
			if(filename!=null)
			{
				try
				{
					FileOutputStream fo=new FileOutputStream(filename);
					DataOutputStream dout=new DataOutputStream(fo);
					String result=area.getText();
					dout.writeUTF(result);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				save();
			}
		}
		
		if(what.equals("Save as"))
		{
			save();
		}
		
		if(what.equals("Exit"))
		{
		int input=JOptionPane.showConfirmDialog(this, "Are you sure you want to exit", "Confirm Exit", JOptionPane.YES_NO_OPTION);	
			
			if(input==0)
			{
				this.dispose();
			}
			
		}
		
		if(what.equals("Cut"))
		{
			area.cut();
		}
		
		if(what.equals("Copy"))
		{
			area.copy();
		}
		
		
		if(what.equals("Paste"))
		{
			area.paste();
		}
		
		if(what.equals("Delete"))
		{
			String ch=area.getSelectedText();
			ch.equals("");
			area.setText(ch);
		}
		
		if(what.equals("Select All"))
		{
			area.selectAll();
		}
		
		if(what.equals("Font..."))
		{
			if(fontDialog==null)
				fontDialog=new FontChooser(area.getFont());

			if(fontDialog.showDialog(javapad.this,"Choose a font"))
				javapad.this.area.setFont(fontDialog.createFont());

	
		}
		
		if(what.equals("About Javapad"))
		{
			new about();
		}
		
		if(what.equals("Text Colour"))
		{
			Color initialcolor=Color.RED;    
			Color color=JColorChooser.showDialog(this,"Select a Text Color",initialcolor);    
			area.setForeground(color);
		}
		
		if(what.equals("Area Colour"))
		{
			Color initialcolor=Color.RED;    
			Color color=JColorChooser.showDialog(this,"Select a Area Color",initialcolor);    
			area.setBackground(color);		
		}
	}
	
	
	public void save()
	{
		FileDialog fileDialog=new FileDialog(this, "Save File", FileDialog.SAVE);
		fileDialog.setVisible(true);
		
		if(fileDialog.getFile()!=null)
		{
			filename=fileDialog.getDirectory()+ fileDialog.getFile();
			setTitle(filename);
		}
		
		try {
			FileWriter fw=new FileWriter(filename);
			fw.write(area.getText());
			setTitle(filename);
			fw.close();
			
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	
	}
	
	public void newfile()
	{
		
		String result= area.getText();
		if(result.isEmpty())
		{
			area.setText("");
		}
		else 
		{
			int retun=JOptionPane.showConfirmDialog(null, "Do you want to save changes ?");
			if(retun==0)
			{
				FileDialog fileDialog=new FileDialog(this, "Save File", FileDialog.SAVE);
				fileDialog.setVisible(true);
				
				if(fileDialog.getFile()!=null)
				{
					filename=fileDialog.getDirectory()+ fileDialog.getFile();
					setTitle(filename);
				}
				
				try {
					FileWriter fw=new FileWriter(filename);
					fw.write(area.getText());
					setTitle(filename);
					fw.close();
					
				} catch (IOException e) {
					System.out.println("File Not Found");
					e.printStackTrace();
				}
			}
			else if(retun==1)
			{
				area.setText("");					
			}
			else if(retun==2)
			{
				area.setText(area.getText());
			}
			
		}
		
	}

	
	
}