package templates;

import static templates.Dependencies.READ_ARRAY;
import static templates.Dependencies.READ_MATRIX;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReadMatrixTemplate extends LoadingTemplate {

  public ReadMatrixTemplate(){
    this("readMatrixImport", "read matrix import");
  }

  private ReadMatrixTemplate(String n, String d){
    super(n, d, "readMatrix(, )", READ_MATRIX);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {
    super.evalLoading(context);
    return new TextResult("");
  }
}
