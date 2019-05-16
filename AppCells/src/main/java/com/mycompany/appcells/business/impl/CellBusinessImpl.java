/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.business.impl;

import com.mycompany.appcells.business.CellBusiness;
import com.mycompany.appcells.dao.CellDAO;
import com.mycompany.appcells.dao.impl.CellDAOImpl;
import com.mycompany.appcells.model.Cell;
import java.util.List;

/**
 *
 * @author sala312
 */
public class CellBusinessImpl implements CellBusiness {

    CellDAO cellDAO = new CellDAOImpl();

    @Override
    public List<Cell> getCells() {
        return cellDAO.getCells();
    }

    @Override
    public void reserveCell(Cell cell) {
        cell.setReserved(true);
        cellDAO.updateCell(cell, true);
    }

    @Override
    public void freeCell(String id) {
        Cell cell = new Cell(id, "", "", "", false);
        cellDAO.updateCell(cell, true);
    }

    @Override
    public Cell updateCell(Cell cell) {
        return cellDAO.updateCell(cell, false);
    }

}
