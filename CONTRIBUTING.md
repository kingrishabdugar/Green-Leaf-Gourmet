<h1 align="center">🍃Green-Leaf-Gourmet🍃</h1>
<h3 align="center">Foodies welcome here 😋😉 | Eat natural 🌱 – Eat organic 🥦 | Directly from Mother Earth🌍</h3>

<div align="center">

[![Github All Releases](https://img.shields.io/github/downloads/kingrishabdugar/Green-Leaf-Gourmet/total.svg)](https://github.com/kingrishabdugar/Green-Leaf-Gourmet/releases/)

<img width="500" src="ReadMe files/Welcome_Green-Leaf-Gourmet.png">
</div>

## Getting involved

:+1::tada: First of all, thanks for taking the time to contribute! :tada::+1:

If you wish to contribute, please open a **new issue** before making any pull request. **Every pull request should
have a reference to an issue.**


### Making your your first pull request

- First, fork this repository
- Clone it using ``` git clone https://github.com/[username]/Green-Leaf-Gourmet.git ```
- It is always recommended to make your changes in a new branch rather than master.
  So create a new branch using ``` git branch mybug ```
- Checkout into your new branch using ``` git checkout mybug ```
- Hack the code, and do all kinds of awesome stuff.
- After you are done add your changes using ``` git add --all```
- Commit your changes using ``` git commit ``` and provide a commit message.
- After you have committed your changes push your changes to your forked repository
  using ``` git push origin mybug ```
- Finally create a pull request from github.
- If everything is alright then soon your changes will get merged or else you will
  be asked to make changes.

There should be **only one commit per pull request**.

Please try to make sure that your commit message and body follows the
guidelines below.

- Commit message should be of the form : ``` Fixes issue #[issue_number] - what you solved in one line ```
- After commit message, there should be a commit body where you can mention what you did in short or in detail

Please try to follow this format as it will be helpul to stay aligned.


### Connecting to the Database locally :  
##### Note : To work on a local MySQL server it's important to connect to the MySQL locally before starting the application

#### Installing MySQL
- Head over to https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing and
- Follow the install instructions for the appropriate OS.
- For connecting to your local mysql database, you will also need to install the Connector/J JAR. You can
download the zip file here : https://dev.mysql.com/downloads/connector/j/5.1.html

#### How to Connect to MySQL from Windows Command Line
- Start by opening the Run command box in Windows. Use the keyboard shortcut – hold the Windows (super) key and press the letter R ```(Win+R)```.
- Then, type in ```cmd``` and press Enter. This command opens the Windows command line.
![image](https://user-images.githubusercontent.com/56007479/208224485-28bdf98e-17da-4dcf-8871-4cb4c11162b7.png)

- A black command line interface should launch, with white text and a cursor for you to type.
- Verify MySQL is Running on Windows
- Next, run the command to display a list of all the services that are currently running. Enter the following in the command prompt: ```net start```
- If MySQL is not on the list, you can start it using the Services panel. Enter the following command: ```services.msc```
- A new window will launch and display the list of services available on your system. Scroll down to find MySQL, and check the status column. Left-click the MySQL service to highlight it, then right-click to open a context menu. Finally, left-click on start.
- Connect to a Local MySQL Server
- Launch MySQL Command Line Client from Start Menu.
- MySQL will prompt you for your password. Enter the password, and you’ll connect to the MySQL server.

Alternatively,
- Start MySQL in Windows using the following command: ```mysql.exe -u[username] -p```
- Replace [username] with the username for your MySQL installation.
- Enter ```mysql.exe -uroot -p```, and MySQL will launch using the ```root``` user.
- MySQL will prompt you for your password. Enter the password from the user account you specified with the ```–u``` tag, and you’ll connect to the MySQL server.

