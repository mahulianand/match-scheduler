package com.matchscheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Match {
	private Team firstTeam;
	private Team secondTeam;
	private Location location;
	private Calendar date;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public Match(Team firstTeam, Team secondTeam) {
		super();
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public String toString() {

		return (date == null ? "-" : simpleDateFormat.format(date.getTime())) + " :-: " + firstTeam.getTeamName()
				+ " vs " + secondTeam.getTeamName() + " ( " + (location == null ? "-" : location.getLocation()) + " )";

	}

}
