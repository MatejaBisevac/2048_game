import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;






public class GUI  extends JFrame implements ActionListener{

	JButton levo = new MojeDugme(1);
	JButton desno = new MojeDugme(2);
	JButton gore = new MojeDugme(3);
	JButton dole = new MojeDugme(4);


JLabel skor = new JLabel("	Poeni : ");
JButton undo = new MojeDugme(5);
JLabel[][] ploce = new JLabel[4][4];

Engine engin = new Engine();
	
	JPanel glavnaPloca = new JPanel(new GridLayout(4,4));
	JPanel strelice = new JPanel(new GridLayout(2,5));
	JPanel glava = new JPanel(new GridLayout(1,5));
	
	

	public GUI(){
	
	for(int i = 0 ; i < 4 ; i++)
	for(int j = 0 ; j < 4 ; j++) {
	ploce[i][j] = new JLabel("");
	ploce[i][j].setPreferredSize(new Dimension(120,120));
	}
		osveziGUI();
		
		setujDugmice();
		
		setujGlavu();
		
		setujSredinu();
			
		setujStrelice();
		
		
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void osveziGUI() {
	skor.setText("Poeni : " + engin.poeni);

		for(int i = 0 ; i < 4 ; i++)
		for(int j = 0 ; j < 4 ; j++)
			if(engin.polja[i][j]!=null)
				ploce[i][j].setIcon(engin.dajIkonicu(i,j));
			else
				ploce[i][j].setIcon(engin.dajIkonicu(i, j));
	}

	private void setujGlavu() {
		glava.add(skor);
		glava.add(new JLabel(""));
		glava.add(new JLabel(""));
		glava.add(new JLabel(""));
		glava.add(undo);
		glava.setPreferredSize(new Dimension(50,50));
		getContentPane().add(glava,BorderLayout.NORTH);
		
		
	}
	
	public void setujSredinu() {
	for(int i = 0 ; i < 4 ; i++)
	for(int j = 0 ; j < 4 ; j++)
		glavnaPloca.add(ploce[i][j]);
		
		getContentPane().add(glavnaPloca,BorderLayout.CENTER);
	}
	public void setujStrelice() {
		getContentPane().add(strelice,BorderLayout.SOUTH);
	}
	
	public void setujDugmice() {
		
		levo.setPreferredSize(new Dimension(50,50));
		levo.setText("<  ");
		desno.setPreferredSize(new Dimension(50,50));	
		desno.setText("  >");
		gore.setPreferredSize(new Dimension(50,50));	
		gore.setText("/\\");
		dole.setPreferredSize(new Dimension(50,50));	
		dole.setText("\\/");	
		undo.setText("undo");
		undo.setPreferredSize(new Dimension(50,50));
				
		levo.addActionListener(this);
		desno.addActionListener(this);
		gore.addActionListener(this);
		dole.addActionListener(this);
		undo.addActionListener(this);
		
				strelice.add(new JLabel());
				strelice.add(new JLabel());
				strelice.add( gore);
				strelice.add(new JLabel());
				strelice.add(new JLabel());
				strelice.add(new JLabel());
				strelice.add( levo);
				strelice.add(dole);
				strelice.add(desno);
				strelice.add(new JLabel());
		
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
	MojeDugme md =(MojeDugme) e.getSource();	
		if( md.id == 1) {
			engin.bekapuj();
			engin.igrajLevo();
			
			proveriKraj();
			
			if(engin.popunjenost()==false)
			engin.novoPolje();
			else
				zavrsiIgruGubitak();
			osveziGUI();
			proveriKraj();
			
		}
		if(md.id == 2) {
			engin.bekapuj();
			engin.igrajDesno();
			
			if(engin.popunjenost()==false)
			engin.novoPolje();
			else
				zavrsiIgruGubitak();
			
			osveziGUI();
			proveriKraj();
		}
		if(md.id == 3) {
			engin.bekapuj();
			engin.igrajGore();
			
			if(engin.popunjenost()==false)
			engin.novoPolje();
			else
				zavrsiIgruGubitak();
			
			osveziGUI();
			proveriKraj();
		}
		if(md.id == 4) {
			engin.bekapuj();
			engin.igrajDole();
			
				if(engin.popunjenost()==false)
				engin.novoPolje();
				else
				zavrsiIgruGubitak();
			
				osveziGUI();
				proveriKraj();
		}
		if(md.id == 5) {
			engin.povrati();
			osveziGUI();
		}

	}
	public void proveriKraj() {
	int brojac = 0;
		for(int i= 0 ; i < 4 ; i ++) 
		for(int j = 0 ; j < 4 ; j++)
		if(engin.polja[i][j]!=null)
		{
			if(engin.polja[i][j].val ==256) {
				zavrsiIgruPobeda();
				return;
			}
		}
		else
		{
			brojac ++;
		}
		
		if(brojac==16) zavrsiIgruGubitak();
	}
	
	public void zavrsiIgruGubitak() {

			
			int i = JOptionPane.showConfirmDialog(null, "Izgubili ste sa rezultatom :  " + engin.poeni + 
										  "\n Da li zelite da pokusate ponovo?" , "Kraj Igre!!!", JOptionPane.YES_NO_OPTION);
		
		if(i == JOptionPane.YES_OPTION) {
		
			engin.novaIgra();
			osveziGUI();
		}
		else
				System.exit(0);
	}
	
	
	
	
	
	
	public void zavrsiIgruPobeda() {
		
		int i = JOptionPane.showConfirmDialog(null, "Dostigli ste maksimalnu verziju seksi Vasica, vase oci su blagoslovene sa rezultatom : " 
				 +engin.poeni+ "\n Da li zelite da igrate ponovo?" , "Kraj Igre!!!", JOptionPane.YES_NO_OPTION);

				if(i == JOptionPane.YES_OPTION) {
				
				engin.novaIgra();
				osveziGUI();
				}
				else
				System.exit(0);
	}
	
	
	

	
}
