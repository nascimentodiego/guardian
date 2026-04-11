package br.com.bit.guardian.registration.domain.usecase.login

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CheckLogInInputValidUseCaseTest {

    private lateinit var useCase: CheckLogInInputValidUseCaseImpl

    @Before
    fun setup() {
        useCase = CheckLogInInputValidUseCaseImpl()
    }

    @Test
    fun `invoke with both non-empty fields returns true`() = runTest {
        val result = useCase("user@example.com", "password123").first()
        assertTrue(result)
    }

    @Test
    fun `invoke with empty email returns false`() = runTest {
        val result = useCase("", "password123").first()
        assertFalse(result)
    }

    @Test
    fun `invoke with empty password returns false`() = runTest {
        val result = useCase("user@example.com", "").first()
        assertFalse(result)
    }

    @Test
    fun `invoke with both empty fields returns false`() = runTest {
        val result = useCase("", "").first()
        assertFalse(result)
    }

    @Test
    fun `invoke with whitespace-only values returns true since isNotEmpty passes`() = runTest {
        val result = useCase("   ", "   ").first()
        assertTrue(result)
    }
}
