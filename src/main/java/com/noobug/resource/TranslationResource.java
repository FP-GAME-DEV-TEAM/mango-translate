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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZonedDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/translation")
public class TranslationResource {
    @Autowired
    MenuGossipRepository menuGossipRepository;

    @Autowired
    MenuGossipTranslationRepository menuGossipTranslationRepository;

    @PostMapping("/menuGossipTranslateSubmit")
    public String menuGossipTranslateSubmit(Long id, String translation, Model model, HttpSession session, HttpServletRequest request){
        Object loginName = session.getAttribute("user");
        Result result = Result.ok("翻译已提交，将会在后台进行审核", null);

        if(StringUtils.isEmpty(loginName)){
            result = Result.error(1, "请先登录");
        } else {
            menuGossipTranslationRepository.save(new MenuGossipTranslation(null, id, loginName.toString(), translation, ZonedDateTime.now(), Boolean.FALSE));
        }

        model.addAttribute("result", result);
        model.addAttribute("jump", result.getCode() == 1 ? "/" : "/translate/menuGossip");
        return "result";
    }

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/translate/menuGossip")
    public String menuGossip(Model model){
        model.addAttribute("gossips", menuGossipRepository.findAllByNeedTranslate(Boolean.TRUE));
        return "menu_gossip";
    }
}
