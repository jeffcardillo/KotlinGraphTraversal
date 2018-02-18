package graph

data class Node(val name: String) {
    var segments: MutableList<Segment> = mutableListOf()
}

data class Segment(val node: Node, val distance: Int) {
    // properties used in the shortest path alg.
    var calculatedDistFromSource: Int = 0
}