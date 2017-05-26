package jp.co.ixui.spring.boilerplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

	@GetMapping("/content")
	public String index() {
		return "content/index";
	}

}
