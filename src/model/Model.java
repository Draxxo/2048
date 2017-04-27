package model;
//Console2048.java

import java.util.*;

public class Model
{
	private boolean perdu = true;

	private int scoreAct;
	private int scoreMax;
	
	private int nbCoup = 0;
	
	private int[][] tab2048;
	
	//le contructeur de la classe
	public Model()
	{
		this.scoreAct = 0;
		this.scoreMax = 0;
	
		tab2048 = new int[4][4];
		
		this.init();
	}
	
	//initialise la partie avec 2tuiles placé aléatoirement
	public void init()
	{
		for(int i=0;i<tab2048.length;i++)
		{
			for(int j=0;j<tab2048[0].length;j++)
				tab2048[i][j] = 0;
		}
	
		this.ajoutTuile();
		this.ajoutTuile();
	}
	
	//permet de dire quel tuile doivent être additionner
	public boolean coupAdd(String dir)
	{
		if(this.coupPossible(dir))
		{
			this.coupSauteZero(dir);
			
			int retour = 0; 
			if(dir.equals("N"))
			{
				for(int i=1;i<tab2048.length;i++)
				{
					for(int j=0;j<tab2048[0].length;j++)
					{
						retour = 0;
						retour = this.changeTuile(tab2048[i][j], tab2048[i-1][j]);
						
						if(retour != tab2048[i-1][j])
						{
							tab2048[i][j]   = 0;
							tab2048[i-1][j] = retour;
							this.setScore(retour);
						}
					}
				}
			}
			
			if(dir.equals("S"))
			{
				for(int i=2;i>=0;i--)
				{
					for(int j=0;j<tab2048[0].length;j++)
					{
						retour = 0;
						retour = this.changeTuile(tab2048[i][j], tab2048[i+1][j]);
						
						if(retour != tab2048[i+1][j])
						{
							tab2048[i][j]   = 0;
							tab2048[i+1][j] = retour;
							this.setScore(retour);
						}
					}
				}
			}
			
			if(dir.equals("E"))
			{
				for(int i=0;i<tab2048.length;i++)
				{
					for(int j=2;j>=0;j--)
					{
						retour = 0;
						retour = this.changeTuile(tab2048[i][j], tab2048[i][j+1]);
						
						if(retour != tab2048[i][j+1])
						{
							tab2048[i][j]   = 0;
							tab2048[i][j+1] = retour;
							this.setScore(retour);
						}
					}
				}
			}
			
			if(dir.equals("O"))
			{
				for(int i=0;i<tab2048.length;i++)
				{
					for(int j=1;j<tab2048[0].length;j++)
					{
						retour = 0;
						retour = this.changeTuile(tab2048[i][j], tab2048[i][j-1]);
						
						if(retour != tab2048[i][j-1])
						{
							tab2048[i][j]   = 0;
							tab2048[i][j-1] = retour;
							this.setScore(retour);
						}
					}
				}
			}
			
			this.coupSauteZero(dir);
			this.ajoutTuile();
			
			this.nbCoup++;
			return true;
		}
		else 
			this.perdu = this.possibleTuile() || this.possibleCoup();
		
		return false;
	}
	
	//permet de savoir si on peut faire un coup ou non dans la direction choisi
	public boolean coupPossible(String dir)
	{
		if(dir.equals("N"))
		{
			for(int i=1;i<tab2048.length;i++)
			{
				for(int j=0;j<tab2048[0].length;j++)
				{
					if(tab2048[i][j] != 0)
					{
						if(tab2048[i-1][j] == 0 || tab2048[i][j] == tab2048[i-1][j])
							return true;
					}
				}
			}
		}
		
		if(dir.equals("S"))
		{
			for(int i=2;i>=0;i--)
			{
				for(int j=0;j<tab2048[0].length;j++)
				{
					if(tab2048[i][j] != 0)
					{
						if(tab2048[i+1][j] == 0 || tab2048[i][j] == tab2048[i+1][j])
							return true;
					}
				}
			}
		}
			
		if(dir.equals("E"))
		{
			for(int i=0;i<tab2048.length;i++)
			{
				for(int j=2;j>=0;j--)
				{
					if(tab2048[i][j] != 0)
					{
						if(tab2048[i][j+1] == 0 || tab2048[i][j] == tab2048[i][j+1])
							return true;
					}
				}
			}
		}
			
		if(dir.equals("O"))
		{
			for(int i=0;i<tab2048.length;i++)
			{
				for(int j=1;j<tab2048[0].length;j++)
				{
					if(tab2048[i][j] != 0)
					{
						if(tab2048[i][j-1] == 0 || tab2048[i][j] == tab2048[i][j-1])
							return true;
					}
				}
			}
		}
			
		return false;
	}
	
