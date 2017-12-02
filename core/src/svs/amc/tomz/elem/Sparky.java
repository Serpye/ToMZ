package svs.amc.tomz.elem;

import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Lcynte;


/**
 * Created by serpye on 7/1/17.
 */
public class Sparky extends Spark
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Texture texture;

	public static float vel = Lcynte.screenWidth / 200.0f;

	public float aride;
	public byte arideStepIndex;
	public static byte globalArideStepIndex;
	public static float sqcArideStep[];

	public static float sparkSizeStep;

	public static short maxLength = 50;

	public static short sqcSparkXYItera = 0;

	public static float desrParticalSize = 0.3f;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Sparky(float x, float y, float size, float vectorX, Texture texture)
	{
		this.x = x;
		this.y = y;

		this.texture = texture;

		if (vectorX == 0)
			aride = sqcArideStep[globalArideStepIndex];

		else
			aride = (float)Math.acos(vectorX);

		arideStepIndex = globalArideStepIndex++;

		if (globalArideStepIndex == sqcArideStep.length)
			globalArideStepIndex = 0;

//======|	Sparks
		this.size = size;
		float originalSparkSize = 0.8f*size;
		sparkSizeStep = vel*originalSparkSize / maxLength;

		sqcSparkXY = new float[20][3];
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
		x += Math.cos(aride)*vel;
		y += Math.sin(aride)*vel;

		aride += sqcArideStep[arideStepIndex];

		size -= desrParticalSize;

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

		if (size < 1)
			return  true;

		return false;
	}
}
