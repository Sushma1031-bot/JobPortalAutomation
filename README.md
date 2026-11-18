Job Portal Application Automation Testing
Project Overview
This project demonstrates automated functional testing of a web-based Job Portal application. It uses Selenium WebDriver to interact with the website, JUnit 5 to organize and verify test cases, and Maven to manage dependencies and run the test suite.
The project covers key user journeys such as opening the homepage, searching for jobs, registering an account, logging in, submitting a job application, and viewing the application in the application area.
Technologies Used
Programming language: Java
Browser automation: Selenium WebDriver
Testing framework: JUnit 5
Build and dependency tool: Apache Maven
Code editor: Visual Studio Code
Browser: Google Chrome
Website technologies: HTML, CSS, JavaScript
Main Features Tested
Homepage verification
Job search
User registration
User login
Job application submission
Application status / application-card display
Project Structure
JobPortalAutomation/
├── pom.xml
├── src/
│   └── test/
│       └── java/
│           └── tests/
│               └── JobPortalTest.java
└── target/
    └── surefire-reports/
The target directory and Surefire reports are generated after running the tests.
Prerequisites
Install the following software:
Java Development Kit (JDK)
Apache Maven
Visual Studio Code (or another Java editor)
Google Chrome
A local copy of the Job Portal website
Check that Java and Maven are available in the terminal:
java -version
mvn -version
Website Setup
Open the Job Portal website folder in Visual Studio Code.
Start the website using the Live Server extension, if that is how the local website is configured.
Confirm that the website opens at:
http://127.0.0.1:5500/index.html
Keep the local website server running while executing the Selenium tests.
If Live Server uses a different port, update websiteUrl in JobPortalTest.java to match the actual local website address.
Running the Automation Tests
Open the JobPortalAutomation folder in Visual Studio Code.
Open the integrated terminal in the project folder (the folder containing pom.xml).
Make sure the local Job Portal website is running.
Run all tests with:
mvn clean test
Maven compiles the test code, runs the JUnit tests, and creates test reports.
Test Reports
After the test run, open:
target/surefire-reports/
The folder normally contains a text summary and XML report for the test class. Open the .txt report to review the test count, failures, errors, and skipped tests.
Reported Test Run
During project development, you reported a successful Maven run with:
Tests run: 6
Build status: BUILD SUCCESS
This is the result reported for that run. Run mvn clean test again to generate a fresh report for the current version of the project.
Test Data and Safety
Use fictional demo names, email addresses, and phone numbers only. Do not put real passwords, personal information, or sensitive data into test accounts. If the demo website stores account information in browser local storage, treat that storage as test data only.
Troubleshooting
mvn is not recognized
Check that Maven is installed and its bin directory is included in the Windows PATH. Open a new terminal and run mvn -version.
Connection refused / website cannot be reached
Start the local website server and confirm that the URL in websiteUrl matches the address shown by Live Server.
Element not found or not interactable
Check that the element ID or CSS selector in the test matches the current website HTML. Make sure the correct page or form is visible before interacting with it. Prefer explicit waits with WebDriverWait rather than fixed delays.
Chrome or driver compatibility message
Check that Google Chrome and Selenium are up to date. A compatibility warning does not automatically mean that a test failed; check the final Maven test summary.
Conclusion
This project demonstrates how Selenium WebDriver, JUnit 5, and Maven can be combined to automate important functional checks for a Job Portal application. The test suite helps make repeated checks more consistent and provides execution results through Maven Surefire reports.
Future Enhancements
Add negative tests for invalid registration and login details.
Verify the exact application status text.
Add tests for location filters and other search criteria.
Run the suite on additional browsers.
Integrate automated tests into a continuous integration pipeline.
