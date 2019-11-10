
Narrative:
I want to find by regex
So that I can get particular entity

Scenario: scenario description
Given the collection
When I type a regex: .*ABS
Then I got a result:
{transformer=FROM_LANDSCAPE}
   | name | ABS |
   | duration  | 50 |

Scenario: Remove entities by list
Given there is a collection of entities:
{transformer=FROM_LANDSCAPE}
   | name | ABS | Core| FBW |
   | duration  | 50 | 60 | 35 |   


When I give a list:
{transformer=FROM_LANDSCAPE}
   |name  |ABS | Core |
   |duration   | 50  | 60 |   


Then I get updatedList:
{transformer=FROM_LANDSCAPE}
   |name  |FBW |
   |duration   | 35      |