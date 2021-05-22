package work_five;

import work_three.work_three;
import work_two.multiply;

public class AES {
	public static int[] sbox = {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01,
			0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0,
			0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F,
			0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04, 0xC7, 0x23, 0xC3,
			0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 0x09, 0x83,
			0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
			0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C,
			0x58, 0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F,
			0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6,
			0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2, 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17,
			0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A,
			0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 0xE0, 0x32, 0x3A, 0x0A,
			0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79, 0xE7, 0xC8,
			0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
			0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD,
			0x8B, 0x8A, 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9,
			0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E,
			0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68,
			0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16};
	public static String[] rcon = {"01000000", "02000000", "04000000", "08000000", "10000000",
			"20000000", "40000000", "80000000", "1b000000", "36000000"};
	public static int[] isbox = {0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5,0x38, 0xBF, 0x40,
			0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB, 0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87,
			0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB, 0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2,
			0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 0x08, 0x2E, 0xA1, 0x66,
			0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25, 0x72, 0xF8,
			0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92,
			0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D,
			0x9D, 0x84, 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05,
			0xB8, 0xB3, 0x45, 0x06, 0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF,
			0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B, 0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA,
			0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73, 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD,
			0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E, 0x47, 0xF1, 0x1A, 0x71,
			0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B, 0xFC, 0x56,
			0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4,
			0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80,
			0xEC, 0x5F, 0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F,
			0x93, 0xC9, 0x9C, 0xEF, 0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB,
			0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61, 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26,
			0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D};
	public static int[][] galois = {{0x02, 0x03, 0x01, 0x01},
	        {0x01, 0x02, 0x03, 0x01},
	        {0x01, 0x01, 0x02, 0x03},
	        {0x03, 0x01, 0x01, 0x02}};
	    
	 public static  int[][] invgalois = {{0x0e, 0x0b, 0x0d, 0x09},
	        {0x09, 0x0e, 0x0b, 0x0d},
	        {0x0d, 0x09, 0x0e, 0x0b},
	        {0x0b, 0x0d, 0x09, 0x0e}};


