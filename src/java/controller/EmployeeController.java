package controller;

import dao.EmployeeManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import worker.Employee;
import worker.Gender;

@Named(value = "employeeController")
@RequestScoped
public class EmployeeController {

    @Inject
    EmployeeManager emplomana;

    private String name;
    private int salary;
    private Gender gender;
    private String title;
    private List<Employee> allEmployees;
    private Employee employee;
    private long employeeID;

    private long Average;
    private long minSalary;
    private long maxSalary;
    private long procentMale;
    private long procentFemale;

    // ********METHODS**********
    public void submit() {
        Employee e = new Employee(name, salary, gender, title);
        emplomana.addEmployee(e);
        allEmployees = emplomana.getAllEmployees();
    }

    public void delete() {
        emplomana.removeEmployee(employeeID);
    }

    public void update() {
        emplomana.titleUpdate(employeeID, title);

    }

    @PostConstruct
    public void fillEmployeeList() {
        this.allEmployees = emplomana.getAllEmployees();
    }

    public String employeeSearch() {
        this.employee = emplomana.findById(employeeID);
        return "employeeById";
    }

    public void salaryAverage() {
        long salaryTotalt = 0;
        for (Employee e : allEmployees) {
            salaryTotalt += e.getSalary();
        }
        Average = salaryTotalt / allEmployees.size();
    }

    public void minimunSalary() {
        minSalary = allEmployees.get(0).getSalary();
        for (Employee e : allEmployees) {
            if (e.getSalary() < minSalary) {
                minSalary = e.getSalary();
            }
        }
    }

    public void maximunSalary() {
        maxSalary = 0;
        for (Employee e : allEmployees) {
            if (e.getSalary() > maxSalary) {
                maxSalary = e.getSalary();
            }
        }
    }

    public void maleProcent() {
        long maleTotalt = 0;
        for (Employee e : allEmployees) {
            if (e.getGender().equals(Gender.Male)) {
                maleTotalt++;
            }
        }
        procentMale = (long) (((double) maleTotalt / allEmployees.size()) * 100);
    }
    
    public void femaleProcent() {
        long femaleTotalt = 0;
        for (Employee e : allEmployees) {
            if (e.getGender().equals(Gender.Female)) {
                femaleTotalt++;
            }
        }
        procentFemale = (long) (((double) femaleTotalt / allEmployees.size()) * 100);
    }
    // ********GETTERS AND SETTERS**********

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAverage() {
        return Average;
    }

    public void setAverage(long Average) {
        this.Average = Average;
    }

    public long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(long minSalary) {
        this.minSalary = minSalary;
    }

    public long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public long getProcentFemale() {
        return procentFemale;
    }

    public void setProcentFemale(long procentFemale) {
        this.procentFemale = procentFemale;
    }

    public long getProcentMale() {
        return procentMale;
    }

    public void setProcentMale(long procentMale) {
        this.procentMale = procentMale;
    }

    // ********CONSTRUCTOR**********
    public EmployeeController() {
    }

}
