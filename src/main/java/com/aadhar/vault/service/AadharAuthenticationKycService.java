package com.aadhar.vault.service;

public interface AadharAuthenticationKycService {
    String storeAdv(String aadharNo, Long userId);

	String getUidRequest(String refNumber, Long userId);

	String getRefNumber(String aadharNo, Long userId);

	String activateUid(String refNumber, Long userId);

	String deActivateUID(String refNumber, Long userId);
}
