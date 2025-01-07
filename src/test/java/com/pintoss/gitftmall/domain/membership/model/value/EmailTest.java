package com.pintoss.gitftmall.domain.membership.model.value;

import com.pintoss.gitftmall.common.exceptions.client.EmptyEmailException;
import com.pintoss.gitftmall.common.exceptions.client.InvalidEmailFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    void givenInCorrectFormattingEmail_whenCreate_thenThrowInvalidEmailFormatException() {
        Assertions.assertThrowsExactly(InvalidEmailFormatException.class, () -> new Email("test@naver"));
        Assertions.assertThrowsExactly(InvalidEmailFormatException.class, () -> new Email("testemail.com"));
        Assertions.assertThrowsExactly(InvalidEmailFormatException.class, () -> new Email("testemail.com"));
    }

    @Test
    void givenEmptyValue_whenCreate_thenThrowEmptyEmailException() {
        Assertions.assertThrowsExactly(EmptyEmailException.class, () -> new Email(""));
    }

    @Test
    void givenNullValue_whenCreate_thenThrowEmptyEmailException() {
        Assertions.assertThrowsExactly(EmptyEmailException.class, () -> new Email(null));
    }

    @Test
    void equals_ShouldReturnTrue_whenValuesAreEqual(){
        Email origin = new Email("test@email.com");
        Email target = new Email("test@email.com");

        Assertions.assertTrue(origin.equals(target));
    }

    @Test
    void equals_ShouldReturnFalse_whenValuesAreDifferent(){
        Email origin = new Email("test@email.com");
        Email target = new Email("change@email.com");

        Assertions.assertFalse(origin.equals(target));
    }

}