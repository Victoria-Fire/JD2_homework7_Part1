package by.academy.it.reunova;

import by.academy.it.reunova.entity.People;
import by.academy.it.reunova.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class PeopleTest {

    @Test
    public void savePeopleSuccess() {
        People people = People.builder()
                .name("Александр")
                .surname("Александров")
                .patronymic("Александрович")
                .build();

        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(people);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        }
        People peopleEntity = entityManager.find(People.class, 1);
        Assert.assertEquals(people.getName(), peopleEntity.getName());
        Assert.assertEquals(people.getSurname(), peopleEntity.getSurname());
        Assert.assertEquals(people.getPatronymic(), peopleEntity.getPatronymic());

        entityManager.close();
    }
}
