package in.co.rays.mongo;

import java.util.List;

public class TestUserModel {
	
	public static void main(String[] args) {
		           testadd();
				// testdelete();
				// testupdate();
				// testserch();
				// testauthenticate();

	}
	private static void testauthenticate() {
		// TODO Auto-generated method stub
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		bean.setLogin("Ramesh777@gmail.com");
		bean.setPassword("Ramesh@1234");

		List<UserBean> list = model.search(bean);

		System.out.println("Total Records = " + list.size());
		System.out.println("User Invalide");

		for (UserBean u : list) {

			System.out.println("ID = " + u.getId());
			System.out.println("First Name = " + u.getFirstName());
			System.out.println("Last Name = " + u.getLastName());
			System.out.println("Login = " + u.getLogin());

			System.out.println("--------------------");
		}

	}

	private static void testserch() {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setFirstName("Rajesh");

		List<UserBean> list = model.search(bean);

		System.out.println("Total Records = " + list.size());

		for (UserBean u : list) {

			System.out.println("ID = " + u.getId());
			System.out.println("First Name = " + u.getFirstName());
			System.out.println("Last Name = " + u.getLastName());
			System.out.println("Login = " + u.getLogin());

			System.out.println("--------------------");
		}

	}

	private static void testupdate() {
		// TODO Auto-generated method stub
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		bean.setId(1);
		bean.setFirstName("hyyyy");
		bean.setLastName("soni");
		bean.setLogin("kanak@gmail.com");
		bean.setPassword("kanak1234");
		model.update(bean);

	}

	private static void testdelete() {

		UserBean beans = new UserBean();
		UserModel model = new UserModel();
		beans.setId(1);
		model.delete(beans);

	}

	private static void testadd() {
		// TODO Auto-generated method stub
		UserBean beans = new UserBean();
		UserModel model = new UserModel();

		beans.setFirstName("Ramesh");
		beans.setLastName("soni");
		beans.setLogin("Ramesh@gmail.com");
		beans.setPassword("Ramesh@1234");
		model.add(beans);
		System.out.println("Add Sussecsfully");

	}

}

	
	


