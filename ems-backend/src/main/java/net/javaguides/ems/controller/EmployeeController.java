package net.javaguides.ems.controller;

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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Build add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/hello")
    public String Hello(){
        return "my first app";
    }

    //Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
       List<EmployeeDto> employees= employeeService.getAllEmployees();
       return ResponseEntity.ok(employees);
    }

    //Building Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable("id")Long employeeId, @RequestBody EmployeeDto updatedEmployee){
      EmployeeDto employeeDto =  employeeService.updateEmployee(employeeId, updatedEmployee);
      return ResponseEntity.ok(employeeDto);
    }

    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted succesfully!");
    }
}