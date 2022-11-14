package com.study.hellospring.entitymanager_example.repository;

import com.study.hellospring.entitymanager_example.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // Create, Update
    public void save(User user) {
        em.persist(user);
    }

    // Delete
    public void remove(Long id) {
        em.remove(findById(id));
    }

    // Read
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User findByName(String findName) {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :findName", User.class)
                .setParameter("findName", findName)
                .getSingleResult();
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
