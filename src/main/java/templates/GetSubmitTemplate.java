package templates;

import static templates.Dependencies.GET_INT;
import static templates.Dependencies.GET_LONG;
import static templates.Dependencies.SUBMIT;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetSubmitTemplate extends LoadingTemplate {

  public GetSubmitTemplate(){
    this("getSubmitImport", "load submit files");
  }

  private GetSubmitTemplate(String n, String d){
    super(n, d, "submit();", SUBMIT);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {
    super.evalLoading(context);
    return new TextResult("");
  }
}
