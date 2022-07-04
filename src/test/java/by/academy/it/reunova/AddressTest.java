package by.academy.it.reunova;

import by.academy.it.reunova.entity.Address;
import by.academy.it.reunova.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class AddressTest {

    @Test
    public void saveAddressSuccess() {
        Address address = Address.builder()
                .city("Москва")
                .street("Саянская")
                .house(13)
                .build();

        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            entityManager.getTransaction().rollback();
        }
        Address addressEntity = entityManager.find(Address.class, 1);
        Assert.assertEquals(address.getCity(), addressEntity.getCity());
        Assert.assertEquals(address.getStreet(), addressEntity.getStreet());
        Assert.assertEquals(address.getHouse(), addressEntity.getHouse());

        entityManager.close();
    }
}
