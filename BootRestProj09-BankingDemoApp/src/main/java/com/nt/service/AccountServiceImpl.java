package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.dto.AccountDTO;
import com.nt.dto.TransferDTO;
import com.nt.entity.AccEntity;
import com.nt.exception.AccountNotFoundException;
import com.nt.exception.InsufficientFundsException;
import com.nt.repo.AccountRepository;
import com.nt.userEntity.User;
import com.nt.userRepo.UserRepository;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public String createAccount(AccountDTO accDTO) {
		
		User user = userRepo.findById(accDTO.getUser().getUid())
                .orElseThrow(() -> new RuntimeException("User not found"));
		
		AccEntity entity = new AccEntity();
		
		entity.setAccHolderName(accDTO.getAccHolderName());
		entity.setAadharId(accDTO.getAadharId());
		entity.setBalance(accDTO.getBalance());
		entity.setContactNo(accDTO.getContactNo());
		entity.setEmail(accDTO.getEmail());
		entity.setAccNo(generateAccoutNo());
		entity.setPanId(accDTO.getPanId());
		entity.setUser(user);
		
		AccEntity accDetails = accRepo.save(entity);
		
		AccountDTO dto = new AccountDTO();
		dto.setAccId(accDetails.getAccId());
		dto.setAccHolderName(accDetails.getAccHolderName());
		dto.setAccNo(accDetails.getAccNo());
		dto.setAadharId(accDetails.getAadharId());
		dto.setBalance(accDetails.getBalance());
		dto.setContactNo(accDetails.getContactNo());
		dto.setEmail(accDetails.getEmail());
		dto.setPanId(accDetails.getPanId());
		dto.setUser(accDetails.getUser());
		return "account created successfully with account no"+dto.getAccNo();
	}
	
	public long generateAccoutNo() {
		long accNo=  (long)(Math.random()*1000000000000L);
		return accNo;
	}

	@Override
	public String depositAmount(Long accNo, Double amount) throws AccountNotFoundException {
	    Optional<AccEntity> opt = accRepo.findByAccNo(accNo);
	    
	    if (opt.isPresent()) {
	        AccEntity entity = opt.get();
	        double totalAmount = entity.getBalance() + amount;
	        entity.setBalance(totalAmount);
	        accRepo.save(entity);
	        return String.format("%s Rs/- deposited to account %d successfully. Available balance: %.2f", amount, accNo, totalAmount);
	    } else {
	        throw new AccountNotFoundException("Account not found with account number: " + accNo);
	    }
	}

	@Override
	public String withdrawAmount(Long accNo, Double amount) throws AccountNotFoundException {
		
		if (amount <= 0) {
	        throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
	    }
		
		 Optional<AccEntity> opt = accRepo.findByAccNo(accNo);
		 
		 if (opt.isPresent()) {
		        AccEntity entity = opt.get();
		        double avialableBalance = entity.getBalance();
		        if(amount >avialableBalance) {
		            throw new InsufficientFundsException("Insufficient funds for account number: " + accNo);
		        }
		        double totalAmount =avialableBalance  - amount;
		        entity.setBalance(totalAmount);
		        accRepo.save(entity);
		        return String.format("%s Rs/- withdrawn from account %d successfully. Available balance: %.2f", amount, accNo, totalAmount);
		    } else {
		        throw new AccountNotFoundException("Account not found with account number: " + accNo);
		    }
	}

	@Override
	public List<AccEntity> getAllAccounts() {
		List<AccEntity> accList = accRepo.findAll();
		return accList ;
	}

	@Override
	public double getBalance(Long accNo) {
		return accRepo.findBalanceByAccNo(accNo);
	}

	 public List<AccEntity> getAccountByUserId(Integer uid) {
	        return accRepo.findByUser_Uid(uid)
	                .orElseThrow(() -> new RuntimeException("user id not found for fetching account details ,please create new account"));
	    }

	@Override
	public String transferAmount(TransferDTO transferDTO) throws AccountNotFoundException {
		
		
		AccEntity fromAccount = getAccountByAccNo(transferDTO.getFromAccount());
		AccEntity toAccount = getAccountByAccNo(transferDTO.getToAccount());
		
		if(transferDTO.getAmount() <= 0) {
			return "Amount must be gerater than zero";
		}
		else if(transferDTO.getAmount()>fromAccount.getBalance()) {
			return "Insufficient funds in your account,please enter a valid amount";
		}
		else {
		double amount = fromAccount.getBalance()-transferDTO.getAmount();
		double amount2 = toAccount.getBalance()+transferDTO.getAmount();
		
		fromAccount.setBalance(amount);
		toAccount.setBalance(amount2);
		
		accRepo.saveAll(List.of(fromAccount,toAccount));
		
		return "Amount"+" "+transferDTO.getAmount()+" "+"transfer successfully from accNo"+" "+transferDTO.getFromAccount()+" "+"to accNo"+" "+transferDTO.getToAccount();
		}
	}
	
	public AccEntity getAccountByAccNo(Long accNo) throws AccountNotFoundException{
		Optional<AccEntity> opt = accRepo.findByAccNo(accNo);
		if(opt.isEmpty()) {
			throw new AccountNotFoundException("Acount number is not existed,please enter a valis account number");
		}
		return opt.get();
	}
	
	
}
