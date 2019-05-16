/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.business;

import com.mycompany.appcells.model.Cell;
import java.util.List;

/**
 *
 * @author giocode
 */
public interface CellBusiness {

    List<Cell> getCells();

    void reserveCell(Cell cell);

    void freeCell(String id);

    Cell updateCell(Cell cell);

}
