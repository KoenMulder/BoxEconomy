BoxEconomy
==========

![](https://raw.githubusercontent.com/KoenMulder/BoxEconomy/master/boxeconomy.png)

The BoxEconomy Bukkit Economy Plugin

## Dependencies

* https://dl.bukkit.org/downloads/craftbukkit/
* https://code.google.com/p/google-gson/
* https://bitbucket.org/xerial/sqlite-jdbc
 
## Commands

|  name                                        |   status  |     description                                          | permissions        |
|----------------------------------------------|-----------|----------------------------------------------------------|---------------------
|/balance                                      |😃          |get your current account balance                          |                    |
|/balance [player]                             |😃          |shows the spedified players account                       | boxeconomy.admin   |
|/balance [player] remove [amount]             |😃          |remove money from the players account                     | boxeconomy.admin   |
|/balance [player] add [amount]                |😃          |add money to the players account                          | boxeconomy.admin   |
|/pay [player] [amount]                        |😃          |give money to another player                              |                    |
|/trade [player] [item] [amount] [money]       |😃          |trade request                                             |                    |
|/trade accept [player]                        |😃          |trade request accept                                      |                    |
|/trade deny [player]                          |😃          |trade request deny                                        |                    |
|/auction start [item] [amount] [price] [time] |😴          |start an auction with unique id                           |                    |
|/auction stop                                 |😴          |stops the auction the player created                      |                    |
|/auction cancel [id]                          |😴          |cancel auction of id (admin command)                      |                    |
|/bid [auction-id] [amount]                    |😴          |bid in the specified auction                              |                    |
|/be reload                                    |😴          |reload BoxEconomy from the config.yml(admin command)      |                    |
|/be list                                      |😴          |shows the commands the player is allowed to use           |                    |
|/be help [command]                            |😴          |shows the description of the command                      |                    |
