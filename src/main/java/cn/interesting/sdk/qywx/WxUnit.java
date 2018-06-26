package cn.interesting.sdk.qywx;

import cn.interesting.sdk.qywx.exception.ErrcodeException;
import cn.interesting.sdk.qywx.exception.HttpSessionInvalidException;
import cn.interesting.sdk.qywx.msg.send.Messager;
import cn.interesting.sdk.qywx.msg.send.NewsMessage;
import cn.interesting.sdk.qywx.utils.WXUtils;

/**
 * 测试
 * @author Administrator
 *
 */
public class WxUnit {

	public static void main(String[] args) throws ErrcodeException, HttpSessionInvalidException {
		WXUtils.initConfig("classpath:cn/interesting/sdk/qywx/config/weixin-config.xml");
		
		NewsMessage news = new NewsMessage();
		news.setTouser("110");
		news.add("百度", "百度一下，你就知道", "https://www.baidu.com", "https://www.baidu.com/img/bd_logo1.png");
		Messager.send(news);
	}
}
