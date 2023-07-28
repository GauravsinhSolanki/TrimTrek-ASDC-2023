package com.ProjectTrial1.Projectdemo1.contact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServicesTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressServices addressServices;

    @Test
    public void testAddAddress() throws Exception {
        AddressDto addressDto = new AddressDto("1", "1", "state", "city", "house", "locality", "pincode", "creator");

        // Create an ArgumentCaptor for Address
        ArgumentCaptor<Address> addressCaptor = ArgumentCaptor.forClass(Address.class);

        when(addressRepository.addAddress(addressCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = addressServices.addAddress(addressDto);

        // Check that the returned Address has the expected values
        assertEquals(addressDto.getUserId(), result.getUserId());
        assertEquals(addressDto.getState(), result.getState());
        assertEquals(addressDto.getCity(), result.getCity());
        assertEquals(addressDto.getHouse(), result.getHouse());
        assertEquals(addressDto.getLocality(), result.getLocality());
        assertEquals(addressDto.getPinCode(), result.getPinCode());
        assertEquals(addressDto.getCreatedBy(), result.getCreatedBy());
    }

    @Test
    public void testGetAllAddresses() {
        Address address = new Address(1, "1", "1", "state", "city", "house", "locality", "pincode", "creator", LocalDateTime.now(), null, null);
        when(addressRepository.getAllAddresses()).thenReturn(Arrays.asList(address));

        List<AddressDto> result = addressServices.getAllAddresses();

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getAddressId());
        assertEquals("1", result.get(0).getUserId());
        assertEquals("state", result.get(0).getState());
        assertEquals("city", result.get(0).getCity());
        assertEquals("house", result.get(0).getHouse());
        assertEquals("locality", result.get(0).getLocality());
        assertEquals("pincode", result.get(0).getPinCode());
        assertEquals("creator", result.get(0).getCreatedBy());
    }

    @Test
    public void testGetAddressByUserId() {
        Address address = new Address(1, "1", "1", "state", "city", "house", "locality", "pincode", "creator", LocalDateTime.now(), null, null);
        when(addressRepository.getAddressByUserId("1")).thenReturn(Arrays.asList(address));

        List<AddressDto> result = addressServices.getAddressByUserId("1");

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getAddressId());
        assertEquals("1", result.get(0).getUserId());
        assertEquals("state", result.get(0).getState());
        assertEquals("city", result.get(0).getCity());
        assertEquals("house", result.get(0).getHouse());
        assertEquals("locality", result.get(0).getLocality());
        assertEquals("pincode", result.get(0).getPinCode());
        assertEquals("creator", result.get(0).getCreatedBy());
    }

    @Test
    public void testGetAddressByAddressId() {
        Address address = new Address(1, "1", "1", "state", "city", "house", "locality", "pincode", "creator", LocalDateTime.now(), null, null);
        when(addressRepository.getAddressByAddressId("1")).thenReturn(address);

        AddressDto result = addressServices.getAddressByAddressId("1");

        assertEquals("1", result.getAddressId());
        assertEquals("1", result.getUserId());
        assertEquals("state", result.getState());
        assertEquals("city", result.getCity());
        assertEquals("house", result.getHouse());
        assertEquals("locality", result.getLocality());
        assertEquals("pincode", result.getPinCode());
        assertEquals("creator", result.getCreatedBy());
    }
}
