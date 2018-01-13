package com.search.ipsearch.app.result;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.search.ipsearch.app.result.PhoneticSearchResultsPersistenceUtility;
import com.search.ipsearch.app.result.PropertyReaderConfiguration;

import org.apache.log4j.Logger;
import org.ipas.commons.CAckMessageNbrList;
import org.ipas.commons.CAckMessageTextList;
import org.ipas.commons.CAction;
import org.ipas.commons.CActionList;
import org.ipas.commons.CActionTypeId;
import org.ipas.commons.CAgent;
import org.ipas.commons.CAgentId;
import org.ipas.commons.CCriteriaFile;
import org.ipas.commons.CCriteriaJournal;
import org.ipas.commons.CCriteriaMark;
import org.ipas.commons.CCriteriaNiceClass;
import org.ipas.commons.CCriteriaNiceClassList;
import org.ipas.commons.CCriteriaPatent;
import org.ipas.commons.CCriteriaProcess;
import org.ipas.commons.CCriteriaUserdoc;
import org.ipas.commons.CDailyLog;
import org.ipas.commons.CDailyLogId;
import org.ipas.commons.CDeliveryId;
import org.ipas.commons.CDocumentId;
import org.ipas.commons.CDocumentIdList;
import org.ipas.commons.CDocumentImage;
import org.ipas.commons.CDocumentSeqId;
import org.ipas.commons.CFile;
import org.ipas.commons.CFileIdList;
import org.ipas.commons.CFileSummary;
import org.ipas.commons.CFileSummaryList;
import org.ipas.commons.CJournal;
import org.ipas.commons.CJournalId;
import org.ipas.commons.CJournalList;
import org.ipas.commons.CMark;
import org.ipas.commons.CMergeData;
import org.ipas.commons.CMergeDataList;
import org.ipas.commons.CNiceClass;
import org.ipas.commons.CNiceClassList;
import org.ipas.commons.COfficeSectionId;
import org.ipas.commons.COfficedocId;
import org.ipas.commons.COption;
import org.ipas.commons.COptionField;
import org.ipas.commons.COptionFieldList;
import org.ipas.commons.COptionList;
import org.ipas.commons.COutputField;
import org.ipas.commons.COutputFieldCode;
import org.ipas.commons.COutputFieldCodeList;
import org.ipas.commons.COutputFieldList;
import org.ipas.commons.COwner;
import org.ipas.commons.CParisPriority;
import org.ipas.commons.CPatent;
import org.ipas.commons.CPayment;
import org.ipas.commons.CPerson;
import org.ipas.commons.CProcess;
import org.ipas.commons.CProcessId;
import org.ipas.commons.CProcessSummary;
import org.ipas.commons.CProcessSummaryList;
import org.ipas.commons.CReceipt;
import org.ipas.commons.CRelationship;
import org.ipas.commons.CRepresentative;
import org.ipas.commons.CSqlColumn;
import org.ipas.commons.CSqlRow;
import org.ipas.commons.CSqlRowList;
import org.ipas.commons.CStatusId;
import org.ipas.commons.CUserId;
import org.ipas.commons.CUserdoc;
import org.ipas.commons.CUserdocList;
import org.ipas.commons.CUserdocSummaryList;
import org.ipas.commons.CommonsProxyFactory;
import org.ipas.commons.IAgent;
import org.ipas.commons.IDailyLog;
import org.ipas.commons.IDelivery;
import org.ipas.commons.IFile;
import org.ipas.commons.IJournal;
import org.ipas.commons.IMark;
import org.ipas.commons.IPatent;
import org.ipas.commons.IProcess;
import org.ipas.commons.ISql;
import org.ipas.commons.IOutputField;
import org.ipas.commons.CFileId;
import org.ipas.commons.IUserdoc;
import org.ipas.proxy.IpasDateTime;
import org.ipas.proxy.IpasException;
import org.ipas.proxy.IpasInteger;

// See FAQ about using EJB3 from GlassFish
// https://glassfish.dev.java.net/javaee5/ejb/EJB_FAQ.html


import org.apache.log4j.Logger;  

public class Test {
	// IPAS proxy factory 
	public static CommonsProxyFactory commonsProxyFactory;

    private String dbHostName;
    private String iiopPort;

    public String getDbHostName() {
        return dbHostName;
    }

    public void setDbHostName(String dbHostName) {
        this.dbHostName = dbHostName;
    }

    public String getIiopPort() {
        return iiopPort;
    }

