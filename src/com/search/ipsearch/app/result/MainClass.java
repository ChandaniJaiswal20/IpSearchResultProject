package com.search.ipsearch.app.result;

import org.ipas.proxy.IpasException;

/**
 * 
 * Issue: In many IP Offices, the IP_SEARCH_APPL_RESULT table that stores the results of a similarity search
 *  gets very big in size, thus increasing the overall size of the IPAS Database and slowing down the performance of IPAS.
 *  
 *  This class is used to instantiate utility that aim to reduce the size of this table by exporting 
 *  the relevant information content using generation of an Office document configured 
 *  on a Note action and moving it to the EDMS so that the document can be reviewed by examiners.
 * 
 * 
 * @author Chandani Jaiswal
 *
 */

public class MainClass {

	public static void main(String[] args) throws IpasException {
		PhoneticSearchResultsPersistenceUtility phoneticSearchResultsPersistenceUtility = PhoneticSearchResultsPersistenceUtility.getInstance();
        phoneticSearchResultsPersistenceUtility.processDbQuery();
        phoneticSearchResultsPersistenceUtility.stopUtility();
	}
}
