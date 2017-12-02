package svs.amc.tomz.elem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import svs.amc.tomz.InputPrcs;

/**
 * Created by serpye on 6/3/17.
 */
public class Element
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public float x;
	public float y;

	public static short size;
	public short elemSize;

	public static short counterMoving = 0;

	public byte isDying = 0; /*0 - nothing
								1 - dying
								2 - growing
								3 - moving*/

	public Cell cell;

	public static Texture sqcImage[] =
	{
		new Texture(Gdx.files.internal("e1.png")),
		new Texture(Gdx.files.internal("e2.png")),
		new Texture(Gdx.files.internal("e3.png")),
		new Texture(Gdx.files.internal("e4.png")),
		new Texture(Gdx.files.internal("e5.png")),
		new Texture(Gdx.files.internal("e6.png")),
		new Texture(Gdx.files.internal("e7.png")),
		new Texture(Gdx.files.internal("e8.png")),
		new Texture(Gdx.files.internal("e9.png")),
		new Texture(Gdx.files.internal("e10.png")),
		new Texture(Gdx.files.internal("e11.png")),
		new Texture(Gdx.files.internal("e12.png")),
		new Texture(Gdx.files.internal("e13.png")),
		new Texture(Gdx.files.internal("e14.png")),
		new Texture(Gdx.files.internal("e15.png")),
		new Texture(Gdx.files.internal("e16.png")),
		new Texture(Gdx.files.internal("e17.png")),
		new Texture(Gdx.files.internal("e18.png")),
		new Texture(Gdx.files.internal("e19.png")),
		new Texture(Gdx.files.internal("e20.png")),
		new Texture(Gdx.files.internal("e21.png")),
		new Texture(Gdx.files.internal("e22.png"))
	};

	public byte imageIndex;

	public float velocity = Cell.size / 150.0f;//200

	public short hor;
	public short ver;

	public static Object elemDeleteMutex = new Object();
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Element(Cell cell, int index)
	{
		this.cell = cell;

		short ofs = (short)((Cell.size - size) >> 1);

		x = cell.x + ofs;
		y = cell.y + ofs;

		imageIndex = (byte)index;

		elemSize = size;
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void checkForMoving()
	{
		if (cell.ysqc != 0 && Cell.sqcCell[cell.xsqc][cell.ysqc - 1].elem == null)
		{
			isDying = 3;
		}
	}

	public void moving()
	{
		y -= velocity;

		hor = (short)((x - InputPrcs.ofsX) / Cell.size);
		ver = (short)((y + elemSize - InputPrcs.ofsY) / Cell.size);

		cell.elem = null;
		cell = Cell.sqcCell[hor][ver];
		cell.elem = this;

		if (ver == 0 || Cell.sqcCell[hor][ver - 1].elem != null)
		{
			isDying = 0;

			short ofs = (short)((Cell.size - Element.size) >> 1);

			x = ofs + cell.x;
			y = ofs + cell.y;
		}
	}
}
