package com.nts.reservation.reserve.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.nts.reservation.common.utils.RegexPattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class ReservationParam {

	@Positive
	private int displayInfoId;
	
	@NotEmpty
	private List<ReservationPrice> price;
	
	@Positive
	private int productId;
	
	@NotBlank(message="이메일 정보가 필요합니다.")	
	@Pattern(message="이메일 형식을 잘 못 입력하였습니다.", regexp=RegexPattern.EMAIL_REGEX)
	@Size(min = 9, max = 254)
	private String reservationEmail;
	
	@Pattern(message="이름 정보를 다시 확인해주세요.", regexp=RegexPattern.NAME_REGEX)
	@NotBlank(message="이름 정보가 필요합니다.")
	@Size(min = 2, max = 254)
	private String reservationName;
	
	@Pattern(message="전화번호 형식을 잘 못 입력하였습니다.", regexp=RegexPattern.TEL_REGEX)
	@NotBlank(message="전화번호 정보가 필요합니다.")
	@Size(min = 10, max = 11)
	private String reservationTel;
	
	@NotBlank(message="예약 날짜 정보가 필요합니다.")
	private String reservationDate;
}
