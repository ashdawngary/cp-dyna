package templates;

import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.impl.TextRangeInterval;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiClass;

import java.util.Optional;
import templates.managers.DependencyManager;

abstract class LoadingTemplate extends MacroBase {
  private final String invokeTarget;
  private final Dependencies loadTarget;
  LoadingTemplate(String s, String d, String target, Dependencies toLoad){
    super(s, d);
    invokeTarget = target;
    loadTarget = toLoad;
  }

  public void evalLoading(ExpressionContext context){
    Optional<Editor> ed= Optional.ofNullable(context.getEditor());
    if (ed.isEmpty()){
      return;
    }

    Document d  = ed.get().getDocument();

    int st = context.getTemplateStartOffset();
    int end = context.getTemplateEndOffset();
    String s = d.getText(TextRangeInterval.create(st, end));
    System.out.printf("matching (%s) (%s)\n", invokeTarget, s);
    if (s.equals(invokeTarget)){
      DependencyManager.loadDepdency(loadTarget, context);

    }
  }

}
