Short: A minecraft plugin adding a team chest, that can be accessed through the command /teamchest.

Administrative Information:
- /viewTeamChest [team name]: 
  - let's any operator open any team chest
- /removeTeamChest [team name] [true | false]:
  - let's any operator remove a team and decide whether or not the items of the team chest should be added to his inventory (if the inventory is full, the items will be dropped)

config-parameters:
- teamChest.activated:
  - activates / deactivates the plugin
- teamChest.rows
  - sets the amount of rows to a fixed amount if teamChest.rowsAmountPerPerson is false
- teamChest.rowsAmountPerPerson
  - if enabled the size of the team chest is dependent on the amount of players in the team (every player until and including the 6-th player adds one row until the team chest reaches its full size of 54 slots (=double chest))