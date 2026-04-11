package br.com.bit.guardian.registration.domain.usecase.login

import br.com.bit.guardian.registration.util.fakes.FakeLoginRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class IsUserLoggedUseCaseTest {

    private lateinit var fakeRepository: FakeLoginRepository
    private lateinit var useCase: IsUserLoggedUseCaseImpl

    @Before
    fun setup() {
        fakeRepository = FakeLoginRepository()
        useCase = IsUserLoggedUseCaseImpl(fakeRepository)
    }

    @Test
    fun `invoke returns true when user is logged in`() = runTest {
        fakeRepository.isUserLoggedResult = flowOf(true)

        val result = useCase().first()

        assertTrue(result)
    }

    @Test
    fun `invoke returns false when user is not logged in`() = runTest {
        fakeRepository.isUserLoggedResult = flowOf(false)

        val result = useCase().first()

        assertFalse(result)
    }

    @Test
    fun `invoke delegates to repository isUserLogged`() = runTest {
        var repositoryCalled = false
        val repositoryWithCapture = object : FakeLoginRepository() {
            override fun isUserLogged() = flowOf(true).also { repositoryCalled = true }
        }

        IsUserLoggedUseCaseImpl(repositoryWithCapture).invoke().first()

        assertTrue(repositoryCalled)
    }
}
