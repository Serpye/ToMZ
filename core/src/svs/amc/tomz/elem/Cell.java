package svs.amc.tomz.elem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Enterante;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.gp.GameProcess;

/**
 * Created by serpye on 6/3/17.
 */
public class Cell
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Cell sqcCell[][];

	public short x;
	public short y;

	public byte xsqc;
	public byte ysqc;

	public Element elem;

	public static short size;

	public static Texture sqcImage[] =
	{
		new Texture(Gdx.files.internal("cellF1.png")),
		new Texture(Gdx.files.internal("cellF2.png")),
		new Texture(Gdx.files.internal("cellF3.png")),
		new Texture(Gdx.files.internal("cellF4.png")),
		new Texture(Gdx.files.internal("cellF5.png")),
		new Texture(Gdx.files.internal("cellF6.png")),
		new Texture(Gdx.files.internal("cellF7.png")),
		new Texture(Gdx.files.internal("cellF8.png")),
		new Texture(Gdx.files.internal("cellF9.png")),
		new Texture(Gdx.files.internal("cellF10.png"))
	};
	public byte imageIndex = 0;

	public static Object cellMutex = new Object();

	public byte animation = 0;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Cell(int x, int y, short xsqc, short ysqc)
	{
		this.x = (short)x;
		this.y = (short)y;
		this.xsqc = (byte)xsqc;
		this.ysqc = (byte)ysqc;
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static void setCellImage()
	{
		if (Enterante.indexImage == 1 || Enterante.indexImage == 5)
		{
			sqcImage[0] = new Texture(Gdx.files.internal("cell1F1.png"));
			sqcImage[1] = new Texture(Gdx.files.internal("cell1F2.png"));
			sqcImage[2] = new Texture(Gdx.files.internal("cell1F3.png"));
			sqcImage[3] = new Texture(Gdx.files.internal("cell1F4.png"));
			sqcImage[4] = new Texture(Gdx.files.internal("cell1F5.png"));
			sqcImage[5] = new Texture(Gdx.files.internal("cell1F6.png"));
			sqcImage[6] = new Texture(Gdx.files.internal("cell1F7.png"));
			sqcImage[7] = new Texture(Gdx.files.internal("cell1F8.png"));
			sqcImage[8] = new Texture(Gdx.files.internal("cell1F9.png"));
			sqcImage[9] = new Texture(Gdx.files.internal("cell1F10.png"));
		}

		else
		{
			sqcImage[0] = new Texture(Gdx.files.internal("cellF1.png"));
			sqcImage[1] = new Texture(Gdx.files.internal("cellF2.png"));
			sqcImage[2] = new Texture(Gdx.files.internal("cellF3.png"));
			sqcImage[3] = new Texture(Gdx.files.internal("cellF4.png"));
			sqcImage[4] = new Texture(Gdx.files.internal("cellF5.png"));
			sqcImage[5] = new Texture(Gdx.files.internal("cellF6.png"));
			sqcImage[6] = new Texture(Gdx.files.internal("cellF7.png"));
			sqcImage[7] = new Texture(Gdx.files.internal("cellF8.png"));
			sqcImage[8] = new Texture(Gdx.files.internal("cellF9.png"));
			sqcImage[9] = new Texture(Gdx.files.internal("cellF10.png"));
		}
	}

	public static void animations()
	{
		Thread animation = new Thread()
		{
			public void run()
			{
				short hor, ver;
				do
				{
					synchronized (cellMutex)
					{
						hor = 0;
						do
						{
							ver = 0;
							do
							{
								if (sqcCell[hor][ver].animation == 1)
								{
									if (sqcCell[hor][ver].imageIndex < 9)
										sqcCell[hor][ver].imageIndex += 2;

									if (sqcCell[hor][ver].imageIndex >= 9)
									{
										sqcCell[hor][ver].animation = 0;
										sqcCell[hor][ver].imageIndex = 9;
									}
								}

								else if (sqcCell[hor][ver].animation == 2)
								{
									if (sqcCell[hor][ver].imageIndex == 0)
										sqcCell[hor][ver].animation = 0;

									else
										sqcCell[hor][ver].imageIndex--;
								}
							}
							while (++ver < Enterante.vertis);
						}
						while (++hor < Enterante.horis);
					}

					if (GameProcess.buttonMenu.animation == 1)
					{
						if (GameProcess.buttonMenu.textureFrame < 9)
							GameProcess.buttonMenu.textureFrame += 2;

						if (GameProcess.buttonMenu.textureFrame >= 9)
						{
							GameProcess.buttonMenu.animation = 0;
							GameProcess.buttonMenu.textureFrame = 9;
						}
					}

					else if (GameProcess.buttonMenu.animation == 2)
					{
						if (GameProcess.buttonMenu.textureFrame == 0)
							GameProcess.buttonMenu.animation = 0;

						else
							GameProcess.buttonMenu.textureFrame--;
					}

					if (GameProcess.buttonRemix.animation == 1)
					{
						if (GameProcess.buttonRemix.textureFrame < 9)
							GameProcess.buttonRemix.textureFrame += 2;

						if (GameProcess.buttonRemix.textureFrame >= 9)
						{
							GameProcess.buttonRemix.animation = 0;
							GameProcess.buttonRemix.textureFrame = 9;
						}
					}

					else if (GameProcess.buttonRemix.animation == 2)
					{
						if (GameProcess.buttonRemix.textureFrame == 0)
							GameProcess.buttonRemix.animation = 0;

						else
							GameProcess.buttonRemix.textureFrame--;
					}

					if (ProgressBar.arideStep > 0)
					{
						if (ProgressBar.arideStep > 30)
						{
							ProgressBar.arideStep -= 30;

							SparkList.add(new SparkClearCount());
						}

						ProgressBar.arideStep -= 0.04f;

						if (ProgressBar.arideStep < 0)
							ProgressBar.arideStep = 0;
					}

					try
					{
						Thread.sleep(50);
					}

					catch (InterruptedException exc)
					{				}
				}
				while (true);
			}
		};
		animation.start();
	}

	public static void dameute()
	{
		if (sqcCell == null)
			return;

		short x = 0;
		short y;
		short ofs;
		synchronized (cellMutex)
		{
			do
			{
				y = 0;
				do
				{
					Lcynte.spriteBatch.draw
							(
									sqcImage[sqcCell[x][y].imageIndex],
									sqcCell[x][y].x,
									sqcCell[x][y].y,
									size,
									size
							);

					if (sqcCell[x][y].elem != null)
					{
						ofs = (short)((Element.size - sqcCell[x][y].elem.elemSize) >> 1);
/*Fly away*/			Lcynte.spriteBatch.draw
								(
										Element.sqcImage[sqcCell[x][y].elem.imageIndex + Enterante.ofsRanQ],
										sqcCell[x][y].elem.x + ofs,
										sqcCell[x][y].elem.y + ofs,
										sqcCell[x][y].elem.elemSize,
										sqcCell[x][y].elem.elemSize
								);
					}
				}
				while (++y < Enterante.vertis);
			}
			while (++x < Enterante.horis);
		}
	}
}
