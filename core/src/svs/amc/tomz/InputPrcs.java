package  svs.amc.tomz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import svs.amc.tomz.elem.Cell;
import svs.amc.tomz.elem.Element;
import svs.amc.tomz.gp.*;

/**
 * Created by serpye on 6/5/17.
 */
public class InputPrcs implements com.badlogic.gdx.InputProcessor
{
/*::|	FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Cell activeCell;

	public static Element activeElem;

	public static short ofsX;
	public static short ofsY;

	public static boolean isMoving;

	public static TextComponent activeComponent;

	public static Sound sound;

	public static String command = "";
/*::|	F/P		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public boolean mouseMoved(int x, int y)
	{
		y = Lcynte.screenHeight - y;

		/*if (Condition.isMenu == false)
		{
			synchronized (Cell.cellMutex)
			{
				if
						(
						x > ofsX
								&&
								x < ofsX + Cell.size*Enterante.horis
								&&
								y > ofsY
								&&
								y < ofsY + Cell.size*Enterante.vertis
						)
				{
					short hor = (short)((x - ofsX) / Cell.size);
					short ver = (short)((y - ofsY) / Cell.size);

					if (activeCell != null)
					{
						if (activeCell.xsqc == hor && activeCell.ysqc == ver)
							return true;

						activeCell.animation = 2;
					}

					activeCell = Cell.sqcCell[hor][ver];

					activeCell.animation = 1;

					return true;
				}
			}

			if
					(
					x > GameProcess.buttonRemix.x
							&&
							x < TextComponent.size + GameProcess.buttonRemix.x
							&&
							y > GameProcess.buttonRemix.y
							&&
							y < GameProcess.buttonRemix.y + Button_M.height
					)
			{
				if (activeComponent != null)
				{
					if (activeComponent == GameProcess.buttonRemix)
						return true;

					activeComponent.animation = 2;
				}

				activeComponent = GameProcess.buttonRemix;

				activeComponent.animation = 1;
			}

			else if
					(
					x > GameProcess.buttonMenu.x
							&&
							x < TextComponent.size + GameProcess.buttonMenu.x
							&&
							y > GameProcess.buttonMenu.y
							&&
							y < GameProcess.buttonMenu.y + Button_M.height
					)
			{
				if (activeComponent != null)
				{
					if (activeComponent == GameProcess.buttonMenu)
						return true;

					activeComponent.animation = 2;
				}

				activeComponent = GameProcess.buttonMenu;

				activeComponent.animation = 1;
			}

			else
			{
				if (activeComponent != null)
				{
					activeComponent.animation = 2;
					activeComponent = null;
				}
			}
		}

		else
		{
			if
			(
				x > Menu.buttonNewGame.x
					&&
				x < TextComponent.size + Menu.buttonNewGame.x
					&&
				y > Menu.buttonNewGame.y
					&&
				y < Menu.buttonNewGame.y + Button_M.height
			)
			{
				if (activeComponent != null)
				{
					if (activeComponent == Menu.buttonNewGame)
						return true;

					activeComponent.animation = 2;
				}

				activeComponent = Menu.buttonNewGame;

				activeComponent.animation = 1;
			}

			else if
			(
				x > Menu.buttonOptions.x
					&&
				x < TextComponent.size + Menu.buttonOptions.x
					&&
				y > Menu.buttonOptions.y
					&&
				y < Menu.buttonOptions.y + Button_M.height
			)
			{
				if (activeComponent != null)
				{
					if (activeComponent == Menu.buttonOptions)
						return true;

					activeComponent.animation = 2;
				}

				activeComponent = Menu.buttonOptions;

				activeComponent.animation = 1;
			}

			else if
			(
				x > Menu.buttonClose.x
					&&
				x < TextComponent.size + Menu.buttonClose.x
					&&
				y > Menu.buttonClose.y
					&&
				y < Menu.buttonClose.y + Button_M.height
			)
			{
				if (activeComponent != null)
				{
					if (activeComponent == Menu.buttonClose)
						return true;

					activeComponent.animation = 2;
				}

				activeComponent = Menu.buttonClose;

				activeComponent.animation = 1;
			}

			else
			{
				if (activeComponent != null)
				{
					activeComponent.animation = 2;
					activeComponent = null;
				}
			}
		}*/

