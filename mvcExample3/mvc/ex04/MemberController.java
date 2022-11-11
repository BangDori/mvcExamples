package ch05.ex04;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ex04")
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("/list")
	public String memberlistPage(Model model) {
		model.addAttribute("members", dao.listMembers());
		
		return "ex04/listMembers";
	}
		
	@RequestMapping("/addForm")
	public String addForm() {
		return "ex04/addForm";
	}
	
	@RequestMapping("/add")
	public String addNewMember(Model model, MemberVO member) {
		dao.addMember(member);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list/{id}", method=RequestMethod.GET)
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("member", dao.getMember(id));
		
		return "ex04/updateForm";
	}
	
	@RequestMapping(value="/list/update", method=RequestMethod.POST)
	public String updateMember(Model model, MemberVO member) {
		dao.updateMember(member);
		
		return "redirect:/ex04/list";
	}
	
	@RequestMapping(value="/list/delete/{id}")
	public String deleteMember(@PathVariable int id) {
		dao.deleteMember(id);
		
		return "redirect:/ex04/list";
	}
}
