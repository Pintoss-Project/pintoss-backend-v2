package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.ExpiredServerTokenException;
import com.pintoss.gitftmall.core.exceptions.client.InvalidRequestTokenException;
import com.pintoss.gitftmall.core.exceptions.client.NotFoundMemberException;
import com.pintoss.gitftmall.domain.membership.application.command.ReissueServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.ReissueResult;
import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final UserRepository userRepository;
    private final TokenManageService tokenManageService;

    @Transactional
    public ReissueResult reissue(ReissueServiceCommand command){

        // 1. 요청 리프레쉬 토큰 검증
        if( !tokenManageService.isValidToken(command.getRefreshToken())) {
            throw new InvalidRequestTokenException(ErrorCode.INVALID_ACCESS, "유효하지 않은 토큰입니다.");
        }

        // 2. 사용자 id 추출
        String subject = tokenManageService.getSubject(command.getRefreshToken());

        // 3. 사용자 정보 조회
        User user = userRepository.findById(Integer.parseInt(subject)).orElseThrow(
                () -> new NotFoundMemberException("존재하지 않는 회원입니다.")
        );

        // 4. 서버 리프레쉬 토큰과 요청 리프레쉬 비교
        if( !user.isEqualsRefreshToken(command.getRefreshToken()) ) {
            throw new InvalidRequestTokenException(ErrorCode.INVALID_ACCESS,"서버의 리프레쉬 토큰과 일치하지 않습니다.");
        }
        
        // 5. 서버 리프레쉬 만료 여부 확인
        if ( tokenManageService.isExpiredToken(user.getRefreshToken()) ){
            throw new ExpiredServerTokenException(ErrorCode.EXPIRED_TOKEN, "서버의 리프레쉬 토큰이 만료되었습니다.");
        };

        // 6. 액세스, 리프레쉬 토큰 생성 및 저장
        String accessToken = tokenManageService.createToken(subject,false);
        String refreshToken = tokenManageService.createToken(subject, true);

        user.storeRefreshToken(refreshToken);

        return new ReissueResult(accessToken, refreshToken);
    }
}
