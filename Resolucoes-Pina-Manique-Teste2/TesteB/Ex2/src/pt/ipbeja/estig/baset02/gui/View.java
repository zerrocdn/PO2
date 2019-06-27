package pt.ipbeja.estig.baset02.gui;

import pt.ipbeja.estig.baset02.model.Position;

import java.util.List;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public interface View {
    void swapCell(Position pos);

    void updateText(List<List<Integer>> pieces);
}
