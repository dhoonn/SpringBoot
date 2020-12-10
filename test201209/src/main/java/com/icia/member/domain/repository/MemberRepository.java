package com.icia.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icia.member.domain.entity.MemberEntity;

//JpaRepository<엔티티클래스 이름, pk 타입>     <> : 제네릭
public interface MemberRepository extends JpaRepository<MemberEntity, String>{

}
