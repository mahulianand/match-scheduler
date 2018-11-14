package com.matchscheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Schedule {

	private Map<Calendar, List<Match>> dateWiseMatches = new LinkedHashMap<>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public void addMatch(Match match) {
		List<Match> matches = dateWiseMatches.get(match.getDate());
		if (Objects.nonNull(matches)) {
			matches.add(match);
		} else {
			matches = new ArrayList<>();
			matches.add(match);
			dateWiseMatches.put(match.getDate(), matches);
		}
	}

	public Map<Calendar, List<Match>> getSchedule() {
		return dateWiseMatches;
	}

	public void print() {
		System.out.println(toString());
	}

	public String toString() {
		Set<Entry<Calendar, List<Match>>> entrySet = dateWiseMatches.entrySet();
		StringBuilder returnString = new StringBuilder();
		for (Entry<Calendar, List<Match>> entry : entrySet) {
			List<Match> matches = entry.getValue();
			for (Match match : matches) {
				returnString.append(simpleDateFormat.format(match.getDate().getTime())).append(" :-: ")
						.append(match.getFirstTeam().getTeamName()).append(" vs ")
						.append(match.getSecondTeam().getTeamName()).append(" ( ")
						.append(match.getLocation().getLocation()).append(" )\n");
			}
		}
		return returnString.toString();
	}
}
