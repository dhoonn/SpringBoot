package com.icia.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	//상세조회(ajax)
	@PostMapping("/member/view")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam(value="memail") String memail) {
		MemberDTO memberView = memberService.memberView(memail);
		return memberView;
	}
	//상세조회 (Rest api)
	@GetMapping("/member/{memail}")
	public String memberView(@PathVariable("memail") String memail, Model model) {
		MemberDTO memberView = memberService.memberView(memail);
		model.addAttribute("MemberView", memberView);
		return "memberView";
	}
	//삭제
	@DeleteMapping("/member/{memail}")
	public @ResponseBody String memberDelete(@PathVariable("memail") String memail) {
		String result = memberService.memberDelete(memail);
		return result;
	}
	//수정화면 출력
	@GetMapping("/member/update")
	public String memberUpdate(HttpSession session, Model model) {
		String loginId = (String) session.getAttribute("loginId");
		MemberDTO updateView = memberService.memberView(loginId);
		model.addAttribute("updateView", updateView);
		return "memberupdate";
	}
	//수정확인
	@RequestMapping(value="/member/update", method= {RequestMethod.PUT, RequestMethod.POST})
	public String memberUpdateProcess(MemberDTO member) {
		memberService.memberJoin(member);
		return "redirect:/member/"+member.getMemail();
	}
	
	//수정확인(ajax)
	@PutMapping("/member/update")
	public String memberUpdateProcess1(@RequestBody MemberDTO member) {
		memberService.memberJoin(member);
		return "ok";
	}
}
