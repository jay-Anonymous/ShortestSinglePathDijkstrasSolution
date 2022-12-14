/*
 * 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package NetworkFlowOptimization;

import java.util.Scanner;

/**
 * Graph class
 *
 * @author anto
 */
public class Graph {

    private final ArrayList<Node> list;
    private final ArrayList<Arc> arcList;
    private ArrayList<Node> ordered;
    private final Node s;
    private final Node t;
    int maxC = Integer.MIN_VALUE;
    private final boolean isOrdered;
    private final boolean negCost;
    private boolean negCycle;
    PriorityQueue<Node> activeNodesList;
    int sourceFlow;
    int times;
    ArrayList<Path> paths;

    ;

    /**
     * New graph from nodes and arcs lists
     * @param list ArrayList List of nodes
     * @param arcList ArrayList List of arcs
     */
    public Graph(ArrayList<Node> list, ArrayList<Arc> arcList) {
        this.paths = new ArrayList<>();
        this.sourceFlow = 0;
        this.negCycle = false;
        this.activeNodesList = new PriorityQueue<>(10, (Node o1, Node o2) -> o2.distance - o1.distance);

        this.list = list;
        this.arcList = arcList;
        this.s = list.get(0);
        this.t = list.get(list.size() - 1);
        for (Arc a : arcList) {
            a.getHead().in.add(a);
            a.getTail().out.add(a);
        }
        this.isOrdered = this.order();
        this.negCost = this.negCost();
    }

    /**
     * New graph from nodes and arcs lists
     * @param list ArrayList List of nodes
     * @param arcList ArrayList List of arcs
     * @param spp boolean for special problem graph
     */
    public Graph(ArrayList<Node> list, ArrayList<Arc> arcList, boolean spp) {
        this.paths = new ArrayList<>();
        this.sourceFlow = 0;
        this.negCycle = false;
        this.activeNodesList = new PriorityQueue<>(10, (Node o1, Node o2) -> o2.distance - o1.distance);
        this.list = list;
        this.arcList = arcList;
        this.s = list.get(0);
        this.t = list.get(list.size() - 1);
        this.isOrdered = true;
        this.ordered = new ArrayList<>();
        this.ordered.addAll(list);
        this.negCost = false;
    }

    /**
     * New graph from nodes and arcs lists, source and sink
     * @param list ArrayList List of nodes
     * @param arcList ArrayList List of arcs
     * @param source Node source
     * @param sink Node sink
     */
    public Graph(ArrayList<Node> list, ArrayList<Arc> arcList, Node source, Node sink) {
        this.paths = new ArrayList<>();
        this.sourceFlow = 0;
        this.negCycle = false;
        this.activeNodesList = new PriorityQueue<>(10, (Node o1, Node o2) -> o2.distance - o1.distance);
        if (!list.get(0).equals2(source) || !list.get(list.size() - 1).equals2(sink)) {
            list.remove(source);
            list.remove(sink);
            this.list = new ArrayList<>();
            this.list.add(source);
            this.list.addAll(list);
            this.list.add(sink);
        } else {
            this.list = list;
        }
        this.arcList = arcList;
        this.s = source;
        this.t = sink;
        this.ordered = new ArrayList<>();
        this.isOrdered = this.order();
        this.negCost = this.negCost();
    }

    /**
     * Get new list of reinitialised nodes
     * @return ArrayList of nodes
     */
    public ArrayList<Node> getList() {
        ArrayList<Node> list2 = new ArrayList<>();
        list2.addAll(list);
        initialize(list2);
        return list2;
    }

    /**
     * Get new list of arcs
     * @return ArrayList of arcs
     */
    public ArrayList<Arc> getArcList() {
        ArrayList<Arc> arcList2 = new ArrayList<>();
        arcList2.addAll(arcList);
        return arcList2;
    }

    /**
     * Get Source node
     * @return Node Source
     */
    public Node getSource() {
        return s;
    }

    /**
     * Get Sink node
     * @return Node Sink
     */
    public Node getSink() {
        return t;
    }

    /**
     * Get max Cost
     * @return int maximum cost from all arcs
     */
    public int getC() {
        if (maxC > Integer.MIN_VALUE) {
            return maxC;
        }

        for (Arc a : arcList) {
            if (maxC < Math.abs(a.getCost())) {
                maxC = Math.abs(a.getCost());
            }
        }
        return maxC;
    }

    /**
     * Get nodes number
     * @return int
     */
    public int nodesNumber() {
        return list.size();
    }

    /**
     * Get arcs number
     * @return int
     */
    public int arcsNumber() {
        return arcList.size();
    }

    /**
     * Get topologically ordered nodes list (if possible)
     * @return
     */
    public ArrayList<Node> getOrdered() {
        if (isOrdered) {
            ArrayList<Node> newOrdered = new ArrayList<>();
            newOrdered.addAll(ordered);
            initialize(newOrdered);
            return newOrdered;
        }
        return null;
    }

