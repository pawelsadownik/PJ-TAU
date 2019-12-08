using System;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

namespace plukasiewicz.net
{
    [TestFixture]
    public class RoutingTestWithScreenShot
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
        public void RoutingTest()
        {
            driver.Navigate().GoToUrl("https://www.plukasiewicz.net/");
            driver.FindElement(By.PartialLinkText("Kontakt")).Click();
            try
            {
                Assert.AreEqual("https://www.plukasiewicz.net/Contact", driver.Url);
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
