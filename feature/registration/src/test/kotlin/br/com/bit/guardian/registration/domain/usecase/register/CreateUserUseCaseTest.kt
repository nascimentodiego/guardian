package br.com.bit.guardian.registration.domain.usecase.register

import br.com.bit.guardian.registration.util.fakes.FakeLoginRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import br.com.bit.guardian.registration.data.repository.model.User as DataUser

class CreateUserUseCaseTest {

    private lateinit var fakeRepository: FakeLoginRepository
    private lateinit var useCase: CreateUserUseCaseImpl

    @Before
    fun setup() {
        fakeRepository = FakeLoginRepository()
        useCase = CreateUserUseCaseImpl(fakeRepository)
    }

    @Test
    fun `invoke maps repository data user to domain user`() = runTest {
        fakeRepository.createUserResult = flowOf(
            DataUser(name = "New User", email = "new@example.com", photoUrl = "https://photo.url")
        )

        val result = useCase("new@example.com", "SecurePass1@").first()

        assertEquals("New User", result.name)
        assertEquals("new@example.com", result.email)
    }

    @Test
    fun `invoke delegates credentials to repository createUser`() = runTest {
        var capturedEmail = ""
        var capturedPassword = ""
        val repositoryWithCapture = object : FakeLoginRepository() {
            override fun createUser(email: String, password: String) = flowOf(
                DataUser(name = "User", email = email, photoUrl = "")
            ).also {
                capturedEmail = email
                capturedPassword = password
            }
        }

        CreateUserUseCaseImpl(repositoryWithCapture).invoke("signup@test.com", "P@ssw0rd").first()

        assertEquals("signup@test.com", capturedEmail)
        assertEquals("P@ssw0rd", capturedPassword)
    }

    @Test
    fun `invoke domain user does not expose photoUrl`() = runTest {
        fakeRepository.createUserResult = flowOf(
            DataUser(name = "User", email = "user@test.com", photoUrl = "http://private.photo")
        )

        val result = useCase("user@test.com", "pass").first()

        assertTrue("Domain User should only have name and email", result.name == "User")
    }
}