    /**
     * Is the graph topologically ordered?
     * @return boolean
     */
    public boolean isIsOrdered() {
        return isOrdered;
    }

    /**
     * Does the graph contain an arc with negative cost?
     * @return boolean
     */
    public boolean isNegCost() {
        return negCost;
    }

    /**
     *  Reinitialise boolean attribute (previously)
     */
    public void previously() {
        for (Node i : list) {
            i.previously = false;
        }
    }

    private boolean order() {
        for (Node i : list) {
            i.indegree = 0;
        }
        for (Arc a : arcList) {
            a.head.indegree++;
        }

        LinkedList<Node> LIST = new LinkedList<>();
        int next = 0;
        Node n;

        for (Node i : list) {
            if (i.indegree == 0) {
                LIST.add(i);
            }
        }

        while (!LIST.isEmpty()) {
            n = LIST.poll();
            n.order = ++next;
            ordered.add(n);

            for (Arc a : n.out) {
                a.head.indegree--;
                if (a.getHead().indegree == 0) {
                    LIST.add(a.getHead());
                }
            }
        }

        return next >= list.size();
    }

    private boolean negCost() {
        for (Arc a : arcList) {
            if (a.getCost() < 0) {
                return true;
            }
        }
        return false;
    }

    private void initialize(ArrayList<Node> list) {
        for (Node i : list) {
            i.previously = false;
            i.contained = false;
            i.distance = Node.INFINITY;
            i.pred = null;
            i.predArc = null;
        }
        list.get(0).distance = 0;
    }

    /**
     * Reinitialise nodes for shortest path algorithms
     */
    public void initialize() {
        initialize(list);
    }

    /**
     * Reinitialise boolean attribute (previously) for nodes in list
     * @param list
     */
    public static void previously(ArrayList<Node> list) {
        for (Node i : list) {
            i.previously = false;
        }
    }

    /**
     * Get adjacency matrix
     * @return String The Adjacency matrix
     */
    public String adjMatrix() {
        int n = list.size();
        renumber();
        String result = "Adjacency matrix \n";
        int[][] nad = new int[n][n];
        int x, y;

        if (n <= 100) {
            for (Arc a : arcList) {
                x = a.getTail().number - 1;
                y = a.getHead().number - 1;
                nad[x][y] = 1;
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    result = result.concat(nad[row][col] + " ");
                }
                result = result.concat("\n");
            }
            result = result.concat("\n");
        } else {
            result = result.concat("Matrix too large!! (n>100)\n\n");
        }

