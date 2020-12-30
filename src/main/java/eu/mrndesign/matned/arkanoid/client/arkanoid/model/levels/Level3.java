package eu.mrndesign.matned.arkanoid.client.arkanoid.model.levels;

import static eu.mrndesign.matned.arkanoid.client.arkanoid.utils.Constants.DEFAULT_TIME;

import java.util.LinkedList;
import java.util.List;

import eu.mrndesign.matned.arkanoid.client.arkanoid.model.Brick;

public class Level3 extends BaseLevel {

	private List<Brick> bricks;

	public Level3() {
		bricks = new LinkedList<>();
		generateBricks(bricks);
	}

	@Override
	public BaseLevel getNextLevel() {
		return new Level4();
	}

	@Override
	public String getLayout() {
		return "$$%%%%.%%!" + "$##%%%.$$!" + "$#.....#$!" + "$$##%$$$$!";
	}

	public List<Brick> getBricks() {
		return bricks;
	}

	@Override
	public long getTimeInMillis() {
		return DEFAULT_TIME;
	}
}
