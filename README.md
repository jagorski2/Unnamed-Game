Cloning Instructions
=====


This Assumes the following:

1. Eclipse for Java Developers is installed
2. All Plugins are installed
3. Java JDK is installed
4. Andoird SDK is installed
To begin working on this code you must do the following:

1. Open eclipse
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
  1. Directory: Choose where you want git to put the repository
  2. Inital Branch: master
  3. Check Clone Submodules
  4. Remote name: origin
8. Click Finish
9. Add an ANDROID_HOME variable to your path
  1. Start
  2. Right Click on Computer
  3. Properties
  4. Advanced system settings (left column)
  5. Environment Variables...
  6. Click the "New..." button that is for User Variables not System
  7. Variable name: ANDROID_HOME
  8. Variable value: C:\Program Files (x86)\Android\android-sdk
  9. Click OK on all windows then exit out of the Computer Properties screen
10. Open cmd.exe
11. Navigate to the local destination you chose in Step 7
12. Type "gradlew.bat build" and press enter
13. Gradle will now build the projects, wait for it to finish.
14. Go back to eclipse
15 Import the Project
  1. File
  2. Import...
  3. Gradle
  4. Gradle Project
  5. Root Folder: This is the local destination chose is step 7
  6. Click Build Model
  7. Select All
  8. Finish
16. Done
    
