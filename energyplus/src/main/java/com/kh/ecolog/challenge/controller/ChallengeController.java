package com.kh.ecolog.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    // 챌린지 목록 조회 (+ 페이징) 
    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> findAllChallenge(
    	@RequestParam(name = "page", defaultValue = "0") int page,
    	@RequestParam(name = "keyword", required = false) String keyword){
    		return ResponseEntity.ok(challengeService.findAllChallenge(page, keyword));
    	}

    // 챌린지 상세 조회
    @GetMapping("/{challengeSeq}")
    public ResponseEntity<ChallengeDTO> getChallengeDetail(@PathVariable ("challengeSeq")Long challengeSeq) {
        ChallengeDTO dto = challengeService.getChallengeDetail(challengeSeq);
        return ResponseEntity.ok(dto);
    }

    // 챌린지 삭제
    @DeleteMapping("/{challengeSeq}")
    public ResponseEntity<?> deleteChallenge(@PathVariable("challengeSeq") long challengeSeq) {
    	
    	challengeService.deleteChallenge(challengeSeq);
    	return ResponseEntity.ok().build();
    }
    
    
    // 챌린지 수정 
    @PutMapping("/{challengeSeq}")
    public ResponseEntity<ChallengeDTO> updateChallenge(@PathVariable(name = "challengeSeq") long challengeSeq, ChallengeDTO challenge,
    													@RequestParam(name="file", required = false )MultipartFile file){
    	
    	challenge.setChallengeSeq(challengeSeq);
    	return ResponseEntity.status(HttpStatus.CREATED)
    										.body(challengeService.updateChallenge(challenge, file));
    }
    
    // 패이지 수 조회 
    @GetMapping("/pages")
    public ResponseEntity<Integer> getPageCount(@RequestParam(name = "keyword",required = false) String keyword){
    	return ResponseEntity.ok(challengeService.getTotalPages(keyword));
    }
    

    
}















