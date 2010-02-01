/*
Date Created - 1/31/2010
Author - Arun Shanmugam Kumar 
blog - http://technodump-arun.blogspot.com/
*/

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.richfaces.json.JSONException;
import org.richfaces.json.JSONWriter;

import static java.lang.System.out;

public class ExtJSRichfaces {

	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	//This String should store a JSON serialized object
	private String jsonData;
	
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private Account selectedAccount;
	
	public Account getSelectedAccount() {
		return selectedAccount;
	}

	public void setSelectedAccount(Account selectedAccount) {
		this.selectedAccount = selectedAccount;
	}

	public String selAccountNumber = "";
	
	public String getSelAccountNumber() {
		return selAccountNumber;
	}

	public void setSelAccountNumber(String selAccountNumber) {
		this.selAccountNumber = selAccountNumber;
	}

	public String selAccountName = "";
	
	public String getSelAccountName() {
		return selAccountName;
	}

	public void setSelAccountName(String selAccountName) {
		this.selAccountName = selAccountName;
	}

	public ExtJSRichfaces () {
		
		accounts = new ArrayList<Account> (3);
		accounts.add(new Account (1, "Account1"));
		accounts.add(new Account (2, "Account2"));
		accounts.add(new Account (3, "Account3"));
	}
	
	public void accNumClickListener (ActionEvent evt){
		
		StringWriter sWriter = new StringWriter ();
		
		try {
			
			new JSONWriter (sWriter)
				.array()
					.array()
						.value(selectedAccount.getAccountNumber())
						.value(selectedAccount.getAccountName())
					.endArray()
				.endArray();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		jsonData = sWriter.toString();
		out.println (jsonData);
	}
	
	public class Account {
		
		private Integer accountNumber;
		private String accountName;
		
		public Integer getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Integer accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getAccountName() {
			return accountName;
		}
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		
		public Account (Integer accountNumber, String accountName){
			
			this.accountNumber = accountNumber;
			this.accountName = accountName;
		}
		
		@Override
		public String toString(){
			
			return "accountNumber = "+accountNumber+",accountName = "+ accountName+"\n";
		}
	}
	
	public static void main (String[] args){
		
		//test1
		out.println(new ExtJSRichfaces().accounts);
		
		//test2
		ExtJSRichfaces objRef = new ExtJSRichfaces();
		objRef.selectedAccount =  objRef.accounts.get(0);
		objRef.accNumClickListener (null);
		out.println(objRef.jsonData);
	}
}
