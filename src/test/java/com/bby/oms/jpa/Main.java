package com.bby.oms.jpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.bby.oms.jpa.domain.Geek;
import com.bby.oms.jpa.domain.Person;

public class Main {
	
	private static final Logger LOGGER = Logger.getLogger("JPA");
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		
		try {
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();
			persistPerson(entityManager);
			persistGeek(entityManager);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		}
	}
	
	private void persistPerson(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Person person = new Person();
			person.setFirstName("Homer");
			person.setLastName("Simpson");
			person.setSalary(new BigDecimal(200000));
			entityManager.persist(person);

			person = new Person();
			person.setFirstName("Ali");
			person.setLastName("Karademir");
			person.setSalary(new BigDecimal(300000));
			entityManager.persist(person);
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	
	private void persistGeek(EntityManager entityManager) {
		EntityTransaction transaction = entityManager.getTransaction();
		try{
			transaction.begin();
			
			Geek geek = new Geek();
			geek.setFirstName("Gavin");
			geek.setLastName("Coffee");
			geek.setFavouriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			
			geek = new Geek();
			geek.setFirstName("Thomas");
			geek.setLastName("Micro");
			geek.setFavouriteProgrammingLanguage("C#");
			entityManager.persist(geek);
			
			geek = new Geek();
			geek.setFirstName("Christian");
			geek.setLastName("Cup");
			geek.setFavouriteProgrammingLanguage("Java");
			entityManager.persist(geek);
			transaction.commit();

			listPeople(entityManager);

		}catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		
	}
	
	private void listPeople(EntityManager entityManager) {
		TypedQuery<Person> query = entityManager.createQuery("from Person", Person.class);
		
		List<Person> resultList = query.getResultList();
		System.out.println("List of People:");
		for(Person person : resultList) {
			StringBuilder sb = new StringBuilder();
			sb.append(person.getFirstName()).append(" ").append(person.getLastName());
			if (person instanceof Geek) {
				Geek geek = (Geek)person;
				sb.append(" ").append(geek.getFavouriteProgrammingLanguage());
			}
			System.out.println(sb.toString());
		}
	}
}
