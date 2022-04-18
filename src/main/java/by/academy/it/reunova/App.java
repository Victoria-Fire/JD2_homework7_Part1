package by.academy.it.reunova;

import by.academy.it.reunova.entity.Address;
import by.academy.it.reunova.entity.People;
import by.academy.it.reunova.util.HibernateUtil;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        Address address1 = Address.builder()
                .city("Минск")
                .street("Куйбышева")
                .house(46)
                .build();

        Address address2 = Address.builder()
                .city("Гомель")
                .street("Строителей")
                .house(17)
                .build();

        People people1 = People.builder()
                .name("Петя")
                .surname("Петров")
                .patronymic("Петрович")
                .build();

        People people2 = People.builder()
                .name("Иван")
                .surname("Иванов")
                .patronymic("Иванович")
                .build();

        People people3 = People.builder()
                .id(2)
                .name("Федор")
                .surname("Федоров")
                .patronymic("Федорович")
                .build();

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.persist(people1);
        entityManager.persist(people2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        System.out.println(entityManager.find(Address.class, 1));
        System.out.println(entityManager.find(People.class, 2));
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Address adr1 = entityManager.find(Address.class, 2);
        adr1.setCity("Лондон");
        entityManager.merge(people3);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Address adr2 = entityManager.find(Address.class, 1);
        People peop = entityManager.find(People.class, 2);
        entityManager.remove(adr2);
        entityManager.remove(peop);
        entityManager.getTransaction().commit();

        entityManager.close();
        HibernateUtil.close();
    }
}
