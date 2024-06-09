package com.mndz.cyrus.test.client;

import com.mndz.cyrus.test.domain.VoucherItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "voucherClient", url = "https://app.swaggerhub.com/apis/mynt-iat/mynt-programming-exams/1.1.0#/voucher")
public interface VoucherClient {

    // Won't be able to test as I have no API KEY. See VoucherService.
    @RequestMapping(method = RequestMethod.GET, value = "/voucher/{voucherCode}", produces = "application/json")
    VoucherItem getVoucher(@PathVariable("voucherCode") String voucherCode, @RequestParam("key") String key);
}
