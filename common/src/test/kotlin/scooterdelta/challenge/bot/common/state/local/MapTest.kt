package scooterdelta.challenge.bot.common.state.local

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.state.remote.domain.BaseCell
import scooterdelta.challenge.bot.common.state.remote.domain.Point

class MapTest {

    lateinit var map: Map<BaseCell>

    @Before fun setUp() {
        map = generateNbyNMap(10, 10)
    }

    @Test fun testGetAdjacentKeys() {
        val subMap = map.findNAdjacentCellsMap(Point(0, 0), 1)

        assertThat("The map size is correct", subMap.totalCells, equalTo(4))
        assertThat("The center location is present", subMap.getCellFromMap(0, 0), equalTo(Point(0, 0) as BaseCell))
        assertThat("None existent locations return null", subMap.getCellFromMap(5, 5) == null, equalTo(true))
    }

    @Test fun testGetTopRightAdjacentKeys() {
        val subMap = map.findNAdjacentCellsMap(Point(9, 9), 1)

        assertThat("The map size is correct", subMap.totalCells, equalTo(4))
        assertThat("The center location is present", subMap.getCellFromMap(9, 9), equalTo(Point(9, 9) as BaseCell))
    }

    @Test fun testGetOffCenterAdjacentKeys() {
        val subMap = map.findNAdjacentCellsMap(Point(5, 5), 1)

        assertThat("The map size is correct", subMap.totalCells, equalTo(9))
        assertThat("The center location is present", subMap.getCellFromMap(5, 5), equalTo(Point(5, 5) as BaseCell))
    }

    private fun generateNbyNMap(xSize: Int, ySize: Int): Map<BaseCell> {
        val cells: MutableList<Point> = mutableListOf()
        for (x in 0..xSize - 1) {
            (0..ySize - 1).mapTo(cells) { Point(x, it) }
        }
        return Map(cells)
    }

}