        return result;
    }

    /**
     * Get costs
     * @return String A list of arc costs
     */
    public String arcCosts() {
        int n = arcList.size();
        ArrayList<Arc> ordArc = new ArrayList<>();
        ordArc.addAll(arcList);

        Collections.sort(ordArc);
        String result = "Arc costs\n";

        if (n <= 2000) {
            for (Arc a : ordArc) {
                result = result.concat("( " + a.getTail().number + " , " + a.getHead().number + " ) =>" + a.getCost() + "\n");
            }
        } else {
            result = result.concat("To many arcs!! (>2000)\n");
        }
        result = result.concat("\n");
        return result;
    }

    /**
     * Set residual graph values
     */
    public void setResidual() {
        for (Arc a : arcList) {
            a.residualForwardCapacity = a.capacity - a.flow;
            a.residualReverseCapacity = a.flow;
        }
    }

    /**
     * Get Max Flow Results (all arcs)
     * @return String with Results for Max Flow Problem Algorithms
     */
    public String maxFlowArcsResults() {
        String result = "Flow out of source node \n";
        int maxFlowS = 0;
        int maxFlowT = 0;

        for (Arc a : s.out) {
            maxFlowS += a.flow;
            if (a.flow > 0) {
                result = result.concat(a.toFlow());
            }
        }
        result = result.concat("Tot=" + maxFlowS + "\n");
        result = result.concat("\nFlow entering sink \n");
        for (Arc a : t.in) {
            maxFlowT += a.flow;
            if (a.flow > 0) {
                result = result.concat(a.toFlow());
            }
        }
        result = result.concat("Tot=" + maxFlowT + "\n");

        ArrayList<Arc> ordArc = new ArrayList<>();
        ordArc.addAll(arcList);
        Collections.sort(ordArc);

        result = result.concat("\nFlow arcs\n");
        for (Arc a : ordArc) {
            if (a.flow > 0) {
                result = result.concat(a.toFlow());
            }
        }

        return result;
    }

    /**
     * Get Max Flow Results 
     * @return String with Results for Max Flow Problem Algorithms
     */
    public String maxFlowResults() {
        String result = "Flow exiting the source = \t\t";
        int maxFlowS = 0;
        int maxFlowT = 0;

        for (Arc a : s.out) {
            maxFlowS += a.flow;
        }
        result = result.concat(maxFlowS + "\nFlow entering the sink = \t\t");
        for (Arc a : t.in) {
            maxFlowT += a.flow;
        }
        result = result.concat(maxFlowT + "\n");
        return result;
    }

    /**
     * Get Max Flow all arcs
     * @return String with all arc of initial graph for Max Flow Problem Algorithms
     */
    public String maxFlowAllArcs() {
        int n = arcList.size();
        String result = "List of arcs\n";
        if (n > 2000) {
            result = result.concat("To many arcs!! (>2000)\n");
            return result;
        }

        ArrayList<Arc> ordArc = new ArrayList<>();
        ordArc.addAll(arcList);
        Collections.sort(ordArc);

        for (Arc a : ordArc) {
            result = result.concat(a.toFlow());
        }
        result = result.concat("\n");
        return result;
    }

    /**
     * Get Min Cost Flow Results (all arcs)
     * @return String with Results for Min Cost Flow Problem Algorithms
     */
    public String minCostFlowArcsResult() {
        int n = arcList.size();
        String result = "List of arcs\n";
        if (n > 1000000) {
            result = result.concat("To many arcs!! (>1.000.000)\n");
            return result;
        }

        ArrayList<Arc> ordArc = new ArrayList<>();
        ordArc.addAll(arcList);
        Collections.sort(ordArc);

        for (Arc a : ordArc) {
            if (a.flow > 0) {
                result = result.concat(a.toMinCostFlow());
            }
        }
        result = result.concat("\n");
        return result;
    }

    /**
     * Get Min Cost Flow all arcs
     * @return String with all arcs of initial graph for Min Cost Flow Problem Algorithms
     */
    public String minCostFlowAllArcs() {
        int n = arcList.size();
        String result = "List of arcs\n";
        if (n > 2000) {
            result = result.concat("To many arcs!! (>2000)\n");
            return result;
        }

        ArrayList<Arc> ordArc = new ArrayList<>();
        ordArc.addAll(arcList);
        Collections.sort(ordArc);

        for (Arc a : ordArc) {
            result = result.concat(a.toEmptyMinCostFlow());
        }
        result = result.concat("\n");
        return result;
    }

    /**
     * Reset to zero the flows of all arcs 
     */
    public void resetFlows() {
        for (Node n : list) {
            n.massBalance = 0;
            n.activeForwardArc = 0;
        }

        for (Arc a : arcList) {
            a.flow = 0;
        }
        this.setResidual();
    }

    /**
     * Reverse Breadth First Search
     */
    public void reverseBreadthFirstSearch() {
        this.previously();
        LinkedList<Node> q = new LinkedList<>();
        Node n;

        q.add(t);
        t.previously = true;
        t.distance = 0;

        while (!q.isEmpty()) {
            n = q.poll();
            for (Arc a : n.in) {
                if (!a.tail.previously) {
                    a.tail.previously = true;
                    a.tail.distance = n.distance + 1;
                    q.add(a.tail);
                }
            }
        }
    }

    void renumber() {
        int next = 0;
        for (Node n : list) {
            n.number = ++next;
        }
    }

    void setSourceResidualFlow() {
        int flow = 0;
        for (Arc a : s.out) {
            flow += a.residualForwardCapacity;
        }
        sourceFlow = flow;
    }

    /**
     * Is there a negative cycle in the graph?
     * @return boolean
     */
    public boolean isNegCycle() {
        return negCycle;
    }

    /**
     * Set if there is a negative cycle in the graph
     * @param negCycle boolean
     */
    public void setNegCycle(boolean negCycle) {
        this.negCycle = negCycle;
    }

    /**
     * Perform the calculation of the total cost in min cost flow problem
     * @return int Total cost
     */
    public String totalCost() {
        String result = "";
        int sum = 0;

        for (Arc a : arcList) {
            sum += a.flow * a.cost;
        }
        result = "\nTotal cost = " + sum;

        return result;
    }

    /**
     * Result of min cost flow algorithms
     * @return String results
     */
    public String minCostFlowResults() {
        if (paths.isEmpty()) {
            return "";
        }
        String result = "\nFlows Paths / Cycles:\n";

        for (Path p : paths) {
            result = result.concat(p.toString());
        }

        result = result.concat("\n");
        return result;
    }

    /**
     * Mass excess of graph nodes
     * @return String
     */
    public String excessInNodes() {
        String result = "\nMass balance excess of nodes - e(i) = {  ";
        for (Node n : list) {
            result = result.concat(n.getValue() + "  ");
        }
        result = result.concat("}");
        return result;
    }

}
