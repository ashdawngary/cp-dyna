package templates.managers;

import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import templates.Dependencies;

public class DependencyManager {

  public static void loadDepdency(Dependencies dep, ExpressionContext context){
    PsiElement f = context.getPsiElementAtStartOffset();
    if (f == null){
      return;
    }

    while (!(f instanceof PsiClass)){
      f = f.getParent();
      if (f == null){
        return;
      }
    }

    loadDepencyWithContext(dep, context, (PsiClass) f, JavaPsiFacade.getElementFactory(context.getProject()));
  }

  public static void loadDepencyWithContext(Dependencies dep, ExpressionContext ctx,  PsiClass psiClass, PsiElementFactory factory){
    switch (dep){
      case PARSER_BASE:
        loadParser(psiClass,factory);
        loadDepencyWithContext(Dependencies.SET_INPUT_FILES, ctx, psiClass, factory);
        break;
      case GET_INT:
        loadDepencyWithContext(Dependencies.PARSER_BASE, ctx, psiClass, factory);
        loadGetInt(psiClass,factory);
        break;
      case GET_LONG:
        loadDepencyWithContext(Dependencies.PARSER_BASE, ctx, psiClass, factory);
        loadGetLong(psiClass,factory);
        break;
      case GET_DOUBLE:
        loadDepencyWithContext(Dependencies.PARSER_BASE, ctx, psiClass, factory);
        loadGetDouble(psiClass, factory);
        break;
      case GET_STRING:
        loadDepencyWithContext(Dependencies.PARSER_BASE, ctx, psiClass, factory);
        loadGetString(psiClass, factory);
        break;
      case GET_LINE:
        loadDepencyWithContext(Dependencies.PARSER_BASE, ctx, psiClass, factory);
        loadGetLine(psiClass, factory);
        break;
      case LOAD_PW:
        loadPW(psiClass, factory);
        loadDepencyWithContext(Dependencies.SET_OUTPUT_FILES, ctx, psiClass, factory);
        break;
      case SUBMIT:
        loadDepencyWithContext(Dependencies.LOAD_PW, ctx, psiClass, factory);
        loadSubmit(psiClass, factory);
        break;
      case SET_INPUT_FILES:
        loadSetInputFiles(psiClass, factory);
        break;
      case SET_OUTPUT_FILES:
        loadSetOutputFiles(psiClass, factory);
        break;
      case READ_ARRAY:
        loadDepencyWithContext(Dependencies.GET_INT, ctx, psiClass, factory);
        loadReadArray(psiClass, factory);
        break;
      case READ_MATRIX:
        loadDepencyWithContext(Dependencies.GET_INT, ctx, psiClass, factory);
        loadReadMatrix(psiClass, factory);
        break;
    }
  }

