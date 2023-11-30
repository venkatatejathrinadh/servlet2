package controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Sample1 {
	public static void main(String[] args) {
		int [] arr= {10,20,30};
    	Object [] abb=copyToObjectArray(arr);
    	   List l2=  convert(abb);
    	   System.out.println(l2);
	}
	static List convert(Object[] arr)   {
		List   l1 =new ArrayList();
		for (int i = 0; i < arr.length; i++) {
			l1.add(arr[i]);
		}
		return l1;
	}
	static Object[] copyToObjectArray(int [] arr)  {
		Object[] abb=new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			abb[i]=arr[i];
		}
		return abb;
	}
	
}
