/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.data;

import com.mycompany.appcells.model.Cell;
import com.mycompany.appcells.util.RoleEnum;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sala312
 */
public class CellData {

    private static final List<Cell> CELLS;

    static {
        CELLS = new ArrayList<>();
        CELLS.add(new Cell("1"));
        CELLS.add(new Cell("2"));
        CELLS.add(new Cell("3"));
        CELLS.add(new Cell("4", "http://writingexercises.co.uk/images2/randomimage/tree.jpg", "15/05/2019", "1054", true));
        CELLS.add(new Cell("5"));
        CELLS.add(new Cell("6"));
        CELLS.add(new Cell("7"));
        CELLS.add(new Cell("8"));
        CELLS.add(new Cell("9"));
        CELLS.add(new Cell("10"));
        CELLS.add(new Cell("11"));
        CELLS.add(new Cell("12"));
        CELLS.add(new Cell("13"));
        CELLS.add(new Cell("14"));
        CELLS.add(new Cell("15"));
    }

    public static List<Cell> getCellList() {
        return CELLS;
    }

}
