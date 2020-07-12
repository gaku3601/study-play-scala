package services

import javax.inject.Inject
import models.Post
import repositories.PostRepository

class PostService @Inject()(pr: PostRepository) {
  def getAll: Seq[Post] = pr.findAll

  def add(body: String): Unit = {
    val post = Post(body)
    pr.add(post)
  }
}
