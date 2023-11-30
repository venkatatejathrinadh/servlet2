package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.UserDto;
import service.UserService;

//Used to Map a request string should be same as action
@WebServlet("/add-task")
public class AddTask extends HttpServlet {
	//Request is Coming from form so do Post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Checking Session ie, User is Logged in
		UserDto dto = (UserDto) req.getSession().getAttribute("user");
		if (dto == null) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			//Receiving values from front end
			String tName = req.getParameter("tname");
			String tDescription = req.getParameter("tdescription");
			
			//Loading values inside objects to save using hibernate
			Task task = new Task();
			task.setName(tName);
			task.setDescription(tDescription);
			task.setCreatedTime(LocalDateTime.now());
			task.setStatus(false);

			//Saving Task
			UserService service = new UserService();
			service.saveTask(task);
			//Getting previous List
			List<Task> tasks = dto.getTasks();
			//Creating new list if its not there
			if (tasks == null)
				tasks = new ArrayList<Task>();
			tasks.add(task);
			//Setting tasks to user
			dto.setTasks(tasks);
			//Updating user
			service.updateUser(dto);

			UserDao dao = new UserDao();
			//Updating session
			req.getSession().setAttribute("user", dao.findByEmail(dto.getEmail()));

			resp.getWriter().print("<h1 align='center' style='color:green'>Task Added Success</h1>");
			//Sending data to Home.jsp
			req.setAttribute("list", dto.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}
}
