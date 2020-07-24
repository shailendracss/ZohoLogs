package tests;

import org.testng.annotations.Test;

import com.reporting.Reporter;
import com.reporting.STATUS;
import com.util.Constant;
import com.xl.ExcelManager;

import or.Dashboard;
import or.TimeTracker;

public class ZohoAutomation {

	@Test
	private void createJobs() {
		Dashboard d = new Dashboard();

		d.doCheckout();
	}

	@Test
	private void fillTimeLog() {
		Dashboard d = new Dashboard();
		
		d.doCheckout();
		
		d.openTimeTrackerPage();

		ExcelManager xl = new ExcelManager(Constant.getTestDataFilePath(), "TimeLogs");

		TimeTracker t = new TimeTracker();
		
		int rowCount = xl.getRowCount();

		for (int i = 0; i < rowCount; i++) {
			
			
			 // if (i==0) { break; }
			 
			
			String fill = xl.getValue(i, "filled");
			String date = xl.getValue(i, "Date");
			
			if(!date.equals("") && fill.equals("")) {
				String proj = xl.getValue(i, "ProjectName");
				String job = xl.getValue(i, "JobName");
				String work = xl.getValue(i, "WorkItem");
				String time = xl.getValue(i, "Time");
				String desc = xl.getValue(i, "Desc");
				
				boolean flagUpdate = t.logTime(proj, job, work, date, time, desc);
				
				if (flagUpdate) {
					Reporter.report(STATUS.INFO, "Updating Y in row "+(i+1));
					xl.setValue(i, "filled", "y");
				}else {
					Reporter.report(STATUS.FAIL, "NOT Updating Y in row "+(i+1));
				}
				
			}
		}
		
		d.doCheckout();
	}
}
