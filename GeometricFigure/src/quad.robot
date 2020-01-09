*** Settings ***
Library    Process
Suite Teardown    Terminate All Processes    kill=True
Test Template     Should return correct quad

*** Test Cases ***      a       b       c       d       expected

Niepoprawne dane #1     -1      -1      -1      a       nieprawidłowe parametry
Niepoprawne dane #2     0       0       0       0       nieprawidłowe parametry

Kwadrat                 2       2       2       2       kwadrat
Prostokąt               2       2       3       3       prostokąt

Trapez           2       3       1       1       czworobok

Nierozpoznano       10      1       1       1       nierozpoznano

*** Keywords ***
Should return correct quad
    [Arguments]     ${a}    ${b}   ${c}     ${d}    ${expected}
    ${result} =     Run Process     php     figure.php    ${a}   ${b}   ${c}    ${d}
    Should Be Equal As Strings    ${result.stdout}    ${expected}