package com.teama.bookingcar.controller;

import com.teama.bookingcar.dto.DriverDto;
import com.teama.bookingcar.entity.Driver;
import com.teama.bookingcar.repository.DriverRepository;
import com.teama.bookingcar.service.IDriverService;
import com.teama.bookingcar.service.impl.DriverService;
import com.teama.bookingcar.service.mapper.impl.DriverMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class DriverControllerTest {

    @Mock
    DriverService driverService;

    DriverController driverController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        driverController = new DriverController(driverService);
    }


    @Test
    public void itShouldCreateDriverSuccess() throws Exception {
        DriverDto dto = new DriverDto();
        given(driverService.create(dto))
                .willReturn(dto);
        ResponseEntity<DriverDto> responseEntity1 = (ResponseEntity<DriverDto>) driverController.create(dto);

        ResponseEntity<DriverDto> responseEntity = ResponseEntity.ok(dto);

        assertThat(responseEntity).isEqualTo(responseEntity);

    }
}
