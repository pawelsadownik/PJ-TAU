using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

namespace AutomaticTests
{
    [TestFixture]
    public class RegisterSucces
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
        public void TheRegisterSuccesTest()
        {
            driver.Manage().Cookies.DeleteAllCookies();
            driver.Navigate().GoToUrl("http://automationpractice.com/");
            driver.FindElement(By.LinkText("Sign in")).Click();
            driver.FindElement(By.Id("email_create")).Click();
            driver.FindElement(By.Id("email_create")).Clear();
            driver.FindElement(By.Id("email_create")).SendKeys(new Random().Next(1000) + "xxx@xx.pl");
            driver.FindElement(By.Id("SubmitCreate")).Click();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);            
            driver.FindElement(By.Name("customer_firstname")).Clear();
            driver.FindElement(By.Name("customer_firstname")).SendKeys("Jan");
            driver.FindElement(By.Id("customer_lastname")).Clear();
            driver.FindElement(By.Id("customer_lastname")).SendKeys("Kowalski");
            driver.FindElement(By.Id("passwd")).Clear();
            driver.FindElement(By.Id("passwd")).SendKeys("123123");
            driver.FindElement(By.Id("address1")).Clear();
            driver.FindElement(By.Id("address1")).SendKeys("Karpinskiego");
            driver.FindElement(By.Id("city")).Clear();
            driver.FindElement(By.Id("city")).SendKeys("Gdynia");
            driver.FindElement(By.Id("postcode")).Clear();
            driver.FindElement(By.Id("postcode")).SendKeys("81173");
            driver.FindElement(By.Id("id_state")).Click();
            new SelectElement(driver.FindElement(By.Id("id_state"))).SelectByText("Arkansas");
            driver.FindElement(By.Id("phone_mobile")).Clear();
            driver.FindElement(By.Id("phone_mobile")).SendKeys("666666666");
            driver.FindElement(By.Id("submitAccount")).Click();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);     
            try
            {
                Assert.AreEqual("Welcome to your account. Here you can manage all of your personal information and orders.", driver.FindElement(By.XPath("(.//*[normalize-space(text()) and normalize-space(.)='My account'])[2]/following::p[1]")).Text);
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
