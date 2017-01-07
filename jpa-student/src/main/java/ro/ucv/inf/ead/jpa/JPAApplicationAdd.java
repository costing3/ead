package ro.ucv.inf.ead.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ro.ucv.inf.ead.jpa.model.Address;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Phone;
import ro.ucv.inf.ead.jpa.model.Student;

public class JPAApplicationAdd {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();

    try {
      System.out.println("Adding students - Begin");
      em.getTransaction().begin();
      Student student = new Student("Mihai", "Computer Science");

      Address address = new Address();
      address.setCity("Craiova");
      address.setAddress("A.I. Cuza, 13");

      em.persist(address);
      student.setAddress(address);

      Course course1 = new Course("Data Mining");
      em.persist(course1);

      Course course2 = new Course("Oriented Object Programming");
      em.persist(course2);

      student.getCourses().add(course1);
      student.getCourses().add(course2);

      em.persist(student);

      Phone phone1 = new Phone();
      phone1.setNumber("222222");
      phone1.setType("Vodafone");
      phone1.setStudent(student);
      em.persist(phone1);

      em.getTransaction().commit();
      System.out.println("Adding students - End");

    } catch (Exception e) {
      System.err.println("Adding students - Error: " + e.getMessage());
      // e.printStackTrace();
      em.getTransaction().rollback();
    } finally {
      em.close();
      emf.close();
    }
   
  }

}
