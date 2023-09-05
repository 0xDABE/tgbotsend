# tgbotsend
Script and java program complex, which makes you to send files from your PC (Termux too) to Telegram.
Telegram Bot will send you files you sent before from terminal to keep them on Telegram Servers (50 MiB file limit TelegramAPI).
# Requirements:
 - [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or above
 - Telegram Bot data: Bot's `token`, bot's `username` and your `ChatID`.
 - (optional) Terminal which supports ANSI escape codes ([Windows Terminal](https://apps.microsoft.com/store/detail/windows-terminal/9N0DX20HK701?hl=en-en&gl=en), Konsole)
 # Running .jar:
 - Unzip all files in archieve to any directory. (You must not rename config file, but you can do it with .jar file)
 - Create a script, that launches .jar file:
 ## On Windows:
 - Make sure your java binaries are in PATH (OS environment). Try
 ```cmd
java
```
If you see info about java usage, then java is in PATH, else you should put it into path (you may not do it using absolute path)
- Create a .bat file:
### If Java in PATH:
```cmd
java -jar C:\Absolute\Path\To\Your\tgbotsend.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
```
- Make sure you are using java 17 binaries from PATH with
```cmd
java --version
```
### If NOT in path:
```cmd
C:\Absolute\Path\To\Java17orAbove\Binaries\java.exe -jar C:\Absolute\Path\To\Your\tgbotsend.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
```
#
- After creating .bat file move it to any separated directory and add this directory to PATH
- You can name .bat file as you wish, for example **snd.bat**
- Complete installation from Finish below
## On Linux:
- Make sure you are using Java 17:
```bash
java --version
```
- Create a bash script and name it as you wish, **snd** for example:
```bash
#!/bin/bash
java -jar /path/to/tgbotsend.jar $1 $2 $3 $4 $5 $6 $7 $8 $9
```
- Put it to `/home` directory or another directory, from which you can run scripts (`/usr/bin` for example)
- Make this script executable:
```bash
chmod u+x /path/to/script
```
- Complete installation from Finish below
# Finish
- Try:
```cmd
snd
```
in your terminal. If you see a help page you are done. Now you should to fill the config file.
- Fill the config file with bot `token`, `username` and your `ChatID`.
- To send files or directories put them as arguments in terminal:
```cmd
snd dir1 dir2 file1.txt file2.mp3 file3
```
- Converting .bat to .exe file is recomended.
