package com.tes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tes.model.EmpData;
import com.tes.model.Todo;
import com.tes.model.Users;
import com.tes.services.TodoServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.utilities.AuthenticationUtil;
import com.tes.utilities.Validator;

/**
 * This class used to manage todays working schedule of employee.
 * 
 * @author Jemish Moradiya
 */
@RestController
@RequestMapping(value = {"/admin", "/env", "/thirdParty", "/management"})
public class TodoController
{

	@Autowired
	TodoServices todoServices;

	@Autowired
	UsersServices usersServices;

	@Autowired
	EmpDataServices empDataServices;

	@Autowired
	private AuthenticationUtil authenticationUtil;
	private static final Logger LOGGER = LogManager.getLogger(TodoController.class);

	/**
	 * This method used to load worker todays data
	 * 
	 * @param request the servlet request we are processing.
	 * @return todoId and todoText
	 * @throws JsonProcessingException Intermediate base class for all problems
	 *         encountered when processing (parsing,
	 *         generating) JSON content
	 * @throws JSONException indicates that some exception happened during
	 *         JSON processing.
	 */
	@RequestMapping(value = {"/ajax-load-todos"})
	public @ResponseBody @JsonRawValue String getStatisticsValues(HttpServletRequest request)
			throws JsonProcessingException, JSONException
	{
		String jsonString = null;
		try
		{
			JSONArray jsonArray = new JSONArray();
			// EmpData empDataSession = (EmpData)
			// request.getSession().getAttribute("empDataSession");

			// String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users loggedInUser = authenticationUtil.getLoggedInUser();
			int uId = (int) loggedInUser.getUsersId();
			// int uId = (int) request.getSession().getAttribute("uId");
			List<Object[]> todoDatas = todoServices.findOneByGetAllToDo(uId);

			if (!Validator.isEmpty(todoDatas))
			{
				for (Object[] todoData : todoDatas)
				{
					int todoId = (int) todoData[0];
					String todoText = (String) todoData[1];
					String todoDate = (String) todoData[2];

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("todoText", todoText);
					jsonObject.put("todoId", todoId);
					jsonObject.put("todoDate", todoDate);

					jsonArray.put(jsonObject);
				}
			}
			else
			{
				jsonString = "";
			}

			jsonString = jsonArray.toString();
		}
		catch (Exception e)
		{
			LOGGER.error(e);
		}

		return jsonString;
	}

	/**
	 * This method used to describe the working schedule of worker for daily basis.
	 * 
	 * @param action add todo data
	 * @param todoData todays data
	 * @param id todo id
	 * @param request the servlet request we are processing.
	 * @return add / delete / archive
	 */
	@RequestMapping("/ajax-add-todo")
	public @ResponseBody String WorkerAddTodo(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "todo", required = false) String todoData,
			@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request,
			HttpServletRequest response)
	{

		String ajaxResponse;
		ajaxResponse = "";
		try
		{

			if (action.equals("add"))
			{
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String createDate = formatter.format(date);

				EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
				int empId = empDataSession.getEmpDataId();

				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");

				todoServices.save(todo);
				ajaxResponse = "add";
			}
			else if (action.equals("delete"))
			{

				todoServices.findOneByDeleteToDo(id);
				ajaxResponse = "delete";
			}
			else if (action.equals("archive"))
			{

				todoServices.updateToDoAction(id);
				ajaxResponse = "archive";
			}
		}
		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return ajaxResponse;
	}
}
