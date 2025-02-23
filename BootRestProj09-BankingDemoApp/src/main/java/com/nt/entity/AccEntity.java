package com.nt.entity;

import com.nt.userEntity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="bank_demo_db")
public class AccEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer accId;
	private Long accNo; 
	private String accHolderName;
	private Double balance;
	private long contactNo;
	private String email; 
	private Long aadharId;
	private String panId;
	
	  @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id", referencedColumnName = "uid", nullable = false)
	    private User user;

}
