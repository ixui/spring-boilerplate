package jp.co.ixui.spring.boilerplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping(value = {"/admin", "/admin/dashboard"})
	public String index() {
		return "admin/dashboard";
	}

}
