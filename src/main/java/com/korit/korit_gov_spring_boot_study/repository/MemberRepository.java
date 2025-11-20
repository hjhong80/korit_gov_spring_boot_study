package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberRepository {
    private static MemberRepository instance;
    private List<Member> memberList;

    private MemberRepository() {
        this.memberList = new ArrayList<>();
    }

    public static MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public void addMember(Member member) {
        member.setUserId(memberList.size() + 1);
        memberList.add(member);
    }

    public Member findMemberByName(String name) {
        return memberList.stream()
                .filter(i -> Objects.equals(i.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Member> getMemberAll() {
        return memberList;
    }

}
