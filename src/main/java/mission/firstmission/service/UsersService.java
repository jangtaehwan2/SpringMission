package mission.firstmission.service;

import lombok.RequiredArgsConstructor;
import mission.firstmission.domain.users.Users;
import mission.firstmission.domain.users.UsersRepository;
import mission.firstmission.domain.users.dto.UsersSignInDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final 변수들을 입력 받는 기본 생성자
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    // 필요한 기능들
    // 1. 회원 등록 기능
    // 2. 로그인 기능
    // 3. 회원 보기 기능 (로그인 필요)
    // 4. 추후 여력이 된다면,,, -> 유저 수정/삭제 기능 추가 해보기

    @Transactional
    public Long signUp(UsersSignUpDto signUpDto) {
        validateDuplicateUser(signUpDto);
        return usersRepository.save(signUpDto.toEntity()).getId();
    }

    private void validateDuplicateUser(UsersSignUpDto users) {
        Users isValidate = usersRepository.findByName(users.getName());
        if(isValidate != null) {
            throw new RuntimeException("Validate Users Name");
        }
    }

    @Transactional
    public Users signIn(UsersSignInDto signInDto) {
        Users users = usersRepository.findByName(signInDto.getName());
        if(users == null){
            throw new RuntimeException("Not found Users");
        }
        if(!users.getPw().equals(signInDto.getPw())) {
            throw new RuntimeException("Password mismatch");
        }
        return users;
    }

    @Transactional
    public List<Users> signUpUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersList;
    }
}
