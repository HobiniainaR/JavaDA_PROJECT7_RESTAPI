package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller class for managing user-related operations.
 * This class handles user management operations such as adding, updating,
 * and deleting users, as well as listing all users.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Displays the list of all users.
     *
     * @param model the {@link Model} object to pass attributes to the view.
     * @return the name of the view to be rendered (user list page).
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Displays the form for adding a new user.
     *
     * @param user the {@link User} object to bind form data.
     * @return the name of the view to be rendered (user add page).
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Validates and saves a new user.
     *
     * @param user   the {@link User} object containing form data.
     * @param result the {@link BindingResult} object to hold validation errors.
     * @param model  the {@link Model} object to pass attributes to the view.
     * @return a redirect to the user list page if successful; otherwise, the user add page.
     */
    @PostMapping("/user/validate")
    public String validate(User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Displays the form for updating an existing user.
     *
     * @param id    the ID of the user to be updated.
     * @param model the {@link Model} object to pass attributes to the view.
     * @return the name of the view to be rendered (user update page).
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        user.setPassword(""); // Clear password field for security reasons
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Updates an existing user with new data.
     *
     * @param id     the ID of the user to be updated.
     * @param user   the {@link User} object containing updated data.
     * @param result the {@link BindingResult} object to hold validation errors.
     * @param model  the {@link Model} object to pass attributes to the view.
     * @return a redirect to the user list page if successful; otherwise, the user update page.
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id    the ID of the user to be deleted.
     * @param model the {@link Model} object to pass attributes to the view.
     * @return a redirect to the user list page.
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteById(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
