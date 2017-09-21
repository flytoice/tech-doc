package com.tianrun.finance.pro1.util;

import java.io.UnsupportedEncodingException;

public class CRC16 {
	public static String getCrc16_IBM1(String ctx) throws UnsupportedEncodingException{
		byte[] d = ctx.getBytes("utf-8");
		
		byte[] data = new byte[d.length+2];
		System.arraycopy(d, 0, data, 0, d.length);
		
		//寄存器,32位，实际需要的是低16位 2字节，初始全1 下面说的最高位，均指第16字节的最高位
		int wcrc = 0x0000;
		
		int dumy; //代表最高位
		
		for (int i=0;i<data.length;i++){
			for (int j = 0; j < 8; j++) {
				dumy = wcrc & 0x8000; //判断高位是否为1
				
				//最高位置0，左移1位，低位自动补0
				wcrc<<=1;
				wcrc&=0x0000ffff;
				//补位
				if (0x80 == (data[i] & 0x80)){ //判断高位，即补入的位 1
					wcrc |= 0x0001;
				} else { 
					//补入的位 0 在wcrc<<=1时自动先补0
				}
				
				if (dumy==0x8000){	//最高位为1
					wcrc ^= 0x8005;
				}
				data[i]<<=1;
			}
			
		}
		wcrc ^=0x0000;
		return Integer.toHexString(wcrc).toUpperCase(); 
	}
}
