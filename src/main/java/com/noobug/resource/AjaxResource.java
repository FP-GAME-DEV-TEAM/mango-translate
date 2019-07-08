package com.noobug.resource;

import com.noobug.Result;
import com.noobug.domain.LoginLog;
import com.noobug.domain.MenuGossipTranslation;
import com.noobug.repository.LoginLogRepository;
import com.noobug.repository.MenuGossipRepository;
import com.noobug.repository.MenuGossipTranslationRepository;
import com.noobug.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxResource {

    @Autowired
    MenuGossipRepository menuGossipRepository;

    @Autowired
    MenuGossipTranslationRepository menuGossipTranslationRepository;

    @GetMapping("/getMenuGossipRecentTranslation")
    public ResponseEntity<Result> getMenuGossipRecentTranslation(Long id, Model model, HttpSession session, HttpServletRequest request){
        List<MenuGossipTranslation> result = menuGossipTranslationRepository.findTop5ByMenuIdOrderByTimeDesc(id);

        return ResponseEntity.ok(Result.ok(result));
    }
}
