package com.example.tumblbug.service;

import com.example.tumblbug.dto.ProjectDetailResponseDto;
import com.example.tumblbug.dto.ProjectRequestDto;
import com.example.tumblbug.dto.ProjectResponseDto;
import com.example.tumblbug.dto.ProjectsByCategoryResponseDto;
import com.example.tumblbug.entity.Project;
import com.example.tumblbug.entity.User;
import com.example.tumblbug.repository.ProjectRepository;
import com.example.tumblbug.repository.RewardRepository;
import com.example.tumblbug.repository.ThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final RewardRepository rewardRepository;

    private final ThumbnailRepository thumbnailRepository;

    // 프로젝트 리스트 조회
    public Slice<ProjectsByCategoryResponseDto> getProjectsByCategory(int page, int size, String category, String sort, String query) {
        Set<String> categories = new HashSet<>(Arrays.asList("all", "game", "fashion", "culture", "pet", "beauty"));
        Set<String> sorts = new HashSet<>(Arrays.asList("popular", "publishedAt", "amount", "endedAt"));

        if (!categories.contains(category)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category 값이 유효하지 않습니다");
        }
        if (!sorts.contains(sort)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sort 값이 유효하지 않습니다");
        }

        Pageable pageable = PageRequest.of(page, size);

        Slice<Project> projects = null;
        if (category.equals("all")) {
            switch (sort) {
                case "popular":
                    projects = projectRepository.findAllByTitleContainingOrderByFundingCountDesc(query, pageable);
                    break;
                case "publishedAt":
                    projects = projectRepository.findAllByTitleContainingOrderByStartDateDesc(query, pageable);
                    break;
                case "amount":
                    projects = projectRepository.findAllByTitleContainingOrderByTotalFundingPriceDesc(query, pageable);
                    break;
                case "endedAt":
                    projects = projectRepository.findAllByTitleContainingOrderByEndDateAsc(query, pageable);
                    break;
            }
        } else {
            switch (sort) {
                case "popular":
                    projects = projectRepository.findAllByCategoryAndTitleContainingOrderByFundingCountDesc(category, query, pageable);
                    break;
                case "publishedAt":
                    projects = projectRepository.findAllByCategoryAndTitleContainingOrderByStartDateDesc(category, query, pageable);
                    break;
                case "amount":
                    projects = projectRepository.findAllByCategoryAndTitleContainingOrderByTotalFundingPriceDesc(category, query, pageable);
                    break;
                case "endedAt":
                    projects = projectRepository.findAllByCategoryAndTitleContainingOrderByEndDateAsc(category, query, pageable);
                    break;
            }
        }

        if (projects == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "프로젝트 목록을 조회하는데 실패하였습니다");
        }

        return projects.map(ProjectsByCategoryResponseDto::new);
    }

    // 프로젝트 상세정보 조회
    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project " + projectId + " is not found"));
        return new ProjectDetailResponseDto(project);
    }

    // 프로젝트 생성
    @Transactional
    public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto, User user) {
        Project project = projectRepository.save(new Project(projectRequestDto, user));
        rewardRepository.saveAll(project.getRewards());
        thumbnailRepository.saveAll(project.getThumbnails());
        return new ProjectResponseDto(project);
    }

}
