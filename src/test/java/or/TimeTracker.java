package or;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class TimeTracker {
	private SeleniumMethods com;
	public static String url = "timelogs";

	public TimeTracker() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//button[.='Log Time'][contains(@class,'primary')]")
	private WebElement btn_LogTime;

	@FindBy(xpath = "//label[.='Project Name']//following-sibling::div//a[contains(@class,'select')]")
	private WebElement select_ProjectName;

	@FindBy(xpath = "//label[.='Job Name']//following-sibling::div//a[contains(@class,'select')]")
	private WebElement select_JobName;
	
	@FindBy(xpath = "//label[.='Work Item']//following-sibling::div//a[contains(@class,'select')]")
	private WebElement select_WorkItem;
	
	@FindBy(name = "Date")
	private WebElement text_Date;
	
	@FindBy(id = "timelog_hrstime")
	private WebElement text_Time;
	
	@FindBy(xpath = "//button[.='Save']")
	private WebElement btn_Save;
	
	
	private void openLogTimeForm() {
		com.waitForElementTobe_Visible(btn_LogTime);
		com.click(btn_LogTime, "btn_LogTime");
		com.waitForElementTobe_Visible(select_ProjectName);
	}
	
	public boolean logTime(String project, String job, String workItem, String date, String time) {
		CustomReporter.createNode("Creating time log for | "+date+" | "+project+" | "+job+" | "+workItem+" | "+time+" | ");
		openLogTimeForm();
		searchAndFill(select_ProjectName, project);
		searchAndFill(select_JobName, job);
		searchAndFill(select_WorkItem, workItem);
		com.sendKeys("Date", text_Date, date);
		com.sendKeys("Time", text_Time, time);
		com.click(btn_Save, "btn_Save");
		return com.waitForElementsTobe_Present(By.id("alert-success"));
	}

	private void searchAndFill(WebElement select_Obj, String txt) {
		com.click_UsingAction(select_Obj);
		
		com.sendKeys(com.switchTo_ActiveElement(), txt);
		
		com.click_UsingAction(By.xpath("//div[contains(.,'"+txt+"')][contains(@class,'label')]"), txt);
		
		com.sendKeys(By.name("Description"), Keys.ESCAPE);
	}
}
