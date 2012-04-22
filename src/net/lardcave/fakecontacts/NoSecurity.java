package net.lardcave.fakecontacts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.res.AssetFileDescriptor;
import android.os.MemoryFile;

public class NoSecurity {
	private static boolean initialised = false;
	private static Method m_MemoryFile_deactivate, m_AssetFileDescriptor_fromMemoryFile;
	
	private static void init(){
		if(!initialised) {
			try {
				m_MemoryFile_deactivate = MemoryFile.class.getDeclaredMethod("deactivate");
				m_AssetFileDescriptor_fromMemoryFile = AssetFileDescriptor.class.getDeclaredMethod("fromMemoryFile", MemoryFile.class);
			} catch (SecurityException e) {
				e.printStackTrace();
				return;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				return;
			}
			initialised = true;
		}
	}
	
	public static void MemoryFile_deactivate(MemoryFile f)
	{
		init();
		
		try {
			m_MemoryFile_deactivate.invoke(f);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static AssetFileDescriptor AssetFileDescriptor_fromMemoryFile(MemoryFile f)
	{
		init();
		
		try {
			return (AssetFileDescriptor) m_AssetFileDescriptor_fromMemoryFile.invoke(null,  f);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
