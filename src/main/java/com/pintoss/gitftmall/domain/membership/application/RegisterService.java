package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.core.exceptions.client.DuplicateEmailException;
import com.pintoss.gitftmall.core.exceptions.client.DuplicatePhoneException;
import com.pintoss.gitftmall.domain.membership.application.command.RegisterServiceCommand;
import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.vo.Email;
import com.pintoss.gitftmall.domain.membership.domain.vo.Phone;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.membership.domain.vo.UserRole;
import com.pintoss.gitftmall.domain.membership.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

/*
    회원가입 기능 중 이메일, 전화번호 중복 검증에 대한 구현 코드를 도메인 서비스로 분리해야하는 것인지에 대해서 생각해봤다.
    우선, 도메인 서비스는 무엇이고, 왜 분리해야하는 것일까 ?

    도메인 서비스는 비즈니스 로직을 처리하기 위한 서비스 객체이다. 분리하는 이유는 애플리케이션 서비스는 유스케이스의 구현, 흐름 제어를 담당한다.
    그렇기 때문에 애플리케이션 서비스에서 비즈니스 로직을 구현하면 안되는 것이다. 그리고 핵심 규칙이 도메인 객체 내부에서 처리를 못하거나 여러 도메인 객체의
    협력이 필요한 경우에 분리한다.

    그래서 나는 우선적으로 애플리케이션 서비스에서 흐름제어를 간단하게 하기 위해 이메일, 전화번호 중복 검증 구현 코드를 "UserValidationSerivce"로 분리하려고했다.
    그리고 이는 DB(외부)에 접근해야한다. 그래서 뭔가 이상하다는 생각이 들었다. 왜냐하면 비즈니스 로직은 외부와 독립적이어야하는데 외부에 의존하고있기 때문이다.
    그래서 다시 코드를 애플리케이션에 작성하였다.

    고민해본 결과 유효성 검증은 비즈니스 규칙보다는 기술적 검증이라는 것을 알게되었다. 아래는 비즈니스 규칙과 기술적 검증에 대한 설명이다.

    1. 비즈니스 규칙
        - 정의
            - 비즈니스 규칙은 도메인(비즈니스 모델)의 고유한 정책, 제약, 프로세스를 정의합니다.
            - 이 규칙은 도메인 전문가(비즈니스 이해관계자)와 논의하여 도출되며, 비즈니스의 성공적 운영을 위해 반드시 지켜야 하는 논리입니다.
        - 특징
            - 도메인 중심: 특정 도메인의 본질을 반영하고, 그 도메인이 작동하는 방식을 정의합니다.
            - 위반 시 도메인 상태 손상: 비즈니스 규칙을 위반하면 도메인의 일관성이나 신뢰성을 잃게 됩니다.
            - 도메인 전문가의 요구사항: 이 규칙은 주로 비즈니스 요구사항으로부터 도출됩니다.
            - 재사용 가능성: 특정 유스케이스에 국한되지 않고, 도메인 전반에서 적용될 가능성이 높습니다.
    2. 기술적 검증
        - 정의
            - 기술적 검증은 주로 데이터의 무결성, 일관성, 유효성을 보장하기 위한 검증입니다.
            - 이는 데이터베이스 상태, 입력 데이터 형식, 외부 시스템과의 상호작용 등을 확인하는 작업입니다.
        - 특징
            - 기술 중심: 도메인의 비즈니스 로직보다는, 데이터의 품질이나 시스템 동작을 보장하기 위한 검증에 초점이 맞춰져 있습니다.
            - 도메인 상태와 무관: 기술적 검증이 실패해도 반드시 도메인의 일관성을 손상시키는 것은 아닙니다.
            - 구현의 필요성: 비즈니스 로직보다는 기술적 제약 사항(예: 데이터베이스 무결성, API 계약 등)에서 발생합니다.
            - 일회성 사용 가능성: 특정 유스케이스나 요청 흐름에서만 필요할 수 있습니다.

    즉, 비즈니스 규칙은 도메인의 일관성 및 비즈니스 요구사항을 충족하는 것이고, 기술적 검증은 데이터 무결성과 시스템 동작을 보장하기 위함이다.
*/
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    public void signup(RegisterServiceCommand command) {
        Email email = new Email(command.getEmail());
        Phone phone = new Phone(command.getPhone());

        if(userRepository.existsByEmail_Email(email.getEmail())){
            throw new DuplicateEmailException("이미 존재하는 회원입니다.");
        };
        if(userRepository.existsByPhone_Phone(phone.getPhone())){
            throw new DuplicatePhoneException("이미 존재하는 전화번호입니다.");
        }

        User user = User.create(
                email,
                command.getPassword(),
                command.getName(),
                phone,
                Set.of(new UserRole(RoleEnum.USER)),
                encoder
        );

        userRepository.save(user);
    }

    public Boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail_Email(email);
    }

    public Boolean checkPhoneDuplicate(String phone) {
        return userRepository.existsByPhone_Phone(phone);
    }
}
