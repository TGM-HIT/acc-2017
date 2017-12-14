"""
:author Markus Reichl
:version 2017-12-01
"""


import argparse


class GameOfLife:
    def __init__(self, pattern: str):
        """
        Set up default values by interpreting the given GoL pattern of 1 generation.
        The following format is required:
        {width}";"{height}";"{generation}";"{width * height * ("0" or "1")) "$")}";"

        :param pattern: A GoL pattern with exactly 1 generation
        """
        # Splice values
        values = pattern[:-1].split(";")
        # Load values from pattern
        self.__width = int(values[0])
        self.__height = int(values[1])
        self.__generation = int(values[2])
        self.__pattern = str(values[3])
        # Initialize rules
        self.__rules = []

    def get_pattern(self, generation: int):
        """
        Get the pattern of a specific generation

        :param generation: Generation of the requested pattern
        :return: The requested pattern as a string
        """
        # Check if generation is older
        while generation > self.__generation:
            self.__generation += 1
        # Check if generation is younger
        while generation < self.__generation:
            self.__generation -= 1
        # Return the new pattern
        return str("%s;%s;%s;%s$\n" % (self.__width, self.__height, self.__generation, self.__pattern))

    def get_next_pattern(self):
        """
        Get the pattern for the next generation

        :return: The pattern for the next generation as a string
        """
        return self.get_pattern(self.__generation + 1)

    def get_previous_pattern(self):
        """
        Get the pattern for the previous generation

        :return: The pattern for the previous generation as a string
        """
        return self.get_pattern(self.__generation - 1)

    def add_rule(self, description: str):
        """
        Add a rule

        :param description: Unique rule description
        """
        self.__rules.append(description)

    def add_rules(self, *descriptions: str):
        """
        Add multiple rules

        :param descriptions: A tuple of unique rule descriptions
        """
        for description in descriptions:
            self.__rules.append(description)

    def remove_rule(self, description: str):
        """
        Remove a rule

        :param description: Unique rule description
        """
        self.__rules.remove(description)

    def remove_rules(self, *descriptions: str):
        """
        Remove multiple rules

        :param descriptions: A tuple of unique rule descriptions
        """
        for description in descriptions:
            self.__rules.remove(description)

    def export_generations(self, start, end, filename):
        """
        Export a set of GoL patterns from start to end generation to a file with the given filename

        :param start: Start generation
        :param end: End generation
        :param filename: File to write the patterns to
        """
        self.__pattern = self.get_pattern(start).split(";")[3][:-2]
        self.__generation = start

        with open(filename, "w+") as file:
            while self.__generation < end:
                file.write(self.get_next_pattern())
                self.__generation += 1


    def get_objects(self) -> str:
        """
        List objects in the following format: object;{instances}$object;{instances}
        """
        pass


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("-p", "--pattern",type=str,
                        help="Game-Of-Life pattern (e.g. '4;4;0;1011101010101001$')",
                        required=False)
    parser.add_argument("-f", "--filename",type=str,
                        help="Filename for generation output.",
                        required=False)
    parser.add_argument("-s", "--start",type=int,
                        help="Start of generation output.",
                        required=False)
    parser.add_argument("-e", "--end", type=int,
                        help="End of generation output.",
                        required=False)
    args = parser.parse_args()

    pattern = "2;2;0;0001$"
    if args.pattern is not None: pattern = args.pattern
    filename = "output.csv"
    if args.filename is not None: filename = args.filename
    start = 0
    if args.start is not None: start = args.start
    end = 3
    if args.end is not None: end = args.end

    gol = GameOfLife(pattern)
    gol.export_generations(start, end, filename)


if __name__ == "__main__":
    main()
