package baba;

import javax.swing.*;

public class Boutton extends JFrame  {
	public Boutton(boolean bombe, boolean decouvert) {
		this.boutton = new JButton();
		this.bombe = bombe;
		this.decouvert = decouvert;
	}
	public int getBombeAdjacente() {
		return this.nbBombe;
	}
	public void setBombeAdjacente(int nbBombe) {
		this.nbBombe = nbBombe;
	}
	public boolean getBombe() {
		return this.bombe;
	}
	public void setBombe(boolean bombe) {
		this.bombe = bombe;
	}
	public boolean getDecouvert() {
		return this.decouvert;
	}
	public void setDecouvert(boolean decouvert) {
		this.decouvert = decouvert;
	}
	public boolean getDrapeau() {
		return this.drapeau;
	}
	
	//les attributs
	JButton boutton;
	int nbBombe;
	boolean bombe;
	boolean decouvert;
	boolean drapeau;
}
