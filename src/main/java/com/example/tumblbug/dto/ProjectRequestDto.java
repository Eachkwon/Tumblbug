package com.example.tumblbug.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class ProjectRequestDto {

    @NotBlank // game, fashion, culture, pet, beauty 중 하나인지 검사
    private String category;

    @NotBlank(message = "프로젝트 요약은 공백일 수 없습니다")
    // @Length(min = 10, max = 50, message = "최소 10자 이상 최대 50자 이하로 적어주세요.")
    @Length(max = 50, message = "최대 50자 이하로 적어주세요.")
    private String summary;

    @NotBlank(message = "프로젝트 제목은 공백일 수 없습니다")
    @Length(max = 32, message = "최대 32자 이하로 적어주세요.")
    private String title;

    @NotNull(message = "대표 이미지는 필수 항목입니다")
    @Size(min = 1, max = 5, message = "대표 이미지는 최소 1개, 최대 5개까지 업로드 가능합니다")
    private List<ImageRequestDto> thumbnails;

    // @NotBlank(message = "목표 금액은 공백일 수 없습니다")
    // @Min(value = 500_000, message = "50만원 이상의 금액을 입력해주세요.")
    @Min(value = 1, message = "1원 이상의 금액을 입력해주세요.")
    @Max(value = Integer.MAX_VALUE, message = "2,147,483,647원 이하의 금액을 입력해주세요.")
    private Integer goal;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Valid
    @NotNull(message = "선물은 필수 항목입니다")
    @Size(min = 1, message = "선물은 최소 1개 이상 필요합니다")
    private List<RewardRequestDto> rewards;

    @NotBlank(message = "프로젝트 계획은 공백일 수 없습니다")
    private String plan;

    @NotBlank(message = "창작자 이름은 공백일 수 없습니다")
    @Length(max = 20, message = "최대 20자 이하로 적어주세요.")
    private String creatorName;

    @NotBlank(message = "창작자 소개는 공백일 수 없습니다")
    // @Length(min = 10, max = 300, message = "최소 10자 이상 최대 300자 이하로 적어주세요.")
    @Length(max = 300, message = "최소 10자 이상 최대 300자 이하로 적어주세요.")
    private String creatorBiography;

}
