package net.javaguides.ems.controller;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping ("/api/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Build add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        log.info("Creating a new employee: {}",employeeDto);
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        log.info("Employee created succesfully with ID: {}",savedEmployee.getId());
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/hello")
    public String Hello(){
        return "my first app";
    }

    //Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable("id") Long employeeId){
        log.info("Get employee ID: {}",employeeId);
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        log.info("Employee Details: {}",employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        log.info("Fetching all employee details" );
       List<EmployeeDto> employees= employeeService.getAllEmployees();
        log.info("Total Employees fetched: {}", employees.size());
       return ResponseEntity.ok(employees);
    }

    //Building Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id")Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        log.info("Updating employee: {}", updatedEmployee);
      EmployeeDto employeeDto =  employeeService.updateEmployee(employeeId, updatedEmployee);
      log.info("Employee updated succesfully: {}", employeeDto);
      return ResponseEntity.ok(employeeDto);
    }

    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Long employeeId){
        log.info("Deleting Employee with ID: {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        log.info("Employee with ID: {} has been deleted succesfully!", employeeId);
        return ResponseEntity.ok("Employee deleted succesfully!");
    }
}
