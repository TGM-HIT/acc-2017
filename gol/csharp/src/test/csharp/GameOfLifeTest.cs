using csharp.main.csharp;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace csharp.test.csharp {
    [TestClass]
    public class GameOfLifeTest {
        [TestMethod]
        public void TestInitPattern() {
            var classUnderTest = new GameOfLife("2;2;0;1000$");
            Assert.AreEqual("2;2;0;1000$\n", classUnderTest.GetPattern(0));
        }
    }
}