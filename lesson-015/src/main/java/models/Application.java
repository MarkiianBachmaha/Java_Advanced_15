package models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import utils.HibernateXMLUtil;

public class Application {
	public static void main(String[] args) {

		Session session = HibernateXMLUtil.getSessionFactory().openSession();

		Post post = new Post();
		post.setTitle("Something new");

		Comment comment = new Comment();
		comment.setAuthorName("Paul Dez");
		comment.setPosts(post);

		Set<Comment> comment1 = new HashSet<>();
		comment1.add(comment);
		post.setComments(comment1);

		Post post1 = new Post();
		post1.setTitle("TESLA");

		Comment comment2 = new Comment();
		comment2.setAuthorName("Elon Musk");
		comment2.setPosts(post);

		Set<Comment> comment3 = new HashSet<>();
		comment3.add(comment2);
		post.setComments(comment3);

		// save to DB
		Transaction transaction = session.beginTransaction();
		session.save(post);
		session.save(post1);
		transaction.commit();

		// read from DB
		System.out.println("Post #1");
		Post postDB1 = (Post) session.get(Post.class, 1);
		System.out.println(postDB1 + " -----> " + postDB1.getComments());
		System.out.println("Post #2");
		Post postDB2 = (Post) session.get(Post.class, 2);
		System.out.println(postDB2 + " -----> " + postDB2.getComments());

		System.out.println("Comment #1");
		Comment commentDB1 = (Comment) session.get(Comment.class, 1);
		System.out.println(commentDB1 + "----->" + commentDB1.getPost());
		System.out.println("Comment #2");
		Comment commentDB2 = (Comment) session.get(Comment.class, 2);
		System.out.println(commentDB2 + "----->" + commentDB2.getPost());

	}

}