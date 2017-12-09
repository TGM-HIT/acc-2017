package gol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Implementation for the Advent Coding Contest 2017
 * 
 * @author Michael Borko (mborko@tgm.ac.at)
 *
 */
public class GameOfLife {

	private Integer width = 0;
	private Integer height = 0;
	private Integer generation = 0;
	private String pattern;
	private Set<String> ruleSet;

	/**
	 * The constructor of GameOfLife initializes the pattern and all the private
	 * attributes.
	 * 
	 * @param initPattern
	 *            should obey this definition for a pattern "3;3;0;110001110$",
	 *            where the information is defined as
	 *            "width;height;generation;[row][row][row]$" and the dollar ($) at
	 *            the end marks the End-Of-Line.
	 */
	public GameOfLife(String initPattern) {
		String[] info = initPattern.split(";");
		this.width = Integer.parseInt(info[0]);
		this.height = Integer.parseInt(info[1]);
		this.generation = Integer.parseInt(info[2]);
		this.pattern = info[3].substring(0, info[3].length() - 1);
	}

	/**
	 * Returns a pattern as a String for the given generation number.
	 * 
	 * @param generation
	 *            The generation for which the pattern should be returned.
	 * @return should obey this definition for a pattern "3;3;0;110001110$", where
	 *         the information is defined as
	 *         "width;height;generation;[row][row][row]$" and the dollar ($) at the
	 *         end marks the End-Of-Line.
	 */
	public String getPattern(Integer generation) {

		// Here you should evaluate the pattern

		StringBuffer ret = new StringBuffer();
		ret.append(width + ";");
		ret.append(height + ";");
		ret.append(this.generation + ";");
		ret.append(pattern + "$\n");
		return ret.toString();
	}

	/**
	 * Returns the pattern of the next generation.
	 * 
	 * @return should obey this definition for a pattern "3;3;0;110001110$", where
	 *         the information is defined as
	 *         "width;height;generation;[row][row][row]$" and the dollar ($) at the
	 *         end marks the End-Of-Line.
	 */
	public String getNextPattern() {
		return getPattern(++generation);
	}

	/**
	 * Returns the pattern of the previous generation.
	 * 
	 * @return should obey this definition for a pattern "3;3;0;110001110$", where
	 *         the information is defined as
	 *         "width;height;generation;[row][row][row]$" and the dollar ($) at the
	 *         end marks the End-Of-Line.
	 */
	public String getPreviousPattern() {
		return getPattern(--generation);
	}

	/**
	 * Adding one rule to the Game-Of-Life Engine rule-set.
	 * 
	 * @param descriptionID
	 *            A String which defines a Game-Of-Life rule.
	 */
	public void addRule(String descriptionID) {
		ruleSet.add(descriptionID);
	}

	/**
	 * Adding rules to the Game-Of-Life Engine rule-set.
	 * 
	 * @param descriptionIDList
	 *            A List of Strings with Game-Of-Life rules.
	 */
	public void addRules(List<String> descriptionIDList) {
		for (String id : descriptionIDList)
			addRule(id);
	}

	/**
	 * Removing one rule from the Game-Of-Life Engine rule-set.
	 * 
	 * @param descriptionID
	 *            A String which defines a Game-Of-Life rule.
	 */
	public void removeRule(String descriptionID) {
		ruleSet.remove(descriptionID);
	}

	/**
	 * Removing rules from the Game-Of-Life Engine rule-set.
	 * 
	 * @param descriptionIDList
	 *            A List of Strings with Game-Of-Life rules.
	 */
	public void removeRules(List<String> descriptionIDList) {
		for (String id : descriptionIDList)
			removeRule(id);
	}

	/**
	 * Exporting the patterns to a file. Example: 2;2;0;1011$ 2;2;1;0000$
	 * 2;2;2;0000$ 2;2;3;0000$ 2;2;4;0000$
	 * 
	 * @param start
	 *            First generation number.
	 * @param end
	 *            Last generation number.
	 * @param filename
	 *            Name of File.
	 */
	public void exportGenerations(Integer start, Integer end, String filename) {
		StringBuffer output = new StringBuffer("");
		if (start <= end)
			for (int i = start; i <= end; i++)
				output.append(getPattern(i));
		else
			for (int i = end; i >= start; i--)
				output.append(getPattern(i));

		try {
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(new File(filename)));
			writer.write(output.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check your functionality with this main method.
	 * 
	 * @param args
	 *            Define as first parameter the pattern (e.g.
	 *            "4;4;0;1011101010101001$"), second the filename, third the start
	 *            generation and as last parameter the end generation.
	 *            E.g. java gol.GameOfLife 4;4;0;1011101010101001$ out.csv 0 100
	 */
	public static void main(String[] args) {
		GameOfLife gol;
		if (args.length != 4) {
			gol = new GameOfLife("4;4;0;1011101010101001$");
			gol.exportGenerations(0, 5, "output.csv");
		} else if (args.length == 4) {
			try {
				gol = new GameOfLife(args[0]);
				gol.exportGenerations(Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
