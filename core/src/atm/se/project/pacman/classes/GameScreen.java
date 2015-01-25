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
		body.position.set(10f, 5f);
		
		
		PolygonShape zid1 = new PolygonShape();
		zid1.setAsBox(.1f, 3);
		
		fixture.shape=zid1;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
		
		
		
		world.createBody(body).createFixture(fixture);
		
		zid1.dispose();
		
		//zid2
		body.type=BodyType.StaticBody;
		body.position.set(-1f, -8f);
		
		
		PolygonShape zid2 = new PolygonShape();
		zid2.setAsBox(9f, 0.1f);
				
		fixture.shape=zid2;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
		
		world.createBody(body).createFixture(fixture);
		zid2.dispose();
		
		//zid3
		body.type=BodyType.StaticBody;
		body.position.set(10f, -4f);//pozitia stanga, sus
				
				
		PolygonShape zid3 = new PolygonShape();
		zid3.setAsBox(.1f, 4f);//latime, lungime
				
		fixture.shape=zid3;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
				
		world.createBody(body).createFixture(fixture);
				
		zid3.dispose();
		
		//zid4
		body.type=BodyType.StaticBody;
		body.position.set(5.1f, 8f);//pozitia stanga, sus
				
				
		PolygonShape zid4 = new PolygonShape();
		zid4.setAsBox(5f, 0.1f);//latime, lungime
						
		fixture.shape=zid4;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
				
		world.createBody(body).createFixture(fixture);
		zid4.dispose();
		
		//zid5
		body.type=BodyType.StaticBody;
		body.position.set(-6.1f, 8f);//pozitia stanga, sus
						
						
		PolygonShape zid5 = new PolygonShape();
		zid4.setAsBox(4f, 0.1f);//latime, lungime
								
		fixture.shape=zid5;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
						
		world.createBody(body).createFixture(fixture);
		zid5.dispose();
		
		//zid6
		body.type=BodyType.StaticBody;
		body.position.set(-10f, 3f);
				
				
		PolygonShape zid6 = new PolygonShape();
		zid1.setAsBox(.1f, 5);
				
		fixture.shape=zid6;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
					
		world.createBody(body).createFixture(fixture);
				
		zid6.dispose();	
		
		//zid7
		body.type=BodyType.StaticBody;
		body.position.set(-10f, -6.1f);//pozitia stanga, sus
				
				
		PolygonShape zid7 = new PolygonShape();
		zid7.setAsBox(.1f, 2f);//latime, lungime
				
		fixture.shape=zid7;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
				
		world.createBody(body).createFixture(fixture);
				
		zid7.dispose();
		
		//zidurile pentru al 2 lea chenar
		//zid8
		body.type=BodyType.StaticBody;
		body.position.set(0f, 6f);
				
				
		PolygonShape zid8 = new PolygonShape();
		zid8.setAsBox(8f, 0.1f);
						
		fixture.shape=zid8;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
				
		world.createBody(body).createFixture(fixture);
		zid8.dispose();
		
		//zid9
		body.type=BodyType.StaticBody;
		body.position.set(-8f, -1f);//pozitia stanga, sus
						
						
		PolygonShape zid9 = new PolygonShape();
		zid9.setAsBox(.1f, 5f);//latime, lungime
						
		fixture.shape=zid9;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
						
		world.createBody(body).createFixture(fixture);
						
		zid9.dispose();	
		
		//zid10
		body.type=BodyType.StaticBody;
		body.position.set(1f, -6f);
						
						
		PolygonShape zid10 = new PolygonShape();
		zid10.setAsBox(7f, 0.1f);
								
		fixture.shape=zid10;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
						
		world.createBody(body).createFixture(fixture);
		zid10.dispose();
		
		//zid11
		body.type=BodyType.StaticBody;
		body.position.set(8f, 2.1f);//pozitia stanga, sus
								
								
		PolygonShape zid11 = new PolygonShape();
		zid11.setAsBox(.1f, 4f);//latime, lungime
								
		fixture.shape=zid11;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
								
		world.createBody(body).createFixture(fixture);
								
		zid11.dispose();
		
		//zid12
		body.type=BodyType.StaticBody;
		body.position.set(8f, -5.1f);//pozitia stanga, sus
										
										
		PolygonShape zid12 = new PolygonShape();
		zid12.setAsBox(.1f, 1f);//latime, lungime
										
		fixture.shape=zid12;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
										
		world.createBody(body).createFixture(fixture);
										
		zid12.dispose();
		
		//zidurile pentru al 3 lea chenar
		//zid13
		body.type=BodyType.StaticBody;
		body.position.set(4f, 4f);
						
						
		PolygonShape zid13 = new PolygonShape();
		zid13.setAsBox(2f, 0.1f);
								
		fixture.shape=zid13;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
						
		world.createBody(body).createFixture(fixture);
		zid13.dispose();
		
		//zid14
		body.type=BodyType.StaticBody;
		body.position.set(-3f, 4f);
								
								
		PolygonShape zid14 = new PolygonShape();
		zid14.setAsBox(3f, 0.1f);
										
		fixture.shape=zid14;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
								
		world.createBody(body).createFixture(fixture);
		zid14.dispose();
		
		//zid15
		body.type=BodyType.StaticBody;
		body.position.set(-6f, 0.1f);//pozitia stanga, sus
										
										
		PolygonShape zid15 = new PolygonShape();
		zid15.setAsBox(.1f, 4f);//latime, lungime
										
		fixture.shape=zid15;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
										
		world.createBody(body).createFixture(fixture);
										
		zid15.dispose();
		
		//zid16
		body.type=BodyType.StaticBody;
		body.position.set(-1.1f, -4f);
								
								
		PolygonShape zid16 = new PolygonShape();
		zid16.setAsBox(5f, 0.1f);
										
		fixture.shape=zid16;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
								
		world.createBody(body).createFixture(fixture);
		zid16.dispose();
		
		//zid17
		body.type=BodyType.StaticBody;
		body.position.set(6f, -2.1f);//pozitia stanga, sus
												
												
		PolygonShape zid17 = new PolygonShape();
		zid17.setAsBox(.1f, 4f);//latime, lungime
												
		fixture.shape=zid17;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
												
		world.createBody(body).createFixture(fixture);
												
		zid17.dispose();
		
		//zid18
		body.type=BodyType.StaticBody;
		body.position.set(0.0f, -2f);
										
										
		PolygonShape zid18 = new PolygonShape();
		zid18.setAsBox(4f, 0.1f);
												
		fixture.shape=zid18;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
										
		world.createBody(body).createFixture(fixture);
		zid18.dispose();
		
		//zid19
		body.type=BodyType.StaticBody;
		body.position.set(3.9f, -0.1f);//pozitia stanga, sus
														
														
		PolygonShape zid19 = new PolygonShape();
		zid19.setAsBox(.1f, 2f);//latime, lungime
														
		fixture.shape=zid19;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
														
		world.createBody(body).createFixture(fixture);
														
		zid19.dispose();
		
		//zid20
		body.type=BodyType.StaticBody;
		body.position.set(0.0f, 1.9f);
												
												
		PolygonShape zid20 = new PolygonShape();
		zid20.setAsBox(4f, 0.1f);
														
		fixture.shape=zid20;
		fixture.friction=0f;
		fixture.restitution=0f;
		fixture.density=5;
												
		world.createBody(body).createFixture(fixture);
		zid20.dispose();
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
