package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.service.test.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*TODO
 * swagger 관련 설정
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/test")
public class DbCheckController {

    private final AdminService adminService;

    @GetMapping("/db")
    public String login(@ModelAttribute("admin") LoginRequestDto admin) {
        return "db-login";
    }

    @PostMapping("/db")
    public String login(@Validated @ModelAttribute("admin") LoginRequestDto admin,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        //값 검증
        boolean isAdminAccount = adminService.loginAdmin(admin);
        if (!isAdminAccount) {
            bindingResult.reject("wrongAdminAccount", "관리자 계정이 아닙니다.");
        }
        if (bindingResult.hasErrors()) {
            return "db-login";
        }

        //로그인 세션 설정
        HttpSession session = request.getSession();
        session.setAttribute("loginAdmin", admin);

        return "redirect:/test/db/main";
    }

    @GetMapping("/db/main")
    public String main() {
        return "db-main";
    }

}
