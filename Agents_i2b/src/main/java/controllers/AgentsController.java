package controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Agent;
import domain.AgentLoginData;
import services.AgentsService;
import util.JasyptEncryptor;
import util.exception.AgentNotFoundException;


/**
 * Created by Nicolás on 08/02/2017.
 * Adapted by Alejandro on 14/02/2018 (Agents).
 */
@Controller
public class AgentsController {

    private final AgentsService agentsService;

    AgentsController(AgentsService part){
        this.agentsService = part;
    }

    //The first page shown will be login.html.
    @GetMapping(value="/")
    public String getAgentInfo(Model model) throws IOException {
        model.addAttribute("agentinfo", new AgentLoginData());
        model.addAttribute("kindNames", agentsService.getAgentKindNames());
        return "login";
    }

    //This method process an POST html request once fulfilled the login.html form (clicking in the "Enter" button).
    @RequestMapping(value = "/agentForm", method = RequestMethod.POST)
    public String showInfo(Model model, @ModelAttribute AgentLoginData data, HttpSession session) {
        Agent agent = agentsService.getAgent(data.getLogin(), data.getPassword(), data.getKind());
        if(agent == null) {
            throw new AgentNotFoundException();
        } else {
            model.addAttribute("agent", agent);
            session.setAttribute("agent", agent);
            return "data";
        }
    }

    @RequestMapping(value="/passMenu", method = RequestMethod.GET)
    public String showMenu(Model model){
        //Just in case there must be more processing.
        return "changePassword";
    }

    @RequestMapping(value="/agentChangePassword",method = RequestMethod.POST)
    public String changePassword(Model model, @RequestParam String password
            , @RequestParam String newPassword
            , @RequestParam String newPasswordConfirm
            , HttpSession session) {
    	
        JasyptEncryptor encryptor= new JasyptEncryptor();
        Agent agent = (Agent) session.getAttribute("agent");
        if(encryptor.checkPassword(password, agent.getPassword()) &&
                newPassword.equals(newPasswordConfirm)){
            agentsService.updateInfo(agent, newPassword);
            session.setAttribute("agent", agent);
            model.addAttribute("agent", agent);
            return "data";
        }
        return "changePassword";
    }

}


