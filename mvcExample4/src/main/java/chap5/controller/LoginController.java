package chap5.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chap5.model.MemberDAO;
import chap5.model.MemberVO;

@Controller
public class LoginController {
	@Autowired
	MemberDAO dao;
	HttpSession session;
	
	@RequestMapping(value = "/ex2/login", method = RequestMethod.GET)
	public String loginReq(Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/ex2/login", method=RequestMethod.POST)
	public String loginProcessing(HttpServletRequest req, Model model) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HashMap<String, Boolean> errors = new HashMap<>();
		
		if (email == null || email.isEmpty())
			errors.put("email", true);
		if (password == null || password.isEmpty())
			errors.put("password", true);
		
		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			return "message";
		}
		
		MemberVO member = dao.getMemberByEmail(email);
		if(member == null)
			errors.put("notfound", true);
		else if (!member.getPassword().equals(password))
			errors.put("mismatch", true);
		
		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);
			return "message";
		}
		
		session = req.getSession();
		session.setAttribute("login", member);
		
		return "redirect:list";
	}
	
	@RequestMapping("/ex2/logout")
	public String logout(HttpServletRequest req, Model model) {
		session.removeAttribute("login");
		
		return "redirect:list";
	}

}
