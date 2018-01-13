package com.search.ipsearch.app.result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class DBDataProcessor {
	
	private String databaseHost	= null;
	private String databasePort	=	null;
	private String databaseTool	=	null;
	private String databaseName	=	null;
	private	String userName		=	null;
	private	String password		=	null;
    private String iiopPort     =   null;
	private	Connection connection	=	null;
	
	public static Logger logger	=	Logger.getLogger(DBDataProcessor.class);
	
	static int count	=	0;

	public DBDataProcessor(String databaseHost, String databasePort,
                           String databaseTool, String databaseName, String userName, String password, String iiopPort){
		 this.databaseHost	=   databaseHost;
		 this.databasePort	=	databasePort;
		 this.databaseTool	=	databaseTool;
		 this.databaseName	=	databaseName;
		 this.userName		=	userName;
		 this.password		=	password;
         this.iiopPort      =   iiopPort;
         init();
    }
	
	public void init(){
		if(connection==null)
			configureConnection();
	}
	
	public Connection getConnection(){
		return connection;
	}

	public void closeConnection(){
		 if(connection!=null){
		 try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	}

	public  void 	configureConnection() {
		//Connection conn = null;
		if( databaseTool.equalsIgnoreCase("oracle") ){
			logger.info("Database Type is Oracle....");
		//("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+databaseName,userName,password  );
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection	=	DriverManager.getConnection("jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":"+ databaseName, userName, password  );
		} catch (ClassNotFoundException e) {
			logger.error("Error Occurred while loading OracleDriver class ", e);
			//e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Error Occurred while obtaining connection ", e);
		//	e.printStackTrace();
		}
		}
		else if(databaseTool.equalsIgnoreCase("sql")){
			logger.info("Database Type is MSSQL....");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection	=	DriverManager.getConnection( "jdbc:sqlserver://"+databaseHost+ ":" + databasePort +";" +
			    		  "databaseName="+databaseName+";"+"user="+userName+";"+"password="+password);
			} catch (ClassNotFoundException e) {
				logger.error("Error Occurred while loading SQLDriver class ", e);
			//	e.printStackTrace();
			} catch (SQLException e) {
				logger.error("Error Occurred While obtaining  Connection: ", e);
			//	e.printStackTrace();
			}
		}
	}

    /*
     * This method is used to search the file in between filing date from and filing date to. 
     */
	public List<IpFile> ipFileListByFileAndFilingDetails(String fileSequence, String fileType, String fileSeriesToProcessFor, final String filingDateFrom, final String filingDateTo) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query =	null;
		List<IpFile> ipFileList = new ArrayList<IpFile>();
        if(isNotNullOrAValidDate(filingDateFrom) && isNotNullOrAValidDate(filingDateTo)){

        }
        
        if(databaseTool.equalsIgnoreCase("sql")){
        	
        	query	=	"SELECT FILE_SEQ, FILE_SER, FILE_TYP, FILE_NBR FROM IP_FILE WHERE FILE_SEQ = '" + fileSequence +"' AND FILE_TYP = '" + fileType + "' AND FILE_SER IN ("+ fileSeriesToProcessFor +") " +
                    "AND CONVERT(DATE,FILING_DATE)   between '" + filingDateFrom + "' AND '" + filingDateTo + "' ";
        }
        else{
        	
        	 query = "SELECT FILE_SEQ, FILE_SER, FILE_TYP, FILE_NBR FROM IP_FILE WHERE FILE_SEQ = '" + fileSequence +"' AND FILE_TYP = '" + fileType + "' AND FILE_SER IN ("+ fileSeriesToProcessFor +") " +
                     "AND TRUNC(FILING_DATE) between TO_DATE('" + filingDateFrom + "', 'DD-MM-YYYY') AND TO_DATE('" + filingDateTo + "', 'DD-MM-YYYY')";
        }
		
		try {
			init();
			ps = connection.prepareStatement(query);
         /*   ps.setString(1, fileSequence);
            ps.setString(2, fileType);
            String[] fileSeriesArray = fileSeriesToProcessFor.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fileSeriesArray.length; i++) {
                String s = fileSeriesArray[i];
                sb.append("'").append(s.trim()).append("'");
                if(i != fileSeriesArray.length-1){
                    sb.append(",");
                }
            }
            ps.setString(3, sb.toString());
			ps.setString(4, filingDateFrom);
			ps.setString(5, filingDateTo);*/
            rs = ps.executeQuery();
			while (rs.next()) {
				IpFile ipFile = new IpFile();
				ipFile.setFileNumber(rs.getInt("FILE_NBR"));
				ipFile.setFileSeries(rs.getInt("FILE_SER"));
				ipFile.setFileSequence(rs.getString("FILE_SEQ"));
				ipFile.setFileType(rs.getString("FILE_TYP"));
				ipFileList.add(ipFile);
			}
			logger.debug("IP_FILE List (By Filing Date) size : " + ipFileList.size());
		} catch (SQLException e) {
			logger.error("Error Occurred while Reading IP_FILE records.", e);
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ipFileList;
	}

    private boolean isNotNullOrAValidDate(String filingDateFrom) {
        return false;
    }

    public boolean executeDbQuery(String theSqlQuery){
        ResultSet rs = null;
        Statement stmt = null;
        try {
            init();
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.executeQuery(theSqlQuery);
            rs = stmt.getResultSet();
            return rs!= null && rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    /**
     * This method is used for check existing ipSearch Result has data.
     * @return
     */
    public boolean checkIpSearchAppResultHasDataFor(IpFile ipFile) {

		ResultSet rs = null;
		PreparedStatement ps = null;
        boolean flag = false;
		String query = "SELECT FILE_SEQ, FILE_SER, FILE_TYP, FILE_NBR FROM IP_SEARCH_APPL_RESULT "
				+ "WHERE FILE_SEQ = '" + ipFile.getFileSequence() +"' AND FILE_TYP = '" + ipFile.getFileType() + 
				"' AND FILE_SER = "+ ipFile.getFileSeries() +"  AND FILE_NBR = "+ ipFile.getFileNumber() +" AND IND_CONFIRMED_SIMILARITY='S' ";
		try {
			init();
			ps = connection.prepareStatement(query);
       
            rs = ps.executeQuery();
            
			while (rs.next()) {
                flag = true;
				return flag;
			}
		} catch (SQLException e) {
			logger.error("Error Occurred while Reading IP_FILE records.", e);
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
    }
    
    
    /**
     * This method is used for check existing ipSearch Result has data.
     * @return
     */
    public boolean deleteRecordFromIpSearchResult(IpFile ipFile,String queryCondition) {

		ResultSet rs = null;
		PreparedStatement ps = null;
        boolean flag = false;
        String query =null;
        
               query = "DELETE FROM IP_SEARCH_APPL_RESULT "
				+ "WHERE FILE_SEQ = '" + ipFile.getFileSequence() +"' AND FILE_TYP = '" + ipFile.getFileType() + 
				"' AND FILE_SER = "+ ipFile.getFileSeries() +"  AND FILE_NBR = "+ ipFile.getFileNumber() +queryCondition ;
		 
		
        
		try {
			init();
			ps = connection.prepareStatement(query);
			  // execute the ps
			flag = ps.execute();
			/** markIdFormatted = ipFile.getFileSequence() + "/" +
					ipFile.getFileType() + "/" +
	                ipFile.getFileSeries() + "/" +
	                ipFile.getFileNumber();
	                
	                **/
			logger.info("-----Successfully deleted  IP_SEARCH_APPL_RESULT record  for file number--------- " + ipFile.getMarkIdFormatted());
			return flag;
         
		} catch (SQLException e) {
			logger.error("Error Occurred while deleting  IP_FILE records for file number---------"+ipFile.getMarkIdFormatted()+"---", e);
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
    }
}
