package com.example.tumblbug.service;

import com.example.tumblbug.dto.*;
import com.example.tumblbug.entity.*;
import com.example.tumblbug.repository.ImageRepository;
import com.example.tumblbug.repository.ProjectRepository;
import com.example.tumblbug.repository.RewardRepository;
import com.example.tumblbug.repository.ThumbnailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    RewardRepository rewardRepository;

    @Mock
    ThumbnailRepository thumbnailRepository;

    @Mock
    ImageRepository imageRepository;

    @Nested
    @DisplayName("프로젝트 리스트 조회")
    class GetAllProjects {

//        @Nested
//        @DisplayName("실패")
//        class Fail {
//        }

        @Nested
        @DisplayName("성공")
        class Success {

            @Test
            @DisplayName("정상 케이스")
            void success() {
                // given
                String category = "game";

                List<Project> projects = new ArrayList<>();

                Project project = Project.builder()
                        // .projectId(projectId)
                        .category("game")
                        // .summary("프로젝트 요약")
                        // .title("프로젝트 제목")
                        .thumbnails(Collections.emptyList())
                        // .goal(1_000_000)
                        // .startDate(LocalDate.of(2022, 8, 1))
                        // .endDate(LocalDate.of(2022, 8, 5))
                        // .creatorName("홍길동")
                        // .totalFundingPrice(500_000)
                        .build();

                projects.add(project);

                ProjectService projectService = new ProjectService(projectRepository, rewardRepository, thumbnailRepository, imageRepository);

                when(projectRepository.findAllByCategory(category))
                        .thenReturn(projects);

                // when
                List<ProjectsByCategoryResponseDto> projectsByCategoryResponseDtos = projectService.getProjectsByCategory(category);

                // then
                for (ProjectsByCategoryResponseDto projectsByCategoryResponseDto : projectsByCategoryResponseDtos) {
                    assertEquals(category, projectsByCategoryResponseDto.getCategory());
                }
            }

        }

    }

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

                ProjectService projectService = new ProjectService(projectRepository, rewardRepository, thumbnailRepository, imageRepository);

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
                        .startDate(LocalDate.of(2022, 8, 1))
                        .endDate(LocalDate.of(2022, 8, 5))
                        .rewards(Collections.emptyList())
                        .plan("프로젝트 계획")
                        // .images(Collections.emptyList())
                        .creatorName("홍길동")
                        .creatorBiography("안녕하세요. 홍길동입니다.")
                        .totalFundingPrice(500_000)
                        .fundingCount(20)
                        // user(user)
                        .build();

                ProjectService projectService = new ProjectService(projectRepository, rewardRepository, thumbnailRepository, imageRepository);

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

    @Nested
    @DisplayName("프로젝트 생성")
    class CreateProject {

//        @Nested
//        @DisplayName("실패")
//        class Fail {
//        }

        @Nested
        @DisplayName("성공")
        class Success {

            @Test
            @DisplayName("정상 케이스")
            void success() {
                // given
                User user = User.builder()
                        .build();

                String category = "game";
                String summary = "프로젝트 요약";
                String title = "프로젝트 제목";
                List<ImageRequestDto> thumbnailRequestDtos = Collections.emptyList();
                Integer goal = 1_000_000;
                LocalDate startDate = LocalDate.now().plusDays(1);
                LocalDate endDate = LocalDate.from(startDate).plusDays(6);
                List<RewardRequestDto> rewardRequestDtos = Collections.emptyList();
                String plan = "프로젝트 계획";
                List<ImageRequestDto> imageRequestDtos = Collections.emptyList();
                String creatorName = "홍길동";
                String creatorBiography = "안녕하세요. 홍길동입니다.";

                ProjectRequestDto projectRequestDto = ProjectRequestDto.builder()
                        .category(category)
                        .summary(summary)
                        .title(title)
                        .thumbnails(thumbnailRequestDtos)
                        .goal(goal)
                        .startDate(startDate)
                        .endDate(endDate)
                        .rewards(rewardRequestDtos)
                        .plan(plan)
                        .images(imageRequestDtos)
                        .creatorName(creatorName)
                        .creatorBiography(creatorBiography)
                        .build();

                Long projectId = 1L;

//                Project project = new Project(projectRequestDto);
//
//                List<Reward> rewards = project.getRewards();

                List<Thumbnail> thumbnails = thumbnailRequestDtos.stream().map((thumbnailRequestDto) -> new Thumbnail(thumbnailRequestDto, null)).collect(Collectors.toList());

                List<Reward> rewards = rewardRequestDtos.stream().map((rewardRequestDto) -> new Reward(rewardRequestDto, null)).collect(Collectors.toList());

                List<Image> images = imageRequestDtos.stream().map((imageRequestDto) -> new Image(imageRequestDto, null)).collect(Collectors.toList());

                Project savedProject = Project.builder()
                        .projectId(projectId)
                        .category(category)
                        .summary(summary)
                        .title(title)
//                        .thumbnails(thumbnails)
                        .goal(goal)
                        .startDate(startDate)
                        .endDate(endDate)
                        .rewards(rewards)
                        .plan(plan)
//                        .images(images)
                        .creatorName(creatorName)
                        .creatorBiography(creatorBiography)
                        .totalFundingPrice(0)
                        .fundingCount(0)
                        // user(user)
                        .build();

                ProjectService projectService = new ProjectService(projectRepository, rewardRepository, thumbnailRepository, imageRepository);

                when(projectRepository.save(any(Project.class)))
                        .thenReturn(savedProject);

                // when
                ProjectResponseDto projectResponseDto = projectService.createProject(projectRequestDto, user);

                // then
                assertEquals(projectId, projectResponseDto.getProjectId());
                assertEquals(category, projectResponseDto.getCategory());
                assertEquals(summary, projectResponseDto.getSummary());
                assertEquals(title, projectResponseDto.getTitle());
                // assertEquals(thumbnails, projectResponseDto.getThumbnails());
                assertEquals(goal, projectResponseDto.getGoal());
                assertEquals(startDate, projectResponseDto.getStartDate());
                assertEquals(endDate, projectResponseDto.getEndDate());
                assertEquals(rewards.stream().map(RewardResponseDto::new).collect(Collectors.toList()), projectResponseDto.getRewards());
                assertEquals(plan, projectResponseDto.getPlan());
                // assertEquals(images, projectResponseDto.getImages());
                assertEquals(creatorName, projectResponseDto.getCreatorName());
                assertEquals(creatorBiography, projectResponseDto.getCreatorBiography());
                assertEquals(0, projectResponseDto.getTotalFundingPrice());
                assertEquals(0, projectResponseDto.getFundingCount());
            }

        }

    }

}
