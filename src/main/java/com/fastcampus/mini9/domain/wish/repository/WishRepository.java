package com.fastcampus.mini9.domain.wish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.mini9.domain.wish.entity.Wish;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
