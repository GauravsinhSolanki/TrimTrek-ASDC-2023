package com.ProjectTrial1.Projectdemo1.contact;

import com.ProjectTrial1.Projectdemo1.common.ConstraintViolationException;
import com.ProjectTrial1.Projectdemo1.common.NotFoundException;
import com.ProjectTrial1.Projectdemo1.hirebarber.servicezone.ServiceZoneServices;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServices {
	private static final Logger LOG = LoggerFactory.getLogger(AddressServices.class);

	@Value("${error.message.invalid.input}")
    private String InvalidInput;

	@Value("${error.message.wrong.id}")
	private String idNotExist;

	@Value("${error.message.id.already.exist}")
	private String idAlreadyExist;

	@Autowired
	AddressRepository addressRepo;


//	@Autowired
//    ServiceZoneServices serviceZoneServices;

	public Address addAddress(AddressDto addressDto) throws Exception {
		LOG.debug("addAddress, addressDto: "+ addressDto);
//		if(isAddressDtoValid(addressDto))
//			throw new Exception(InvalidInput);
		if(addressIdAlreadyExist(addressDto.getAddressId()))
			throw new Exception(idAlreadyExist);
		Address dbAddress;
		try {
		Address address = connvertAddressDtotoEntity(addressDto);
		dbAddress = addressRepo.addAddress(address);
		}
		catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(idAlreadyExist);
		}
		LOG.debug("addAddress, dbAddress: "+ dbAddress);
		return dbAddress;
	}

	private boolean addressIdAlreadyExist(String addressId) {
		LOG.info("addressIdAlreadyExist.");
		boolean exist = true;
		Address address = addressRepo.getAddressByAddressId(addressId);
		if(address == null)
			exist = false;
		LOG.debug("addressIdAlreadyExist, exist " +exist);
		return exist;
	}

//	private boolean isAddressDtoValid(AddressDto addressDto) {
//		boolean userId = addressDto.getUserId()==null || addressDto.getUserId().isEmpty();
//		boolean addressId = addressDto.getAddressId()==null || addressDto.getAddressId().isEmpty();
//		boolean city = addressDto.getCity()==null || addressDto.getCity().isEmpty();
//		boolean state = addressDto.getState()==null || addressDto.getState().isEmpty();
//
//		LOG.debug("isAddressDtoValid, userId {}  addressId {}  city {}  state {}" , userId , addressId , city , state);
//		return userId || addressId || city || state;
//	}

	private Address connvertAddressDtotoEntity(AddressDto addressDto) {
		LOG.debug("connvertAddressDtotoEntity, addressDto: "+ addressDto);
		Address address = new Address();
		String generatedString = generateUniqueUserId();
		address.setAddressId(generatedString);
//		address.setAddressId(addressDto.getAddressId());
		address.setUserId(addressDto.getUserId());
		address.setCity(addressDto.getCity());
		address.setHouse(addressDto.getHouse());
		address.setLocality(addressDto.getLocality());
//		address.setMapUrl(addressDto.getMapUrl());
		address.setPinCode(addressDto.getPinCode());
		address.setState(addressDto.getState());
		address.setCreatedBy(addressDto.getCreatedBy());
		address.setCreatedOn(LocalDateTime.now());
//		address.setServiceZoneId(serviceZoneServices.getServiceZoneByServiceZoneId(addressDto.getServiceZoneId()));
		LOG.debug("connvertAddressDtotoEntity, address: "+ address);
		return address;
	}
	private String generateUniqueUserId() {
		String generatedUserId = RandomStringUtils.random(11, true, true);
		return generatedUserId;
	}

	public List<AddressDto> getAllAddresses() {
		LOG.debug("getAllAddresses,  ");
		List<Address> addresses = addressRepo.getAllAddresses();
		List<AddressDto> dtos = new ArrayList<>();

		for(Address address : addresses) {

			AddressDto dto = convertAddressEntitytoDto(address);
			dtos.add(dto);
		}
		LOG.debug("getAllAddresses, dtos: "+ dtos);
		return dtos;
	}

	private AddressDto convertAddressEntitytoDto(Address address) {
		LOG.debug("convertAddressEntitytoDto, address: "+ address);
		AddressDto dto = new AddressDto();
		dto.setAddressId(address.getAddressId());
		dto.setUserId(address.getUserId());
		dto.setCity(address.getCity());
		dto.setHouse(address.getHouse());
		dto.setLocality(address.getLocality());
//		dto.setMapUrl(address.getAddressId());
		dto.setPinCode(address.getPinCode());
		dto.setState(address.getState());
		dto.setCreatedBy(address.getCreatedBy());
//		dto.setServiceZoneId(address.getServiceZoneId().getServiceZoneId());

		LOG.debug("convertAddressEntitytoDto, dto: "+ dto);
		return dto;
	}

	public List<AddressDto> getAddressByUserId(String userId) {
		LOG.debug("getAddressByUserId, userId: "+ userId);
		List<AddressDto> dtos = new ArrayList<>();
		List<Address> addresses = addressRepo.getAddressByUserId(userId);

		for(Address address : addresses) {

			AddressDto dto = convertAddressEntitytoDto(address);
			dtos.add(dto);
		}
		LOG.debug("getAddressByUserId, dtos: "+ dtos);
		return dtos;
	}

	public AddressDto getAddressByAddressId(String addressId) {
		LOG.debug("getAddressByAddressId, addressId: "+ addressId);
		AddressDto dto = null;
		try {
		Address address = addressRepo.getAddressByAddressId(addressId);
		 dto = convertAddressEntitytoDto(address);
		}
		catch (NullPointerException e) {
			throw new NotFoundException(idNotExist);
		}
		LOG.debug("getAddressByAddressId, dto: "+ dto);
		return dto;

	}




}
