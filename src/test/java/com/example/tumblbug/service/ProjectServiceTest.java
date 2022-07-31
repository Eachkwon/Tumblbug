package com.example.tumblbug.service;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.RewardResponseDto;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @Nested
    @DisplayName("프로젝트 상세정보 조회")
    class GetProjectDetail {

        @Nested
        @DisplayName("실패")
        class Fail {

            @Test
            @DisplayName("존재하지않는 projectId")
            void project_not_found() {
                // given
                Long projectId = -1L;

                ProjectService projectService = new ProjectService(projectRepository);

                when(projectRepository.findById(projectId))
                        .thenReturn(Optional.empty());

                // when
                Exception exception = assertThrows(ResponseStatusException.class, () -> projectService.getProjectDetail(projectId));

                // then
                assertEquals("404 NOT_FOUND \"Project " + projectId + " is not found\"", exception.getMessage());
            }

        }

        @Nested
        @DisplayName("성공")
        class Success {

            @Test
            @DisplayName("정상 케이스")
            void success() {
                // given
                Long projectId = 1L;

                Project project = Project.builder()
                        .projectId(projectId)
                        .category("game")
                        .summary("프로젝트 요약")
                        .title("프로젝트 제목")
                        .thumbnails(Collections.emptyList())
                        .goal(1_000_000)
                        .startDate(LocalDateTime.of(2022, 8, 1, 0, 0))
                        .endDate(LocalDateTime.of(2022, 8, 5, 0, 0))
                        .rewards(Collections.emptyList())
                        .plan("프로젝트 계획")
                        // .images(Collections.emptyList())
                        .creatorName("홍길동")
                        .creatorBiography("안녕하세요. 홍길동입니다.")
                        .totalFundingPrice(500_000)
                        .fundingCount(20)
                        // user(user)
                        .build();

                ProjectService projectService = new ProjectService(projectRepository);

                when(projectRepository.findById(projectId))
                        .thenReturn(Optional.of(project));

                // when
                ProjectDetailResponseDto projectDetailResponseDto = projectService.getProjectDetail(projectId);

                // then
                assertEquals(project.getCategory(), projectDetailResponseDto.getCategory());
                assertEquals(project.getSummary(), projectDetailResponseDto.getSummary());
                assertEquals(project.getTitle(), projectDetailResponseDto.getTitle());
                assertEquals(project.getThumbnails(), projectDetailResponseDto.getThumbnails());
                assertEquals(project.getGoal(), projectDetailResponseDto.getGoal());
                assertEquals(project.getStartDate(), projectDetailResponseDto.getStartDate());
                assertEquals(project.getEndDate(), projectDetailResponseDto.getEndDate());
                assertEquals(project.getRewards().stream().map(RewardResponseDto::new).collect(Collectors.toList()), projectDetailResponseDto.getRewards());
                assertEquals(project.getPlan(), projectDetailResponseDto.getPlan());
                assertEquals(project.getCreatorName(), projectDetailResponseDto.getCreatorName());
                assertEquals(project.getCreatorBiography(), projectDetailResponseDto.getCreatorBiography());
                assertEquals(project.getTotalFundingPrice(), projectDetailResponseDto.getTotalFundingPrice());
                assertEquals(project.getFundingCount(), projectDetailResponseDto.getFundingCount());
            }

        }

    }
}
