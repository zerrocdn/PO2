package pt.ipbeja.estig.baset02.gui;

import javafx.scene.control.Button;
import pt.ipbeja.estig.baset02.model.Position;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class CellButton extends Button
{
	private final Position position;

	public CellButton(String text, Position position)
	{
		super(text);
		this.position = position;
		this.setText(text);
	}

	/**
	 * @return the position
	 */
	public Position getPosition()
	{
		return this.position;
	}
}
