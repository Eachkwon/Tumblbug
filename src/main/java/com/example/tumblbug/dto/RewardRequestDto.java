package com.example.tumblbug.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class RewardRequestDto {

    @NotBlank(message = "선물 아이템은 공백일 수 없습니다")
    @Length(max = 50, message = "최대 50자 이하로 적어주세요.")
    private String rewardItem;

    // @NotBlank(message = "목표 금액은 공백일 수 없습니다")
    // @Min(value = 1_000, message = "1000원 이상의 금액을 입력해주세요.")
    @Min(value = 1, message = "1원 이상의 금액을 입력해주세요.")
    @Max(value = Integer.MAX_VALUE, message = "2,147,483,647원 이하의 금액을 입력해주세요.")
    private Integer fundingPrice;

}
