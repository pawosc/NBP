# Recruitment task
task description: https://www.smart4aviation.aero/java_developer/task.php

- program download and parse xml documents
- calculate average buying rates and standard deviation selling rates for specific date range from http://www.nbp.pl/kursy
- Jdk version used: Jdk11

## 1. Additional libraries used
- Lombok Project
- JAXB
- Junit5
- Apache Commons IO

## 2. How to open project

#### 2.1 Clone project
a) copy https://github.com/pawosc/nbp.git
b) File -> New -> Open project from version control > Git > Copy URL
#### 2.2 Import project to IntelliJ
Download project and open IntelliJ, select file, open, localize your folder, select NBP project and Ok. Select  auto import and press OK.

## 3 . Install additional software

#### 3 .1 Intelij Lombok Plugin
Install Lombok plugin: https://projectlombok.org/setup/intellij

**Now we must enable annotation processing**!!!
Click file, setting and go to Annotation Processors in Build, Execution, Deployment → Compiler. Next Enable annotation processing and click OK.

## 4. Run project
#### 4.1 From Intelij
Go to MainClass and press Ctrl + F10 or click the green arrow by main method signature.
#### 4.2 from Cmd
Go to the folder where the project is located ->right mouse button-> open command windows here -> type **mvn exec:java -Dexec.mainClass="pl.parser.nbp.MainClass"**
