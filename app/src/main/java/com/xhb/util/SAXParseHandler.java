package com.xhb.util;

import android.text.TextUtils;
import android.util.Log;


import com.xhb.bean.WebURL;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParseHandler extends DefaultHandler {

    public static final String ITEM = "item";
    List<WebURL> mWebURLs;
    WebURL mWebURL;

    int type = 1;
    boolean mIsItem;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mWebURLs = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mWebURL = new WebURL();
        if(TextUtils.equals(localName, ITEM)){
            for (int i = 0; i < attributes.getLength(); i++) {
                if(TextUtils.equals(attributes.getLocalName(i), "id")){
                    mWebURL.setID(Integer.valueOf(attributes.getValue(i)));
                }
                if(TextUtils.equals(attributes.getLocalName(i), "url")){
                    mWebURL.setUrl(attributes.getValue(i));
                }
            }
            mIsItem = true;
        }else{
            mIsItem = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(TextUtils.equals(localName, ITEM)){
            mWebURLs.add(mWebURL);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String content = String.valueOf(ch,start,length);
        Log.i("myLog","content---"+content+"     boolean---"+mIsItem);
        if(mIsItem){
            mWebURL.setContent(content);
            mIsItem = false;
        }

    }

    public List<WebURL> getXMLList() {
        return mWebURLs;
    }
}
