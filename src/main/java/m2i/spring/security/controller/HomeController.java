package m2i.spring.security.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
@GetMapping("/")	
public ModelAndView getHome(Principal principal) {
	ModelAndView mv = new ModelAndView("index");
	mv.addObject("userName",principal.getName());
	return mv;
}

}
