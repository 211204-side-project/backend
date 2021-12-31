package com.scope.socialboardweb.controller.test;

import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.dto.test.CommentTableEntityDto;
import com.scope.socialboardweb.dto.test.PostTableEntityDto;
import com.scope.socialboardweb.dto.test.UserTableEntityDto;
import com.scope.socialboardweb.service.test.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/db/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/test/db";
    }

    @GetMapping("/db/table/user")
    public String showUserTable(Model model) {
        List<String> entityFieldsNames = getEntityFieldsNames(UserTableEntityDto.class);
        List<UserTableEntityDto> allUsers = adminService.getAllUserEntities();


        model.addAttribute("entities", allUsers);
        model.addAttribute("entityFieldsNames", entityFieldsNames);
        model.addAttribute("entityName", "User");
        return "db-table";
    }

    @GetMapping("/db/table/post")
    public String showPostTable(Model model) {
        List<String> entityFieldsNames = getEntityFieldsNames(PostTableEntityDto.class);
        List<PostTableEntityDto> allPosts = adminService.getAllPostEntities();


        model.addAttribute("entities", allPosts);
        model.addAttribute("entityFieldsNames", entityFieldsNames);
        model.addAttribute("entityName", "Post");
        return "db-table";
    }

    @GetMapping("/db/table/comment")
    public String showCommentTable(Model model) {
        List<String> entityFieldsNames = getEntityFieldsNames(CommentTableEntityDto.class);
        List<CommentTableEntityDto> allCommentEntities = adminService.getAllCommentEntities();


        model.addAttribute("entities", allCommentEntities);
        model.addAttribute("entityFieldsNames", entityFieldsNames);
        model.addAttribute("entityName", "Comment");
        return "db-table";
    }



    // 필드명(칼럼명) 가져오기
    private <T> List<String> getEntityFieldsNames(Class<T> dtoType) {
        List<String> entityFieldsNames = new ArrayList<>();
        Field[] declaredFields = dtoType.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            entityFieldsNames.add(declaredField.getName());
        }
        return entityFieldsNames;
    }

}
