#include<iostream>
#include<stdio.h>
#include <stack>
using namespace std;
#define INT_MAX       2147483647
typedef struct Arc{
	Arc* next;
	int d;
}Arc ;
typedef struct Node{
	Arc * fa;
	Arc * la;
	int city;
	int preCity = -1;
	int minDis = INT_MAX;
}Node ;
int ncity, nquery;
Node* G;
const int NO_PRE_NOTIFY_CITY = -1;

void insertArc(Node& c, int d){
	Arc* pa;
	pa = new Arc();
	pa->next = NULL;
	pa->d = d;
	if (c.fa == NULL){
		c.la = c.fa = pa;
		return;
	}
	c.la->next = pa;
	c.la = pa;
}
Node *input(){
	scanf_s("%d%d", &ncity, &nquery);
	Node* ns  = new Node[ncity + 1];//定义数组
	
	for (int i = 0; i < ncity + 1; i++){
		ns[i].city = i;
		ns[i].fa = ns[i].la = NULL;
		ns[i].minDis = INT_MAX;
	}
	for (int i = 0; i < ncity-1; i++){
		int a, b;
		scanf_s("%d%d", &a, &b);
		insertArc(ns[a], b);
		insertArc(ns[b], a);
	}
	return ns;
	
}
int search(int city){
	return G[city].minDis;
}
void testInput(){
	for (int i = 1; i <= ncity; i++){
		cout << (G)[i].city << "->";
		Arc *p = (G)[i].fa;
		while (p != NULL){
			cout << p->d << "->";
			p = p->next;
		}
		cout << endl;
	}
	//getchar(); getchar(); getchar();
}
void testResult(){
	static int c = 1;
	for (int i = 1; i <= ncity; i++){
		printf("%d: %d\n", i,search(i) );
	}
	printf("%d:\n\n", c++);
}
int update(int center){
	stack<Node*> s;
	G[center].minDis = 0;
	s.push(&G[center]);
	//使用s栈中所有元素更新其他节点
	while (!s.empty()){
		Node* u = s.top();
		s.pop();
		Arc* sArc = u->fa;
		int dis = 1;
		//更新u的邻居
		while (sArc != NULL){
			 //取出一个元素
			if (sArc->d != u->preCity){
				Node* dcity = &G[sArc->d];
				if (u->minDis + dis < dcity->minDis){
					//更新节点dcity，并将dcity作为污点(传播点)
					dcity->minDis = u->minDis + dis;
					dcity->preCity = u->city;
					s.push(dcity);
				}
			}
			sArc = sArc->next;
		}
		//u节点更新完毕，
		u->preCity = NO_PRE_NOTIFY_CITY;
	}
	return 1;
}
int main(){
	// TODO: Implement your program
	G = input();
	testInput();
	update(1);
	//testResult();
	update(3);
	//testResult();
	
	update(19);
	//testResult(); 
	update(12);
	update(5);
	update(14);
	update(10);
	testResult();

	getchar(); getchar();
	return 0;
}
