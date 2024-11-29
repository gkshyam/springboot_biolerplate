package com.sample.springboot_boilerplate.controller;

import com.sample.springboot_boilerplate.dto.EmployeeDTO;
import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/org")
@CrossOrigin(origins = "http://localhost:5173")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        List<OrganizationDTO> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}/product/list")
    public ResponseEntity<List<ProductDTO>> getProductList(@PathVariable("id") Integer id) {
        List<ProductDTO> products = organizationService.getProductList(id);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Integer id) {
        try {
            OrganizationDTO organization = organizationService.getOrganizationById(id);
            return ResponseEntity.ok(organization);
        } catch (ResourceNotFoundException ex) {
            // Send a default 404 Not Found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/{id}/employee/list")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeList(@PathVariable("id") Integer id) {
        List<EmployeeDTO> employee = organizationService.getEmployeeList(id);
        return ResponseEntity.ok(employee);
    }


    @GetMapping("/{id}/employee/{emp_email}/direct_reports")
    public ResponseEntity<List<EmployeeDTO>> getManagerEmails(@PathVariable("id") Integer id , @PathVariable("emp_email") String emp_email ) {
        List<EmployeeDTO> employee = organizationService.getManagerEmails(id, emp_email);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}/employee/{manager_email}/direct_reports_for_employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeEmails(@PathVariable("id") Integer id , @PathVariable("manager_email") String manager_email ) {
        List<EmployeeDTO> employee = organizationService.getEmployeeEmails(id, manager_email);
        return ResponseEntity.ok(employee);
    }


}
