package com.kh.ecolog.promise.model.dto;

import java.sql.Date;

import com.kh.ecolog.promise.vo.Promise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PromiseDTO {
	private Long userPromiseId;
	private Long userId;
	private String userPromise;
	private Date userPromiseDate;
	
	public static PromiseDTO fromEntity(Promise promise) {
        PromiseDTO dto = new PromiseDTO();
        dto.setUserPromiseId(promise.getUserPromiseId());
        dto.setUserPromise(promise.getUserPromise());
        dto.setUserId(promise.getUserId());
        return dto;
    }
}
