import unittest

from gameoflife import GameOfLife


class EncryptionTests(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_gol_init(self):
        gol = GameOfLife("2;2;0;0001$")
        print(gol.get_pattern(0))
        self.assertTrue(gol.get_pattern(0) == "2;2;0;0001$\n")