		return true;
	}

	public boolean touchDown(int x, int y, int pointer, int button)
	{
		y = Lcynte.screenHeight - y;

		if (Condition.isMenu == false)
		{
			synchronized (Element.elemDeleteMutex)
			{
				if (isMoving || Element.counterMoving > 0)
					return true;

				if
						(
						x > ofsX
								&&
								x < ofsX + Cell.size*Enterante.horis
								&&
								y > ofsY
								&&
								y < ofsY + Cell.size*Enterante.vertis
						)
				{
					short hor = (short)((x - ofsX) / Cell.size);
					short ver = (short)((y - ofsY) / Cell.size);

					if
							(
							activeElem != null
									&&
									activeElem.cell != null
									&&
									Cell.sqcCell[hor][ver].elem != null
									&&
									activeElem != Cell.sqcCell[hor][ver].elem)
					{
						if
								(
								Math.abs(activeElem.cell.xsqc - hor) < 2
										&&
										Math.abs(activeElem.cell.ysqc - ver) == 0
										||
										Math.abs(activeElem.cell.xsqc - hor) == 0
												&&
												Math.abs(activeElem.cell.ysqc - ver) < 2
								)
						{
							isMoving = true;
							Enterante.change(activeElem, Cell.sqcCell[hor][ver].elem);
							activeElem = null;
						}

						else
							activeElem = Cell.sqcCell[hor][ver].elem;
					}

					else
						activeElem = Cell.sqcCell[hor][ver].elem;
				}
			}

			if
			(
				x > GameProcess.buttonRemix.x
					&&
				x < TextComponent.size + GameProcess.buttonRemix.x
					&&
				y > GameProcess.buttonRemix.y
					&&
				y < GameProcess.buttonRemix.y + Button_M.height
			)
			{
				GameProcess.buttonRemix.actionClass.onClick();
			}

			else if
			(
				x > GameProcess.buttonMenu.x
					&&
				x < TextComponent.size + GameProcess.buttonMenu.x
					&&
				y > GameProcess.buttonMenu.y
					&&
				y < GameProcess.buttonMenu.y + Button_M.height
			)
			{
				GameProcess.buttonMenu.actionClass.onClick();
				Button_M.sound.play(0.05f);
			}

			synchronized (Cell.cellMutex)
			{
				if
						(
						x > ofsX
								&&
								x < ofsX + Cell.size*Enterante.horis
								&&
								y > ofsY
								&&
								y < ofsY + Cell.size*Enterante.vertis
						)
				{
					short hor = (short)((x - ofsX) / Cell.size);
					short ver = (short)((y - ofsY) / Cell.size);

					if (activeCell != null)
					{
						if (activeCell.xsqc == hor && activeCell.ysqc == ver)
							return true;

						activeCell.animation = 2;
					}

					activeCell = Cell.sqcCell[hor][ver];

					activeCell.animation = 1;

					return true;
				}
			}
		}

		else
		{
			if
			(
				x > Menu.buttonNewGame.x
					&&
				x < TextComponent.size + Menu.buttonNewGame.x
					&&
				y > Menu.buttonNewGame.y
					&&
				y < Menu.buttonNewGame.y + Button_M.height
			)
			{
				Menu.buttonNewGame.actionClass.onClick();
				Button_M.sound.play(0.05f);
			}

			else if
			(
				x > Menu.buttonOptions.x
					&&
				x < TextComponent.size + Menu.buttonOptions.x
					&&
				y > Menu.buttonOptions.y
					&&
				y < Menu.buttonOptions.y + Button_M.height
			)
			{
				Menu.buttonOptions.actionClass.onClick();
				Button_M.sound.play(0.05f);
			}

			else if
			(
				x > Menu.buttonClose.x
					&&
				x < TextComponent.size + Menu.buttonClose.x
					&&
				y > Menu.buttonClose.y
					&&
				y < Menu.buttonClose.y + Button_M.height
			)
			{
				Menu.buttonClose.actionClass.onClick();
				Button_M.sound.play(0.05f);
			}
		}

		return true;
	}

	public boolean touchUp(int x, int y, int pointer, int button)
	{
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer)
	{
		return false;
	}

	public boolean scrolled(int amount)
	{
		return false;
	}

	public boolean keyDown(int keyCode)
	{
		/*if (keyCode == Input.Keys.SPACE)
		{
			short itera = 0;
			do
			{
				ProgressBar.sqcAccum[itera] = ProgressBar.maxAccum;
			}
			while (++itera < ProgressBar.sqcAccum.length);
		}*/
		
		/*else if (keyCode == Input.Keys.S)
		{
			Thread thread = new Thread()
			{
				public void run()
				{
					byte x = 0;
					byte y = 0;
					boolean flag = false;
					do
					{
						synchronized (Cell.cellMutex)
						{
							if (flag)
								Enterante.change(Cell.sqcCell[x][y].elem, Cell.sqcCell[x + 1][y].elem);

							else
								Enterante.change(Cell.sqcCell[x][y].elem, Cell.sqcCell[x][y + 1].elem);
						}

						if (flag)
						{
							if (++ y == Cell.sqcCell[0].length)
							{
								y = 0;

								if (++ x == Cell.sqcCell.length);
									x = 0;
							}
						}

						flag = !flag;

						try
						{
							Thread.sleep(5000);
						}

						catch (InterruptedException exc)
						{				}

					}
					while (true);
				}
			};

			thread.start();
		}*/

		/*else if (keyCode == Input.Keys.A)
			System.out.println(Element.counterMoving);*/

		return true;
	}

	public boolean keyTyped(char character)
	{
		command += character;

		return true;
	}

	public boolean keyUp(int keyCode)
	{
		if (keyCode == Input.Keys.ESCAPE)
			System.exit(0);

		else if (keyCode == Input.Keys.ENTER)
		{
			command = command.substring(0, command.length() - 1);
			if (command.equals("reset"))
				Gdx.files.local("file").writeString("1 0 0", false);

			command = "";
		}

		return false;
	}
}
