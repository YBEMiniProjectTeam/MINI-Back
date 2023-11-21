package com.fastcampus.mini9.domain.payment.entity;

import java.time.ZonedDateTime;

import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.reservation.entity.Reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ZonedDateTime payAt;
	private Integer amount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@ManyToOne
	@JoinColumn
	private Member member;
	@OneToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
}
