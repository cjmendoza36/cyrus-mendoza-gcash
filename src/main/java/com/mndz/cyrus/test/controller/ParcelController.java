package com.mndz.cyrus.test.controller;

import com.mndz.cyrus.test.domain.Parcel;
import com.mndz.cyrus.test.service.ParcelService;
import com.mndz.cyrus.test.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "/v1/parcels",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ParcelController {

    private final ParcelService parcelService;
    private final VoucherService voucherService;

    public ParcelController(ParcelService parcelService, VoucherService voucherService) {
        this.parcelService = parcelService;
        this.voucherService = voucherService;
    }

    @PostMapping("/compute-cost")
    public ResponseEntity<Object> computeCost(@Valid @RequestBody Parcel parcel) {
        double cost = parcelService.computeCost(parcel) - voucherService.getDiscount(parcel.getVoucher());
        return ResponseEntity.ok(Collections.singletonMap("cost", cost));
    }

}
