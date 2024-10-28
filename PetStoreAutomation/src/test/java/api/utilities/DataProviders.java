package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		int rowNum = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);

		String apiData[][] = new String[rowNum][colCount];

		// Loop through rows and columns
		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colCount; j++) { 
				apiData[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}

		return apiData;
	}
	
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//TestData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		int rowNum = xl.getRowCount("Sheet1"); // Corrected the sheet name
		
		String apiData[] = new String[rowNum];
		
		// Loop through rows and fetch the username (assuming it's in the second column)
		for (int i = 1; i <= rowNum; i++) {
			apiData[i - 1] = xl.getCellData("Sheet1", i, 1); // Column index for username (1 in this case)
		}
		
		return apiData;
	}
}
