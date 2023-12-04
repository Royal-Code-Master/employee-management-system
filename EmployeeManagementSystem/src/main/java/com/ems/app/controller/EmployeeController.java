package com.ems.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.app.pojo.ConfirmationForm;
import com.ems.app.pojo.Employee;
import com.ems.app.repo.EmployeeRepo;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping("/")
	public String getIndex(Model model) {
		List<Employee> employeeList = employeeRepo.findAll();
		model.addAttribute("employees", employeeList);
		model.addAttribute("employee", new Employee());
		model.addAttribute("confirmationForm", new ConfirmationForm());
		return "index";
	}

	@PostMapping("/create")
	public String newEmployee(Employee employee, Model model) {
		model.addAttribute("employee", new Employee());
		String empId = "EMP";
		Random random = new Random();
		long randomNumber = 1000 + random.nextInt(9000);
		empId = empId + randomNumber;
		employee.setId(empId);
		if(employeeRepo.save(employee) != null) {
			model.addAttribute("success", "Employee Added to Our System Successfully");
		}
		
		return "redirect:/";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee, Model model) {
		model.addAttribute("employee", new Employee());
		Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());

		if (existingEmployee.isPresent()) {
			employeeRepo.save(employee);
		} else {
			model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
		}
		return "redirect:/";
	}

	@PostMapping("/remove")
	public String removeEmployee(Employee employee, Model model) {
		model.addAttribute("employee", new Employee());
		Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
		if (existingEmployee.isPresent()) {
			employeeRepo.deleteById(employee.getId());
		}
		return "redirect:/";
	}
	
	@PostMapping("/remove/all")
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) {
        String confirmation = confirmationForm.getConfirmation();
        if ("Yes".equalsIgnoreCase(confirmation)) {
            employeeRepo.deleteAll();
        } else {
        	return "redirect:/";
        }
        return "redirect:/";
    }


}
