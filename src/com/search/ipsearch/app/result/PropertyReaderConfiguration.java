package com.search.ipsearch.app.result;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyReaderConfiguration {
    public String databaseHost;
    public String databasePort;
    public String databaseTool;
    public String databaseName;
    public String userName;
    public String password;
    public String filingDateFrom;
    public String filingDateTo;
    public String iiopPort;
    public String actionTypeId;
    public String fileSequence;
    public String fileType;
    public String fileSeriesToProcessFor;
    public String userId;
    public String keepSelectedMark;

    public static Logger logger = Logger.getLogger(PropertyReaderConfiguration.class);
    private Properties searchAppResultProperties = new Properties();


    public void loadAndCheckConfiguration() {
        logger.debug("Loading configuration file.....");
        FileReader fileReader;
        try {
            fileReader = new FileReader("ipSearchAppResult.properties");
            searchAppResultProperties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkValuesOfConfigFile();
        userId = searchAppResultProperties.getProperty("userId");
        databaseHost = searchAppResultProperties.getProperty("databaseHost");
        databasePort = searchAppResultProperties.getProperty("databasePort");
        databaseTool = searchAppResultProperties.getProperty("databaseTool");
        databaseName = searchAppResultProperties.getProperty("databaseName");
        userName = searchAppResultProperties.getProperty("userName");
        password = searchAppResultProperties.getProperty("password");
        filingDateFrom = searchAppResultProperties.getProperty("filingDateFrom");
        filingDateTo = searchAppResultProperties.getProperty("filingDateTo");
        iiopPort = searchAppResultProperties.getProperty("iiopPort");
        actionTypeId = searchAppResultProperties.getProperty("actionTypeId");
        fileSequence = searchAppResultProperties.getProperty("fileSequence");
        fileType = searchAppResultProperties.getProperty("fileType");
        fileSeriesToProcessFor = searchAppResultProperties.getProperty("fileSeriesToProcessFor");
        keepSelectedMark = searchAppResultProperties.getProperty("keepSelectedMark");
    }


    private void checkValuesOfConfigFile() {
        Enumeration<String> propertyKeyNames = (Enumeration<String>) searchAppResultProperties.propertyNames();
        while (propertyKeyNames.hasMoreElements()) {
            String propertyKey = propertyKeyNames.nextElement();
            String propertyValue = searchAppResultProperties.getProperty(propertyKey);
            if (propertyValue == null || propertyValue.isEmpty()) {
                throw new MissingConfigurationException("Please specify a value for property : " + propertyKey + " in configuration file ipSearchAppResult.properties");
            }
            if ("databaseTool".equals(propertyKey)) {
                propertyValue = searchAppResultProperties.getProperty(propertyKey);
                if (propertyValue != null && !(propertyValue.equals("sql") || propertyValue.equals("oracle"))) {
                    logger.error("Expected either 'sql' or 'oracle' as value for property " + propertyKey + " in " +
                            "configuration file ipSearchAppResult.properties but got " + propertyValue + " instead. App will exit now.");
                    throw new MissingConfigurationException("Expected either 'sql' or 'oracle' as value for property " + propertyKey + " in " +
                            "configuration file ipSearchAppResult.properties but got " + propertyValue + " instead. App will exit now.");
                }
            }
            System.out.println("Property : " + propertyKey + " , Value : " + propertyValue);
        }
    }

}
