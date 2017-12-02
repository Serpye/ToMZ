package svs.amc.tomz;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.gp.GameProcess;

/**
 * Created by serpye on 7/2/17.
 */
public class Cursor
{
	public static Texture texture;

	public static float size;
	public static float vel = Lcynte.screenWidth / 10000.0f;
	public static float sparkSizeStep;
	public static short maxLength = 150;

	public static final byte ofs = 5;//50

	public static float desrParticalSize;
	//-------
	public static float sqcSparkXY[][];

	public static Object cursorMutex = new Object();

	public static void init()
	{
		desrParticalSize = 0.05f;
		texture = new Texture(Gdx.files.internal("spark/sparkCursor.png"));

		Pixmap pixmap = new Pixmap(Gdx.files.internal("arrow.png"));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, 0, 0));

		pixmap.dispose();

		size = 64;//50

		float originalSparkSize = 0.3f*size;
		sparkSizeStep = vel*originalSparkSize / maxLength;

		sqcSparkXY = new float[20][5];
		short itera = 0;
		do
		{
			sqcSparkXY[itera][0] = ofs;
			sqcSparkXY[itera][1] = ofs;

			sqcSparkXY[itera][2] = itera*originalSparkSize / sqcSparkXY.length;

			sqcSparkXY[itera][3] = (float)(GameProcess.ran.nextFloat()*Math.PI*2);

			sqcSparkXY[itera][4] = (GameProcess.ran.nextFloat()*2 - 1) / 300.0f;
		}
		while (++itera < sqcSparkXY.length);
	}

	public static void dameute()
	{
		short itera = 0;
		do
		{
			Lcynte.spriteBatch.draw
					(
						texture,
						sqcSparkXY[itera][0]
								-
								sqcSparkXY[itera][2] / 2,
						sqcSparkXY[itera][1]
								-
								sqcSparkXY[itera][2] / 2,
						sqcSparkXY[itera][2],
						sqcSparkXY[itera][2]
					);
		}
		while (++itera < sqcSparkXY.length);
	}

	public static void move()
	{
		short itera = 0;
		do
		{
			sqcSparkXY[itera][0] += Math.cos(sqcSparkXY[itera][3])*vel;
			sqcSparkXY[itera][1] += Math.sin(sqcSparkXY[itera][3])*vel;

			sqcSparkXY[itera][3] += sqcSparkXY[itera][4];

			sqcSparkXY[itera][2] -= desrParticalSize;

			if (sqcSparkXY[itera][2] < 1)
			{
				sqcSparkXY[itera][0] = Gdx.input.getX() + ofs;
				sqcSparkXY[itera][1] = Lcynte.screenHeight - Gdx.input.getY() - ofs;

				sqcSparkXY[itera][2] = 0.3f*size;

				sqcSparkXY[itera][3] = (float)(GameProcess.ran.nextFloat()*Math.PI*2);

				sqcSparkXY[itera][4] = (GameProcess.ran.nextFloat()*2 - 1) / 300.0f;
			}
		}
		while (++itera < sqcSparkXY.length);
	}
}
