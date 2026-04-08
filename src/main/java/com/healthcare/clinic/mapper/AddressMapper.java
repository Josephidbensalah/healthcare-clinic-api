package com.healthcare.clinic.mapper;

import com.healthcare.clinic.dto.AddressDto;
import com.healthcare.clinic.entities.Address;

public class AddressMapper {

    public static AddressDto mapToAddressDto(Address address, AddressDto addressDto) {
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setRegion(address.getRegion());
        return addressDto;
    }

    public static Address mapToAddress(AddressDto dto, Address address) {
        if (dto == null) return address;

        // update fields that are not null in the DTO
        if (dto.getStreet() != null) {
            address.setStreet(dto.getStreet());
        }
        if (dto.getCity() != null) {
            address.setCity(dto.getCity());
        }
        if (dto.getRegion() != null) {
            address.setRegion(dto.getRegion());
        }
        return address;
    }
}
