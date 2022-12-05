/*
 * Copyright (C) 2019 anto
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

import java.util.Scanner;

/**
 * MinCostFlowProblems GUI
 * @author anto
 */
public class MinCostFlowProblems extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form MinCostFlowProblems
     */
    public MinCostFlowProblems() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        text = new javax.swing.JTextPane();
        title = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        reloadGraph = new javax.swing.JCheckBox();
        back = new javax.swing.JButton();
        calculate = new javax.swing.JButton();
        arcsList = new javax.swing.JCheckBox();
        adjMatrix = new javax.swing.JCheckBox();
        graphType = new javax.swing.JComboBox<>();
        nodes = new javax.swing.JFormattedTextField();
        nodesLabel = new javax.swing.JLabel();
        percArcs = new javax.swing.JFormattedTextField();
        percArcsLabel = new javax.swing.JLabel();
        cycles = new javax.swing.JCheckBox();
        maxArcCapacity = new javax.swing.JFormattedTextField();
        maxArcCapacityLabel = new javax.swing.JLabel();
        randomSeed = new javax.swing.JFormattedTextField();
        randomSeedLabel = new javax.swing.JLabel();
        flowArcs = new javax.swing.JCheckBox();
        massLabel = new javax.swing.JLabel();
        mass = new javax.swing.JFormattedTextField();

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        text.setEditable(false);
        text.setVerifyInputWhenFocusTarget(false);
        scroll.setViewportView(text);

        title.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        title.setText("Min Cost Flow Problems");

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NetworkFlowOptimization/Logo_UniCa_64.png"))); // NOI18N

        reloadGraph.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        reloadGraph.setText("Reload graph");
        reloadGraph.setEnabled(false);

        back.setInheritsPopupMenu(true);
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        calculate.setText("Calculate");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });

        arcsList.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        arcsList.setText("Arcs List");

        adjMatrix.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        adjMatrix.setText("Adjacency Matrix");

        graphType.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        graphType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Example graph", "Random graph", "Load graph" }));
        graphType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphTypeActionPerformed(evt);
            }
        });

        nodes.setColumns(5);
        nodes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nodes.setText("20");
        nodes.setToolTipText("Preferred number of nodes desired in the random graph");
        nodes.setEnabled(false);
        nodes.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        nodesLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        nodesLabel.setLabelFor(nodes);
        nodesLabel.setText("Number of nodes");
        nodesLabel.setToolTipText("");
        nodesLabel.setEnabled(false);
        nodesLabel.setPreferredSize(new java.awt.Dimension(80, 20));

        percArcs.setColumns(5);
        percArcs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        percArcs.setText("10");
        percArcs.setEnabled(false);
        percArcs.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        percArcsLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        percArcsLabel.setText("% arcs (1..100)");
        percArcsLabel.setEnabled(false);
        percArcsLabel.setPreferredSize(new java.awt.Dimension(80, 20));

        cycles.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cycles.setText("Graph with cycles");
        cycles.setEnabled(false);

        maxArcCapacity.setColumns(5);
        maxArcCapacity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        maxArcCapacity.setText("10");
        maxArcCapacity.setToolTipText("");
        maxArcCapacity.setEnabled(false);
        maxArcCapacity.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        maxArcCapacityLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        maxArcCapacityLabel.setText("Max arc capacity");
        maxArcCapacityLabel.setEnabled(false);

        randomSeed.setColumns(5);
        randomSeed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        randomSeed.setText("0");
        randomSeed.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        randomSeedLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        randomSeedLabel.setText("Random seed");

        flowArcs.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        flowArcs.setText("Result arcs");

        massLabel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        massLabel.setLabelFor(mass);
        massLabel.setText("e(s)");
        massLabel.setEnabled(false);

        mass.setColumns(5);
        mass.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        mass.setText("10");
        mass.setEnabled(false);
        mass.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(scroll)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(icon)
                            .add(title))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 125, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(flowArcs)
                                            .add(adjMatrix))
                                        .add(10, 10, 10))
                                    .add(layout.createSequentialGroup()
                                        .add(cycles)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(arcsList)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(nodesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(percArcsLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(nodes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(percArcs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(reloadGraph))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(massLabel)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(mass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(maxArcCapacityLabel)
                                            .add(randomSeedLabel))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(maxArcCapacity)
                                            .add(randomSeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(graphType, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(calculate)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(back)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(graphType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(back)
                    .add(calculate)
                    .add(title))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(reloadGraph)
                                    .add(arcsList)
                                    .add(mass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(massLabel))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(nodes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(nodesLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(maxArcCapacity, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(maxArcCapacityLabel)
                                    .add(cycles))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(percArcs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(percArcsLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(randomSeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(randomSeedLabel)
                                    .add(flowArcs)))
                            .add(icon))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(scroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(adjMatrix)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        NetworkFlowOptimization.disposeChildFrame();
    }//GEN-LAST:event_backActionPerformed

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed
        text.setText("");
        int n = graphType.getSelectedIndex();

        int maxCap = Integer.parseInt(maxArcCapacity.getText());
        if (maxCap <= 0) {
            maxCap = 1;
        }

        int seedLocal = Integer.parseInt(randomSeed.getText());
        int nodesLocal = Integer.parseInt(nodes.getText());
        int arcPercentLocal = Integer.parseInt(percArcs.getText());
        int flow = Integer.parseInt(mass.getText());
        if (flow < 1) {
            flow = 1;
        }
        boolean cycle = cycles.isSelected();
        Graph graph = null;
        String result = "";
        switch (n) {
            case 2:
                if (NetworkFlowOptimization.graph == null || reloadGraph.isSelected()) {
                    try {
                        graph = GraphMaker.loadGraph();
                        if (graph == null) {
                            return;
                        }
                        NetworkFlowOptimization.graph = graph;
                    } catch (IOException ex) {
                        Logger.getLogger(NetworkFlowOptimization.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    graph = NetworkFlowOptimization.graph;
                    graph.resetFlows();
                }
                break;
            case 1:
                graph = GraphMaker.randomGraph(nodesLocal, arcPercentLocal, seedLocal, 1, 100, maxCap, cycle, flow);
                break;
            default:
                graph = GraphMaker.exampleGraph(seedLocal, 1, 100);
                break;
        }

        if (adjMatrix.isSelected()) {
            text.setText(text.getText().concat(graph.adjMatrix()));
        }
        if (arcsList.isSelected()) {
            text.setText(text.getText().concat(graph.minCostFlowAllArcs()));
        }

        text.setText(text.getText().concat("\nNumber of nodes = " + graph.nodesNumber() + ";  Number of arcs = " + graph.arcsNumber()));
        text.setText(text.getText().concat(graph.excessInNodes() + "\n"));

        long stop = MinCostFlowProblem.successiveShortestPath(graph);
        graph.renumber();

        if (stop > 0) {
            text.setText(text.getText().concat("\nSuccessive Shortest Paths Algorithm\nExecution time = " + (double) stop / 1000000 + " milliseconds\n"));
            text.setText(text.getText().concat(graph.totalCost()));
            text.setText(text.getText().concat("\nSuccessive paths = " + graph.times + "\n"));
        } else {
            text.setText(text.getText().concat("\nThere is no feasible solution\n"));
            text.setText(text.getText().concat(result));
            return;
        }

        if (flowArcs.isSelected()) {
            result = result.concat(graph.minCostFlowArcsResult());
        } else {
            result = result.concat(graph.minCostFlowResults());
        }

        text.setText(text.getText().concat(result));

        result="";
        
        stop = MinCostFlowProblem.cycleCanceling(graph);
        graph.renumber();

        if (stop > 0) {
            text.setText(text.getText().concat("\nCycle Canceling Algorithm\nExecution time = " + (double) stop / 1000000 + " milliseconds\n"));
            text.setText(text.getText().concat(graph.totalCost()));
            text.setText(text.getText().concat("\nNeg Cycles = " + graph.times + "\n"));
        } else {
            text.setText(text.getText().concat("\nThere is no feasible solution\n"));
            text.setText(text.getText().concat(result));
            return;
        }

        if (flowArcs.isSelected()) {
            result = result.concat(graph.minCostFlowArcsResult());
        } else {
            result = result.concat(graph.minCostFlowResults());
        }

        text.setText(text.getText().concat(result));
        //text.setText(text.getText().concat(graph.massBalance()));
    }//GEN-LAST:event_calculateActionPerformed

    private void graphTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphTypeActionPerformed
        int n = graphType.getSelectedIndex();
        switch (n) {
            case 0:
                maxArcCapacity.setEnabled(false);
                maxArcCapacityLabel.setEnabled(false);
                randomSeed.setEnabled(true);
                randomSeedLabel.setEnabled(true);
                cycles.setEnabled(false);
                nodes.setEnabled(false);
                nodesLabel.setEnabled(false);
                percArcs.setEnabled(false);
                percArcsLabel.setEnabled(false);
                mass.setEnabled(false);
                massLabel.setEnabled(false);
                reloadGraph.setEnabled(false);
                break;
            case 1:
                maxArcCapacity.setEnabled(true);
                maxArcCapacityLabel.setEnabled(true);
                randomSeed.setEnabled(true);
                randomSeedLabel.setEnabled(true);
                cycles.setEnabled(true);
                nodes.setEnabled(true);
                nodesLabel.setEnabled(true);
                percArcs.setEnabled(true);
                percArcsLabel.setEnabled(true);
                mass.setEnabled(true);
                massLabel.setEnabled(true);
                reloadGraph.setEnabled(false);
                break;
            case 2:
                maxArcCapacity.setEnabled(false);
                maxArcCapacityLabel.setEnabled(false);
                randomSeed.setEnabled(false);
                randomSeedLabel.setEnabled(false);
                cycles.setEnabled(false);
                nodes.setEnabled(false);
                nodesLabel.setEnabled(false);
                percArcs.setEnabled(false);
                percArcsLabel.setEnabled(false);
                reloadGraph.setEnabled(true);
                mass.setEnabled(false);
                massLabel.setEnabled(false);
                break;
        }
    }//GEN-LAST:event_graphTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adjMatrix;
    private javax.swing.JCheckBox arcsList;
    private javax.swing.JButton back;
    private javax.swing.JButton calculate;
    private javax.swing.JCheckBox cycles;
    private javax.swing.JCheckBox flowArcs;
    private javax.swing.JComboBox<String> graphType;
    private javax.swing.JLabel icon;
    private javax.swing.JFormattedTextField mass;
    private javax.swing.JLabel massLabel;
    private javax.swing.JFormattedTextField maxArcCapacity;
    private javax.swing.JLabel maxArcCapacityLabel;
    private javax.swing.JFormattedTextField nodes;
    private javax.swing.JLabel nodesLabel;
    private javax.swing.JFormattedTextField percArcs;
    private javax.swing.JLabel percArcsLabel;
    private javax.swing.JFormattedTextField randomSeed;
    private javax.swing.JLabel randomSeedLabel;
    private javax.swing.JCheckBox reloadGraph;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane text;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

}
