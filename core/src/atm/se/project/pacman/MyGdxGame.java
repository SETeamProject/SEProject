package atm.se.project.pacman;

import atm.se.project.pacman.classes.MainMenu;
import atm.se.project.pacman.splash.Splash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("pac.jpg");
		setScreen(new MainMenu());
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 250, 400);
		//batch.end();
		super.render();
	}

	@Override
	public void resize(int width, int height) {
			super.resize(width, height);
	}

	@Override
	public void pause() {
			super.pause();
	}

	@Override
	public void resume() {
			super.resume();
	}

	@Override
	public void dispose() {
			super.dispose();
	}
}
