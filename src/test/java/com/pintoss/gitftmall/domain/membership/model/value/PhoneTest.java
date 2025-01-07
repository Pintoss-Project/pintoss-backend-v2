package com.pintoss.gitftmall.domain.membership.model.value;

import com.pintoss.gitftmall.common.exceptions.client.EmptyPhoneException;
import com.pintoss.gitftmall.common.exceptions.client.InvalidPhoneFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PhoneTest {

    @Test
    void validate_ThrowInvalidPhoneException_WhenInvalidFormatPhone() {
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("010-123-1234")); // 가운데 번호가 3자리인 경우
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("01012345678"));  // "-"가 없는 경우
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("010-1234-123"));  // 마지막 번호가 3자리인 경우
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("010-가나다라-마바사하"));  // 숫자가 아닌 경우
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("010-!@#$%-abse"));  // 숫자가 아닌 경우
        Assertions.assertThrowsExactly(InvalidPhoneFormatException.class, () -> new Phone("011-1234-5678"));  // 앞 번호가 010이 아닌 경우
    }

    @Test
    void validate_ThrowEmptyPhoneException_WhenEmptyPhone() {
        Assertions.assertThrowsExactly(EmptyPhoneException.class, () -> new Phone(""));
    }

    @Test
    void validate_ThrowEmptyPhoneException_WhenNullValue() {
        Assertions.assertThrowsExactly(EmptyPhoneException.class, () -> new Phone(null));
    }

    @Test
    void equals_ShouldReturnTrue_WhenValuesAreEqual() {
        Phone origin = new Phone("010-1234-1234");
        Phone target = new Phone("010-1234-1234");

        Assertions.assertTrue(origin.equals(target));
    }

    @Test
    void equals_ShouldReturnFalse_WhenValuesAreDifferent() {
        Phone origin = new Phone("010-1234-1234");
        Phone target = new Phone("010-1234-5678");

        Assertions.assertFalse(origin.equals(target));
    }

}