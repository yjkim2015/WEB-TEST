package com.quick.yjk.main;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.yjk.common.component.CommonController;

@Controller
public class MainController extends CommonController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String rootView(final Locale locale, final Model model) {
		System.out.println("MainController redirect login");

		return "redirect:/login/index";
	}
	
	@RequestMapping(value="/admin/main", method = RequestMethod.GET) 
	public String adminmain() {
		System.out.println("adminmain");
		return "admin/index";	
	}
	
	@RequestMapping(value="/warehouse/main", method = RequestMethod.GET) 
	public String warehousemain(Model model, HttpServletRequest request) {
		final HttpSession session = request.getSession();
		model.addAttribute("userVo", session.getAttribute("userVo"));
		return "warehouse/index";	
	}
	
	@RequestMapping(value="/driver/main", method = RequestMethod.GET) 
	public String drivermain() {
		
		return "driver/index";	
	}
}
