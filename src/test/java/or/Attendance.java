package or;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reporting.Reporter;
import com.reporting.STATUS;
import com.selenium.SeleniumMethods;
import com.selenium.webdriver.DriverFactory;


public class Attendance {
	private SeleniumMethods com;
	public static String title = "attendance";

	public Attendance() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(xpath = "//div[@title='Check-in']")
	private WebElement btn_CheckIn;
	
	@FindBy(xpath = "//div[@title='Check-out']")
	private WebElement btn_CheckOut;
	
	public void doCheckoutAndVerify() {
		
		if(!com.waitForElementsTobe_NotVisible(btn_CheckOut,0)) {
			com.click_UsingAction(btn_CheckOut, "btn_CheckOut");
			com.waitForElementsTobe_Present(btn_CheckIn, "btn_CheckIn");
			com.wait(2);
			
			//com.click_UsingAction(btn_CheckIn, "btn_CheckIn");
			//com.waitForElementsTobe_Present(btn_CheckOut, "btn_CheckOut");
		}else {
			Reporter.report(STATUS.PASS, "You are already Checked Out");
		}
		
	}

	

}
