import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.internal.Action


import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.junit.Assert
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class steps {
	WebDriver driver

	@Given("I want to navigate to home")
	def navigate() {
		System.setProperty("webdriver.chrome.driver","D:\\FreshersRampUp\\MSTestFramework\\chromedriver.exe")
		driver = new ChromeDriver()
		driver.navigate().to('http://automationpractice.com/index.php')
		driver.manage().window().maximize()
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
	}


	@When("I click the login button")
	def toLoginPage(){
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a")).click()
	}

	@And("I give (.*) and (.*)")
	def login(String email, String password) {
		driver.findElement(By.id("email")).sendKeys(email)
		driver.findElement(By.id("passwd")).sendKeys(password)
	}
	@And("I login")
	def clickSignIn(){
		driver.findElement(By.id("SubmitLogin")).click()
	}

	@And("I click Women Section")
	def toWomenSection(){
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a")).click()
	}

	@And("From style select2 (.*)")
	def selectStyle(String optionToSelect){
		selectStyleOption("ul_layered_id_feature_6"  , optionToSelect)
	}

	@And("select 5th option from sort by")
	def selectSortBy(){
		driver.findElement(By.id("selectProductSort")).click()
		driver.findElement(By.xpath("//*[@id='selectProductSort']/option[6]")).click()
	}

	@And("Search for (.*)")
	def search(String SearchValue){
		driver.findElement(By.id("search_query_top")).sendKeys(SearchValue)
		driver.findElement(By.xpath("//*[@id='searchbox']/button")).click()
	}

	@And("I click on 'Quick View'")
	def quickView(){
		Actions actions = new Actions(driver)
		WebElement Product = driver.findElement(By.xpath("//*[@id='center_column']/ul/li/div/div[2]/h5/a"))
		actions.moveToElement(Product).build().perform()
		WebElement QuickView = driver.findElement(By.xpath("//*[@id='center_column']/ul/li/div/div[1]/div/a[2]/span"))
		actions.moveToElement(QuickView).build().perform()
		QuickView.click()
	}

	@And("I add the quanity as '2' and click on 'Add to cart'")
	def addToCart(){
		WebElement frame = driver.findElement(By.className("fancybox-iframe"))
		driver.switchTo().frame(frame)
		driver.findElement(By.xpath("//*[@id='quantity_wanted_p']/a[2]")).click()
		driver.findElement(By.xpath("//*[@id='add_to_cart']/button")).click()
		WebDriverWait w = new WebDriverWait(driver, 10)
		w.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h2")))
		WebElement Message = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[1]/h2"))
		String ActualMessage = Message.getText().trim()
		Assert.assertTrue(ActualMessage.contains("Product successfully added to your shopping cart"))
		driver.findElement(By.className("cross")).click()
	}

	@And("I click the cart button")
	def goToCart(){
		driver.findElement(By.xpath("//*[@id='header']/div[3]/div/div/div[3]/div/a/b")).click()
	}

	@And("I verify")
	def verify(){
		WebElement Title = driver.findElement(By.xpath("//*[@id='cart_title']"))
		String ActualTitle = Title.getText()
		Assert.assertTrue(ActualTitle.toLowerCase().contains("Shopping-cart summary".toLowerCase()))
		WebElement Name = driver.findElement(By.xpath("//*[@id='product_1_1_0_425677']/td[2]/p/a"))
		String ActualName = Name.getText()
		Assert.assertTrue(ActualName.toLowerCase().contains("Faded short sleeve t-shirt".toLowerCase()))
		WebElement Price = driver.findElement(By.xpath("//*[@id='total_product_price_1_1_425677']"))
		String ActualPrice = Price.getText()
		Assert.assertTrue(ActualPrice.contains("33.02"))
		WebElement Quantity = driver.findElement(By.cssSelector("input[class='cart_quantity_input form-control grey']"))
		String ActualQuantity = Quantity.getAttribute('value')
		WebElement Summary = driver.findElement(By.xpath("//*[@id='order_step']/li[1]"))
		String Class = Summary.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
	}

	@And("I click 'Proceed to Checkout' and navigate to Address")
	def afterSummary(){
		driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]/span")).click()
		WebElement Address = driver.findElement(By.xpath("//*[@id='order_step']/li[3]"))
		String Class = Address.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
	 }

	@And("I click 'Proceed to Checkout' and navigate to Shipping")
	def afterAddress(){
		driver.findElement(By.xpath("//*[@id='center_column']/form/p/button/span")).click()
		WebElement Shipping = driver.findElement(By.xpath("//*[@id='order_step']/li[4]"))
		String Class = Shipping.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
	}

	@And("I check if error message is displayed")
	def checkErrorMessage(){
		driver.findElement(By.xpath("//*[@id='form']/p/button/span")).click()
		WebElement Error = driver.findElement(By.xpath("//*[@id='order']/div[2]/div/div/div/div/p"))
		String ErrorMessage = Error.getText()
		Assert.assertTrue(ErrorMessage.toLowerCase().contains("You must agree to the terms of service before continuing.".toLowerCase()))
		driver.findElement(By.xpath("//*[@id='order']/div[2]/div/div/a")).click()
		driver.findElement(By.xpath("//*[@id='cgv']")).click()
	}

	@And("I click 'Proceed to Checkout' and navigate to Payment")
	def afterShipping(){
		driver.findElement(By.xpath("//*[@id='form']/p/button/span")).click()
		WebElement Payment = driver.findElement(By.xpath("//*[@id='step_end']"))
		String Class = Payment.getAttribute('class')
		Assert.assertTrue(Class.toLowerCase().contains("current".toLowerCase()))
	}

	@And("I select a mode of payment and confirm the order")
	def confirmOrder(){
		driver.findElement(By.xpath("//*[@id='HOOK_PAYMENT']/div[1]/div/p/a")).click()
		driver.findElement(By.xpath("//*[@id='cart_navigation']/button")).click()
	}

	@Then("My order is placed successfully")
	def Success() {
		WebElement Success = driver.findElement(By.xpath("//*[@id='center_column']/div/p/strong"))
		String SuccessMessage = Success.getText()
		Assert.assertTrue(SuccessMessage.toLowerCase().contains("Your order on My Store is complete.".toLowerCase()))
	}

	def void selectStyleOption(String locatorParameterValue, String optionToSelect){
		WebElement UL = driver.findElement(By.id(locatorParameterValue))
		List <WebElement> LIItems = UL.findElements(By.tagName("li"))
		for(WebElement e : LIItems) {
			String name = e.getText()
			if((name.contains(optionToSelect)) || (LIItems.indexOf(e) == 2)){
				e.findElement(By.className("checkbox")).click()
			}
		}
	}
}
