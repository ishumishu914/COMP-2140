import java.io.*;
%%
%{
    static int numOfIdentifiers =0;
	static int numOfKeywords=0;
	static int numOfInt=0;
	static int numOfComments =0;
	static int numOfQString =0;
	public static void main(String argv[]) throws java.io.IOException
	{
		A2 yy = new A2(new FileInputStream("A2.input"));
		yy.yylex();
		FileWriter fw = new FileWriter("A2.output");
		fw.write("identifiers: " + new Integer(numOfIdentifiers ).toString() + "\n");
		fw.write("keywords: " + new Integer(numOfKeywords).toString() + "\n");
		fw.write("numbers: " + new Integer(numOfInt).toString() + "\n");
		fw.write("comments: " + new Integer(numOfComments ).toString() + "\n");
		fw.write("quotedString: " + new Integer(numOfQString ).toString() + "\n");
		fw.close();
	}
%}
%type void
%class A2
%eofval{return;
%eofval}
%state COMMENT
KEYWORDS = IF|BEGIN|WRITE|END|RETURN|READ|INT|REAL|MAIN
IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*
QUOTEDSTRING = \"[^\"]*\"
NUMBERS = [0-9]+(\.[0-9]+)?
%%
<YYINITIAL>"/**" {yybegin(COMMENT);}
<COMMENT>"**/" {numOfComments++; yybegin(YYINITIAL);}
<COMMENT> .|\n|\r {}
<YYINITIAL> {KEYWORDS} {numOfKeywords++;}
<YYINITIAL> {IDENTIFIER} {numOfIdentifiers++;}
<YYINITIAL> {NUMBERS} {numOfInt++;}
<YYINITIAL> {QUOTEDSTRING} {numOfQString++;}
.|\n|\r {}