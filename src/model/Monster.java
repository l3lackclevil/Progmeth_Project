package model;

import java.util.ArrayList;
import java.util.List;

import gameLogic.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import scene.GamePlayScreen;

public class Monster extends Entity {
	protected double currentMaxHp;
	protected double currentHp;
	protected double currentExp;
	private List<Double> maxHp = new ArrayList<>();
	private List<Integer> Exp = new ArrayList<>();
	final private List<Double> multipleHp = new ArrayList<>();
	final private int multipleExp = 3;
	final private double growthRateHp = 1.2;
	final private double growthRateExp = 1.2;
	final private int numMonster = 4;
	private Image monImg1, monImg2, monImg3;
	

	public Monster(String name, int level) {
		super(name, level);
		setmaxHp();
		setMultipleHp();
		setExp();
		this.currentMaxHp = this.maxHp.get(0);
		this.currentHp = (double) this.currentMaxHp;
		this.currentExp = this.Exp.get(0);

		monImg1 = new Image("9.png");
		monImg2 = new Image("9.png");
		monImg3 = new Image("9.png");
		this.isVisible = true;
	}

	public void setmaxHp() {
		this.maxHp.add(1000.0);
		this.maxHp.add(2000.0);
		this.maxHp.add(3000.0);
	}

	public void setMultipleHp() {
		this.multipleHp.add(2.5);
		this.multipleHp.add(2.0);
		this.multipleHp.add(1.8);
		this.multipleHp.add(15.0 / 9.00);
		this.multipleHp.add(22.0 / 15.00);
		this.multipleHp.add(32.0 / 22.00);

	}

	public void setExp() {
		this.Exp.add(150);
		this.Exp.add(300);
		this.Exp.add(500);
	}

	public List<Double> getMultipleHp() {
		return multipleHp;
	}

	public int getMultipleExp() {
		return multipleExp;
	}

	public double getCurrentMaxHp() {
		return currentMaxHp;
	}

	public void setCurrentMaxHp(int currentMaxHp) {
		this.currentMaxHp = currentMaxHp;
	}

	public Double getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(Double currentHp) {
		this.currentHp = currentHp;
	}

	public List<Double> getMaxHp() {
		return maxHp;
	}

	public List<Integer> getExp() {
		return Exp;
	}

	public boolean isDead() {
		if (this.currentHp <= 0) {
			return true;
		}
		return false;
	}

	public boolean isUpgrade() {
		if (GameManager.getCurrentCha().getLevel() == 5 || GameManager.getCurrentCha().getLevel() == 9 ||
				GameManager.getCurrentCha().getLevel() == 12 || GameManager.getCurrentCha().getLevel() == 15 ||
				GameManager.getCurrentCha().getLevel() == 17 || GameManager.getCurrentCha().getLevel() == 19) {
			return true;
		}
		return false;
	}

	public void update(double atk) {
		
		this.decreaseHp(atk);
		
		if (this.isDead()) {
			GameManager.getCurrentCha().update(this.currentExp);
			if (this.isUpgrade()) {
				this.upgradeMonster();
			} else {
				if(GameManager.getcurrentNumMon()==3) {
					this.levelUp();
				}
				this.newMonster();
			}
			this.level++;
			System.out.println('\n'+"now level" + this.level +"Hp"+ this.currentMaxHp+'\n');
		}
	}

	public void newMonster() {
		if(GameManager.getcurrentNumMon()==3) 
			GameManager.setcurrentNumMon(0);
		GameManager.setcurrentNumMon((GameManager.getcurrentNumMon()+1)%4);
		this.currentMaxHp = this.maxHp.get(GameManager.getcurrentNumMon()-1);
		this.currentHp = this.currentMaxHp;
		this.currentExp = this.Exp.get(GameManager.getcurrentNumMon()-1);
		
	}

	public void upgradeMonster() {
		
		
		int index = 0;
		if (GameManager.getCurrentCha().getLevel() == 5) {
			index = 0;
		} else if (GameManager.getCurrentCha().getLevel() == 9) {
			index = 1;
		} else if (GameManager.getCurrentCha().getLevel() == 12) {
			index = 2;
		} else if (GameManager.getCurrentCha().getLevel() == 15) {
			index = 3;
		} else if (GameManager.getCurrentCha().getLevel() == 17) {
			index = 4;
		} else if (GameManager.getCurrentCha().getLevel() == 19) {
			index = 5;
		}

		for (int i = 0; i < this.maxHp.size(); i++) {
			this.maxHp.set(i, this.maxHp.get(i) * this.multipleHp.get(index));
		}
		for (int i = 0; i < this.Exp.size(); i++) {
			this.Exp.set(i, this.Exp.get(i) * this.multipleExp);
		}
		this.currentMaxHp = this.maxHp.get(0);
		this.currentHp = this.currentMaxHp;
		this.currentExp = this.Exp.get(0);

	}

	public void levelUp() {
	
		for (int i = 0; i < this.maxHp.size(); i++) {
			this.maxHp.set(i, this.maxHp.get(i) * this.growthRateHp);
		}
		for (int i = 0; i < this.Exp.size(); i++) {
			this.Exp.set(i, (int)(this.Exp.get(i) * this.growthRateExp));
		}
		
	}

	public void decreaseHp(double atk) {
		this.currentHp -= atk;
	}

	public void draw(GraphicsContext gc, double x, double y) {
		if (GameManager.getcurrentNumMon() == 1) {
			gc.drawImage(monImg1, x, y, monImg1.getWidth() / 3, monImg1.getHeight() / 3);
		} else if (GameManager.getcurrentNumMon() == 2) {
			gc.drawImage(monImg2, x, y, monImg2.getWidth() / 3, monImg2.getHeight() / 3);
		} else {
			gc.drawImage(monImg3, x, y, monImg3.getWidth() / 3, monImg3.getHeight() / 3);
		}
		

	}

	@Override
	public String getName() {
		if (GameManager.getcurrentNumMon() == 1) {
			return "Monster1";
		} else if (GameManager.getcurrentNumMon() == 2) {
			return "Monster2";
		} else {
			return "Monster3";
		}
	}

}
