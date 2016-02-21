package hpl.sys;

import java.awt.*;
import java.io.*;
import hpl.lang.*;
import hpl.values.*;
import javax.swing.JFrame;

public class HPLRepl {

    static JFrame display;	// window for display area
    static HPLEvaluator interp;
    static HPLContext globalEnv;

    //static Screen screen;	// display area
    //static PainterFrame frame;	// top level frame
    static Painter lastResult;	// painter to be drawn

    /**
     * Setup a global environment, execute any programs supplied on
     * the command line, and if desired execute the Read Eval Print
     * Loop.
     *
     * @param args a <code>String[]</code> value
     */
    public static void main(String[] args) {
	// setup graphics, interpreter and global environment
	setup();

	boolean interactive = true;
        for (String arg : args) {
            if (arg.equals("-e")) {
                interactive = false;
            } else {
                try {
                    parseEvalShow(new FileReader(new File(arg)), globalEnv);
                } catch (FileNotFoundException fnfe) {
                    System.out.println("Could not find file " + arg);
                }
            }
        }

	if (interactive)
	    repl(System.in, globalEnv);
    }

    public static void setup() {
	display = new JFrame("Painter Display");
	//display.setSize(400, 400);
	display.getContentPane().setLayout(new GridLayout(1,1));
	display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	interp = new HPLEvaluator();
        globalEnv = interp.mkInitialContext();

	Screen screen = interp.getScreen();
	display.getContentPane().add(screen);
	display.addComponentListener(screen);
	display.setSize(screen.getPreferredSize());
	display.setVisible(true);
    }

    /**
     * In an infinite loop: read HPL commands in from standard input,
 evaluate them as a sequence with respect to the given
 environment, and display the lastResult.
     *
     * @param is the input stream of characters encoding the HPL commands
     * @param genv a <code>HPLEnvironment</code> value
     */
    public static void repl(InputStream is, HPLContext genv) {
        final String FIRST_PROMPT = "HPL+> ";
        final String NEXT_PROMPT = " ...  ";
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                System.out.print(FIRST_PROMPT);
                StringBuffer input = new StringBuffer();
                String line = reader.readLine();
                while (line != null && !line.equals(".")) {
                    System.out.print(NEXT_PROMPT);
                    input.append("\n");
                    input.append(line);
                    line = reader.readLine();
                }
                StringReader r = new StringReader(new String(input));
                parseEvalShow(r, genv);
            }
        } catch (IOException ex) {
            System.out.println("Bye bye!");
        }
    }

    /**
     *
     * @param r The reader containing the program fragment to be interpreted
     * @param env The environment w.r.t. which the fragment should be evaluated
     */
    public static void parseEvalShow(Reader r, HPLContext env) {
	HPLLexer lexer;
        HPLParser parser;
	PIRProgram commands = null;

	try {
            lexer = new HPLLexer(r);
	    parser = new HPLParser(lexer);
	    commands = (PIRProgram) parser.parse().value;
	} catch (Exception e) {
	    System.out.println("Syntax Error: " + e.getMessage());
	}

	Painter result;
	if (commands != null)
	    try {
		result = commands.visit(interp, env);
		if (result != Painter.DEFAULT) {
                    System.out.println("\n" + result.toString());
		} else {
		    System.out.println("\nNo result");
		}
	    } catch (HPLException hple) {
		System.out.println("Runtime Error: " + hple.report());
	    }
    }

}
