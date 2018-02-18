package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NodeExtensionsTest {

    @Test
    fun testFindShortestDistanceFigureA() {
        val a = Node("A")
        val b = Node("B")
        val c = Node("C")
        val d = Node("D")
        val e = Node("E")
        val f = Node("F")
        val g = Node("G")
        val h = Node("H")
        val i = Node("I")
        val noPath = Node("\"No Path\"")

        a.connectToNode(b, 15)
        a.connectToNode(c, 25)
        b.connectToNode(e, 10)
        b.connectToNode(h, 5)
        b.connectToNode(i, 25)
        c.connectToNode(d, 10)
        c.connectToNode(e, 20)
        f.connectToNode(e, 10)
        i.connectToNode(h, 15)
        e.connectToNode(g, 5)

        assertEquals(a.findShortestDistance(a), 0)
        assertEquals(a.findShortestDistance(b), 15)
        assertEquals(a.findShortestDistance(c), 25)
        assertEquals(a.findShortestDistance(d), 35)
        assertEquals(a.findShortestDistance(e), 25)
        assertEquals(a.findShortestDistance(f), 35)
        assertEquals(a.findShortestDistance(g), 30)
        assertEquals(a.findShortestDistance(h), 20)
        assertEquals(a.findShortestDistance(i), 35)
        assertEquals(a.findShortestDistance(noPath), -1)
    }

    @Test
    fun testFindShortestDistanceFigureB() {
        val a = Node("A")
        val b = Node("B")
        val c = Node("C")
        val d = Node("D")
        a.connectDirectlyToNode(b, 1)
        b.connectDirectlyToNode(c, 2)
        c.connectDirectlyToNode(a, 3)
        a.connectDirectlyToNode(d, 4)

        assertEquals(a.findShortestDistance(b), 1)
        assertEquals(a.findShortestDistance(c), 3)
        assertEquals(a.findShortestDistance(d), 4)
        assertEquals(b.findShortestDistance(d), 9)
        assertEquals(c.findShortestDistance(a), 3)
    }
}