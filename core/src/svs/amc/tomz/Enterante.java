package svs.amc.tomz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.elem.Cell;
import svs.amc.tomz.elem.Element;
import svs.amc.tomz.elem.ProgressBar;
import svs.amc.tomz.elem.SparkProgressBar;
import svs.amc.tomz.gp.GameProcess;
import svs.amc.tomz.gp.TCEx;
import svs.amc.tomz.gp.TextComponent;

/**
 * Created by serpye on 6/3/17.
 */
public class Enterante
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static byte level;
	public static long scores;
	public static byte ranQ;
	public static byte ofsRanQ;

	public static byte horis;
	public static byte vertis;

	public static byte indexImage;
	public static byte backgroundImageIndex = 0;
	public static byte sqcBackgroundImageRandom[];
	public static boolean musicFlag;

	public static byte clearCount = 0;

	public static Sound soundDestroy;
	public static Sound soundChange;
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static void initGameProccess()
	{
		soundDestroy = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-coin-04.wav"));
		soundChange = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-wavy-whistle.wav"));

		horis = (byte)(11*(level - 1) / 99 + 4);//((6*level - 600) / 99 + 10);
		vertis = (byte)(4*(level - 1) / 99 + 4);//((3*level - 3) / 99 + 4);
		ranQ = (byte)((8*(level - 1)) / 99 + 3);

		if (ofsRanQ == 11)
			ofsRanQ = 0;

		else
			ofsRanQ = 11;

//======|		Background
		musicFlag = !musicFlag;

		if (GameProcess.music != null)
		{
			GameProcess.music.stop();
			GameProcess.music.dispose();
		}

		if (++backgroundImageIndex > 5)
			backgroundImageIndex = 0;

		TCEx.texture = new Texture(Gdx.files.internal
							(
								"button/MN_ButtBack"
									+
								(sqcBackgroundImageRandom[backgroundImageIndex] + 1)
									+
								".png"
							));
		TCEx.backTexture = new Texture(Gdx.files.internal
							(
								"button/MN_ButtBack"
									+
								(sqcBackgroundImageRandom[backgroundImageIndex] + 1)
									+
								"F.png"
							));

		switch (sqcBackgroundImageRandom[backgroundImageIndex])
		{
			case 0 :
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background1.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/Peter_Gundry_-_The_Forest_Queen_(mp3.pm).mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/39_event__16.mp3"));

				TextComponent.remise = 1f;
				TextComponent.neurelle = 0.9f;
				TextComponent.aluese = 0.25f;
			break;

			case 1 :
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background2.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/13_serpent_clan_~_swans_pool.mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/12_serpent_clan_~_serpent_attack.mp3"));

				TextComponent.remise = 0.2f;
				TextComponent.neurelle = 0.6f;
				TextComponent.aluese = 1f;
			break;

			case 2 :
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background3.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/lena-chamamyan-love-in-damascus_(mp3.cc).mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/05_dragon_clan_~_better_days.mp3"));

				TextComponent.remise = 0.8f;
				TextComponent.neurelle = 0.5f;
				TextComponent.aluese = 0.15f;
			break;

			case 3 :
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background4.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/Beautiful_Chinese_Music_-_Imperial_Dynasty_(mp3.pm).mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/myzuka.ru_05_becoming_one_of_the_people_becoming_one_with_neytiri.mp3"));

				TextComponent.remise = 0.1f;
				TextComponent.neurelle = 1f;
				TextComponent.aluese = 0.5f;
			break;

			case 4 :
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background5.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/Peter_Gundry_-_Autumn_s_Child_(mp3.pm).mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/07_dragon_clan_~_dragon_cave.mp3"));

				TextComponent.remise = 0.7f;
				TextComponent.neurelle = 0.1f;
				TextComponent.aluese = 1f;
			break;

			default:
				GameProcess.imageBackground = new Texture(Gdx.files.internal("background/background6.jpg"));

				if (musicFlag)
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/Antti_Martikainen_-_The_Forbidden_City_(mp3.pm).mp3"));

				else
					GameProcess.music = Gdx.audio.newMusic(Gdx.files.internal("music/33_event__10.mp3"));

				TextComponent.remise = 1f;
				TextComponent.neurelle = 1f;
				TextComponent.aluese = 0.1f;
		}

		GameProcess.music.setLooping(true);
		GameProcess.music.play();
//_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-

		InputPrcs.ofsY = (short)((Lcynte.screenHeight << 1) / 10);
		Cell.size = (short)((Lcynte.screenHeight - (InputPrcs.ofsY << 1)) / vertis);
		Element.size = (short)(0.9f*Cell.size);
		InputPrcs.ofsX = (short)((Lcynte.screenWidth - Cell.size*horis) >> 1);

		Cell.sqcCell = new Cell[horis][vertis];
		short hor = 0;
		short ver;
		while (hor < Cell.sqcCell.length)
		{
			ver = 0;
			while (ver < Cell.sqcCell[0].length)
			{
				Cell.sqcCell[hor][ver] = new Cell
						(
							hor*Cell.size + InputPrcs.ofsX,
							ver*Cell.size + InputPrcs.ofsY,
							hor,
							ver
						);

				ver++;
			}

			hor++;
		}

		/*hor = 0;
		do
		{
			ver = 0;
			do
			{
				Cell.sqcCell[hor][ver].elem =(new Element
						(
							Cell.sqcCell[hor][ver],
							GameProcess.ran.nextInt(5)
						));
			}
			while (++ver < vertis);
		}
		while(++hor < horis);*/

		hor = 0;
		ver = (short)(vertis - 1);
		do
		{
			Cell.sqcCell[hor][ver].elem = (new Element
					(
							Cell.sqcCell[hor][ver],
							GameProcess.ran.nextInt(ranQ)
					));
		}
		while(++hor < horis);

		//_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-

		ProgressBar.init();
		int quantity = (ProgressBar.width - 4)*(level - 1) / 99 + 4;

		ProgressBar.sqcColor = new float[quantity][3];
		ProgressBar.sqcInc = new short[quantity];
		ProgressBar.sqcIsGrow = new boolean[quantity];

		ProgressBar.maxAccum = 9;//ProgressBar.maxAccum = (short)(90*(level - 1) / 99 + 10);
		ProgressBar.sqcAccum = new short[quantity];

		short itera = 0;
		do
		{
			ProgressBar.sqcIsGrow[itera] = GameProcess.ran.nextBoolean();
			ProgressBar.sqcInc[itera] = (short)GameProcess.ran.nextInt(ProgressBar.maxBright);
		}
		while (++itera < ProgressBar.sqcInc.length);

		ProgressBar.size = (short)(ProgressBar.width / ProgressBar.sqcColor.length);

		SparkProgressBar.desrParticalSize = -0.5f*(level - 1) / 99 + 1;
		//-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
	}

	public static void checkMatch()
	{
		short x = 0;
		short y;

		do
		{
			y = 0;
			do
			{
				//hor
				if
				(
					x + 2 < horis
						&&
					Cell.sqcCell[x][y].elem != null
						&&
					Cell.sqcCell[x + 1][y].elem != null
						&&
					Cell.sqcCell[x + 2][y].elem != null
						&&
					Cell.sqcCell[x][y].elem.isDying < 3
						&&
					Cell.sqcCell[x + 1][y].elem.isDying < 3
						&&
					Cell.sqcCell[x + 2][y].elem.isDying < 3
						&&
					Cell.sqcCell[x][y].elem.imageIndex
						==
					Cell.sqcCell[x + 1][y].elem.imageIndex
						&&
					Cell.sqcCell[x][y].elem.imageIndex
						==
					Cell.sqcCell[x + 2][y].elem.imageIndex
				)
				{
					Cell.sqcCell[x + 1][y].elem.isDying =
					Cell.sqcCell[x][y].elem.isDying =
					Cell.sqcCell[x + 2][y].elem.isDying = 1;
				}

				//ver
				if
				(
					y + 2 < vertis
						&&
					Cell.sqcCell[x][y].elem != null
						&&
					Cell.sqcCell[x][y + 1].elem != null
						&&
					Cell.sqcCell[x][y + 2].elem != null
						&&
					Cell.sqcCell[x][y].elem.isDying < 3
						&&
					Cell.sqcCell[x][y + 1].elem.isDying < 3
						&&
					Cell.sqcCell[x][y + 2].elem.isDying < 3
						&&
					Cell.sqcCell[x][y].elem.imageIndex
						==
					Cell.sqcCell[x][y + 1].elem.imageIndex
						&&
					Cell.sqcCell[x][y].elem.imageIndex
						==
					Cell.sqcCell[x][y + 2].elem.imageIndex
				)
				{
					Cell.sqcCell[x][y].elem.isDying =
					Cell.sqcCell[x][y + 1].elem.isDying =
					Cell.sqcCell[x][y + 2].elem.isDying = 1;
				}
			}
			while (++y < vertis);
		}
		while (++x < horis);
	}

	public static boolean localCheckMatch(Element elem1, Element elem2)
	{
		synchronized (Cell.cellMutex)
		{
			if (elem1 == null || elem2 == null)
				return false;

			Cell cell1 = elem1.cell;

			if (cell1 == null)
				return false;

			// firts hor
			if (cell1.xsqc + 2 < horis)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc + 1][cell1.ysqc].elem != null
								&&
								Cell.sqcCell[cell1.xsqc + 2][cell1.ysqc].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc + 1][cell1.ysqc].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc + 2][cell1.ysqc].elem.imageIndex
						)
					return true;
			}

			//second hor
			if (cell1.xsqc + 1 < horis && cell1.xsqc - 1 > -1)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc + 1][cell1.ysqc].elem != null
								&&
								Cell.sqcCell[cell1.xsqc - 1][cell1.ysqc].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc + 1][cell1.ysqc].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc - 1][cell1.ysqc].elem.imageIndex
						)
					return true;
			}

			//last third hor
			if (cell1.xsqc - 2 > -1)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc - 1][cell1.ysqc].elem != null
								&&
								Cell.sqcCell[cell1.xsqc - 2][cell1.ysqc].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc - 1][cell1.ysqc].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc - 2][cell1.ysqc].elem.imageIndex
						)
					return true;
			}

			// firts ver
			if (cell1.ysqc + 2 < vertis)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc][cell1.ysqc + 1].elem != null
								&&
								Cell.sqcCell[cell1.xsqc][cell1.ysqc + 2].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc + 1].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc + 2].elem.imageIndex
						)
					return true;
			}

			//second ver
			if (cell1.ysqc + 1 < vertis && cell1.ysqc - 1 > -1)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc][cell1.ysqc + 1].elem != null
								&&
								Cell.sqcCell[cell1.xsqc][cell1.ysqc - 1].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc + 1].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc - 1].elem.imageIndex
						)
					return true;
			}

			//last third ver
			if (cell1.ysqc - 2 > -1)
			{
				if
						(
						Cell.sqcCell[cell1.xsqc][cell1.ysqc - 1].elem != null
								&&
								Cell.sqcCell[cell1.xsqc][cell1.ysqc - 2].elem != null
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc - 1].elem.imageIndex
								&&
								elem1.imageIndex
										==
										Cell.sqcCell[cell1.xsqc][cell1.ysqc - 2].elem.imageIndex
						)
					return true;
			}

			Cell cell2 = elem2.cell;

			// firts hor
