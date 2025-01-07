package com.pintoss.gitftmall.domain.membership.model.value;

import com.pintoss.gitftmall.common.exceptions.client.EmptyPasswordException;
import com.pintoss.gitftmall.common.exceptions.client.InvalidPasswordFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordTest {

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void validate_ThrowInvalidPasswordException_WhenInvalidFormatPassword(){
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("testPw",encoder)); // 8자 미만
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("testPassword12",encoder)); // 특수 문자가 없는 경우
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("testPassword!@#!",encoder)); // 숫자가 없는 경우
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("!@#!1234567",encoder)); // 영문이 없는 경우
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("천사password1004!@",encoder)); // 한글이 포함되는 경우
        Assertions.assertThrowsExactly(InvalidPasswordFormatException.class, () -> new Password("가나다라마바사123456789abcdefg",encoder)); // 20자 초과
    }

    @Test
    void validate_ThrowEmptyPasswordException_WhenEmptyPassword(){
        Assertions.assertThrowsExactly(EmptyPasswordException.class, () -> new Password("",encoder));
    }

    @Test
    void validate_ThrowEmptyPasswordException_WhenNullValue(){
        Assertions.assertThrowsExactly(EmptyPasswordException.class, () -> new Password(null,encoder));
    }

    @Test
    void equals_ShouldReturnTrue_WhenValuesAreEqual() {
        Password origin = new Password("pintoss1004!",encoder);

        Assertions.assertTrue(origin.matches("pintoss1004!", encoder));
    }

    @Test
    void equals_ShouldReturnFalse_WhenValuesAreDifferent() {
        Password origin = new Password("pintoss1004!",encoder);

        Assertions.assertFalse(origin.matches("change1004!", encoder));
    }

}