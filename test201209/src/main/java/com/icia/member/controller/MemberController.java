package com.icia.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	
	//회원가입 페이지 이동
	@GetMapping("/member/join")
	public String joinForm() {
		return "memberjoin";
	}

	//회원가입 
	@PostMapping("/member/join")
	public String memberJoin(MemberDTO member) {
		int result = memberService.memberJoin(member);
		return "memberlogin";
	}
}
