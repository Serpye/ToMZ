package svs.amc.tomz.gp;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by serpye on 6/11/17.
 */
public abstract class Condition
{
	public static Music music;
	public static Texture imageBackground;
	public static boolean isMenu;

	public abstract void dameute();

	public abstract void dispose();
}
