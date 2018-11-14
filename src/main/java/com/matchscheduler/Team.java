package com.matchscheduler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Team {
	private String teamName;
	private Location homeLocation;
	private Deque<Match> teamMatches = new LinkedList<>();

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Location getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(Location homeLocation) {
		this.homeLocation = homeLocation;
	}

	public void addTeamMatch(Match match) {
		this.teamMatches.add(match);
	}

	public Deque<Match> getTeamMatches() {
		return this.teamMatches;
	}

	public Team(String teamName, Location homeLocation) {
		super();
		this.teamName = teamName;
		this.homeLocation = homeLocation;
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", homeLocation=" + homeLocation + "]";
	}

}
