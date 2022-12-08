package chap6.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import chap6.model.ArticleDAO;
import chap6.model.ArticleVO;
import chap6.model.ContentDAO;
import chap6.model.ContentVO;
import chap6.model.MemberVO;

@Controller
@RequestMapping("/ex2")
public class ArticleController {
	@Autowired
	ArticleDAO articleDAO;
	@Autowired
	ContentDAO contentDAO;
	
	@RequestMapping("/list")
	public String listArticles(Model model) {
		List<ArticleVO> articles = articleDAO.getAllArticles();
		model.addAttribute("articles", articles);
		
		return "listArticles";
	}
	
	@RequestMapping("/write")
	public String writeArticle(HttpServletRequest req, Model model) {
		return "writeForm";
	}
	
	@RequestMapping("/writeReq")
	public String addArticle(HttpServletRequest req, Model model) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		ArticleVO articleVO = new ArticleVO();
		ContentVO contentVO = new ContentVO();
		
		articleVO.setTitle(title);
		articleVO.setWriter(Integer.parseInt(req.getParameter("writer")));
		articleVO.setReadcnt(Integer.parseInt(req.getParameter("readcnt")));
		
		if(title != null || !title.isEmpty() ||
				content != null || !content.isEmpty()) {
			articleDAO.addArticle(articleVO);
			
			contentVO.setAid(articleDAO.getArticleNo(title));
			contentVO.setContent(content);
			
			contentDAO.addArticle(contentVO);
		}
				
		return "redirect:list";
	}
	
	@RequestMapping("/read")
	public String readArticle(HttpServletRequest req, Model model) {
		articleDAO.updateReadcnt(
				Integer.parseInt(req.getParameter("aid")));
		ArticleVO articleVO = articleDAO.getArticleById(
				Integer.parseInt(req.getParameter("aid")));
		ContentVO contentVO = contentDAO.getArticleById(
				Integer.parseInt(req.getParameter("aid")));
		
		req.setAttribute("article", articleVO);
		req.setAttribute("content", contentVO);
		
		return "readForm";
	}
	
	@RequestMapping("/update")
	public String updateArticle(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("login");
		ArticleVO articleVO = articleDAO.getArticleById(
				Integer.parseInt(req.getParameter("aid")));
		
		if(member != null && member.getName().equals(articleVO.getName())) {
			ContentVO contentVO = contentDAO.getArticleById(
					Integer.parseInt(req.getParameter("aid")));
			req.setAttribute("article", articleVO);
			req.setAttribute("content", contentVO);
			
			return "updateForm";			
		} else {
			HashMap<String, Boolean> errors = new HashMap<>();
			errors.put("auth", true);
			
			req.setAttribute("error", "auth");
			model.addAttribute("errors", errors);

			return "message";
		}

	}
	
	@RequestMapping("/updateReq")
	public String updateReqArticle(HttpServletRequest req, Model model) {
		Map<String, String> map = new HashMap<>();
		map.put("aid", req.getParameter("aid"));
		map.put("title", req.getParameter("title"));
		map.put("content", req.getParameter("content"));
		
		articleDAO.updateArticle(map);
		contentDAO.updateArticle(map);
		
		HashMap<String, Boolean> exec = new HashMap<>();
		exec.put("update", true);
		
		req.setAttribute("execute", true);
		model.addAttribute("exec", exec);
		
		return "message";
	}
	
	@RequestMapping("/delete")
	public String deleteArticle(HttpServletRequest req, Model model) {
		int aid = Integer.parseInt(req.getParameter("aid"));

		HttpSession session = req.getSession();
		MemberVO member = (MemberVO) session.getAttribute("login");
		ArticleVO articleVO = articleDAO.getArticleById(
				Integer.parseInt(req.getParameter("aid")));
		
		if(member != null && member.getName().equals(articleVO.getName())) {			
			articleDAO.deleteArticle(aid);
			contentDAO.deleteArticle(aid);
	
			HashMap<String, Boolean> exec = new HashMap<>();
			exec.put("delete", true);
			
			req.setAttribute("execute", true);
			model.addAttribute("exec", exec);
		} else {
			HashMap<String, Boolean> errors = new HashMap<>();
			errors.put("auth", true);
			
			req.setAttribute("error", "auth");
			model.addAttribute("errors", errors);
		}
		
		return "message";
	}
}
