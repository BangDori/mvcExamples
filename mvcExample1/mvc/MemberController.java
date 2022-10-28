package com.yu;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;
       
    public MemberController() {
        super();
        memberDAO = new MemberDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = null;
		action = request.getPathInfo();
		
		System.out.println("action: " + action);
		if(action == null || action.equals("/*") || action.equals("/listMembers.do")) {
			ArrayList<MemberVO> memberList = memberDAO.getMembers();
			request.setAttribute("memberList", memberList);
			
			forwardReq(request, response, "/listMembers.jsp");
		} else if(action.equals("/insertReq.do")) {
			forwardReq(request, response, "/insert.jsp");
		} else if(action.equals("/insert.do")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setName(request.getParameter("name"));
			memberVO.setPassword(request.getParameter("password"));
			memberVO.setEmail(request.getParameter("email"));
			memberVO.setRegdate(request.getParameter("regdate"));
			
			memberDAO.insertMember(memberVO);
			forwardReq(request, response, "/member/listMembers.do");
		} else if(action.equals("/updateReq.do")) {
			MemberVO memberVO = memberDAO.getMember(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("memberVO", memberVO);
			
			forwardReq(request, response, "/update.jsp");
		} else if(action.equals("/update.do")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(Integer.parseInt(request.getParameter("id")));
			memberVO.setName(request.getParameter("name"));
			memberVO.setPassword(request.getParameter("password"));
			memberVO.setEmail(request.getParameter("email"));
			memberVO.setRegdate(request.getParameter("regdate"));
			
			memberDAO.updateMember(memberVO);
			forwardReq(request, response, "/member/listMembers.do");
		} else if(action.equals("/delete.do")) {
			memberDAO.deleteMember(Integer.parseInt(request.getParameter("id")));
			
			forwardReq(request, response, "/member/listMembers.do");
		}
	}
	
	protected void forwardReq(HttpServletRequest request, HttpServletResponse response, String nextPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
