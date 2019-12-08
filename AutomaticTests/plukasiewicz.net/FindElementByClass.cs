using System;
using System.Text;
using Castle.Core.Configuration;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace plukasiewicz.net
{
    [TestFixture]
    public class FindElementByClass
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
        public void FindElement()
        {
            driver.Navigate().GoToUrl("https://www.plukasiewicz.net/");
            driver.FindElement(By.ClassName("copyright"));
            Assert.AreEqual("Copyright by Paweł Łukasiewicz. Kopiowanie oraz rozpowszechnianie treści bez wiedzy autora jest zabronione.", driver.FindElement(By.XPath("(.//*[normalize-space(text()) and normalize-space(.)='Kontakt'])[1]/following::p[5]")).Text);
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
