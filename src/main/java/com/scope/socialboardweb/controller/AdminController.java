package com.scope.socialboardweb.controller;

import com.scope.socialboardweb.domain.Comment;
import com.scope.socialboardweb.domain.Post;
import com.scope.socialboardweb.domain.User;
import com.scope.socialboardweb.dto.LoginRequestDto;
import com.scope.socialboardweb.dto.table.CommentTableEntityDto;
import com.scope.socialboardweb.dto.table.PostTableEntityDto;
import com.scope.socialboardweb.dto.table.UserTableEntityDto;
import com.scope.socialboardweb.service.AdminService;
import com.scope.socialboardweb.service.TableNames;
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
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

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

        return "redirect:/admin/db/main";
    }

    @GetMapping("/db/main")
    public String main() {
        return "db-main";
    }

    @GetMapping("/db/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/db";
    }

    @GetMapping("/db/table/user")
    public String showUserTable(Model model) throws Exception {
        List<String> dtoFieldsNames = getEntityFieldsNames(UserTableEntityDto.class);
        List<UserTableEntityDto> allUsers = adminService.getAllEntities(User.class, UserTableEntityDto.class);

        model.addAttribute("dtos", allUsers);
        model.addAttribute("dtoFieldsNames", dtoFieldsNames);
        model.addAttribute("entityName", TableNames.USER);

        return "db-table";
    }

    @GetMapping("/db/table/post")
    public String showPostTable(Model model) throws Exception {
        List<String> dtoFieldsNames = getEntityFieldsNames(PostTableEntityDto.class);
        List<PostTableEntityDto> allPosts = adminService.getAllEntities(Post.class, PostTableEntityDto.class);

        model.addAttribute("dtos", allPosts);
        model.addAttribute("dtoFieldsNames", dtoFieldsNames);
        model.addAttribute("entityName", TableNames.POST);
        return "db-table";
    }

    @GetMapping("/db/table/comment")
    public String showCommentTable(Model model) throws Exception {
        List<String> dtoFieldsNames = getEntityFieldsNames(CommentTableEntityDto.class);
        List<CommentTableEntityDto> allComments = adminService.getAllEntities(Comment.class, CommentTableEntityDto.class);


        model.addAttribute("dtos", allComments);
        model.addAttribute("dtoFieldsNames", dtoFieldsNames);
        model.addAttribute("entityName", TableNames.COMMENT);
        return "db-table";
    }

    @GetMapping("/db/table/follow")
    public String showFollowTable(Model model) throws Exception {
        return "db-table-not-show";
    }
    @GetMapping("/db/table/alert")
    public String showAlertTable(Model model) throws Exception {
        return "db-table-not-show";
    }
    @GetMapping("/db/table/chat-room")
    public String showChatRoomTable(Model model) throws Exception {
        return "db-table-not-show";
    }
    @GetMapping("/db/table/chat")
    public String showChatTable(Model model) throws Exception {
        return "db-table-not-show";
    }

    @DeleteMapping("/db/table/{tableName}/{rowPk}")
    public String deleteRow(@PathVariable("tableName") TableNames tableName,
                            @PathVariable("rowPk") Long rowPk,
                            RedirectAttributes redirectAttributes) {
        adminService.deleteRow(tableName, rowPk);
        return "redirect:/admin/db/table/" + tableName.toString().toLowerCase(Locale.ROOT);
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
