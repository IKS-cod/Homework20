package Homework20.service;

import Homework20.exception.EmployeeAlreadyAddedException;
import Homework20.exception.EmployeeNotFoundException;
import Homework20.exception.EmployeeStorageIsFullException;
import Homework20.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeService {
    private final int maxEmployees = 10;

    private final Map<String, Employee> map = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        String fullName = firstName + lastName;
        System.out.println(fullName);
        if (map.containsKey(fullName)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (map.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        map.put(fullName, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        String fullName = firstName + lastName;
        if (!map.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        map.remove(fullName);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);
        String fullName = firstName + lastName;
        if (!map.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Set<Employee> findAll() {
        Set<Employee> employeeSet=new LinkedHashSet<>();
        for (Employee employee : map.values()) {
            employeeSet.add(employee);
        }return employeeSet;
    }
}
