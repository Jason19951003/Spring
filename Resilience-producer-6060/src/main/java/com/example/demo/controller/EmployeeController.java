package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	// 1.利用 GlobalExceptionHandler 來處理錯誤
	@GetMapping("/{empId}")
	public Employee getEmployee1(@PathVariable Integer empId) {
		if(empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);
		return emp;
	}
	
	// 當 empId 是合法, 但是因為網路或資料庫忙碌, 而發生問題
	@CircuitBreaker(name = "employeeCircuitBreaker", fallbackMethod = "getEmployeeFallback")
	@GetMapping("/breaker/{empId}")
	public Employee getEmployee2(@PathVariable Integer empId) {
		// empId 不正確
		if(empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		
		// 假設 empId >= 10 都會發生異常
		if(empId >= 10) {
			throw new RuntimeException("資料庫或網路繁忙");
		}
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);
		return emp;
	}
	
	// Retry
	@Retry(name = "employeeRetry", fallbackMethod = "getEmployeeFallback")
	@GetMapping("/retry/{empId}")
	public Employee getEmployee3(@PathVariable Integer empId) {
		// empId 不正確
		if(empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		
		// 假設 empId >= 10 都會發生異常
		if(empId >= 10) {
			throw new RuntimeException("資料庫或網路繁忙");
		}
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);
		return emp;
	}
	
	// Bilkhead semaphone 隔離信號
	@Bulkhead(name = "employeeBulkhead", type=Bulkhead.Type.SEMAPHORE, fallbackMethod = "getEmployeeFallback")
	@GetMapping("/semaphone/{empId}")
	public Employee getEmployee4(@PathVariable Integer empId) throws InterruptedException {
		// empId 不正確
		if(empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		
		// 假設 empId >= 10 都會發生異常
		if(empId >= 10) {
			throw new RuntimeException("資料庫或網路繁忙");
		}
		
		// 模擬業務處理遞延
		// 給 @Bulkhead 使用
		// 當執行 http://localhost:6060/employee/semaphone/1 過多時就會印出 "Bulkhead call Rejected"
		Thread.sleep(3000);
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);
		return emp;
	}
	
	// Bilkhead threadpool 執行緒池
	@Bulkhead(name = "employeeThreadPoolBulkhead", type=Bulkhead.Type.THREADPOOL,  fallbackMethod = "getCompletableFutureEmployeeFallback")
	@GetMapping("/threadpool/{empId}")
	public CompletableFuture<Employee> getEmployee5(@PathVariable Integer empId) {
		return CompletableFuture.supplyAsync(() -> {
			// empId 不正確
			if(empId <= 0) {
				throw new RuntimeException("員工編號不正確, 無此員工");
			}
			
			// 假設 empId >= 10 都會發生異常
			if(empId >= 10) {
				throw new RuntimeException("資料庫或網路繁忙");
			}
			
			// 模擬業務處理遞延
			// 給 @Bulkhead 使用
			// 當執行 http://localhost:6060/employee/threadpool/1 過多時就會印出 "Bulkhead call Rejected"
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new RuntimeException("業務處理遞延處理失敗");
			}
			
			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpName("John");
			emp.setDescription("Manager");
			emp.setSalary(15_0000);
			return emp;
		});
		
	}
	
	// 利用 TimeLimiter 來處理錯誤, 目的是限制方法執行的最大時間，防止長時間未響應的請求拖垮系統。
	@GetMapping("/timelimiter/{empId}")
	@TimeLimiter(name = "employeeTimeLimiter", fallbackMethod = "getCompletableFutureEmployeeFallback")
	public CompletableFuture<Employee> getEmployee6(@PathVariable Integer empId) {
		return CompletableFuture.supplyAsync(() -> {
			// empId 不正確
			if(empId <= 0) {
				throw new RuntimeException("員工編號不正確, 無此員工");
			}
			
			// 假設 empId >= 10 都會發生異常
			if(empId >= 10) {
				throw new RuntimeException("資料庫或網路繁忙");
			}
			
			// 模擬業務處理遞延(超過 2000ms 就會發生 TimeLimiter's TimeoutException)
			try {
				//Thread.sleep(1000); // 在 2000ms 內沒問題
				Thread.sleep(3000); // 超過 2000ms 會拋出 TimeoutException 例外
			} catch (InterruptedException e) {
				throw new RuntimeException("業務處理遞延處理失敗");
			}
			
			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpName("John");
			emp.setDescription("Manager");
			emp.setSalary(15_0000);
			return emp;
		});
		
	}
	
	
	// 利用 RateLimiter 來處理錯誤
	@GetMapping("/ratelimiter/{empId}")
	@RateLimiter(name = "employeeRateLimiter", fallbackMethod = "getEmployeeFallback")
	public Employee getEmployee7(@PathVariable Integer empId) {
		if(empId <= 0) {
			throw new RuntimeException("員工編號不正確, 無此員工");
		}
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("John");
		emp.setDescription("Manager");
		emp.setSalary(15_0000);
		return emp;
	}
	
	
	// 這是一個回退方法(Fallback), 當 getEmployee 方法發生異常將調用此方法
	public Employee getEmployeeFallback(Integer empId, Throwable t) {
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmpName("Fallback");
		emp.setDescription(t.getMessage());
		emp.setSalary(0);
		return emp;
	}
	
	// 這是一個回退方法(Fallback), 當 getEmployee 方法發生異常將調用此方法
	public CompletableFuture<Employee> getCompletableFutureEmployeeFallback(Integer empId, Throwable t) {
		return CompletableFuture.supplyAsync(() -> {
			Employee emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpName("Fallback");
			emp.setDescription(t.getMessage());
			emp.setSalary(0);
			return emp;
		});
	}
		
	
	
	
	
}
