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
    }

    public static List<Cell> getCellList() {
        return CELLS;
    }

}
