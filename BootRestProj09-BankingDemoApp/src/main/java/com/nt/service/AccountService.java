package com.nt.service;

import java.util.List;

import com.nt.dto.AccountDTO;
import com.nt.dto.TransferDTO;
import com.nt.entity.AccEntity;
import com.nt.exception.AccountNotFoundException;

public interface AccountService {
	
	public String createAccount(AccountDTO accDTO);
	public String depositAmount(Long accNo,Double amount)throws AccountNotFoundException;
	public String withdrawAmount(Long accNo, Double amount)throws AccountNotFoundException;
	public List<AccEntity> getAllAccounts ();
	public double getBalance(Long accNo);
	public List<AccEntity> getAccountByUserId(Integer uid);
	public String transferAmount(TransferDTO transferDTO)throws AccountNotFoundException;
}
