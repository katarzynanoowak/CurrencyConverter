import static org.junit.Assert.*; 
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before; 
import org.junit.Test;
import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.Keys;

public class Converter {

static WebDriver driver; 


@Before public void BrowserOpen() {
	
	//Provide own path to your driver
	System.setProperty("webdriver.chrome.driver", "C:\\\\Program Files\\\\Selenium\\\\chromedriver_win32\\\\chromedriver.exe");     
	driver= new ChromeDriver() ;     
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); } 



@Test public void PositiveCheck() { 

	driver.get("https://www.xe.com/currencyconverter"); 
	driver.manage().window().maximize(); 
	driver.findElement(By.xpath("//*[@id=\'__tealiumGDPRecModal\']/div/div/div[2]/button")).click();
	driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys("1234.78");
	
	
	WebDriverWait wait1 = new WebDriverWait(driver, 30);
	WebElement dropdown1;
	dropdown1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='from']")));
	dropdown1.sendKeys("PLN");
	dropdown1.sendKeys(Keys.ENTER);
		
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement dropdown;
	dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='to']")));
	dropdown.sendKeys("CAD");
	dropdown.sendKeys(Keys.ENTER);
	
	WebDriverWait waitt = new WebDriverWait(driver, 30);
	WebElement submit = waitt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#converterForm > form > button.Button-sc-1ikk70s-0.submitButton.SubmitButton-sc-6euey0-0.hEIFba")));
	submit.click();
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	
	if(driver.findElements(By.xpath("//*[@id=\'converterResult\']/section/div[1]/div[1]")).size() != 0){
		System.out.println("Conversion rate is Present");
		}else{
		System.out.println("Conversion rate is Absent");
		}
	if(driver.findElements(By.xpath("//*[@id=\'converterResult\']/section/div[1]/div[2]")).size() != 0){
		System.out.println("Conversion rate is Present");
		}else{
		System.out.println("Conversion rate is Absent");
		}

	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
}
@Test public void Convertion(){

	driver.get("https://www.xe.com/currencyconverter"); 
	driver.manage().window().maximize(); 
	driver.findElement(By.xpath("//*[@id=\'__tealiumGDPRecModal\']/div/div/div[2]/button")).click();
	driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys("10");
	
	
	
}

@Test public void NegativeValues() {

	driver.get("https://www.xe.com/currencyconverter"); 
	driver.manage().window().maximize(); 
	driver.findElement(By.xpath("//*[@id=\'__tealiumGDPRecModal\']/div/div/div[2]/button")).click();
	driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys("-10");

	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#converterForm > form > button.Button-sc-1ikk70s-0.submitButton.SubmitButton-sc-6euey0-0.hEIFba")));
	submit.click();
	
}
@Test public void NonNumericValues() {

	driver.get("https://www.xe.com/currencyconverter"); 
	driver.manage().window().maximize(); 
	driver.findElement(By.xpath("//*[@id=\'__tealiumGDPRecModal\']/div/div/div[2]/button")).click();
	driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys("letters&^#%@");
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#converterForm > form > button.Button-sc-1ikk70s-0.submitButton.SubmitButton-sc-6euey0-0.hEIFba")));
	submit.click();
}

@Test public void Swap() {
	driver.get("https://www.xe.com/currencyconverter"); 
	driver.manage().window().maximize(); 
	driver.findElement(By.xpath("//*[@id=\'__tealiumGDPRecModal\']/div/div/div[2]/button")).click();
	driver.findElement(By.xpath("//*[@id=\'amount\']")).sendKeys("100");
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement swap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#converterForm > form > button.Button-sc-1ikk70s-0.converterform-inverseButton.inverseButton.InverseButton-sc-1nb1vl2-0.fJevRG")));
	swap.click();
	
	WebDriverWait waitt = new WebDriverWait(driver, 30);
	WebElement submit = waitt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#converterForm > form > button.Button-sc-1ikk70s-0.submitButton.SubmitButton-sc-6euey0-0.hEIFba")));
	submit.click();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 	
}

@Test public void DropDownListSortedOrNot() throws InterruptedException {
	
	
		
		driver.get("https://www.xe.com/currencyconverter"); 
		driver.manage().window().maximize(); 
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement dropdown;
		dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='from']")));
	
		//dropdown.click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   
   List<WebElement> dropDownvalues = dropdown.findElements(By.id("react-select-4-option-1-45"));
   ArrayList<String> listValues = new ArrayList<String>();
   for(WebElement value : dropDownvalues) {
      System.out.println("values are"+ value.getText());
      listValues.add(value.getText());
   }
   boolean sortedOrNot = sortedOrNot(listValues);
   assertEquals(true, sortedOrNot);
}  
   public boolean sortedOrNot(ArrayList<String> dropDownValues) {
	   
	      System.out.println("number of values "+ dropDownValues.size());
	      for(int i=0; i<dropDownValues.size();i++) {
	         int temp = dropDownValues.get(i).compareTo(dropDownValues.get(i+1));
	         if(temp>1) {
	            System.out.println("i value"+i);
	            return false;
	         }
	      }
	      return true; 
	   }

@After public void BrowserClose() { 
		driver.quit(); 
		}   
}
