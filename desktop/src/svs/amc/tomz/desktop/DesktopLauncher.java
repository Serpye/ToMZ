package svs.amc.tomz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import svs.amc.tomz.Lcynte;

public class DesktopLauncher {
	public static void main (String[] sqcString)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;

		if (sqcString != null)
		{
			try
			{
				if (Boolean.parseBoolean(sqcString[0]))
					config.fullscreen = true;

				else
				{
					try
					{
						config.width = Integer.parseInt(sqcString[0]);
						config.height = Integer.parseInt(sqcString[1]);
					}

					catch (Exception exc2)
					{
						config.width = 1229; //1366
						config.height = 691; // 768
					}
				}
			}

			catch (Exception exc)
			{
				try
				{
					config.width = Integer.parseInt(sqcString[0]);
					config.height = Integer.parseInt(sqcString[1]);
				}

				catch (Exception exc2)
				{
					config.width = 1229; //1366
					config.height = 691; // 768
				}
			}
		}

		else
		{
			config.width = 1229; //1366
			config.height = 691; // 768
		}

		//System.out.println(config.width + "\t" + config.height);

		new LwjglApplication(new Lcynte(), config);
	}
}
