package com.lane.interview.algorithm.day4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 手写加强堆
 */
public class HeapGreater<T> {

	private ArrayList<T> heap;
	//反向索引表
	// [a,b,c]
	// a -> 0
	// b -> 1
	// c -> 2
	private HashMap<T, Integer> indexMap;
	private int heapSize;
	private Comparator<? super T> comp;

	public HeapGreater(Comparator<? super T> c) {
		heap = new ArrayList<>();
		indexMap = new HashMap<>();
		heapSize = 0;
		comp = c;
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public int size() {
		return heapSize;
	}

	public boolean contains(T obj) {
		return indexMap.containsKey(obj);
	}

	public T peek() {
		return heap.get(0);
	}

	public void push(T obj) {
		heap.add(obj);
		indexMap.put(obj, heapSize);
		heapInsert(heapSize++);
	}

	/*
	* 弹出元素
	* 思路：
	* 1.将堆顶元素和堆中最后一个元素交换
	* 2.删除最后一个元素
	* 3.针对堆顶元素调整堆
	* */
	public T pop() {
		T ans = heap.get(0);
		swap(0, heapSize - 1);
		indexMap.remove(ans);
		heap.remove(--heapSize);
		heapify(0);
		return ans;
	}

	/*
	* 删除一个元素
	* 思路：
	* 1.将要删除的元素和堆中最后一个元素交换
	* 2.删除最后一个元素
	* 3.调整堆
	* */
	public void remove(T obj) {
		// replace是最后一个元素
		T replace = heap.get(heapSize - 1);
		// index是要删除元素的索引
		int index = indexMap.get(obj);
		// 反向索引表中把要删除元素干掉
		indexMap.remove(obj);
		// 把最后一个元素在堆中干掉
		heap.remove(--heapSize);
		// 判断要删除的元素是否是最后一个元素：如果是，不用调整堆;如果不是，调整堆
		if (obj != replace) {
			heap.set(index, replace);
			indexMap.put(replace, index);
			resign(replace);
		}
	}

	/*
	* 调整堆
	* */
	public void resign(T obj) {
		heapInsert(indexMap.get(obj));
		heapify(indexMap.get(obj));
	}

	// 请返回堆上的所有元素
	public List<T> getAllElements() {
		List<T> ans = new ArrayList<>();
		for (T c : heap) {
			ans.add(c);
		}
		return ans;
	}

	private void heapInsert(int index) {
		while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
			swap(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	private void heapify(int index) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
			best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
			if (best == index) {
				break;
			}
			swap(best, index);
			index = best;
			left = index * 2 + 1;
		}
	}

	private void swap(int i, int j) {
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		heap.set(i, o2);
		heap.set(j, o1);
		indexMap.put(o2, i);
		indexMap.put(o1, j);
	}

}
