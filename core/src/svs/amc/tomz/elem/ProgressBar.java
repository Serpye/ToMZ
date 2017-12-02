package svs.amc.tomz.elem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import svs.amc.tomz.InputPrcs;
import svs.amc.tomz.Lcynte;
import svs.amc.tomz.gp.GameProcess;

/**
 * Created by serpye on 6/12/17.
 */
public class ProgressBar
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static short x;
	public static short y;

	public static short width;
	public static short height;

	public static short size;

	public static float sqcColor[][];
	public static short sqcAccum[];
	public static short maxAccum;
	public static short sqcInc[];
	public static boolean sqcIsGrow[];
	public static short maxBright;

	public static final TextureRegion texturePointer = new TextureRegion
			(
				new Texture(Gdx.files.internal("progressBarPointer.png"))
			);
	public static short aride = 0;

	public static float arideStep;

	public static short sizePointer;
	public static short yPointer;
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static void init()
	{
		height = (short)(Lcynte.screenWidth / 100);
		maxBright = (short)(height << 1);
		width = (short)(Cell.sqcCell.length*Cell.size);

		x = (short)(Lcynte.screenWidth - InputPrcs.ofsX + (InputPrcs.ofsX - width) >> 1);

		y = (short)(InputPrcs.ofsY >> 1);

		yPointer = (short)(y + (height >> 1));
		sizePointer = (short)(height << 2);

		arideStep = aride = 0;
	}

	public static void dameute()
	{
		short itera = 0;
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		GameProcess.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		do
		{
			if
			(
				sqcColor[itera][0] == 0
					&&
				sqcColor[itera][1] == 0
					&&
				sqcColor[itera][2] == 0
			)
				break;

			GameProcess.shapeRenderer.setColor
					(
							sqcColor[itera][0],
							sqcColor[itera][1],
							sqcColor[itera][2],
							0.5f
					);

			GameProcess.shapeRenderer.rect
					(
						x + itera*size,
						y -sqcInc[itera],
						size,
						height + (sqcInc[itera] << 1)
					);

			if (sqcIsGrow[itera])
			{
				if (sqcInc[itera] > maxBright)
				{
					sqcInc[itera] = maxBright;
					sqcIsGrow[itera] = false;
				}

				else
					sqcInc[itera]++;
			}

			else
			{
				if (sqcInc[itera] < 1)
					sqcIsGrow[itera] = true;

				else
					sqcInc[itera]--;
			}

			GameProcess.shapeRenderer.setColor
								(
									sqcColor[itera][0],
									sqcColor[itera][1],
									sqcColor[itera][2],
									1f
								);


			GameProcess.shapeRenderer.rect(x + itera*size, y, size, height);
		}
		while(++itera < sqcColor.length);

		GameProcess.shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	public static void dameutePointer()
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

		if
		(
			sqcColor[index][0] == 0
				&&
			sqcColor[index][1] == 0
				&&
			sqcColor[index][2] == 0
		)
		{
			if (index == 0)
			{
				Lcynte.spriteBatch2.setColor(0.1f, 0.1f, 0.1f, 1f);
				Lcynte.spriteBatch2.draw
						(
							texturePointer,
							(ProgressBar.x + ProgressBar.size*index)
									+
									size*sqcAccum[index] / maxAccum - (sizePointer >> 1),
							yPointer - (sizePointer >> 1),
							sizePointer >> 1,sizePointer >> 1,
							sizePointer, sizePointer,
							1,
							1,
							aride
						);

				return;
			}

			else
				index--;

		}

		Lcynte.spriteBatch2.setColor(sqcColor[index][0], sqcColor[index][1], sqcColor[index][2], 1f);
		Lcynte.spriteBatch2.draw
				(
						texturePointer,
						(ProgressBar.x + ProgressBar.size*index)
								+
							size*sqcAccum[index] / maxAccum - (sizePointer >> 1),
						yPointer - (sizePointer >> 1),
						sizePointer >> 1,sizePointer >> 1,
						sizePointer, sizePointer,
						1,
						1,
						aride -= arideStep
				);
	}
	/*Lcynte.spriteBatch.draw
							(
									sqcImage[0],
									sqcCell[x][y].x,
									sqcCell[x][y].y,
									size >> 1,size >> 1,
									size, size,
									1,
									1,
									30
							);*/
}
