using System;
using System.IO;
using System.Text;

using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;


namespace plukasiewicz.net
{
    [TestFixture]
    public class FormValidationTestWithScreenShot
    {
        private IWebDriver driver;
        private StringBuilder verificationErrors;
        private string baseURL;
        private bool acceptNextAlert = true;
        private readonly string path = "/Users/pawelsadownik/RiderProjects/AutomaticTests/AutomaticTests";
        
        [SetUp]
        public void SetupTest()
        {        

          driver = new ChromeDriver(path);

          baseURL = "";
          verificationErrors = new StringBuilder();
        }
        
        [TearDown]
        public void TeardownTest()
        {
            try
            {
                driver.Quit();
            }
            catch (Exception)
            {
            }
            Assert.AreEqual("", verificationErrors.ToString());
        }
        
        [Test]
        public void TheFormValidationTest()
        {
            driver.Navigate().GoToUrl("https://www.plukasiewicz.net/");
            driver.FindElement(By.LinkText("Kontakt")).Click();
            driver.FindElement(By.Id("Name")).SendKeys("Pawel");
            driver.FindElement(By.Id("Subject")).SendKeys("TEst");
            driver.FindElement(By.Id("Body")).SendKeys("TEst");
            driver.FindElement(By.Id("contactSubmit")).Click();
            try
            {
                Assert.AreEqual("Adres email jest wymagany", driver.FindElement(By.XPath("(.//*[normalize-space(text()) and normalize-space(.)='Adres email:'])[1]/following::span[1]")).Text);
                Screenshot shot = ((ITakesScreenshot) driver).GetScreenshot();
                shot.SaveAsFile(Path.Combine(path, DateTime.Now.Ticks +".png"));
            }
            catch (AssertionException e)
            {
                verificationErrors.Append(e.Message);
            }
        }
        private bool IsElementPresent(By by)
        {
            try
            {
                driver.FindElement(by);
                return true;
            }
            catch (NoSuchElementException)
            {
                return false;
            }
        }
        
        private bool IsAlertPresent()
        {
            try
            {
                driver.SwitchTo().Alert();
                return true;
            }
            catch (NoAlertPresentException)
            {
                return false;
            }
        }
        
        private string CloseAlertAndGetItsText() {
            try {
                IAlert alert = driver.SwitchTo().Alert();
                string alertText = alert.Text;
                if (acceptNextAlert) {
                    alert.Accept();
                } else {
                    alert.Dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }
}
