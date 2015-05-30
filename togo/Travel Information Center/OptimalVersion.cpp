#include<iostream>
#include<stdio.h>
#include <stack>
using namespace std;
#define INT_MAX       2147483647
typedef struct Arc{
	Arc* next;
	int d;
}Arc;
typedef struct Node{
	Arc * fa;
	Arc * la;
	int city;
	int preCity = -1;
	int minDis = INT_MAX;
}Node;
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
	Node* ns = new Node[ncity + 1];//��������

	for (int i = 0; i < ncity + 1; i++){
		ns[i].city = i;
		ns[i].fa = ns[i].la = NULL;
		ns[i].minDis = INT_MAX;
	}
	for (int i = 0; i < ncity - 1; i++){
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
		printf("%d: %d\n", i, search(i));
	}
	printf("%d:\n\n", c++);
}
void  update(int center){
	stack<Node*> s;
	G[center].minDis = 0;
	s.push(&G[center]);
	//ʹ��sջ������Ԫ�ظ��������ڵ�
	while (!s.empty()){
		Node* u = s.top();
		s.pop();
		Arc* sArc = u->fa;
		int dis = 1;
		//����u���ھ�
		while (sArc != NULL){
			//ȡ��һ��Ԫ��
			if (sArc->d != u->preCity){
				Node* dcity = &G[sArc->d];
				if (u->minDis + dis < dcity->minDis){
					//���½ڵ�dcity������dcity��Ϊ�۵�(������)
					dcity->minDis = u->minDis + dis;
					dcity->preCity = u->city;
					s.push(dcity);
				}
			}
			sArc = sArc->next;
		}
		//u�ڵ������ϣ�
		u->preCity = NO_PRE_NOTIFY_CITY;
	}
}
//�ȰѲ�ѯ�Ĵ���������
#define UPDATE 1
#define QUERY 2
void query(){
	update(1);
	int *qs = new int[nquery];
	int *ts = new int[nquery];
	for (int i = 0; i < nquery; i++){
		scanf_s("%d%d", &ts[i], &qs[i]);	
	}

	//�Ż�1������������0���� ͳһ���ú��ٸ���
	for (int i = 0; i < nquery-1; i++){
		
		if (ts[i] == QUERY){//��ѯ 
			printf("%d\n", search(qs[i]));
		}
		if (ts[i] == UPDATE){
			if (ts[i + 1] != UPDATE){
				update(qs[i]);
			}
			//������������00
			else{
				int startZero = i;
				//Ѱ��������0, ֱ��ts[i]!=0Ϊֹ
				while (i < nquery - 1 && ts[++i] == UPDATE);
				for (int j = startZero; j < i; j++){
					G[qs[j]].minDis = 0;
				}
				for (int j = startZero; j < i; j++){
					update(qs[j]);
				}
				i--;//������Ϻ�i--�����죬����0�Ľڵ�
			}
		}
	}
	if (ts[nquery-1]==QUERY)
		printf("%d\n", search(qs[nquery-1]));

}
int main(){
	// TODO: Implement your program
	G = input();
	query();
	//getchar(); getchar(); getchar();
	return 0;
}