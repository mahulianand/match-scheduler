package com.matchscheduler;

import java.util.Calendar;

public interface Contraint {
	public boolean isValid(Team firstTeam, Team secondTeam, Location location, Calendar calendar, Schedule schedule);
}
