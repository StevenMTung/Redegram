package br.com.steventung.redegram.data.repository

import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.domain.model.User
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<Post>>
    fun setLikePost(post: Post)
    fun setCommentLike(commentId: String, postId: Long)
    fun getPostById(postId: Long): Flow<Post?>
    fun getUserProfile(): Flow<User>
    fun addNewCommentToPostCommentsList(postId: Long, newComment: Comment)
}