    public void setIiopPort(String iiopPort) {
        this.iiopPort = iiopPort;
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private static Logger logger = Logger.getLogger(Test.class);

    
    
    public static void main(String[] args) {

		// create proxy factory based on the IIOP service address and port 
	//	commonsProxyFactory = new CommonsProxyFactory("localhost:3700");

		// ******************************************************************
		// add an extra payment for a prior payment, using addExtraPayment()
		// ******************************************************************
	
		// nbr of the receipt which is being supplemented 
		/*IpasInteger supplementedReceiptNbr = new IpasInteger(993456789012345.0);
		// supplementary payment
		CPayment extraPayment = new CPayment();
		extraPayment.setCurrencyType("R$");
		extraPayment.setReceiptType("GRU");
		extraPayment.setReceiptDate(new IpasDateTime("10/09/2011"));
		extraPayment.setReceiptNbr(new IpasInteger(773456789012345.0));
		extraPayment.setReceiptAmount(new Double(1234));

		try {
			addExtraPayment(supplementedReceiptNbr, extraPayment);
		} catch (IpasException e) {
			// insert failed
			System.out.println("addExtraPayment error:" + e.getMessage()+"\r\n");
		}*/

		// ******************************************************************
		// create/open a daily log book, using createOpenDailyLog() 
		// ******************************************************************
/*
		// get data from external sources (replace constants by actual data)
		// code for the document origin of the daily log
		String docOrigin = "SP";
		// date of the daily log
		IpasDateTime dailyLogDate = new IpasDateTime("18/09/2011");

		try {
			createOpenDailyLog(docOrigin, dailyLogDate);
		} catch (IpasException e) {
			// insert failed
			System.out.println("createOpenDailyLog error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// receive a new file in an open daily log book
		// ****************************************************
/*
		// code for the document origin of the daily log
		String docOrigin = "SP";
		// reception date and time
		IpasDateTime specialReceptionDate = new IpasDateTime("18/09/2011 08:04:23");
		// code of the application type
		String applicationType = "M";
		// code of the application subtype
		String applicationSubtype = "1";
		// id of the file being received
		CFileId fileId = new CFileId();
		fileId.setFileSeq("MA");
		fileId.setFileType("M");
		fileId.setFileSeries(new IpasInteger(1));
		fileId.setFileNbr(new IpasInteger(100));
		// id of the document being received
		CDocumentId documentId = new CDocumentId();
		documentId.setDocLog("E");
		documentId.setDocOrigin("SP");
		documentId.setDocSeries(new IpasInteger(2011));
		documentId.setDocNbr(new IpasInteger(98765432101234.0));
		// payment receipt
		CReceipt receipt = new CReceipt();
		receipt.setCurrencyType("R$");
		receipt.setReceiptType("GRU");
		receipt.setReceiptDate(new IpasDateTime("10/09/2011"));
		receipt.setReceiptNbr(new IpasInteger(123456789012345.0));
		receipt.setDReceiptAmount(new Double(1234));

		// relationship to a prior file
		CRelationship relationship = new CRelationship(); 
		// notes
		String notes = "Notas";

		// get API objects implementing interfaces
		IFile iFile = commonsProxyFactory.getIFile();

		try {
			iFile.mReceive(docOrigin, applicationType, applicationSubtype, relationship, 
					specialReceptionDate, receipt, fileId, documentId, notes);
		} catch (IpasException e) {
			System.out.println("iFile.mReceive() error:" + e.getMessage()+"\r\n");
		}
*/
		// **************************************************************
		// receive a new user document in an open daily log book
		// **************************************************************
/*		
		// code for the document origin of the daily log
		String docOrigin = "SP";
		// reception date and time
		IpasDateTime specialReceptionDate = new IpasDateTime("18/09/2011 08:04:23");
		// code of the userdoc type
		String userdocType = "3491";
		// id of the document being received
		CDocumentId documentId = new CDocumentId();
		documentId.setDocLog("E");
		documentId.setDocOrigin("SP");
		documentId.setDocSeries(new IpasInteger(2011));
		documentId.setDocNbr(new IpasInteger(98765432101235.0));
		// sequence id of the document being received (optional)
		CDocumentSeqId documentSeqId = new CDocumentSeqId();

		// list of ids for affected filed
		CFileIdList affectedFileIdList = new CFileIdList();
		CFileId affectedFileId= new CFileId();
		affectedFileId.setFileSeq("MA");
		affectedFileId.setFileType("M");
		affectedFileId.setFileSeries(new IpasInteger(1));
		affectedFileId.setFileNbr(new IpasInteger(100));
		// indicator that all  the affected files have not been captured yet
		Boolean indNotAllAffectedFilesCapturedYet = true;
		// id of the affected userdoc
		CDocumentId affectedDocumentId = new CDocumentId();
		// id of the office doc which is being responded by the user doc
		COfficedocId respondedOfficedocId = new COfficedocId();

		// id of the office section to which the user doc must be physically sent
		COfficeSectionId targetOfficeSectionId = new COfficeSectionId();
		// payment receipt
		CReceipt receipt = new CReceipt();
		receipt.setCurrencyType("R$");
		receipt.setReceiptType("GRU");
		receipt.setReceiptDate(new IpasDateTime("10/09/2011"));
		receipt.setReceiptNbr(new IpasInteger(345456789012345.0));
		receipt.setDReceiptAmount(new Double(1234));
		// nbr of agent who filed the user doc
		IpasInteger agentNbr = new IpasInteger(); 
		// notes
		String notes = "Notas";

		// get API objects implementing interfaces
		IUserdoc iUserdoc= commonsProxyFactory.getIUserdoc();

		try {
			iUserdoc.mReceive(docOrigin, userdocType, affectedFileIdList, indNotAllAffectedFilesCapturedYet, 
					respondedOfficedocId, targetOfficeSectionId, 
					specialReceptionDate, receipt, agentNbr, notes, 
					affectedDocumentId, documentSeqId, documentId);
		} catch (IpasException e) {
			System.out.println("iUserdoc.mReceive() error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// close a daily log book
		// ****************************************************
/*		
		// get data from external sources (replace constants by actual data)
		// code for the document origin of the daily log
		String docOrigin = "SP";
		// date of the daily log
		IpasDateTime dailyLogDate = new IpasDateTime("18/09/2011");

		// get API objects implementing interfaces
		IDailyLog iDailyLog = commonsProxyFactory.getIDailyLog();

		// declare objects
		CDailyLog dailyLog;

		// build id of daily log to be closed
		CDailyLogId dailyLogId = new CDailyLogId();
		dailyLogId.setDocLog("E");					// code for "input" daily log
		dailyLogId.setDocOrigin(docOrigin);			// code for document origin
		dailyLogId.setDailyLogDate(dailyLogDate);	// date of daily log

		// close the daily log book
		try {
			iDailyLog.mClose(dailyLogId);
		} catch (IpasException e) {
			System.out.println("iDailyLog.mClose() error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// mark search (phonetic)
		// ****************************************************
/*		
		Scanner in = new Scanner(System.in);

		// get API objects implementing interfaces
		IMark iMark = commonsProxyFactory.getIMark();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		CMark mark = new CMark();

		// get mark name and class to be searched
		String markNameSearch = new String();
		IpasInteger niceClassSearch = new IpasInteger();

		System.out.println("Enter mark name to be searched for:");
		markNameSearch = in.nextLine();

		System.out.println("Enter Nice class:");
		niceClassSearch = new IpasInteger(in.nextInt());

		try {
			// build criteriaNiceClassList with the Nice class to be used as search criteria
			CCriteriaNiceClass criteriaNiceClass = new CCriteriaNiceClass();
			criteriaNiceClass.setNiceClassNbr(niceClassSearch);
			CCriteriaNiceClassList criteriaNiceClassList = new CCriteriaNiceClassList();   
			criteriaNiceClassList.add(criteriaNiceClass);

			// build criteriaMark with all the selection criteria
			CCriteriaMark criteriaMark = new CCriteriaMark();
			criteriaMark.getCriteriaSignData().setMarkNameSoundsLike(markNameSearch);
			criteriaMark.getCriteriaSignData().setMinSimilarityPercentage(new IpasInteger(40));
			criteriaMark.getCriteriaProtectionData().setCriteriaNiceClassList(criteriaNiceClassList);

			// get list of marks fulfilling the criteria
			CFileSummaryList fileSummaryList = iMark.mGetList(criteriaMark);

			// browse results and print each mark name and other data
			for (CFileSummary fileSummary : fileSummaryList ) {
				// read details of mark
				mark = iMark.mRead(fileSummary.getFileId(), false, false);
				// print mark data
				System.out.println("Mark name:" + mark.getSignData().getMarkName());
				System.out.println("Filing date:" + mark.getFile().getFilingData().getFilingDate().toString());
				// print list of owners
				for (COwner owner : mark.getFile().getOwnershipData().getOwnerList()) {
					System.out.println("Owner:" + owner.getPerson().getPersonName());
				}
				// print list of Nice classes
				for (CNiceClass niceClass : mark.getProtectionData().getNiceClassList()) {
					System.out.println("Nice class:" + niceClass.getNiceClassNbr().toString() + " " + niceClass.getNiceClassDescription());
				}
			}
		} catch (IpasException e) {
			System.out.println("iMark.mGetList() error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// mark read
		// ****************************************************
/*		
		Scanner in = new Scanner(System.in);

		// get API objects implementing interfaces
		IMark iMark = commonsProxyFactory.getIMark();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		CMark mark = new CMark();

		// build mark key
		CFileId fileId = new CFileId();
		System.out.println("Enter file seq:");
		fileId.setFileSeq(in.nextLine());

		System.out.println("Enter file type:");
		fileId.setFileType(in.nextLine());

		System.out.println("Enter file series:");
		fileId.setFileSeries(new IpasInteger(in.nextInt()));

		System.out.println("Enter file nbr:");
		fileId.setFileNbr(new IpasInteger(in.nextInt()));

		try {
			mark = iMark.mRead(fileId, false, false);

			System.out.println("Mark name:" + mark.getSignData().getMarkName());
			System.out.println("Filing date:" + mark.getFile().getFilingData().getFilingDate().toString());
			for (COwner owner : mark.getFile().getOwnershipData().getOwnerList()) {
				System.out.println("Owner:" + owner.getPerson().getPersonName());
			}
			for (CNiceClass niceClass : mark.getProtectionData().getNiceClassList()) {
				System.out.println("Nice class:" + niceClass.getNiceClassNbr().toString() + " " + niceClass.getNiceClassDescription());
			}
		} catch (IpasException e) {
			System.out.println("iMark.mRead() error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// mark insert
		// ****************************************************
/*
		// get API objects implementing interfaces
		IMark iMark = commonsProxyFactory.getIMark();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		CMark mark = new CMark();

		mark = new CMark();

		// load file id (key to the file)
		mark.getFile().getFileId().setFileNbr(new IpasInteger(12));
		mark.getFile().getFileId().setFileSeries(new IpasInteger(1));
		mark.getFile().getFileId().setFileType("M");
		mark.getFile().getFileId().setFileSeq("BR");

		// load application type/subtype
		mark.getFile().getFilingData().setApplicationType("M");
		mark.getFile().getFilingData().setApplicationSubtype("1");

		// load filing date and law
		mark.getFile().getFilingData().setFilingDate(new IpasDateTime("02/01/2005 09:34:54"));
		mark.getFile().getFilingData().setLawCode(new IpasInteger(1));

		// load sign data
		mark.getSignData().setSignType("N");
		mark.getSignData().setMarkName("ALO BRASIL 2");

		// load owner
		COwner owner = new COwner();
		owner.getPerson().setPersonName("Pedro Goncalves");
		owner.getPerson().setNationalityCountryCode("US");
		owner.getPerson().setResidenceCountryCode("US");
		owner.getPerson().setAddressStreet("Rua Alegria 1234");
		mark.getFile().getOwnershipData().getOwnerList().add(owner);

		owner = new COwner();
		owner.getPerson().setPersonName("Maria Bonita");
		owner.getPerson().setNationalityCountryCode("FR");
		owner.getPerson().setResidenceCountryCode("FR");
		owner.getPerson().setAddressStreet("Champs Elisees 4455");
		mark.getFile().getOwnershipData().getOwnerList().add(owner);

		// load Nice class
		CNiceClass niceClass = new CNiceClass();
		niceClass.setNiceClassNbr(new IpasInteger(4));
		niceClass.setNiceClassDescription("Detalhe de classe 4 bla bla bla");
		mark.getProtectionData().getNiceClassList().add(niceClass);

		niceClass = new CNiceClass();
		niceClass.setNiceClassNbr(new IpasInteger(8));
		niceClass.setNiceClassDescription("Detalhe de classe 8  ble ble ble");
		mark.getProtectionData().getNiceClassList().add(niceClass);

		CPayment payment = new CPayment();
		payment.setCurrencyType("R$");
		payment.setReceiptAmount(123456.00);
		payment.setReceiptDate(new IpasDateTime("02/01/2005 08:00:00"));
		payment.setReceiptNbr(new IpasInteger(new Double(123456789012345.0)));
		payment.setReceiptType("GRU");
		mark.getFile().getFilingData().getPaymentList().add(payment);

		// insert
		try {
			iMark.mInsert(mark);
		} catch (IpasException e) {
			System.out.println("iMark.mInsert error");
			System.out.println("Error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// mark update
		// ****************************************************
/*
		// get API objects implementing interfaces
		IMark iMark = commonsProxyFactory.getIMark();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		CMark mark = new CMark();

		// build mark key
		CFileId fileId = new CFileId();
		fileId.setFileSeq("-");
		fileId.setFileType("M");
		fileId.setFileSeries(new IpasInteger(1));
		fileId.setFileNbr(new IpasInteger(20000));

		try {
			mark = iMark.mRead(fileId, false, false);

			// update receipt nbr
			CPayment payment = new CPayment();
			payment.setCurrencyType("NAC");
			payment.setReceiptAmount(12345.67);
			payment.setReceiptDate(new IpasDateTime("28/12/2010"));
			double nbr = 123456789012345.00;
			IpasInteger receiptNbr = new IpasInteger(123456789012345.0);
			payment.setReceiptNbr(receiptNbr);
			payment.setReceiptType("IPO");

			mark.getFile().getFilingData().getPaymentList().add(payment);
			iMark.mUpdate(mark);
		} catch (IpasException e) {
			System.out.println("iMark.mUddate error:" + e.getMessage()+"\r\n");
		}
*/

		// ****************************************************
		// insert mark action, using insertMarkAction()
		// ****************************************************

		// id of the mark



		// ****************************************************
		// insert userdoc action, using insertUserdocAction()
		// ****************************************************
/*
		// id of the userdoc
		CDocumentId documentId = new CDocumentId();
		documentId.setDocLog("E");
		documentId.setDocOrigin("SP");
		documentId.setDocSeries(new IpasInteger(2011));
		documentId.setDocNbr(new IpasInteger(98765432101234.0));
		// action type
		String actionType = "xxx";
		// action date
		IpasDateTime actionDate = new IpasDateTime("17/12/2010");
		// user nbr
		IpasInteger userNbr = new IpasInteger(4);

		try {
			insertUserdocAction(documentId, actionType, actionDate, userNbr);
		} catch (IpasException e) {
			System.out.println("insertMarkAction error:" + e.getMessage()+"\r\n");
		}
*/
		// ****************************************************
		// get next journal to be published, using getNextJournalToBePublished
		// ****************************************************
/*
		CJournalId journalId = new CJournalId();
		
		try {
			journalId = getNextJournalToBePublished();
		} catch (IpasException e) {
			System.out.println("getNextJournalToBePublished error:" + e.getMessage()+"\r\n");
		}

		System.out.println("Next journal to be published:" + journalId.getJournalCode());
*/
		// ****************************************************
		// get next journal to be exported after publication, using getNextJournalToBeExported
		// ****************************************************
/*
		// control date of last processed journal
		IpasDateTime controlDate = new IpasDateTime("01/01/2000");

		CJournalId journalId = new CJournalId();
		
		try {
			journalId = getNextJournalToBeExported(controlDate);
		} catch (IpasException e) {
			System.out.println("getNextJournalToBeExported error:" + e.getMessage()+"\r\n");
		}

		System.out.println("Next journal to be exported:" + journalId.getJournalCode());
*/
		// ****************************************************
		// get list of actions in a journal, including the affected mark/userdoc
		// ****************************************************
/*
		// get API objects implementing interfaces
		IJournal iJournal = commonsProxyFactory.getIJournal();
		IMark iMark = commonsProxyFactory.getIMark();
		IUserdoc iUserdoc = commonsProxyFactory.getIUserdoc();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		// control date of last processed journal
		CJournalId journalId = new CJournalId();
		journalId.setJournalCode("01");
		
		try {
			CActionList actionSummaryList = iJournal.mGetActionList(journalId);
			// browse list of actions and print action data items
			for (CAction actionSummary : actionSummaryList) {
				// read action details
				CAction action = iProcess.mReadAction(actionSummary.getActionId());

				// read process containing the action (do not read actions or freezings)
				CProcess process = iProcess.mRead(actionSummary.getActionId().getProcessId(), false, false);
				// build texts describing the keys of the affected entities
				String fileIdText = process.getProcessOriginData().getFileId().getFileSeq() + "/" + 
					process.getProcessOriginData().getFileId().getFileType() + "/" +
					process.getProcessOriginData().getFileId().getFileSeries() + "/" +
					process.getProcessOriginData().getFileId().getFileNbr();
				String documentIdText = process.getProcessOriginData().getDocumentId().getDocOrigin() + "/" +
					process.getProcessOriginData().getDocumentId().getDocLog() + "/" +
					process.getProcessOriginData().getDocumentId().getDocSeries() + "/" +
					process.getProcessOriginData().getDocumentId().getDocNbr();
				String offidocIdText = process.getProcessOriginData().getOfficedocId().getOffidocOrigin() +
					process.getProcessOriginData().getOfficedocId().getOffidocSeries() +
					process.getProcessOriginData().getOfficedocId().getOffidocNbr();
				// check the origin of the process
				if (process.getProcessOriginData().getRelatedToWorkCode().intValue() == 1) {
					// process is related to a file
					System.out.println("Affected mark: " + fileIdText);
				} else if (process.getProcessOriginData().getRelatedToWorkCode().intValue() == 2) {
					// process is related to a userdoc and one affected file
					System.out.println("Affected userdoc+mark: " + documentIdText + " + " + fileIdText);
				} else if (process.getProcessOriginData().getRelatedToWorkCode().intValue() == 3) {
					// process is related to a officedoc
					System.out.println("Affected officedoc: " + offidocIdText);
				} else if (process.getProcessOriginData().getRelatedToWorkCode().intValue() == 4) {
					// process is related to nothing
					System.out.println("Affects no external entity");
				} else if (process.getProcessOriginData().getRelatedToWorkCode().intValue() == 5) {
					// process is related to userdoc with no file
					System.out.println("Affected userdoc: " + documentIdText);
				}
				
				// print action details
				System.out.println("Process type/nbr: " + action.getActionId().getProcessId().getProcessType() + "/" + action.getActionId().getProcessId().getProcessNbr());
				System.out.println("Date: " + action.getActionDate());
				System.out.println("Type/name: " + action.getActionType().getActionTypeId().getActionType() + "/" + action.getActionType().getActionName());
				System.out.println("General notes: " + action.getNotes());
				System.out.println("User nbr: " + action.getCaptureUser().getUserId().getUserNbr());
				// the "notes" fields are only shown if applicable
				if (!action.getActionType().getNotes1Prompt().equals("")) {
					System.out.println("Notes 1 label/value: " + action.getActionType().getNotes1Prompt() + "/" + action.getNotes1());
				}
				if (!action.getActionType().getNotes2Prompt().equals("")) {
					System.out.println("Notes 2 label/value: " + action.getActionType().getNotes2Prompt() + "/" + action.getNotes2());
				}
				if (!action.getActionType().getNotes3Prompt().equals("")) {
					System.out.println("Notes 3 label/value: " + action.getActionType().getNotes3Prompt() + "/" + action.getNotes3());
				}
				if (!action.getActionType().getNotes4Prompt().equals("")) {
					System.out.println("Notes 4 label/value: " + action.getActionType().getNotes4Prompt() + "/" + action.getNotes4());
				}
				if (!action.getActionType().getNotes5Prompt().equals("")) {
					System.out.println("Notes 5 label/value: " + action.getActionType().getNotes5Prompt() + "/" + action.getNotes5());
				}
				// print list of selected options 
				for (COption option : action.getSelectedOptionList()) {
					// longNameAsPrinted is built based on longName after substituting the 
		            // field names by their values
		            String longNameAsPrinted = new String(option.getLongName());
		            int index1 = 0;
		            int index2 = 0;
		            while (true) {
		            	// locate opening "<{"
		            	index1 = longNameAsPrinted.indexOf("<{", index2); //$NON-NLS-1$
		            	if (index1 == -1) break;
		            	// locate closing "}>"
		            	index2 = longNameAsPrinted.indexOf("}>", index1); //$NON-NLS-1$
		            	if (index2 == -1) break;
		            	// search currentOption.listOptionField for the field name found
		            	COptionFieldList optionFieldList = option.getOptionFieldList();
		            	for (COptionField optionField : optionFieldList) {
		            		if (optionField.getOptionFieldName().equals(longNameAsPrinted.substring(index1 + 2, index2))) {
		            			// the field name was found, replace it with the value
		            			longNameAsPrinted = longNameAsPrinted.substring(0, index1) + optionField.getOptionFieldValue() + longNameAsPrinted.substring(index2 + 2);
		            			// continue searching from the point where starting of field was found
		            			index2 = index1;
		            			break;
		            		}
		            	}
		            }
					System.out.println("List/option: " + option.getListCode() + "/" + option.getOptionNbr());
					System.out.println("Option printed text: " + longNameAsPrinted);
				}
			}
		} catch (IpasException e) {
			System.out.println("Error:" + e.getMessage()+"\r\n");
		}
*/

	}

	public void insertMarkAction(String fileSeq, String fileType, int fileSeries, int fileNumber, String actionTypeId, String userId) throws IpasException {
		// create proxy factory based on the IIOP service address and port
        String hostPort = this.getDbHostName()+":"+this.getIiopPort();
        commonsProxyFactory = new CommonsProxyFactory(hostPort);
        if(commonsProxyFactory == null){
           
				throw new RuntimeException("Error obtaining connection to IPAS Application specified by Host Name : " +
				        this.getDbHostName() + " , IIOP Port : " + this.getIiopPort() + " in the properties file.");
			
			
			
			
        }
		CFileId cFileId = new CFileId();
		cFileId.setFileSeq(fileSeq);
		cFileId.setFileType(fileType);
		cFileId.setFileSeries(new IpasInteger(fileSeries));
		cFileId.setFileNbr(new IpasInteger(fileNumber));
		// action date - of current day (as of now)
        Date date = GregorianCalendar.getInstance().getTime();
		IpasDateTime actionDate = new IpasDateTime(sdf.format(date));
		// user nbr
		IpasInteger userNbr = new IpasInteger(userId);

	
			insertMarkAction(cFileId,actionTypeId, actionDate, userNbr);
	
			
			
	
	}

	public static void createOpenDailyLog(
			// code for the document origin of the daily log
			String docOrigin, 
			// date of the daily log
			IpasDateTime dailyLogDate
	) throws IpasException {

		// Purpose: create/open a daily log book

		// get API objects implementing interfaces
		IDailyLog iDailyLog = commonsProxyFactory.getIDailyLog();

		// declare objects
		CDailyLog dailyLog;

		// build id of daily log to be created/opened
		CDailyLogId dailyLogId = new CDailyLogId();
		dailyLogId.setDocLog("E");
		dailyLogId.setDocOrigin(docOrigin);
		dailyLogId.setDailyLogDate(dailyLogDate);

		// read/insert the daily log book
		try {
			dailyLog = iDailyLog.mRead(dailyLogId);
		} catch (IpasException e) {
			// daily log book does not exist, insert it
			dailyLog = new CDailyLog();
			dailyLog.setDailyLogId(dailyLogId);
			iDailyLog.mInsert(dailyLog);
		}

		// open the daily log book
		iDailyLog.mOpen(dailyLogId);
	}

	
	public static void addExtraPayment(
			// nbr of the receipt which is being supplemented
			IpasInteger supplementedReceiptNbr,		 
			// supplementary payment
			CPayment extraPayment	
	) throws IpasException {

		// Purpose: add an supplementary payment for prior payment

		// get API objects implementing interfaces
		IFile iFile = commonsProxyFactory.getIFile();
		IUserdoc iUserdoc = commonsProxyFactory.getIUserdoc();

		// the supplemented receipt may have been used in a file or a userdoc, so both are read
		// read file by receipt nbr
		CCriteriaFile criteriaFile = new CCriteriaFile();
		criteriaFile.getCriteriaReceipt().setReceiptNbr(supplementedReceiptNbr);
		CFileSummaryList fileSummaryList = iFile.mGetList(criteriaFile);
		if (fileSummaryList.size() == 1) {
			// the file where the supplemented receipt was used is found, it is updated 
			// read the file
			CFile file = iFile.mRead(fileSummaryList.get(0).getFileId());
			// add the supplementary payment to the list of payments for the file
			file.getFilingData().getPaymentList().add(extraPayment);
			// update the file
			iFile.mUpdate(file);
        } else if (fileSummaryList.size() > 1) {
			// the receipt nbr is used in several files, generate error
			throw new IpasException("The supplemented receipt nbr is used by " + String.valueOf(fileSummaryList.size()) + " different files");
		} else {
			// the file where the supplemented receipt was used is not found, a userdoc is searched for 
			CCriteriaUserdoc criteriaUserdoc = new CCriteriaUserdoc();
			criteriaUserdoc.getCriteriaReceipt().setReceiptNbr(supplementedReceiptNbr);
			CUserdocSummaryList userdocSummaryList = iUserdoc.mGetList(criteriaUserdoc);
			if (userdocSummaryList.size() == 1) {
				// the userdoc where the supplemented receipt was used is found, it is updated 
				// read the userdoc
				CUserdoc userdoc = iUserdoc.mRead(userdocSummaryList.get(0).getDocumentId());
				// add the supplementary payment to the list of payments for the userdoc
				userdoc.getFilingData().getPaymentList().add(extraPayment);
				// update the userdoc
				iUserdoc.mUpdate(userdoc);
            } else if (userdocSummaryList.size() > 1) {
				// the receipt nbr is used in several userdocs, generate error
				throw new IpasException("The supplemented receipt nbr is used by " + String.valueOf(userdocSummaryList.size()) + " different userdocs");
			} else {
				// the receipt nbr is not used in any file or userdoc, generate error
				throw new IpasException("The supplemented receipt nbr is not used");
			}
		}

	}

	public static void insertMarkAction(
			// id of the mark
			CFileId fileId,		 
			// type of the action
			String actionType,
			// date of the action
			IpasDateTime actionDate,
			// user who took decision
			IpasInteger userNbr
	) throws IpasException {

		// get API objects implementing interfaces
		IMark iMark = commonsProxyFactory.getIMark();
		IProcess iProcess = commonsProxyFactory.getIProcess();

		CMark mark = iMark.mRead(fileId, false, false);

        String markIdFormatted = mark.getFile().getFileId().getFileSeq() + "/" +
                mark.getFile().getFileId().getFileType() + "/" +
                mark.getFile().getFileId().getFileSeries() + "/" +
                mark.getFile().getFileId().getFileNbr();

		logger.info("------- Inserting Note Action generating Office Doc for Mark : "+ markIdFormatted + " ----------------");
		// get processId from mark
		CProcessId processId = mark.getFile().getProcessId();

		// build action type for the decision
		CActionTypeId actionTypeId = new CActionTypeId();
		actionTypeId.setActionType(actionType);

		// build user who took decision
		CUserId userId = new CUserId();
		userId.setUserNbr(userNbr);

		// build extra parameters which will not be used 
		
		COptionList optionList = new COptionList();
		String notes1 = "TEST NOTES 1";
		String notes2 = "TEST NOTES 2";
		String notes3 = "TEST NOTES 3";
		String notes4 = "TEST NOTES 4";
		String notes5 = "TEST NOTES 5";
	
        String actionNoteForNA = "Test Data NOTE Action for Mark ( "+ markIdFormatted +" ) inserted via IpasAPI";
		CAckMessageNbrList ackMessageNbrList = new  CAckMessageNbrList();
		CAckMessageTextList ackMessageTextList = new  CAckMessageTextList();
		
		// Insert Action
        // ActionType 'A' - New Ordinary Action in the Workflow
		/*iProcess.mInsertAction(processId, actionTypeId, actionDate, manualDueDate,
				responsibleUserId, optionList, notes1, notes2, notes3, notes4, notes5, actionNoteForOA,
				ackMessageNbrList, ackMessageTextList, userId, new IpasInteger(4));*/

        // Insert Action
        // ActionType 'N' - New NOTE Action in the Workflow - OD Printing
		//IpasInteger refMark = new IpasInteger(641);
		
     
            iProcess.mInsertNoteAction(processId, actionTypeId, actionDate, optionList,
                    notes1, notes2, notes3, notes4, notes5, actionNoteForNA, ackMessageNbrList,
                    ackMessageTextList, new CUserId(), "");
     
	}

	public static void insertUserdocAction(
			// id of the userdoc
			CDocumentId documentId,		 
			// type of the action
			String actionType,
			// date of the action
			IpasDateTime actionDate,
			// user who took decision
			IpasInteger userNbr
	) throws IpasException {

		// get API objects implementing interfaces
		IProcess iProcess = commonsProxyFactory.getIProcess();

		// build action type for the decision
		CActionTypeId actionTypeId = new CActionTypeId();
		actionTypeId.setActionType(actionType);

		// build user who took decision
		CUserId userId = new CUserId();
		userId.setUserNbr(userNbr);

		// build extra parameters which will not be used 
		IpasDateTime manualDueDate = new IpasDateTime();
		CUserId responsibleUserId = new CUserId();
		COptionList optionList = new COptionList();
		String notes1 = "";
		String notes2 = "";
		String notes3 = "";
		String notes4 = "";
		String notes5 = "";
		String notes = "";
		CAckMessageNbrList ackMessageNbrList = new  CAckMessageNbrList();
		CAckMessageTextList ackMessageTextList = new  CAckMessageTextList();

		// get list of processes affecting the userdoc
		CCriteriaProcess criteriaProcess = new CCriteriaProcess();
		criteriaProcess.getCriteriaProcessByOfficedoc().setDocumentId(documentId);
		CProcessSummaryList processSummaryList = iProcess.mGetList(criteriaProcess, new IpasInteger(100));

		// several processes may exist in case the userdoc affects many files, so an
		// action in inserted in each of the processes
		for (CProcessSummary processSummary : processSummaryList ) {
			// add action
			/*iProcess.mInsertAction(processSummary.getProcessId(), actionTypeId, actionDate, manualDueDate, 
					responsibleUserId, optionList, notes1, notes2, notes3, notes4, notes5, notes, 
					ackMessageNbrList, ackMessageTextList, userId);*/
		}

	}

	
	public static CJournalId getNextJournalToBePublished() throws IpasException {

		// returns the next to-be-published journal later than the control date  
		// get API objects implementing interfaces
		IJournal iJournal = commonsProxyFactory.getIJournal();

		// get list of to-be-published journals
		CCriteriaJournal criteriaJournal = new CCriteriaJournal();
		criteriaJournal.setIndToBePublished(true);
		CJournalList journalList = iJournal.mGetList(criteriaJournal);

		// check no journals exists and generate an exception
		if (journalList.size() == 0) {
			throw new IpasException("No journal exists in the to-be-published status");
		}
		
		// browse list of journals and select the one with the minimum one
		String minJournalCode = String.valueOf(Character.MAX_VALUE);
		CJournalId journalId =  new CJournalId(); 
		for (CJournal journal : journalList ) {
			if (journal.getJournalId().getJournalCode().compareTo(minJournalCode) < 0) {
				journalId = journal.getJournalId();
				minJournalCode = journal.getJournalId().getJournalCode();
			}
		}
		return  journalId;
	}

	public static CJournalId getNextJournalToBeExported(
			// control date
			IpasDateTime controlDate
	) throws IpasException {

		// returns the next published journal later than the control date
		
		// get API objects implementing interfaces
		IJournal iJournal = commonsProxyFactory.getIJournal();

		// get list of journals published after the control date
		CCriteriaJournal criteriaJournal = new CCriteriaJournal();
		criteriaJournal.setPublicationDateMin(controlDate);
		CJournalList journalList = iJournal.mGetList(criteriaJournal);

		// check no journals exists and generate an exception
		if (journalList.size() == 0) {
			throw new IpasException("No journal has been published after the control date");
		}
		
		// browse list of journals and select the one with the minimum publication date
		IpasDateTime minPublicationDate = new IpasDateTime("31/12/2999");
		CJournalId journalId =  new CJournalId(); 
		for (CJournal journal : journalList ) {
			if (journal.getPublicationDate().getCalendarValue().compareTo(minPublicationDate.getCalendarValue()) < 0) {
				journalId = journal.getJournalId();
				minPublicationDate = journal.getPublicationDate();
			}
		}
		return  journalId;
	}

}

