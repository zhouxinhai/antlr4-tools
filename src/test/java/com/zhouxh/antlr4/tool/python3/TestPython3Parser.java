package com.zhouxh.antlr4.tool.python3;

import com.zhouxh.antlr4.tool.python3.parser.Python3Parser;
import com.zhouxh.antlr4.tool.python3.parserExt.Builder;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestPython3Parser {

    @Test
    public void trailingCommentShouldPass() {
        new Builder.Parser("def func():\n" + "  return 42\n" + "  # comment").build().file_input();
    }

    @Test
    public void leadingCommentShouldPass() {
        new Builder.Parser("# comment\n" + "def func():\n" + "  return 42").build().file_input();
    }

    @Test
    public void leadingAndTrailingCommentShouldPass() {
        new Builder.Parser("# line break\n" + "def func():\n" + "  return 42\n" + "  # comment").build().file_input();
    }

    // Scans the root for ".py" files and puts them in 'files', recursively.
    private void scan(File root, List<File> files) {

        File[] contents = root.listFiles();

        if (contents != null) {
            for (File file : contents) {
                if (file.isFile() && file.getName().endsWith(".py")) {
                    files.add(file);
                } else if (file.isDirectory() && !file.getName().equals("test") && !file.getName().equals("tests")) {
                    // Only go into folders other than 'test' and 'tests', which contain invalid Python source files.
                    this.scan(file, files);
                }
            }
        }
    }

    /**
     * Parses all ".py" files that reside in 'src/test/python', recursively.
     * <p>
     * Note that running this may take 20 to 30 seconds to complete: it parses
     * all files from Python 3's standard library (http://hg.python.org/cpython/file/default/Lib/).
     */
    @Test
    public void testParser() {

        final File root = new File("src/test/python");
        final List<File> tests = new ArrayList<>();

        scan(root, tests);

        final int total = tests.size();
        int counter = 0;

        for (File test : tests) {

            counter++;

            System.out.printf("parsing %s/%s: '%s' ... ", counter, total, test.getName());

            try {
                Python3Parser parser = new Builder.Parser(new ANTLRInputStream(new FileInputStream(test))).build();
                parser.file_input();
                System.out.println("OK");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("failed to parse " + test.getAbsolutePath());
            }
        }
    }





}
