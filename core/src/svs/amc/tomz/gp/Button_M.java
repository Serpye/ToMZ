package svs.amc.tomz.gp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import svs.amc.tomz.Lcynte;

/**
 * Created by serpye on 6/11/17.
 */
public class Button_M extends TextComponent
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static short height;

	public static final Texture sqcTexture[] =
	{
		new Texture(Gdx.files.internal("button/GP_ButtF1.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF2.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF3.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF4.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF5.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF6.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF7.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF8.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF9.png")),
		new Texture(Gdx.files.internal("button/GP_ButtF10.png"))
	};

	public static final Texture textureBack =  new Texture(Gdx.files.internal("button/GP_ButtBack.png"));

	public ActionClass actionClass;

	public static Sound sound;
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Button_M(int x, int y, String text)
	{
		super(x, y);

		setText(text);
	}
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public void setText(String string)
	{
		text = string;

		GlyphLayout glyphLayout = new GlyphLayout(Lcynte.font, text);
		ofsXText = (short)(size*0.6f - ((int)((size >> 1) - glyphLayout.width) >> 1));
		ofsYText = (short)(height*0.9f - ((int)((size >> 1) - glyphLayout.height) >> 1));
	}

	public void dameute()
	{
		Lcynte.spriteBatch2.setColor(remise, neurelle, aluese, 1f);
		Lcynte.spriteBatch2.draw(textureBack, x, y, size, height);

		Lcynte.font.setColor(remise, neurelle, aluese, 1f);
		Lcynte.font.draw(Lcynte.spriteBatch2, text, x + ofsXText, y + ofsYText);

		Lcynte.spriteBatch2.draw(sqcTexture[textureFrame], x, y, size, height);
	}
}
