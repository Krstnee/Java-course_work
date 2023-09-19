package com.example.client;
import com.example.config.UserInfo;
import com.example.config.UserService;
import com.example.operation.Operation;
import com.example.operation.OperationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {

    private final ClientService clientService;
    private final OperationService operationService;

    @Autowired
    private UserService userService;

    @GetMapping("/result")
    public String showOperation(Model model, @Param("keyword") String keyword) {
        List<Operation> listOperations = operationService.listAll(keyword);
        model.addAttribute("listOperations", listOperations);
        model.addAttribute("keyword", keyword);
        return "blog";
    }
    @GetMapping("/new_post")
    public String showNewOperationForm(Model model) {
        Operation operation = new Operation();
        model.addAttribute("operation", operation);
        return "new_post";
    }
    @GetMapping("/edit_post/{post_id}")
    public ModelAndView showEditOperationForm(@PathVariable(name="post_id") Long post_id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Operation operation = operationService.get(post_id);
        mav.addObject("Operation", operation);
        return mav;
    }

    @PostMapping("/auth/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo);
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }
    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Client> listClients = clientService.listAll(keyword);
        model.addAttribute("listClients", listClients);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_DILER')")
    public String showNewClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "new_client";
    }
    @PostMapping("/save_post")
    public String saveOperation(@ModelAttribute("operation") Operation operation) {
        operationService.save(operation);
        return "redirect:/result";
    }
    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditClientForm(@PathVariable(name="id") Long id) {
        ModelAndView mav = new ModelAndView("edit_client");
        Client client = clientService.get(id);
        mav.addObject("Client", client);
        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        clientService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/login_page")
    public String showLogin() {// add any additional objects you want to pass to the HTML page
        return "login_page";
    }
    @GetMapping("/about_us")
    public String showAbout() {// add any additional objects you want to pass to the HTML page
        return "about_us";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }
}


