package templates.managers;

public class CFTemplateSources {
  public static final String getIntSrc = "public static int getInt() throws IOException {\n"
      + "\t\tif (st != null && st.hasMoreTokens()) {\n"
      + "\t\t\treturn Integer.parseInt(st.nextToken());\n"
      + "\t\t}\n"
      + "\t\tst = new StringTokenizer(sc.readLine());\n"
      + "\t\treturn Integer.parseInt(st.nextToken());\n"
      + "\t}\n";
  public static final String getLongSrc = "public static long getLong() throws IOException {\n"
      + "\t\tif (st != null && st.hasMoreTokens()) {\n"
      + "\t\t\treturn Long.parseLong(st.nextToken());\n"
      + "\t\t}\n"
      + "\t\tst = new StringTokenizer(sc.readLine());\n"
      + "\t\treturn Long.parseLong(st.nextToken());\n"
      + "\t}\n";
  public static final String pwSrc = "public static PrintWriter pw = new PrintWriter(System.out);";
  public static final String[] submitSrcs = {"public static void submit(int[] k, boolean close) {\n"
      + "\t\tpw.println(Arrays.toString(k));\n"
      + "\t\tif (close) {\n"
      + "\t\t\tpw.close();\n"
      + "\t\t}\n"
      + "\t}\n",
      "public static void submit(int p, boolean close) {\n"
      + "\t\tpw.println(Integer.toString(p));\n"
      + "\t\tif (close) {\n"
      + "\t\t\tpw.close();\n"
      + "\t\t}\n"
      + "\t}\n"
      + " \n",
      "public static void submit(String k, boolean close) {\n"
      + "\t\tpw.println(k);\n"
      + "\t\tif (close) {\n"
      + "\t\t\tpw.close();\n"
      + "\t\t}\n"
      + "\t}\n"
      + " \n",
      "public static void submit(double u, boolean close) {\n"
      + "\t\tpw.println(Double.toString(u));\n"
      + "\t\tif (close) {\n"
      + "\t\t\tpw.close();\n"
      + "\t\t}\n"
      + "\t}\n",
      "public static void submit(long lng, boolean close) {\n"
      + "\t\tpw.println(Long.toString(lng));\n"
      + "\t\tif (close) {\n"
      + "\t\t\tpw.close();\n"
      + "\t\t}\n"
      + " \n"
      + "\t}\n",
      "public static void submit() {\n"
      + "\t\tpw.close();\n"
      + "\t}\n" };
  public static final String scDef = "public static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));";
  public static final String stDef = "public static StringTokenizer st;";
  public static final String setInputSrc = "public static void setInputFile(String fn) throws IOException {\n"
      + "\t\tsc = new BufferedReader(new FileReader(fn));\n"
      + "\t}\n";
  public static final String setOutputSrc = "public static void setOutputFile(String fn) throws IOException {\n"
      + "\t\tpw = new PrintWriter(new BufferedWriter(new FileWriter(fn)));\n"
      + "\t}\n";


  public static final String getDoubleSrc = "public static double getDouble() throws IOException {\n"
      + "\t\tif (st != null && st.hasMoreTokens()) {\n"
      + "\t\t\treturn Double.parseDouble(st.nextToken());\n"
      + "\t\t}\n"
      + "\t\tst = new StringTokenizer(sc.readLine());\n"
      + "\t\treturn Double.parseDouble(st.nextToken());\n"
      + "\t}\n";

  public static final String getStringSrc = "public static String getString() throws IOException {\n"
      + "\t\tif (st != null && st.hasMoreTokens()) {\n"
      + "\t\t\treturn st.nextToken();\n"
      + "\t\t}\n"
      + "\t\tst = new StringTokenizer(sc.readLine());\n"
      + "\t\treturn st.nextToken();\n"
      + "\t}\n";

  public static final String getLine = "public static String getLine() throws IOException {\n"
      + "\t\treturn sc.readLine();\n"
      + "\t}\n";

  public static final String readMatrixSrc = "public static int[][] readMatrix(int lines, int cols) throws IOException {\n"
      + "\t\tint[][] matrr = new int[lines][cols];\n"
      + "\t\tfor (int i = 0; i < lines; i++) {\n"
      + "\t\t\tfor (int j = 0; j < cols; j++) {\n"
      + "\t\t\t\tmatrr[i][j] = getInt();\n"
      + "\t\t\t}\n"
      + "\t\t}\n"
      + "\t\treturn matrr;\n"
      + "\t}\n";

  public static final String readArraySrc = "public static int[] readArray(int lines) throws IOException {\n"
      + "\t\tint[] ar = new int[lines];\n"
      + "\t\tfor (int i = 0; i < lines; i++)\n"
      + "\t\t\tar[i] = getInt();\n"
      + "\t\treturn ar;\n"
      + "\t}\n";

}
