package com.ezen.shop.controller;

import com.ezen.shop.controller.action.Action;
import com.ezen.shop.controller.action.IndexAction;
import com.ezen.shop.controller.action.member.ContractAction;
import com.ezen.shop.controller.action.member.EditFormAction;
import com.ezen.shop.controller.action.member.FindZipNumAction;
import com.ezen.shop.controller.action.member.IdCheckFormAction;
import com.ezen.shop.controller.action.member.JoinAction;
import com.ezen.shop.controller.action.member.JoinFormAction;
import com.ezen.shop.controller.action.member.LoginAction;
import com.ezen.shop.controller.action.member.LoginFormAction;
import com.ezen.shop.controller.action.member.LogoutAction;
import com.ezen.shop.controller.action.member.MemberUpdateAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		else if( command.equals("loginForm") ) ac = new LoginFormAction();
		else if( command.equals("login") ) ac = new LoginAction();
		else if( command.equals("logout") ) ac = new LogoutAction();
		else if( command.equals("contract") ) ac = new ContractAction();
		else if( command.equals("joinForm") ) ac = new JoinFormAction();
		else if( command.equals("idCheckForm") ) ac = new IdCheckFormAction();
		else if( command.equals("findZipNum") ) ac = new FindZipNumAction();
		else if( command.equals("join") ) ac = new JoinAction();
		else if( command.equals("editForm") ) ac = new EditFormAction();
		else if( command.equals("memberUpdate") ) ac = new MemberUpdateAction();
		
		
		return ac;
	}
}
