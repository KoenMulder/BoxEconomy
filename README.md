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
|/balance [player]                             |😃          |get [player]'s balance                                    | boxeconomy.admin   |
|/balance [player] remove [amount]             |😃          |remove money from [player]'s account                      | boxeconomy.admin   |
|/balance [player] add [amount]                |😃          |add money o [player]'s account                            | boxeconomy.admin   |
|/pay [player] [amount]                        |😃          |send money to another player                              |                    |
|/trade [player] [item] [amount] [money]       |😃          |trade request                                             |                    |
|/trade accept [player]                        |😃          |accept the trade request of [player                       |                    |
|/trade deny [player]                          |😃          |deny the trade request of [player]                        |                    |
|/auction start [item] [amount] [price] [time] |😴          |start a new auction with unique id                        |                    |
|/auction stop                                 |😴          |stop the current auction                                  |                    |
|/auction cancel [id]                          |😴          |cancel auction of id (admin command)                      |                    |
|/bid [auction-id] [amount]                    |😴          |bid in the specified auction                              |                    |
|/be reload                                    |😴          |reload BoxEconomy from the config.yml(admin command)      |                    |
|/be list                                      |😴          |shows the commands the player is allowed to use           |                    |
|/be help [command]                            |😴          |shows the description of the command                      |                    |
