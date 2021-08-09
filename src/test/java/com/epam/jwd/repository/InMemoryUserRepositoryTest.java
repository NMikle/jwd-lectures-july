package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.holder.UserHolder;
import com.epam.jwd.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InMemoryUserRepositoryTest {

    @Mock
    private Iterator<User> userIterator;

    @Mock
    private UserHolder holder;

    @InjectMocks
    private InMemoryUserRepository repo;

    @Test
    public void create_shouldReturnUserWithId_always() {
        final User user = User.createUser("Brad", 43);

        final User actualUser = repo.create(user);

        assertNotNull(actualUser);
        assertNotNull(actualUser.getId());
        verify(holder).save(actualUser);
    }

    @Test
    public void read_shouldReturnUserById_whenUserPresent() {
        final int userId = 4;
        final User user = new User(userId, "Brad", 43);

        when(holder.iterator()).thenReturn(userIterator);
        when(userIterator.hasNext()).thenAnswer(new FirstTrueThenFalse());
        when(userIterator.next()).thenReturn(user);
        User actualUser = null;

        try {
            actualUser = repo.read(userId);
        } catch (UserNotFoundException e) {
            fail(e);
        }
        assertNotNull(actualUser);
        assertEquals(userId, actualUser.getId());

        verify(holder).iterator();
        verify(userIterator).hasNext();
        verify(userIterator).next();

    }

    @Test
    public void read_throwUserNotFoundException_whenUserNotPresent() {

        final int userId = 4;
        final User user = new User(1, "Brad", 43);

        when(holder.iterator()).thenReturn(userIterator);
        when(userIterator.hasNext()).thenAnswer(new FirstTrueThenFalse());
        when(userIterator.next()).thenReturn(user);
        try {
            repo.read(userId);

            fail();
        } catch (UserNotFoundException e) {
            assertNotNull(e);
            assertTrue(e.getMessage().contains(String.valueOf(userId)));

            verify(holder).iterator();
            verify(userIterator, times(2)).hasNext();
            verify(userIterator).next();
        }

    }

    private static class FirstTrueThenFalse implements Answer<Boolean> {

        private int counter = 0;

        @Override
        public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
            return counter++ == 0;
        }
    }

}
