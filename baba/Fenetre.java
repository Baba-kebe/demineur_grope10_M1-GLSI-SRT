  package baba;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class Fenetre extends JFrame implements ActionListener, MouseListener{
	public Fenetre() {
		setTitle("Démineur en java");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contenu = this.getContentPane();
		
		// creation des barres de menu
		  barreMenus = new JMenuBar();
		  setJMenuBar(barreMenus);
		  
		  //creationMenu
		  nvllepartie = new JMenu("Nouvelle partie");
	      statistiques  = new JMenu("Statistiques du jeu");
	      pause = new JMenu("Pause");
	      pause.addActionListener(this);
	      reprendre = new JMenu("Reprendre");
	      reprendre.addActionListener(this);
	      help = new JMenu("help");
	      
	      //ajout du menu sur la fenetre
	      
		  barreMenus.add(nvllepartie);
		  barreMenus.add(statistiques);
		  barreMenus.add(pause);
		  barreMenus.add(reprendre);
		  barreMenus.add(help);
		  
		  //ajout des sous menus en y incluant une actionlistener
		  
		  e1 = new JMenuItem("Niveau debutant");
	      nvllepartie.add(e1);
	      e1.addActionListener(this);
	      
	      e2 = new JMenuItem("Niveau intermediaire");
	      nvllepartie.add(e2);
	      e2.addActionListener(this);
	      
	      e3 = new JMenuItem("Niveau expert");
	      nvllepartie.add(e3);
	      e3.addActionListener(this);
	      
	      e4 = new JMenuItem("stats");
	      statistiques.add(e4);
	      e4.addActionListener(this);
	      
	}
// les redirection lors du clic sur les sous menu
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource(); // pour savoir la source qui a declencher l'action parmi nos actionListener
		
		if(panelJeu != null) remove(panelJeu);
		if(panelText != null) remove(panelText);
		
		if(source == e1) {
			
			nb_partie++;
	
			nb_lignes = 9;
			nb_colonnes = 9;
			nombreBombe = 10;
			
			bouttons = new Boutton[nb_lignes][nb_colonnes];
			
			panelJeu = new JPanel();
			panelJeu.setLayout(new GridLayout(nb_lignes,nb_colonnes));
			
				for(int i=0; i<nb_lignes; i++) {
					for(int j=0; j<nb_colonnes; j++) {
						if (Math.random() * 100 < (nombreBombe * 100)/(nb_lignes * nb_colonnes)) {
							bouttons[i][j] = new Boutton(true, false); 
							nb_bombe_cacher++;
							panelJeu.add(bouttons[i][j].boutton);
							bouttons[i][j].boutton.addMouseListener(this);
							bouttons[i][j].boutton.addActionListener(this);
						}else {
							bouttons[i][j] = new Boutton(false, false);
							panelJeu.add(bouttons[i][j].boutton);
							bouttons[i][j].boutton.addMouseListener(this);
							bouttons[i][j].boutton.addActionListener(this);
							
						}
							
					}
				}	
		}else if(source == e2) {
			
			nb_partie++;
			
			nb_lignes = 16;
			nb_colonnes = 16;
			nombreBombe = 40;
			
			bouttons = new Boutton[nb_lignes][nb_colonnes];
			
			panelJeu = new JPanel();
			panelJeu.setLayout(new GridLayout(nb_lignes,nb_colonnes)); 
			
				for(int i=0; i<nb_lignes; i++) {
					for(int j=0; j<nb_colonnes; j++) {
						if ((Math.random() * 100 < ((nombreBombe * 100) / (nb_lignes * nb_colonnes)))) {
							bouttons[i][j] = new Boutton(true, false);
							panelJeu.add(bouttons[i][j].boutton);
							nb_bombe_cacher++;
							bouttons[i][j].boutton.addMouseListener(this);
						}else {
							bouttons[i][j] = new Boutton(false, false);
							panelJeu.add(bouttons[i][j].boutton);
							bouttons[i][j].boutton.addMouseListener(this);
						}
							
					}
				}
			
		}else if(source == e3) {
			
			nb_partie++;
			
			nb_lignes = 16;
			nb_colonnes = 30;
			nombreBombe = 99;
			
			bouttons = new Boutton[nb_lignes][nb_colonnes];
			
			panelJeu = new JPanel();
			panelJeu.setLayout(new GridLayout(nb_lignes,nb_colonnes));
			
				for(int i=0; i<nb_lignes; i++) {
					for(int j=0; j<nb_colonnes; j++) {
						if (Math.random() * 100 < (nombreBombe * 100)/(nb_lignes * nb_colonnes)) {
							bouttons[i][j] = new Boutton(true, false);
							nb_bombe_cacher++;
							panelJeu.add(bouttons[i][j].boutton);
							bouttons[i][j].boutton.addMouseListener(this);
						}else {
							bouttons[i][j] = new Boutton(false, false);
							panelJeu.add(bouttons[i][j].boutton);
							bouttons[i][j].boutton.addMouseListener(this);
						}
							
					}
				}
		}else if(source == pause) {
			if(timer.isRunning()) timer.stop();
		}else if(source == reprendre) {
			if(!timer.isRunning()) timer.restart();
		}
		if(source == e4) {
			
			nb_partie = nb_partie_gagne + nb_partie_perdu;
			
			panelJeu = new JPanel();
			panelJeu.setLayout(new GridLayout(1,3));
			lb = new JLabel("Nombre de parties jouees :  "+nb_partie+"\n"+
			" Nombre de parties perdu : "+nb_partie_perdu+"\n"+
					" Nombre de partie gagner: "+ nb_partie_gagne);
			panelJeu.add(lb);
		}
		nbCases = nb_lignes * nb_colonnes;
		// pour reinitialiser le chrono avant de redemarrer une partie
		chrono = 180;

		// un label indiquant le nombre de bombe par defaut du jeu en f(niveau) 
		
		lb = new JLabel("Nombre de Bombe : "+ nombreBombe);
		Font font = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 24);
		lb.setFont(font);
		panelText = new JPanel();
		panelText.setLayout(new GridLayout(1,2));
		panelText.add(lb);
		
		// un label pour le chronometrage
		
		ch = new JLabel("Temps restantes :"+ chrono);
		ch.setFont(new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 24));
		panelText.add(ch);
		
		contenu.add(panelJeu, BorderLayout.CENTER);
		contenu.add(panelText, BorderLayout.AFTER_LAST_LINE);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// on demarre le le timer 
		if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) timer.start();
		nb_partie++;
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		
		JButton bp = (JButton) e.getSource();
		int x = 0;
		int y = 0;
		
		for(int i=0; i<nb_lignes;i++) {
			for(int j = 0; j<nb_colonnes; j++) {
				if(bp == bouttons[i][j].boutton) {
					x = i;
					y = j;
				}
			}
		}
		if(e.getButton() == MouseEvent.BUTTON1) {
			//si cest le premier on decremente le nombre de cases restantes a decouvrir
			if(e.getClickCount() == 1) {
				nbCases--;
			}
			bouttons[x][y].setDecouvert(true);
			if(bouttons[x][y].getBombe() || chrono == 0) {
				bouttons[x][y].boutton.setText("💣");
				bouttons[x][y].boutton.setFont(new Font("Italic", Font.LAYOUT_RIGHT_TO_LEFT, 25));
				timer.stop();
				
				// partie perdu
				nb_partie_perdu++;
				
				JOptionPane.showMessageDialog(panelJeu, "vous avez perdu la partie");
				// on supprime la grille et le text en bas
				remove(panelJeu);
				remove(panelText);
				
			}
			else {
				int nb = 0;
				for(int i =-1; i<2; i++) {
					for(int j =-1; j<2; j++) {
						if((i!=0 || j != 0) && x+i >= 0 && i+x < nb_lignes && j+y >= 0 && j+y < nb_colonnes ) {
							if(bouttons[i+x][j+y].getBombe()) nb++;
						}
					}
				}
				bouttons[x][y].boutton.setText(nb+"");
				bouttons[x][y].boutton.setFont(new Font("Italic", Font.LAYOUT_RIGHT_TO_LEFT, 25));
			}
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			if(e.getClickCount() == 1) {
				if(bouttons[x][y].boutton.getText().equals("")) {
					bouttons[x][y].boutton.setText("🚩");
					bouttons[x][y].boutton.setFont(new Font("Italic", Font.LAYOUT_RIGHT_TO_LEFT, 25));
				}
				else if(bouttons[x][y].boutton.getText().equals("🚩"))  {
					bouttons[x][y].boutton.setText("?");
					bouttons[x][y].boutton.setFont(new Font("Italic", Font.LAYOUT_RIGHT_TO_LEFT, 25));
				}
				else if(bouttons[x][y].boutton.getText().equals("?")) {
					bouttons[x][y].boutton.setText("");
					bouttons[x][y].boutton.setFont(new Font("Italic", Font.LAYOUT_RIGHT_TO_LEFT, 25));
				}
			}
		}
		
		if(nbCases == nb_bombe_cacher) {
			JOptionPane.showMessageDialog(panelJeu, "Felicitations, Vous avez gegner la partie");
			remove(panelJeu);
			remove(panelText); 
			
			nb_partie_gagne++;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	Container contenu;
	JMenuBar barreMenus;
	JMenu help, nvllepartie, statistiques, pause, reprendre ;
	JMenuItem e1, e2, e3, e4, e5, e6, e7;
	JPanel panelJeu, panelText;
	Boutton[][] bouttons;
	int nb_lignes , nb_colonnes, nombreBombe; //ligne et colone du jeu variant en fonction du niveau
	
	JLabel lb, ch;
	int nb_bombe_cacher;
	int nbCases;
	int chrono = 120;
	
	int nb_partie_gagne, nb_partie_perdu, nb_partie;
	
	// Modifier le texte du boutton en f(temps) 
	Timer timer = new Timer(1000, e ->{
		if(chrono> 0) {
			chrono --;
			ch.setText("Temps Restants : " +chrono);
		}else {
			return;
		}
	});
  
}
