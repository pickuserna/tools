

import java.util.Scanner;
import java.util.Stack;

//城市节点
//!!!!需要注意， 重复更新Center的话，不可以覆盖更新时间
//???是否需要将数据存下来
//!!!!findMyArc 需要优化
//ways lastUpdateTime

class Node {
	Node outer = this;

	class Way {
		Arc myArc;
		Arc zArc;
		Node zNode;// zNode is a crossing Node or a center city
		int dis;
		int updateTimeWay;// 使用更新时间

		public Way(Arc myArc, Arc zArc, Node zNode, int dis, int updateTime) {
			super();
			this.myArc = myArc;
			this.zArc = zArc;
			this.zNode = zNode;
			this.updateTimeWay = updateTime;
			this.dis = dis;
		}

		public void setWay(Arc myArc, Arc zArc, Node zNode, int dis,
				int updateTime) {
			this.myArc = myArc;
			this.zArc = zArc;
			this.zNode = zNode;
			this.updateTimeWay = updateTime;
			this.dis = dis;
		}

		public Node getMyNode() {
			return outer;
		}

		public Way getOutWay() {
			// only two

			return zNode.ways[1 - zArc.indexWay];
		}

		// return [zArc,myArc, ??, dis, upt]
		public Way getOppWay() {
			int zindex = this.zArc.indexWay;
			if (zindex == -1)
				return null;
			return this.zNode.ways[zindex];
		}

		public String toString() {
			return city + "->" + myArc.desCity + " " + zArc.desCity + ":->"
					+ zNode.city + " dis:" + dis + " uptWay:" + updateTimeWay;
		}
	}

	int preNotifyCity = -1;

	class Arc {
		int desCity;
		private Arc next;
		int indexWay = -1;

		public Arc(int dc) {
			this.desCity = dc;
			next = null;
			indexWay = -1;
		}

		public Arc getNext() {
			return next;
		}

		public Arc getOutArc() {
			if (this == firstArc)
				return lastArc;
			return firstArc;
		}

		public Node getMyNode() {
			return outer;
		}

	}

	int city;
	private Arc firstArc = null;
	private Arc lastArc = null;
	int countArcs = 0;

	Way[] ways;
	int topIndexWays;

	private int uptTime = 1;
	// private Way bestWay = null;
	private int minDis = Integer.MAX_VALUE;

	// construct the node with city
	public Node(int c) {
		this.city = c;

	}

	// arc
	public Arc createArc(int dc) {
		countArcs++;
		if (firstArc == null) {
			firstArc = lastArc = new Arc(dc);
		} else {
			this.lastArc.next = new Arc(dc);
			this.lastArc = this.lastArc.next;
		}
		return this.lastArc;
	}

	public Arc findMyArc(int dCity) {
		Node.Arc t = this.firstArc;
		while (t != null) {
			if (t.desCity == dCity)
				return t;
			t = t.next;
		}
		return null;
	}

	// way
	public Way createWay(Arc myArc, Arc zArc, Node zNode, int dis,
			int updateTime) {
		if (ways == null) {
			ways = new Way[countArcs];
			topIndexWays = 0;
		}
		if (myArc.indexWay == -1) {
			myArc.indexWay = topIndexWays;
			ways[topIndexWays++] = new Way(myArc, zArc, zNode, dis, updateTime);
			// myArc.isBuild = true;
			this.preNotifyCity = myArc.desCity;// ????????????????????????????????????????????????????????????
			return ways[topIndexWays - 1];
		} else {
			ways[myArc.indexWay].setWay(myArc, zArc, zNode, dis, updateTime);
			return ways[myArc.indexWay];
		}
	}

	public Node getzNode(Arc myArc) {
		return ways[myArc.indexWay].zNode;
	}

	public Way getWay(Arc myArc) {
		return ways[myArc.indexWay];
	}

	// mindis
	public void uptMinDis(int mindis, Way w, int upt) {
		this.minDis = mindis;
		w.updateTimeWay = upt;
	}

