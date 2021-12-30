package com.scope.socialboardweb.repository.custom;

import com.scope.socialboardweb.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@Import(CustomUserRepository.class)
//@ActiveProfiles("test")
//@DataJpaTest
@SpringBootTest
@Transactional
class CustomUserRepositoryTest {

    @Autowired
    CustomUserRepository repository;

    @DisplayName("유저 엔티티로 저장 - SUCCESS")
    @Test
    void saveSuccess() {
        //given
        User user = new User("testUniqueId", "testNickname", "testPassword", "010-1234-1234");

        //when
        Optional<User> savedUser = repository.save(user);

        //then
        assertSame(true, savedUser.isPresent());
    }

    @DisplayName("유저 엔티티로 저장 - FAIL")
    @Test
    void saveFail() {
        //given
        User user = new User();

        //when, then
        assertThrows(Exception.class, () -> {
            repository.save(user);
        });

    }

    @DisplayName("저장 및 조회 - findById")
    @Test
    void saveAndFindById() {
        //given
        User user = new User("testUniqueId", "testNickname", "testPassword", "010-1234-1234");

        //when
        Optional<User> savedUser = repository.save(user);

        //then
        assertAll(
            () -> {assertSame(true, savedUser.isPresent());},
            () -> {assertEquals(user, repository.findById(savedUser.get().getId()).get());},
            () -> {assertSame(user, repository.findById(savedUser.get().getId()).get());}
        );
    }

    @DisplayName("저장 및 조회 - findByAccountId")
    @Test
    void saveAndFindByAccountId() {
        //given
        User user = new User("testUniqueId", "testNickname", "testPassword", "010-1234-1234");

        //when
        Optional<User> savedUser = repository.save(user);

        //then
        assertAll(
            () -> {assertSame(true, savedUser.isPresent());},
            () -> {assertEquals(user, repository.findByAccountId(savedUser.get().getAccountId()).get());},
            () -> {assertSame(user, repository.findByAccountId(savedUser.get().getAccountId()).get());}
        );
    }

    @DisplayName("저장 및 조회 - findByPassword")
    @Test
    void saveAndFindByPassword() {
        //given
        String pw = "testPassword";
        User user1 = new User("testUniqueId1", "testNickname1", pw, "010-1234-1234");
        User user2 = new User("testUniqueId2", "testNickname2", pw, "010-1234-1234");
        User user3 = new User("testUniqueId3", "testNickname3", pw, "010-1234-1234");

        //when
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);
        List<User> resultList = repository.findByPassword(pw);

        //then
        assertAll(
            () -> {assertEquals(3, resultList.size());},
            () -> {assertEquals(user1, resultList.get(0));},
            () -> {assertEquals(user2, resultList.get(1));},
            () -> {assertEquals(user3, resultList.get(2));}
        );

    }

    @DisplayName("저장 및 조회 - findByAccountIdAndPassword")
    @Test
    void saveAndFindByAccountIdAndPassword() {
        //given
        String accountId = "testUniqueId";
        String pw = "testPassword";
        User user = new User(accountId, "testNickname", pw, "010-1234-1234");

        //when
        repository.save(user);
        Optional<User> result = repository.findByAccountIdAndPassword(accountId, pw);

        //then

        assertAll(
            ()->{assertSame(true, result.isPresent());},
            ()->{assertEquals(user, result.get());}
        );

    }

    @DisplayName("저장 및 삭제 - 엔티티로 Delete")
    @Test
    void saveAndDeleteByEntity() {
        //given
        User user = new User("testUniqueId", "testNickname", "testPassword", "010-1234-1234");

        //when
        repository.save(user);
        repository.delete(user);

        //then

        assertSame(true, repository.findById(user.getId()).isEmpty());

    }

    @DisplayName("저장 및 삭제 - id(pk)로 Delete")
    @Test
    void saveAndDeleteById() {
        //given
        User user = new User("testUniqueId", "testNickname", "testPassword", "010-1234-1234");

        //when
        repository.save(user);
        repository.deleteById(user.getId());

        //then
        assertSame(true, repository.findById(user.getId()).isEmpty());

    }



}