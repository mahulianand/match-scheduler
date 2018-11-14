package com.matchscheduler;

import java.util.Calendar;

public class NoOfMatchsPerDay implements DateContraint {

	@Override
	public boolean isValid(Team firstTeam, Team secondTeam, Location location, Calendar calendar, Schedule schedule) {
		if (schedule.getSchedule().containsKey(calendar)) {
			if (schedule.getSchedule().get(calendar).size() >= 2) {
				return false;
			}
		}
		return true;
	}

}
