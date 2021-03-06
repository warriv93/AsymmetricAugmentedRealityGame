package models;

import org.joda.time.DateTime;

import models.sub.AllActions;

/**
 * @see <a href=
 *      "http://docs.aarg.apiary.io/#reference/0/bomb-defusal/try-to-defuse-a-bomb">Apiary</a>
 */
public class SpecificDefuseInformation {
	private int id;
	private int player;
	private boolean defused = false;
	private DateTime when;
	private AllActions actions = new AllActions();
	private transient int gameId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public boolean isDefused() {
		return defused;
	}

	public void setDefused(boolean defused) {
		this.defused = defused;
	}

	public DateTime getWhen() {
		return when;
	}

	public void setWhen(DateTime when) {
		this.when = when;
	}

	public AllActions getActions() {
		return actions;
	}

	public void setActions(AllActions actions) {
		this.actions = actions;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
