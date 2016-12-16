package org.workcraft.plugins.petrify.tools;

import java.util.ArrayList;

import org.workcraft.AbstractConversionCommand;
import org.workcraft.Framework;
import org.workcraft.plugins.fsm.Fsm;
import org.workcraft.plugins.fst.Fst;
import org.workcraft.plugins.petri.PetriNetModel;
import org.workcraft.plugins.petrify.tasks.TransformationResultHandler;
import org.workcraft.plugins.petrify.tasks.TransformationTask;
import org.workcraft.plugins.stg.StgModel;
import org.workcraft.tasks.TaskManager;
import org.workcraft.workspace.ModelEntry;
import org.workcraft.workspace.WorkspaceEntry;
import org.workcraft.workspace.WorkspaceUtils;

public class NetConversionCommand extends AbstractConversionCommand {

    @Override
    public String getDisplayName() {
        return "Net synthesis [Petrify]";
    }

    @Override
    public Position getPosition() {
        return Position.MIDDLE;
    }

    @Override
    public boolean isApplicableTo(WorkspaceEntry we) {
        return WorkspaceUtils.isApplicable(we, PetriNetModel.class) || WorkspaceUtils.isApplicable(we, Fsm.class);
    }

    @Override
    public void run(WorkspaceEntry we) {
        ArrayList<String> args = getArgs();
        final TransformationTask task = new TransformationTask(we, "Net synthesis", args.toArray(new String[args.size()]));
        boolean hasSignals = WorkspaceUtils.isApplicable(we, StgModel.class) || WorkspaceUtils.isApplicable(we, Fst.class);
        final Framework framework = Framework.getInstance();
        final TaskManager taskManager = framework.getTaskManager();
        final TransformationResultHandler monitor = new TransformationResultHandler(we, hasSignals);
        taskManager.queue(task, "Petrify net synthesis", monitor);
    }

    @Override
    public ModelEntry convert(ModelEntry me) {
        return null; // !!!
    }

    public ArrayList<String> getArgs() {
        return new ArrayList<>();
    }

}
