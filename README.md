View as raw for better formating, somebody should fix this, idk how to.
=====
To begin working on this code you must do the following.

1. Open eclipse
2. Window->Open Perspective->Git / Other.. Git
3. Clone a Git Repository

  a. URL: https://github.com/jagorski2/board.git

  b. HOST: github.com
  
  c. Repository path: /jagorski2/board.git
  
  d. Protocol: https
  
  e. Port: Leave blank
  
  f. User: Enter your username that is a collaborator on to the repository
  
  g. Password: Enter your password for you github account.
  
4. CLick Next>
5. Select All
6. Click Next>
7. Local Destination
  a. Directory: Choose where you want git to put the repository

  b. Inital Branch: master
  
  c. Check Clone Submodules
  
  d. Remote name: origin
  
8. Click Finish
9. Add an ANDROID_HOME variable to your path
  a. Start

  b. Right Click on Computer
  
  c. Properties
  
  d. Advanced system settings (left column)
  
  e. Environment Variables...
  
  f. Click the "New..." button that is for User Variables not System
  
  g. Variable name: ANDROID_HOME
  
  h. Variable value: C:\Program Files (x86)\Android\android-sdk
  
  i. Click OK on all windows then exit out of the Computer Properties screen
  
10. Open cmd.exe
11. Navigate to the local destination you chose in Step 7
12. Type "gradlew.bat build" and press enter
13. Gradle will now build the projects, wait for it to finish.
14. Go back to eclipse
15 Import the Project
  a. File

  b. Import...
  
  c. Gradle
  
  d. Gradle Project
  
  e. Root Folder: This is the local destination chose is step 7
  
  f. Click Build Model
  
  g. Select All
  
  h. Finish
  
16. Done
    
