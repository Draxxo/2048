package controler;

import model.Model;

public class Controler {

	private Model model;
	
	public Controler() {
		this.model = new Model();
	}

	public int getScoreAct() {
		return this.model.getScoreAct();
	}

	public int getScoreMax() {
		return this.model.getScoreMax();
	}

	public int getNbCoup() {
		return this.model.getNbCoup();
	}

	public int getTab2048(int i, int j) {
		return this.model.getTab2048(i, j);
	}

	public void reset() {
		this.model.reset();
	}

	public void setPerdu() {
		this.model.setPerdu();
	}

	public boolean perdu() {
		return this.model.perdu();
	}

	public void coupAdd(String str) {
		this.model.coupAdd(str);
	}

	public boolean gagne() {
		return this.model.gagne();
	}

	public void setScoreMax(int scoreAct) {
		this.model.setScoreMax(scoreAct);
	}
}
