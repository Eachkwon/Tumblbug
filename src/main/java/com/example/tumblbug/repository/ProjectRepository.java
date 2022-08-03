package com.example.tumblbug.repository;

import com.example.tumblbug.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByTitleContainingOrderByFundingCountDesc(String title);

    List<Project> findAllByTitleContainingOrderByStartDateDesc(String title);

    List<Project> findAllByTitleContainingOrderByTotalFundingPriceDesc(String title);

    List<Project> findAllByTitleContainingOrderByEndDateAsc(String title);

    List<Project> findAllByCategoryAndTitleContainingOrderByFundingCountDesc(String category, String title);

    List<Project> findAllByCategoryAndTitleContainingOrderByStartDateDesc(String category, String title);

    List<Project> findAllByCategoryAndTitleContainingOrderByTotalFundingPriceDesc(String category, String title);

    List<Project> findAllByCategoryAndTitleContainingOrderByEndDateAsc(String category, String title);

}
