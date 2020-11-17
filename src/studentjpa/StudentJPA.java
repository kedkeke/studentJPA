/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kedke
 */
public class StudentJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student student1 = new Student(1, "kedke", 3.50);
        Student student2 = new Student(2, "kris", 3.75);
        StudentTable.insertStudent(student1);
        StudentTable.insertStudent(student2);
        List<Student> studentList = StudentTable.findAllStudent();
        printAllStudent(studentList);
    }
    
    public static void printAllStudent(List<Student> studentList) {
        for(Student student : studentList) {
           System.out.print(student.getId() + " ");
           System.out.print(student.getName() + " ");
           System.out.println(student.getGpa() + " ");
       }
    }
    
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
