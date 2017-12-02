package svs.amc.tomz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import svs.amc.tomz.gp.*;

public class Lcynte extends ApplicationAdapter
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static short screenWidth;
	public static short screenHeight;

	public static SpriteBatch spriteBatch;
	public static SpriteBatch spriteBatch2;

	public static BitmapFont font;

	public static Condition condition;

	//public static Lcynte lcynte;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/

/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void create ()
	{
		screenWidth = (short)Gdx.graphics.getWidth();
		screenHeight = (short)Gdx.graphics.getHeight();

		spriteBatch = new SpriteBatch();
		spriteBatch2 = new SpriteBatch();

		Gdx.input.setInputProcessor(new InputPrcs());
		Gdx.input.setCatchBackKey(true);

		Cursor.init();

		GameProcess.shapeRenderer = new ShapeRenderer();

		FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator
				(
					Gdx.files.internal("Picasso.ttf")
				);
		FreeTypeFontGenerator.FreeTypeFontParameter ftfp = new FreeTypeFontGenerator
															.FreeTypeFontParameter();
		ftfp.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		ftfp.size = (int)(screenHeight*0.03f);
		ftfp.color = Color.WHITE;
		font = ftfg.generateFont(ftfp);

		ftfg.dispose();

		TextComponent.size = (short)(Lcynte.screenWidth / 6);
		Button_M.height = (short)(311*TextComponent.size / 298);

		Button_M.sound = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-menu-03-b.wav"));

		condition = new Menu();

		Thread cursorThread = new Thread()
		{
			public void run()
			{
				do
				{
					synchronized (Cursor.cursorMutex)
					{
						Cursor.move();
					}

					try
					{
						Thread.sleep(1);
					}

					catch (InterruptedException exc)
					{				}
				}
				while (true);
			}
		};
		cursorThread.start();
	}

	public void render()
	{
		/*spriteBatch.begin();

		spriteBatch.end();*/
		condition.dameute();

		spriteBatch.begin();
		synchronized (Cursor.cursorMutex)
		{
			Cursor.dameute();
		}
		spriteBatch.end();
	}

	@Override
	public void dispose()
	{
		/*for (byte i = 0; i < Cell.sqcImage.length; i++)
			Cell.sqcImage[i].dispose();

		for (byte i = 0; i < Element.sqcImage.length; i++)
			Element.sqcImage[i].dispose();

		condition.dispose();

		spriteBatch.dispose();*/
	}
}


/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
