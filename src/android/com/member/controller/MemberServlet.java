package android.com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.member.model.MemberDAO_interface;
import android.com.member.model.MemberService;
import android.com.member.model.MemberDAO;

public class MemberServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String contentType = context.getInitParameter("contentType");
		req.setCharacterEncoding("UTF-8");

		MemberService memSvc=new MemberService();

		Gson gson = new Gson();
		String outStr = "";

		String action = req.getParameter("action");
		System.out.println("參數指令是：" + action);

		/** 比對動作 **/
		if ("isMember".equals(action)) {
			
			String userId = req.getParameter("userId");
			String password = req.getParameter("password");
			outStr = String.valueOf(memSvc.isMember(userId, password));

		}
		res.setContentType(contentType);
		PrintWriter out = res.getWriter();
		System.out.println("結果:" + outStr);
		out.print(outStr);
		out.close();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
