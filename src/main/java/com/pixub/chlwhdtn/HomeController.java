package com.pixub.chlwhdtn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	Connection con;
	Statement stat;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String testJson;

		try {
			con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Choi\\VS");
			stat = con.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS TESTABLE (msg VARCHAR(100))");
			stat.close();
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		testJson = "{\"response\":\"" + request.getParameter("lspd") + "\"}";
		
		
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print(testJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public void test(HttpServletResponse response) {
		String testJson = "{\"response\":\"" + "pong" + "\"}";
		
		try {
			response.setCharacterEncoding("UTF-8"); // ¿Ã∞≈ «ÿ¡‡æﬂ «—±€ æ»±˙¡¸
			response.setContentType("text/html; charset=utf-8"); // ¿Ã∞≈ «ÿ¡‡æﬂ «—±€ æ»±˙¡¸
			response.getWriter().print(testJson); // ø©±‚ø° µÈæÓ∞£ ∞¥√º∞° ∆‰¿Ã¡ˆø° ∂‰
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
