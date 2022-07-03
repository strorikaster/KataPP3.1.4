package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.ExceptionHandler.NoSuchUserException;
import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/users")
    public  ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<User> getPrincipalUser(Principal principal) {
       //Principal principal = request.getUserPrincipal();
        System.out.println(principal.toString());
        User user = (User)userService.loadUserByUsername((userService.getUserByName(principal.getName())).getEmail());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID =  " + id + " in Database");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


//    @PostMapping("/users")
//    public User addNewUser(@RequestBody User user, @RequestParam Long[] rolesId) {
//        HashSet<Role> roles = new HashSet<>();
//        for(Long roleId : rolesId) {
//            roles.add(roleService.show(roleId));
//        }
//        user.setRoles(roles);
//        userService.save(user);
//        return user;
//    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user/*, BindingResult bindingResult*/) {
        /*if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user/*, BindingResult bindingResult*/) {
       /* if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database");
        }
        userService.delete(id);
        //return "User with ID " + id + " was delete";
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

}
