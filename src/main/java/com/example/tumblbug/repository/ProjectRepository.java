package com.example.tumblbug.repository;

import com.example.tumblbug.entity.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Slice<Project> findAllByTitleContainingOrderByFundingCountDesc(String title, Pageable pageable);

    Slice<Project> findAllByTitleContainingOrderByStartDateDesc(String title, Pageable pageable);

    Slice<Project> findAllByTitleContainingOrderByTotalFundingPriceDesc(String title, Pageable pageable);

    Slice<Project> findAllByTitleContainingOrderByEndDateAsc(String title, Pageable pageable);

    Slice<Project> findAllByCategoryAndTitleContainingOrderByFundingCountDesc(String category, String title, Pageable pageable);

    Slice<Project> findAllByCategoryAndTitleContainingOrderByStartDateDesc(String category, String title, Pageable pageable);

    Slice<Project> findAllByCategoryAndTitleContainingOrderByTotalFundingPriceDesc(String category, String title, Pageable pageable);

    Slice<Project> findAllByCategoryAndTitleContainingOrderByEndDateAsc(String category, String title, Pageable pageable);

}
