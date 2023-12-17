// package com.example.employee;

// import org.springframework.web.bind.annotation.*;
// import com.example.service; 

// @RestController
// @RequestMapping("/employees")
// public class EmployeeController {
//     private final EmployeeService employeeService;

//     @Autowired
//     public EmployeeController(EmployeeService employeeService) {
//         this.employeeService = employeeService;
//     }

//     @GetMapping
//     public Iterable<Employee> getAllEmployees() {
//         return employeeRepository.findAll();
//     }

//     @GetMapping("/{id}")
//     public Employee getEmployeeById(@PathVariable Long id) {
//         return employeeRepository.findById(id)
//                 .orElseThrow(() -> new EmployeeNotFoundException(id));
//     }

//     @PostMapping
//     public Employee createEmployee(@RequestBody Employee employee) {
//         return employeeRepository.save(employee);
//     }

//     @PutMapping("/{id}")
//     public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//         return employeeRepository.findById(id)
//                 .map(employee -> {
//                     employee.setFirstName(newEmployee.getFirstName());
//                     employee.setLastName(newEmployee.getLastName());
//                     return employeeRepository.save(employee);
//                 })
//                 .orElseGet(() -> {
//                     newEmployee.setId(id);
//                     return employeeRepository.save(newEmployee);
//                 });
//     }

//     @DeleteMapping("/{id}")
//     public void deleteEmployee(@PathVariable Long id) {
//         employeeRepository.deleteById(id);
//     }

//     @PostMapping("/{employeeId}/assign-department/{departmentId}")
//     public Employee assignEmployeeToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
//         Employee employee = employeeRepository.findById(employeeId)
//                 .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

//         Department department = departmentRepository.findById(departmentId)
//                 .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

//         employee.getDepartments().add(department);
//         employeeRepository.save(employee);

//         return employee;
//     }

//     @GetMapping("/{employeeId}/departments")
//     public Set<Department> getEmployeeDepartments(@PathVariable Long employeeId) {
//         Employee employee = employeeRepository.findById(employeeId)
//                 .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

//         return employee.getDepartments();
//     }
// }
