package pt.ipbeja.estig.baset02.gui;

import java.util.List;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public interface View {
    void updateText(List<List<Integer>> pieces);

    void updateColumnNumbers(List<List<Integer>> nums);
}
