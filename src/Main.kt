import graph.Node
import com.jeffcardillo.graph.connectDirectlyToNode
import com.jeffcardillo.graph.connectToNode
import com.jeffcardillo.graph.findShortestDistanceVerbose

fun main(args : Array<String>) {
    println("Building Undirected Graph for Figure A.")
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

    a.findShortestDistanceVerbose(a)
    a.findShortestDistanceVerbose(b)
    a.findShortestDistanceVerbose(c)
    a.findShortestDistanceVerbose(d)
    a.findShortestDistanceVerbose(e)
    a.findShortestDistanceVerbose(f)
    a.findShortestDistanceVerbose(g)
    a.findShortestDistanceVerbose(h)
    a.findShortestDistanceVerbose(i)

    e.findShortestDistanceVerbose(f)
    d.findShortestDistanceVerbose(i)
    b.findShortestDistanceVerbose(f)
    a.findShortestDistanceVerbose(noPath)

    println("")
    println("Building Directed Graph for Figure B.")
    val aA = Node("A")
    val bB = Node("B")
    val cC = Node("C")
    val dD = Node("D")
    aA.connectDirectlyToNode(bB, 1)
    bB.connectDirectlyToNode(cC, 2)
    cC.connectDirectlyToNode(aA, 3)
    aA.connectDirectlyToNode(dD, 4)

    aA.findShortestDistanceVerbose(bB)
    aA.findShortestDistanceVerbose(cC)
    aA.findShortestDistanceVerbose(dD)
    bB.findShortestDistanceVerbose(dD)
    cC.findShortestDistanceVerbose(aA)
}