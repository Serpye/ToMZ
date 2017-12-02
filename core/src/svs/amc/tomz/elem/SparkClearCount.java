package svs.amc.tomz.elem;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Enterante;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.gp.GameProcess;
import svs.amc.tomz.gp.TextComponent;

/**
 * Created by serpye on 7/1/17.
 */
public class SparkClearCount extends Spark
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Sound sound;
	public static Texture texture;

	public static float vel = Lcynte.screenWidth / 190.0f;
	public static float sparkSizeStep;

	public static short maxLength = 300;

	public static short sqcSparkXYItera = 0;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public SparkClearCount()
	{
		short index = 0;
		while
		(
			index < ProgressBar.sqcAccum.length
				&&
			ProgressBar.sqcAccum[index] >= ProgressBar.maxAccum
		)
			index++;

		if (index == ProgressBar.sqcColor.length)
			return;

		x = (ProgressBar.x + ProgressBar.size*index)
				+
				size*ProgressBar.sqcAccum[index] / ProgressBar.maxAccum;
		y =	ProgressBar.yPointer;

		size = ProgressBar.sizePointer;

		float originalSparkSize = 0.8f*size;
		sparkSizeStep = vel*originalSparkSize / maxLength;

		sqcSparkXY = new float[10][3];
		short itera = 0;
		do
		{
			sqcSparkXY[itera][0] = x;
			sqcSparkXY[itera][1] = y;

			sqcSparkXY[itera][2] = itera*originalSparkSize / sqcSparkXY.length;
		}
		while (++itera < sqcSparkXY.length);
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void dameuteDirectly()
	{
		short itera = 0;

		itera = sqcSparkXYItera;
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

		itera = 0;
		while (itera < sqcSparkXYItera)
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

			itera++;
		}

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
		float velX, velY;

		/*size -= desrParticalSize;

		if (size < ProgressBar.height << 1)
			size = ProgressBar.height << 1;*/

		float prX = (short)(GameProcess.componentClearQuantity.x + (TextComponent.size >> 1) - x);
		float prY = (short)(GameProcess.componentClearQuantity.y + (TextComponent.size >> 1) - y);

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
				sqcSparkXYItera++;

				if (sqcSparkXYItera == sqcSparkXY.length)
					sqcSparkXYItera = 0;

				sqcSparkXY[itera][0] = x;
				sqcSparkXY[itera][1] = y;

				sqcSparkXY[itera][2] = 0.8f*size;

			}
		}
		while (++itera < sqcSparkXY.length);

		if
		(
			Math.abs(GameProcess.componentClearQuantity.x + (TextComponent.size >> 1) - x) < size
				&&
			Math.abs(GameProcess.componentClearQuantity.y + (TextComponent.size >> 1) - y) < size
		)
		{
			if (Enterante.clearCount < Byte.MAX_VALUE)
				Enterante.clearCount++;

			GameProcess.componentClearQuantity.setText(Byte.toString(Enterante.clearCount));

			synchronized (mutex)
			{
				short iteraSparks = 0;
				do
				{
					SparkList.add(new Sparky
							(
									sqcSparkXY[iteraSparks][0],
									sqcSparkXY[iteraSparks][1],
									sqcSparkXY[iteraSparks][2],
									0,
									texture
							));
				}
				while (++iteraSparks < sqcSparkXY.length);

			}

			sound.play(0.2f);

			return true;
		}

		return false;
	}
}