	public static void main(String[] args) 
	{

		encrypt(new StringBuffer("1b5e8b0f1bc78d238064826704830cdb"),new StringBuffer("3475bd76fa040b73f521ffcd9de93f24"),10,0);
		System.out.println();
	
		encrypt(new StringBuffer("fba4ec67020f1573ed28b47d7286d298"),new StringBuffer("2b24424b9fed596659842a4d0b007c61"),10,1);
		System.out.println();
	}
	public static StringBuffer[][] encrypt(StringBuffer plaintext,StringBuffer roundkey,int n,int decode)//实现加解密
	{
		StringBuffer[][] text = new StringBuffer[4][4];
        StringBuffer[] K=new StringBuffer[4];
        for(int i=0;i<4;i++)
        {
        	K[0]=new StringBuffer( plaintext.substring(i*8, i*8+2));
        	text[0][i]=K[0];
        	K[1]=new StringBuffer( plaintext.substring(i*8+2, i*8+4));
        	text[1][i]=K[1];
        	K[2]=new StringBuffer( plaintext.substring(i*8+4,i*8+6));
        	text[2][i]=K[2];
        	K[3]=new StringBuffer( plaintext.substring(i*8+6, i*8+8));
        	text[3][i]=K[3];
        }
        StringBuffer[][] binarytext = new StringBuffer[4][4];
        for(int i=0;i<4;i++)
        {
        	binarytext[i][0]=toBinaryString(text[i][0]);
        	binarytext[i][1]=toBinaryString(text[i][1]);
        	binarytext[i][2]=toBinaryString(text[i][2]);
        	binarytext[i][3]=toBinaryString(text[i][3]);
        }       
        StringBuffer[][] extendkey = new StringBuffer[4][4*n+4];
        extendkey =getSubKey( roundkey,n);
        StringBuffer[][] tem= new StringBuffer[4][4];
        
        StringBuffer[][] afterbytesub= new StringBuffer[4][4];
        StringBuffer[][] aftershiftrow= new StringBuffer[4][4];
        StringBuffer[][] aftermix= new StringBuffer[4][4];
        StringBuffer[][] result= new StringBuffer[4][4];
        if(decode==0)//加密
        {
        	tem=addkey(binarytext,extendkey,0);
        	  for(int i=1;i<n;i++)
              {
              	afterbytesub=bytesub(tem);
              	aftershiftrow=shiftrow(afterbytesub);
              	aftermix=mix(aftershiftrow);
              	tem=addkey(aftermix,extendkey,i);
              }
              afterbytesub=bytesub(tem);
              aftershiftrow=shiftrow(afterbytesub);
              result=addkey(aftershiftrow,extendkey,n);
        } 
      if(decode==1)//解密
      {
    	  tem=addkey(binarytext,extendkey,n);
    	  StringBuffer[][] afteraddkey= new StringBuffer[4][4];
    	 for(int i=n-1;i>=1;i--)
          {
    		aftershiftrow=invshiftrow(tem);
          	afterbytesub=invbytesub(aftershiftrow);
        	afteraddkey=addkey(afterbytesub,extendkey,i);
          	tem=invmix(afteraddkey);
          
          }
    	  aftershiftrow=invshiftrow(tem);
          afterbytesub=invbytesub(aftershiftrow);  
          result=addkey(afterbytesub,extendkey,0);
      }
        for(int i=0;i<4;i++)
        {
        	System.out.print(tohex(result[0][i])+""+tohex(result[1][i])+""+tohex(result[2][i])+""+tohex(result[3][i]));	
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
	  public static StringBuffer[] T(StringBuffer[] a,int j)//T盒
	  {
		  StringBuffer[] tem=new StringBuffer[4];
		  StringBuffer tem1=new StringBuffer();
		  StringBuffer tem2=new StringBuffer();
		  for(int i=0;i<3;i++)
		  {
			  tem[i]=a[i+1];
		  }
		  int k1,k2;
		  tem[3]=a[0];
		  int[] s=new int[4];
		  for(int i=0;i<4;i++)
		  {
			  k1=(int)(tem[i].charAt(0)-'0')*8+(int)(tem[i].charAt(1)-'0')*4+(int)(tem[i].charAt(2)-'0')*2+(int)(tem[i].charAt(3)-'0');
			  k2=(int)(tem[i].charAt(4)-'0')*8+(int)(tem[i].charAt(5)-'0')*4+(int)(tem[i].charAt(6)-'0')*2+(int)(tem[i].charAt(7)-'0');  
			  s[i]=sbox[k1*16+k2];
			
			   
			  tem1= new StringBuffer(Integer.toBinaryString(s[i]));
			  while(tem1.length()<8)
	      		{
					  tem1=new StringBuffer('0'+tem1.toString());
	      		}
			  tem2.append(tem1); 
		  }
		  StringBuffer result=new StringBuffer();
		
		  result=XOR(tem2,toBinaryString(new StringBuffer(rcon[j])));
		  StringBuffer[] K=new StringBuffer[4];
	      K[0]=new StringBuffer(result.substring(0,8));
          K[1]=new StringBuffer(result.substring(8, 16));
          K[2]=new StringBuffer(result.substring(16,24));
          K[3]=new StringBuffer(result.substring(24, 32));
		  return K;
	  }
	public static  StringBuffer[][] getSubKey(StringBuffer key,int n)//扩展密钥
	{
		StringBuffer[][] subkey = new StringBuffer[4][n-6];
        StringBuffer[] K=new StringBuffer[n-6];
        for(int i=0;i<n-6;i++)
        {
        	K[0]=new StringBuffer(key.substring(i*8, i*8+2));
        	subkey[0][i]=K[0];
        	K[1]=new StringBuffer(key.substring(i*8+2, i*8+4));
        	subkey[1][i]=K[1];
        	K[2]=new StringBuffer(key.substring(i*8+4,i*8+6));
        	subkey[2][i]=K[2];
        	K[3]=new StringBuffer(key.substring(i*8+6, i*8+8));
        	subkey[3][i]=K[3];
        }
        StringBuffer[][] binarykey = new StringBuffer[4][n-6];
        for(int i=0;i<n-6;i++)
        {
        	binarykey[0][i]=toBinaryString(subkey[0][i]);
        	binarykey[1][i]=toBinaryString(subkey[1][i]);
        	binarykey[2][i]=toBinaryString(subkey[2][i]);
        	binarykey[3][i]=toBinaryString(subkey[3][i]);
        }
        StringBuffer[][] extendkey = new StringBuffer[4][4*n+4];
    	for(int i=0;i<n-6;i++)
    	{
    		extendkey[0][i]=binarykey[0][i];
    		extendkey[1][i]= binarykey[1][i];
    		extendkey[2][i]= binarykey[2][i];
    		extendkey[3][i]= binarykey[3][i];
    	}
        for(int i=n-6;i<4*n+4;i++)
        {
        
        	if(i%(n-6)!=0)
        	{
        		extendkey[0][i]= XOR(extendkey[0][i-(n-6)],extendkey[0][i-1]);	 
        		extendkey[1][i]= XOR(extendkey[1][i-(n-6)],extendkey[1][i-1]);
        		extendkey[2][i]= XOR(extendkey[2][i-(n-6)],extendkey[2][i-1]);	 
        		extendkey[3][i]= XOR(extendkey[3][i-(n-6)],extendkey[3][i-1]);
        	}
        	if(i%(n-6)==0)
        	{
        		 StringBuffer[] tem=new StringBuffer[4];
        		 tem[0]=extendkey[0][i-1];
        		 tem[1]=extendkey[1][i-1];
        		 tem[2]=extendkey[2][i-1];
        		 tem[3]=extendkey[3][i-1];
        		 tem=T(tem,i/(n-6)-1);
        		
        		extendkey[0][i]= XOR(extendkey[0][i-(n-6)],tem[0]);
        		extendkey[1][i]= XOR(extendkey[1][i-(n-6)],tem[1]);	
        		extendkey[2][i]= XOR(extendkey[2][i-(n-6)],tem[2]);
        		extendkey[3][i]= XOR(extendkey[3][i-(n-6)],tem[3]);

        	}
        }
       
        return extendkey;

	}
		
	public static StringBuffer[][] invmix(StringBuffer[][] aftershiftrow)//逆的列混淆
	{
		StringBuffer[][] tem=new StringBuffer[4][4];
		
		for(int i=0;i<4;i++)
		{
			tem[i][0]=new StringBuffer(Integer.toBinaryString((invgalois[i][0])));
			  while(tem[i][0].length()<8)
      		{
				  tem[i][0]=new StringBuffer('0'+tem[i][0].toString());
      		}
			  tem[i][1]=new StringBuffer(Integer.toBinaryString((invgalois[i][1])));
			  while(tem[i][1].length()<8)
      		{
				  tem[i][1]=new StringBuffer('0'+tem[i][1].toString());
      		}
			  tem[i][2]=new StringBuffer(Integer.toBinaryString((invgalois[i][2])));
			  while(tem[i][2].length()<8)
      		{
				  tem[i][2]=new StringBuffer('0'+tem[i][2].toString());
      		}
			  tem[i][3]=new StringBuffer(Integer.toBinaryString((invgalois[i][3])));
			  while(tem[i][3].length()<8)
      		{
				  tem[i][3]=new StringBuffer('0'+tem[i][3].toString());
      		}
		}
		StringBuffer[][] aftermix=new StringBuffer[4][4];
		StringBuffer[] tem1=new StringBuffer[4];
		for(int j=0;j<4;j++)
		{
			for(int i=0;i<4;i++)
			{
				multiply a=new multiply();
				tem1[0]=new StringBuffer(a.multiply(tohex(tem[i][0]).toString(),tohex(aftershiftrow[0][j]).toString(),"100011011"));
				tem1[1]=new StringBuffer(a.multiply(tohex(tem[i][1]).toString(),tohex(aftershiftrow[1][j]).toString(),"100011011"));
				tem1[2]=new StringBuffer(a.multiply(tohex(tem[i][2]).toString(),tohex(aftershiftrow[2][j]).toString(),"100011011"));
				tem1[3]=new StringBuffer(a.multiply(tohex(tem[i][3]).toString(),tohex(aftershiftrow[3][j]).toString(),"100011011"));
				aftermix[i][j]=XOR(XOR(XOR(toBinaryString(tem1[0]),toBinaryString(tem1[1])),toBinaryString(tem1[2])),toBinaryString(tem1[3]));
			}
		}
		
		return aftermix;
	}
	public static StringBuffer[][] invshiftrow(StringBuffer[][] afterbytesub)//逆的行变换
	{
		int[] con= {0,3,2,1};
		StringBuffer tem=new StringBuffer();
		StringBuffer[][] aftershiftrow=new StringBuffer[4][4];
		for(int i=0;i<4;i++)
    	{
    		aftershiftrow[i][0]=afterbytesub[i][0];
    		aftershiftrow[i][1]= afterbytesub[i][1];
    		aftershiftrow[i][2]= afterbytesub[i][2];
    		aftershiftrow[i][3]= afterbytesub[i][3];
    	
    	}
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<con[i];j++)
			{
				tem=aftershiftrow[i][0];
				aftershiftrow[i][0]=aftershiftrow[i][1];
				aftershiftrow[i][1]=aftershiftrow[i][2];
				aftershiftrow[i][2]=aftershiftrow[i][3];
				aftershiftrow[i][3]=tem;
			}
		}
		return aftershiftrow;
		
	}
	public static StringBuffer[][] invbytesub( StringBuffer[][] state)//逆的字节代换
	{
		  int k1,k2;
		  StringBuffer[][] afterbytesub=new StringBuffer[4][4];
		  int[][] s=new int[4][4];
		  for(int i=0;i<4;i++)
		  {
			  for(int j=0;j<4;j++)
			  {
				  k1=(int)(state[i][j].charAt(0)-'0')*8+(int)(state[i][j].charAt(1)-'0')*4+(int)(state[i][j].charAt(2)-'0')*2+(int)(state[i][j].charAt(3)-'0');
				  k2=(int)(state[i][j].charAt(4)-'0')*8+(int)(state[i][j].charAt(5)-'0')*4+(int)(state[i][j].charAt(6)-'0')*2+(int)(state[i][j].charAt(7)-'0');  
				  s[i][j]=isbox[k1*16+k2];
				
				  afterbytesub[i][j]=new StringBuffer(Integer.toBinaryString(s[i][j]));//不能直接转化为二进制
				  while(afterbytesub[i][j].length()<8)
	        		{
					  afterbytesub[i][j]=new StringBuffer('0'+afterbytesub[i][j].toString());
	        		}
			  }
		  }
		  return afterbytesub;
	}
	
