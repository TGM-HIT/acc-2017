using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace csharp.main.csharp
{
    /// <summary>
    ///     Implementation for the Advent Coding Contest 2017 in C#
    /// </summary>
    /// <author>
    ///     Marc Rousavy (marcrousavy@hotmail.com)
    /// </author>
    public class GameOfLife {
        /// <summary>
        ///     The constructor of GameOfLife initializes the pattern and all the private attributes
        /// </summary>
        /// <param name="initPattern">
        ///     should obey this definition for a pattern <code>"3;3;0;110001110$"</code>, where the information is
        ///     defined as <code>"width;height;generation;[row][row][row]$"</code> and the dollar($) at the end marks
        ///     the End-Of-Line.
        /// </param>
        public GameOfLife(string initPattern) {
            string[] info = initPattern.Split(";");
            Width = int.Parse(info[0]);
            Height = int.Parse(info[1]);
            Generation = int.Parse(info[2]);
            Pattern = info[3].Substring(0, info[3].Length - 1);
            RuleSet = new HashSet<string>();
        }

        private int Width { get; }

        private int Height { get; }

        private int Generation { get; set; }

        private string Pattern { get; }

        private HashSet<string> RuleSet { get; }

        /// <summary>
        ///     Returns a pattern as a String for the given generation number.
        /// </summary>
        /// <param name="generation">
        ///     The generation for which the pattern should be returned.
        /// </param>
        /// <returns>
        ///     should obey this definition for a pattern "3;3;0;110001110$", where the information is defined as
        ///     <code>"width;height;generation;[row][row][row]$"</code> and the dollar ($) at the end marks the End-Of-Line.
        /// </returns>
        public string GetPattern(int generation) {
            // TODO: Here you should evaluate the pattern


            var ret = new StringBuilder();
            ret.Append(Width + ";");
            ret.Append(Height + ";");
            ret.Append(Generation + ";");
            ret.Append(Pattern + "$\n");
            return ret.ToString();
        }

        /// <summary>
        ///     Returns the pattern of the next generation.
        /// </summary>
        /// <returns>
        ///     should obey this definition for a pattern "3;3;0;110001110$", where
        ///     the information is defined as <code>"width;height;generation;[row][row][row]$"</code>
        ///     and the dollar ($) at the end marks the End-Of-Line.
        /// </returns>
        public string GetNextPattern() {
            return GetPattern(++Generation);
        }

        /// <summary>
        ///     Returns the pattern of the previous generation.
        /// </summary>
        /// <returns>
        ///     should obey this definition for a pattern "3;3;0;110001110$", where
        ///     the information is defined as <code>"width;height;generation;[row][row][row]$"</code>
        ///     and the dollar ($) at the end marks the End-Of-Line.
        /// </returns>
        public string GetPreviousPattern() {
            return GetPattern(--Generation);
        }

        /// <summary>
        ///     Adding one rule to the Game-Of-Life Engine rule-set.
        /// </summary>
        /// <param name="descriptionId">
        ///     A String which defines a Game-Of-Life rule.
        /// </param>
        public void AddRule(string descriptionId) {
            RuleSet.Add(descriptionId);
        }

        /// <summary>
        ///     Adding rules to the Game-Of-Life Engine rule-set.
        /// </summary>
        /// <param name="descriptionIdList">
        ///     A List of Strings with Game-Of-Life rules.
        /// </param>
        public void AddRules(List<string> descriptionIdList) {
            descriptionIdList.ForEach(AddRule);
        }

        /// <summary>
        ///     Removing one rule from the Game-Of-Life Engine rule-set.
        /// </summary>
        /// <param name="descriptionId">
        ///     A String which defines a Game-Of-Life rule.
        /// </param>
        public void RemoveRule(string descriptionId) {
            RuleSet.Remove(descriptionId);
        }

        /// <summary>
        ///     Removing rules from the Game-Of-Life Engine rule-set.
        /// </summary>
        /// <param name="descriptionIdList">
        ///     A List of Strings with Game-Of-Life rules.
        /// </param>
        public void RemoveRules(List<string> descriptionIdList) {
            descriptionIdList.ForEach(RemoveRule);
        }

        /// <summary>
        ///     Exporting the patterns to a file. Example:
        ///     * <code>2;2;0;1011$ 2;2;1;0000$</code>
        ///     * <code>2;2;2;0000$ 2;2;3;0000$ 2;2;4;0000$</code>
        /// </summary>
        /// <param name="start">
        ///     First generation number.
        /// </param>
        /// <param name="end">
        ///     Last generation number.
        /// </param>
        /// <param name="filename">
        ///     Name of File.
        /// </param>
        public void ExportGenerations(int start, int end, string filename) {
            var output = new StringBuilder();

            if (start <= end)
                for (int i = start; i <= end; i++)
                    output.Append(GetPattern(i));
            else
                for (int i = end; i >= start; i--)
                    output.Append(GetPattern(i));

            try {
                File.WriteAllText(filename, output.ToString());
            } catch (Exception e) {
                Console.WriteLine(e);
            }
        }
    }
}