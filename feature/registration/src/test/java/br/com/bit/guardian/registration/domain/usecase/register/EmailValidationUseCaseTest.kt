package br.com.bit.guardian.registration.domain.usecase.register

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class EmailValidationUseCaseTest {

    private lateinit var useCase: EmailValidationUseCaseImpl

    @Before
    fun setup() {
        useCase = EmailValidationUseCaseImpl()
    }

    @Test
    fun `invoke with standard valid email returns true`() = runTest {
        assertTrue(useCase("user@example.com").first())
    }

    @Test
    fun `invoke with subdomain email returns true`() = runTest {
        assertTrue(useCase("user@mail.example.com").first())
    }

    @Test
    fun `invoke with plus sign in local part returns true`() = runTest {
        assertTrue(useCase("user+tag@example.com").first())
    }

    @Test
    fun `invoke with dot in local part returns true`() = runTest {
        assertTrue(useCase("first.last@example.com").first())
    }

    @Test
    fun `invoke with underscore in local part returns true`() = runTest {
        assertTrue(useCase("user_name@example.com").first())
    }

    @Test
    fun `invoke with empty string returns false`() = runTest {
        assertFalse(useCase("").first())
    }

    @Test
    fun `invoke with email missing at sign returns false`() = runTest {
        assertFalse(useCase("userexample.com").first())
    }

    @Test
    fun `invoke with email missing domain returns false`() = runTest {
        assertFalse(useCase("user@").first())
    }

    @Test
    fun `invoke with email missing local part returns false`() = runTest {
        assertFalse(useCase("@example.com").first())
    }

    @Test
    fun `invoke with space in email returns false`() = runTest {
        assertFalse(useCase("user @example.com").first())
    }

    @Test
    fun `invoke with only at sign returns false`() = runTest {
        assertFalse(useCase("@").first())
    }
}
