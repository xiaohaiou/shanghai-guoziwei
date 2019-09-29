package com.softline.util;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionService {

	// 用法参考
	// String[] arr1 = {"M116","M140","M250","M120","M98"};
	// String[] arr2 = {"M116","M140","M250","M187","M98","M120"};
	// Collection<String> collection1 = toCollection(arr1);
	// Collection<String> collection2 = toCollection(arr2);
	// Collection<String> collection = intersection(collection1, collection2);
	// for (String s : collection) {
	// System.out.println(s);
	// }

	/**
	 * 求两个集合的交集
	 * 
	 * @param collection1
	 *            集合1
	 * @param collection2
	 *            集合2
	 * @return
	 */
	public static <T> Collection<T> intersection(Collection<T> collectionOne,

	Collection<T> collectionTwo) {
		Collection<T> collection = new ArrayList<T>();
		for (T t : collectionOne) {
			if (collectionTwo.contains(t)) {
				collection.add(t);
			}
		}
		return collection;

	}

	/**
	 * 将数组数据转换为集合
	 * 
	 * @param arr
	 *            待转换的数组
	 * @return 数组所对应的集合
	 */
	public static <T> Collection<T> toCollection(T[] arr) {
		Collection<T> collection = new ArrayList<T>();
		for (T t : arr) {
			collection.add(t);
		}
		return collection;
	}

	/**
	 * 将数组数据转换为集合
	 * 
	 * @param arr
	 *            待转换的数组
	 * @return 数组所对应的集合
	 */
	public static <T> Collection<T> toCollection(ArrayList<T> arr) {
		Collection<T> collection = new ArrayList<T>();
		for (T t : arr) {
			if (!collection.contains(t)) {
				collection.add(t);
			}
		}
		return collection;
	}
}
