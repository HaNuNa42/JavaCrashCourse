package com.hibernateDemo;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {

		SessionFactory factory = new Configuration() // session factory konfigürasyonunu yapıyoruz.
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();

		//Session aslında Unit of Work tasarım deseninin uygulanmış halidir.
		Session session = factory.getCurrentSession(); // sessionfactory de bir oturum almamız gerek session oluşturuyoruz.
		try {
			session.beginTransaction(); // transaction.a başlayacağımızı söylüyoruz

			//HQL - Hibernate Query Language
			//select * from city
			// List<City> cities = session.createQuery("from City").getResultList(); //sonucu list olarak döndüreceğinden getresultlist metodu ile döndürüyoruz. listeyi bas anlamında kullanılır.
			// List<City> cities = session.createQuery("from City c where c.countryCode='TUR' OR c.countryCode='USA'").getResultList(); //c diye bir alias bıraktık istediğimiz harfi bırakabiliriz. Hibernate koşullu sorgular yazacağımızda alias ile çalışır.
			// List<City> cities = session.createQuery("from City c where c.countryCode='TUR' AND c.district='Konya'").getResultList();
			// List<City> cities = session.createQuery("from City c where c.name LIKE '%kar%'").getResultList();
			//ASC - Ascending yani a.dan z.ye sıralama. Order by'ın default olan hali asc.dir. DESC - Descending yani z.den a.ya sıralama
			//List<City> cities = session.createQuery("from City c ORDER BY c.name ASC").getResultList();
			
			/*for(city:cities) {
				System.out.println(city.getName());
			}*/
			
			/*
			  List<String> countryCodes = session.createQuery("select c.countryCode from City c GROUP BY c.countryCode").getResultList();
			  
			  for(String countryCode:countryCodes) { System.out.println(countryCode); }
			 */
			
			
			
			//INSERT 
//			City city = new City();
//			city.setName("konya 5");
//			city.setCountryCode("TUR");
//			city.setDistrict("iç anadolu");
//			city.setPopulation(50000000);
//			session.save(city);
			
			
			//UDPATE
//			City city = session.get(City.class, 4084);
//			city.setPopulation(4000);
//			System.out.println(city.getName());
//			session.save(city);
			
			
			//DELETE
			City city = session.get(City.class, 4084);
			session.delete(city);
			
			
			
			
			session.getTransaction().commit(); // mevcut olan bu transaction.u veritabanına commit et yani bas diyoruz.
			System.out.println("şehir silndi");
			
		} finally {
			factory.close(); // sessionfactory.mizi kapatıyoruz
		}
	}
}