/*fly away*/if (cell2.xsqc + 2 < horis)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc + 1][cell2.ysqc].elem != null
								&&
								Cell.sqcCell[cell2.xsqc + 2][cell2.ysqc].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc + 1][cell2.ysqc].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc + 2][cell2.ysqc].elem.imageIndex
						)
					return true;
			}

			//second hor
			if (cell2.xsqc + 1 < horis && cell2.xsqc - 1 > -1)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc + 1][cell2.ysqc].elem != null
								&&
								Cell.sqcCell[cell2.xsqc - 1][cell2.ysqc].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc + 1][cell2.ysqc].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc - 1][cell2.ysqc].elem.imageIndex
						)
					return true;
			}

			//last third hor
			if (cell2.xsqc - 2 > -1)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc - 1][cell2.ysqc].elem != null
								&&
								Cell.sqcCell[cell2.xsqc - 2][cell2.ysqc].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc - 1][cell2.ysqc].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc - 2][cell2.ysqc].elem.imageIndex
						)
					return true;
			}

			// firts ver
			if (cell2.ysqc + 2 < vertis)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc][cell2.ysqc + 1].elem != null
								&&
								Cell.sqcCell[cell2.xsqc][cell2.ysqc + 2].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc + 1].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc + 2].elem.imageIndex
						)
					return true;
			}

			//second ver
			if (cell2.ysqc + 1 < vertis && cell2.ysqc - 1 > -1)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc][cell2.ysqc + 1].elem != null
								&&
								Cell.sqcCell[cell2.xsqc][cell2.ysqc - 1].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc + 1].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc - 1].elem.imageIndex
						)
					return true;
			}

			//last third ver
			if (cell2.ysqc - 2 > -1)
			{
				if
						(
						Cell.sqcCell[cell2.xsqc][cell2.ysqc - 1].elem != null
								&&
								Cell.sqcCell[cell2.xsqc][cell2.ysqc - 2].elem != null
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc - 1].elem.imageIndex
								&&
								elem2.imageIndex
										==
										Cell.sqcCell[cell2.xsqc][cell2.ysqc - 2].elem.imageIndex
						)
					return true;
			}

			return false;
		}
	}

	public static void spawn()
	{
		short x = 0;
		short y = (short)(vertis - 1);
		do
		{
			if (Cell.sqcCell[x][y].elem == null)
				Cell.sqcCell[x][y].elem = new Element
						(
							Cell.sqcCell[x][y],
							GameProcess.ran.nextInt(ranQ)
						);
		}
		while (++x < horis);
	}

	public static void change(final Element elem1, final Element elem2)
	{
		final Cell cell1 = elem1.cell;
		final Cell cell2 = elem2.cell;

		final float vel = Cell.size / 200.0f;

		Thread thread = new Thread()
		{
			public void run()
			{
				float moveXE1;
				float moveYE1;
				float moveXE2;
				float moveYE2;

				if (elem1.x > elem2.x)
				{
					moveXE1 = -vel;
					moveXE2 = -moveXE1;
					moveYE1 = moveYE2 = 0;
				}

				else if (elem1.x < elem2.x)
				{
					moveXE1 = vel;
					moveXE2 = -moveXE1;
					moveYE1 = moveYE2 = 0;
				}

				else
				{
					if (elem1.y > elem2.y)
					{
						moveYE1 = -vel;
						moveYE2 = -moveYE1;
						moveXE1 = moveXE2 = 0;
					}

					else
					{
						moveYE1 = vel;
						moveYE2 = -moveYE1;
						moveXE1 = moveXE2 = 0;
					}
				}

				short itera = (short)(Cell.size / vel);

				do
				{
					elem1.x += moveXE1;
					elem1.y += moveYE1;
					elem2.x += moveXE2;
					elem2.y += moveYE2;

					try
					{
						Thread.sleep(1);
					}

					catch (InterruptedException exc)
					{					}
				}
				while (--itera > 0);

				short ofs = (short)((Cell.size - Element.size) >> 1);
				elem1.x = ofs + cell2.x;
				elem1.y = ofs + cell2.y;
				elem2.x = ofs + cell1.x;
				elem2.y = ofs + cell1.y;

				elem1.cell = cell2;
				cell2.elem = elem1;
				elem2.cell = cell1;
				cell1.elem = elem2;

				if (!localCheckMatch(elem1, elem2))
				{
					moveXE1 = -moveXE1;
					moveXE2 = -moveXE2;
					moveYE1 = -moveYE1;
					moveYE2 = -moveYE2;

					itera = (short)(Cell.size / vel);
					do
					{
						elem1.x += moveXE1;
						elem1.y += moveYE1;
						elem2.x += moveXE2;
						elem2.y += moveYE2;

						try
						{
							Thread.sleep(1);
						}

						catch (InterruptedException exc)
						{					}
					}
					while (--itera > 0);

					elem1.x = ofs + cell1.x;
					elem1.y = ofs + cell1.y;
					elem2.x = ofs + cell2.x;
					elem2.y = ofs + cell2.y;

					elem1.cell = cell1;
					cell1.elem = elem1;
					elem2.cell = cell2;
					cell2.elem = elem2;

					soundChange.play(0.09f);
				}

				else
					soundDestroy.play(0.07f);

				if (InputPrcs.activeCell != null)
					InputPrcs.activeCell.animation = 2;

				InputPrcs.isMoving = false;
			}
		};
		thread.start();
	}
}
