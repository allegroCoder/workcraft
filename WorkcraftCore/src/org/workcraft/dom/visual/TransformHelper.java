package org.workcraft.dom.visual;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import org.workcraft.dom.Node;
import org.workcraft.exceptions.NotAnAncestorException;
import org.workcraft.util.Geometry;
import org.workcraft.util.Hierarchy;

public class TransformHelper {

    public static void applyTransform(Node node, AffineTransform transform) {
        if (node instanceof Movable) {
            ((Movable) node).applyTransform(transform);
        }
    }

    public static AffineTransform getTransformToAncestor(Node node, Node ancestor) {
        AffineTransform t = new AffineTransform();
        while (ancestor != node) {
            Node next = node.getParent();
            if (next == null) {
                throw new NotAnAncestorException();
            }
            if (next instanceof Movable) {
                t.preConcatenate(((Movable) next).getTransform());
            }
            node = next;
        }
        return t;
    }

    public static AffineTransform getTransformToRoot(Node node) {
        return getTransformToAncestor(node, Hierarchy.getRoot(node));
    }

    public static AffineTransform getTransformFromRoot(Node node) {
        return getTransform(Hierarchy.getRoot(node), node);
    }

    public static AffineTransform getTransform(Node node1, Node node2) {
        Node parent = Hierarchy.getCommonParent(node1, node2);
        AffineTransform node1ToParent = getTransformToAncestor(node1, parent);
        AffineTransform node2ToParent = getTransformToAncestor(node2, parent);
        AffineTransform parentToNode2 = Geometry.optimisticInverse(node2ToParent);

        parentToNode2.concatenate(node1ToParent);
        return parentToNode2;
    }

    public static Touchable transform(Touchable touchable, AffineTransform transform) {
        return new TouchableTransformer(touchable, transform);
    }

    public static double snapP5(double x) {
        return (double) (Math.round(x * 2)) / 2;
    }

    public static Point2D getCentre(Collection<Node> nodes) {
        Rectangle2D bb = null;
        for (Node node : nodes) {
            if (node instanceof Touchable) {
                Rectangle2D nodeBoundingBox = ((Touchable) node).getBoundingBox();
                bb = BoundingBoxHelper.union(bb, nodeBoundingBox);
            }
        }
        return new Point2D.Double(bb.getCenterX(), bb.getCenterY());
    }

    public static Point2D getSnappedCentre(Collection<Node> nodes) {
        Point2D centre = getCentre(nodes);
        double snapX = snapP5(centre.getX());
        double snapY = snapP5(centre.getY());
        return new Point2D.Double(snapX, snapY);
    }

}
