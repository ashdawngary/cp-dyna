package templates;

import static templates.Dependencies.GET_DOUBLE;
import static templates.Dependencies.GET_INT;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetDoubleTemplate extends LoadingTemplate {

  public GetDoubleTemplate(){
    this("getDoubleImport", "get double");
  }

  private GetDoubleTemplate(String n, String d){
    super(n, d, "getDouble()", GET_DOUBLE);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("getDouble()");
  }
}
