package xtec.eott.controller.dashboard;

import java.util.ArrayList;

public class DashboardClient {
	
	public float points;
	public int order_by_month;
	public int order_by_category;
	public float points_to_colons;
	public float points_by_month;
	public ArrayList<String> last_transactions;
	
	public String get_points(int user_id) { return ""; }
	
	public String get_order_by_month(int user_id, String month) { return ""; }
	
	public String get_order_by_category(int user_id) { return ""; }
	
	public String get_points_to_colons(float points) { return ""; }
	
	public String get_points_by_mont(String month) { return ""; }
	
	public String get_order_transaction(int order_id) { return ""; }
	

}
