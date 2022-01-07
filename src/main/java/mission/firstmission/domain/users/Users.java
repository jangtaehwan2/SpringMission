package mission.firstmission.domain.users;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 어노테이션
@Entity
public class Users {

    @Id // PK를 의미하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 부여 어노테이션
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String pw;

    @Builder // Builder 방식을 사용한 객체 생성을 유도함
    public Users(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }
}
