package or;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

public class Login {

	private static final String loginPagetitle = "Zoho Accounts";
	
	private static final String SHEET_NAME = Constant.getEnvironmentInfoSheet();
	
	@FindBy(xpath = "(//a[.='LOGIN'])[1]")
	private WebElement link_Login;
	
	@FindBy(id = "login_id")
	private WebElement text_Email;

	@FindBy(id = "nextbtn")
	private WebElement button_Next;

	@FindBy(id = "password")
	private WebElement text_Password;

	@FindBy(id = "nextbtn")
	private WebElement button_SignIn;

	SeleniumMethods com;

	public Login() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	
	public void performLogin(String type) {
		//com.navigateToAndVerifyPageTitle(link_Login,loginPagetitle);
		
		
		CustomReporter.report(STATUS.INFO, "xl file path " + Constant.getTestDataFilePath());
		
		String user = "";
		String pass = "";
		String userMap = "";

		DataTable DataTable = new DataTable(Constant.getTestDataFilePath(), SHEET_NAME);
		int rowCount = DataTable.getRowCount();
		int credentialsRow = -1;
		for (int row = 1; row < rowCount; row++) {
				String userType = DataTable.getValue(row, "user type");
				if (type.equalsIgnoreCase(userType)) {
					credentialsRow = row;
					break;
				}
		}

		if (credentialsRow != -1) {
			user = DataTable.getValue(credentialsRow, "username");
			pass = DataTable.getValue(credentialsRow, "password");
		} else {
			CustomReporter.report(STATUS.FAIL, "Passsed user type '" + type + "' is not present in the test data sheet "
					+ SHEET_NAME);
		}
		
		
		com.sendKeys("Username", text_Email, user);	
		com.click(button_Next, "Next");
		com.wait(2);
		com.sendKeys(text_Password, pass);	
		com.click(button_SignIn, "SignIn");
		com.wait(2);
		
		Dashboard d = new Dashboard();
		com.waitForElementTobe_NotVisible(d.peopleLoader);
		
		if (com.verifyPageTitle(Dashboard.title)) {
			CustomReporter.report(STATUS.PASS, "Login succeed with user id: " + userMap + " and username: " + user);
		} else {
			CustomReporter.report(STATUS.PASS,
					"Login Failed with user mapping id: " + userMap + " and username: " + user);
			Assert.fail("Login Failed with user mapping id: " + userMap + " and username: " + user);
		}
	}


	public Login load_App_URL() {
		
		CustomReporter.createNode("Loading Zoho URL");
		
		//DataTable dataTable = new DataTable(Constant.getTestDataFilePath(), SHEET_NAME);
		//String baseUrl = dataTable.getValue(1, "url"); 
		String baseUrl = "https://accounts.zoho.in/signin?servicename=zohopeople&signupurl=https://www.zoho.in/people/signup.html" ; 
		com.get(baseUrl);
		CustomReporter.report(STATUS.INFO, "Fired up url: "+"<br/><b style='font-size: small;'>"+baseUrl+"</b>");
		
		return this;
	}
	

}
