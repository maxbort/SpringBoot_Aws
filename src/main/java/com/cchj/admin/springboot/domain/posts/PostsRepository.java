package com.cchj.admin.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> { //DAO라고 불리는 DB Layer 접근자
    // JpaRepository<할당할 Entity 클래스, 해당 Entity 클래스의 PK 타입>으로 선언.
    // Posts 클래스로 DataBase를 접근하게 해주는 Jpa 레파지토리
    // 중요! Entity 클래스는 기본 Repository가 반드시 필요하며 그 둘은 같은 패키지에 위치해야한다.

    // 프로젝트 규모가 커져 도메인 별로 프로젝트를 분리해야 한다면 Entity 클래스와 기본 Repository는 함께 움직여야 한다.
}
