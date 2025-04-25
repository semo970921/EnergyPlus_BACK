package com.kh.ecolog.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ecolog.challenge.model.dto.ChallengeDTO;
import com.kh.ecolog.challenge.model.service.ChallengeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;


    // 챌린지 등록 
    @PostMapping
    public ResponseEntity<?> saveChallenge(@Valid ChallengeDTO challenge, @RequestParam(name = "file", required = false) MultipartFile file){
    	
    	challengeService.saveChallenge(challenge, file);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    
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


    
}