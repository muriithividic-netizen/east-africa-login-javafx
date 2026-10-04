# East Africa Login

A simple JavaFX login form with a username, password, and country calling-code
field. Typing a supported East African calling code shows its flag and country
name. The login is a demo only and does not check a real account.

## What the finished app does

The app opens a small window with username, password, and country-code fields.
As you type a supported calling code (for example, `+254`), the country's flag
and name appear immediately. Select **Log in** to see a message if a field is
missing or the country code is not supported. If the inputs are filled in, the
app displays a demo message; it does not verify the username or password.

The Java source includes comments explaining the main JavaFX controls, how
country lookup works, and what happens when you press the login button.

## Requirements

- Eclipse IDE 2026-09 (4.41.0) with the **Eclipse IDE for Java Developers**
  package (Maven integration is included)
- JDK 21 or newer
- Internet access on the first Maven build so Maven can download JavaFX and
  test dependencies

## Open in Eclipse

1. Select **File > Import... > Maven > Existing Maven Projects**.
2. Choose this project's folder and select `pom.xml`.
3. Finish the import and allow Maven to resolve the dependencies.
4. Right-click the project and select **Run As > Maven build...**.
5. Enter `javafx:run` as the goal and select **Run**.

Alternatively, open `LoginApplication.java` and run it as a Java application
after Maven has downloaded the project dependencies.

You can also run the test suite with the Maven goal `test`.

## Country codes

Enter the calling code with or without a leading `+`. The flag and country
name update as soon as a supported code is entered. Supported codes are
Burundi (+257), Democratic Republic of the Congo (+243), Djibouti (+253),
Eritrea (+291), Ethiopia (+251), Kenya (+254), Rwanda (+250), Somalia (+252),
South Sudan (+211), Tanzania (+255), and Uganda (+256).
