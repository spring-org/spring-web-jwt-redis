package com.example.springwebjwtredis.member.controller;

import com.example.springwebjwtredis.member.domain.MemberDto;
import com.example.springwebjwtredis.member.dto.RequestAddMember;
import com.example.springwebjwtredis.member.dto.RequestUpdateMember;
import com.example.springwebjwtredis.member.dto.ResponseMember;
import com.example.springwebjwtredis.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ResponseMember> addMember(@RequestBody RequestAddMember requestAddMember) {
        log.info("사용자 등록: {}", requestAddMember);

        MemberDto memberDto = memberService.addMember(requestAddMember.toDto());

        log.info("등록된 사용자: {}", memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDto.toResponse(memberDto));
    }

    @PutMapping(value = "/{memberId}")
    public ResponseEntity<ResponseMember> updateMember(@PathVariable Long memberId, @RequestBody RequestUpdateMember updateMember) {
        log.info("사용자 정보 수정 : {}", updateMember);

        MemberDto memberDto = memberService.updateMember(memberId, updateMember.toDto());

        return ResponseEntity.ok().body(MemberDto.toResponse(memberDto));
    }

    @DeleteMapping(value = "/{memberId}")
    public ResponseEntity<ResponseMember> deleteMember(@PathVariable Long memberId) {

        MemberDto memberDto = memberService.deleteMember(memberId);
        log.info("사용자 삭제: {}", memberId);
        return ResponseEntity.ok().body(MemberDto.toResponse(memberDto));
    }
}
