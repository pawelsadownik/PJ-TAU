using System;
using System.Drawing;
using System.Text;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace AutomaticTests
{
    [TestFixture]
    public class TryCreateAccountWithoutEmail
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
        public void TheCreateAnAccountWithoutEmailTest()
        {
            driver.Manage().Cookies.DeleteAllCookies();
            driver.Manage().Window.Size = new Size(1024, 768);
            driver.Navigate().GoToUrl("http://automationpractice.com/");
            driver.FindElement(By.LinkText("Sign in")).Click();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);            
            driver.FindElement(By.Name("SubmitCreate")).Click();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);            

            try
            {
                Assert.AreEqual("Invalid email address.", driver.FindElement(By.XPath("(.//*[normalize-space(text()) and normalize-space(.)='Create an account'])[1]/following::li[1]")).Text);            
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
