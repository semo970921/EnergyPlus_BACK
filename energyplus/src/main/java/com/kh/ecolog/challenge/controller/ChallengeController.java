package com.kh.ecolog.challenge.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ecolog.challenge.model.dto.ChallengeDTO;
import com.kh.ecolog.challenge.model.service.ChallengeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:5173")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // 챌린지 목록 조회
    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getChallenges() {
        List<ChallengeDTO> list = challengeService.getChallengeList();
        return ResponseEntity.ok(list);
    }

    // 챌린지 상세 조회
    @GetMapping("/{challengeSeq}")
    public ResponseEntity<ChallengeDTO> getChallengeDetail(@PathVariable ("challengeSeq")Long challengeSeq) {
        ChallengeDTO dto = challengeService.getChallengeDetail(challengeSeq);
        return ResponseEntity.ok(dto);
    }

    // 챌린지 등록
    @PostMapping
    public ResponseEntity<Void> createChallenge(@RequestBody ChallengeDTO dto) {
        challengeService.createChallenge(dto);
        return ResponseEntity.ok().build();
    }
}