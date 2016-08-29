package com.xhb.onlystar;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.xhb.R;

import static android.R.attr.name;

public class TestWebviewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_webview);
        mWebView= (WebView) findViewById(R.id.show_web);
        //加载网页
        mWebView.loadUrl("http://www.baidu.com");
        //加载本地html
       // mWebView.loadUrl("file:///android_asset/test.html");

        //设置支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);

        //Js调用Java方法
       // mWebView.addJavascriptInterface(new TestJs(),"Xuhuibo");


        //设置为调试状态
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //            mWebView.setWebContentsDebuggingEnabled(true);
        //        }
        //浏览器输入        chrome://inspect/#devices


        //Cookie管理
        CookieManager manager=CookieManager.getInstance();//単例
        manager.setAcceptCookie(true);
        manager.setCookie("domain","cookie");
      //  manager.removeAllCookies();


        //设置浏览方式----->本应用还是系统浏览器
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //是不是重新加载 ，拦截url页面
              if(url.contains("404")){

               }
               // view.loadUrl("http://www.zhihu.com");
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始加载              显示loading动画
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //页面结束                 隐藏loading动画
                super.onPageFinished(view, url);
                //java调用JS
               // mWebView.loadUrl("javascript:javaCallJS()");
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //url 替换
                if(url.contains("logo.img")){

                }
                super.onLoadResource(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }

            //拦截网页请求
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }
        });


        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }


    //JS调用原生App
    public class TestJs{
        //安卓4.2加此注解才能生效
        @JavascriptInterface
        public void testJs(String js){
            Log.i("myLog", "testJs: "+js);
            Toast.makeText(TestWebviewActivity.this, "hi "+js, Toast.LENGTH_SHORT).show();
        }
    }

    //网页回退
    @Override
    public void onBackPressed() {
        if(mWebView!=null&&mWebView.canGoBack()){
            //历史记录
            WebBackForwardList list=mWebView.copyBackForwardList();
            WebHistoryItem item=list.getItemAtIndex(0);
            Log.i("myLog", "size: "+list.getSize()+" item"+item+" url"+item.getUrl()+" title"+item.getTitle());

            mWebView.goBack();//回退
            //mWebView.goForward();//前进
            //mWebView.goBackOrForward(+1);
        }else{
            super.onBackPressed();
        }

    }
}
