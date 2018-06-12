package es.uniovi.asw.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Incident;
import com.uniovi.entities.IncidentState;
import com.uniovi.entities.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorsService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InProcessIncidenceSteps {

	@Autowired
	private IncidentsService inciService;
	
	@Autowired
	private OperatorsService opService;
	
	private Incident incidence;
	private Operator operator;
	
	@Given("^operator with email \"([^\"]*)\"$")
	public void el_operario_con_identificador(String operatorIdentifier) throws Throwable {
		this.operator = opService.getOperatorByEmail(operatorIdentifier);
	}

	@Given("^the first of his assigned incidences$")
	public void the_first_of_his_assigned_incidences() throws Throwable {
		incidence = inciService.getIncidentsOf(operator).get(0);
	}

	@When("^he in process the incidence$")
	public void he_in_process_the_incidence() throws Throwable {
		inciService.changeState(incidence.getInciName(), IncidentState.IN_PROCESS.toString());
	}

	@Then("^the incidence is in processed$")
	public void the_incidence_is_in_processed() throws Throwable {
		assertTrue(incidence.getState().equals(IncidentState.IN_PROCESS));
	}
}
