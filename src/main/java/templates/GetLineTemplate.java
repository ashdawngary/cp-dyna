package templates;

import static templates.Dependencies.GET_DOUBLE;
import static templates.Dependencies.GET_LINE;
import static templates.Dependencies.GET_LONG;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetLineTemplate extends LoadingTemplate {

  public GetLineTemplate(){
    this("getLineImport", "get line");
  }

  private GetLineTemplate(String n, String d){
    super(n, d, "getLine()", GET_LINE);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("getLine()");
  }
}
