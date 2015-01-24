package atm.se.project.pacman.classes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.xml.internal.bind.CycleRecoverable;

public class GameScreen implements Screen {

	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	
	private final float TIMESTEP=1/60f;
	private final int VELOCITY=8;
	private final int POZITIE=3;
	

	
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		world =new World(new Vector2(0, -9.81f), true);
		debugRenderer=new Box2DDebugRenderer();
		camera=new OrthographicCamera();

		Gdx.input.setInputProcessor(new InputESC(){
			@Override
			public boolean keyDown(int keycode) {
				
				if(keycode==Keys.ESCAPE)
					((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
				return true;
			}
		});
		
		BodyDef body=new BodyDef();
		body.type=BodyType.DynamicBody;
		body.position.set(0, 1);
		
		CircleShape shape=new CircleShape();
		shape.setRadius(0.5f);
		

		/*Vector2[] vect=new Vector2[4];
		
		vect[0]=new Vector2(30f,-25f);
		vect[3]=new Vector2(-30f,-25f);
		vect[2]=new Vector2(-30f,25f);
		vect[1]=new Vector2(30f,25f);
		*/
		FixtureDef fixture=new FixtureDef();
		fixture.shape=shape;
		fixture.density= 2.5f;
		fixture.friction=.25f;
		fixture.restitution=.75f;

		world.createBody(body).createFixture(fixture);
		
		shape.dispose();
		
		//tabla
		body.type=BodyType.StaticBody;
		body.position.set(0, 0);
		
		
		
		//PolygonShape ground=new PolygonShape();
		//ground.set(vect);
		
		ChainShape ground=new ChainShape();
		ground.createChain(new Vector2[]{new Vector2(12f,-10f),new Vector2(-12f,-10f),new Vector2(-12f,10f),new Vector2(12f,10f),new Vector2(12f,-10f)});
		
		fixture.shape=ground;
		fixture.friction=.5f;
		fixture.restitution=0;
		
		world.createBody(body).createFixture(fixture);
		
		ground.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		debugRenderer.render(world, camera.combined);
		
		world.step(TIMESTEP, VELOCITY, POZITIE);
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		camera.viewportWidth=width/25;
		camera.viewportHeight=height/25;
		
		camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

		world.dispose();
		debugRenderer.dispose();
	}

}
