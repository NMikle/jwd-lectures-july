package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.holder.UserHolder;
import com.epam.jwd.model.OldUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InMemoryOldUserRepositoryTest {

    @Mock
    private Iterator<OldUser> userIterator;

    @Mock
    private UserHolder holder;

    @InjectMocks
    private InMemoryUserRepository repo;

    @Test
    public void create_shouldReturnUserWithId_always() {
        final OldUser oldUser = OldUser.createUser("Brad", 43);

        final Optional<OldUser> actualUser = repo.create(oldUser);

        assertTrue(actualUser.isPresent());
        assertNotNull(actualUser.get().getId());
        verify(holder).save(actualUser.get());
    }

    @Test
    public void read_shouldReturnUserById_whenUserPresent() {
        final int userId = 4;
        final OldUser oldUser = new OldUser(userId, "Brad", 43);

        when(holder.iterator()).thenReturn(userIterator);
        when(userIterator.hasNext()).thenAnswer(new FirstTrueThenFalse());
        when(userIterator.next()).thenReturn(oldUser);
        Optional<OldUser> actualUser = Optional.empty();

        try {
            actualUser = repo.read(userId);
        } catch (UserNotFoundException e) {
            fail(e);
        }
        assertTrue(actualUser.isPresent());
        assertEquals(userId, actualUser.get().getId());

        verify(holder).iterator();
        verify(userIterator).hasNext();
        verify(userIterator).next();

    }

    @Test
    public void read_throwUserNotFoundException_whenUserNotPresent() {

        final int userId = 4;
        final OldUser oldUser = new OldUser(1, "Brad", 43);

        when(holder.iterator()).thenReturn(userIterator);
        when(userIterator.hasNext()).thenAnswer(new FirstTrueThenFalse());
        when(userIterator.next()).thenReturn(oldUser);
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
