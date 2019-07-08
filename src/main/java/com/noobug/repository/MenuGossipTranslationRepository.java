package com.noobug.repository;

import com.noobug.domain.MenuGossipTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuGossipTranslationRepository extends JpaRepository<MenuGossipTranslation, Long> {
    List<MenuGossipTranslation> findTop5ByMenuIdOrderByTimeDesc(Long id);
}
