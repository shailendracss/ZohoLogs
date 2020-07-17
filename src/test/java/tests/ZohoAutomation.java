package tests;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.xlUtil.DataTable;

import or.Dashboard;
import or.TimeTracker;

public class ZohoAutomation {

	@Test
	private void createJobs() {

	}

	@Test
	private void fillTimeLog() {
		Dashboard d = new Dashboard();
		d.openTimeTrackerPage();

		DataTable xl = new DataTable(Constant.getTestDataFilePath(), "TimeLogs");

		TimeTracker t = new TimeTracker();
		
		int rowCount = xl.getRowCount();

		for (int i = 0; i < rowCount; i++) {
			
			String fill = xl.getValue(i, "filled");
			String date = xl.getValue(i, "Date");
			
			if(!date.equals("") && fill.equals("")) {
				String proj = xl.getValue(i, "ProjectName");
				String job = xl.getValue(i, "JobName");
				String work = xl.getValue(i, "WorkItem");
				String time = xl.getValue(i, "Time");
				
				t.logTime(proj, job, work, date, time);
				
				CustomReporter.report(STATUS.INFO, "Updating Y in row "+(i+1));
				xl.setValue(i, "filled", "y");
			}
		}

	}
}
