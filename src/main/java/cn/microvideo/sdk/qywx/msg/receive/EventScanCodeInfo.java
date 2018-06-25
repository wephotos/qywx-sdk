package cn.microvideo.sdk.qywx.msg.receive;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 扫码信息
 * @author Administrator
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventScanCodeInfo {
	/**
	 * 扫描类型，一般是qrcode 
	 */
	private String ScanType;
	/**
	 * 扫描结果，即二维码对应的字符串信息 
	 */
	private String ScanResult;
	
	/**
	 * 扫描类型，一般是qrcode
	 * @return 扫描类型
	 */
	public String getScanType() {
		return ScanType;
	}

	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	/**
	 * 扫描结果，即二维码对应的字符串信息 
	 * @return 扫描结果
	 */
	public String getScanResult() {
		return ScanResult;
	}

	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}

	@Override
	public String toString() {
		return "ScanCodeInfo [ScanType=" + ScanType + ", ScanResult="
				+ ScanResult + "]";
	}
}
