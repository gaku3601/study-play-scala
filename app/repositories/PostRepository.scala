package repositories

import models.Post
import scalikejdbc._

object PostRepository {
  def findAll: Seq[Post] = DB readOnly { implicit session =>
    sql"SELECT id, body FROM posts".map { rs =>
      Post(rs.int("id"), rs.string("body"))
    }.list().apply()
  }

  def add(post: Post): Unit = DB localTx { implicit session =>
    sql"INSERT INTO posts(body) VALUES(${post.body})".update().apply()
  }
}
