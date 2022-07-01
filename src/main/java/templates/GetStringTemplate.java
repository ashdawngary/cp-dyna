package templates;

import static templates.Dependencies.GET_STRING;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetStringTemplate extends LoadingTemplate {

  public GetStringTemplate(){
    this("getStringImport", "get string token");
  }

  private GetStringTemplate(String n, String d){
    super(n, d, "getString()", GET_STRING);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("getString()");
  }
}
