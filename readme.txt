Given assignment is implmented by using Page Object Model with Page Factory design Pattern.

All Page Objects are grouped under package com.amazon.pages
All test cases are grouped under package com.amazon.tests

Tested with Firefox 56 (32 bit) version and Chrome 65(32 version)
Selenium Webdriver version is 3.11

For the test cases, test data is taken from CatalogBook.json which is implemented in JSON format. 

CatalogBook.json
-----------------
CatalogBook.json contains book details information as below

Book title,Book author,Book ISBN 13 digit,Book ISBN 10 digit,Book edition information are taken as String(key,value pairs) 
Kindle Details,Paperback Details and Customer Rating information are implemented as JSON objects
Books offers which inlcude new books, rental books, Used books are implemented as JSON Arrays which internally are implmented as JSON Objects.

How to Run:
-------------
Download code from git and add libraries present in lib folder and testng reference libraries in eclipse project.
Configure Testng suite datacatalog_suite.xml present in suite folder and Run.
For now I have kept default testng report generated as reporting structure.