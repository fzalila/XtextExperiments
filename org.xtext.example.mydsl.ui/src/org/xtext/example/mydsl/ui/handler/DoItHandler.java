package org.xtext.example.mydsl.ui.handler;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

public class DoItHandler extends AbstractHandler {

	@Inject
	EObjectAtOffsetHelper eObjectAtOffsetHelper;

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final XtextEditor xtextEditor = EditorUtils.getActiveXtextEditor(event);
		if (xtextEditor != null) {
			xtextEditor.getDocument().readOnly(new IUnitOfWork<String, XtextResource>() {

				@Override
				public String exec(XtextResource state) throws Exception {
					final TextSelection textSelection = (TextSelection) xtextEditor.getSelectionProvider()
							.getSelection();
					EObject object2 = eObjectAtOffsetHelper.resolveElementAt(state, textSelection.getOffset());
					System.err.println(object2);
					return null;
				}

			});
		}

		return null;
	}

}
