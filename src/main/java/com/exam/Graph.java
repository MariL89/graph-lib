package com.exam;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.*;

@NoArgsConstructor
public class Graph<T> {

    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    private Map<T, Set<T>> mapVertices = new HashMap<>();

    @SneakyThrows
    public void addVertex(T vertex) {
        if (mapVertices.containsKey(vertex)) {
            throw new IllegalAccessException("Vertex already added");
        }
        mapVertices.putIfAbsent(vertex, new HashSet<>());
    }

    public void removeVertex(T vertex) {
        mapVertices.values().forEach(e -> e.remove(vertex));
        mapVertices.remove(vertex);
    }

    @SneakyThrows
    public void addEdge(T v1, T v2) {
        if (v1.equals(v2)) {
            throw new IllegalAccessException("Equals vertices");
        }
        mapVertices.computeIfAbsent(v1, a -> new HashSet<>()).add(v2);
        Set<T> v2List = mapVertices.computeIfAbsent(v2, a -> new HashSet<>());
        if (!directed) {
            v2List.add(v1);
        }
    }

    public void removeEdge(T v1, T v2) {
        Set<T> eV1 = mapVertices.get(v1);
        Set<T> eV2 = mapVertices.get(v2);
        if (eV1 != null) {
            eV1.remove(v2);
        }
        if (eV2 != null) {
            eV2.remove(v1);
        }
    }

    @SneakyThrows
    public List<T> getPath(T v1, T v2) {
        if (!mapVertices.containsKey(v1) || !mapVertices.containsKey(v2)) {
            throw new IllegalAccessException("v1 or v2 not found in graph");
        }

        Set<T> visited = new HashSet<>();

        List<T> pathList = new ArrayList<>();
        pathList.add(v1);

        return findPath(v1, v2, visited, pathList);
    }

    private List<T> findPath(T v1, T v2, Set<T> visited, List<T> pathList) {
        if (v1.equals(v2)) {
            return pathList;
        }
        visited.add(v1);

        for (T item : mapVertices.get(v1)) {
            if (!visited.contains(item)) {
                pathList.add(item);
                List<T> result = findPath(item, v2, visited, pathList);
                if (result != null) {
                    return result;
                }
                pathList.remove(item);
            }
        }

        visited.remove(v1);
        return null;
    }
}
