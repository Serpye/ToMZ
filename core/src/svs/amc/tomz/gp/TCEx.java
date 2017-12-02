package svs.amc.tomz.gp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import svs.amc.tomz.Lcynte;

/**
 * Created by serpye on 7/1/17.
 */
public class TCEx extends TextComponent
{
/*::		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Texture texture;
	public static Texture backTexture;

	public String text2;
	public short ofsXText2;
	public short ofsYText2;
/*::		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public TCEx(int x, int y, String text, String text2)
	{
		super(x, y);

		this.text = text;
		GlyphLayout glyphLayout = new GlyphLayout(Lcynte.font, text);
		ofsXText = (short)(((int)(size - glyphLayout.width) >> 1) + size / 7);
		ofsYText = (short)(((int)(size - glyphLayout.height*2) >> 1) + size / 5);

		setText(text2);
	}
/*::		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void setText(String string)
	{
		text2 = string;

		GlyphLayout glyphLayout = new GlyphLayout(Lcynte.font, text2);
		ofsXText2 = (short)(((int)(size - glyphLayout.width) >> 1));
		ofsYText2 = (short)(((int)(size - glyphLayout.height) >> 1));
	}

	public void dameute()
	{
		Lcynte.spriteBatch.draw(backTexture, x, y, size, size);

		Lcynte.font.setColor(remise, neurelle, aluese, 1f);
		Lcynte.font.draw(Lcynte.spriteBatch, text, x + ofsXText, y + ofsYText);
		Lcynte.font.draw(Lcynte.spriteBatch, text2, x + ofsXText2, y + ofsYText2);

		Lcynte.spriteBatch.draw(texture, x, y, size, size);
	}
}
