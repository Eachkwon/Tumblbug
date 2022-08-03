package com.example.tumblbug.repository;

import com.example.tumblbug.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByOrderByFundingCountDesc();

    List<Project> findAllByOrderByStartDateDesc();

    List<Project> findAllByOrderByTotalFundingPriceDesc();

    List<Project> findAllByOrderByEndDateAsc();

    List<Project> findAllByCategoryOrderByFundingCountDesc(String category);

    List<Project> findAllByCategoryOrderByStartDateDesc(String category);

    List<Project> findAllByCategoryOrderByTotalFundingPriceDesc(String category);

    List<Project> findAllByCategoryOrderByEndDateAsc(String category);

}
