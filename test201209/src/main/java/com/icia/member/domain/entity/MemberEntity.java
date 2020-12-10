package com.icia.member.domain.entity;

import javax.persistence.*;

import lombok.*;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="bootmember") // bootmember라는 이름의 테이블이 생성됨
public class MemberEntity {

	//해당 테이블의 컬럼에 대한 정의
	/*
	 * bootmember는 총 4개의 컬럼을 가지고 있음
	 * 	create table bootmember(
	 * 		mnumber number primary key, (시퀀스 사용)
	 * 		memail nvarchar2(50) unique,
	 * 		mpassword nvarchar2(30) not null,
	 * 		mname nvarchar2(20) not null
	 * );
	 */
	
	@Id //pk를 지정할 때 사용
	private String memail;
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스 사용
//	private int mnumber;
	
//	@Column(length=100, unique=true, nullable=false)
//	private String memail;
	
	@Column(length=100, nullable=false)
	private String mpassword;
	
	@Column(length=100, nullable=false)
	private String mname;
	
	@Builder
	public MemberEntity(String memail, String mpassword, String mname) {
		//this.mnumber = mnumber;
		this.memail = memail;
		this.mpassword = mpassword;
		this.mname = mname;
	}
}
