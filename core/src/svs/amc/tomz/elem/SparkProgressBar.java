package svs.amc.tomz.elem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.Enterante;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.gp.GameProcess;
import svs.amc.tomz.gp.TextComponent;

/**
 * Created by serpye on 6/12/17.
 */
public class SparkProgressBar extends Spark
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Sound sound;
	public static final Texture sqcSpark[] =
	{
		new Texture(Gdx.files.internal("spark/spark1.png")),
		new Texture(Gdx.files.internal("spark/spark2.png")),
		new Texture(Gdx.files.internal("spark/spark3.png")),
		new Texture(Gdx.files.internal("spark/spark4.png")),
		new Texture(Gdx.files.internal("spark/spark5.png")),
		new Texture(Gdx.files.internal("spark/spark6.png")),
		new Texture(Gdx.files.internal("spark/spark7.png")),
		new Texture(Gdx.files.internal("spark/spark8.png")),
		new Texture(Gdx.files.internal("spark/spark9.png")),
		new Texture(Gdx.files.internal("spark/spark10.png")),
		new Texture(Gdx.files.internal("spark/spark11.png")),
		new Texture(Gdx.files.internal("spark/spark12.png")),
		new Texture(Gdx.files.internal("spark/spark13.png")),
		new Texture(Gdx.files.internal("spark/spark14.png")),
		new Texture(Gdx.files.internal("spark/spark15.png")),
		new Texture(Gdx.files.internal("spark/spark16.png")),
		new Texture(Gdx.files.internal("spark/spark16.png")),
		new Texture(Gdx.files.internal("spark/spark18.png")),
		new Texture(Gdx.files.internal("spark/spark19.png")),
		new Texture(Gdx.files.internal("spark/spark20.png")),
		new Texture(Gdx.files.internal("spark/spark21.png")),
		new Texture(Gdx.files.internal("spark/spark22.png"))
	};

	public byte imageIndex;

	public static final float sqcColor[][] =
	{
		{0.28235295f, 0.75686276f, 0.9137255f},
		{0.09803922f, 0.10980392f, 0.105882354f},
		{0.7607843f, 0.18431373f, 0.16862746f},
		{0.36862746f, 0.16078432f, 0.0f},
		{0.49411765f, 0.5411765f, 0.627451f},
		{0.20784314f, 0.8352941f, 0.627451f},
		{0.37254903f, 0.21568628f, 0.5372549f},
		{0.023529412f, 0.09019608f, 0.08627451f},
		{0.39607844f, 0.09411765f, 0.13333334f},
		{0.84313726f, 0.2901961f, 0.043137256f},
		{0.72156864f, 0.12941177f, 0.39607844f},
		{0.14509805f, 0.5254902f, 0.13333334f},
		{0.6f, 0.050980393f, 0.13333334f},
		{0.98039216f, 0.49803922f, 0.13333334f},
		{0.12941177f, 0.29411766f, 0.13333334f},
		{0.76862746f, 0.05882353f, 0.09019608f},
		{0.5411765f, 0.05882353f, 0.050980393f},
		{0.9019608f, 0.9019608f, 0.0627451f},
		{0.28627452f, 0.2901961f, 0.22352941f},
		{0.28627452f, 0.32156864f, 0.6627451f},
		{0.2784314f, 0.39215687f, 0.6666667f},
		{0.1254902f, 0.16078432f, 0.23137255f}
	};


	public static float vel = Lcynte.screenWidth / 200.0f;
	public static float sparkSizeStep;
	public static short maxLength = 150;

	public static float desrParticalSize;
	//-------
	public static byte currentImageIndex;
	public static byte scoresBuffer = 1;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public SparkProgressBar (Cell cell)
	{
		x = cell.x + (Cell.size >> 1);
		y = cell.y + (Cell.size >> 1);

		size = Element.size;

		if (cell.elem != null)
			imageIndex = (byte)(cell.elem.imageIndex + Enterante.ofsRanQ);

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
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void dameuteDirectly()
	{
		short itera = 0;
		do
		{
			Lcynte.spriteBatch.draw
					(
							sqcSpark[imageIndex],
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
						sqcSpark[imageIndex],
						x - (size / 2),
						y - (size / 2),
						size,
						size
				);
	}


	public boolean moveDirectly()
	{
		float velX, velY;
		short index = 0;
		while
				(
				index < ProgressBar.sqcAccum.length
						&&
						ProgressBar.sqcAccum[index] >= ProgressBar.maxAccum
				)
			index++;

		if (index == ProgressBar.sqcColor.length)
		{
			synchronized (mutex)
			{
				GameProcess.reinit();
			}

			return false;
		}

		size -= desrParticalSize;

		if (size < ProgressBar.height << 1)
			size = ProgressBar.height << 1;

		float prX = (short)(ProgressBar.x + ProgressBar.size*index + (ProgressBar.size >> 1) - x);
		float prY = (short)(ProgressBar.y - y);

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

				sqcSparkXY[itera][2] = 0.8f* size;

			}
		}
		while (++itera < sqcSparkXY.length);

		synchronized (mutex)
		{
			if
					(
					Math.abs(ProgressBar.x + ProgressBar.size*index + (ProgressBar.size >> 1) - x) < size
							&&
							Math.abs(ProgressBar.y - y) < ProgressBar.height
					)
			{
				if (ProgressBar.sqcAccum == null)
				{
					ProgressBar.sqcColor[index][0] = sqcColor[imageIndex][0];
					ProgressBar.sqcColor[index][1] = sqcColor[imageIndex][1];
					ProgressBar.sqcColor[index][2] = sqcColor[imageIndex][2];
				}

				else
				{
					ProgressBar.sqcAccum[index]++;
					ProgressBar.sqcColor[index][0] += sqcColor[imageIndex][0];
					ProgressBar.sqcColor[index][1] += sqcColor[imageIndex][1];
					ProgressBar.sqcColor[index][2] += sqcColor[imageIndex][2];

					ProgressBar.sqcColor[index][0] /= 2;
					ProgressBar.sqcColor[index][1] /= 2;
					ProgressBar.sqcColor[index][2] /= 2;
				}

				if (imageIndex == currentImageIndex)
				{
					scoresBuffer++;

					if (scoresBuffer > Byte.MAX_VALUE)
						scoresBuffer = Byte.MAX_VALUE;
				}

				else
					scoresBuffer = 1;

				Enterante.scores += scoresBuffer;
				ProgressBar.arideStep++;

				currentImageIndex = imageIndex;

				synchronized (TextComponent.mutexTC)
				{
					GameProcess.componentScore.setText(Long.toString(Enterante.scores));
				}

				sound.play(0.03f);

				return true;
			}
		}

		return false;
	}
}
