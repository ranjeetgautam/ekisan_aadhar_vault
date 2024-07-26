package com.aadhar.vault.controller;

import java.security.SecureRandomSpi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aadhar.vault.exception.GenericException;
import com.aadhar.vault.payload.ResponseMessage;
import com.aadhar.vault.service.AadharAuthenticationKycService;
import com.aadhar.vault.service.impl.AadharAuthenticationKycServiceImpl;

@RestController
@RequestMapping("/api/auth/aadhar")
public class AadharAuthenticationKycController {
    private final Logger log = LoggerFactory.getLogger(AadharAuthenticationKycController.class);

    @Autowired
    private AadharAuthenticationKycService aadharAuthenticationKycService;

    @PostMapping("/v1/str")
    public ResponseMessage<String> storeAdv(@RequestParam(required = true) String aadharNo,
                                            @RequestParam(required = true) Long userId) {
        try {
            log.debug("Before calling storeAdv");
            String refNum = aadharAuthenticationKycService.storeAdv(aadharNo, userId);
            log.debug("After calling storeAdv");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data Found", refNum);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/get_ref")
    public ResponseMessage<String> getRefNumber(@RequestParam(required = true) String aadharNo,
                                                 @RequestParam(required = true) Long userId) {
        try {
            log.debug("Before calling getRefNumber");
            String refNumber = aadharAuthenticationKycService.getRefNumber(aadharNo, userId);
            log.debug("After calling getRefNumber");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data Found", refNumber);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/get_uid")
    public ResponseMessage<String> getUidRequest(@RequestParam(required = true) String refNumber,
                                                  @RequestParam(required = true) Long userId) {
        try {
            log.debug("Before calling getUidRequest");
            String uid = aadharAuthenticationKycService.getUidRequest(refNumber, userId);
            log.debug("After calling getUidRequest");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data Found", uid);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    
    @PostMapping("/v1/activate_uid")
    public ResponseMessage<String> activateUid(@RequestParam(required = true) String refNumber,
                                                @RequestParam(required = true) Long userId) {
        try {
            log.debug("Before calling activateUid");
            String response = aadharAuthenticationKycService.activateUid(refNumber, userId);
            log.debug("After calling activateUid");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data Found", response);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/de_activate_uid")
    public ResponseMessage<String> deActivateUID(@RequestParam(required = true) String refNumber,
                                                  @RequestParam(required = true) Long userId) {
        try {
            log.debug("Before calling deActivateUID");
            String response = aadharAuthenticationKycService.deActivateUID(refNumber, userId);
            log.debug("After calling deActivateUID");
            return new ResponseMessage<>(HttpStatus.OK.value(), "Data Found", response);
        } catch (Exception e) {
            log.debug("Error: " + e.getMessage());
            throw new GenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
