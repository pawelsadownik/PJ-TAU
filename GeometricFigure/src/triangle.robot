*** Settings ***
Library    Process
Suite Teardown    Terminate All Processes    kill=True

Test Template     Should return correct triangle
*** Test Cases ***      a       b       c       expected



Niepoprawne dane #1     x       x       x       nieprawidłowe parametry
Niepoprawne dane #2     -1      -1      -1      nieprawidłowe parametry
Niepoprawne dane #3     0       0       0       nieprawidłowe parametry


Równoboczny             1       1       1       trójkąt równoboczny

Równoramienny b i c     2       1       1       trojkąt równoramienny
Równoramienny a i c     1       2       1       trojkąt równoramienny
Równoramienny a i b     1       1       2       trojkąt równoramienny

Różnoboczny c>b>a       2       4       5       trojkąt różnoboczny
Różnoboczny b>c>a       2       5       4       trojkąt różnoboczny
Różnoboczny a>b>c       5       4       2       trojkąt różnoboczny

Nieprawidłowy #1        1       2       3       nierozpoznano
Nieprawidłowy #2        1       4       3       nierozpoznano
Nieprawidłowy #3        4       3       1       nierozpoznano

Jeden argument #1         1   ${EMPTY}   ${EMPTY}    nierozpoznano
Dwa argumenty  #1         1       1      ${EMPTY}    nierozpoznano

*** Keywords ***
Should return correct triangle
    [Arguments]     ${a}    ${b}   ${c}    ${expected}
    ${result} =     Run Process     php     figure.php    ${a}   ${b}   ${c}
    Should Be Equal As Strings    ${result.stdout}    ${expected}