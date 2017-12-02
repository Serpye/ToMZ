package svs.amc.tomz.gp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Enterante;
import svs.amc.tomz.Lcynte;


/**
 * Created by serpye on 6/11/17.
 */
public class Menu extends Condition
{
/*::|	FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Button_M buttonNewGame;
	public static Button_M buttonOptions;
	public static Button_M buttonClose;
	/*::|	CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Menu()
	{
		isMenu = true;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/03_menu_2.mp3"));
		music.setLooping(true);
		music.play();

		short ofs = (short)((Lcynte.screenHeight - 3*Button_M.height) >> 2);

		String string;
		if (Gdx.files.internal("file").readString().equals("1 0 0"))
			string = "New Game";

		else
			string = "Continue";

		buttonNewGame = new Button_M
					(
						(Lcynte.screenWidth - TextComponent.size) >> 1,
						Lcynte.screenHeight - Button_M.height - ofs,
						string
					);

		buttonNewGame.ofsXText -= 0.4f*buttonNewGame.ofsXText;

		ActionClass actionClass = new ActionClass()
		{
			public void onClick()
			{
				dispose();
				Lcynte.condition = new GameProcess();
			}
		};
		buttonNewGame.actionClass = actionClass;

		buttonOptions = new Button_M
					(
						buttonNewGame.x,
						buttonNewGame.y - Button_M.height - ofs,
						"Options"
					);

		buttonOptions.ofsXText -= 0.2f*buttonOptions.ofsXText;
		actionClass = new ActionClass()
		{
			public void onClick()
			{
				++Enterante.level;
				GameProcess.save();
			}
		};
		buttonOptions.actionClass = actionClass;

		buttonClose = new Button_M
				(
					buttonOptions.x,
					buttonOptions.y - Button_M.height - ofs,
					"Close"
				);
		actionClass = new ActionClass()
		{
			public void onClick()
			{
				System.exit(0);
			}
		};
		buttonClose.actionClass = actionClass;

		TextComponent.remise = 0.2f;
		TextComponent.neurelle = 1f;
		TextComponent.aluese = 0.41f;

		imageBackground = new Texture(Gdx.files.internal("background/menuBackground.jpg"));

		Thread animation = new Thread()
		{
			public void run()
			{	
				do
				{
					if (buttonNewGame.animation == 1)
					{
						if (buttonNewGame.textureFrame < 9)
							buttonNewGame.textureFrame += 2;

						if (buttonNewGame.textureFrame >= 9)
						{
							buttonNewGame.animation = 0;
							buttonNewGame.textureFrame = 9;
						}
					}

					else if (buttonNewGame.animation == 2)
					{
						if (buttonNewGame.textureFrame == 0)
							buttonNewGame.animation = 0;

						else
							buttonNewGame.textureFrame--;
					}

					if (buttonOptions.animation == 1)
					{
						if (buttonOptions.textureFrame < 9)
							buttonOptions.textureFrame += 2;

						if (buttonOptions.textureFrame >= 9)
						{
							buttonOptions.animation = 0;
							buttonOptions.textureFrame = 9;
						}
					}

					else if (buttonOptions.animation == 2)
					{
						if (buttonOptions.textureFrame == 0)
							buttonOptions.animation = 0;

						else
							buttonOptions.textureFrame--;
					}

					if (buttonClose.animation == 1)
					{
						if (buttonClose.textureFrame < 9)
							buttonClose.textureFrame += 2;

						if (buttonClose.textureFrame >= 9)
						{
							buttonClose.animation = 0;
							buttonClose.textureFrame = 9;
						}
					}

					else if (buttonClose.animation == 2)
					{
						if (buttonClose.textureFrame == 0)
							buttonClose.animation = 0;

						else
							buttonClose.textureFrame--;
					}

					try
					{
						Thread.sleep(50);
					}

					catch (InterruptedException exc)
					{				}
				}
				while (isMenu);
			}
		};
		animation.start();
	}
/*::|	F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void dameute()
	{
		Lcynte.spriteBatch.begin();

		Lcynte.spriteBatch.draw(imageBackground, 0, 0, Lcynte.screenWidth, Lcynte.screenHeight);

		Lcynte.spriteBatch.end();

		Lcynte.spriteBatch2.begin();

		buttonClose.dameute();
		buttonNewGame.dameute();
		buttonOptions.dameute();

		Lcynte.spriteBatch2.end();
	}

	public void dispose()
	{
		isMenu = false;
		imageBackground = null;

		buttonOptions = null;
		buttonNewGame = null;
		buttonClose = null;

		music.stop();
		music.dispose();
	}
}
