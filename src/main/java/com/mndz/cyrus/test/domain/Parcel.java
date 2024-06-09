package com.mndz.cyrus.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parcel {

    @Min(value = 1, message = "should be greater than 0")
    private double height;

    @Min(value = 1, message = "should be greater than 0")
    private double width;

    @Min(value = 1, message = "should be greater than 0")
    private double length;

    @Min(value = 1, message = "should be greater than 0")
    private double weight;

    private String voucher;

    public double getVolume() {
        return height * width * length;
    }
}
