package com.scope.socialboardweb.repository.custom;

import com.scope.socialboardweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomUserRepository {

    @Autowired
    private EntityManager em;

    //저장 관련

    public Optional<User> save(User user) {
        em.persist(user);
        return Optional.ofNullable(user);
    }

    //조회 관련
    /**
     * 사용자 ID (PK값 X) 으로 사용자 조회하기
     * @param id 사용자의 pk값
     * @return 조회 결과가 없으면 null 반환
     */
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public List<User> findAll() {
        String jpql = "select u from User u";
        List<User> users = em.createQuery(jpql, User.class)
            .getResultList();
        return users;
    }

    /**
     * 사용자 ID (PK값 X) 으로 사용자 조회하기
     * @param userId 사용자의 로그인 ID
     * @return 조회 결과가 없으면 빈 컬렉션 반환
     */
    public Optional<User> findByUserId(String userId) {
        User user;
        String jpql = "select u from User u where u.userId = :userId";

        TypedQuery<User> userTypedQuery = em.createQuery(jpql, User.class)
            .setParameter("userId", userId);

        try {
            user = userTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    public List<User> findByPassword(String password) {
        String jpql = "select u from User u where u.password = :password";
        List<User> users = em.createQuery(jpql, User.class)
            .setParameter("password", password)
            .getResultList();
        return users;
    }

    /**
     * 로그인 아이디, 비밀번호를 사용하여 사용자 조회
     * @param userId 로그인 아이디
     * @param password 비밀번호
     * @return null 반환 시, 조회 실패
     */
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        User user;
        String jpql = "select u from User u where u.userId=:userId " +
            "and u.password=:password";

        TypedQuery<User> userTypedQuery = em.createQuery(jpql, User.class)
            .setParameter("userId", userId)
            .setParameter("password", password);

        try {
            user = userTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        return Optional.ofNullable(user);
    }


    //삭제 관련

    public void delete(User user) {
        em.remove(user);
    }

    /**
     * 사용자 pk 값으로 삭제
     * @param id DB상의 PK값
     */
    public void deleteById(Long id) {
        String jpql = "delete from User u where u.id=:id";
        em.createQuery(jpql)
            .setParameter("id", id)
            .executeUpdate();
    }
}
