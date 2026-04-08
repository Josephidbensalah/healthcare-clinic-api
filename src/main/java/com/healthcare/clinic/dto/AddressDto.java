package com.healthcare.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Address details")
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Street name", example = "P.O. Box 4175")
    private String street;

    @Schema(description = "City", example = "Kuwait")
    private String city;

    @Schema(description = "Region", example = "Safat")
    private String region;
}
