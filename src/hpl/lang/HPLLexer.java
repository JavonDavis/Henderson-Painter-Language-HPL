/* Specification for ECOLI tokens */
// user customisations
package hpl.lang;
import hpl.sys.*;
import java_cup.runtime.*;


public class HPLLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    public int getChar()
    {
	return yychar + 1;
    }
    public int getLine()
    {
	return yyline + 1;
    }
    public String getText()
    {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public HPLLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public HPLLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private HPLLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 1;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0,
		44
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,5:2,2,4,5,2,4:18,5,48,57,3,4,53,49,4,9,10,51,50,6,16,1,52,56,55:9,8,7,4" +
"7,35,47,4:2,27,42,54,33,30,36,25,54,23,54:2,44,24,28,54,26,54,31,40,29,41,5" +
"4,46,54:3,11,4,12,4,54,4,18,39,54,32,21,34,15,54,13,54:2,43,14,19,54,17,54," +
"22,37,20,38,54,45,54:3,4,49,4:3,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,129,
"0,1,2,1,3,1:9,4,1,5:3,1:5,6,1,2,7:5,8,7:8,1:3,9,1,8,7,1,10,2,11,12,13,14,15" +
",16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40" +
",41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65" +
",66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88")[0];

	private int yy_nxt[][] = unpackFromString(89,58,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,47:2,15,112,47:3,84,47,51,47:2,114,47:3,86" +
",47,102,47,115,16,116,125,47:2,128,47:2,87,88,104,105,17,18,19,20,21,22,23," +
"47,24,83,25,-1:113,26:2,-1:2,4,-1,4:55,-1:13,47,89,47,-1,47:2,27,47:14,28,-" +
"1,47:11,-1:7,47:3,-1:36,48,-1:23,46,-1:53,24:2,-1:14,47:3,-1,47:18,-1,47:11" +
",-1:7,47:3,-1:56,32:2,-1,1,49:56,45,-1,49:56,-1:14,47:3,-1,47:7,91,47:3,27," +
"47:6,-1,28,47:10,-1:7,47:3,-1:18,56,-1:53,47:3,-1,47:15,29,47:2,-1,47:11,-1" +
":7,47:3,-1:27,58,-1:44,47:3,-1,47:16,30,47,-1,47:11,-1:7,47:3,-1:19,60,-1:5" +
"2,47:3,-1,47:3,31,47:14,-1,47:11,-1:7,47:3,-1:28,62,-1:43,47:3,-1,47:12,31," +
"47:5,-1,47:11,-1:7,47:3,-1:14,64,-1:57,47:3,52,47:18,-1,47:11,-1:7,47:3,-1:" +
"24,66,-1:47,47:3,-1,47:4,33,47:13,-1,47:11,-1:7,47:3,-1:20,68,-1:51,47:3,54" +
",47:18,-1,47:11,-1:7,47:3,-1:29,70,-1:42,47:3,-1,47:13,33,47:4,-1,47:11,-1:" +
"7,47:3,-1:21,72,-1:50,47:3,-1,47:3,34,47:14,-1,47:11,-1:7,47:3,-1:30,74,-1:" +
"41,47:3,-1,47:12,34,47:5,-1,47:11,-1:7,47:3,-1:22,76,-1:49,47:3,-1,47:3,35," +
"47:14,-1,47:11,-1:7,47:3,-1:31,78,-1:40,47:3,-1,47:12,36,47:5,-1,47:11,-1:7" +
",47:3,-1:23,41,-1:48,47:3,-1,47:4,37,47:13,-1,47:11,-1:7,47:3,-1:32,42,-1:3" +
"9,47:3,-1,47:13,38,47:4,-1,47:11,-1:7,47:3,-1:23,43,-1:48,47:3,-1,47:4,39,4" +
"7:13,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:13,40,47:4,-1,47:11,-1:7,47:3,-1:2" +
",50,-1:53,24:2,-1:14,47:3,-1,47:2,53,47:15,-1,47:7,90,47:3,-1:7,47:3,-1:22," +
"80,-1:49,47:3,-1,47:11,55,47:6,-1,47:8,92,47:2,-1:7,47:3,-1:14,47:3,-1,47:4" +
",57,47:13,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:13,59,47:4,-1,47:11,-1:7,47:3" +
",-1:14,47:2,61,-1,47:18,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:18,-1,47,63,47:" +
"9,-1:7,47:3,-1:14,47:3,-1,47:8,65,47:9,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:" +
"18,-1,47:4,67,47:6,-1:7,47:3,-1:14,47:3,-1,47:17,127,-1,47:11,-1:7,47:3,-1:" +
"14,69,47:2,-1,47:18,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:6,71,47:11,-1,47:11" +
",-1:7,47:3,-1:14,47:3,-1,47:2,73,47:15,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:" +
"11,75,47:6,-1,47:11,-1:7,47:3,-1:14,47,77,47,-1,47:18,-1,47:11,-1:7,47:3,-1" +
":14,47:3,-1,47:7,79,47:10,-1,47:11,-1:7,47:3,-1:14,47,81,47,-1,47:18,-1,47:" +
"11,-1:7,47:3,-1:14,47:3,-1,47:7,82,47:10,-1,47:11,-1:7,47:3,-1:14,47:3,-1,4" +
"7:4,93,47:13,-1,47:11,-1:7,47:3,-1:21,85,-1:50,47:3,-1,47,94,47:16,-1,47:11" +
",-1:7,47:3,-1:14,47:3,-1,47:10,95,47:7,-1,47:11,-1:7,47:3,-1:14,96,47:2,-1," +
"47:18,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:6,97,47:11,-1,47:11,-1:7,47:3,-1:" +
"14,47:3,-1,47,98,47:16,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:10,99,47:7,-1,47" +
":11,-1:7,47:3,-1:14,47:3,-1,47,100,47:16,-1,47:11,-1:7,47:3,-1:14,47:3,-1,4" +
"7:10,101,47:7,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47,106,47:16,-1,47:11,-1:7,4" +
"7:3,-1:20,103,-1:51,47:3,-1,47:10,107,47:7,-1,47:11,-1:7,47:3,-1:14,47:3,-1" +
",47:5,108,47:12,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:14,109,47:3,-1,47:11,-1" +
":7,47:3,-1:14,47:3,-1,47:5,110,47:12,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:14" +
",111,47:3,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:17,117,-1,47:11,-1:7,47:3,-1:" +
"14,113,-1:57,47:3,-1,47:18,-1,118,47:10,-1:7,47:3,-1:14,47:3,-1,47:18,-1,47" +
":3,119,47:7,-1:7,47:3,-1:19,120,-1:52,47:3,-1,47:18,-1,47:6,121,47:4,-1:7,4" +
"7:3,-1:14,47:3,-1,47:18,-1,47:2,122,47:8,-1:7,47:3,-1:18,123,-1:53,47:3,126" +
",47:18,-1,47:11,-1:7,47:3,-1:14,47:3,-1,47:18,-1,47:5,124,47:5,-1:7,47:3,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ //. on a line by itself is EOF
			  return new Symbol(sym.EOF);}
					case -3:
						break;
					case 3:
						{
                        //skip newline, but reset char counter
                        yychar = 0;
                      }
					case -4:
						break;
					case 4:
						{ // ignore line comments
                      }
					case -5:
						break;
					case 5:
						{
		    throw new java.io.IOException("Unrecognised character: " +
							yytext());
		}
					case -6:
						break;
					case 6:
						{ // ignore whitespace
                      }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.COMMA);}
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.SEMI);}
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.COLON);}
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.LPAREN);}
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.RPAREN);}
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.LBRACKET);}
					case -13:
						break;
					case 13:
						{ return new Symbol(sym.RBRACKET);}
					case -14:
						break;
					case 14:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.MINUS); }
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.ASSIGN); }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.CMP, yytext()); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.NOT); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.LCMP, yytext()); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.PLUS); }
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.TIMES); }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.DIV); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.MOD); }
					case -24:
						break;
					case 24:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -25:
						break;
					case 25:
						{
			yybegin(STRING);
		}
					case -26:
						break;
					case 26:
						{
			// FRACTION
			return new Symbol(sym.FRACTION, new Double(yytext()));
		}
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.IN); }
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.IF); }
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.END);}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.END);}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.LET); }
					case -32:
						break;
					case 32:
						{
			// REAL no. used for defining frames
			return new Symbol(sym.REAL, new Double(yytext()));
		}
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.ELSE); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.WAIT); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.PAINT); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.PAINT); }
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.FRAME); }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.FRAME); }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.SUBFRAME); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.SUBFRAME); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.IMG_PAINTER);}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.IMG_PAINTER);}
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.DEF_PAINTER);}
					case -44:
						break;
					case 44:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -45:
						break;
					case 45:
						{
			yybegin(YYINITIAL);
		}
					case -46:
						break;
					case 47:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -47:
						break;
					case 48:
						{ return new Symbol(sym.CMP, yytext()); }
					case -48:
						break;
					case 49:
						{
			// constant string
			// System.out.println(yytext());
			return new Symbol(sym.STRING, yytext());
		}
					case -49:
						break;
					case 51:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -50:
						break;
					case 53:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -51:
						break;
					case 55:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -52:
						break;
					case 57:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -53:
						break;
					case 59:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -54:
						break;
					case 61:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -55:
						break;
					case 63:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -56:
						break;
					case 65:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -57:
						break;
					case 67:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -58:
						break;
					case 69:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -59:
						break;
					case 71:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -60:
						break;
					case 73:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -61:
						break;
					case 75:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -62:
						break;
					case 77:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -63:
						break;
					case 79:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -64:
						break;
					case 81:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -65:
						break;
					case 82:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -66:
						break;
					case 83:
						{
	       		// INTEGER
	       		return new Symbol(sym.INTEGER, new Integer(yytext()));
	       	}
					case -67:
						break;
					case 84:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -68:
						break;
					case 86:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -69:
						break;
					case 87:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -70:
						break;
					case 88:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -71:
						break;
					case 89:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -72:
						break;
					case 90:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -73:
						break;
					case 91:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -74:
						break;
					case 92:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -75:
						break;
					case 93:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -76:
						break;
					case 94:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -77:
						break;
					case 95:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -78:
						break;
					case 96:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -79:
						break;
					case 97:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -80:
						break;
					case 98:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -81:
						break;
					case 99:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -82:
						break;
					case 100:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -83:
						break;
					case 101:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -84:
						break;
					case 102:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -85:
						break;
					case 104:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -86:
						break;
					case 105:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -87:
						break;
					case 106:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -88:
						break;
					case 107:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -89:
						break;
					case 108:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -90:
						break;
					case 109:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -91:
						break;
					case 110:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -92:
						break;
					case 111:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -93:
						break;
					case 112:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -94:
						break;
					case 114:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -95:
						break;
					case 115:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -96:
						break;
					case 116:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -97:
						break;
					case 117:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -98:
						break;
					case 118:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -99:
						break;
					case 119:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -100:
						break;
					case 121:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -101:
						break;
					case 122:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -102:
						break;
					case 124:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -103:
						break;
					case 125:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -104:
						break;
					case 127:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -105:
						break;
					case 128:
						{
			// IDENTIFIER
			return new Symbol(sym.VAR, yytext());
		}
					case -106:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
