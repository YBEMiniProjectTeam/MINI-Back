package com.fastcampus.mini9.domain.member.entity;

import java.time.LocalDate;
import java.util.List;

import com.fastcampus.mini9.domain.wish.entity.Wish;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@OneToMany(mappedBy = "member")
	private List<Wish> wishList;

	@Builder
	private Member(String email, String pwd, String name, LocalDate birthday, List<Wish> wishList) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.wishList = wishList;
	}
}
