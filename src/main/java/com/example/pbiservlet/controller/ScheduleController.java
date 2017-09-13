package com.example.pbiservlet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.pbiservlet.repository.ScheduleRepository;
import com.example.pbiservlet.repository.Schedule;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleRepository repository;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String listPost(Model model) {
		model.addAttribute("schedules", repository.findAll());
		return "schedules/list";
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/schedules");
    }
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
    public String newProject() {
        return "schedules/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("topic") String topic, String date) {
        repository.save(new Schedule(topic, date));
        return new ModelAndView("redirect:/schedules");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("schedule_id") long id,
    							@RequestParam("topic") String topic, String date) {
        Schedule schedule = repository.findOne(id);
        schedule.setTopic(topic);
        schedule.setDate(date);
        repository.save(schedule);
        return new ModelAndView("redirect:/schedules");
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Schedule schedule = repository.findOne(id);
        model.addAttribute("schedule", schedule);
        return "schedules/edit";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(@RequestParam("topic") String topic, Model model)
	{
		model.addAttribute("schedules", repository.findByTopicContainingIgnoreCase(topic));
		return "schedules/list";
	}
}