  private static void loadReadArray(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory, "readArray", CFTemplateSources.readArraySrc, null);
  }

  private static void loadReadMatrix(PsiClass psiClass, PsiElementFactory factory){
    addMethodIfNotPresent(psiClass, factory, "readMatrix", CFTemplateSources.readMatrixSrc, null);
  }

  private static void loadGetLine(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory,
        "getLine", CFTemplateSources.getLine,
        new String[]{"java.io"});
  }

  private static void loadGetString(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory,
        "getString", CFTemplateSources.getStringSrc,
        new String[]{"java.io"});
  }

  private static void loadGetDouble(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory,
        "getDouble", CFTemplateSources.getDoubleSrc,
        new String[]{"java.io"});
  }

  private static void loadSetOutputFiles(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory,
        "setOutputFile",
        CFTemplateSources.setOutputSrc,
        new String[]{"java.io"});
  }

  private static void loadSetInputFiles(PsiClass psiClass, PsiElementFactory factory) {
    addMethodIfNotPresent(psiClass, factory,
        "setInputFile",
        CFTemplateSources.setInputSrc,
        new String[]{"java.io"});
  }

  private static void loadSubmit(PsiClass psiClass, PsiElementFactory factory) {
    PsiMethod[] psiMethods = psiClass.getMethods();
    Set<String> mn = Arrays.stream(psiMethods).map(PsiMethod::getNameIdentifier).filter(Objects::nonNull)
        .map(PsiElement::getText).collect(Collectors.toSet());

    if (!mn.contains("submit")){
      addImportStatement(psiClass,factory,"java.util");
      for (String submitSrc : CFTemplateSources.submitSrcs){
        PsiElement classCloseRBrace = Objects.requireNonNull(psiClass.getRBrace());
        PsiMethod submitmethod = factory.createMethodFromText(submitSrc, psiClass);
        psiClass.addBefore(submitmethod,classCloseRBrace);
        //System.out.println("added " + submitSrc);
      }
    }
  }

  private static void loadPW(PsiClass psiClass, PsiElementFactory factory) {
    PsiField[] classFields= psiClass.getFields();
    Set<String> cf = Arrays.stream(classFields).map(PsiField::getNameIdentifier)
        .map(PsiElement::getText).collect(Collectors.toSet());

    PsiElement classOpenLBrace = Objects.requireNonNull(psiClass.getLBrace());
    if (!cf.contains("pw")){
      PsiField pwDef = factory.createFieldFromText(CFTemplateSources.pwSrc, psiClass);
      psiClass.addAfter(pwDef, classOpenLBrace);
      addImportStatement(psiClass, factory, "java.io");
    }
  }



  public static void loadGetInt(PsiClass psiClass, PsiElementFactory factory) {
    PsiMethod[] psiMethods = psiClass.getMethods();
    Set<String> mn = Arrays.stream(psiMethods).map(PsiMethod::getNameIdentifier).filter(Objects::nonNull)
        .map(PsiElement::getText).collect(Collectors.toSet());

    if (!mn.contains("getInt")){
      PsiElement classCloseRBrace = Objects.requireNonNull(psiClass.getRBrace());
      PsiMethod gi = factory.createMethodFromText(CFTemplateSources.getIntSrc, psiClass);
      psiClass.addBefore(gi,classCloseRBrace);
      addImportStatement(psiClass,factory, "java.io");
    }
  }

  public static void addMethodIfNotPresent(PsiClass psiClass, PsiElementFactory factory, String fn, @NotNull String fnsrc, @Nullable String[] sources ){
    PsiMethod[] psiMethods = psiClass.getMethods();
    Set<String> mn = Arrays.stream(psiMethods).map(PsiMethod::getNameIdentifier).filter(Objects::nonNull)
        .map(PsiElement::getText).collect(Collectors.toSet());

    if (!mn.contains(fn)){
      PsiElement classCloseRBrace = Objects.requireNonNull(psiClass.getRBrace());
      PsiMethod gi = factory.createMethodFromText(fnsrc, psiClass);
      psiClass.addBefore(gi,classCloseRBrace);
      if (sources != null){
        for (String src : sources){
          addImportStatement(psiClass,factory, src);
        }
      }
    }
  }

  public static void loadGetLong(PsiClass psiClass, PsiElementFactory factory) {
    PsiMethod[] psiMethods = psiClass.getMethods();
    Set<String> mn = Arrays.stream(psiMethods).map(PsiMethod::getNameIdentifier).filter(Objects::nonNull)
        .map(PsiElement::getText).collect(Collectors.toSet());

    if (!mn.contains("getLong")){
      PsiElement classCloseRBrace = Objects.requireNonNull(psiClass.getRBrace());
      PsiMethod gi = factory.createMethodFromText(CFTemplateSources.getLongSrc, psiClass);
      psiClass.addBefore(gi,classCloseRBrace);
      addImportStatement(psiClass,factory, "java.io");
    }
  }


  public static void loadParser(PsiClass psiClass, PsiElementFactory factory){
    PsiField[] classFields= psiClass.getFields();
    Set<String> cf = Arrays.stream(classFields).map(PsiField::getNameIdentifier)
        .map(PsiElement::getText).collect(Collectors.toSet());

    PsiElement classOpenLBrace = Objects.requireNonNull(psiClass.getLBrace());
    if (!cf.contains("sc")){
      PsiField scDef = factory.createFieldFromText(CFTemplateSources.scDef, psiClass);
      psiClass.addAfter(scDef, classOpenLBrace);
      addImportStatement(psiClass, factory, "java.util");
    }

    if (!cf.contains("st")){
      PsiField stDef = factory.createFieldFromText(CFTemplateSources.stDef, psiClass);
      psiClass.addAfter(stDef, classOpenLBrace);
      addImportStatement(psiClass, factory, "java.util");
    }
  }

  public static void addImportStatement(PsiClass psiClass, PsiElementFactory factory, String pck){
    PsiJavaFile j = (PsiJavaFile) psiClass.getParent();
    PsiImportList importList = Objects.requireNonNull(j.getImportList());
    PsiImportStatement[] imState = importList.getImportStatements();

    Set<String> imports = Arrays.stream(imState).map(PsiImportStatement::getQualifiedName).collect(Collectors.toSet());

    if (!imports.contains(pck)){
      PsiImportStatement newimport = factory.createImportStatementOnDemand(pck);
      importList.addBefore(newimport, importList.getFirstChild());
    }
  }

}
