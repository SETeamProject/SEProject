package atm.se.project.pacman.classes;


import atm.se.project.pacman.interfaces.Shape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Squares implements Shape{
	
	static final float muchie = 1f;
	
	Vector2 position = new Vector2();
	Rectangle square = new Rectangle();

	@Override
	public void draw(Vector2 pos) {
		this.position=pos;
		this.square.height=muchie;
		this.square.width=muchie;
	}
}
