Project Development Environment Setup
====

1. Download [this file](http://198.199.86.104/Project_Install.zip)
2. Unzip Project_Install.zip
2. It Contains the following
  1. 7z920-x64.msi - 7-Zip installer 
  2. android.exe   - Android SDK installed
  3. Android.zip   - Contains the tooks, APIs, and extras. (So you don't have to download them from SDK Manager)
  4. Eclipse.zip   - Eclipse IDE for Java Developers with all of our plugins installed
  5. gwt.zip       - GWK SDK, this for some reason does not get kept with the eclipse install.
  6. install.bat   - Install batch file
  7. jdk.exe       -  JDK 8.11
3. Open up an Administator CMD.
  1. Hit Windows Key
  2. type cmd.exe
  3. Right click on the cmd.exe app
  4. Run as Administrator
4. In the Administartor cmd.exe navigate to where Project_Install.zip was extracted
5. Type install.bat
6. Your Environment is now set up to develop.

Cloning Instructions
=====

This Assumes the following that you have followed the above install instructions.


To begin working on this code you must do the following:

1. Open eclipse C:\project\eclipse\eclipse.exe
2. Window->Open Perspective->Git / Other.. Git
3. Clone a Git Repository
  1. URL: https://github.com/jagorski2/board.git
  2. HOST: github.com
  3. Repository path: /jagorski2/board.git
  4. Protocol: https
  5. Port: Leave blank
  6. User: Enter your username that is a collaborator on to the repository
  7. Password: Enter your password for you github account.
4. CLick Next>
5. Select All
6. Click Next>
7. Local Destination
  1. Directory: C:\project\git\board
  2. Inital Branch: master
  3. Check Clone Submodules
  4. Remote name: origin
8. Click Finish
10. Open cmd.exe (NOTE: a regular one, not an Administrator one as the  Path variables will be wrong)
11. cd C:\project\git\board
12. Type "gradlew.bat build" and press enter
13. Gradle will now build the projects, wait for it to finish.
14. Go back to eclipse
15 Import the Project
  1. File
  2. Import...
  3. Gradle
  4. Gradle Project
  5. Root Folder: C:\project\git\board
  6. Click Build Model
  7. Select All
  8. Finish
16. Change to the Java Perspective
17. Right click on my-gdx-game-html project which might have a Red exclamation point
18. Select Build Path -> Configure Build Path
19. Select Libraries Tab
20. Select GWT SDK
21. Click Edit...
22. Configure SDKs...
23. Click Add...
24. Installation directory: C:\project\Additional_Resources\gwt-2.6.1
25. Click Ok
26. Keep Clicking OK / Finish until back at Java Perspective
27. Done!
    
