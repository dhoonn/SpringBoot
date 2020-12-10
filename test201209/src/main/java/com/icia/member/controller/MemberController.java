package com.icia.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

import lombok.AllArgsConstructor;

@SessionAttributes("loginId")
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
		String result = memberService.memberJoin(member);
		if(result !=null)
			return "memberlogin";
		else
			return "joinfail";
	}
	
	//로그인 페이지 이동
	@GetMapping("/member/login")
	public String loginForm() {
		return "memberlogin";
	}
	
	//로그인
	@PostMapping("/member/login")
	public String memberLogin(MemberDTO member, Model model) {
		MemberDTO loginMember = memberService.memberLogin(member);
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			model.addAttribute("loginId", loginMember.getMemail());
			return "mypage";
		} else {
			return "loginfail";
		}
	}
	
	//목록 메소드
	@PostMapping("/member/list")
	public String memberList(Model model) {
		List<MemberDTO> memberList = memberService.memberList();
		model.addAttribute("memberList", memberList);
		return "memberlist";
	}
}
