package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.AddMemberReqDto;
import com.korit.korit_gov_spring_boot_study.entity.Member;
import com.korit.korit_gov_spring_boot_study.repository.MemberRepository;

import java.util.List;

public class MemberService {
    private static MemberService instance;

    private MemberRepository memberRepository;

    private MemberService() {
        this.memberRepository = MemberRepository.getInstance();
    }

    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }

    public void addMember(AddMemberReqDto addMemberReqDto) {
        memberRepository.addMember(addMemberReqDto.toEntity());
    }

    public boolean isDuplicatedName(String name) {
        return memberRepository.findMemberByName(name) != null;
    }

    public List<Member> getMemberAll() {
        return memberRepository.getMemberAll();
    }

}
