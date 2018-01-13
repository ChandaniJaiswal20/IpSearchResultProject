					++ READ ME ++
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Requirement of the utility:
This utility (IpSearchAppResultPersistenceUtility) has been created for a requirement raised by an office to reduce the size of IPAS IP_SEARCH_APPL_RESULT table). In addition, the Office wants to extract the information in this table in the form of a report generated for the records that the examiner considers important or similar to the trade mark.

For most of the offices, the IP_SEARCH_APPL_RESULT table becomes too big.
This is increasing the database size of the offices, thus also slowing down the performance of IPAS Application. 
Hence, this utility aims to reduce the size of this table by exporting the information content by generating Office document configured on a Note action and moving them to the EDMS.

Scope of the utility:
The utility is generic for any office and helpful for those who would want to reduce the size of the IP_SEARCH_APPL_RESULT table in their database. The current utility is packaged with IPAS3.1.1c version of IpasApi.jar file.
The IpasApi.jar (placed in the lib folder of the utility) can be replaced with the IpasApi.jar file of a different versions of IPAS and can be used with those as well.
The utility has been tested to run with both Oracle 11G and MSSQL 2008 databases and the same can be configured using the ipSearchAppResult.properties file in this utility.

Contents of IP_SEARCH_APPL_RESULT table:
This table stores the records of trademark files/applications that the system has found in the database as the result of a similarity search (for a trademark)  
Once the similarity search is triggered, the results for each file are stored in this table. Normally you could expect between 300 and 1000 entries in the IP_SEARCH_APPL_RESULT table for every application file.


Usage of the records in this table:
During the proceedings, the trademark examiner should check the files and select those who have some similarity with the trademark been examined. In those cases the record is flagged with the IND_CONFIRMED_SIMILARITY='S'
This helps to print in the report, just the records that the examiner considers important (IND_CONFIRMED_SIMILARITY='S' for such records in the IP_SEARCH_APPL_RESULT table ) or similar to the trade mark that is being studied. The remaining records are not recorded in the generated report.



Solution to reduce the size of the  IP_SEARCH_APPL_RESULT table:
In order to reduce the size of the table, this utility is used to generate for each file an office document (configured with an action note) with the contents of this table. This can then be sent to the EDMS for final storage. Once is done, the records in this table should be cleared. The utility provides the option to remove only those  records that have IND_CONFIRMED_SIMILARITY!= 'S' in the IP_SEARCH_APPL_RESULT table
The generated office document(s) can then be used by the Examiner to examine the similarity search results.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Prerequisites:

Jdk(Java version 1.6.*) should be installed in the running system.
Glassfish domain running IPAS Application 

This release has been packaged with the IPAS API library for the release IPAS311c. 
To run this with any other version of IPAS Application, then please replace this jar with the corresponding IPAS API jar in 'lib' folder.

The following things needs to be configured in the run.bat file

Set JAVA_HOME in environment variables to point to the JDK directory.
Set GlassFish_home folder path location (For example: D:\glassfish3\glassfish)

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
ipSearchAppResultPersistenceUtility folder should contains following folders/files:

1) ipSearchAppResult.properties		(file)
2) IpSearchResult.jar			(The JAR containing the Java Classes which run the utility)
3) lib      				(folder)
4) log						(folder)
5) ReadMe.txt				(this file)
6) run.bat					(file)
7) log4j.properties			(properties file for log4j)

'ipSearchAppResult.properties' file contains properties of database and IPAS environment which needs to be modified accordingly before running this program.
The following things needs to be configured in the "ipSearchAppResult.properties" file

Set iiopHost with the IP Address of the Glassfish domain server running IPAS.
Set iiopPort with the port of the Glassfish domain server running IPAS.
Set databaseTool with value 'sql' for MSSQL database environment and 'oracle' for  Oracle Database environment of IPAS.
Set databaseHost with the IP Address of the Database of IPAS.
Set databasePort with the port of the Database of IPAS.
Set the Database connection details against databaseName, userName and password.
Set the filingDateFrom starting date. format - DD-MON-YYYY
Set the filingDateTo ending date. format - DD-MON-YYYY
Set the actionTypeId with the Office document action type id. (a numeric value indicating ActionTypeId - value can be had from IPAS Designer)
Set the fileSequence with the sequence of file. - eg. DZ
Set the fileType with the type of file. - (e.g. T for Trademarks)
Set the fileSeriesToProcessFor with the file series for which processing needs to be done. (a comma separated multi value like 2016, 2015  (indicating a range) or single series like 2016)
Set the keepSelectedMark with 'Yes' to delete only those records who have IND_CONFIRMED_SIMILARITY !='S' in the IP_SEARCH_APPL_RESULT table
'lib' folder contains all jar files that is required for this program.

'ReadMe' file contains the instructions to run this program.

'run.bat' file contains script to execute this program.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
HOW TO RUN PROGRAM?
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

1) Open 'ipSearchAppResult.properties' file and change properties of all attributes (as applicable) and save it.
2) Double click on 'run.bat' to start program.













