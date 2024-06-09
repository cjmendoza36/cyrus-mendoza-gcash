package com.mndz.cyrus.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mndz.cyrus.test.domain.Parcel;
import com.mndz.cyrus.test.service.ParcelService;
import com.mndz.cyrus.test.service.VoucherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcelController.class)
public class ParcelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelService parcelService;

    @MockBean
    private VoucherService voucherService;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testComputeCost() throws Exception {
        Parcel parcel = new Parcel(10, 10, 10, 5, "MYNT");

        double computedCost = 100.0;
        double discount = 10.0;

        when(parcelService.computeCost(any(Parcel.class))).thenReturn(computedCost);
        when(voucherService.getDiscount("MYNT")).thenReturn(discount);

        mockMvc.perform(post("/v1/parcels/compute-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(parcel))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cost").value(computedCost - discount));
    }

    @Test
    public void testParcelWithInvalidFields() throws Exception {
        Parcel parcel = new Parcel(0, 0, 0, 0, null);

        mockMvc.perform(post("/v1/parcels/compute-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(parcel))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.height").value("should be greater than 0"))
                .andExpect(jsonPath("$.errors.width").value("should be greater than 0"))
                .andExpect(jsonPath("$.errors.length").value("should be greater than 0"))
                .andExpect(jsonPath("$.errors.weight").value("should be greater than 0"));
    }
}

