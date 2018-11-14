package com.matchscheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Deque;
import java.util.List;

public class Scheduler {
	List<Contraint> contraints = new ArrayList<Contraint>();

	public void addContraint(Contraint contraint) {
		contraints.add(contraint);
	}

	public void schedule(Deque<Match> matches, Calendar calendar, Schedule schedule) {
		int iterationCount = 0;
		while (!matches.isEmpty()) {
			iterationCount++;

			Match match = matches.remove();

			Team firstTeam = match.getFirstTeam();
			Team secondTeam = match.getSecondTeam();

			boolean valid = checkDateContraints(firstTeam, secondTeam, firstTeam.getHomeLocation(), calendar, schedule);
			if (!valid || iterationCount > matches.size()) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			}

			valid = checkAllContraints(firstTeam, secondTeam, firstTeam.getHomeLocation(), calendar, schedule);
			if (valid) {
				match.setDate((Calendar) calendar.clone());
				schedule.addMatch(match);
				firstTeam.addTeamMatch(match);
				secondTeam.addTeamMatch(match);
				iterationCount = 0;
				continue;
			}

			matches.addLast(match);
		}
	}

	private boolean checkAllContraints(Team firstTeam, Team secondTeam, Location location, Calendar calendar,
			com.matchscheduler.Schedule schedule) {
		for (Contraint contraint : contraints) {
			if (!contraint.isValid(firstTeam, secondTeam, location, calendar, schedule)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkDateContraints(Team firstTeam, Team secondTeam, Location location, Calendar calendar,
			com.matchscheduler.Schedule schedule) {
		for (Contraint contraint : contraints) {
			if (contraint instanceof DateContraint) {
				if (!contraint.isValid(firstTeam, secondTeam, location, calendar, schedule)) {
					return false;
				}
			}
		}
		return true;
	}

}
