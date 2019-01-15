package ie.todolist.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.todolist.daos.ActivitiesDAO;
import ie.todolist.daos.UsersDAO;
import ie.todolist.models.Activities;
import ie.todolist.models.ToDoPriority;
import ie.todolist.models.ToDoStatus;
import ie.todolist.models.Users;
import ie.todolist.validation.ActivitiesValidation;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {
	
	@Autowired
	private ActivitiesDAO activitiesDao;

	@Autowired
	private UsersDAO usersDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ActivitiesValidation());
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public ModelAndView form(Activities activities, HttpSession session) {
		
		Users users = (Users)session.getAttribute("users");
		
		ModelAndView modelAndView = new ModelAndView("/activities/form");
		modelAndView.addObject("getStatus", ToDoStatus.values());
		modelAndView.addObject("getPriority", ToDoPriority.values());
		modelAndView.addObject("userName", users.getUser());
		
		//List my ToDo list from database
		List<Activities> listActivities = activitiesDao.findByUser(users.getId());
		modelAndView.addObject("listActivities", listActivities);
		
		return modelAndView;
	}

	@RequestMapping(value="/form", method= RequestMethod.POST, name="saveActivitie")
	public ModelAndView save(@Valid Activities activities, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {

		if(result.hasErrors()) {
			return form(activities, session);
		}
		
		Users users = (Users)session.getAttribute("users");
		
		Users user = usersDao.findById(users.getId());
		activities.setUser(user);
		
		activitiesDao.save(activities);
		
		redirectAttributes.addFlashAttribute("success", "Inserted new activity");
		//redirect - O direcionamento é do lado do cliente, feito pelo spring
		ModelAndView modelAndView = new ModelAndView("redirect:form");
		//forward - O direcionamanto vai pelo lado do servidor
		//ModelAndView modelAndView = new ModelAndView("forward:form");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", name="deleteActivitie")
	public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
	    
	    activitiesDao.delete(id);
		
		redirectAttributes.addFlashAttribute("success", "activity removed");
		ModelAndView modelAndView = new ModelAndView("redirect:/activities/form");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{id}", name="updateActivitie")
	public ModelAndView update(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
	    
	    Activities activities = activitiesDao.findById(id);
	    
	    redirectAttributes.addFlashAttribute("activities", activities);
		ModelAndView modelAndView = new ModelAndView("redirect:/activities/form");
		
		return modelAndView;    
	}
}
