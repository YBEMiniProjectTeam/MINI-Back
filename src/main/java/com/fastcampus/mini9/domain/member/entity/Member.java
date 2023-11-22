package com.fastcampus.mini9.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private LocalDate birthday;

    @Builder
    private Member(String email, String pwd, String name, LocalDate birthday) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birthday = birthday;
    }
}
