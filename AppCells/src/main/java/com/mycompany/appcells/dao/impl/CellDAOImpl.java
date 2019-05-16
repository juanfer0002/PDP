/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.dao.impl;

import com.mycompany.appcells.dao.CellDAO;
import com.mycompany.appcells.data.CellData;
import com.mycompany.appcells.model.Cell;
import com.mycompany.appcells.model.Cell;
import java.util.List;

/**
 *
 * @author sala312
 */
public class CellDAOImpl implements CellDAO {

    @Override
    public List<Cell> getCells() {
        return CellData.getCellList();
    }

    @Override
    public Cell updateCell(Cell cell, boolean updateStatus) {
        Cell cellInlist = this.getCell(cell.getId());

        if (cellInlist != null) {
            cellInlist.setDate(cell.getDate());
            cellInlist.setUserId(cell.getUserId());
            cellInlist.setPhoto(cell.getPhoto());

            if (updateStatus) {
                cellInlist.setReserved(cell.isReserved());
            }
        }

        return cellInlist;
    }

    @Override
    public Cell getCell(String id) {
        Cell lookingCell = new Cell(id);
        List<Cell> cells = this.getCells();
        int index = cells.indexOf(lookingCell);
        return index > -1 ? cells.get(index) : null;
    }

}
