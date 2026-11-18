
### README – Job Portal Application Automation Testing

### 1\. Project Overview

The Job Portal Application Automation Testing project focuses on automating the testing of a web-based job portal application using Selenium WebDriver, Java, JUnit 5, and Maven.

The application, named CareerConnect, allows users to register, log in, search for jobs, apply for jobs, and check their application status.

The main objective is to verify that the website's important features work correctly through automated test cases.

### 2\. Technologies Used

* Java 21 – Programming language used to write automation scripts.

* Selenium WebDriver 4.27.0 – Used to automate browser interactions.

* JUnit 5 – Used to create and execute test cases.

* Maven 3.10.1 – Used to manage project dependencies and run tests.

* Visual Studio Code – Code editor used for development.

* Google Chrome – Browser used for automation testing.

### 3\. Features Tested

The following six features are included in the project testing:

* Homepage Verification: Checks whether the job portal website opens successfully.

* Job Search: Verifies whether users can search and filter available jobs.

* User Registration: Tests whether a new user can register successfully.

* User Login: Checks whether registered users can log in.

* Job Application: Verifies whether users can submit job applications.

* Application Status: Checks whether users can view their application status.

### 4\. Project Structure

```text
JobPortalAutomation/
│
├── README.md
├── pom.xml
│
├── src/
│   └── test/
│       └── java/
│           └── tests/
│               └── JobPortalTest.java
│
└── target/
    └── surefire-reports/
```

### 5\. Prerequisites

Before running the project, install the following:

* Java JDK 21

* Apache Maven 3.10.1

* Visual Studio Code

* Google Chrome

* Selenium WebDriver dependency

* JUnit 5 dependency

### 6\. How to Run the Project

Step 1: Open the Job Portal website folder in VS Code.

Step 2: Start the website using the Live Server extension.

Step 3: Make sure the website is running at:

```text
http://127.0.0.1:5500/index.html
```

Step 4: Open the `JobPortalAutomation` folder in VS Code.

Step 5: Open the terminal in VS Code.

Step 6: Execute the following command:

```bash
mvn clean test
```

Step 7: Maven will execute the automation test cases.

Step 8: Check the terminal for the test execution results.

### 7\. Test Execution Results

The project was successfully executed using Maven.

Reported execution results:

* Total test cases: 6

* Build status: BUILD SUCCESS

* Test execution: Successful, according to the reported run.

The test reports are generated inside:

```text
target/surefire-reports/
```

Important report files:

```text
TEST-tests.JobPortalTest.xml
tests.JobPortalTest.txt
```

### 8\. Advantages of the Project

* Reduces manual testing effort.

* Helps identify errors in website functionality.

* Improves testing accuracy and consistency.

* Allows repeated execution of test cases.

* Saves time during software testing.

### 9\. Future Enhancements

* Add more automated test cases.

* Include cross-browser testing.

* Generate detailed HTML test reports.

* Integrate the project with Jenkins.

* Add database testing.

* Improve security and user authentication.

### 10\. Conclusion

The Job Portal Application Automation Testing project demonstrates how Selenium WebDriver, Java, JUnit 5, and Maven can be used to automate the testing of a web application.

The project successfully covers important job portal functionalities, including registration, login, job searching, job application, and application status verification.

This project provides practical experience in software testing and automation testing concepts.
