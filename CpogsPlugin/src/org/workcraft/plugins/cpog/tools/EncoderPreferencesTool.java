package org.workcraft.plugins.cpog.tools;

import java.io.File;

import org.workcraft.Framework;
import org.workcraft.Tool;
import org.workcraft.plugins.cpog.EncoderSettings;
import org.workcraft.plugins.cpog.EncoderSettings.GenerationMode;
import org.workcraft.plugins.cpog.EncoderSettingsSerialiser;
import org.workcraft.plugins.cpog.VisualCPOG;
import org.workcraft.plugins.cpog.gui.EncoderConfigurationDialog;
import org.workcraft.plugins.shared.presets.PresetManager;
import org.workcraft.util.GUI;
import org.workcraft.workspace.WorkspaceEntry;

public class EncoderPreferencesTool implements Tool {

	private final Framework framework;
	private EncoderSettings settings;
	private EncoderConfigurationDialog dialog;
	PresetManager<EncoderSettings> pmgr;

	public EncoderPreferencesTool(Framework framework) {
		this.framework = framework;
	}

	@Override
	public boolean isApplicableTo(WorkspaceEntry we) {
		if (we.getModelEntry() == null) return false;
		if (we.getModelEntry().getVisualModel() instanceof VisualCPOG) return true;
		return false;
	}

	@Override
	public String getSection() {
		return "Encoding";
	}

	@Override
	public String getDisplayName() {
		return "SCENCO";
	}

	@Override
	public void run(WorkspaceEntry we) {
		settings = new EncoderSettings(10, GenerationMode.OPTIMAL_ENCODING, false, false);
		pmgr = new PresetManager<EncoderSettings>(new File("config/cpog_presets.xml"), new EncoderSettingsSerialiser());
		dialog = new EncoderConfigurationDialog(framework.getMainWindow(), pmgr, settings, we);

		GUI.centerToParent(dialog, framework.getMainWindow());
		dialog.setVisible(true);
		// TASK INSERTION
		/*final ProgrammerChainTask programmerTask = new ProgrammerChainTask(we, dialog.getSettings(), framework);
		framework.getTaskManager().queue(programmerTask, "Scenco tool chain",
				new ProgrammerChainResultHandler(programmerTask));*/
	}

}