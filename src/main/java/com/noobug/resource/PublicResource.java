package com.noobug.resource;

import com.noobug.Result;
import com.noobug.domain.LoginLog;
import com.noobug.repository.LoginLogRepository;
import com.noobug.repository.MenuGossipRepository;
import com.noobug.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZonedDateTime;

@Controller
public class PublicResource {

    @Autowired
    LoginLogRepository loginLogRepository;

    @Autowired
    MenuGossipRepository menuGossipRepository;

    @Value("${noobug.loginToken}")
    String loginToken;

    @PostMapping("/loginSubmit")
    public String login(String name, String token, Model model, HttpSession session, HttpServletRequest request){
        Result result = Result.ok("登录成功", null);
        String ip = IpUtil.getRequestIp(request);

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(token)){
            result = Result.error(1, "必填项不能为空");
        }else{
            name = name.trim();

            if(name.length() > 20){
                result = Result.error(2, "名称长度限制20");
            }else if(!token.equals(loginToken)){
                result = Result.error(3, "密令错误");
            }
        }

        loginLogRepository.save(new LoginLog(null, name, token, ZonedDateTime.now(), ip, result.getSuccess()));
        session.setAttribute("user", name);
        model.addAttribute("result", result);
        model.addAttribute("jump", result.getSuccess() ? "/home" : "/");
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
