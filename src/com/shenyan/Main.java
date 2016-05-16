package com.shenyan;

import java.io.*;
import java.net.*;

public class Main {

    public static void HttpDemo() {
        String url = "http://127.0.0.1:9898/appFeedback";
        String requestBody = "this is from java http demo";
        BufferedWriter output = null;
        InputStream input = null;
        ByteArrayOutputStream buf = null;
        try {


            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("content-type", "text/html");
            conn.setRequestProperty("Head1", "HeadValue1");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);

            conn.connect();
//            if (requestBody != null && !requestBody.equals("")) {
//                output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//                output.write(requestBody);
//                output.flush();
//                output.close();
//            }
            input = conn.getInputStream();
            byte[] response;
            buf = new ByteArrayOutputStream();
            byte[] ch = new byte[256];
            int len;
            while ((len = input.read(ch)) >= 0) {
                buf.write(ch, 0, len);
            }
            response = buf.toByteArray();
            buf.close();
            input.close();
            String backstring = new String(response, "UTF-8");
            System.out.println("backstring = " + backstring);
        } catch (MalformedURLException | SocketTimeoutException | ProtocolException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            String data = e.toString();
            if (data.toLowerCase().contains("unknownhost")) {
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            mHandler.sendEmptyMessage(FAIL);
            try {
                if (buf != null) {
                    buf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        // write your code here
        HttpDemo();
    }
}
