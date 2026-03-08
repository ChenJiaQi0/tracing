package org.jeecg.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @Description:
 * @Author: cjq
 * @Date: 2026.2.19
 * @Version: V1.0
 */
public class Base64Utils {
    /**
     * 将base64解密转为输入流
     *
     * @param base64Str，要把base64前缀去掉
     * @return
     */
    public static InputStream base64ToInputStream(String base64Str) {
        if (base64Str.contains(",")) {
            base64Str = base64Str.split(",")[1];
        }
        base64Str = base64Str.replaceAll("\n", "").replaceAll("\r", "");
        try {
            byte[] bytes = Base64.getDecoder().decode(base64Str);
            return new ByteArrayInputStream(bytes);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将内网地址对应的图片转换为base64
     *
     * @param imagePath 网络图片路径
     * @return res 图片对应的Base64编码
     */
    public static String intranetOfImageChangeBase64(String imagePath) {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        try {
            InputStream inStream = FileDownloadUtils.getDownInputStream(imagePath, "");

            if (inStream == null) {
                return null;
            }

            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 对字节数组Base64编码
        byte[] encode = Base64.getEncoder().encode(outPut.toByteArray());
        String res = new String(encode);
        return res;
    }
}
