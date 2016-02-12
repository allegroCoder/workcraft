/*
*
* Copyright 2008,2009 Newcastle University
*
* This file is part of Workcraft.
*
* Workcraft is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Workcraft is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Workcraft.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package org.workcraft.plugins.dfs;

import org.workcraft.annotations.VisualClass;
import org.workcraft.dom.Container;
import org.workcraft.dom.Node;
import org.workcraft.dom.math.AbstractMathModel;
import org.workcraft.dom.math.MathConnection;
import org.workcraft.dom.math.MathNode;
import org.workcraft.dom.references.HierarchicalUniqueNameReferenceManager;
import org.workcraft.serialisation.References;
import org.workcraft.util.Hierarchy;

@VisualClass (org.workcraft.plugins.dfs.VisualDfs.class)
public class Dfs extends AbstractMathModel {

    public Dfs() {
        this(null, null);
    }

    public Dfs(Container root, References refs) {
        super(root, new HierarchicalUniqueNameReferenceManager(refs) {
            @Override
            public String getPrefix(Node node) {
                if ((node instanceof Logic) || (node instanceof CounterflowLogic)) return "l";
                if ((node instanceof Register) || (node instanceof CounterflowRegister)) return "r";
                if (node instanceof ControlRegister) return "c";
                if ((node instanceof PushRegister) || (node instanceof PopRegister)) return "p";
                return super.getPrefix(node);
            }
        });
    }

    public MathConnection connect(Node first, Node second) {
        MathConnection con = new MathConnection((MathNode) first, (MathNode) second);
        Hierarchy.getNearestContainer(first, second).add(con);
        return con;
    }

    public ControlConnection controlConnect(Node first, Node second) {
        ControlConnection con = new ControlConnection((MathNode) first, (MathNode) second);
        Hierarchy.getNearestContainer(first, second).add(con);
        return con;
    }

}
