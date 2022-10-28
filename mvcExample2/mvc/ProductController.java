package product;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductDAO productDAO;
    HttpSession session;
    ArrayList<ProductVO> basket;
    
    public ProductController() {
    	productDAO = new ProductDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();

		System.out.println("action: " + action);
		if(action == null || action.equals("/*") || action.equals("/list.do")) {
			ArrayList<ProductVO> productList = productDAO.getProductList();
			request.setAttribute("productList", productList);
			
			forwardReq(request, response, "/list.jsp");
		} else if(action.equals("/detail.do")) {
			ProductVO productVO = productDAO.getProductById(Integer.parseInt(request.getParameter("id")));
			productDAO.updateReadCnt(Integer.parseInt(request.getParameter("id")), productVO.getReadcnt());
			request.setAttribute("productVO", productVO);
		
			forwardReq(request, response, "/detail.jsp");
		} else if(action.equals("/buy.do")) {			
			if(basket == null) {
				session = request.getSession(true);

				basket = new ArrayList<>();
				session.setAttribute("basket", basket);
			}
			
			if(request.getParameter("id") != null) {
				ProductVO productVO = productDAO.getProductById(Integer.parseInt(request.getParameter("id")));
				basket.add(productVO);
			}
			
			forwardReq(request, response, "/buy.jsp");
		} else if(action.equals("/reset.do")) {
			basket.clear();
			
			forwardReq(request, response, "/buy.jsp");
		}
	}
	
	private void forwardReq(HttpServletRequest request, HttpServletResponse response, String nextPage)
			throws ServletException, IOException  {
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
