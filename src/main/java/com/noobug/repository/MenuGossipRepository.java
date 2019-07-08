package com.noobug.repository;

import com.noobug.domain.MenuGossip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuGossipRepository extends JpaRepository<MenuGossip, Long> {

    List<MenuGossip> findAllByNeedTranslate(Boolean needTranslate);
}
