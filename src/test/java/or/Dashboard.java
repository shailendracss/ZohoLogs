package or;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class Dashboard {
	public static final String title = "Zoho People";

	public By peopleLoader = By.xpath("//div[@class='zp_loading']");

	@FindBy(xpath = "//span[.='Time Tracker']")
	private WebElement link_TimeTracker;
	
	SeleniumMethods com;

	public Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void openTimeTrackerPage() {
		com.click_UsingAction(link_TimeTracker, "link_TimeTracker");
	}

}
