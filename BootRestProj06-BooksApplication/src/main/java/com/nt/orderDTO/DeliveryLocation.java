package com.nt.orderDTO;

import java.io.IOException;
import java.io.OutputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryLocation {

	Integer Id;
	String name;
	Integer cost;
	
}
