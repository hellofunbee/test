package com.jingu.IOT.util.excelUtils;

public interface ExcelRowIterator {

	public void init();

	public boolean nextRow();

	public String getCellValue(int col);

	public int getCellCount();

	public byte getSheetIndex();

	public int getRowPos();

	public void prevRow();
}
