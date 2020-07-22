package or;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.SeleniumMethods;
import com.selenium.webdriver.DriverFactory;

public class Dashboard {
	public static final String title = "Zoho People";

	public By peopleLoader = By.xpath("//div[@class='zp_loading']");

	@FindBy(xpath = "//span[.='Time Tracker']")
	private WebElement link_TimeTracker;
	
	@FindBy(id = "zpeople_userimage")
	private WebElement img_User;

	@FindBy(linkText = "Sign Out")
	private WebElement link_Signout;
	
	SeleniumMethods com;

	public Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void openTimeTrackerPage() {
		com.click_UsingAction(link_TimeTracker, "link_TimeTracker");
	}

	public void logout() {
		com.click(img_User, "User's Image");
		com.wait(2);
		com.navigateToAndVerifyPageUrl(link_Signout, "logout");
	}

}
