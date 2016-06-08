public class HuffmanNode implements Comparable{
   private boolean isLeaf;
   private Character val;
   private HuffmanNode left;
   private HuffmanNode right;
   private int count;
      
   public HuffmanNode(int num, HuffmanNode l, HuffmanNode r){
      val = null;
      isLeaf = false;
      left = l;
      right = r;
      count = num;   
   }
   public HuffmanNode(Character v, int i){
   isLeaf = true;
   val = v;
   count = i;
   left = right = null;
   }
   public HuffmanNode(Character v,HuffmanNode l,HuffmanNode r){
      val = v;
      left = l;
      right = r;
      count = 0;
   }
   public HuffmanNode(HuffmanNode n){
      val = n.getValue();
      left = n.getLeft();
      right = n.getRight();
      count = 0;
   }
   public HuffmanNode(Character v){
      val = v;
      left = right = null;
      count = 0;
   }
   public HuffmanNode(){
      val = null;
      left = right = null;
      count = 0;
   }
   public boolean isLeaf(){
      if(left==null&&right==null){
         isLeaf = true;
      }else{
         isLeaf = false;
      }
      return isLeaf;
   }
   public HuffmanNode getRight(){
      return right;
   }
   public HuffmanNode getLeft(){
      return left;
   }
   public Character getValue(){
      return val;
   }
   public int getCount(){
      return count;
   }
   public void setValue(Character v){
      val = v;
   }
   public void setLeft(HuffmanNode l){
      left = l;
   }
   public void setRight(HuffmanNode r){
      right = r;
   }
   public String toString(){
      String s = "";
      s= val+" "+count;
      return s;
   }
   public int compareTo(Object o){
      HuffmanNode s = (HuffmanNode)o;
      if(count>s.getCount())
         return 1;
      else if(count==s.getCount())
         return 0;
      else{
         return -1;
      }
   }
   
}