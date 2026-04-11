package br.com.bit.guardian.registration.domain.usecase.register

import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PasswordValidationUseCaseTest {

    private lateinit var useCase: PasswordValidationUseCaseImpl

    private val validPassword = "ValidPass1@"
    private val validEmail = "other@example.com"

    @Before
    fun setup() {
        useCase = PasswordValidationUseCaseImpl()
    }

    @Test
    fun `invoke returns exactly 6 validation rules`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertEquals(6, errors.size)
    }

    @Test
    fun `invoke with fully valid password passes all rules`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue("All rules should pass", errors.all { it.isValid })
    }

    // — MIN_MAX_CHARACTER rule —

    @Test
    fun `invoke password shorter than 8 chars fails min-max rule`() = runTest {
        val errors = useCase("Va1@", "Va1@", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.MIN_MAX_CHARACTER }.isValid)
    }

    @Test
    fun `invoke password with exactly 8 chars passes min-max rule`() = runTest {
        val errors = useCase("Valid1@a", "Valid1@a", validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.MIN_MAX_CHARACTER }.isValid)
    }

    @Test
    fun `invoke password with exactly 30 chars passes min-max rule`() = runTest {
        val password = "ValidPass1@ValidPass1@ValidPas" // 30 chars
        val errors = useCase(password, password, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.MIN_MAX_CHARACTER }.isValid)
    }

    @Test
    fun `invoke password longer than 30 chars fails min-max rule`() = runTest {
        val longPass = "ValidPass1@ValidPass1@ValidPass1@" // 32 chars
        val errors = useCase(longPass, longPass, validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.MIN_MAX_CHARACTER }.isValid)
    }

    // — CAPITAL_LETTER rule —

    @Test
    fun `invoke password without uppercase fails capital letter rule`() = runTest {
        val errors = useCase("validpass1@", "validpass1@", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.CAPITAL_LETTER }.isValid)
    }

    @Test
    fun `invoke password with at least one uppercase passes capital letter rule`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.CAPITAL_LETTER }.isValid)
    }

    // — NUMBER_CHARACTER rule —

    @Test
    fun `invoke password without digit fails number character rule`() = runTest {
        val errors = useCase("ValidPass@!", "ValidPass@!", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.NUMBER_CHARACTER }.isValid)
    }

    @Test
    fun `invoke password with at least one digit passes number character rule`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.NUMBER_CHARACTER }.isValid)
    }

    // — SPECIAL_CHARACTER rule —

    @Test
    fun `invoke password without special character fails special character rule`() = runTest {
        val errors = useCase("ValidPass12", "ValidPass12", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.SPECIAL_CHARACTER }.isValid)
    }

    @Test
    fun `invoke password with special character passes special character rule`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.SPECIAL_CHARACTER }.isValid)
    }

    // — DIFFERENT_FROM_EMAIL rule —

    @Test
    fun `invoke password equal to email fails different from email rule`() = runTest {
        val email = "user@example.com"
        val errors = useCase(email, email, email).first()
        assertFalse(errors.first { it.type == PasswordErrorType.DIFFERENT_FROM_EMAIL }.isValid)
    }

    @Test
    fun `invoke password different from email passes different from email rule`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.DIFFERENT_FROM_EMAIL }.isValid)
    }

    @Test
    fun `invoke with empty email fails different from email rule`() = runTest {
        val errors = useCase(validPassword, validPassword, "").first()
        assertFalse(errors.first { it.type == PasswordErrorType.DIFFERENT_FROM_EMAIL }.isValid)
    }

    // — EQUALS_PASSWORD rule —

    @Test
    fun `invoke with matching passwords passes equals password rule`() = runTest {
        val errors = useCase(validPassword, validPassword, validEmail).first()
        assertTrue(errors.first { it.type == PasswordErrorType.EQUALS_PASSWORD }.isValid)
    }

    @Test
    fun `invoke with non-matching passwords fails equals password rule`() = runTest {
        val errors = useCase("ValidPass1@", "DifferentPass2#", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.EQUALS_PASSWORD }.isValid)
    }

    @Test
    fun `invoke with empty passwords fails equals password rule`() = runTest {
        val errors = useCase("", "", validEmail).first()
        assertFalse(errors.first { it.type == PasswordErrorType.EQUALS_PASSWORD }.isValid)
    }
}
