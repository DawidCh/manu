package com.chojnacki.manufaktura.manuliczek.output;

import com.chojnacki.manufaktura.manuliczek.model.Shop;

import java.awt.*;

public class PatioColorer extends Colorer {

    private final int cellCousineWidth = 110;
    private final int maxCousineName = 21;

    public PatioColorer() {
        cellsPerRow = 2;
        fillTable = false;
    }

    @Override
    public void setShopsCount(int shopsCount) {
        rows = (int) Math.ceil((float) shopsCount / (float) cellsPerRow);
        tableWidth = cellsPerRow * (cellIdWidth + cellNameWidth + cellCousineWidth + 4 * cellXMargin);
        tableHeight = rows * cellHeight;
    }

    @Override
    protected int[] getPeriodPosition() {
        return new int[]{1345, 75};
    }

    @Override
    public void paintShopInTable(Shop shop, int currentCounter, Graphics2D graphic) {
        int currentRow;
        int currentColumn;

        if (fillTable == Shop.VERTICAL) {
            currentRow = currentCounter % rows;
            currentColumn = (int) Math.floor(currentCounter / rows);
        } else {
            currentRow = (int) Math.floor(currentCounter / cellsPerRow);
            currentColumn = currentCounter % cellsPerRow;
        }

        //horizontal line
        graphic.drawLine(0, currentRow * cellHeight, tableWidth, currentRow * cellHeight);

        int columnWidth = (cellIdWidth + cellNameWidth + cellCousineWidth + 4 * cellXMargin);
        //vertical line
        graphic.drawLine(currentColumn * columnWidth, 0, currentColumn * columnWidth, tableHeight);
        graphic.setColor(Color.gray);
        graphic.drawLine(currentColumn * columnWidth + cellIdWidth, 0, currentColumn * columnWidth + cellIdWidth, tableHeight);
        graphic.drawLine(currentColumn * columnWidth + cellIdWidth + cellXMargin + cellNameWidth, 0, currentColumn * columnWidth + cellIdWidth + cellXMargin + cellNameWidth, tableHeight);
        graphic.setColor(Color.black);
        //draw strings
        graphic.drawString(shop.getShopId(), currentColumn * columnWidth + cellXMargin, (currentRow + 1) * cellHeight - cellYMargin);
        graphic.drawString(prepareShopName(shop), currentColumn * columnWidth + cellIdWidth + cellXMargin, (currentRow + 1) * cellHeight - cellYMargin);
        graphic.drawString(prepareCousine(shop), currentColumn * columnWidth + cellIdWidth + cellNameWidth + 2 * cellXMargin, (currentRow + 1) * cellHeight - cellYMargin);

        //draw border lines
        if (rows - 1 == currentRow) {
            graphic.drawLine(0, tableHeight - 1, tableWidth - 1, tableHeight - 1);
            graphic.drawLine(tableWidth, 0, tableWidth, tableHeight - 1);
        }
    }

    private String prepareCousine(Shop shop) {
        String cousine = shop.getCousine();
        if (cousine.length() > maxCousineName) {
            cousine = cousine.substring(0, maxCousineName + 1);
        }
        return cousine;
    }
}
