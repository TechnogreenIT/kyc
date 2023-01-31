package com.tes.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tes.model.Consent;
import com.tes.model.EmpData;
import com.tes.model.HazardousManifest;
import com.tes.model.RegAmbientPoll;
import com.tes.model.RegEffPoll;
import com.tes.model.RegSewPoll;
import com.tes.model.RegStPoll;
import com.tes.model.RegularData;
import com.tes.model.Todo;
import com.tes.model.Users;
import com.tes.services.TodoServices;
import com.tes.services.UsersServices;
import com.tes.services.admin.CompanyProfileServices;
import com.tes.services.admin.EmpDataServices;
import com.tes.services.environmentalofficer.ConsentServices;
import com.tes.services.environmentalofficer.HazardousManifestServices;
import com.tes.services.environmentalofficer.RegularDataServices;
import com.tes.services.thirdparty.RegAmbientPollServices;
import com.tes.services.thirdparty.RegEffPollServices;
import com.tes.services.thirdparty.RegSewPollServices;
import com.tes.services.thirdparty.RegStPollServices;
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
	ConsentServices consentServices;

	@Autowired
	private AuthenticationUtil authenticationUtil;

	@Autowired
	RegularDataServices regularDataServices;

	@Autowired
	HazardousManifestServices hazardousManifestServices;

	@Autowired
	RegStPollServices regStPollServices;

	@Autowired
	RegAmbientPollServices regAmbientPollServices;

	@Autowired
	RegEffPollServices regEffPollServices;

	@Autowired
	RegSewPollServices regSewPollServices;

	@Autowired
	CompanyProfileServices companyProfileServices;

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

	// ////mmmmmm Env
	@RequestMapping("/ajax-todosteps-add")
	public @ResponseBody String stepsAddTodo(HttpServletRequest request,
			HttpServletRequest response)
	{

		String ajaxResponse;
		ajaxResponse = "";
		try
		{
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = formatter.format(date);

			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int empId = empDataSession.getEmpDataId();
			int todoDatas = todoServices.deletecheckTodoData(empId);
			// check data present or not consent table.
			// Integer consent = consentServices.checkDataPresent();
			List<Consent> consentE = consentServices.checkDataPresent("Consent to Establish", new PageRequest(0, 1));
			List<Consent> consentO = consentServices.checkDataPresent("Consent to Operate", new PageRequest(0, 1));

			if (consentE.isEmpty())
			{
				String todoData = "Fill Consent to Establish";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";
			}
			if (consentO.isEmpty())
			{
				String todoData = "Fill Consent to Operate";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";
			}
			// check data present or not regulardata table.
			List<RegularData> regularDatas = regularDataServices.getCheckRegularData(createDate, new PageRequest(0, 1));
			if (regularDatas.isEmpty())
			{
				String todoData = "Fill Daily Input";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";

			}
			// check data present or not hazardous_manifest table.
			List<HazardousManifest> hazmanifest = hazardousManifestServices.checkHazManifestDTPresent(new PageRequest(0, 1));
			if (hazmanifest.isEmpty())
			{
				String todoData = "Fill Waste Manifest";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";

			}
			else
			{
				String manifestDate = hazmanifest.get(0).getSubmittedDate();
				LocalDate finalDate = LocalDate.parse(manifestDate);
				LocalDate todayDate = LocalDate.now();

				LocalDate day90before = todayDate.minusDays(90);
				// DAYS.between(dateBefore, dateAfter);
				Date dateFirst = Date.from(day90before.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date secondDate = Date.from(finalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				long time_difference = dateFirst.getTime() - secondDate.getTime();
				long days_difference = TimeUnit.MILLISECONDS.toDays(time_difference) % 365;
				long days = Math.abs(days_difference);
				long staticDay = 7l, staticDay2 = 5l;
				if (days == staticDay || days == staticDay2)
				{
					// String todoData = "Hazardous Waste Dispose .... Days Remaining";
					String todoData = "Hazardous Waste Dispose Within  " + days + " Days.";
					EmpData empData = new EmpData();
					empData.setEmpDataId(empId);
					Todo todo = new Todo();
					todo.setTodo(todoData);
					todo.setCreateDate(createDate);
					todo.setEmpData(empData);
					todo.setAction("new");
					todoServices.save(todo);
					ajaxResponse = "Success";
				}

			}

			// check data present or not view compliance form(regstpoll,regambientpoll,regeffpoll,reegsewpoll) table.
			List<RegStPoll> regStackData = regStPollServices.checkRegSTPollData(new PageRequest(0, 1));
			List<RegAmbientPoll> regAmbientPoll = regAmbientPollServices.checkRegAmbientPollData(new PageRequest(0, 1));
			List<RegEffPoll> regEffPoll = regEffPollServices.checkRegEffPollData(new PageRequest(0, 1));
			List<RegSewPoll> regSewPoll = regSewPollServices.checkRegSewPollData(new PageRequest(0, 1));

			if (regStackData.isEmpty() && regAmbientPoll.isEmpty() && regEffPoll.isEmpty() && regSewPoll.isEmpty())
			{
				String todoData = "View Compliance Forms";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";
			}

		}

		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return ajaxResponse;
	}

	// /////mmmm
	//// admin TODO steps
	@RequestMapping("/ajax-todostepsadmin-add")
	public @ResponseBody String stepsAddAdminTodo(HttpServletRequest request,
			HttpServletRequest response)
	{

		String ajaxResponse;
		ajaxResponse = "";
		try
		{
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String createDate = formatter.format(date);

			EmpData empDataSession = (EmpData) request.getSession().getAttribute("empDataSession");
			int empId = empDataSession.getEmpDataId();
			int todoDatas = todoServices.deletecheckTodoData(empId);

			// / for Admin login
			// check data present or not company_profile table.
			Integer companyProfile = companyProfileServices.checkcompanyprofData();

			if (companyProfile == 0)
			{
				String todoData = "Create Company Profile";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";
			}

			// Admin users profile
			// check data present or not users table.
			List<Users> usersData = usersServices.checkUserData(new PageRequest(0, 1));
			if (usersData.isEmpty())
			{
				String todoData = "Create Three Users";
				EmpData empData = new EmpData();
				empData.setEmpDataId(empId);
				Todo todo = new Todo();
				todo.setTodo(todoData);
				todo.setCreateDate(createDate);
				todo.setEmpData(empData);
				todo.setAction("new");
				todoServices.save(todo);
				ajaxResponse = "Success";
			}
		}

		catch (Exception e)
		{

			LOGGER.error(e);
		}

		return ajaxResponse;
	}
	////
}
