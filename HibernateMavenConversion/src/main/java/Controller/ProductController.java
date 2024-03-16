package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.ProductDTO;
@WebServlet("/addProduct")
public class ProductController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pName=req.getParameter("productName");
		double pPrice=Double.parseDouble(req.getParameter("productPrice"));
		String pCat=req.getParameter("productCat");
		int pQty=Integer.parseInt(req.getParameter("productQty"));
		
		ProductDTO p=new ProductDTO();
		
		p.setProductName(pName);
		p.setProductPrice(pPrice);
		p.setProductCat(pCat);
		p.setProductQty(pQty);
		
		Session session=new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(ProductDTO.class).buildSessionFactory().openSession();
		Transaction tr=session.beginTransaction();
		
		session.save(p);
		tr.commit();
		session.close();
	}

}
