package cn.microvideo.sdk.qywx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import cn.microvideo.sdk.qywx.config.EnvConfig;
import cn.microvideo.sdk.qywx.exception.HttpClientException;
import cn.microvideo.sdk.qywx.exception.HttpSessionInvalidException;
import cn.microvideo.sdk.qywx.web.ThreadSession;

/**
 * 请求服务HTTP工具类，可保留当前请求的会话，代理HTTP请求
 * @ClassName: HttpUtils
 * @Description: HTTP请求工具类
 * @author Aaron.tian
 * @date 2016年10月28日
 *
 */
public final class HttpUtils {
	/**
	 * 日志
	 */
	private final static Logger logger = Logger.getLogger(HttpUtils.class);
	/**
	 * HTTP header key of cookie
	 */
	public final static String SET_COOKIE = "Set-Cookie";
	/**
	 * HTTPS
	 */
	public final static String SCHEME_HTTPS = "https";

	/**
	 * private default construction
	 */
	private HttpUtils() {
		throw new Error("类禁止生成实例");
	}

	/**
	 * 发送GET请求
	 * @param url 请求路径
	 * @return 获取到的结果字符串
	 * @throws HttpSessionInvalidException 
	 */
	public static String get(String url) throws HttpSessionInvalidException {
		HttpGet get = new HttpGet(getURI(url));
		return getHttpString(get);
	}

	/**
	 * 使用指定请示体发起POST请求
	 * @param url POST 地址
	 * @param postBody 发送的请求体
	 * @return 响应结果
	 * @throws HttpSessionInvalidException 
	 */
	public static String post(String url, String postBody) throws HttpSessionInvalidException {
		try {
			HttpPost post = new HttpPost(getURI(url));
			StringEntity body = new StringEntity(postBody, EnvConfig.charset);
			post.setEntity(body);
			return getHttpString(post);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持的编码", e);
		}
	}

