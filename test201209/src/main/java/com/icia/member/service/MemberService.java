package com.icia.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icia.member.domain.entity.MemberEntity;
import com.icia.member.domain.repository.MemberRepository;
import com.icia.member.dto.MemberDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {
	private MemberRepository memberRepository;
	
	//회원가입 
	public String memberJoin(MemberDTO member) {
		//전달받은 member 객체를 db에 저장
		return memberRepository.save(member.toEntity()).getMemail();
	}

	//로그인
	public MemberDTO memberLogin(MemberDTO member) {
		//입력받은 이메일에 해당하는 정보를 가져옴
		Optional<MemberEntity> memberentityWrapper = memberRepository.findById(member.getMemail());
		//가져온 데이터를 엔티티 클래스 타입에 저장
		MemberEntity memberEntity = memberentityWrapper.get();
		//엔티티에 저장된 데이터를 DTO 클래스 타입에 저장
		MemberDTO loginMember= MemberDTO.builder()
											.memail(memberEntity.getMemail())
											.mpassword(memberEntity.getMpassword())
											.mname(memberEntity.getMname())
											.build();
		//입력받은 비번과 디비에서 조회한 비번 비교
		if(member.getMpassword().equals(loginMember.getMpassword()))
			return loginMember;
		else
			return null;
	}
	
	//목록조회
	public List<MemberDTO> memberList(){
		List<MemberEntity> memberEntity = memberRepository.findAll();
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		for(MemberEntity entity : memberEntity) {
			MemberDTO member = MemberDTO.builder()
											.memail(entity.getMemail())
											.mpassword(entity.getMpassword())
											.mname(entity.getMname())
											.build();
			memberList.add(member);
		}
		return memberList;
	}

}
