package githubflavoredmarkdowneclipseplugin;

import githubflavoredmarkdowneclipseplugin.ProjectReconcilerStrategy;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.reconciler.Reconciler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;

public class ProjectReconciler extends Reconciler {

    private ProjectReconcilerStrategy fStrategy;

    public ProjectReconciler() {
        // TODO this is logic for .project file to fold tags. Replace with your language logic!
        fStrategy = new ProjectReconcilerStrategy();
        this.setReconcilingStrategy(fStrategy, IDocument.DEFAULT_CONTENT_TYPE);
    }

    @Override
    public void install(ITextViewer textViewer) {
        super.install(textViewer);
        ProjectionViewer pViewer =(ProjectionViewer)textViewer;
        fStrategy.setProjectionViewer(pViewer);
    }
}