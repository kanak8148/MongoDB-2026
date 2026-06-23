package in.co.rays.mongo;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import in.co.rays.util.MongoDBDatasource;


public class UserModel {
	
	public int nextPK() {

		int pk = 0;

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users = db.getCollection("st_users");

		Document doc = users.find().sort(new Document("id", -1)).first();

		if (doc != null) {
			pk = doc.getInteger("id");
		}

		return pk + 1;
	}
	
	public int add(UserBean bean) {

		int pk = nextPK();

		bean.setId(pk);

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users =
				db.getCollection("st_users");

		Document doc = new Document();

		doc.append("id", bean.getId());
		doc.append("firstName", bean.getFirstName());
		doc.append("lastName", bean.getLastName());
		doc.append("login", bean.getLogin());
		doc.append("password", bean.getPassword());

		users.insertOne(doc);

		System.out.println("Record Added Successfully");

		return pk;
	}

	
	public void delete(UserBean bean) {

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users = db.getCollection("st_users");

		users.deleteOne(eq("id", bean.getId()));

		System.out.println("Record Deleted Successfully");

	}

	public void update(UserBean bean) {

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users = db.getCollection("st_users");

		Document filter = new Document("id", bean.getId());

		Document update = new Document("$set", new Document("firstName", bean.getFirstName())
				.append("lastName", bean.getLastName()).append("login", bean.getLogin()));

		users.updateOne(filter, update);

		System.out.println("Record Updated Successfully");
	}

	public List<UserBean> search(UserBean bean) {

		List<UserBean> list = new ArrayList<>();

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users = db.getCollection("st_users");

		Document filter = new Document();

		if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {

			filter.append("firstName", bean.getFirstName());
		}

		if (bean.getLastName() != null && bean.getLastName().length() > 0) {

			filter.append("lastName", bean.getLastName());
		}

		if (bean.getLogin() != null && bean.getLogin().length() > 0) {

			filter.append("login", bean.getLogin());
		}

		FindIterable<Document> docs = users.find(filter);

		for (Document doc : docs) {

			UserBean beans = new UserBean();

			beans.setId(doc.getInteger("id"));
			beans.setFirstName(doc.getString("firstName"));
			beans.setLastName(doc.getString("lastName"));
			beans.setLogin(doc.getString("login"));
			beans.setPassword(doc.getString("password"));

			list.add(beans);
		}

		return list;

	}

	public UserBean authenticate(String login, String password) {

		MongoDatabase db = MongoDBDatasource.getDatabase();

		MongoCollection<Document> users = db.getCollection("st_users");

		Document filter = new Document("login", login).append("password", password);

		Document doc = users.find(filter).first();

		if (doc != null) {

			UserBean bean = new UserBean();

			bean.setId(doc.getInteger("id"));
			bean.setFirstName(doc.getString("firstName"));
			bean.setLastName(doc.getString("lastName"));
			bean.setLogin(doc.getString("login"));
			bean.setPassword(doc.getString("password"));

			System.out.println("User Valide");

			return bean;
		}

		return null;

	}


}
