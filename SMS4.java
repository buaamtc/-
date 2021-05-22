package work_five;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import work_six.SM4;
import work_two.worktwo;

public class SMS4 {
	public static   StringBuffer iv=new StringBuffer("0123456789abcdeffedcba9876543210");
	public static   StringBuffer key=new StringBuffer("0123456789abcdeffedcba9876543210");

	public static int[] SBOX={0xd6,0x90,0xe9,0xfe,0xcc,0xe1,0x3d,0xb7,0x16,0xb6,0x14,0xc2,0x28,0xfb,0x2c,0x05,
            0x2b,0x67,0x9a,0x76,0x2a,0xbe,0x04,0xc3,0xaa,0x44,0x13,0x26,0x49,0x86,0x06,0x99,
            0x9c,0x42,0x50,0xf4,0x91,0xef,0x98,0x7a,0x33,0x54,0x0b,0x43,0xed,0xcf,0xac,0x62,
            0xe4,0xb3,0x1c,0xa9,0xc9,0x08,0xe8,0x95,0x80,0xdf,0x94,0xfa,0x75,0x8f,0x3f,0xa6,
            0x47,0x07,0xa7,0xfc,0xf3,0x73,0x17,0xba,0x83,0x59,0x3c,0x19,0xe6,0x85,0x4f,0xa8,
            0x68,0x6b,0x81,0xb2,0x71,0x64,0xda,0x8b,0xf8,0xeb,0x0f,0x4b,0x70,0x56,0x9d,0x35,
            0x1e,0x24,0x0e,0x5e,0x63,0x58,0xd1,0xa2,0x25,0x22,0x7c,0x3b,0x01,0x21,0x78,0x87,
           0xd4,0x00,0x46,0x57,0x9f,0xd3,0x27,0x52,0x4c,0x36,0x02,0xe7,0xa0,0xc4,0xc8,0x9e,
            0xea,0xbf,0x8a,0xd2,0x40,0xc7,0x38,0xb5,0xa3,0xf7,0xf2,0xce,0xf9,0x61,0x15,0xa1,
            0xe0,0xae,0x5d,0xa4,0x9b,0x34,0x1a,0x55,0xad,0x93,0x32,0x30,0xf5,0x8c,0xb1,0xe3,
            0x1d,0xf6,0xe2,0x2e,0x82,0x66,0xca,0x60,0xc0,0x29,0x23,0xab,0x0d,0x53,0x4e,0x6f,
            0xd5,0xdb,0x37,0x45,0xde,0xfd,0x8e,0x2f,0x03,0xff,0x6a,0x72,0x6d,0x6c,0x5b,0x51,
            0x8d,0x1b,0xaf,0x92,0xbb,0xdd,0xbc,0x7f,0x11,0xd9,0x5c,0x41,0x1f,0x10,0x5a,0xd8,
            0x0a,0xc1,0x31,0x88,0xa5,0xcd,0x7b,0xbd,0x2d,0x74,0xd0,0x12,0xb8,0xe5,0xb4,0xb0,
            0x89,0x69,0x97,0x4a,0x0c,0x96,0x77,0x7e,0x65,0xb9,0xf1,0x09,0xc5,0x6e,0xc6,0x84,
            0x18,0xf0,0x7d,0xec,0x3a,0xdc,0x4d,0x20,0x79,0xee,0x5f,0x3e,0xd7,0xcb,0x39,0x48};


	public static int[] CK = { 0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
		 0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9, 0xe0e7eef5,
		 0xfc030a11, 0x181f262d, 0x343b4249, 0x50575e65, 0x6c737a81,
		 0x888f969d, 0xa4abb2b9, 0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d,
		 0x141b2229, 0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
		 0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209, 0x10171e25,
		 0x2c333a41, 0x484f565d, 0x646b7279 };

