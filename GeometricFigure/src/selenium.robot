*** Settings ***
Library  SeleniumLibrary

*** Variables ***
${LOGIN URL}          http://www.poczta.onet.pl/
${BROWSER}      Chrome
@{list} =   Niepoprawny e-mail lub hasło.   Wprowadź poprawne dane.


*** Test Cases ***
Valid Login
    Open main page
    Rodo
    Input Username
    Input Password
    Login button
    Assert Onet Mail
    [Teardown]    Close Browser

Invalid Login
    Open main page
    Rodo
    Input invalid login
    Input invalid password
    Login button
    Assert invalid
    [Teardown]  close browser

*** Keywords ***

Open main page
    Open browser    ${LOGIN URL}   ${BROWSER}
    Title Should Be    Poczta Onet – zaloguj się
Rodo
    wait until element is visible  css=button.cmp-button_button.cmp-intro_acceptAll
    Click Element  css=button.cmp-button_button.cmp-intro_acceptAll
Input Username
    Input Text	id=f_login	validmail@onet.pl
Input password
    Input Text  id=f_password   validpassword
Login button
    click element  css=input.loginButton
Assert Onet Mail
    page should contain element  id=NewMail-button
Input invalid login
    Input Text	id=f_login  blednylogin@onet.pl
Input invalid password
    Input Text  id=f_password   blednehaslo1
Assert invalid
    wait until element is visible   class=messageContent
    get text  class=messageContent
    Should Contain Any	${list}	Niepoprawny e-mail lub hasło.   Wprowadź poprawne dane