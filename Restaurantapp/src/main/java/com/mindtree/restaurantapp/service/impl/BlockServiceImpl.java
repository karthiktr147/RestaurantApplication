package com.mindtree.restaurantapp.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.restaurantapp.dto.BlockDto;
import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;
import com.mindtree.restaurantapp.repository.BlockRepository;
import com.mindtree.restaurantapp.service.BlockService;

@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	public BlockRepository blockRepository;

	public Block addblock(BlockDto block) throws RestaurantAppServiceexception {
		Block block1 = new Block();
		block1.setBlockName(block.getBlockName());
		return blockRepository.save(block1);
	}

	public String writeExcelBlock(Block block,String path) throws RestaurantAppServiceexception, IOException {
		int rowIndex = 0;
		Workbook workbook = new XSSFWorkbook();
		Sheet studentsSheet = workbook.createSheet("Block");
		Row row = studentsSheet.createRow(rowIndex++);
		int cellIndex = 0;
		row.createCell(cellIndex++).setCellValue(block.getBlockId());
		row.createCell(cellIndex++).setCellValue(block.getBlockName());
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fos.close();
		return " Writing the shop object data into Excel File is Complete";
	}

	@Override
	public Block readExcelBlock(String path) throws RestaurantAppServiceexception, IOException {
		FileInputStream fis = new FileInputStream(path);
		Block block2 = new Block();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell blockCell = cellIterator.next();
				switch (blockCell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					block2.setBlockName(blockCell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					block2.setBlockId((int) blockCell.getNumericCellValue());
					break;
				}
			}
		}
		wb.close();
		fis.close();
		return block2;
	}

}
