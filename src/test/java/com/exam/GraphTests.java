package com.exam;

import org.junit.Assert;
import org.junit.Test;

public class GraphTests {

    @Test
    public void testGetPathNull() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        Assert.assertNull(graph.getPath(2, 0));
    }

    @Test
    public void testGetPathNotNull() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        Assert.assertNotNull(graph.getPath(2, 0));
    }

    @Test
    public void testGetPathDirectedNull() {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        Assert.assertNull(graph.getPath(2, 0));
    }
}
