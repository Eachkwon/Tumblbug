package com.example.tumblbug.service;

import com.example.tumblbug.dto.FundRequestDto;
import com.example.tumblbug.dto.FundResponseDto;
import com.example.tumblbug.entity.Fund;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.Reward;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.FundRepository;
import com.example.tumblbug.repository.RewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundServiceTest {

    @Mock
    FundRepository fundRepository;

    @Mock
    RewardRepository rewardRepository;

    @Nested
    @DisplayName("후원하기")
    class DoFunding {

        @Nested
        @DisplayName("실패")
        class Fail {

            @Test
            @DisplayName("존재하지않는 rewardId")
            void project_not_found() {
                // given
                User user = User.builder()
                        .build();

                Long rewardId = -1L;

                FundRequestDto fundRequestDto = FundRequestDto.builder()
                        .rewardId(rewardId)
                        .build();

                FundService fundService = new FundService(fundRepository, rewardRepository);

                when(rewardRepository.findById(rewardId))
                        .thenReturn(Optional.empty());

                // when
                Exception exception = assertThrows(ResponseStatusException.class, () -> fundService.fund(fundRequestDto, user));

                // then
                assertEquals("404 NOT_FOUND \"Reward " + rewardId + " is not found\"", exception.getMessage());
            }

            @Test
            @DisplayName("아직 시작하지않은 펀딩")
            void before_startDate() {
                User user = User.builder()
                        .build();

                Project project = Project.builder()
                        // .projectId(1L)
                        // .category("game")
                        // .summary("프로젝트 요약")
                        // .title("프로젝트 제목")
                        // .thumbnails(Collections.emptyList())
                        // .goal(1_000_000)
                        .startDate(LocalDateTime.now().plusDays(1))
                        .endDate(LocalDateTime.MAX)
                        // .rewards(List.of(reward))
                        // .plan("프로젝트 계획")
                        // .images(Collections.emptyList())
                        // .creatorName("홍길동")
                        // .creatorBiography("안녕하세요. 홍길동입니다.")
                        // .totalFundingPrice(500_000)
                        // .fundingCount(20)
                        // .user(user)
                        .build();

                Reward reward = Reward.builder()
                        // .rewardId(1L)
                        // .rewardItem("선물")
                        // .fundingPrice(25_000)
                        .project(project)
                        .build();

                Long rewardId = 1L;

                FundRequestDto fundRequestDto = FundRequestDto.builder()
                        .rewardId(rewardId)
                        .build();

                FundService fundService = new FundService(fundRepository, rewardRepository);

                when(rewardRepository.findById(rewardId))
                        .thenReturn(Optional.of(reward));

                // when
                Exception exception = assertThrows(ResponseStatusException.class, () -> fundService.fund(fundRequestDto, user));

                // then
                assertEquals("400 BAD_REQUEST \"아직 펀딩을 시작하지 않았습니다\"", exception.getMessage());
            }

            @Test
            @DisplayName("이미 종료된 펀딩")
            void after_endDate() {
                User user = User.builder()
                        .build();

                Project project = Project.builder()
                        // .projectId(1L)
                        // .category("game")
                        // .summary("프로젝트 요약")
                        // .title("프로젝트 제목")
                        // .thumbnails(Collections.emptyList())
                        // .goal(1_000_000)
                        .startDate(LocalDateTime.MIN)
                        .endDate(LocalDateTime.now().minusDays(1))
                        // .rewards(List.of(reward))
                        // .plan("프로젝트 계획")
                        // .images(Collections.emptyList())
                        // .creatorName("홍길동")
                        // .creatorBiography("안녕하세요. 홍길동입니다.")
                        // .totalFundingPrice(500_000)
                        // .fundingCount(20)
                        // .user(user)
                        .build();

                Reward reward = Reward.builder()
                        // .rewardId(1L)
                        // .rewardItem("선물")
                        // .fundingPrice(25_000)
                        .project(project)
                        .build();

                Long rewardId = 1L;

                FundRequestDto fundRequestDto = FundRequestDto.builder()
                        .rewardId(rewardId)
                        .build();

                FundService fundService = new FundService(fundRepository, rewardRepository);

                when(rewardRepository.findById(rewardId))
                        .thenReturn(Optional.of(reward));

                // when
                Exception exception = assertThrows(ResponseStatusException.class, () -> fundService.fund(fundRequestDto, user));

                // then
                assertEquals("400 BAD_REQUEST \"이미 펀딩이 종료되었습니다\"", exception.getMessage());
            }

        }

        @Nested
        @DisplayName("성공")
        class Success {

            @Test
            @DisplayName("정상 케이스")
            void success() {
                // given
                User user = User.builder()
                        .userId(1L)
                        // .email("hong123@gmail.com")
                        // .name("홍길동")
                        // .password("qlalfqjsgh123")
                        .build();

                int totalFundingPrice = 500_000;
                int fundingCount = 20;
                Project project = Project.builder()
                        .projectId(1L)
                        // .category("game")
                        // .summary("프로젝트 요약")
                        // .title("프로젝트 제목")
                        // .thumbnails(Collections.emptyList())
                        // .goal(1_000_000)
                        .startDate(LocalDateTime.MIN)
                        .endDate(LocalDateTime.MAX)
                        // .rewards(List.of(reward))
                        // .plan("프로젝트 계획")
                        // .images(Collections.emptyList())
                        // .creatorName("홍길동")
                        // .creatorBiography("안녕하세요. 홍길동입니다.")
                        .totalFundingPrice(totalFundingPrice)
                        .fundingCount(fundingCount)
                        // .user(user)
                        .build();

                int fundingPrice = 25_000;
                Reward reward = Reward.builder()
                        .rewardId(1L)
                        // .rewardItem("선물")
                        .fundingPrice(fundingPrice)
                        .project(project)
                        .build();

                Long rewardId = 1L;

                FundRequestDto fundRequestDto = FundRequestDto.builder()
                        .rewardId(rewardId)
                        .build();

                Fund fund = new Fund(user, reward);

                FundService fundService = new FundService(fundRepository, rewardRepository);

                when(rewardRepository.findById(rewardId))
                        .thenReturn(Optional.of(reward));

                when(fundRepository.save(any(Fund.class)))
                        .thenReturn(fund);

                // when
                FundResponseDto fundResponseDto = fundService.fund(fundRequestDto, user);

                // then
                assertEquals(project.getProjectId(), fundResponseDto.getProjectId());
                assertEquals(user.getUserId(), fundResponseDto.getUserId());
                assertEquals(reward.getRewardId(), fundResponseDto.getRewardId());
                assertEquals(totalFundingPrice + fundingPrice, project.getTotalFundingPrice());
                assertEquals(fundingCount + 1, project.getFundingCount());
            }

        }

    }

}
