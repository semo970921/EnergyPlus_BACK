package com.kh.ecolog.promise.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Promise {
	private Long userPromiseId;
	private Long userId;
	private String userPromise;
	private Date userPromiseDate;
}
