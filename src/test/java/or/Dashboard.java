package or;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reporting.Reporter;
import com.reporting.STATUS;
import com.selenium.SeleniumMethods;
import com.selenium.webdriver.DriverFactory;
import com.util.Constant;

public class Dashboard {
	public static final String title = "Zoho People";

	public By peopleLoader = By.xpath("//div[@class='zp_loading']");

	@FindBy(xpath = "//span[.='Time Tracker']")
	private WebElement link_TimeTracker;
	
	@FindBy(xpath = "//span[.='Attendance']")
	private WebElement link_Attendance;
	
	@FindBy(id = "zpeople_userimage")
	private WebElement img_User;

	@FindBy(linkText = "Sign Out")
	private WebElement link_Signout;
	
	@FindBy(xpath = "//div[@class='loader-inner line-scale']")
	private WebElement loader_LineScale;
	

	
	SeleniumMethods com;

	public Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void openTimeTrackerPage() {
		com.click_UsingAction(link_TimeTracker, "link_TimeTracker");
	}

	public void logout() {
		Reporter.createNode("Performing Logout");
		com.click(img_User, "User's Image");
		com.wait(2);
		com.click(link_Signout, "link_Signout");
		com.verifyPageUrl("logout", true);
	}

	public void doCheckout() {
		Reporter.createNode("Performing Checkout");
		Reporter.report(STATUS.INFO, "Enable Checkout Flag Value "+Constant.getCheckOutFlag());
		
		if("Y".equalsIgnoreCase(Constant.getCheckOutFlag())) {
			com.click_UsingAction(link_Attendance, "link_Attendance");
			com.verifyPageUrl(Attendance.title, true);
			com.wait(1);
			com.waitForElementTobe_NotVisible(loader_LineScale);
			
			Attendance a = new Attendance();
			a.doCheckoutAndVerify();
		}
	}

}