	//permet de faire avancer les tuiles la ou il faut
	public void coupSauteZero(String dir)
	{
		if(dir.equals("N"))
		{
			for(int ij = 0;ij<3;ij++)
			{
				for(int i=0;i<tab2048.length-1;i++)
				{
					for(int j=0;j<tab2048[0].length;j++)
					{
						if(tab2048[i][j] == 0)
						{
							tab2048[i][j]   = tab2048[i+1][j];
							tab2048[i+1][j] = 0;
						}
					}
				}
			}
		}
		
		if(dir.equals("S"))
		{
			for(int ij = 0;ij<3;ij++)
			{
				for(int i=3;i>0;i--)
				{
					for(int j=0;j<tab2048[0].length;j++)
					{
						if(tab2048[i][j] == 0)
						{
							tab2048[i][j]   = tab2048[i-1][j];
							tab2048[i-1][j] = 0;
						}
					}
				}
			}
		}
		
		if(dir.equals("E"))
		{
			for(int ij = 0;ij<3;ij++)
			{
				for(int i=0;i<tab2048.length;i++)
				{
					for(int j=3;j>0;j--)
					{
						if(tab2048[i][j] == 0)
						{
							tab2048[i][j]   = tab2048[i][j-1];
							tab2048[i][j-1] = 0;
						}
					}
				}
			}
		}
		
		if(dir.equals("O"))
		{
			for(int ij = 0;ij<3;ij++)
			{
				for(int i=0;i<tab2048.length;i++)
				{
					for(int j=0;j<tab2048[0].length-1;j++)
					{
						if(tab2048[i][j] == 0)
						{
							tab2048[i][j]   = tab2048[i][j+1];
							tab2048[i][j+1] = 0;
						}
					}
				}
			}
		}
	}
	
	//ajoute une tuile à chaque coup
	public void ajoutTuile()
	{
		if(this.possibleTuile())
		{
			int alea = (int)(Math.random()*8);
			
			int posX = (int)(Math.random()*4);
			int posY = (int)(Math.random()*4);
				
			if(tab2048[posX][posY] == 0 )
			{
				if(alea<7)
					tab2048[posX][posY] = 2;
				else
					tab2048[posX][posY] = 4;
			}
			else
				this.ajoutTuile();
		}
		else
			this.perdu = this.possibleCoup();
	}
	
	//met à jour le score
	public void setScore(int score)
	{
		this.scoreAct += score;
	}
	
	//permet de savoir si on peut ajouter un tuile
	public boolean possibleTuile()
	{
		for(int i=0;i<tab2048.length;i++)
		{
			for(int j=0;j<tab2048[0].length;j++)
			{
				if(tab2048[i][j] == 0)
					return true;
			}
		}
		
		return false;
	}
	
	//permet de renvoyer la nouvelle tuile
	public int changeTuile(int envoie,int recoit)
	{
		int retour = recoit;
		
		if(envoie == recoit)
			retour = envoie + recoit;
			
		return retour;
	}
	
	//change la valeur de perdu à faux
	public boolean perdu()
	{
		return this.perdu;
	}
	
	//change le valeur de perdu à vrai
	public void setPerdu()
	{
		this.perdu = true;
	}
	
	//retourne la valeur qu'on demande
	public int getTab2048(int i, int j)
	{
		return this.tab2048[i][j];
	}
	
	//test si le joueur peut encore faire un coup si tab2048 est rempli
	public boolean possibleCoup()
	{
		System.out.println("test");
		for(int i=0;i<tab2048.length-1;i++)
		{
			for(int j=0;j<tab2048[0].length-1;j++)
			{
				System.out.println("test");
				if(tab2048[i][j] == tab2048[i+1][j] || tab2048[i][j] == tab2048[i][j+1]) return true;
			}
		}
	
		return false;
	}
	
	//test si le joueur à gagné ou non
	public boolean gagne()
	{
		for(int i=0;i<tab2048.length;i++)
		{
			for(int j=0;j<tab2048[0].length;j++)
			{
				if(tab2048[i][j]==2048)
					return true;
			}
		}
		
		return false;
	}
	
	//reinitialise la partie à zéro
	public void reset()
	{
		this.init();
		
		if(this.scoreMax < scoreAct)
			this.scoreMax = scoreAct;
			
		this.scoreAct = 0;
		this.nbCoup   = 0;
	}
	
	//affiche dans la console le tableau
	public String toString()
	{
		String ch = "2048 | " + this.scoreAct + " | " + this.scoreMax + "\n\n";
		
		for(int i=0;i<tab2048.length;i++)
		{
			for(int j=0;j<tab2048[0].length;j++)
				ch += String.format("%4d", tab2048[i][j]);
				
			ch += "\n";
		}
		
		return ch;
	}
	
	//revoie le score actuel
	public int getScoreAct()
	{
		return this.scoreAct;
	}
	
	//revoie le nombres de coups
	public int getNbCoup()
	{
		return this.nbCoup;
	}
	
	//revoie le meilleur score
	public int getScoreMax()
	{
		return this.scoreMax;
	}
	
	//modifie le meilleur score
	public void setScoreMax(int val)
	{
		this.scoreMax = val;
	}
	
	//permet de tester rapidement la class Console2048
	public static void main(String [] arg) 
	{
		String s 	 	 = " ";
		Scanner scIn 	 = new Scanner(System.in);
		Model test = new Model();
		
		while(!test.gagne())
		{
			if(!test.perdu()) break;
			
			System.out.println(test);
			
			System.out.println("Entrez une direction ( N, E, O, S )");
			s = scIn.next();
			test.coupAdd(s);
		}
		
		System.out.println(test);
	}
}