package com.ProjectTrial1.Projectdemo1.contact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository {

	Address addAddress(Address address);
	List<Address> getAllAddresses();
	List<Address> getAddressByUserId(String userId);
	Address getAddressByAddressId(String addressId);
}
