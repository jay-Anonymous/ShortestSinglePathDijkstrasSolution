/*
 * Copyright (C) 2019 antonello.meloni
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
 * Minimun Cost Flow Problem Algorithms
 *
 * @author antonello.meloni
 */
public class MinCostFlowProblem {

    /**
     * Cycle Canceling algorithm (Min cost flow)
     *
     * @param graph a graph with n nodes and m arcs
     * @return long - execution time in nanonseconds
     */
    public static long cycleCanceling(Graph graph) {
        graph.resetFlows();
        graph.paths.clear();
        int times = 0;
        Graph fGraph = getGraphForFeasibleSolution(graph);
        MaxFlowProblem.labeling(fGraph);
        if (!isFeasible(fGraph)) {
            return -1;
        }
        graph.setNegCycle(true);
        Node n;
        graph.renumber();
        long start = System.nanoTime();
        while (graph.isNegCycle()) {
            n = modifiedFifoLabelCorrecting(graph);
            if (graph.isNegCycle()) {
                flowNegCycle(n, graph);
                times++;
            }
        }
        long stop = System.nanoTime() - start;
        graph.times = times;
        return stop;
    }

    /**
     * Successive Shortest Path Algorithm
     *
     * @param graph a graph with n nodes and m arcs
     * @return long - execution time in nanonseconds
     */
    public static long successiveShortestPath(Graph graph) {
        graph.resetFlows();
        graph.paths.clear();
        int times = 0;
        Graph fGraph = getGraphForFeasibleSolution(graph);
        MaxFlowProblem.labeling(fGraph);
        if (!isFeasible(fGraph)) {
            return -1;
        }
        fGraph.resetFlows();
        fGraph.setSourceResidualFlow();
        int flow = fGraph.sourceFlow;
        boolean negCyclePresent = false;
        long start = System.nanoTime();
        while (flow > 0 && !negCyclePresent) {
            times++;
            negCyclePresent = newPathSearch(fGraph);
            flow = fGraph.sourceFlow;
        }
        long stop = System.nanoTime() - start;
        graph.times = times;
        graph.paths.clear();
        graph.paths.addAll(fGraph.paths);
        return stop;
    }

    private static boolean isFeasible(Graph graph) {
        if (!graph.getSource().out.stream().noneMatch((a) -> (a.capacity - a.flow != 0))) {
            return false;
        }
        return graph.getSink().in.stream().noneMatch((a) -> (a.capacity - a.flow != 0));
    }

    private static Graph getGraphForFeasibleSolution(Graph graph) {
        Graph fGraph;
        ArrayList<Node> list = graph.getList();
        ArrayList<Node> ordered = new ArrayList<>();
        ArrayList<Arc> arcList = graph.getArcList();
        int b = 0;
        int neg = 0;
        for (Node n : list) {
            b += n.getValue();
            if (n.getValue() < 0) {
                neg += n.getValue();
            }
        }
        if (b != 0) {
            return null;
        }
        Node s = new Node(-neg);
        Node t = new Node(neg);
        Arc a = null;
        for (Node n : list) {
            if (n.getValue() > 0) {
                a = new Arc(0, s, n, n.getValue());
                s.out.add(a);
                n.in.add(a);
                arcList.add(a);
            } else if (n.getValue() < 0) {
                a = new Arc(0, n, t, -n.getValue());
                n.out.add(a);
                t.in.add(a);
                arcList.add(a);
            }
        }
        ordered.add(s);
        ordered.addAll(list);
        ordered.add(t);
        GraphMaker.number(ordered);
        fGraph = new Graph(ordered, arcList, s, t);
        return fGraph;
    }

    /**
     * Discovers new shortest paths
     * @param graph
     * @return boolean False if these is no new path
     */
    private static boolean newPathSearch(Graph graph) {
        Node nCycle = modifiedDequeueLabelCorrecting(graph);
        Node n=nCycle;
        ArrayList<Arc> arcs = new ArrayList<>();
        int minResCap = Integer.MAX_VALUE;
        graph.previously();
        Path path = new Path();
        graph.paths.add(path);
        while (n.pred != null && !n.previously) {
            arcs.add(n.predArc);
            path.nodes.add(n);
            if (n.predArc.head.equals2(n)) {
                if (minResCap > n.predArc.residualForwardCapacity) {
                    minResCap = n.predArc.residualForwardCapacity;
                }
            } else {
                if (minResCap > n.predArc.residualReverseCapacity) {
                    minResCap = n.predArc.residualReverseCapacity;
                }
            }
            n.previously = true;
            n = n.pred;
            if (n.previously) {
                graph.setNegCycle(true);
                path.nodes.add(n);
                return true;
            }
        }
        n = nCycle;
        for (Arc a : arcs) {
            if (n.predArc.head.equals2(n)) {
                a.setFlow(a.flow + minResCap);
            } else {
                a.setFlow(a.flow - minResCap);
            }
            n = n.pred;
        }
        graph.sourceFlow -= minResCap;
        path.flow = minResCap;
        path.nodes.remove(path.nodes.get(0));
        return false;
    }

