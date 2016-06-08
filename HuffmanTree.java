import java.util.*;
import java.io.*;

public class HuffmanTree{
   private HuffmanNode root;
   private Map<Character, String> eMap;
   
   public HuffmanTree(Scanner sc){
      eMap = null;
      root = null;
      while(sc.hasNextLine()){
         char value = ((char)Integer.parseInt(sc.nextLine()));
         String c = sc.nextLine();
         //System.out.println(value);
         root = add(root, value, c, 0);
      }
   }
   public HuffmanTree(Scanner file, MapHistogram hst){
      eMap = new HashMap<Character, String>();
      buildHistogram(file, hst);
      PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
      Object[]  ch = hst.getMap().keySet().toArray();
      for(Object o:ch){
         char c = (char)o;
         pq.add(new HuffmanNode(c, hst.getMap().get(c)));
      }
      pq.add(new HuffmanNode((char)256,1));
      //System.out.println(pq);
      while(pq.size()>1){
         HuffmanNode small1 = pq.poll();
         HuffmanNode small2 = pq.poll();
         HuffmanNode newhn = new HuffmanNode((small1.getCount()+small2.getCount()),small1,small2);
         pq.add(newhn);
      }
      root = pq.peek();
   }
   private void buildHistogram(Scanner f, MapHistogram h){
      while(f.hasNext()){
         String s = f.nextLine();
         char[] ca = s.toCharArray();
         for(char c:ca){
            h.add(c);
         }
      }
   }
   private HuffmanNode add(HuffmanNode node, char val, String code, int spot){
      char[] ca = code.toCharArray();
      if(node == null){
         node = new HuffmanNode();
      }
      if(spot>=ca.length){
         node = new HuffmanNode(val);
      }else{
         if(ca[spot] == '0'){
            node.setLeft(add(node.getLeft(), val, code, spot+1));
         }
         else if(ca[spot] =='1'){
            node.setRight(add(node.getRight(), val, code, spot+1));
         }
      }
      return node;   
   }
   public void printTree(){
      printTree(root);
   }
   private void printTree(HuffmanNode tree){
      if(tree != null)
		{

			printTree(tree.getLeft());
         if(tree.getValue()!=null){
			//System.out.print(tree.getValue() + " ");
         }
			printTree(tree.getRight());
		}

   }
   public void decode(BitInputStream in, PrintStream out){
      for(int x = 0;x<196197;x++){
         out.print(getOneChar(in));
      }
   }
   private char getOneChar(BitInputStream input){
      HuffmanNode temp = root;
      while(temp.getValue()==null){
         int i = input.readBit();
         if(i == 0){
            temp = temp.getLeft();
         }else{
            temp = temp.getRight();
         }
      }
      return temp.getValue();
   }
   public void generateCodeFile(PrintStream out){
      String s = "";
      generateCodeFile(root, s, out);
   }
   private void generateCodeFile(HuffmanNode tree, String s, PrintStream out){
   if(!tree.isLeaf())
		{
         s+='0';
			generateCodeFile(tree.getLeft(), s,out);
         s = s.substring(0,s.length()-1);
         s+='1';
         generateCodeFile(tree.getRight(),s,out);
		}else{
         out.print((int)tree.getValue()+"\r\n");
         out.print(s+"\r\n");
         eMap.put(tree.getValue(),s);
         s = " ";
         
      }

      
   }
   public void encode(Scanner s, String fileName){
      BitOutputStream bos = new BitOutputStream(fileName);
      while(s.hasNext()){
         String st = s.next();
         char[] ch = st.toCharArray();
         for(char c:ch){
            String pos = eMap.get(c);
            for(char cha:pos.toCharArray()){
               String str = Character.toString(cha);
               int i = Integer.parseInt(str);
               bos.writeBit(i);
            }
         }
         /*System.out.println("eMap: ");
         System.out.println(eMap);
         String finalpos = eMap.get('\r');
         for(char chh:finalpos.toCharArray()){
            String stri = Character.toString(chh);
            int in = Integer.parseInt(stri);
            bos.writeBit(in);
         }
         */
      }
      bos.close();
   }
}  