package pt.ipbeja.estig.baset02.model;

import pt.ipbeja.estig.baset02.gui.View;

import java.util.List;

/**
 * @author João Paulo Barros
 * @version 2019/05/11
 */
public class DummyView implements View {

    @Override
    public void updateText(List<List<Integer>> pieces) {
        // do nothing
    }

    @Override
    public void setOff(int row, int col) {

    }
}
