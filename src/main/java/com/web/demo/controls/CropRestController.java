package com.web.demo.controls;

import com.web.demo.dtos.*;
import com.web.demo.services.CropService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("crops")
public class CropRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropRestController.class);

    CropService cropService;

    @Autowired
    public void setCropService(CropService cropService) {
        this.cropService = cropService;
    }

    @Value("${app.title}")
    private String appTitle;

    @Value("${user.fullname}")
    private String userName;

    @Autowired
    private EmployeeDTO employeeProperties;

    @Autowired
    private WordPressProperties wordPressProperties;

    @Autowired
    private GlobalProperties globalProperties;

    @Autowired
    private ReadDatasource readDatasource;

    @Autowired
    private EmployeeDTO employeeDTO;

    @GetMapping(value = "/hello")
    public String helloWorld() {
        LOGGER.info("CropRestController helloWorld::");
        return "Hello World";
    }

    @GetMapping(value = "/empProp")
    public EmployeeDTO readEmpProp() {
        LOGGER.info("CropRestController readEmpProp::");
        return employeeDTO;
    }
    @GetMapping(value = "/readProp")
    public ReadProperties readProperties() {
        LOGGER.info("CropRestController readProperties::"+appTitle);
        ReadProperties readProperties = new ReadProperties();
        readProperties.setGlobalProperties(globalProperties);
        readProperties.setWordPressProperties(wordPressProperties);
        readProperties.setReadDatasource(readDatasource);
        return readProperties;
    }

    @GetMapping(value = "/readProp1")
    public String readProp() {
        LOGGER.info("CropRestController readProp::"+appTitle);
        String name = employeeProperties.getName();
        String dept = employeeProperties.getDept();

        System.out.println(globalProperties);
        System.out.println(wordPressProperties);
        return "Reading Properties Name="+name+"::dept="+dept;
    }

    @GetMapping(value = "/readPropDynamic")
    public String readPropertiesDynamic() {
        LOGGER.info("CropRestController readPropertiesDynamic::"+appTitle);
        return "Reading Properties Dynamic appTitle="+userName;
    }


    @GetMapping(value = "/readCrop")
    public ResponseEntity<List<CropInsuranceDTO>> readCropCSV() {
        LOGGER.info("CropRestController readCropCSV::");
        CompletableFuture<List<CropInsuranceDTO>> cropFuture =
                supplyAsync(() -> cropService.readCropDetails());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cropFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
