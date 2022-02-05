package com.jeffcardillo.graph

/**
 * Connect the source node to the destination node with a two-way
 * weighted edge. i.e. A <-(w)-> B
 *
 * @param node the destination node
 * @param weight the weight of the edge
 */
fun Node.connectToNode(node: Node, weight: Int) {
    // add link for each direction
    Segment(node, weight).apply {
        addSegment(this)
    }

    Segment(this, weight).apply {
        node.addSegment(this)
    }

    println(this.name + " <---> " + node.name + " (" + weight + ")")
}

/**
 * Connect the source node to the destination node with a one-way
 * weighted edge. i.e. A -(w)-> B
 *
 * @param node the destination node
 * @param weight the weight of the edge
 */
fun Node.connectDirectlyToNode(node: Node, weight: Int) {
    Segment(node, weight).apply {
        addSegment(this)
    }

    println(this.name + " ---> " + node.name + " (" + weight + ")")
}

/**
 * Add a segment to the node
 *
 * @param segment the segment to add
 */
fun Node.addSegment(segment: Segment) {
    segments.add(segment)
}

/**
 * Helper function that will output the results of calling
 * findShortestDistance to the console
 */
fun Node.findShortestDistanceVerbose(destinationNode: Node): Int {
    findShortestDistance(destinationNode).also {
        println("Distance " + this.name + " ---> " + destinationNode.name + " = " + it)
        return it
    }
}

/**
 * Find the shortest distance to the destination node.
 *
 * @param destinationNode the node to find the shortest distance to
 * @return Int the distance to the specified node
 */
fun Node.findShortestDistance(destinationNode: Node): Int {
    // keep track of the distance from source to each node, add self
    val distancesToNodes: MutableMap<Node, Int> = mutableMapOf()
    distancesToNodes[this] = 0

    // maintain a list of segments that still should be explored
    val segmentsToExplore: MutableList<Segment> = mutableListOf()
    segmentsToExplore.addAll(this.segments.toMutableList())

    // maintain a list of segments that have already been explored
    val segmentsExplored: MutableList<Segment> = mutableListOf()

    while (segmentsToExplore.size > 0) {
        // find the shortest distance from source node to next node
        val segToTest: Segment = segmentsToExplore.minByOrNull { it.calculatedDistFromSource + it.distance }!!

        // check if the selected segment is the shortest path so far to the node it leads to
        val testNodeDistance = segToTest.calculatedDistFromSource + segToTest.distance
        if (!distancesToNodes.containsKey(segToTest.node) ||
                distancesToNodes[segToTest.node] ?: 0 > testNodeDistance) {
            distancesToNodes[segToTest.node] = testNodeDistance

            // if this is our destination, might as well bail
            if (segToTest.node == destinationNode) {
                break
            }
        }

        // add the new node's segments to the needs to be explored list
        val segmentList = segToTest.node.segments.toMutableList()

        // update each segment with the distance from the source node
        segmentList.forEach { it.calculatedDistFromSource = testNodeDistance }
        segmentsToExplore.addAll(segmentList)

        // mark the current segment as explored
        segmentsExplored.add(segToTest)

        // remove all explored segments from segments to explore
        segmentsToExplore.removeAll(segmentsExplored)
    }

    // reset temporary values
    segmentsExplored.forEach {
        it.calculatedDistFromSource = 0
    }

    // we now have the distance to every reachable node from the source
    return distancesToNodes[destinationNode] ?: -1
}