    /**
     * Discover neg cycles in the residual graph
     * @param graph
     * @return 
     */
    private static Node modifiedDequeueLabelCorrecting(Graph graph) {
        graph.initialize();
        Node s = graph.getSource();
        int minDist = -graph.nodesNumber() * graph.getC();
        Node n;
        Node nCycle = graph.getSink();
        int dist = 0;
        LinkedList<Node> LIST = new LinkedList<>();
        LIST.add(s);
        while (!LIST.isEmpty()) {
            n = LIST.pollFirst();
            n.contained = false;
            for (Arc i : n.out) {
                dist = i.tail.distance + i.getCost();
                if (i.head.distance > dist && i.residualForwardCapacity > 0) {
                    i.head.distance = dist;
                    i.head.pred = i.tail;
                    i.head.predArc = i;
                    if (!i.head.contained) {
                        if (i.head.previously) {
                            LIST.addFirst(i.head);
                        } else {
                            LIST.add(i.head);
                        }
                        i.head.previously = true;
                        i.head.contained = true;
                    }
                }
            }
            for (Arc i : n.in) {
                dist = i.head.distance - i.getCost();
                if (i.tail.distance > dist && i.residualReverseCapacity > 0) {
                    i.tail.distance = dist;
                    i.tail.pred = i.head;
                    i.tail.predArc = i;
                    if (!i.tail.contained) {
                        if (i.tail.previously) {
                            LIST.addFirst(i.tail);
                        } else {
                            LIST.add(i.tail);
                        }
                        i.tail.previously = true;
                        i.tail.contained = true;
                    }
                }
            }
            if (dist < minDist) {
                LIST.clear();
                nCycle = n;
                graph.setNegCycle(true);
                return nCycle;
            }
        }
        graph.setNegCycle(false);
        return nCycle;
    }

    /**
     * Discover neg cycles in the residual graph
     * @param graph
     * @return 
     */
    private static Node modifiedFifoLabelCorrecting(Graph graph) {
        graph.initialize();
        Node s = graph.getSource();
        int minDist = -graph.nodesNumber() * graph.getC();
        Node n;
        Node nCycle = graph.getSink();
        int dist;
        LinkedList<Node> LIST = new LinkedList<>();
        LIST.add(s);
        while (!LIST.isEmpty()) {
            n = LIST.pollFirst();
            n.contained = false;
            for (Arc i : n.out) {
                dist = i.tail.distance + i.getCost();
                if (i.head.distance > dist && i.residualForwardCapacity > 0) {
                    i.head.distance = dist;
                    i.head.pred = i.tail;
                    i.head.predArc = i;
                    if (!i.head.contained) {
                        LIST.add(i.head);
                        i.head.contained = true;
                    }
                }
            }
            if (n.distance < minDist) {
                LIST.clear();
                nCycle = n;
                graph.setNegCycle(true);
                return nCycle;
            }
            for (Arc i : n.in) {
                dist = i.head.distance - i.getCost();
                if (i.tail.distance > dist && i.residualReverseCapacity > 0) {
                    i.tail.distance = dist;
                    i.tail.pred = i.head;
                    i.tail.predArc = i;
                    if (!i.tail.contained) {
                        LIST.add(i.tail);
                        i.tail.contained = true;
                    }
                }
            }
            if (n.distance < minDist) {
                LIST.clear();
                nCycle = n;
                graph.setNegCycle(true);
                return nCycle;
            }
        }
        graph.setNegCycle(false);
        return nCycle;
    }

    /**
     * Flows units along the neg cycle
     * @param nCycle
     * @param graph 
     */
    private static void flowNegCycle(Node nCycle, Graph graph) {
        Node n = nCycle;
        int minResCap = Integer.MAX_VALUE;
        graph.previously();
        Path path = new Path();
        path.cycle = true;
        graph.paths.add(path);
        while (!n.previously) {
            n.previously = true;
            n = n.pred;
        }
        graph.previously();
        while (!n.previously) {           
            path.arcList.add(n.predArc);
            path.nodes.add(n);
            n.previously = true;
            n = n.pred;
        }
        path.nodes.add(n);
        ArrayList<Node> nodes = path.getNodes();
        Iterator<Node> iterator = nodes.iterator();
        for (Arc a : path.getArcList()) {
            n = iterator.next();
            if (a.tail.equals2(n)) {
                if (minResCap > a.residualForwardCapacity) {
                    minResCap = a.residualForwardCapacity;
                }
            } else {
                if (minResCap > a.residualReverseCapacity) {
                    minResCap = a.residualReverseCapacity;
                }
            }
        }
        iterator = nodes.iterator();
        for (Arc a : path.getArcList()) {
            n = iterator.next();
            if (a.tail.equals2(n)) {
                a.setFlow(a.flow + minResCap);
            } else {
                a.setFlow(a.flow - minResCap);
            }
        }
        path.flow = minResCap;
    }
}
