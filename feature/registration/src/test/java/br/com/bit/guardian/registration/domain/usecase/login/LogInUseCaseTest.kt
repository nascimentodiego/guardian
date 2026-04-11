package br.com.bit.guardian.registration.domain.usecase.login

import br.com.bit.guardian.registration.util.fakes.FakeLoginRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import br.com.bit.guardian.registration.data.repository.model.User as DataUser

class LogInUseCaseTest {

    private lateinit var fakeRepository: FakeLoginRepository
    private lateinit var useCase: LogInUseCaseImpl

    @Before
    fun setup() {
        fakeRepository = FakeLoginRepository()
        useCase = LogInUseCaseImpl(fakeRepository)
    }

    @Test
    fun `invoke maps repository data user to domain user`() = runTest {
        fakeRepository.signInResult = flowOf(
            DataUser(name = "Guardian User", email = "guardian@example.com", photoUrl = "https://photo.url")
        )

        val result = useCase("guardian@example.com", "securePass1@").first()

        assertEquals("Guardian User", result.name)
        assertEquals("guardian@example.com", result.email)
    }

    @Test
    fun `invoke delegates credentials to repository signIn`() = runTest {
        var capturedEmail = ""
        var capturedPassword = ""
        val repositoryWithCapture = object : FakeLoginRepository() {
            override fun signIn(email: String, password: String) = flowOf(
                DataUser(name = "User", email = email, photoUrl = "")
            ).also {
                capturedEmail = email
                capturedPassword = password
            }
        }

        LogInUseCaseImpl(repositoryWithCapture).invoke("user@test.com", "pass@123").first()

        assertEquals("user@test.com", capturedEmail)
        assertEquals("pass@123", capturedPassword)
    }

    @Test
    fun `invoke domain user does not expose photoUrl`() = runTest {
        fakeRepository.signInResult = flowOf(
            DataUser(
                name = "User",
                email = "user@test.com",
                photoUrl = "http://secret.photo"
            )
        )

        val result = useCase("user@test.com", "pass").first()

        assertEquals("User", result.name)
        assertEquals("user@test.com", result.email)
    }
}