	 public static StringBuffer XOR(StringBuffer a,StringBuffer b)//二进制异或
	    {
	    	 StringBuffer result = new StringBuffer();
	    	 
	    	 for (int i = 0; i < a.length(); i++)
	         {
	        	if(a.charAt(i)==b.charAt(i))
	        	{
	        		result.append('0');
	        	}
	        	else 
	        	{
	        		result.append('1');
	        	}
	         }
	    	 return result;
	    }
	public static  StringBuffer tohex( StringBuffer binary)//二进制转化为十六进制
    {
    	 StringBuffer result=new StringBuffer();
    	 char[] a=binary.toString().toCharArray();
    	 int i=0;
    	 while(i<a.length)
     	{
     	
     		int k=(int)(a[i]-'0')*8+(int)(a[i+1]-'0')*4+(int)(a[i+2]-'0')*2+(int)(a[i+3]-'0');
     		
     		result.append(Integer.toHexString(k));
     		i=i+4;
     		
     	}
    	 return result;
    	 
    }
	public static StringBuffer toBinaryString(StringBuffer key)//十六进制转化为二进制
	 {	
	        char[] a=key.toString().toCharArray();
	        StringBuffer keyBinary =new StringBuffer() ;
	        String tem="";
	        for(int i=0;i<a.length;i++)
	        {
	        	if(a[i]>='0'&&a[i]<='9')
	        	{
	        		tem=Integer.toBinaryString(key.charAt(i)-'0');
	        		while(tem.length()<4)
	        		{
	        			tem='0'+tem;
	        		}
	        		keyBinary=keyBinary.append(tem);
	        	}
	        	
	        	else if(a[i]>='a'&&a[i]<='f')
	        	{
	        		tem=Integer.toBinaryString(key.charAt(i)-'a'+10);
	        		while(tem.length()<4)
	        		{
	        			tem='0'+tem;
	        		}
	        		keyBinary=keyBinary.append(tem);
	        	}
	        }
	        return keyBinary;
	    }
	 
	 
	public static StringBuffer encrypt(StringBuffer plaintext,StringBuffer key,int decode)//实现加解密
	{
		StringBuffer[] x=new StringBuffer[36];
		x[0]=toBinaryString(new StringBuffer(plaintext.substring(0,8)));
		x[1]=toBinaryString(new  StringBuffer(plaintext.substring(8,16)));
		x[2]=toBinaryString(new StringBuffer(plaintext.substring(16,24)));
		x[3]=toBinaryString(new  StringBuffer(plaintext.substring(24,32)));
		StringBuffer[] roundkey=SMS4KeyExt(key);
		StringBuffer tem=new StringBuffer();
		if(decode==0)
		{
			for(int i=0;i<32;i++)
	        {
			
	        	tem=L(S(XOR(XOR(XOR(x[i+1],x[i+2]),x[i+3]),roundkey[i])));
	        	x[i+4]=XOR(x[i],tem);
	        
	        }
		}
		 if(decode==1)
		 {
			 for(int i=0;i<32;i++)
		        {
				
		        	tem=L(S(XOR(XOR(XOR(x[i+1],x[i+2]),x[i+3]),roundkey[31-i])));
		        	x[i+4]=XOR(x[i],tem);
		        
		        }
		 }
	       
		 StringBuffer result=new StringBuffer(x[35].toString()+x[34].toString()+x[33].toString()+x[32].toString());
		result=tohex(result);
		//System.out.println(result);
		 return result;
	
	}
	
