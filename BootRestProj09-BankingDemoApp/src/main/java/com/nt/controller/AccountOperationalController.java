package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.AccountDTO;
import com.nt.dto.DepositDTO;
import com.nt.dto.OtpRequest;
import com.nt.dto.OtpValidatorDTO;
import com.nt.dto.TransferDTO;
import com.nt.entity.AccEntity;
import com.nt.exception.AccountNotFoundException;
import com.nt.exception.InsufficientFundsException;
import com.nt.service.AccountService;
import com.nt.service.OtpService;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins="http://localhost:3000")
public class AccountOperationalController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OtpService otpService;
	
	@PostMapping("/create-account")
	public ResponseEntity<String> generateAccount(@RequestBody AccountDTO accDTO){
		
		String response = accountService.createAccount(accDTO);
		
		try {
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Getting error while creating account",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
	
	@PutMapping("/deposit")
	public ResponseEntity<String> depositAmount(@RequestBody DepositDTO depositDTO) {
	    try {
	        String result = accountService.depositAmount(depositDTO.getAccNo(), depositDTO.getAmount());
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    } catch (AccountNotFoundException e) {
	        return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Amount deposit failed due to the server problem", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<String> withdrawAmount(@RequestBody DepositDTO depositDTO) {
	    try {
	        String response = accountService.withdrawAmount(depositDTO.getAccNo(), depositDTO.getAmount());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (AccountNotFoundException e) {
	        return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
	    } catch (InsufficientFundsException e) {
	        return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>("Amount withdrawal failed due to server problem", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/getAll-accounts")
	public ResponseEntity<?> fetchAllAccounts(){
		try {
		List<AccEntity> accounts =  accountService.getAllAccounts();
		return new ResponseEntity<List<AccEntity>>(accounts,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Getting Error while Accounts",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private int otp;
	
	  // Generate OTP
    @GetMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String phoneNumber) {
        try {
            otp = otpService.sendOtp(phoneNumber);
            return new ResponseEntity<>("OTP sent to your registered mobile number", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while generating OTP", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Validate OTP and get account balance
    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidatorDTO otpValidatorDTO) {
        if (otpValidatorDTO.getOtp().equals(otp)) {
            Double balance = accountService.getBalance(otpValidatorDTO.getAccNumber());
            System.out.println("account Balance:"+balance);         
           return new ResponseEntity<>("OTP validated successfully. Account balance is: " + balance, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid OTP. Please try again.", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/getByUserId/{uid}")
    public ResponseEntity<List<AccEntity>> getAccountByUsername(@PathVariable Integer uid) {
        List<AccEntity> account = accountService.getAccountByUserId(uid);
        return ResponseEntity.ok(account);
    }
    
    @PutMapping("/transfer-amount")
    public String transferAmount(@RequestBody TransferDTO transferDTO) throws AccountNotFoundException {
    	String response = accountService.transferAmount(transferDTO);
    	return response;
    }
}
