package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定拼图的最优解
     * 算法：<br>
     * 将起始节点放入一个open列表中<br>
     * 如果open列表为空，则搜索失败，问题无解；否则重复步骤：<br>
     * 1. 访问open列表中的第一个节点v，若v为目标节点，则搜索成功
     * 2. 从open列表中删除节点v，放入close列表中。
     * 3. 将所有与v邻接且未曾被访问的节点放入open列表中
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        this.setBeginJNode(bNode); // 初始状态
        this.setEndJNode(eNode); // 结束状态
        this.currentJNode=null;
        this.visitedList=new HashSet<>(1000); //close列表
        this.exploreList=new LinkedList<JigsawNode>(); //open列表
        this.searchedNodesNum=0; // 已访问节点数
        //Set<JigsawNode> record =new HashSet<>(1000);
        super.reset();

        final int MAX_NODE_NUM=29000;// 最大节点数,访问节点数大于29000个则认为搜索失败
        final int DIRS =4; //四个方向
        // 将起始节点放入exploreList中
        exploreList.add(this.beginJNode);
        //record.add(this.beginJNode);
        // 如果open为空，或者访问节点数大于MAX_NODE_NUM个，则搜索失败，问题无解;否则循环直到求解成功
        while(!exploreList.isEmpty() && this.searchedNodesNum<MAX_NODE_NUM){
            // 取出exploreList的第一个节点N，置为当前节点currentJNode
            this.currentJNode=exploreList.peek();
            // 若currentJNode为目标节点，则搜索成功，计算解路径，退出
            if(this.currentJNode.equals(eNode)){
                this.getPath(); //返回解决步骤
                break;
            }
            //从open列表移除节点
            this.exploreList.remove();
            //record.remove(this.currentJNode);
            //放入close列表
            this.visitedList.add(this.currentJNode);
            this.searchedNodesNum++;

            JigsawNode[] nextNodes=new JigsawNode[DIRS];//待探索的节点
            for(int i=0;i<DIRS;i++)
                nextNodes[i]=new JigsawNode(this.currentJNode);
            // 寻找所有与currentJNode邻接且未曾被发现的节点放入open列表中
            for(int i=0;i<DIRS;i++){
                if(nextNodes[i].move(i) && //这个方向可以移动
                        !this.visitedList.contains(nextNodes[i])){ //这个节点没探索过
                    exploreList.add(nextNodes[i]);
                    //record.add(nextNodes[i]);
                }
            }
        }
        //按demo打印结果
        System.out.println("Jigsaw BF Serch Result");
        System.out.println("Begin state:"+this.getBeginJNode().toString());
        System.out.println("End State:"+this.getEndJNode().toString());
        System.out.println("Solution Path:\n"+this.getSolutionPath());
        System.out.println("Total number of searched nodes:"+this.searchedNodesNum);
        System.out.println("Depth of the current node is:"+this.getCurrentJNode());

        return this.isCompleted(); //因为有节点数限制所以可能未完成
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)<br>
     * 采用的估价方法：<br>
     * 1.所有放错位的数码的个数<br>
     * 2.所有放错位的数码与正确位置的距离之和:本次我们采用欧几里得距离和曼哈顿距离<br>
     * 3.后续节点不正确的数码个数<br>
     * 通过调整几个估价方法的权重达到更优效果
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int suc = 0; // 后续节点不正确的数码个数
        int manHattan=0; // 曼哈顿距离
        int euclidean=0; // 欧拉距离
        int misplaced=0; //放错位置的数码个数

        int dimension = JigsawNode.getDimension(); //拼图维度
        //开始估值
        for (int index = 1; index < dimension * dimension; index++) {
            // 后续节点不正确的个数
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                suc++;
            }
            // 所有放错位的数码的个数
            if (jNode.getNodesState()[index]!=index){
                misplaced++;
            }
        }

        for(int i=1; i<=dimension*dimension; i++){
            if(jNode.getNodesState()[i]!=0){ //对每个非0节点
                for(int j=1; j<=dimension*dimension; j++){
                    //找到这个节点应该在的位置
                    if(jNode.getNodesState()[i]==this.getEndJNode().getNodesState()[j]){
                        int startX=(i-1)/dimension;
                        int startY=(i-1)%dimension;
                        int endX=(j-1)/dimension;
                        int endY=(j-1)%dimension;
                        //曼哈顿距离
                        manHattan+=Math.abs(startX-endX)+ Math.abs(startY-endY);
                        //欧拉距离
                        euclidean+=Math.pow(startX-endX,2)+Math.pow(startY-endY,2);
                        break;
                    }
                }
            }
        }
        // 综合权重进行估值
        jNode.setEstimatedValue(suc*8 +misplaced*1 + manHattan*4 + euclidean*2);
    }
}