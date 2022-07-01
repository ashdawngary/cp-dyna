package templates;

import static templates.Dependencies.GET_INT;
import static templates.Dependencies.READ_ARRAY;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReadArrayTemplate extends LoadingTemplate {

  public ReadArrayTemplate(){
    this("readArrayImport", "read array import");
  }

  private ReadArrayTemplate(String n, String d){
    super(n, d, "readArray()", READ_ARRAY);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("");
  }
}