	/**
	 * 发送POST请求
	 * @param url 请求地址
	 * @return 获取到的结果串
	 * @throws HttpSessionInvalidException 
	 */
	public static String post(String url, Map<String, String> params) throws HttpSessionInvalidException {
		HttpPost post = new HttpPost(getURI(url));
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		if (params != null){
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair bnvp = new BasicNameValuePair(entry.getKey(),entry.getValue());
				parameters.add(bnvp);
			}
		}
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,EnvConfig.charset);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return getHttpString(post);
	}

	/**
	 * 代理请求
	 * @param request
	 * @param response
	 * @param split 代理路径分隔串
	 * @param proxy 代理的地址
	 * @throws HttpSessionInvalidException 
	 */
	public static void doProxy(HttpServletRequest request, HttpServletResponse response, String split, String proxy) throws HttpSessionInvalidException {
		try {
			Objects.requireNonNull(proxy);
			HttpProxy.doProxy(request, response, split, proxy);
		} catch (IOException e){
			throw new HttpClientException("HttpUtils#doProxy()->"+e.getMessage(),e);
		}
	}
	/**
	 * 根据URL字符串生成URI对象s
	 * @param url URL地址
	 * @return URI对象
	 */
	private static URI getURI(String url){
		try {
			URL _url = new URL(url);
			URI uri = new URI(_url.getProtocol(), _url.getUserInfo(),
					_url.getHost(), _url.getPort(), _url.getPath(),
					_url.getQuery(), null);
			return uri;
		} catch (MalformedURLException | URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 获取响应的HTTP数据实体
	 * @param request
	 * @return
	 * @throws HttpSessionInvalidException 
	 */
	private static HttpResponse getHttpResponse(HttpUriRequest request) throws HttpSessionInvalidException{
		try{
			String host = request.getURI().getHost();
			obtainCookieFromLocalSession(host, request);
			HttpClient client = HttpClientFactory.INSTANCE.getHttpClient(request);
			HttpResponse response = client.execute(request);
			obtainCookieIntoLocalSession(host, response);
			return response;
		}catch(IOException e){
			throw new HttpClientException("获取响应结果时发生错误",e);
		}
	}
	/**
	 * 获取请求地址返回的字符串
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws HttpSessionInvalidException 
	 */
	private static String getHttpString(HttpUriRequest request) throws HttpSessionInvalidException {
		try{
			HttpEntity entity = getHttpResponse(request).getEntity();
			return EntityUtils.toString(entity);
		}catch(IOException e){
			throw new HttpClientException("读取响应结果字符串错误",e);
		}finally{
			request.abort();
		}
	}

	/**
	 * 将当前会话中的Cookie置入请求头中
	 * @param host 使用主机串获取Cookie
	 * @param request HTTP请求对象
	 */
	private static void obtainCookieFromLocalSession(String host, HttpUriRequest request) {
		String cookie = ThreadSession.getAttribute(host);
		if (cookie != null) {
			request.setHeader("Cookie", cookie);
		}
	}

	/**
	 * 将服务返回的Cookie放入本地Session中
	 * @param host  使用主机串存在Cookie
	 * @param response HTTP请求响应对象
	 * @throws HttpSessionInvalidException 
	 */
	private static void obtainCookieIntoLocalSession(String host, HttpResponse response) throws HttpSessionInvalidException {
		if(response.getStatusLine().getStatusCode() == 302){
			ThreadSession.removeAttribute(host);
			throw new HttpSessionInvalidException("请求发生了重定向，会话可能已经失效...");
		}
		Header[] headers = response.getHeaders(SET_COOKIE);
		if (headers.length > 0) {
			StringBuilder cookie = new StringBuilder(50);
			for (Header header : headers) {
				for (HeaderElement element : header.getElements()) {
					cookie.append(element.getName());
					cookie.append("=");
					cookie.append(element.getValue());
					cookie.append(";");
				}
			}
			if (cookie.length() > 0) {
				cookie = cookie.deleteCharAt(cookie.length() - 1);
				ThreadSession.setAttribute(host, cookie.toString());
			}
		}
	}

	/**
	 * 设置SSL证书全信任
	 * @author Administrator
	 *
	 */
	final static class HttpClientFactory {
		/**
		 * 类实例
		 */
		public final static HttpClientFactory INSTANCE = new HttpClientFactory();
		/**
		 * 默认的连接客户端
		 */
		private static HttpClient defautClient;
		/**
		 * 处理SSL协议的客户端
		 */
		private static HttpClient defautClient_SSL;
		/**
		 * 无参构造，初始化
		 */
		private HttpClientFactory() {
			_initHttpsClient();
			_initDefaultClient();
		}
		/**
		 * 根据HttpUriRequest获取HttpClient对象
		 * @param request
		 * @return
		 */
		public HttpClient getHttpClient(HttpUriRequest request){
			String scheme = request.getURI().getScheme();
			if (SCHEME_HTTPS.equals(scheme)) {
				return defautClient_SSL;
			}else{
				return defautClient;
			}
		}
		/**
		 * 获取默认的转接客户端
		 * @return
		 */
		private void _initDefaultClient(){
			ThreadSafeClientConnManager conman = new ThreadSafeClientConnManager();
			conman.setMaxTotal(200);
			conman.setDefaultMaxPerRoute(50);
			defautClient = new DefaultHttpClient(conman);
			defautClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
			defautClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
		}
		/**
		 * HTTPS协议请求封装
		 * @return HTTPS请求客户端
		 */
		private void _initHttpsClient() {
			try {
				X509TrustManager x509TrustManager = new X509TrustManager() {
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
					}

					@Override
					public void checkClientTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
					}
				};
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(null, new TrustManager[] { x509TrustManager }, null);
				SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("https", 443, ssf));
				ThreadSafeClientConnManager conman = new ThreadSafeClientConnManager(registry);
				conman.setMaxTotal(200);
				conman.setDefaultMaxPerRoute(50);
				defautClient_SSL = new DefaultHttpClient(conman);
				defautClient_SSL.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
				defautClient_SSL.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
			} catch (Exception e) {
				Logger.getLogger(HttpClientFactory.class).error("SSL设置错误", e);
				throw new RuntimeException("SSL设置错误：" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @ClassName: HttpProxy
	 * @Description: Http代理
	 * @author Aaron.tian
	 * @date 2016年10月27日
	 *
	 */
	final static class HttpProxy {
		/**
		 * 执行HTTP代理
		 * 
		 * @param request
		 * @param response
		 * @param split 代理分隔字符串
		 * @param proxy 代理地址
		 * @throws IOException 
		 * @throws HttpSessionInvalidException 
		 */
		static void doProxy(HttpServletRequest request, HttpServletResponse response, String split, String proxy) throws IOException, HttpSessionInvalidException {
			final String url = getRealServiceURL(request, split, proxy);
			logger.info("proxy url =>"+url);
			HttpResponse _response;
			if ("GET".equalsIgnoreCase(request.getMethod())) {
				HttpGet get = new HttpGet(getURI(url));
				_response = getHttpResponse(get);
			} else {
				HttpPost post = new HttpPost(getURI(url));
				post.addHeader("Accept", request.getHeader("Accept"));
				post.addHeader("Content-Type",request.getContentType());
				post.addHeader("Accept-Charset",request.getCharacterEncoding());
				post.addHeader("Accept-Encoding",request.getHeader("Accept-Encoding"));
				post.addHeader("X-Requested-With",request.getHeader("X-Requested-With"));
				String disposition = request.getHeader("Content-disposition");
				if(StringUtils.isNotEmpty(disposition)){
					post.addHeader("Content-disposition", disposition);
				}
				InputStream stream = request.getInputStream();
				HttpEntity streamEntity = new InputStreamEntity(stream, request.getContentLength());
				post.setEntity(streamEntity);
				_response = getHttpResponse(post);
			}
			output(_response, response);
		}

		/**
		 * 将服务响应对象中的数据输出到客户端对象中
		 * @param _response 请求服务的响应对象
		 * @param response 客户端响应对象
		 * @throws IOException
		 */
		private static void output(HttpResponse _response, HttpServletResponse response) throws IOException {
			Header disposition = _response.getFirstHeader("Content-disposition");
			if (disposition != null) {
				response.addHeader(disposition.getName(),disposition.getValue());
			}
			HttpEntity entity = _response.getEntity();
			try (InputStream inStream = entity.getContent()) {
				byte[] buffer = new byte[4096];
				int len = -1;
				ServletOutputStream ouStream = response.getOutputStream();
				while ((len = inStream.read(buffer)) != -1) {
					ouStream.write(buffer, 0, len);
				}
				ouStream.flush();
			}
		}
	}

		/**
		 * 构建真实的服务请求地址
		 * @param request
		 * @param split 分隔URL中真实PATH的字符串
		 * @param proxy 代理地址
		 * @return
		 */
		private static String getRealServiceURL(HttpServletRequest request, String split, String proxy) {
			String uri = request.getRequestURI();
			if(split == null){
				split = request.getContextPath();
			}
			int _index = uri.indexOf(split);
			if (_index > -1) {
				uri = uri.substring(_index + split.length());
			}else{
				throw new RuntimeException("无法在代理URL中发现分隔字符串:" + split);
			}
			String query = request.getQueryString();
			StringBuilder url = new StringBuilder(proxy);
			url.append(uri);
			if (query != null) {
				url.append("?");
				url.append(query);
			}
			return url.toString();
		}
}
