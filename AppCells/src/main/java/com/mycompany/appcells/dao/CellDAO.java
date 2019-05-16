/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.dao;

import com.mycompany.appcells.model.Cell;
import java.util.List;

/**
 *
 * @author sala312
 */
public interface CellDAO {

    List<Cell> getCells();

    Cell getCell(String id);

    Cell updateCell(Cell cell);

}
