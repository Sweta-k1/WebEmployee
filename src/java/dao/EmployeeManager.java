
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import worker.Employee;


@Stateless
public class EmployeeManager {

  @PersistenceContext
  EntityManager em;
  
  public void addEmployee (Employee e){
      em.persist(e);
  } 
  
  public void removeEmployee (long id){
      Employee p = em.find(Employee.class, id);
        em.remove(em.merge(p));
  }
  
  public void titleUpdate(Long id, String title) {
        Employee p = em.find(Employee.class, id);

        p.setTitle(title);

    }
  
  public List<Employee> getAllEmployees () {
      Query q = em.createQuery("SELECT e FROM Employee e");
      return q.getResultList();
  }
  
  public Employee findById(long id) {
        Employee p = em.find(Employee.class, id);
        return p;

    }
}