	public static StringBuffer L1(StringBuffer B)
	{
		StringBuffer[] l=new StringBuffer[2];
		StringBuffer[] r=new StringBuffer[2];
		StringBuffer[] tem=new StringBuffer[2];
		StringBuffer result;
		l[0]=new StringBuffer(B.substring(13));
		r[0]=new StringBuffer(B.substring(0, 13));
		tem[0]=new StringBuffer(l[0].toString()+r[0].toString());
		l[1]=new StringBuffer(B.substring(23));
		r[1]=new StringBuffer(B.substring(0, 23));
		tem[1]=new StringBuffer(l[1].toString()+r[1].toString());
		result=XOR(XOR(B,tem[0]),tem[1]);
		return result;

	}
	public static StringBuffer L(StringBuffer B)
	{
		StringBuffer[] l=new StringBuffer[4];	
		StringBuffer[] tem=new StringBuffer[4];
		StringBuffer[] r=new StringBuffer[4];
		StringBuffer result;
		l[0]=new StringBuffer(B.substring(24));
		r[0]=new StringBuffer(B.substring(0,24));
		tem[0]=new StringBuffer(l[0].toString()+r[0].toString());
		
		l[1]=new StringBuffer(B.substring(18));
		r[1]=new StringBuffer(B.substring(0,18));
		tem[1]=new StringBuffer(l[1].toString()+r[1].toString());
		
		
	
		l[2]=new StringBuffer(B.substring(10));
		r[2]=new StringBuffer(B.substring(0,10));
		tem[2]=new StringBuffer(l[2].toString()+r[2].toString());
		
		l[3]=new StringBuffer(B.substring(2));
		r[3]=new StringBuffer(B.substring(0,2));
		tem[3]=new StringBuffer(l[3].toString()+r[3].toString());
		
		
		
		result=XOR(XOR(XOR(XOR(B,tem[0]),tem[1]),tem[2]),tem[3]);
		return result;

	}
	  public static StringBuffer S(StringBuffer a)//T盒
	  {
		  StringBuffer[] tem=new StringBuffer[4];
			tem[0]=new StringBuffer(a.substring(0,8));
			tem[1]=new  StringBuffer(a.substring(8,16));
			tem[2]=new StringBuffer(a.substring(16,24));
			tem[3]=new  StringBuffer(a.substring(24,32));
		  
		  StringBuffer tem1=new StringBuffer();
		  StringBuffer tem2=new StringBuffer();
		
		  int k1,k2;
		
		  int[] s=new int[4];
		  for(int i=0;i<4;i++)
		  {
			  k1=(int)(tem[i].charAt(0)-'0')*8+(int)(tem[i].charAt(1)-'0')*4+(int)(tem[i].charAt(2)-'0')*2+(int)(tem[i].charAt(3)-'0');
			  k2=(int)(tem[i].charAt(4)-'0')*8+(int)(tem[i].charAt(5)-'0')*4+(int)(tem[i].charAt(6)-'0')*2+(int)(tem[i].charAt(7)-'0');  
			  s[i]=SBOX[k1*16+k2];
			
			   
			  tem1= new StringBuffer(Integer.toBinaryString(s[i]));
			  while(tem1.length()<8)
	      		{
					  tem1=new StringBuffer('0'+tem1.toString());
	      		}
			  tem2.append(tem1); 
		  }
		  
		
		 
		  return tem2;
	  }
	public static StringBuffer[] SMS4KeyExt(StringBuffer key) 

	{
		StringBuffer[] mk=new StringBuffer[4];
		mk[0]=new StringBuffer(key.substring(0,8));
		mk[1]=new  StringBuffer(key.substring(8,16));
		mk[2]=new StringBuffer(key.substring(16,24));
		mk[3]=new  StringBuffer(key.substring(24,32));
		//System.out.println(mk[3]);
		StringBuffer[] rk=new StringBuffer[32];
		StringBuffer[] k=new StringBuffer[36];
		
        int fk[]= {0xa3b1bac6,0x56aa3350,0x677d9197,0xb27022dc};
        StringBuffer[] binaryck=new StringBuffer[32];
		StringBuffer[] binaryfk=new StringBuffer[4];
		for(int i=0;i<4;i++)
		{
			binaryfk[i]=new StringBuffer(Integer.toBinaryString((fk[i])));
			  while(binaryfk[i].length()<32)
    		{
				  binaryfk[i]=new StringBuffer('0'+binaryfk[i].toString());
    		}
			 // System.out.println( binaryfk[i]);
		}
		for(int i=0;i<32;i++)
		{
			binaryck[i]=new StringBuffer(Integer.toBinaryString((CK[i])));
			  while(binaryck[i].length()<32)
    		{
				  binaryck[i]=new StringBuffer('0'+binaryck[i].toString());
    		}
			  //System.out.println( binaryck[i]);
		}
		
        for(int i=0;i<4;i++)
        {
        	//System.out.println(binaryfk[i]);
        	k[i]=XOR(toBinaryString(mk[i]),binaryfk[i]);
        }
        StringBuffer tem=new StringBuffer();
        for(int i=0;i<32;i++)
        {
        	tem=XOR(XOR(XOR(k[i+1],k[i+2]),k[i+3]),binaryck[i]);
        	k[i+4]=XOR(k[i],L1(S(tem)));
        	rk[i]=k[i+4];
        	//System.out.println(tohex(k[i+4])+"  "+i);
        }
        
        return rk;

	}
	
	
	
