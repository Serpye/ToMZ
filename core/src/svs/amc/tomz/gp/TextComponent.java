package svs.amc.tomz.gp;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import svs.amc.tomz.Lcynte;

/**
 * Created by serpye on 6/28/17.
 */
public abstract class TextComponent
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public byte animation = 0;

	public short x;
	public short y;

	public static short size;

	public byte textureFrame;

	public String text;
	public short ofsXText;
	public short ofsYText;

	public static Object mutexTC = new Object();

	public static float remise;
	public static float neurelle;
	public static float aluese;
	/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public TextComponent(int x, int y)
	{
		this.x = (short)x;
		this.y = (short)y;
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void setText(String string)
	{
		text = string;

		GlyphLayout glyphLayout = new GlyphLayout(Lcynte.font, text);
		ofsXText = (short)(((int)(size - glyphLayout.width) >> 1));
		ofsYText = (short)(((int)(size - glyphLayout.height) >> 1));
	}

	public abstract void dameute();
}
