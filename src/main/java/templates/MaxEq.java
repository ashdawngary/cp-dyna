package templates;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaxEq extends MacroBase {

  public MaxEq(){
    super("lastToken", "lastToken");
  }

  private MaxEq(String n, String d){
    super(n, d);
  }

  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {
    if(quick){
      return new TextResult("");
    }
    PsiElement p = context.getPsiElementAtStartOffset();

    while (p instanceof PsiWhiteSpace){
      p = p.getPrevSibling();
      if (p == null){
        return new TextResult("--");
      }
    }

    return new TextResult(p.getText());
  }

  public boolean isAcceptableInContext(TemplateContextType t){
    return true;
  }
}