	public static StringBuffer[][] mix(StringBuffer[][] aftershiftrow)//列混淆
	{
		StringBuffer[][] tem=new StringBuffer[4][4];
		
		for(int i=0;i<4;i++)
		{
			tem[i][0]=new StringBuffer(Integer.toBinaryString((galois[i][0])));
			  while(tem[i][0].length()<8)
      		{
				  tem[i][0]=new StringBuffer('0'+tem[i][0].toString());
      		}
			  tem[i][1]=new StringBuffer(Integer.toBinaryString((galois[i][1])));
			  while(tem[i][1].length()<8)
      		{
				  tem[i][1]=new StringBuffer('0'+tem[i][1].toString());
      		}
			  tem[i][2]=new StringBuffer(Integer.toBinaryString((galois[i][2])));
			  while(tem[i][2].length()<8)
      		{
				  tem[i][2]=new StringBuffer('0'+tem[i][2].toString());
      		}
			  tem[i][3]=new StringBuffer(Integer.toBinaryString((galois[i][3])));
			  while(tem[i][3].length()<8)
      		{
				  tem[i][3]=new StringBuffer('0'+tem[i][3].toString());
      		}
		}
		StringBuffer[][] aftermix=new StringBuffer[4][4];
		StringBuffer[] tem1=new StringBuffer[4];
		for(int j=0;j<4;j++)
		{
			for(int i=0;i<4;i++)
			{
				multiply a=new multiply();
				tem1[0]=new StringBuffer(a.multiply(tohex(tem[i][0]).toString(),tohex(aftershiftrow[0][j]).toString(),"100011011"));
				tem1[1]=new StringBuffer(a.multiply(tohex(tem[i][1]).toString(),tohex(aftershiftrow[1][j]).toString(),"100011011"));
				tem1[2]=new StringBuffer(a.multiply(tohex(tem[i][2]).toString(),tohex(aftershiftrow[2][j]).toString(),"100011011"));
				tem1[3]=new StringBuffer(a.multiply(tohex(tem[i][3]).toString(),tohex(aftershiftrow[3][j]).toString(),"100011011"));
				aftermix[i][j]=XOR(XOR(XOR(toBinaryString(tem1[0]),toBinaryString(tem1[1])),toBinaryString(tem1[2])),toBinaryString(tem1[3]));
			}
		}
		
		return aftermix;
	}
	public static StringBuffer[][] shiftrow(StringBuffer[][] afterbytesub)//行变换
	{
		int[] con= {0,1,2,3};
		StringBuffer tem=new StringBuffer();
		StringBuffer[][] aftershiftrow=new StringBuffer[4][4];
		for(int i=0;i<4;i++)
    	{
    		aftershiftrow[i][0]=afterbytesub[i][0];
    		aftershiftrow[i][1]= afterbytesub[i][1];
    		aftershiftrow[i][2]= afterbytesub[i][2];
    		aftershiftrow[i][3]= afterbytesub[i][3];
    	
    	}
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<con[i];j++)
			{
				tem=aftershiftrow[i][0];
				aftershiftrow[i][0]=aftershiftrow[i][1];
				aftershiftrow[i][1]=aftershiftrow[i][2];
				aftershiftrow[i][2]=aftershiftrow[i][3];
				aftershiftrow[i][3]=tem;
			}
		}
		return aftershiftrow;
		
	}
	public static StringBuffer[][] bytesub( StringBuffer[][] state)//字节代换
	{
		  int k1,k2;
		  StringBuffer[][] afterbytesub=new StringBuffer[4][4];
		  int[][] s=new int[4][4];
		  for(int i=0;i<4;i++)
		  {
			  for(int j=0;j<4;j++)
			  {
				  k1=(int)(state[i][j].charAt(0)-'0')*8+(int)(state[i][j].charAt(1)-'0')*4+(int)(state[i][j].charAt(2)-'0')*2+(int)(state[i][j].charAt(3)-'0');
				  k2=(int)(state[i][j].charAt(4)-'0')*8+(int)(state[i][j].charAt(5)-'0')*4+(int)(state[i][j].charAt(6)-'0')*2+(int)(state[i][j].charAt(7)-'0');  
				  s[i][j]=sbox[k1*16+k2];
				
				  afterbytesub[i][j]=new StringBuffer(Integer.toBinaryString(s[i][j]));//不能直接转化为二进制
				  while(afterbytesub[i][j].length()<8)
	        		{
					  afterbytesub[i][j]=new StringBuffer('0'+afterbytesub[i][j].toString());
	        		}
			  }
		  }
		  return afterbytesub;
	}
	public static StringBuffer[][] addkey(StringBuffer[][] aftermixcolumn,StringBuffer[][] roundkey,int round)//轮密钥加
	{
		StringBuffer[][] state =new StringBuffer[4][4] ;
		int tem=4*round;
		for(int i=0;i<4;i++)
		{
			state[0][i]= XOR(aftermixcolumn[0][i], roundkey[0][tem]);
			state[1][i]= XOR(aftermixcolumn[1][i], roundkey[1][tem]);
			state[2][i]= XOR(aftermixcolumn[2][i], roundkey[2][tem]);
			state[3][i]= XOR(aftermixcolumn[3][i], roundkey[3][tem]);
			tem++;
		}
	
		return state;
	}

}
