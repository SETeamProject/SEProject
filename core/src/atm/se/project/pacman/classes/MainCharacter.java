package atm.se.project.pacman.classes;

import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Vector2;

public class MainCharacter {
	
	public enum Stari{
		IDLE, MOVING,DEAD
	}
	
	static final float speed = 2f;
	static final float size = 0.5f;
	
	Vector2 pozitie = new Vector2();
	Vector2 acceleratie = new Vector2();
	Rectangle margini = new Rectangle();
	
	Stari stare = Stari.IDLE;
	boolean to_left = true;
	
	public MainCharacter(Vector2 pozitie){
		this.pozitie = pozitie;
		this.margini.height = size;
		this.margini.width = size;
	}
}
