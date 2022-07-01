package templates;

import static templates.Dependencies.GET_INT;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.impl.TextRangeInterval;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GetIntTemplate extends LoadingTemplate {

  public GetIntTemplate(){
    this("getIntImport", "get int");
  }

  private GetIntTemplate(String n, String d){
    super(n, d, "getInt()", GET_INT);
  }


  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params,
      ExpressionContext context, boolean quick) {

    super.evalLoading(context);
    return new TextResult("getInt()");
  }
}
