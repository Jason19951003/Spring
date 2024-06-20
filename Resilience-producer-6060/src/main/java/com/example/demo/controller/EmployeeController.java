package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	// 1. 利用 GlobalExceptionHandler 來處理錯誤
	@GetMapping("/{empId}")
	public Employee getEmployee(@PathVariable Integer empId) {

		if (empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}

		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);

		return emp;

	}
	
	// 當empId 是合法, 但是因為網路或資料庫忙碌, 而發生問題
	@CircuitBreaker(name = "employeeCircuitBreaker", fallbackMethod = "getEmployeeFallback")
	@GetMapping("/breaker/{empId}")
	public Employee getEmployee2(@PathVariable Integer empId) {
		if (empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		
		if (empId >= 10) {
			throw new RuntimeException("資料庫或網路繁忙");
		}
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);

		return emp;
	}
	
	// 這是一個回退方法(Fallback), 當getEmployee 方法發生異常將釣用此方法
	public Employee getEmployeeFallback(Integer empId, Throwable t) {
		if (empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("Fallback");
		emp.setDescription(t.getMessage());
		emp.setSalary(15_0000);
		return emp;
	}

}