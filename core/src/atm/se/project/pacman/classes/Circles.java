package atm.se.project.pacman.classes;


import atm.se.project.pacman.interfaces.Shape;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


public class Circles implements Shape{

	static final float raza = 1f;
	
	Vector2 position = new Vector2();
	Circle cerc = new Circle();

	@Override
	public void draw(Vector2 pos) {
		this.position=pos;
		this.cerc.radius=raza;
	}
}
