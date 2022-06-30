package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class ExampleAction1 extends AnAction {

  @Override
  public void update(AnActionEvent e) {
    // Using the event, evaluate the context, and enable or disable the action.
    Project project = e.getProject();
    e.getPresentation().setEnabledAndVisible(project != null);

  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    System.out.println("done");
  }

}