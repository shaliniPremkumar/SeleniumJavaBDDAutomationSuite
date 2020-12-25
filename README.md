# Buildxact Application Testing Automation Suite
This repository contains a Java based Selenium BDD (Cucumber) automation suite (based on the Page Object Model), embedded with Extent reporting.
The suite contains 1 features cvering 3 different scenarios.

## Set up required
Java JDK >= 1.8
Java IDE (Intellij/Eclipse etc.)
Gradle 6.7

## Get the code
Git:
```
git clone https://github.com/shaliniPremkumar/Selenium-Java-BDD-AutomationFramework.git
cd cucumber-java-skeleton
```
## Use Gradle
Open a command window and run:
```
./gradlew test --rerun-tasks --info
```
This runs Cucumber features using Cucumber's JUnit runner. The ```@RunWith(Cucumber.class)``` annotation on the ```RunCucumberTest``` class tells JUnit to kick off Cucumber.

## Overriding options
The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc. When you use the JUnit runner, these options are generated from the ```@CucumberOptions``` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the ```cucumber.options``` system property. The general form is:

Using Maven:
```
mvn -Dcucumber.options="..." test
```
Using Gradle:
```
gradlew -Dcucumber.options="..." test
```
Let's look at some things you can do with cucumber.options. Try this:
```
-Dcucumber.options="--help"
```
That should list all the available options.

***IMPORTANT***

When you override options with ```-Dcucumber.options```, you will completely override whatever options are hard-coded in your ```@CucumberOptions``` or in the script calling ```cucumber.api.cli.Main```. There is one exception to this rule, and that is the ```--plugin``` option. This will not *override*, but *add* a plugin. The reason for this is to make it easier for 3rd party tools to automatically configure additional plugins by appending arguments to a ```cucumber.properties``` file.

**Running only the scenarios that failed in the previous run**
```
-Dcucumber.options="@target/rerun.txt"
```
This works as long as you have the ```rerun``` formatter enabled.

Specify a different formatter:
For example a JUnit formatter:
```
-Dcucumber.options="--plugin junit:target/cucumber-junit-report.xml"
```
### Input
- [X] .xlsx Excel
- [X] .json

### Output
- [X] .html Extent Reports
