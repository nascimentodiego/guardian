package br.com.bit.guardian.registration.data.repository

import br.com.bit.guardian.registration.data.datasource.remote.response.UserLoginResponse
import br.com.bit.guardian.registration.util.fakes.FakeLoginDataSource
import br.com.bit.guardian.registration.util.fakes.FakeUserPreferencesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private lateinit var fakeDataSource: FakeLoginDataSource
    private lateinit var fakeUserPreferences: FakeUserPreferencesDataSource
    private lateinit var repository: LoginRepositoryImpl

    @Before
    fun setup() {
        fakeDataSource = FakeLoginDataSource()
        fakeUserPreferences = FakeUserPreferencesDataSource()
        repository = LoginRepositoryImpl(fakeDataSource, fakeUserPreferences)
    }

    // — signIn —

    @Test
    fun `signIn returns mapped user on success`() = runTest {
        fakeDataSource.signInResult = flowOf(
            UserLoginResponse(uuid = "123", name = "Test User", email = "test@example.com", photoUrl = "photo.url")
        )

        val user = repository.signIn("test@example.com", "password").first()

        assertEquals("Test User", user.name)
        assertEquals("test@example.com", user.email)
    }

    @Test
    fun `signIn preserves photoUrl in data model`() = runTest {
        fakeDataSource.signInResult = flowOf(
            UserLoginResponse(uuid = "1", name = "User", email = "user@test.com", photoUrl = "https://photo.url")
        )

        val user = repository.signIn("user@test.com", "pass").first()

        assertEquals("https://photo.url", user.photoUrl)
    }

    // — createUser —

    @Test
    fun `createUser returns mapped user on success`() = runTest {
        fakeDataSource.createUserResult = flowOf(
            UserLoginResponse(uuid = "456", name = "New User", email = "new@example.com", photoUrl = "")
        )

        val user = repository.createUser("new@example.com", "password").first()

        assertEquals("New User", user.name)
        assertEquals("new@example.com", user.email)
    }

    @Test
    fun `createUser persists user data in preferences`() = runTest {
        fakeDataSource.createUserResult = flowOf(
            UserLoginResponse(uuid = "789", name = "Pref User", email = "pref@example.com", photoUrl = "photo")
        )

        repository.createUser("pref@example.com", "password").first()

        val stored = fakeUserPreferences.getUserPreferences().first()
        assertEquals("Pref User", stored.name)
        assertEquals("pref@example.com", stored.email)
        assertEquals("789", stored.uuid)
    }

    // — isUserLogged —

    @Test
    fun `isUserLogged returns true when user is authenticated`() = runTest {
        fakeDataSource.isUserLoggedResult = flowOf(true)

        val result = repository.isUserLogged().first()

        assertTrue(result)
    }

    @Test
    fun `isUserLogged returns false when user is not authenticated`() = runTest {
        fakeDataSource.isUserLoggedResult = flowOf(false)

        val result = repository.isUserLogged().first()

        assertFalse(result)
    }

    // — signOut —

    @Test
    fun `signOut completes without emitting error`() = runTest {
        fakeDataSource.signOutResult = flowOf(Unit)

        val result = repository.signOut().first()

        assertEquals(Unit, result)
    }
}
