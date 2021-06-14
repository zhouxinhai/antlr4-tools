package com.newland.bd.workflow.python3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.bd.workflow.python3.api.Python3Inspect;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhouxinhai on 2020/2/27
 */
public class TestPython3Inspect {

    void jsonResAssertSuccess(String retJson) {
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        if (!res) {
            System.out.println(retJson);
        }
        Assert.assertEquals(res, true);
    }

    void jsonResAssertFailed(String retJson) {
        JSONObject jsonObject = JSON.parseObject(retJson);
        boolean res = jsonObject.getBoolean("success");
        if (res) {
            System.out.println(retJson);
        }
        Assert.assertEquals(res, false);
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

    @Test
    void stringTest() {
        String codes = "try:\n" + "    import grp\n" + "    import pwd\n" + "    UID_GID_SUPPORT = True\n" + "except ImportError:\n" + "    UID_GID_SUPPORT = False";
        //System.out.println(codes);
        String retJson = Python3Inspect.inspect(codes);
        jsonResAssertSuccess(retJson);
    }

    @Test
    void filesTest() {
        final File root = new File("src/test/python");
        final List<File> files = new ArrayList<>();
        scan(root, files);

        final int total = files.size();
        int counter = 0;

        for (File file : files) {
            counter++;
            System.out.printf("解析python文件 %s/%s: '%s' ... ", counter, total, file.getName());
            try {
                String retJson = Python3Inspect.inspect(file);
                jsonResAssertSuccess(retJson);
                System.out.println("成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("解析python文件失败 " + file.getAbsolutePath());
            }
        }
    }
}
