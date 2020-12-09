package com.icia.member.service;

import org.springframework.stereotype.Service;

import com.icia.member.domain.repository.MemberRepository;
import com.icia.member.dto.MemberDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
	private MemberRepository memberRepository;
	
	//회원가입 
	public int memberJoin(MemberDTO member) {
		
		//전달받은 member 객체를 db에 저장
		return memberRepository.save(member.toEntity()).getMnumber();
	}

}
