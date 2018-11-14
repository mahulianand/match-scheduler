package com.matchscheduler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void singleTeamNegativeTest() throws Exception {
		try {
			this.mockMvc.perform(get("/schedule").param("teamName", "Team1").param("Team1", "Pune").param("startDate",
					"01-Nov-2018"));
		} catch (Exception e) {
			Assert.assertTrue("Expected Exception", e.getMessage().contains("Atleast 2 teams should be present."));

		}
	}

	@Test
	public void missingTeamLocationNegativeTest() throws Exception {
		try {
			this.mockMvc.perform(get("/schedule").param("teamName", "Team1").param("teamName", "Team2")
					.param("Team1", "Pune").param("startDate", "01-Nov-2018"));
		} catch (Exception e) {
			Assert.assertTrue("Expected Exception",
					e.getMessage().contains("Home Location not specified for team : Team2"));

		}
	}

	@Test
	public void incorrectStartDateNegativeTest() throws Exception {
		try {
			this.mockMvc.perform(get("/schedule").param("teamName", "Team1").param("teamName", "Team2")
					.param("Team1", "Pune").param("Team2", "Mumbai").param("startDate", "01-11-2018"));
		} catch (Exception e) {
			Assert.assertTrue("Expected Exception", e.getMessage().contains("Incorrect date "));

		}
	}

	@Test
	public void basicTestWithTwoTeams() throws Exception {
		this.mockMvc
				.perform(get("/schedule").param("teamName", "Team1").param("teamName", "Team2").param("Team1", "Pune")
						.param("Team2", "Mumbai").param("startDate", "01-Nov-2018"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void oddNumberOfTeamsTest() throws Exception {
		this.mockMvc
				.perform(get("/schedule").param("teamName", "Team1").param("teamName", "Team2")
						.param("teamName", "Team3").param("Team1", "Pune").param("Team2", "Mumbai")
						.param("Team3", "Delhi").param("startDate", "01-Nov-2018"))
				.andDo(print()).andExpect(status().isOk());
	}

}
