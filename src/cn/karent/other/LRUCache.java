package cn.karent.other;
import java.util.Map;
import java.util.HashMap;

/**
 * Least Recently Used 算法实现
 * 整体算法实现采用 链表+哈希实现
 * @author wan
 *
 */
public class LRUCache {
	
	private class LinkNode {
		String key;
		String value;
		LinkNode pre;
		LinkNode next;
		public LinkNode() {
			
		}
		
		public LinkNode(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private LinkNode head;  // head所指向的节点在缓存将满时会被删除
	
	private LinkNode end;   // 每次指向最新的节点
	
	private Map<String, LinkNode> map;
	
	private int capcity = 5; // 缓存容量
	
	private int n;
	
	public LRUCache(int capcity) {
		head = new LinkNode();
		end = new LinkNode();
		head.next = end;
		end.pre = head;
		map = new HashMap<String, LinkNode>();
		this.capcity = capcity;
		this.n = 0;
	}
	
	private void removeLRU() {
		LinkNode node = head.next;
		head.next = node.next;
		node.next.pre = head;
		map.remove(node.key);
	}
	
	private void addNode(LinkNode node) {
		node.next = end;
		node.pre = end.pre;
		end.pre.next = node;
		end.pre = node;
	}
	
	public void put(String key, String value) {
		if( map.containsKey(key) ) {  // 如果存在, 那么就更新数据
			LinkNode node = map.get(key);
			node.value = value;
			refresh(key);
		} else {
			if( n >= capcity) {  // 当缓存存满了之后，移除最不常用的那个数据
				removeLRU();
			}
			LinkNode node = new LinkNode(key, value);
			addNode(node);
			map.put(key, node);
			n++;
		}
	}
	
	/**
	 * 刷新缓存, 将key所指向的node移到最新的地方
	 * @param key
	 */
	private void refresh(String key) {
		LinkNode node = map.get(key);
		if( node.next == end) // 已经是最新的了，不用更新
			return;
		LinkNode pre = node.pre;
		LinkNode next = node.next;
		pre.next = next;
		next.pre = pre;
		addNode(node);
	}
	
	/**
	 * 获取信息后应该更新信息
	 * @param key
	 * @return
	 */
	public String get(String key) {
		if(!map.containsKey(key) )
			return null;
		LinkNode node = map.get(key);
		refresh(key);
		return node.value;
	}
	
	public void remove(String key) {
		if( map.containsKey(key) ) {
			LinkNode node = map.get(key);
			LinkNode pre = node.pre;
			LinkNode next = node.next;
			pre.next = next;
			next.pre = pre;
			n--;
		}
	}
}
