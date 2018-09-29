package uk.co.ElmHoe;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import uk.co.ElmHoe.Utilities.DriverHandler;

public class WebsiteLoadPerformance {

	
	public static void main(String[] args)
	{
		boolean runAgain = true;
			//We build our driver instance
			DriverHandler h = new DriverHandler();
			h.setupDriver();
			while (runAgain)
			{
				h.deleteCookies();
				//We ask the user what page they'd want to check
				System.out.println("Please enter a website URL to load with http included.");
				String urlToLoad = h.s.nextLine();
				if (urlToLoad.startsWith("http"))
				{
					h.driver.get(urlToLoad);
					outputPerformanceInfo(h.driver);
				}else{
					System.out.println("Invalid URL provided!");
				}
				System.out.println("Please type \"exit\" to exit, or press enter to run again.");
				if (h.s.nextLine().equals("exit"))
					runAgain = false;
			}

			h.endWebdriver();					
	}
	
	private static void outputPerformanceInfo(WebDriver h)
	{
		System.out.println("-----------------------------------------------");
		
		System.out.println(" --- Start of Data Dump --- ");
		long navigationStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.navigationStart");
		long redirectStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.redirectStart");
		long redirectEnd = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.redirectEnd");
		long fetchStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.fetchStart");
		long domainLookupStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domainLookupStart");
		long domainLookupFinish =(long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domainLookupEnd");
		long connectStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.connectStart");
		long secureConnectionStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.secureConnectionStart");
		long connectEnd = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.connectEnd");
		long requestStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.requestStart");
		long responseStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.responseStart");
		long responseEnd = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.responseEnd");
		long domLoading = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domLoading");
		long domInteractive = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domInteractive");
		long domContentLoadedStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domContentLoadedEventStart");
		long domContentLoadedEnded = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domContentLoadedEventEnd");
		long domComplete = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.domComplete");
		long loadEventStart = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.loadEventStart");
		long loadEventEnd = (long)((JavascriptExecutor) h).executeScript("return window.performance.timing.loadEventEnd");
		
		System.out.println("Navigation Start: " + navigationStart);
		System.out.println("Redirect Start: " + redirectStart);
		System.out.println("Redirect End: " + redirectEnd);
		System.out.println("Fetch Start: " + fetchStart);
		System.out.println("Domain Lookup Start: " + domainLookupStart);
		System.out.println("Domain Lookup Finish: " + domainLookupFinish);
		System.out.println("Connect Start: " + connectStart);
		System.out.println("Secure Connection Start: " + secureConnectionStart);
		System.out.println("Connect End: " + connectEnd);
		System.out.println("Request Start: " + requestStart);
		System.out.println("Response Start: " + responseStart);
		System.out.println("Response End: " + responseEnd);
		System.out.println("Dom Loading: " + domLoading);
		System.out.println("Dom Interactive: " + domInteractive);
		System.out.println("Dom Content Loaded Start: " + domContentLoadedStart);
		System.out.println("Dom Content Loaded End: " + domContentLoadedEnded);
		System.out.println("Dom Complete: " + domComplete);
		System.out.println("Load Event Start: " + loadEventStart);
		System.out.println("Load Event End: " + loadEventEnd);
		System.out.println(" --- End of Data Dump --- ");
		
		System.out.println("                          ");
		
		System.out.println(" --- Start of Advanced information --- ");
		System.out.println("Redirect Timing: " + (redirectEnd - redirectStart));
		System.out.println("Domain Lookup Timing: " + (domainLookupFinish - domainLookupStart));
		System.out.println("Dom Timing: " + (domComplete - responseEnd));
		if (loadEventEnd - navigationStart >= 5000)
			System.out.println("-----------------------" + "\n" + " " + "\n" + "WARNING: TOTAL LOAD TIME > 5 SECONDS" + "\n" + " " + "\n" + "-----------------------");
		System.out.println("Navigation Start - Load End: " + (loadEventEnd - navigationStart) + "ms");
		System.out.println(" --- End of Advanced information --- ");

		System.out.println("                          ");

		System.out.println(" --- Start of Basic information --- ");
		
		long backendPerformance = responseStart - navigationStart;
		long frontendPerformance = domComplete - responseStart;
		System.out.println(
			"Load Times in milisecond(s)" + "\n" +
				"Backend Performance: " + backendPerformance + "ms" + "\n" + 
				"Frontend Performance: " + frontendPerformance + "ms" + "\n" + 
				"Total Performance: " + (backendPerformance + frontendPerformance) + "ms"
				);
		System.out.println(" ");

		long backendSeconds = TimeUnit.MILLISECONDS.toSeconds(backendPerformance);
		long frontendSeconds= TimeUnit.MILLISECONDS.toSeconds(frontendPerformance);
		System.out.println(
			"Load Times in Second(s):" + "\n" +
				"Backend Peformance: " + backendSeconds + "s" + "\n" +
				"Frontend Performance: " + frontendSeconds + "s" + "\n" + 
				"Total Performance: " + (backendSeconds + frontendSeconds) + "s"
				);
		
		System.out.println(" --- End of Basic information --- ");
		
		System.out.println("-----------------------------------------------");
	}
	
}
