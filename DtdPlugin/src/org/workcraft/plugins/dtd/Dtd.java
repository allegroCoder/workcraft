package org.workcraft.plugins.dtd;

import java.util.Collection;

import org.workcraft.annotations.VisualClass;
import org.workcraft.dom.Container;
import org.workcraft.dom.Node;
import org.workcraft.dom.math.AbstractMathModel;
import org.workcraft.dom.math.MathConnection;
import org.workcraft.dom.math.MathNode;
import org.workcraft.dom.references.HierarchicalUniqueNameReferenceManager;
import org.workcraft.dom.references.ReferenceManager;
import org.workcraft.gui.propertyeditor.ModelProperties;
import org.workcraft.gui.propertyeditor.NamePropertyDescriptor;
import org.workcraft.serialisation.References;
import org.workcraft.util.Hierarchy;
import org.workcraft.util.Identifier;

@VisualClass(org.workcraft.plugins.dtd.VisualDtd.class)
public class Dtd extends AbstractMathModel {

    public Dtd() {
        this(null, (References) null);
    }

    public Dtd(Container root, References refs) {
        this(root, new HierarchicalUniqueNameReferenceManager(refs) {
            @Override
            public String getPrefix(Node node) {
                if (node instanceof Signal) return "x";
                if (node instanceof SignalEntry) return Identifier.createInternal("entry");
                if (node instanceof SignalExit) return Identifier.createInternal("exit");
                if (node instanceof SignalTransition) return Identifier.createInternal("t");
                return super.getPrefix(node);
            }
        });
    }

    public Dtd(Container root, ReferenceManager man) {
        super(root, man);
    }

    public MathConnection connect(Node first, Node second) {
        MathConnection con = new MathConnection((MathNode) first, (MathNode) second);
        Hierarchy.getNearestContainer(first, second).add(con);
        return con;
    }

    public Collection<Signal> getSignals() {
        return Hierarchy.getDescendantsOfType(getRoot(), Signal.class);
    }

    public Collection<Signal> getSignals(final Signal.Type type) {
        return Hierarchy.getDescendantsOfType(getRoot(), Signal.class,
                signal -> (signal != null) && (signal.getType() == type));
    }

    public Collection<SignalTransition> getTransitions() {
        return Hierarchy.getDescendantsOfType(getRoot(), SignalTransition.class);
    }

    public Collection<SignalTransition> getTransitions(final Signal signal) {
        return Hierarchy.getDescendantsOfType(getRoot(), SignalTransition.class,
                transition -> (transition != null) && (transition.getSignal() == signal));
    }

    public Signal.State getPreviousState(SignalEvent event) {
        Signal signal = event.getSignal();
        for (Node node: getPreset(event)) {
            if (node instanceof SignalTransition) {
                SignalTransition transition = (SignalTransition) node;
                if (transition.getSignal() == signal) {
                    SignalTransition.Direction direction = transition.getDirection();
                    return DtdUtils.getNextState(direction);
                }
            }
        }
        return signal.getInitialState();
    }

    @Override
    public ModelProperties getProperties(Node node) {
        ModelProperties properties = super.getProperties(node);
        if (node instanceof SignalEvent) {
            properties.removeByName(NamePropertyDescriptor.PROPERTY_NAME);
        }
        return properties;
    }

}
