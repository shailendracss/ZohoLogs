package or;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reporting.Reporter;
import com.selenium.SeleniumMethods;
import com.selenium.webdriver.DriverFactory;
import com.xl.ExcelManager;

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
	
	@FindBy(name = "Description")
	private WebElement text_Desc;
	
	@FindBy(xpath = "//button[.='Save']")
	private WebElement btn_Save;
	
	
	private void openLogTimeForm() {
		com.waitForElementTobe_Visible(btn_LogTime);
		com.click(btn_LogTime, "btn_LogTime");
		com.waitForElementTobe_Visible(select_ProjectName);
	}
	
	public boolean logTime(String project, String job, String workItem, String date, String time, String desc) {
		Reporter.createNode("Creating time log for | "+date+" | "+project+" | "+job+" | "+workItem+" | "+time+" | " + desc);
		openLogTimeForm();
		searchAndFill(select_ProjectName, project);
		searchAndFill(select_JobName, job);
		searchAndFill(select_WorkItem, workItem);
		com.sendKeys("Date", text_Date, date);
		com.sendKeys("Time", text_Time, time);
	
		if(!"".trim().equals(desc)) {
			com.sendKeys("Desc",text_Desc, desc);
		}
		
		com.click(btn_Save, "btn_Save");
		com.waitForElementsTobe_Present(By.id("alert-success"));
		
		return !com.isClickable(select_ProjectName,0);
	}
	
	private void searchAndFill(WebElement select_Obj, String txt) {
		com.click_UsingAction(select_Obj);
		
		com.sendKeys(com.switchTo_ActiveElement(), txt);
		
		com.click_UsingAction(By.xpath("//div[contains(.,'"+txt+"')][contains(@class,'label')]"), txt);
		
		com.sendKeys(text_Desc, Keys.ESCAPE);
	}
}
