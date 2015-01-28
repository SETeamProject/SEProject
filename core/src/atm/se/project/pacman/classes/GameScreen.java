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
	
	private final float TIMESTEP=1/1000f;
	private final int VELOCITY=1;    //8;
	private final int POZITIE=3;   //3;
	
	
	private float speed=1000;
	private Vector2 movement=new Vector2();
	private Body c;
	
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		world =new World(new Vector2(0, -9.81f), true);
		debugRenderer=new Box2DDebugRenderer();
		camera=new OrthographicCamera();

		Gdx.input.setInputProcessor(new InputESC(){
			@Override
			public boolean keyDown(int keycode) {
				
				switch(keycode)
				{
				case Keys.ESCAPE:	
					((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
					break;
				case Keys.A:
					movement.x=-speed;
					break;
				case Keys.D:
					movement.x=speed;
					break;
				case Keys.S:
					movement.y=-speed;
					break;
				case Keys.W:
					movement.y=speed;
					break;
					
				}	
				return true;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				switch(keycode)
				{
				case Keys.W:
				case Keys.S:
					movement.y=0;
					break;
				case Keys.A:
				case Keys.D:
					movement.x=0;
					break;
					
				}	
				return true;
			}
		});
		
		BodyDef body=new BodyDef();
		body.type=BodyType.DynamicBody;
		body.position.set(0, 1);
		
		CircleShape shape=new CircleShape();
		shape.setRadius(0.45f);
		
		

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
		fixture.restitution=.35f;

		c= world.createBody(body);
		c.createFixture(fixture);
		
		shape.dispose();
		
		c.applyAngularImpulse(0, true);
		
		
		
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
		
		//zidurile pentru primul chenar
		//zid1
		body.type=BodyType.StaticBody;
		
		Integer[] positionVect={10,5,-1,-8,10,-5,5,8,-6,8,-10,3,-10,-6,0,6,-8,-1,1,-6,8,2,8,-5,4,4,-3,4,-6,0,-1,-4,6,-2,0,-2,4,0,0,2};
		float[] coordVect = {0.1f,3.0f,9.0f,0.1f,0.1f,4.0f,5.0f,0.1f,4.0f,0.1f,
				             0.1f,5.0f,0.1f,2.0f,8.0f,0.1f,0.1f,5.0f,7.0f,0.1f,
				             0.1f,4.0f,0.1f,1.0f,2.0f,0.1f,3.0f,0.1f,0.1f,4.0f,
				             5.0f,0.1f,0.1f,4.0f,4.0f,0.1f,0.1f,2.0f,4.0f,0.1f};
		
		PolygonShape[] wallVect= new PolygonShape[20];
		
		int j=0;
		
		for (int i = 0; i < 40; i+=2) {
			
			wallVect[j]= new PolygonShape();
			body.position.set(positionVect[i], positionVect[i+1]);
			wallVect[j].setAsBox(coordVect[i], coordVect[i+1]);
			
			fixture.shape=wallVect[j];
			fixture.friction=50f;
			fixture.restitution=0f;
			fixture.density=5;
			
			world.createBody(body).createFixture(fixture);
			
			wallVect[j].dispose();
			j++;
		}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//daca vreau sa mut camera
		//camera.position.set(c.getPosition().x, c.getPosition().y, 0);
		//camera.update();
		
		debugRenderer.render(world, camera.combined);
		
		world.step(TIMESTEP, VELOCITY, POZITIE);
		
		c.applyForceToCenter(movement, true);
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
