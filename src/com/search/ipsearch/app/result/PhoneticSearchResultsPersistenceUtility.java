package com.search.ipsearch.app.result;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



import org.ipas.proxy.IpasException;

public class PhoneticSearchResultsPersistenceUtility {
    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }
    
    private static PhoneticSearchResultsPersistenceUtility uniqueInstance = null;
    private PropertyReaderConfiguration configuration = null;
    private final DBDataProcessor dataProcessor;
    private static final String LOG4JCONFIGFILE = "log4j.properties";
    private static Connection conn = null;
    private static Logger logger = Logger.getLogger(PhoneticSearchResultsPersistenceUtility.class);

    private PhoneticSearchResultsPersistenceUtility() {
        PropertyConfigurator.configure(LOG4JCONFIGFILE);
        logger.info("Initializing Utility...........................................!!!!!");
        logger.debug("################################################");
        logger.debug("=================================================");

        logger.debug("LOG4JCONFIGFILE:::" + LOG4JCONFIGFILE);
        configuration = new PropertyReaderConfiguration();
        configuration.loadAndCheckConfiguration();

        logger.debug("Obtaining Database Connection....");
        dataProcessor = new DBDataProcessor(configuration.databaseHost, configuration.databasePort,
                configuration.databaseTool, configuration.databaseName, configuration.userName, configuration.password, configuration.iiopPort);
        conn = dataProcessor.getConnection();
        logger.info("Initializing Completed...............................................!!!!!!");
        logger.debug("=================================================");
        logger.debug("################################################");
    }

    public static PhoneticSearchResultsPersistenceUtility getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PhoneticSearchResultsPersistenceUtility();
        }
        return uniqueInstance;
    }

    public PropertyReaderConfiguration getConfiguration() {
        return configuration;
    }

    public void processDbQuery() {
    	
    	 List<IpFile> ipSearchList = new ArrayList<IpFile>();
        try {
            conn.setAutoCommit(false);
           ipSearchList = dataProcessor.ipFileListByFileAndFilingDetails(configuration.fileSequence, configuration.fileType,
                    configuration.fileSeriesToProcessFor, configuration.filingDateFrom, configuration.filingDateTo);
            conn.commit();
            
            logger.debug("Number of Files found for the search creteria in the properties file   :: " + ipSearchList.size());
                      
        } catch (SQLException e) {
            logger.error("Error occurred", e);
            e.printStackTrace();
         //   logger.debug("Rollback records.......");
        } 
            
            Test testApp = new Test();
            
            testApp.setDbHostName(configuration.databaseHost);
            
            testApp.setIiopPort(configuration.iiopPort);
            
			List<IpFile> deletionIpList = new ArrayList<IpFile>();
			
		
			
			for (IpFile ipFile : ipSearchList) {
				if (ipSearchAppResultHasDataFor(ipFile)) {
					
					
					try {
						testApp.insertMarkAction(ipFile.getFileSequence(), ipFile.getFileType(), 
								ipFile.getFileSeries(), ipFile.getFileNumber(), configuration.actionTypeId,configuration.userId);
						
					} catch (IpasException e) {
						// TODO Auto-generated catch block
						logger.error("Exception occured while executing note action for file .."+ipFile.getMarkIdFormatted());
						e.printStackTrace();
						logger.debug("Processing next File...");
						
						continue;
						
				
					}

					dataProcessor.deleteRecordFromIpSearchResult(ipFile,checkConditionToKeepSelectedFile());
					deletionIpList.add(ipFile);
				}
			}
            
           logger.info("--------------Total number of files in IP_FILE for which records in IP_SEARCH_APPL_RESULT table have been deleted -----------" + deletionIpList.size());
           System.out.println("-------Total number of files in IP_FILE for which records in IP_SEARCH_APPL_RESULT table have been deleted -----------" + deletionIpList.size());
           

     
    }

	private String checkConditionToKeepSelectedFile() {
		String queryCondition	=	" ";
		if(configuration.keepSelectedMark.equalsIgnoreCase("YES")||configuration.keepSelectedMark.equalsIgnoreCase("Y")){
			
			queryCondition	=	" AND IND_CONFIRMED_SIMILARITY !='S' ";
			
			 logger.info("-----Going to delete those record having IND_CONFIRMED_SIMILARITY !='S' ");
			
		}
		
		else{
			
			 logger.info("-----Going to delete all records--");
		}
		return queryCondition; 
		
	}

	private boolean ipSearchAppResultHasDataFor(IpFile ipFile) {
		
		return dataProcessor.checkIpSearchAppResultHasDataFor(ipFile);
	}

	/*
	 * This method is used for stop utility.
	 */
	public void stopUtility() {
		if (dataProcessor.getConnection() != null) {
			logger.debug("Closing database connection..........!!");
			dataProcessor.closeConnection();
			logger.debug("Database connection closed..........!!");
		}
		logger.info("Processing Completed.......................!!!");
		logger.info("=================================================");
		logger.info("Please check processed details in log file generated in log folder");
		logger.info("********************************************************");
	}
}
