package com.aadhar.vault.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aadhar.vault.Constant.AadhaarErrorConstant;
import com.aadhar.vault.exception.GenericException;
import com.aadhar.vault.service.AadharAuthenticationKycService;

import in.cdac.connector.ConnectorImpl;
import in.cdac.connector.IConnector;
import in.cdac.cryptoservice.KeyType;
import in.cdac.cryptoservice.Operations;
import in.cdac.cryptoservice.Status;
import in.cdac.cryptoservice.TokenType;
import in.cdac.model.RequestObject;
import in.cdac.model.ResponseObject;
import in.cdac.util.Utility;

@Service
public class AadharAuthenticationKycServiceImpl implements AadharAuthenticationKycService {

	private final Logger log = LoggerFactory.getLogger(AadharAuthenticationKycServiceImpl.class);

	@Value("${accessCode}")
	private String ac;

	@Value("${subCode}")
	private String sc;

	@Value("${licenseKey}")
	private String lk;

	@Value("${url}")
	private String url;

	@Override
	public String storeAdv(String aadharNo, Long userId) {
		log.debug("before calling storeNumber method");
		return storeNumber(aadharNo, userId);

	}

	private String storeNumber(String aadharNo, Long userId) {

		Map<String, String> errorMessages = new HashMap<>();
		AadhaarErrorConstant.populateErrorMessages(errorMessages);

		log.debug("in storeNumber method");
		IConnector conn = new ConnectorImpl();
		RequestObject vltReq = new RequestObject();

		vltReq.setOpr(Operations.STRUID);

		// set Aadhaar number
		vltReq.setNumber(aadharNo);
		vltReq.setTxn(UUID.randomUUID().toString());
		vltReq.setTs(Utility.generateTimeStamp());
		vltReq.setRefNum("");
		vltReq.setKeytype(KeyType.AES);
		vltReq.setTkntype(TokenType.SOFT);
		vltReq.setAc(ac);
		vltReq.setSa(sc);
		vltReq.setLk(lk);
		vltReq.setUrl(url);

		ResponseObject vltResp;

		try {
			log.debug("before making the connection");
			vltResp = conn.requestVault(vltReq);
			log.debug("After making the connection {}",vltResp);
		} catch (Exception e) {
			log.error("Error in connection: " + e.getMessage());
			throw new GenericException("Error connecting to the vault service: " + e.getMessage(),
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		if (vltResp.getStatus().equals(Status.N.value().trim())) {
			log.debug("Error storing Aadhaar number in vault: {}", vltResp);
			printLog(vltResp);
			String errorMessage = errorMessages.get(vltResp.getError());
			if (errorMessage != null) {
				throw new GenericException("Failed to store Aadhaar number in vault. Error code: " + vltResp.getError()
						+ " " + errorMessage, HttpStatus.BAD_REQUEST);
			} else {
				throw new GenericException("Failed to store Aadhaar number in vault. Reason: " + vltResp.getError(),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return vltResp.getRefNum();
		}

	}

	@Override
	public String getRefNumber(String aadharNo, Long userId) {
		// TODO Auto-generated method stub
		log.debug("before calling refNumberGet method");
		return refNumberGet(aadharNo, userId);
	}

	private String refNumberGet(String aadharNo, Long userId) {
		// TODO Auto-generated method stub
		Map<String, String> errorMessages = new HashMap<>();
		AadhaarErrorConstant.populateErrorMessages(errorMessages);

		IConnector conn = new ConnectorImpl();
		RequestObject reqObject = new RequestObject();
		reqObject.setTxn(UUID.randomUUID().toString());
		reqObject.setOpr(Operations.GETREFNUM);
		reqObject.setNumber(aadharNo);
		reqObject.setTkntype(TokenType.HARD);
		reqObject.setTs(Utility.generateTimeStamp());
		reqObject.setAc(ac);
		reqObject.setSa(sc);
		reqObject.setLk(lk);
		reqObject.setUrl(url);

		ResponseObject respObjuid;
		try {
			log.debug("before making the connection");
			respObjuid = conn.requestVault(reqObject);
			log.debug("after making the connection {}",respObjuid);
		} catch (Exception e) {
			log.error("Error in connection: " + e.getMessage());
			throw new GenericException("Error connecting to the vault service: " + e.getMessage(),
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		if (respObjuid.getStatus().equals(Status.N.value().trim())) {
			log.debug("Error retrieving Aadhaar number from vault: {}", respObjuid);
			printLog(respObjuid);
			String errorMessage = errorMessages.get(respObjuid.getError());
			if (errorMessage != null) {
				throw new GenericException("Failed to retrieve Aadhaar number from vault. Error code: "
						+ respObjuid.getError() + " " + errorMessage, HttpStatus.BAD_REQUEST);
			} else {
				throw new GenericException(
						"Failed to retrieve Aadhaar number from vault.  Reason: " + respObjuid.getError(),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			log.debug("return refNum");
			return respObjuid.getRefNum();
		}

	}

	@Override
	public String getUidRequest(String refNumber, Long userId) {
		// TODO Auto-generated method stub
		log.debug("before calling UidRequest method");
		return UidRequest(refNumber, userId);
	}

	private String UidRequest(String refNumber, Long userId) {
		// TODO Auto-generated method
		Map<String, String> errorMessages = new HashMap<>();
		AadhaarErrorConstant.populateErrorMessages(errorMessages);

		IConnector conn = new ConnectorImpl();
		RequestObject vltReq = new RequestObject();

		vltReq.setTxn(UUID.randomUUID().toString());
		vltReq.setOpr(Operations.GETUID);
		vltReq.setRefNum(refNumber);
		vltReq.setAc(ac);
		vltReq.setSa(sc);
		vltReq.setLk(lk);
		vltReq.setUrl(url);
		vltReq.setTs(Utility.generateTimeStamp());
		vltReq.setTkntype(TokenType.SOFT);

		ResponseObject respObjuid;
		try {
			log.debug("before making the connection");
			respObjuid = conn.requestVault(vltReq);
			log.debug("after making the connection",respObjuid);
		} catch (Exception e) {
			log.error("Error in connection: " + e.getMessage());
			throw new GenericException("Error connecting to the vault service: " + e.getMessage(),
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		if (respObjuid.getStatus().equals(Status.N.value().trim())) {
			log.error("Error retrieving UUID: {}", respObjuid);
			printLog(respObjuid);
			String errorMessage = errorMessages.get(respObjuid.getError());
			if (errorMessage != null) {
				throw new GenericException(
						"Failed to retrieve UUID. Error code: " + respObjuid.getError() + " " + errorMessage,
						HttpStatus.BAD_REQUEST);
			} else {
				throw new GenericException("Failed to retrieve UUID.  Reason: " + respObjuid.getError(),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			log.debug("Returning the response");
			return respObjuid.getNumber();
		}

	}

	@Override
	public String activateUid(String refNumber, Long userId) {
		// TODO Auto-generated method stub
		log.debug("before calling getActiveUid method");
		return getActiveUid(refNumber, userId);
	}

	private String getActiveUid(String refNumber, Long userId) {
		// TODO Auto-generated method stub
		Map<String, String> errorMessages = new HashMap<>();
		AadhaarErrorConstant.populateErrorMessages(errorMessages);

		IConnector conn = new ConnectorImpl();
		RequestObject reqObject = new RequestObject();

		reqObject.setTxn(UUID.randomUUID().toString());
		reqObject.setOpr(Operations.ACTIVATE);
		reqObject.setRefNum(refNumber);
		reqObject.setKeytype(KeyType.AES);
		reqObject.setTkntype(TokenType.HARD);
		reqObject.setAc(ac);
		reqObject.setSa(sc);
		reqObject.setLk(lk);
		reqObject.setUrl(url);

		reqObject.setTs(Utility.generateTimeStamp());

		ResponseObject respObjuid;
		try {
			log.debug("before making the connection");
			respObjuid = conn.requestVault(reqObject);
			log.debug("after making the connection {}",respObjuid);
		} catch (Exception e) {
			log.error("Error in connection: " + e.getMessage());
			throw new GenericException("Error connecting to the vault service: " + e.getMessage(),
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		if (respObjuid.getStatus().equals(Status.N.value().trim())) {
			log.debug("Error : {}" , respObjuid);
			printLog(respObjuid);
			String errorMessage = errorMessages.get(respObjuid.getError());
			if (errorMessage != null) {
				throw new GenericException(
						"Failed to activate Uid. Error code: " + respObjuid.getError() + " " + errorMessage,
						HttpStatus.BAD_REQUEST);
			} else {
				throw new GenericException("Failed to activate Uid.  Reason: " + respObjuid.getError(),
						HttpStatus.BAD_REQUEST);
			}

		} else {
			log.debug("return refNum");
			return respObjuid.getRefNum();
		}

	}

	@Override
	public String deActivateUID(String refNumber, Long userId) {
		// TODO Auto-generated method stub
		return uidDeAvtivate(refNumber, userId);
	}

	private String uidDeAvtivate(String refNumber, Long userId) {
		// TODO Auto-generated method stub
		Map<String, String> errorMessages = new HashMap<>();
		AadhaarErrorConstant.populateErrorMessages(errorMessages);

		IConnector conn = new ConnectorImpl();
		RequestObject reqObject = new RequestObject();

		reqObject.setTxn(UUID.randomUUID().toString());
		reqObject.setOpr(Operations.DEACTIVATE);
		reqObject.setRefNum(refNumber);
		reqObject.setKeytype(KeyType.AES);
		reqObject.setTkntype(TokenType.HARD);
		reqObject.setAc(ac);
		reqObject.setSa(sc);
		reqObject.setLk(lk);
		reqObject.setUrl(url);
		reqObject.setTs(Utility.generateTimeStamp());

		ResponseObject respObjuid;

		try {
			log.debug("before making the connection");
			respObjuid = conn.requestVault(reqObject);
			log.debug("after making the connection{}",respObjuid);
		} catch (Exception e) {
			log.error("Error in connection: " + e.getMessage());
			throw new GenericException("Error connecting to the vault service: " + e.getMessage(),
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		if (respObjuid.getStatus().equals(Status.N.value().trim())) {
			log.debug("Error :{} " , respObjuid);
			printLog(respObjuid);
			String errorMessage = errorMessages.get(respObjuid.getError());
			if (errorMessage != null) {
				throw new GenericException(
						"Failed to deActivate Uid. Error code: " + respObjuid.getError() + " " + errorMessage,
						HttpStatus.BAD_REQUEST);
			} else {
				throw new GenericException("Failed to deActivate Uid.  Reason: " + respObjuid.getError(),
						HttpStatus.BAD_REQUEST);
			}

		} else {
			log.debug("return refNum");
			return respObjuid.getRefNum();
		}
	}
	
	public void printLog(ResponseObject resp) {
		log.debug("after Error{}",resp.getError());
		log.debug("after id data{}",resp.getIdData());
		log.debug("after id type{}",resp.getIdType());
		log.debug("after key identifiet{}",resp.getKeyidentifier());
		log.debug("after number{}",resp.getNumber());
		log.debug("after ref num{}",resp.getRefNum());
		log.debug("after status{}",resp.getStatus());
		log.debug("after txn{}",resp.getTxn());
	}

}