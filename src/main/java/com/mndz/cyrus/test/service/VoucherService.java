package com.mndz.cyrus.test.service;

import com.mndz.cyrus.test.client.VoucherClient;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {

    private final VoucherClient voucherClient;

    public VoucherService(VoucherClient voucherClient) {
        this.voucherClient = voucherClient;
    }

    public double getDiscount(String voucher) {
        // Won't be able to test as I have no API KEY
        // Commenting out for now

        /*
        VoucherItem responseEntity = voucherClient.getVoucher(voucher, "API-KEY-HERE");

        if (responseEntity.getError() == null || responseEntity.getError().isEmpty()) {
            return responseEntity.getDiscount();
        }
        */

        return 0.0;
    }
}
