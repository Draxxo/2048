package view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controler.Controler;

public class Ihm_2048 extends JFrame implements ActionListener, KeyListener
{
	private Controler ctrl;
	private JButton	b;
	private JPanel	jeu;
	private JLabel	l;
	
	private JLabel scoreAct;
	private JLabel scoreMax;
	private JLabel nbCp;
	private JLabel info;
	
	public Ihm_2048()
	{
		setTitle("2048 by Alexis Prevost-Maynen (alexisprevostmaynen.fr)");
		setSize(720, 800);
		setBackground(Color.WHITE);
		setResizable(false);
		
		this.ctrl = new Controler();
	
		//affichage du titre et score et bouton
		JPanel ensN = new JPanel(new BorderLayout());
		
		//stocke le titre et les scores
		JPanel ens     = new JPanel(new GridLayout(1,4));
		JLabel titre   = new JLabel("2048");
			  scoreAct = new JLabel("Score : " + ctrl.getScoreAct());
			  scoreMax = new JLabel("Best : " + ctrl.getScoreMax());
			  nbCp     = new JLabel("Number of stroke : " + ctrl.getNbCoup());
		
		ens.add(titre);
		ens.add(scoreAct);
		ens.add(scoreMax);
		ens.add(nbCp);
		
		//le bouton pour reset la partie 
		JPanel  bout = new JPanel(new BorderLayout());
			       b = new JButton("New game !");
		
		bout.add(b);
		b.addActionListener(this);
		add(bout);
		
		ensN.add(ens , BorderLayout.NORTH);
		ensN.add(bout, BorderLayout.EAST);
		
		info = new JLabel("Join the numbers and get to the 2048 tile ! Use your arrow keys to move the tiles.");
		ensN.add(info);
		
		add(ensN, BorderLayout.NORTH);
	
		//le jeu 2048
		this.init();
		add(jeu);
		
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		
		setVisible(true); 
		
		// abonnement aux evenements de gestion de la fenetre
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        } );
	}
	
	//initialise le GridLayout !
	public void init()
	{	
		JLabel img0 = null;
		JLabel img2;
		JLabel img4;
		JLabel img8;
		JLabel img16;
		JLabel img32;
		JLabel img64;
		JLabel img128;
		JLabel img256;
		JLabel img512;
		JLabel img1024;
		JLabel img2048;
		
		jeu = new JPanel(new GridLayout(4, 4, 4, 4));
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				img0    = new JLabel(new ImageIcon(this.getClass().getResource("/0.png")));
				img2    = new JLabel(new ImageIcon(this.getClass().getResource("/2.png")));
				img4    = new JLabel(new ImageIcon(this.getClass().getResource("/4.png")));
				img8    = new JLabel(new ImageIcon(this.getClass().getResource("/8.png")));
				img16   = new JLabel(new ImageIcon(this.getClass().getResource("/16.png")));
				img32   = new JLabel(new ImageIcon(this.getClass().getResource("/32.png")));
				img64   = new JLabel(new ImageIcon(this.getClass().getResource("/64.png")));
				img128  = new JLabel(new ImageIcon(this.getClass().getResource("/128.png")));
				img256  = new JLabel(new ImageIcon(this.getClass().getResource("/256.png")));
				img512  = new JLabel(new ImageIcon(this.getClass().getResource("/512.png")));
				img1024 = new JLabel(new ImageIcon(this.getClass().getResource("/1024.png")));
				img2048 = new JLabel(new ImageIcon(this.getClass().getResource("/2048.png"))); 
				
				switch(ctrl.getTab2048(i,j))
				{
					case 2    : jeu.add(img2   ); break;
					case 4    : jeu.add(img4   ); break;
					case 8    : jeu.add(img8   ); break;
					case 16   : jeu.add(img16  ); break;
					case 32   : jeu.add(img32  ); break;
					case 64   : jeu.add(img64  ); break;
					case 128  : jeu.add(img128 ); break;
					case 256  : jeu.add(img256 ); break;
					case 512  : jeu.add(img512 ); break;
					case 1024 : jeu.add(img1024); break;
					case 2048 : jeu.add(img2048); break;
					
					default : jeu.add(img0);
				}
			}
		}
		jeu.setBackground(new Color(187, 173, 160));
	}
	
	//pour gerer le bouton et permettre le reset du jeu
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == b)
		{
			if(ctrl.getScoreAct()>ctrl.getScoreMax())
			{
				scoreMax.setText("Best : " + ctrl.getScoreAct());
				ctrl.setScoreMax(ctrl.getScoreAct());
			}
			scoreAct.setText("Score : " + 0);
			ctrl.reset();
			nbCp.setText("Nombres de coups : " + ctrl.getNbCoup());
			
			remove(jeu);
			this.init();
			add(jeu);
			
			this.requestFocus();
			
			info.setText("Join the numbers and get to the 2048 tile! Use your arrow keys to move the tiles.");
			ctrl.setPerdu();
		}
	}
	
	//touche appuye
	public void keyPressed(KeyEvent e) { }
	
	//touche relache
	public void keyReleased(KeyEvent e)
	{
		if(ctrl.perdu())
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				ctrl.coupAdd("N");
				
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				ctrl.coupAdd("E");
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
				ctrl.coupAdd("O");
				
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				ctrl.coupAdd("S");
				
			scoreAct.setText("Score : " + ctrl.getScoreAct());
			nbCp.setText("Nombres de coups : " + ctrl.getNbCoup());
			
			remove(jeu);
			this.init();
			add(jeu);
			
			if(ctrl.gagne())
				info.setText("Vous avez gagner ! felicitation !");
		}
		else
			info.setText("Vous avez perdu veuillez appuyer sur le bouton pour recommencer.");
	}
	
	public void keyTyped(KeyEvent e) { }
	
	//le main qui lance le programme
	public static void main(String [] arg) {
		new Ihm_2048();
	}
}