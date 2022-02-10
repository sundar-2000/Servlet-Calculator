package com.calculate;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class operation {
	 int operate(int a,int b) {
		 return 0;
	 }
}

class add extends operation{
	public int operate(int a ,int b) {
		return a+b; 
	}
}

class sub extends operation{
	public int operate(int a ,int b) {
		return a-b; 
	}
}

class mul extends operation{
	public int operate(int a ,int b) {
		return a*b; 
	}
}

class div extends operation{
	public int operate(int a ,int b) {
		return a/b; 
	}
}

class mod extends operation{
	public int operate(int a ,int b) {
		return a%b; 
	}
}

public class Calculate extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String val1 = req.getParameter("num1"), val2 = req.getParameter("num2");
		
		int num1, num2;
		
		res.setContentType("text/html");
		PrintWriter output = res.getWriter();
		
		try {
			num1 = Integer.parseInt(val1);
			num2 = Integer.parseInt(val2);
		} 
		catch (Exception e) {
			output.println("<center><h3>Please Enter valid numbers<h3></center>");
			return;
		}
		
		String op = req.getParameter("operator");
		
		if (is_invalid(op)) {
			output.println("<center><h3>Please Enter valid Operator</h3></center>");
			return;
		}
		
		int ans = 0;
		//Object for upcasting
		operation o;
		
		//Method Overriding
		switch (op.charAt(0)) {
			case '+':
				o = new add();
				break;
			case '-':
				o = new sub();
				break;
			case '*':
				o = new mul();
				break;
			case '/':
				o = new div();
				break;
			default :
				o = new mod();
			
		}
		
		ans = o.operate(num1, num2);
		
		output.println("<center><h3>"+num1 +" "+ op+" "+num2+" = "+ans+"</h3></center>");
	}

	public boolean is_invalid(String s) {
		
		if (s.length() > 1)
			return true;
		
		char valid[] = new char[] { '+', '-', '/', '*', '%' };
		
		for (char i : valid) {
			
			if (i == s.charAt(0))
				return false;
		
		}
		
		return true;

	}

}
