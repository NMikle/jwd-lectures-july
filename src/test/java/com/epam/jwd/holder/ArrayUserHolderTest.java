package com.epam.jwd.holder;

import com.epam.jwd.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ArrayUserHolderTest {

    private static final User TEST_USER = new User(1, "Ann", 33);

    private UserHolder holder;

    @BeforeEach
    public void setUp() {
        System.out.println("before");
        holder = new ArrayUserHolder();
    }

    @AfterEach
    void tearDown() {
        System.out.println("after");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after class");
    }

    @Test
    public void save_shouldReturnPositiveIndex_whenHolderEmpty() {
        System.out.println("index check");
        final int resultId = holder.save(TEST_USER);

        assertTrue(resultId > 0);
    }

    @Test
    public void retrieve_shouldReturnSameUser_whenIndexCorrect() {
        System.out.println("retrieve check");
        final int userId = holder.save(TEST_USER);

        final User actualUser = holder.retrieve(userId);

        assertNotNull(actualUser, "actual user should not be null");
        assertSame(TEST_USER, actualUser);
    }

    /*@ParameterizedTest
    @MethodSource("stringIntProvider")
    public void parameterizedTest(User userToSave, int expectedId) {
        final int savedIndex = holder.save(userToSave);
        assertSame(expectedId, savedIndex);
    }


    private static Stream<Arguments> stringIntProvider() {
        return Stream.of(
                arguments(User.createUser("Bob", 22), 1),
                arguments(User.createUser("Bob", 22), 2),
                arguments(User.createUser("Bob", 22), 3),
                arguments(User.createUser("Bob", 22), 4),
                arguments(User.createUser("Bob", 22), 5)
        );
    }*/

}
