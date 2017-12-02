package svs.amc.tomz.elem;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.gp.GameProcess;
import svs.amc.tomz.gp.TextComponent;

/**
 * Created by serpye on 7/2/17.
 */
public class SparkBomb extends Spark
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Sound sound;
	public static Sound sound2;

	public  Element elem;

	public static Texture texture;
	public static Texture texture2;

	public static float vel = Lcynte.screenWidth / 400f;
	public static float sparkSizeStep;

	public static short maxLength = 200;

	public float sqcSparkHalo[][];
	public short sqcSparkHaloXY[][];
	public short sqcSparkHaloCounter;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public SparkBomb(Element elem)
	{
		x = GameProcess.componentClearQuantity.x + (TextComponent.size >> 1);
		y = GameProcess.componentClearQuantity.y + (TextComponent.size >> 1);

		this.elem = elem;

		size = ProgressBar.sizePointer;

		float originalSparkSize = 0.8f*size;
		sparkSizeStep = vel*originalSparkSize / maxLength;

		sqcSparkXY = new float[50][3];
		short itera = 0;
		do
		{
			sqcSparkXY[itera][0] = x;
			sqcSparkXY[itera][1] = y;

			sqcSparkXY[itera][2] = itera*originalSparkSize / sqcSparkXY.length;
		}
		while (++itera < sqcSparkXY.length);

		sqcSparkHaloXY = new short[100][2];
		itera = 0;
		float aride;
		do
		{
			aride = (float)(GameProcess.ran.nextFloat()*Math.PI / 2);
			sqcSparkHaloXY[itera][0] = (short)(Math.cos(aride)*size / 3);
			sqcSparkHaloXY[itera][1] = (short)(Math.sin(aride)*size / 3);

			switch (GameProcess.ran.nextInt(4))
			{
				case 0 :
					sqcSparkHaloXY[itera][0] = (short)-sqcSparkHaloXY[itera][0];
				break;

				case 1 :
					sqcSparkHaloXY[itera][1] = (short)-sqcSparkHaloXY[itera][1];
				break;

				case 2 :
					sqcSparkHaloXY[itera][0] = (short)-sqcSparkHaloXY[itera][0];
					sqcSparkHaloXY[itera][1] = (short)-sqcSparkHaloXY[itera][1];
			}
		}
		while (++itera < sqcSparkHaloXY.length);

		originalSparkSize = 0.3f*size;

		sqcSparkHalo = new float[20][3];
		itera = 0;
		do
		{
			sqcSparkHalo[itera][0] = x + sqcSparkHaloXY[sqcSparkHaloCounter][0];
			sqcSparkHalo[itera][1] = y + sqcSparkHaloXY[sqcSparkHaloCounter][1];

			sqcSparkHalo[itera][2] = itera*originalSparkSize / sqcSparkHalo.length;

			sqcSparkHaloCounter++;
			if (sqcSparkHaloCounter == sqcSparkHaloXY.length)
				sqcSparkHaloCounter = 0;
		}
		while (++itera < sqcSparkHalo.length);

		sound.play(0.01f);
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void dameuteDirectly()
	{
		short itera = 0;
		do
		{
			Lcynte.spriteBatch.draw
					(
							texture2,
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

		itera = 0;
		do
		{
			Lcynte.spriteBatch.draw
					(
							texture,
							sqcSparkHalo[itera][0]
									-
									sqcSparkHalo[itera][2] / 2,
							sqcSparkHalo[itera][1]
									-
									sqcSparkHalo[itera][2] / 2,
							sqcSparkHalo[itera][2],
							sqcSparkHalo[itera][2]
					);
		}
		while (++itera < sqcSparkHalo.length);

		Lcynte.spriteBatch.draw
				(
						texture,
						x - (size / 2),
						y - (size / 2),
						size,
						size
				);
	}

	public boolean moveDirectly()
	{
		if (elem == null || elem.isDying == 1)
			return true;

		float velX, velY;
		float prX = (short)(elem.x + elem.elemSize / 2 - x);
		float prY = (short)(elem.y + elem.elemSize / 2 - y);

		if (prX != 0)
		{
			velX = (float)Math.sqrt(prX*prX / (prX*prX + prY*prY));

			if (prX < 0)
				velX = -velX;

			velY = velX*prY / prX;
		}

		else
		{
			velY = (float)Math.sqrt(prY*prY / (prX*prX + prY*prY));

			if (prY < 0)
				velY = -velY;

			velX = velY*prX / prY;
		}

		x += velX*vel;
		y += velY*vel;

		short itera = 0;
		do
		{
			sqcSparkXY[itera][2] -= sparkSizeStep;

			if (sqcSparkXY[itera][2] < 1)
			{
				sqcSparkXY[itera][0] = x;
				sqcSparkXY[itera][1] = y;

				sqcSparkXY[itera][2] = 0.8f*size;
			}
		}
		while (++itera < sqcSparkXY.length);

		itera = 0;
		do
		{
			sqcSparkHalo[itera][2] -= sparkSizeStep;

			if (sqcSparkHalo[itera][2] < 1)
			{
				sqcSparkHalo[itera][0] = x + sqcSparkHaloXY[sqcSparkHaloCounter][0];
				sqcSparkHalo[itera][1] = y + sqcSparkHaloXY[sqcSparkHaloCounter][1];

				sqcSparkHalo[itera][2] = 0.3f*size;

				sqcSparkHaloCounter++;
				if (sqcSparkHaloCounter == sqcSparkHaloXY.length)
					sqcSparkHaloCounter = 0;
			}
		}
		while (++itera < sqcSparkHalo.length);

		if
		(
			Math.abs(prX) < size
				&&
			Math.abs(prY) < size
		)
		{
			elem.isDying = 1;

			ProgressBar.arideStep--;

			sound2.play(0.08f);

			return true;
		}

		return false;
	}
}
