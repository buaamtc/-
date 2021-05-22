package work_five;

import work_two.worktwo;

public class SBOX {
	public static void main(String[] args) {
		  System.out.println("S盒：");
		s_box_gen();
		System.out.println();
		  System.out.println("逆S盒：");
		invs_box_gen();
	}
  public static char XOR(char a,char b)//字符异或
  {
	  char result = '1';
        	if(a==b)
        	{
        		result='0';
        	}
        	else if(a!=b)
        	{
        		result='1';
        	}
			return result;
    }
	public static void s_box_gen()//生成S盒
	{
	    int i,j;
	    int[][] s_box=new int[16][16];
	   int[][] s_box_ary=new int[16][16];
	   StringBuffer[][] s_boxhex=new StringBuffer[16][16];
	   StringBuffer[][] s_boxbinary=new StringBuffer[16][16];
	   StringBuffer[] tem=new StringBuffer[4];
		  StringBuffer tem1=new StringBuffer();
		  char[] tem2=new char[8];
		  char[] c= {'0','1','1','0','0','0','1','1'};
		  String s="";
	
	    for(i=0;i<16;i++)
	    {
	        for(j=0;j<16;j++)
	        {
	            s_box_ary[i][j] = ((i<<4)&0xF0) + (j&(0xF));//初始化S盒
	            tem1= new StringBuffer(Integer.toBinaryString( s_box_ary[i][j] ));
				  while(tem1.length()<8)
		      		{
						  tem1=new StringBuffer('0'+tem1.toString());
		      		}
				  s_boxbinary[i][j]=tem1;
				  s_boxhex[i][j]=AES.tohex(s_boxbinary[i][j]);
				  s_boxhex[i][j] =new StringBuffer(worktwo.inverse(s_boxhex[i][j].toString(),"100011011"));
				
					while(s_boxhex[i][j].length()<2)
	        		{
						s_boxhex[i][j]=new StringBuffer('0'+s_boxhex[i][j].toString());
	        		}
					
				  s_boxbinary[i][j]=AES.toBinaryString(s_boxhex[i][j]);
				  
				  for(int k=0;k<8;k++)
				  {
					 
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-k),s_boxbinary[i][j].charAt(7-((k+4)%8)));
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-((k+5)%8)),tem2[7-k]);
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-((k+6)%8)),tem2[7-k]);
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-((k+7)%8)),tem2[7-k]);
					  tem2[7-k]= XOR(tem2[7-k],c[7-k]);
					 
				  }
				
				  s=new String(tem2);
				
				  s_box[i][j]=Integer.parseInt(s,2);
				  System.out.print( Integer.toHexString(s_box[i][j])+"   ");
	        }
	        System.out.println();
	    }
	}
	public static void invs_box_gen()//生成逆的S盒
	{
	    int i,j;
	   int[][] s_box_ary=new int[16][16];
	   StringBuffer[][] s_boxhex=new StringBuffer[16][16];
	   StringBuffer[][] s_boxbinary=new StringBuffer[16][16];

		  StringBuffer tem1=new StringBuffer();
		  char[] tem2=new char[8];
		  char[] d= {'0','0','0','0','0','1','0','1'};
		  String s="";
	
	    for(i=0;i<16;i++)
	    {
	        for(j=0;j<16;j++)
	        {
	            s_box_ary[i][j] = ((i<<4)&0xF0) + (j&(0xF));//初始化逆S盒
	            tem1= new StringBuffer(Integer.toBinaryString( s_box_ary[i][j] ));
	            while(tem1.length()<8)
	      		{
					  tem1=new StringBuffer('0'+tem1.toString());
	      		}
				 s_boxbinary[i][j]=tem1;
				  for(int k=0;k<8;k++)
				  { 
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-(k+2)%8),s_boxbinary[i][j].charAt(7-((k+5)%8)));
					  tem2[7-k]= XOR(s_boxbinary[i][j].charAt(7-((k+7)%8)),tem2[7-k]);
					  tem2[7-k]= XOR(tem2[7-k],d[7-k]); 
				  }
				  s=new String(tem2);  
				  s_boxbinary[i][j]=new StringBuffer(s);
				  s_boxhex[i][j]=AES.tohex(s_boxbinary[i][j]);
				  s_boxhex[i][j] =new StringBuffer(worktwo.inverse(s_boxhex[i][j].toString(),"100011011"));
				  System.out.print( s_boxhex[i][j]+"   ");
	        }
	        System.out.println();
	    }
	}

}