	public void setCenter() {
		this.minDis = 0;
		this.preNotifyCity = this.city;
		// this.bestWay = null;
		if (ways != null) {
			if (this.countArcs <= 2) {
				for (int i = 0; i < ways.length && ways[i] != null; i++) {
					Node dest = ways[i].zNode;
					Arc zArc = ways[i].zArc;
					dest.createWay(zArc, ways[i].myArc, this, ways[i].dis,
							this.city);
				}
			}
		}
	}

	public boolean isCenter() {
		return this.minDis == 0;
	}

	public boolean isOld(int sysUpt) {
		if (this.uptTime == sysUpt)
			return false;
		for (int i = 0; i < ways.length && ways[i] != null; i++) {
			Way oppw = ways[i].getOppWay();
			if (oppw == null)
				continue;
			else {
				if (oppw.updateTimeWay != ways[i].updateTimeWay)
					return true;
			}
		}
		return false;
	}

	public int getMinDis(int sysUpt) {
		if (this.minDis != 0 && sysUpt != this.uptTime) {
			for (int i = 0; i < ways.length && ways[i] != null; i++) {
				if (this.minDis > ways[i].dis + ways[i].zNode.minDis) {
					this.minDis = ways[i].dis + ways[i].zNode.minDis;
				}
			}

		}
		return this.minDis;
	}

	public void setUpdate(int syst) {
		this.uptTime = syst;
	}

	public Arc getFirstArc() {
		return this.firstArc;
	}

	public Arc getLastArc() {
		return this.lastArc;
	}

}

