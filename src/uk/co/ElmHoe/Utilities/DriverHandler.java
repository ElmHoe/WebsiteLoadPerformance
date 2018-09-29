package uk.co.ElmHoe.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import uk.co.ElmHoe.WebsiteLoadPerformance;

public class DriverHandler {
	
	public WebDriver driver;
	public ChromeOptions chromeOptions;
	public Scanner s;
	public WebDriverWait wait;
	public Actions actions;
	
	public void setupDriver()
	{
		InputStreamReader is = new InputStreamReader(System.in);
		s = new Scanner(is);

		//We setup chrome
		setChromeOptions(driver);
		
		//The wait timeout for a website, if it hangs waiting for a response.
		wait = new WebDriverWait(driver, 10);

	}
	
	public boolean setChromeOptions(WebDriver d)
	{
		System.setProperty("webdriver.chrome.driver", checkDriverExists());
		
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		actions = new Actions(driver);

		return true;	
	}
	
	private String checkDriverExists()
	{
		String fileSep = File.separator;
		net.harawata.appdirs.AppDirs appDirs = net.harawata.appdirs.AppDirsFactory.getInstance();
		
		File f = new File(appDirs.getUserConfigDir("ITG-Automation", "1.0", "ITG", true));
		if (!f.exists())
		{
			f.mkdirs();
		}
		
		File chromeDriver = new File (f.getPath() + fileSep + "chromedriver.exe");
		if (!chromeDriver.exists())
		{
			try
			{
				URL inputUrl = WebsiteLoadPerformance.class.getResource("/resources/chromedriver.exe");
				File dest = new File(chromeDriver.getParent() + fileSep + "chromedriver.exe");
				FileUtils.copyURLToFile(inputUrl, dest);
				System.out.println("Successfully copied driver from resources to: " + dest.getPath());
			} 
			catch(IOException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return chromeDriver.toString();
	}

	public void endWebdriver()
	{
		//Close the scanner
		s.close();
		
		//Attempt to close the website (may already be closed)
		try {
			driver.close();	
		}catch(Exception e){
			
		}
		//End the driver (closing it does not end the driver)
		driver.quit();
	}
}
