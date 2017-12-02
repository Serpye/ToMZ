package svs.amc.tomz.gp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import svs.amc.tomz.Enterante;
import svs.amc.tomz.InputPrcs;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.elem.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by serpye on 6/11/17.
 */
public class GameProcess extends Condition
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Random ran = new Random();

	public static ShapeRenderer shapeRenderer;

	public static volatile byte threadID = 0;

	public static TCEx componentScore;
	public static TCEx componentLevel;
	public static TCEx componentClearQuantity;
	public static Button_M buttonMenu;
	public static Button_M buttonRemix;
	/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public GameProcess()
	{
		isMenu = false;
		load();
		InputPrcs.sound = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-toggle.wav"));

		SparkProgressBar.sound = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-fruit-collected.wav"));

		SparkClearCount.texture = new Texture(Gdx.files.internal("spark/sparkCC.png"));
		SparkClearCount.sound = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-good-tip-high.wav"));

		SparkBomb.texture = new Texture(Gdx.files.internal("spark/sparkB.png"));
		SparkBomb.texture2 = new Texture(Gdx.files.internal("spark/sparkBB.png"));
		SparkBomb.sound = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-flame-02.wav"));
		SparkBomb.sound2 = Gdx.audio.newSound(Gdx.files.internal("sound/NFF-explode.wav"));

		Sparky.sqcArideStep = new float[10];
		byte itera = 0;
		do
		{
			Sparky.sqcArideStep[itera] = ran.nextFloat() / 10.0f;
		}
		while (++itera < Sparky.sqcArideStep.length);

		SparkList.add(new SparkProgressBar(new Cell(0,0,(short)0,(short)0)));
		SparkList.add(new SparkProgressBar(new Cell(0,0,(short)0,(short)0)));
		SparkList.add(new SparkProgressBar(new Cell(0,0,(short)0,(short)0)));
		SparkList.add(new SparkProgressBar(new Cell(0,0,(short)0,(short)0)));
		SparkList.clear();

		Enterante.sqcBackgroundImageRandom = new byte[6];
		LinkedList<Byte> list = new LinkedList<Byte>();
		list.add(new Byte((byte)0));
		list.add(new Byte((byte)1));
		list.add(new Byte((byte)2));
		list.add(new Byte((byte)3));
		list.add(new Byte((byte)4));
		list.add(new Byte((byte)5));

		itera = 0;
		byte ranQ;
		do
		{
			ranQ = (byte)ran.nextInt(list.size());
			Enterante.sqcBackgroundImageRandom[itera++] = list.get(ranQ);

			list.remove(ranQ);
		}
		while (list.size() > 0);

		Cell.setCellImage();
		Enterante.initGameProccess();

//======|	Buttons & TC
		short ofsTCEx = (short)((Lcynte.screenHeight - 3*TextComponent.size) >> 2);

		componentScore = new TCEx
				(
					Cell.sqcCell.length*Cell.size
							+
						(InputPrcs.ofsX)
							+
						((InputPrcs.ofsX - TextComponent.size) >> 1),//(Lcynte.screenWidth + InputPrcs.ofsX + TextComponent.size) >> 1,
					Lcynte.screenHeight - ofsTCEx - TextComponent.size,
					"SCORES",
					Long.toString(Enterante.scores)
				);

		componentLevel = new TCEx
				(
					componentScore.x,
					componentScore.y - TextComponent.size - ofsTCEx,
					"Level",
					Byte.toString(Enterante.level)
				);

		componentClearQuantity = new TCEx
				(
					componentLevel.x,
					componentLevel.y - TextComponent.size - ofsTCEx,
					"ClearQ",
					Byte.toString(Enterante.clearCount)
				);

		buttonRemix = new Button_M
				(
						(InputPrcs.ofsX - TextComponent.size) >> 1,
						Lcynte.screenHeight - ofsTCEx - Button_M.height,
						"Clear"
				);
		ActionClass actionClass = new ActionClass()
		{
			public void onClick()
			{
				if (Enterante.clearCount > 0)
				{
					Enterante.clearCount--;

					componentClearQuantity.setText(Byte.toString(Enterante.clearCount));

					synchronized (Element.elemDeleteMutex)
					{
						byte imageIndex;
						byte v, h;
						do
						{
							h = (byte)ran.nextInt(Enterante.horis);
							v = (byte)ran.nextInt(Enterante.vertis);

							if (Cell.sqcCell[h][v].elem != null)
							{
								imageIndex = Cell.sqcCell[h][v].elem.imageIndex;
								break;
							}
						}
						while (true);

						short x = 0;
						short y;
						boolean flag = true;
						do
						{
							y = 0;
							do
							{
								if (Cell.sqcCell[x][y].elem != null && Cell.sqcCell[x][y].elem.imageIndex == imageIndex)
									SparkList.add(new SparkBomb(Cell.sqcCell[x][y].elem));
							}
							while(flag && ++y < Cell.sqcCell[0].length);
						}
						while (flag && ++x < Cell.sqcCell.length);

						SparkList.add(new SparkBomb(null));
					}
				}

				else
					InputPrcs.sound.play(0.05f);
			}
		};
		buttonRemix.actionClass = actionClass;

		buttonMenu = new Button_M
				(
						buttonRemix.x,
						buttonRemix.y - Button_M.height - ofsTCEx,
						"Menu"
				);
		actionClass = new ActionClass()
		{
			public void onClick()
			{
				dispose();
			}
		};
		buttonMenu.actionClass = actionClass;
//======|	Animation
		Cell.animations();

		Thread action = new Thread()
		{
			public void run()
			{
				byte localThreadID = threadID;
				short x;
				short y;
				do
				{
					synchronized (Cell.cellMutex)
					{
						x = 0;
						do
						{
							y = 0;
							do
							{
								if (Cell.sqcCell[x][y].elem != null)
								{
/*Fly away*/						if (Cell.sqcCell[x][y].elem.isDying == 1)
									{
										Cell.sqcCell[x][y].elem.elemSize -= 3;

										if (Cell.sqcCell[x][y].elem.elemSize < 1)
										{
											/*try
											{*/
												byte itera = (byte)((Enterante.level - 1) / 99 + 1);
												do
												{
													SparkList.add
														(
															new SparkProgressBar(Cell.sqcCell[x][y])
														);
												}
												while (--itera > 0);

												synchronized (Element.elemDeleteMutex)
												{
													Cell.sqcCell[x][y].elem.cell = null;
													Cell.sqcCell[x][y].elem = null;
												}
											//}

											/*catch (Exception exc)
											{
												System.err.println(exc);
											}*/

											Enterante.spawn();
										}
									}

/*Fly away*/						/*else if (Cell.sqcCell[x][y].elem.isDying == 2)
									{
										Cell.sqcCell[x][y].elem.elemSize++;

										if (Cell.sqcCell[x][y].elem.elemSize > Element.size)
										{
											Cell.sqcCell[x][y].elem.elemSize = Element.size;
											Cell.sqcCell[x][y].elem.isDying = 0;
										}
									}*/

/*Fly Away*/						else if (Cell.sqcCell[x][y].elem.isDying != 3)
										Cell.sqcCell[x][y].elem.checkForMoving();
								}
							}
							while (++y < Enterante.vertis);
						}
						while (++x < Enterante.horis);

						Enterante.spawn();
					}

					synchronized (SparkProgressBar.mutex)
					{
						SparkProgressBar.move();
					}


					try
					{
						Thread.sleep(10);
					}

					catch (InterruptedException exc)
					{					}
				}
				while (threadID == localThreadID);
			}
		};
		action.start();

		Thread elemMoving = new Thread()
		{
			public void run()
			{
				//while (Cell.sqcCell == null);

				byte localThreadID = threadID;
				short x;
				short y;
				do
				{
					synchronized (Cell.cellMutex)
					{
						x = 0;
						do{
							y = 0;
							do
							{
								if (Cell.sqcCell[x][y].elem != null && Cell.sqcCell[x][y].elem.isDying == 3)
									Cell.sqcCell[x][y].elem.moving();
							}
							while (++ y < Enterante.vertis);
						}
						while (++ x < Enterante.horis);

						Enterante.checkMatch();
					}

					try
					{
						Thread.sleep(1);
					}

					catch (InterruptedException exc)
					{					}
				}
				while (threadID == localThreadID);
			}
		};
		elemMoving.start();
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void dameute()
	{
		Lcynte.spriteBatch.begin();

		synchronized (Cell.cellMutex)
		{
			Lcynte.spriteBatch.draw(imageBackground, 0, 0, Lcynte.screenWidth, Lcynte.screenHeight);
		}

		Cell.dameute();

		synchronized (Spark.mutex)
		{
			Spark.dameute();
		}

		synchronized (TextComponent.mutexTC)
		{
			componentLevel.dameute();
			componentScore.dameute();
			componentClearQuantity.dameute();
		}

		Lcynte.spriteBatch.end();


		ProgressBar.dameute();
//======|	TEXT COMPONENT
		synchronized (TextComponent.mutexTC)
		{
			Lcynte.spriteBatch2.begin();

			buttonMenu.dameute();
			buttonRemix.dameute();

			ProgressBar.dameutePointer();

			Lcynte.spriteBatch2.end();
		}
	}

	public static void reinit()
	{
		if (Enterante.level < 100)
		{
			Enterante.level++;
			componentLevel.text2 = Byte.toString(Enterante.level);
		}

		else
			componentLevel.text2 = "MAX";

		save();

		SparkList.clear();

		threadID++;

        Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run()
				{
                    synchronized (Cell.cellMutex)
                        {
                            short x = 0;
                            short y;

                            do
                            {
                                y = 0;
                                do
                                {
                                    Cell.sqcCell[x][y].elem = null;
                                }
                                while (++y < Enterante.vertis);
                            }
                            while (++x < Enterante.horis);

							x = 0;
							do
							{
								y = 0;
								do
								{
									Cell.sqcCell[x][y] = null;
								}
								while (++y < Enterante.vertis);
							}
							while (++x < Enterante.horis);

							Cell.sqcCell = null;

							Cell.setCellImage();

							Enterante.initGameProccess();

							final byte id = threadID;

							Thread action = new Thread()
							{
								public void run()
								{
									short x;
									short y;
									do
									{
										synchronized (Cell.cellMutex)
										{
											x = 0;
											do
											{
												y = 0;
												do
												{
													if (Cell.sqcCell[x][y].elem != null)
													{
/*Fly away*/						if (Cell.sqcCell[x][y].elem.isDying == 1)
													{
														Cell.sqcCell[x][y].elem.elemSize -= 3;

														if (Cell.sqcCell[x][y].elem.elemSize < 1)
														{
											/*try
											{*/
															byte itera = (byte)((Enterante.level - 1) / 999 + 1);
															do
															{
																SparkList.add
																		(
																				new SparkProgressBar(Cell.sqcCell[x][y])
																		);
															}
															while (--itera > 0);

															synchronized (Element.elemDeleteMutex)
															{
																Cell.sqcCell[x][y].elem.cell = null;
																Cell.sqcCell[x][y].elem = null;
															}
															//}

											/*catch (Exception exc)
											{
												System.err.println(exc);
											}*/

															Enterante.spawn();
														}
													}

/*Fly away*/						else if (Cell.sqcCell[x][y].elem.isDying == 2)
													{
														Cell.sqcCell[x][y].elem.elemSize++;

														if (Cell.sqcCell[x][y].elem.elemSize > Element.size)
														{
															Cell.sqcCell[x][y].elem.elemSize = Element.size;
															Cell.sqcCell[x][y].elem.isDying = 0;
														}
													}

/*Fly Away*/						else if (Cell.sqcCell[x][y].elem.isDying != 3)
														Cell.sqcCell[x][y].elem.checkForMoving();
													}
												}
												while (++y < Enterante.vertis);
											}
											while (++x < Enterante.horis);

											Enterante.spawn();
										}

										synchronized (SparkProgressBar.mutex)
										{
											SparkProgressBar.move();
										}

										try
										{
											Thread.sleep(10);
										}

										catch (InterruptedException exc)
										{					}
									}
									while (threadID == id);
								}
							};
							action.start();

							Thread elemMoving = new Thread()
							{
								public void run()
								{
									short x;
									short y;
									do
									{
										synchronized (Cell.cellMutex)
										{
											x = 0;
											do
											{
												y = 0;
												do
												{
													if (Cell.sqcCell[x][y].elem != null && Cell.sqcCell[x][y].elem.isDying == 3)
														Cell.sqcCell[x][y].elem.moving();
												}
												while (++y < Enterante.vertis);
											}
											while (++x < Enterante.horis);

											Enterante.checkMatch();
										}

										try
										{
											Thread.sleep(1);
										}

										catch (InterruptedException exc)
										{					}
									}
									while (threadID == id);
								}
							};
							elemMoving.start();

							short ofsTCEx = (short)((Lcynte.screenHeight - 3*TextComponent.size) >> 2);

							componentScore.x = (short)
									(
											Cell.sqcCell.length*Cell.size
													+
													(InputPrcs.ofsX)
													+
													((InputPrcs.ofsX - TextComponent.size) >> 1)
									);
							componentScore.y = 	(short)(Lcynte.screenHeight - ofsTCEx - TextComponent.size);

							componentLevel.x = componentScore.x;
							componentLevel.y = (short)(componentScore.y - TextComponent.size - ofsTCEx);

							buttonRemix.x = (short)((InputPrcs.ofsX - TextComponent.size) >> 1);
							buttonRemix.y = (short)(Lcynte.screenHeight - ofsTCEx - Button_M.height);

							buttonMenu.x = 	buttonRemix.x;
							buttonMenu.y = (short)(buttonRemix.y - Button_M.height - ofsTCEx);
                    }
                }
            }
            );
	}

	public static void load()
	{
		FileHandle file = Gdx.files.local("file");

		if (!file.exists())
			file = Gdx.files.internal("file");

		String info = file.readString();
		StringTokenizer stringTokenizer = new StringTokenizer(info);
		Enterante.level = Byte.parseByte(stringTokenizer.nextToken());
		Enterante.scores = Long.parseLong(stringTokenizer.nextToken());
		Enterante.clearCount = Byte.parseByte(stringTokenizer.nextToken());
	}

	public static void save()
	{
		FileHandle file = Gdx.files.local("file");
		String info = Short.toString(Enterante.level)
							+
						" "
							+
						Long.toString(Enterante.scores)
							+
						" "
							+
						Byte.toString(Enterante.clearCount);
		file.writeString(info, false);
	}

	public void dispose()
	{
		threadID = (byte)(Byte.MAX_VALUE + threadID);

		music.stop();
		music.dispose();

		save();

		Lcynte.condition = new Menu();

		/*synchronized (Cell.cellMutex)
		{
			Enterante.sqcBackgroundImageRandom = null;
			Cell.sqcCell = null;
		}

		ProgressBar.sqcInc = null;
		ProgressBar.sqcAccum = null;
		ProgressBar.sqcColor = null;
		ProgressBar.sqcIsGrow = null;
*/
		synchronized (SparkProgressBar.mutex)
		{
			SparkList.clear();
		}

		/*componentScore = null;
		componentLevel = null;
		buttonRemix = null;
		buttonMenu = null;*/
	}
}
