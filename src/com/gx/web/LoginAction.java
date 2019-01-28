package com.gx.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gx.po.User;
import com.gx.service.IUserService;
import com.gx.vo.RowCount;
import com.gx.vo.UserVo;

@Controller
@RequestMapping("/loginController")
public class LoginAction {
	@Autowired
	public IUserService userService;
	
	@RequestMapping("/login")
	public String login(String username, String password, HttpServletRequest request){
		String SQL = " where username='" + username + "' and password='" + password +"'";
		List<User> list = userService.findBySQL(SQL);
		if(list.size() == 1){
			User guser = list.get(0);
			if(guser.getUsername().equals(username) && guser.getPassword().equals(password)){
				request.getSession().setAttribute("username", username);
				return findAll(request);
			}else{
				return "/fail";
			}
		}else{
			return "/fail";
		}
	}
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request){
		List<User> list = userService.findAll();
		request.getSession().setAttribute("users", list);
		String SQL = "";
		List<UserVo> volist = userService.findVoBySQL(SQL);
		request.getSession().setAttribute("powerVos", volist);
		return "redirect:/loginController/main.do";//重定向Controller
	}
	
	@RequestMapping("/main")
	public String main(){
		return "/main";
	}
	
	@RequestMapping("/login2")
	public ModelAndView login2(String username, String password, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String SQL = " where username='" + username + "' and password='" + password +"'";
		List<User> list = userService.findBySQL(SQL);
		List<User> users = null;
		if(list.size() == 1){
			User guser = list.get(0);
			if(guser.getUsername().equals(username) && guser.getPassword().equals(password)){
				mav = new ModelAndView("/main");
				users = userService.findAll();
			}else{
				mav = new ModelAndView("/login");
			}
		}else{
			mav = new ModelAndView("/login");
		}
		mav.addObject("users", users);
		mav.addObject("username", username);
		return mav;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "/add";
	}
	
	@RequestMapping("/add")
	public String insert(User user, HttpServletRequest request){
		int result = userService.insertSelective(user);
		return findAll(request);
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(int userId, HttpServletRequest request){
		User user = userService.selectByPrimaryKey(userId);
		request.getSession().setAttribute("user", user);
		return "/update";
	}
	
	@RequestMapping("/update")
	public String update(User user, HttpServletRequest request){
		int result = userService.updateByPrimaryKeySelective(user);
		return findAll(request);
	}
	
	@RequestMapping("/delete")
	public String delete(int userId, HttpServletRequest request){
		int result = userService.deleteByPrimaryKey(userId);
		return findAll(request);
	}
	
	@RequestMapping("/rowCount")
	public void rowCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String SQL = "";
		RowCount rowCount = userService.rowCount(SQL);
		int count = rowCount.getCount();
		JSONObject json = new JSONObject();
		json.put("count", count);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	@RequestMapping("/findPageByVoSQL")
	public void findPageByVoSQL(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String SQL = "";
		int currentPage = 0;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int pageSize = 0;
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		currentPage = currentPage - 1;
		currentPage = currentPage * pageSize;
		List<UserVo> powerVos = userService.findPageByVoSQL(SQL, currentPage, pageSize);
		JSONObject json = new JSONObject();
		json.put("data", powerVos);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
}
