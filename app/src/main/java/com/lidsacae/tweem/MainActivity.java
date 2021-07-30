package com.lidsacae.tweem;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.net.*;
import android.util.*;
import java.util.*;

public class MainActivity extends Activity 
{	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		webV = (WebView) findViewById(R.id.webV);
		webViewSettings =  new WebViewSettings(MainActivity.this);
		webViewSettings.InitWebView();
		webViewSettings.ChangeWebViewSize(0.7, 1);
    }
	WebView webV; 
	WebViewSettings webViewSettings;
	WebSettings webSettings;
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		ApplyMenuSettings(menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.reload_page:
				webViewSettings.Reload_Page();
				return true;
			case R.id.m1:
				
				// Code for About goes here
				return true;
			case R.id.m2:
				webViewSettings.ChangeWebViewSize(1, 1);
				return true;
			case R.id.m3:
				
				// SignOut method call goes here
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	public void ApplyMenuSettings(Menu menu){
		for(int i=0;i<menu.size();i++) {
			//menu.getItem(i).setTitle();;
		}
	}
	
	
	
	
	
	
	
	
	class WebViewSettings{
		final Context context;
		final WebViewSettings Instance = this;
		public WebViewSettings(Context _context){
			context = _context;
		}
		private ArrayList <String> UrlHistory = new ArrayList<>();
		public void InitWebView(){ 
			webSettings = webV.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webV.getSettings().setBuiltInZoomControls(true);
			if (android.os.Build.VERSION.SDK_INT >= 11){
				webV.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
				webV.getSettings().setDisplayZoomControls(false);
			}
			this.LoadUrl(webV, "http://localhost:8080/");
			
			webV.setWebChromeClient(new WebChromeClient() {
					//Other methods for your WebChromeClient here, if needed..
					@Override
					public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
						return super.onJsAlert(view, url, message, result);
					}
					
			});
			webV.setWebViewClient(new WebViewClient(){
					@Override
					public boolean shouldOverrideUrlLoading(WebView view, String url) {
						if (url.startsWith("tel:")) {
							Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
							startActivity(intent);
						}else if(url.startsWith("http:") || url.startsWith("https:")) {
							Instance.LoadUrl(view, url);
						}
						return true;
					}
					@Override
					public void onPageFinished(WebView webView, String Url){
						super.onPageFinished(webView, Url);
						webView.invalidate();
						webView.requestLayout();
					}
					
					
			});
		}
		public void ChangeWebViewSize(double ScaleWidth, double ScaleHeight){ 
			DisplayMetrics metrics = new DisplayMetrics();
			MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

			android.view.ViewGroup.LayoutParams layoutParamsLinear= webV.getLayoutParams();
			Toast.makeText(MainActivity.this, metrics.widthPixels+"; "+layoutParamsLinear.width+"// "+metrics.heightPixels+"; "+layoutParamsLinear.height, Toast.LENGTH_LONG).show();
			layoutParamsLinear.height = (int) (metrics.heightPixels*ScaleHeight);
			layoutParamsLinear.width = (int) (metrics.widthPixels*ScaleWidth);
			Toast.makeText(MainActivity.this, metrics.widthPixels+"| "+layoutParamsLinear.width+"\\ "+metrics.heightPixels+"| "+layoutParamsLinear.height, Toast.LENGTH_LONG).show();;
			webV.setLayoutParams(layoutParamsLinear);
			
			webV.setInitialScale(1);
			webSettings.setLoadWithOverviewMode(true);
			webSettings.setUseWideViewPort(true);
		}
		public void Reload_Page(){
			webV.loadUrl(UrlHistory.get(UrlHistory.size()-1));
		}
		public void LoadUrl(WebView webV, String Url){
			UrlHistory.add(Url);
			webV.loadUrl(Url);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}//EO Web
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
