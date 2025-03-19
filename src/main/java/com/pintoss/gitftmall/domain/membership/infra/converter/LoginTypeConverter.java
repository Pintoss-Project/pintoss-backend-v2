package com.pintoss.gitftmall.domain.membership.infra.converter;

import com.pintoss.gitftmall.domain.membership.domain.vo.LoginType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginTypeConverter implements Converter<String, LoginType> {
    @Override
    public LoginType convert(String source) {
        return LoginType.fromString(source);
    }
}
