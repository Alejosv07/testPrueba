package com.usercrud.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usercrud.app.domains.User;
import com.usercrud.app.services.UserServiceImp;

import jakarta.validation.Valid;

@Controller
@RequestMapping({ "/user/" })
@SessionAttributes("user")
public class UserController {

	@Autowired
	UserServiceImp userServiceImp;
	
	@GetMapping("index")
	public String index(Model model,SessionStatus status) {
		status.setComplete();
		model.addAttribute("listUser", this.userServiceImp.fetchUserList());
		return "/user/index";
	}

	@GetMapping("details/{id}")
	public String detail(Model model, @PathVariable Long id) {
		model.addAttribute("user", this.userServiceImp.finById(id));
		return "/user/details";
	}

	@GetMapping("remove/{id}")
	public String remove(Model model, @PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			try {
				this.userServiceImp.deleteUserById(this.userServiceImp.finById(id).getId());
				flash.addFlashAttribute("messageSucces", "Éxito en remover");
			} catch (Exception e) {
				flash.addFlashAttribute("messageError", e.getMessage());
				e.printStackTrace();
			}
		} else {
			flash.addFlashAttribute("messageError", "ID was 0.");
		}
		return "redirect:/user/index";
	}
	
	@GetMapping("update/{id}")
	public String update(Model model, @PathVariable Long id, RedirectAttributes flash) {
		if (id > 0) {
			try {
				model.addAttribute("user",userServiceImp.finById(id));
			} catch (Exception e) {
				flash.addFlashAttribute("messageError", e.getMessage());
				e.printStackTrace();
				return "redirect:/user/index";
			}
		} else {
			flash.addFlashAttribute("messageError", "ID was 0.");
			return "redirect:/user/index";
		}
		return "/user/update";
	}
	


	@RequestMapping(value= "update", method = {RequestMethod.POST, RequestMethod.GET})
	public String update(Model model,@Valid User user, BindingResult result, RedirectAttributes flash) {
		try {
			if(result.hasErrors()) {
				return "/user/update";
			}
			this.userServiceImp.updateUser(user);
			flash.addFlashAttribute("messageSucces", "Éxito en actualizar");
			return "redirect:/user/index";
		} catch (Exception e) {
			flash.addFlashAttribute("messageError", e.getMessage());
			e.printStackTrace();
			List<String> errorList = result.getAllErrors().stream().map(Object::toString).collect(Collectors.toList());
			flash.addFlashAttribute("errorList",errorList);
			return "/user/update";
		}
	}
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}
	
	@PostMapping("add")
	public String add(Model model,@Valid User user, BindingResult result, RedirectAttributes flash) {
		try {
			if(result.hasErrors()) {
				return "/user/add";
			}
			this.userServiceImp.saveUser(user);
			flash.addFlashAttribute("messageSucces", "Éxito en agregar");
		} catch (Exception e) {
			flash.addFlashAttribute("messageError", e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/user/index";
	}
	
	@ModelAttribute("title")
	public String title() {
		return "User";
	}
}
