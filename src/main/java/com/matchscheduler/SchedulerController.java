package com.matchscheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

	@RequestMapping("/schedule")
	public String schedule(@RequestParam(value = "teamName") List<String> teamNames,
			@RequestParam() Map<String, String> teamLocation,
			@RequestParam(value = "startDate", defaultValue = "15-Nov-2018") String startDate) throws Exception {

		if (teamNames == null || teamNames.isEmpty()) {
			throw new Exception("Team Names not specified.");
		}
		int noOfTeams = teamNames.size();

		if (noOfTeams < 2) {
			throw new Exception("Atleast 2 teams should be present.");
		}

		List<Team> allTeams = new ArrayList<>(noOfTeams);

		for (int i = 0; i < noOfTeams; i++) {
			String teamName = teamNames.get(i);
			String locationName = teamLocation.get(teamName);

			if (locationName == null || locationName.isEmpty()) {
				throw new Exception("Home Location not specified for team : " + teamName);
			}

			Location homeLocation = new Location(locationName);
			Team team = new Team(teamName, homeLocation);

			allTeams.add(team);
		}

		Deque<Match> allMatches = new LinkedList<>();
		for (Team firstTeam : allTeams) {
			for (Team secondTeam : allTeams) {
				if (firstTeam.equals(secondTeam)) {
					continue;
				}
				Match match = new Match(firstTeam, secondTeam);
				match.setLocation(firstTeam.getHomeLocation());
				allMatches.add(match);
			}
		}

		Schedule schedule = new Schedule();

		Scheduler scheduler = new Scheduler();
		scheduler.addContraint(new ConsecutiveMatches());
		scheduler.addContraint(new NoOfMatchsPerDay());

		if (startDate == null || startDate.isEmpty()) {
			throw new Exception("startDate not specified.");
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = simpleDateFormat.parse(startDate);
			calendar.setTime(date);
		} catch (ParseException e) {
			throw new Exception("Incorrect date " + startDate + ". Expected format : dd-MMM-yyyy", e);
		}
		scheduler.schedule(allMatches, calendar, schedule);

		return schedule.toString();
	}
}
