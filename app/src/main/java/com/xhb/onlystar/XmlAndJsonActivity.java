package com.xhb.onlystar;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xhb.R;
import com.xhb.bean.UserData;
import com.xhb.bean.WebURL;
import com.xhb.util.SAXParseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlAndJsonActivity extends AppCompatActivity {


    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse);
        result = (TextView) findViewById(R.id.result);
    }


    //parse file by SAX
    public void xml_sax(View view) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            SAXParseHandler saxParseHandler = new SAXParseHandler();
            xmlReader.setContentHandler(saxParseHandler);

            InputStream inputStream = getResources().openRawResource(R.raw.test);

            InputSource inputSource = new InputSource(inputStream);

            xmlReader.parse(inputSource);
            List<WebURL> webURLs = saxParseHandler.getXMLList();
            result.setText(webURLs.toString());
           /* // 简单写法---------------替换以上几步
            XMLReader xmlReaderTest = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReaderTest.setContentHandler(new SAXParseHandler());                            // 处理xml文件的
            xmlReaderTest.parse(new InputSource(getResources().openRawResource(R.raw.test)));  // xml文件*/
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //PULL
    public void xml_pull(View view) {
        // pull  只有在xml 里面才能读到
        XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.test);
        try {
            int eventType = xmlResourceParser.getEventType();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG) {
                    String tagName = xmlResourceParser.getName();
                    if (TextUtils.equals(tagName, "item")) {
                        String id = xmlResourceParser.getAttributeValue(0);
                        String uri = xmlResourceParser.getAttributeValue(1);
                        String content = xmlResourceParser.nextText();
                        Log.i("myLog", "id-->" + id + "   uri--->" + uri + "     content---->" + content);
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //DOM
    public void xml_dom(View view) {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.test);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //取得DocumentBuilderFactory实例
            DocumentBuilder builder = factory.newDocumentBuilder(); //从factory获取DocumentBuilder实例
            Document doc = builder.parse(inputStream);   //解析输入流 得到Document实例
            Element rootElement = doc.getDocumentElement();//获取根节点
            NodeList items = rootElement.getElementsByTagName("item");
            for (int i = 0; i < items.getLength(); i++) {
                WebURL webURL = new WebURL();
                Element element = (Element) items.item(i);
                webURL.setID(Integer.parseInt(element.getAttribute("id")));
                webURL.setUrl(element.getAttribute("url"));
                webURL.setContent(element.getTextContent());
                Log.i("myLog", "webUri---->" + webURL);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void json(View view) {
        // JSON
        InputStream is = getResources().openRawResource(R.raw.json);
        String jsonString = convertStreamToString(is);
        try {
            Log.i("myLog", "jsonString: " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            String title = jsonObject.getString("title");
            String content = jsonObject.getString("content");

            JSONObject userJSONObject = jsonObject.getJSONObject("user");
            long uId = userJSONObject.getLong("id");
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            Log.i("myLog", "title: " + title + "content: " + content + " uid: " + uId + " length:" + jsonArray.length());
            Gson gson = new Gson();
            UserData userData = gson.fromJson(jsonString, UserData.class);
            Log.i("myLog", "userData: " + userData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
