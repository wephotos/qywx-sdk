package com.github.wephotos.sdk.qywx.contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * 用户扩展属性
 * @author Aaron.tian
 *
 * @param <K> 扩展属性键
 * @param <V> 扩展属性值
 */
public class UserExtattr<K,V> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 人员扩展属性
	 */
	private List<UserExtattr.Entry<K, V>> attrs = new ArrayList<UserExtattr.Entry<K, V>>();
	
	/**
	 * 获取扩展属性
	 * @return 扩展属性集合
	 */
	public List<UserExtattr.Entry<K, V>> getAttrs() {
		return attrs;
	}
	
	/**
	 * 添加扩展属性
	 * @param name 属性名称
	 * @param value 属性值
	 */
	public void put(K name,V value){
		for(Entry<K, V> entry:attrs){
			if(entry.getName().equals(name)){
				attrs.remove(entry);
				break;
			}
		}
		Entry<K, V> entry = new Entry<K, V>();
		entry.setName(name);
		entry.setValue(value);
		this.attrs.add(entry);
	}
	
	/**
	 * 获取扩展属性值
	 * @param k 扩展属性键
	 * @return 扩展属性值
	 * @throws NotFoundExpcetion 
	 */
	@Nullable
	public V get(K k){
		for(Entry<K, V> entry:attrs){
			if(entry.getName().equals(k)){
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * 用户扩展属性类
	 * @author Administrator
	 *
	 * @param <K> 键
	 * @param <V> 值
	 */
	static class Entry<K,V> implements Serializable{
		private static final long serialVersionUID = 1L;
		private K name;
		private V value;
		public K getName() {
			return name;
		}
		public void setName(K name) {
			this.name = name;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
}
