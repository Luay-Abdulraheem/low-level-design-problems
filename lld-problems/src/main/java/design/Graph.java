package design;

import java.util.*;

public class Graph {
    HashMap<Node, ArrayList<Node>> adjList;

    Graph(List<Node> list) {
        this.adjList = new HashMap<>();
        for (Node n : list)
            adjList.put(n, new ArrayList<>());
    }

    void addEdge(Node node1, Node node2) {
        adjList.get(node1).add(node2);
        adjList.get(node2).add(node1);
    }

    void removeEdge(Node node1, Node node2) {
        ArrayList<Node> node1List = adjList.get(node1);
        ArrayList<Node> node2List = adjList.get(node2);

        node1List.remove(node2);
        node2List.remove(node1);

    }

    ArrayList<String> breadthFirstSearch(Node start) {
        ArrayList<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            for (Node n : adjList.get(currNode))
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
        }
        ArrayList<String> bfs = new ArrayList<>();
        for (Node n : visited)
            bfs.add(n.nodeName);
        return bfs;
    }

    ArrayList<String> depthFirstSearch(Node start) {
        ArrayList<Node> visited = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(start);

        while (!stack.isEmpty()) {
            Node currNode = stack.pop();
            if (!visited.contains(currNode)) {
                visited.add(currNode);
                for (Node n : adjList.get(currNode))
                    stack.push(n);
            }
        }
        ArrayList<String> dfs = new ArrayList<>();
        for (Node n : visited)
            dfs.add(n.nodeName);

        return dfs;
    }

    void printAdjList() {
        for (Map.Entry mapElement : adjList.entrySet()) {
            Node n = (Node) mapElement.getKey();
            System.out.print(n.nodeName + "->");
            ArrayList<Node> list = adjList.get(n);
            for (Node a : list)
                System.out.print(a.nodeName + " ");
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        //creating the nodes
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        ArrayList<Node> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        //Constructing the graphs
        Graph g = new Graph(list);
        g.addEdge(a, e);
        g.addEdge(a, d);
        g.addEdge(d, e);
        g.addEdge(b, e);
        g.addEdge(b, c);

        //print the adjacency list
        System.out.println("Adjacency List: ");
        g.printAdjList();

        //print BFS traversal
        System.out.print("Breadth First Traversal starting from A: ");
        System.out.println(g.breadthFirstSearch(a));

        //print DFS traversal
        System.out.print("Depth First Traversal starting from E: ");
        System.out.println(g.depthFirstSearch(e));

    }
}

class Node {
    String nodeName;

    Node(String name) {
        this.nodeName = name;
    }
}
