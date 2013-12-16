package com.dev.uchesmsresponse;

public final class Receipient 
{
	String name,phone;
	
	Receipient(String n,String p)
	{
		name=n;
		phone=p;
	}
	
	public void setName(String n)
	{
		name=n;
	}
	public String getName()
	{
		return name;
	}
	public void setPhone(String n)
	{
		name=n;
	}
	public String getPhone()
	{
		return phone;
	}
}
