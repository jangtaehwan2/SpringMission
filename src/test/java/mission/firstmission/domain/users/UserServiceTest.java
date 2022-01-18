package mission.firstmission.domain.users;

import mission.firstmission.domain.users.dto.UsersSignInDto;
import mission.firstmission.domain.users.dto.UsersSignUpDto;
import mission.firstmission.repository.relational.UsersRepository;
import mission.firstmission.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@RunWith(SpringRunner.class) 구 버전 4.x 까지? 테스트 어노테이션
@ExtendWith(SpringExtension.class) // 생략이 가능하다.
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersService usersService;

    @AfterEach
    public void cleanUp() {
        usersRepository.deleteAll();
    }

    @Test
    public void usersRepositoryTest() {
        //given
        String name = "name";
        String pw = "pw";

        usersRepository.save(Users.builder()
                .name(name)
                .pw(pw)
                .build());
        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(0);
        assertThat(users.getName()).isEqualTo(name);
        assertThat(users.getPw()).isEqualTo(pw);
    }

    @Test
    public void userSignUpTest() {
        //given
        String name = "hello";
        String pw = "1234";
        //when
        long userId = usersService.signUp(UsersSignUpDto.builder()
                .name(name)
                .pw(pw)
                .build());
        //then
         Users user = usersRepository.findById(userId).get();
         assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void validateSignUpTest() {
        //given
        String name = "hello";
        String pw = "pw";
        //when
        usersService.signUp(UsersSignUpDto.builder()
                .name(name)
                .pw(pw)
                .build());
        RuntimeException e = assertThrows(RuntimeException.class, () ->
                usersService.signUp(UsersSignUpDto.builder()
                .name(name)
                .pw(pw)
                .build()));
        //then
        assertThat(e.getMessage()).isEqualTo("Validate Users Name");
    }

    @Test
    public void userSignInTest() {
        //given
        String name = "name";
        String pw = "pw";
        long userId = usersService.signUp(UsersSignUpDto.builder()
                .name(name)
                .pw(pw)
                .build());
        //when
        Users user = usersService.signIn(UsersSignInDto.builder()
                .name(name)
                .pw(pw)
                .build());
        //then
        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPw()).isEqualTo(pw);
    }
}
