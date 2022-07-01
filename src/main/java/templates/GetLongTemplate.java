package templates;

import static templates.Dependencies.GET_INT;
import static templates.Dependencies.GET_LONG;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetLongTemplate extends LoadingTemplate {

  public GetLongTemplate(){
    this("getLongImport", "get long");
  }

  private GetLongTemplate(String n, String d){
    super(n, d, "getLong()", GET_LONG);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("getLong()");
  }
}
