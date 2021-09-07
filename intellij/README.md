# How to open project in Intellij

1. Open any old project in Intellij
2. Go to `File -> New -> Project from Existing Sources...`
3. Navigate to where you cloned the repo
4. Expand the `Intellij` folder, select `pom.xml` and hit ok
5. Wait for Intellij to load all dependecies
6. To run (terminal) navigate to the `Intellij` folder and run `mvn clean javafx:run`
7. To run (Intellij) expand the maven tab to the far right. Expand `Plugins -> javafx` and doubleclick `javafx:run`
