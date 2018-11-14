package com.matchscheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ConsecutiveMatches implements Contraint {

	@Override
	public boolean isValid(Team firstTeam, Team secondTeam, Location location, Calendar calendar, Schedule schedule) {

		boolean firstTeamCheck = checkDifference(firstTeam, calendar.getTime());
		boolean secondTeamCheck = checkDifference(secondTeam, calendar.getTime());
		return firstTeamCheck && secondTeamCheck;
	}

	private boolean checkDifference(Team team, Date date) {
		Match firstTeamLastMatch = team.getTeamMatches().peekLast();
		if (Objects.nonNull(firstTeamLastMatch)) {
			long diff = getDaysDifference(firstTeamLastMatch.getDate().getTime(), date);
			if (diff <= 1) {
				return false;
			}
		}
		return true;
	}

	private long getDaysDifference(Date firstDate, Date secondDate) {
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

}
