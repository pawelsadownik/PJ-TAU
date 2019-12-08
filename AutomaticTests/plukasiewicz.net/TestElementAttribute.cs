using System;
using System.Drawing;
using System.Text;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;


namespace plukasiewicz.net
{
    [TestFixture]
    public class TestElementAttribute
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
        public void GetElement()
        {
            driver.Navigate().GoToUrl("https://www.plukasiewicz.net/");
            var attribute = driver.FindElement(By.ClassName("navbar-header")).Text;
            Assert.AreEqual("Programowanie w .NET", attribute);

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