public class Main {
	int numCities, numQueries;
	Node[] tree;
	int sysUpdateTime = 0;
	static Main rs = new Main();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rs.readTree();
		//
	}

	// 读树结构
	public void readTree() {
		Scanner scan = new Scanner(System.in);
		numCities = scan.nextInt();
		numQueries = scan.nextInt();
		tree = new Node[numCities + 1];
		// 1 to numCities
		for (int i = 1; i <= numCities; i++) {
			tree[i] = new Node(i);
		}
		// read path
		for (int i = 1; i <= numCities - 1; i++) {
			int sc = scan.nextInt();
			int dc = scan.nextInt();
			tree[sc].createArc(dc);
			tree[dc].createArc(sc);
		}
//		scan.close();
		rs.createZMap();
		System.out.println();
		for(int i=0; i<numQueries; i++){
			int sel = scan.nextInt();
			int city = scan.nextInt();
			if(sel==2){
				int result = rs.query(city);
				System.out.println(result);
			}
			else{
				rs.update(city);
			}
		}
		scan.close();
	}

	//
	public void createZMap() {
		Stack s = new Stack();
		tree[1].setCenter();
		s.push(tree[1]);
		sysUpdateTime = 1;

		Stack straightStack = new Stack();
		while (!s.isEmpty()) {
			Node zNode = (Node) s.pop();// 使用内部类，自己定义遍历顺序，不受这些量的影响
			for (Node.Arc zArc = zNode.getFirstArc(); zArc != null; zArc = zArc
					.getNext()) {
				if (zArc.desCity == zNode.preNotifyCity) {
					continue;
				}
				int distance = 1;
				Node preNode = zNode;
				Node checkNode = tree[zArc.desCity];// 在这里可以封装，使得upTime自动更新
				Node.Arc myArc = null;
				// a straight line
				straightStack.clear();
				while (checkNode.countArcs == 2) {
					myArc = checkNode.getFirstArc();
					Node.Arc oppArc = checkNode.getLastArc();
					if (myArc.desCity != preNode.city) {
						Node.Arc temp = myArc;
						myArc = oppArc;
						oppArc = temp;
					}
					straightStack.push(oppArc);
					checkNode.createWay(myArc, zArc, zNode, distance, 1);
					distance++;
					preNode = checkNode;
					checkNode = tree[oppArc.desCity];
				}
				// end point
				if (checkNode.countArcs == 1) {
					myArc = checkNode.getFirstArc();
				}
				// >2 crossing node
				else {
					myArc = checkNode.findMyArc(preNode.city);
					zNode.createWay(zArc, myArc, checkNode, distance, 1);
					while (!straightStack.isEmpty()) {
						Node.Arc t = (Node.Arc) straightStack.pop();
						t.getMyNode().createWay(t, myArc, checkNode,
								distance - t.getMyNode().ways[0].dis, 1);// error
					}
					s.push(checkNode);
				}
				Node.Way w = checkNode.createWay(myArc, zArc, zNode, distance, 1);
				// 以后minDis可以由其他的变量表示
				checkNode.uptMinDis(distance + zNode.getMinDis(sysUpdateTime),
						w, 1);
			}
		}
	}

	// find the arc of node leading to dCity
	// inWay myNode->zNode; myArc->zArc
	// return zNode->zArc->?? 这条线路

	private void setWay(Node.Way oppWay) {

	}

	public int query(int city) {
		if (tree[city].countArcs > 2
				|| tree[city].getMinDis(sysUpdateTime) == 0)
			return tree[city].getMinDis(sysUpdateTime);
		// ordinary node
		else if (tree[city].isOld(sysUpdateTime)) {
			// update

			Node.Way w0 = tree[city].ways[0];
			Node.Way oldw = w0.getOppWay();
			Node.Way wz = oldw.getOutWay();

			int my_w0dis = w0.dis;
			int totalzDis = oldw.dis;
			while (wz != null && my_w0dis > totalzDis) {
				totalzDis += wz.dis;
				if (totalzDis > my_w0dis)
					break;
				oldw = wz;
				wz = oldw.getOutWay();
				if (wz == null)
					break;
				// wz = oldw.getOutWay();
			}
			// one du
			if (wz == null) {
				int uptime = getUpt(oldw.zNode, oldw.myArc.getOutArc());

				w0.setWay(w0.myArc, oldw.zArc.getOutArc(), oldw.zNode, my_w0dis
						- totalzDis, uptime);
			}
			// two du
			else {
				int upt0 = getUpt(wz.getMyNode(), wz.myArc);
				w0.setWay(w0.myArc, wz.myArc, wz.getMyNode(), (w0.dis
						- totalzDis + wz.dis), upt0);
				Node.Way w1 = tree[city].ways[1];
				int upt1 = getUpt(wz.zNode, wz.zArc);
				w1.setWay(w1.myArc, wz.zArc, wz.zNode, wz.dis - w0.dis, upt1);
			}
		}
		int min = tree[city].getMinDis(sysUpdateTime);
		tree[city].setUpdate(sysUpdateTime);
		return min;
	}

	private int getUpt(Node n, Node.Arc a) {
		return n.ways[a.indexWay].updateTimeWay;
	}

	public void update(int center) {
		if (tree[center].isCenter()) {
			return;
		}
		sysUpdateTime = center;
		tree[center].setCenter();
		// 通知最近的节点是必须的
		Stack s = new Stack();
		s.push(tree[center]);
		while (!s.isEmpty()) {
			Node sNode = (Node) s.pop();
			for (int i = 0; i < sNode.ways.length && sNode.ways[i] != null; i++) {
				Node dest = sNode.ways[i].zNode;
				if (dest.city == sNode.preNotifyCity) {
					continue;
				}
				Node.Arc zArc = sNode.ways[i].zArc;
				int tempdis = sNode.ways[i].dis
						+ sNode.getMinDis(sysUpdateTime);
				if (tempdis < dest.getMinDis(sysUpdateTime)) {
					// update dest
					dest.uptMinDis(tempdis, dest.ways[zArc.indexWay], center);
					s.push(dest);
					dest.preNotifyCity = sNode.city;
				}
			}
			sNode.preNotifyCity = -1;
		}

	}

	private void testZMap() {
		for (int i = 1; i <= numCities; i++) {
			testPrintNode(tree[i]);
		}
	}

	private void testQuery() {
		for (int i = 1; i <= numCities; i++) {
			System.out.println(i + ": " + query(i));
		}
	}

	private void testPrintNode(Node n) {
		for (int i = 0; i < n.ways.length; i++)
			if (n.ways[i] != null)
				System.out.println("c" + n.city + "-" + i + ": " + n.ways[i]
						+ " minDis:" + n.getMinDis(sysUpdateTime));
	}

	private void testPrintWays() {
		for (int j = 1; j <= numCities; j++) {
			Node n = tree[j];
			for (int i = 0; i < n.ways.length && n.ways[i] != null; i++)
				System.out.println(n.ways[i]);
		}
	}
}