	public static int ECB() throws IOException
	{
		
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		String in=new String();
		String out=new String();
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-ecb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ecb";
		int num=0;
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		do{
			i = fin.read();
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(new StringBuffer(tem),key,0);
					//System.out.println(tem2);
					for(int k=0;k<tem2.length()/2;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(tem2.substring(k*2, k*2+2))).toString(),2);
						//System.out.println(te);
						fout.write((char)te);
					}
					num++;
					tem=tem.substring(32);
				}
				//System.out.println(tem+"  "+tem.length());
			}
		}while(i != -1); // 读到文件的末尾返回值为-1
		
		
		int a=0;
		//System.out.println(tem.length());
		if(tem.length()!=32)
		{
			a=32-tem.length();
			for(int k=0;k<a;k++)
			{
				tem1=Integer.toHexString(a);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
			}
			
		}
//			System.out.println(tem);
//			System.out.println(encrypt(new StringBuffer(tem),key,decode));
		tem2=encrypt(new StringBuffer(tem),key,0);
		for(int k=0;k<tem2.length()/2;k++)
		{
			te=Integer.parseInt(toBinaryString(new StringBuffer(tem2.substring(k*2, k*2+2))).toString(),2);
			//System.out.println(te);
			fout.write((char)te);
		}
		fin.close();
		fout.close();
		return (32-a)+(num*32);
	}
	public static void DEECB(int num) throws IOException
	{
		
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		int count=0;
		boolean a=false;
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ecb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\解密cipher-ecb";
	
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		do{
			i = fin.read();
			count++;
			
			if(2*count==num)
			{
				a=true;
				
			}
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(new StringBuffer(tem),key,1);
					//System.out.println(tem2);
					int turn=tem2.length()/2;
					if(a==true)
					{
						turn=16-(count*2-num)/2;
						
					}
					for(int k=0;k<turn;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(tem2.substring(k*2, k*2+2))).toString(),2);
						
						//System.out.println(te);
						fout.write((char)te);
						
					}
				
					tem=tem.substring(32);
				}
				
			}
		
		}while(i != -1); // 读到文件的末尾返回值为-1

		fin.close();
		fout.close();
	
	
	}
	public static int CBC() throws IOException
	{
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
	
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cbc";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-cbc";
		int num=0;
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer(); 
		//fin = new FileInputStream("C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cbc"); // 文件名
		do{
			i = fin.read();
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					tem2=tohex(XOR(toBinaryString(iv),toBinaryString(new StringBuffer(tem))));
					result=encrypt(tem2,key,0);
					iv=result;
					for(int k=0;k<result.length()/2;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						//System.out.println(te);
						fout.write((char)te);
					}
					num++;
					tem=tem.substring(32);
				}
			}
		}while(i != -1); // 读到文件的末尾返回值为-1
		int a=0;
		if(tem.length()!=32)
		{
			a=32-tem.length();
			for(int k=0;k<a;k++)
			{
				tem1=Integer.toHexString(a);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
			}
		}
		tem2=tohex(XOR(toBinaryString(iv),toBinaryString(new StringBuffer(tem))));
		
		result=encrypt(tem2,key,0);
	
		for(int k=0;k<result.length()/2;k++)
		{
			te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
			fout.write((char)te);
		}
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
		return (32-a)+(num*32);
	}
	public static void DECBC(int num) throws IOException
	{
		
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		int count=0;
		boolean a=false;
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-cbc";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\解密cipher-cbc";
	
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer();
		do{
			i = fin.read();
			count++;
			
			if(2*count==num)
			{
				a=true;	
			}
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(new StringBuffer(tem),key,1);
					result=tohex(XOR(toBinaryString(iv),toBinaryString(tem2)));
					int turn=result.length()/2;
					if(a==true)
					{
						turn=16-(count*2-num)/2;
					}
					for(int k=0;k<turn;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						
						//System.out.println(te);
						fout.write((char)te);
						
					}
				    iv=new StringBuffer(tem);
					tem=tem.substring(32);
				}
				
			}
		
		}while(i != -1); // 读到文件的末尾返回值为-1
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
	
	
	}
	public static int CTR() throws IOException
	{
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
	
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-ctr";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ctr";
		int num=0;
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer(); 
		//fin = new FileInputStream("C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cbc"); // 文件名
		do{
			i = fin.read();
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					tem2=encrypt(iv,key,0);
					
					result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));
					for(int k=0;k<result.length()/2;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						//System.out.println(te);
						fout.write((char)te);
					}
					num++;
					tem=tem.substring(32);
				}
			}
		}while(i != -1); // 读到文件的末尾返回值为-1
		int a=0;
		if(tem.length()!=32)
		{
			a=32-tem.length();
			for(int k=0;k<a;k++)
			{
				tem1=Integer.toHexString(a);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
			}
		}
		tem2=encrypt(iv,key,0);
		result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));	
		for(int k=0;k<result.length()/2;k++)
		{
			te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
			fout.write((char)te);
		}
	
		fin.close();
		fout.close();
		return (32-a)+(num*32);
	}
	public static void DECTR(int num) throws IOException
	{
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		int count=0;
		boolean a=false;
		String in=new String();
		String out=new String();	
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ctr";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\解密cipher-ctr";
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer();
		do{
			i = fin.read();
			count++;
			
			if(2*count==num)
			{
				a=true;	
			}
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(iv,key,0);
					result=tohex(XOR(toBinaryString(new StringBuffer(tem)),toBinaryString(tem2)));
					int turn=result.length()/2;
					if(a==true)
					{
						turn=16-(count*2-num)/2;
					}
					for(int k=0;k<turn;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						fout.write((char)te);
					}
				   // iv=new StringBuffer(tem);
					tem=tem.substring(32);
				}
				
			}
		
		}while(i != -1); // 读到文件的末尾返回值为-1
		
		fin.close();
		fout.close();
	}
	
	public static int CFB() throws IOException
	{
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
	
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cfb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-cfb";
		int num=0;
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer(); 
		//fin = new FileInputStream("C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cbc"); // 文件名
		do{
			i = fin.read();
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					tem2=encrypt(iv,key,0);
					result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));
					iv=result;
					for(int k=0;k<result.length()/2;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						//System.out.println(te);
						fout.write((char)te);
					}
					num++;
					tem=tem.substring(32);
				}
			}
		}while(i != -1); // 读到文件的末尾返回值为-1
		int a=0;
		if(tem.length()!=32)
		{
			a=32-tem.length();
			for(int k=0;k<a;k++)
			{
				tem1=Integer.toHexString(a);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
			}
		}
		tem2=encrypt(iv,key,0);
		result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));
		
	
	
		for(int k=0;k<result.length()/2;k++)
		{
			te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
			fout.write((char)te);
		}
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
		return (32-a)+(num*32);
	}
	public static void DECFB(int num) throws IOException
	{
		
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		int count=0;
		boolean a=false;
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-cfb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\解密cipher-cfb";
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer();
		do{
			i = fin.read();
			count++;
			
			if(2*count==num)
			{
				a=true;	
			}
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(iv,key,0);
					result=tohex(XOR(toBinaryString(new StringBuffer(tem)),toBinaryString(tem2)));
					int turn=result.length()/2;
					if(a==true)
					{
						turn=16-(count*2-num)/2;
					}
					for(int k=0;k<turn;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						
						//System.out.println(te);
						fout.write((char)te);
						
					}
				    iv=new StringBuffer(tem);
					tem=tem.substring(32);
				}
				
			}
		
		}while(i != -1); // 读到文件的末尾返回值为-1
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
	}
	public static int OFB() throws IOException
	{
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
	
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-ofb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ofb";
		int num=0;
		
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer(); 
		//fin = new FileInputStream("C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\cipher-cbc"); // 文件名
		do{
			i = fin.read();
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					tem2=encrypt(iv,key,0);
					iv=tem2;
					result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));
					for(int k=0;k<result.length()/2;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						fout.write((char)te);
					}
					num++;
					tem=tem.substring(32);
				}
			}
		}while(i != -1); // 读到文件的末尾返回值为-1
		int a=0;
		if(tem.length()!=32)
		{
			a=32-tem.length();
			for(int k=0;k<a;k++)
			{
				tem1=Integer.toHexString(a);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
			}
		}
		tem2=encrypt(iv,key,0);
		result=tohex(XOR(toBinaryString(tem2),toBinaryString(new StringBuffer(tem))));
		
	
	
		for(int k=0;k<result.length()/2;k++)
		{
			te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
			fout.write((char)te);
		}
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
		return (32-a)+(num*32);
	}
	public static void DEOFB(int num) throws IOException
	{
		
		int i;
		FileInputStream fin; // 注意：该类不能处理中文编码
		String tem="";
		String tem1="";
		StringBuffer tem2=new StringBuffer();
		int j=0;
		int te;
		int count=0;
		boolean a=false;
		String in=new String();
		String out=new String();
		
		in="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\加密cipher-ofb";
		out="C:\\Users\\86151\\Desktop\\第六次实验\\附件二：SM4工作模式测试向量\\解密cipher-ofb";
		fin = new FileInputStream(in); // 文件名
		FileOutputStream fout;
		fout = new FileOutputStream(out); // 文件名
		StringBuffer result=new StringBuffer();
		do{
			i = fin.read();
			count++;
			
			if(2*count==num)
			{
				a=true;	
			}
			//System.out.println(i);
			if( i != -1){
				tem1=Integer.toHexString(i);
				while(tem1.length()<2)
				{
					tem1="0"+tem1;
				}
				tem=tem+tem1;
				//System.out.println(tem);
				if(tem.length()%32==0)
				{
					//System.out.println(encrypt(new StringBuffer(tem),key,decode));
					tem2=encrypt(iv,key,0);
					iv=tem2;
					result=tohex(XOR(toBinaryString(new StringBuffer(tem)),toBinaryString(tem2)));
					int turn=result.length()/2;
					if(a==true)
					{
						turn=16-(count*2-num)/2;
					}
					for(int k=0;k<turn;k++)
					{
						te=Integer.parseInt(toBinaryString(new StringBuffer(result.substring(k*2, k*2+2))).toString(),2);
						
						//System.out.println(te);
						fout.write((char)te);
						
					}
				   
					tem=tem.substring(32);
				}
				
			}
		
		}while(i != -1); // 读到文件的末尾返回值为-1
		iv=new StringBuffer("0123456789abcdeffedcba9876543210");
		fin.close();
		fout.close();
	
	
	}
	public static void main(String[] args) throws IOException {
			
		int[] num=new int[5];
		 num[0]=ECB();
			System.out.println(num[0]);
			DEECB(num[0]);
			
		 num[1]=CBC();
			System.out.println(num[1]);
			DECBC(num[1]);
			
		 num[2]=CTR();
			System.out.println(num[2]);
			DECTR(num[2]);
				
		 num[3]=CFB();
			System.out.println(num[3]);
			DECFB(num[3]);
			
		 num[4]=OFB();
		System.out.println(num[4]);
		DEOFB(num[4]);
			//System.out.println((char)40);
		}
	
	
}
