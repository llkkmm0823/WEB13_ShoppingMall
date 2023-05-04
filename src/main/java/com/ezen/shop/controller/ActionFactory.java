package com.ezen.shop.controller;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.controller.action.IndexAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		
		
		
		
		
		
		
		
		
		return ac;
	}
}
