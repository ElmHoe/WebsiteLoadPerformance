package uk.co.ElmHoe;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import uk.co.ElmHoe.Utilities.DriverHandler;

public class WebsiteLoadPerformance {

	
	public static void main(String[] args)
	{

			//We build our driver instance
			DriverHandler h = new DriverHandler();
			h.setupDriver();
			//We ask the user what page they'd want to check
			System.out.println("Please enter a website URL to load with https included.");
			String urlToLoad = h.s.nextLine();
			h.driver.get(urlToLoad);
			outputPerformanceInfo(h.driver);
			
			//System.out.println("Please press the enter key to stop running.");
			//h.s.nextLine();
			
			h.endWebdriver();					
	}
	
	private static void outputPerformanceInfo(WebDriver h)
	{
		
		long navigationStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.navigationStart");
		long responseStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.responseStart");
		long domComplete = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domComplete");

		long backendPerformance = responseStart - navigationStart;
		long frontendPerformance = domComplete - responseStart;
		
		System.out.println("-----------------------------------------------");
		
		System.out.println(
			"Load Times in milisecond(s)" + "\n" +
				"Backend Performance: " + backendPerformance + "ms" + "\n" + 
				"Frontend Performance: " + frontendPerformance + "ms"
				);
		long backendSeconds = TimeUnit.MILLISECONDS.toSeconds(backendPerformance);
		long frontendSeconds= TimeUnit.MILLISECONDS.toSeconds(frontendPerformance);
		
		System.out.println(
			"Load Times in Second(s):" + "\n" +
				"Backend Peformance: " + backendSeconds + "s" + "\n" +
				"Frontend Performance: " + frontendSeconds + "s"
				);
		System.out.println("-----------------------------------------------");
	}
	
}
