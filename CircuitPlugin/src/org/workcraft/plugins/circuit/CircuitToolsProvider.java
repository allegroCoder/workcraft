package org.workcraft.plugins.circuit;

import java.util.ArrayList;

import org.workcraft.gui.graph.tools.CommentGeneratorTool;
import org.workcraft.gui.graph.tools.CustomToolsProvider;
import org.workcraft.gui.graph.tools.GraphEditorTool;
import org.workcraft.plugins.circuit.tools.CircuitConnectionTool;
import org.workcraft.plugins.circuit.tools.CircuitSelectionTool;
import org.workcraft.plugins.circuit.tools.CircuitSimulationTool;
import org.workcraft.plugins.circuit.tools.ContactGeneratorTool;
import org.workcraft.plugins.circuit.tools.FunctionComponentGeneratorTool;

public class CircuitToolsProvider implements CustomToolsProvider {

    @Override
    public Iterable<GraphEditorTool> getTools() {
        ArrayList<GraphEditorTool> result = new ArrayList<GraphEditorTool>();

        result.add(new CircuitSelectionTool());
        result.add(new CommentGeneratorTool());
        result.add(new CircuitConnectionTool());
        result.add(new FunctionComponentGeneratorTool());
        result.add(new ContactGeneratorTool());
        result.add(new CircuitSimulationTool());

        return result;
    }

}
