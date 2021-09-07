# How to open project in IntelliJ

1. Open any old project in IntelliJ
2. Go to `File -> New -> Project from Existing Sources...`
3. Navigate to where you cloned the repo
4. Expand the `intellij` folder, select `pom.xml` and hit ok
5. Wait for Intellij to load all dependecies
6. You can now run the application using the following methods:
    - Using terminal: Navigate to the `intellij` folder and run `mvn clean javafx:run`
    - Using IntelliJ: Expand the maven tab to the far right. Expand `Plugins -> javafx` and doubleclick `javafx:run`
